package main.java.connections;

import com.sun.mail.iap.ByteArray;
import javafx.embed.swing.SwingFXUtils;
import main.java.requirements.Doctor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class DoctorDataAccessor extends UserDataAccessor{
    public Doctor getDoctor(String username) throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from Doctor inner join \"USER\" on Doctor.DOCTOR_USER_NAME = \"USER\".USER_NAME where USER_NAME='"+username+"'")
        ){
            Doctor doctor = new Doctor(getUser(username, rs)) ;
            doctor.setSalary(rs.getInt("salary"));
            doctor.setClinicId(rs.getInt("clinic_id"));
            return doctor;
        }
    }


    public void setDoctor(Doctor doctor) throws SQLException {
        try (
                PreparedStatement stmnt = connection.prepareStatement("insert into doctor (salary,doctor_user_name) values ((?),(?))")
        ){
                setUser(doctor);
                stmnt.setInt(1,doctor.getSalary());
                stmnt.setInt(2,doctor.getClinicId());
                stmnt.execute();
            } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
