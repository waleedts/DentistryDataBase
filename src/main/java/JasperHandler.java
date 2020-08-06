package main.java;

import main.java.connections.Connector;
import main.java.connections.DataAccessor;
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
import java.util.HashMap;
import java.util.Map;

public class JasperHandler extends DataAccessor {

    public void makeReport(int x) {
        try {
            InputStream in = new FileInputStream(new File("src/main/resources/Tree.jrxml"));
            JasperDesign design = JRXmlLoader.load(in);
            JasperReport report = JasperCompileManager.compileReport(design);
            HashMap map=new HashMap();
            map.put("appointmentID",x);
            JasperPrint print = JasperFillManager.fillReport(report, map, connection);
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(print));
            frame.pack();
            frame.setVisible(true);
            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    isFinished=true;
                }
            });
        } catch (FileNotFoundException | JRException throwables) {
            throwables.printStackTrace();
        }

    }
    boolean isFinished=false;
    public boolean isFinished(){
        return isFinished;
    }
}
