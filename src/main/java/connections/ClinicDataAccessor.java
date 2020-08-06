package main.java.connections;

import javafx.collections.ObservableList;
import main.java.requirements.Clinic;
import main.java.requirements.Doctor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
public class ClinicDataAccessor extends DataAccessor{
    public List<Clinic> getClinicList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from CLINIC inner join DOCTOR on id=clinic_ID inner join \"USER\" on DOCTOR.DOCTOR_USER_NAME = \"USER\".USER_NAME")
        ){
            List<Clinic> clinicList = new ArrayList<>();
            while (rs.next()) {
                int id=rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phoneNumber=rs.getString("phone_number");
                Doctor doctor = new Doctor(rs.getString("first_name"), rs.getString("last_name"),rs.getString("DOCTOR_USER_NAME"));
                int balance=rs.getInt("balance");
                String type=rs.getString("type");
                Clinic clinic = new Clinic(id,name,phoneNumber,address, type,balance,Base64.getDecoder().decode(rs.getString("profile_pic")));
                clinic.setDoctor(doctor);
                clinicList.add(clinic);
            }
            return clinicList;
        }
    }



    public List<Clinic> getUnusedClinics() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from CLINIC left join DOCTOR D on CLINIC.ID = D.CLINIC_ID where D.CLINIC_ID is null")
        ){
            List<Clinic> clinicList = new ArrayList<>();
            while (rs.next()) {
                int id=rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phoneNumber=rs.getString("phone_number");
                int balance=rs.getInt("balance");
                String type=rs.getString("type");
                Clinic clinic = new Clinic(id,name,phoneNumber,address, type,balance,Base64.getDecoder().decode(rs.getString("profile_pic")));
                clinicList.add(clinic);
            }
            return clinicList;
        }
    }
    public Clinic getClinic(String clinicId) throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from CLINIC inner join DOCTOR on id=clinic_ID inner join \"USER\" on DOCTOR.DOCTOR_USER_NAME = \"USER\".USER_NAME where clinic_id='"+clinicId+"'")
        ){
            if(rs.next()) {
                Clinic clinic = new Clinic(rs.getInt("id"), rs.getString("name"), rs.getString(3), rs.getString(3), rs.getString("type"), rs.getInt("balance"),Base64.getDecoder().decode(rs.getString(7)));
                Doctor doctor = new Doctor(rs.getString("first_name"), rs.getString("last_name"), rs.getString("DOCTOR_USER_NAME"));
                doctor.setProfilePic(Base64.getDecoder().decode(rs.getString(19)));
                clinic.setDoctor(doctor);
                return clinic;
            } else {
                return null;
            }
        }
    }
    public void setClinic(String name,String type,String phone_number,String address,byte[] profile_pic) throws SQLException {
        try (
                PreparedStatement stmnt = connection.prepareStatement("insert into Clinic (balance,name,type,address,phone_number,profile_pic) values ((?),(?),(?),(?),(?),(?))");
        ){
                stmnt.setInt(1,0);
                stmnt.setString(2,name);
                stmnt.setString(3,type);
                stmnt.setString(4,address);
                stmnt.setString(5,phone_number);
                stmnt.setString(6,Base64.getEncoder().encodeToString(profile_pic));
                stmnt.execute();
        }
    }

}
