package main.java.connections;

import javafx.collections.ObservableList;
import main.java.requirements.Clinic;
import main.java.requirements.Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
                Clinic clinic = new Clinic(id,name,phoneNumber,address, type,balance);
                clinic.setDoctor(doctor);
                clinicList.add(clinic);
            }
            return clinicList;
        }
    }
    public Clinic getClinic(String clinicId) throws SQLException {
        System.out.println(clinicId);
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from CLINIC inner join DOCTOR on id=clinic_ID inner join \"USER\" on DOCTOR.DOCTOR_USER_NAME = \"USER\".USER_NAME where clinic_id='"+clinicId+"'")
        ){
            if(rs.next()) {
                Clinic clinic = new Clinic(rs.getInt("id"), rs.getString("name"), rs.getString("phone_number"), rs.getString("address"), rs.getString("type"), rs.getInt("balance"));
                Doctor doctor = new Doctor(rs.getString("first_name"), rs.getString("last_name"), rs.getString("DOCTOR_USER_NAME"));
                clinic.setDoctor(doctor);
                return clinic;
            } else {
                return null;
            }
        }
    }

}
