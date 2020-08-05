package main.java;

import main.java.connections.Connector;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

public class JasperHandler {

    public static void main(String[] args) {
        try {
            Connection connection;
            connection = Connector.getConnection();
            InputStream in = new FileInputStream(new File("src/main/resources/Tree.jrxml"));
            JasperDesign design = JRXmlLoader.load(in);
            JasperReport report = JasperCompileManager.compileReport(design);
            JasperPrint print = JasperFillManager.fillReport(report, null, connection);
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(print));
            frame.pack();
            frame.setVisible(true);
        } catch (SQLException | FileNotFoundException | JRException throwables) {
            throwables.printStackTrace();
        }
    }
}
