package main.java.helpers;

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
import java.util.Map;

public class JasperHandler extends DataAccessor {
    public void makeReport(Map<String,Object> map,boolean isAppointmentBill) {
        try {
            String string;
            if(isAppointmentBill){
                string="AppointmentBill.jrxml";
            }else{
                string="Service.jrxml";
            }
            InputStream in = new FileInputStream(new File("src/main/resources/"+string));
            JasperDesign design = JRXmlLoader.load(in);
            JasperReport report = JasperCompileManager.compileReport(design);

            JasperPrint print = JasperFillManager.fillReport(report, map, connection);
            JFrame frame = new JFrame("Report");
            frame.getContentPane().add(new JRViewer(print));
            frame.pack();
            frame.setVisible(true);
        } catch (FileNotFoundException | JRException throwables) {
            throwables.printStackTrace();
        }

    }
}
