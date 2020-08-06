package main.java.connections;

import main.java.requirements.Patient;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PatientDataAccessor extends UserDataAccessor{
    public Patient getPatient(String username) throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from Patient inner join \"USER\" on PATIENT.PATIENT_USER_NAME = \"USER\".USER_NAME where USER_NAME='"+username+"'")
        ){
            Patient patient=new Patient(getUser(username, rs));
            patient.setAllergies(rs.getString("Allergies"));
            patient.setBloodType(rs.getString("Blood_type"));
            patient.setCost(rs.getInt("Debt"));
            return patient;
        }
    }
    public void setPatient(Patient patient){
        try (
                PreparedStatement stmnt = connection.prepareStatement("insert into Patient (Allergies,Blood_type,debt,patient_user_name) values ((?),(?),(?),(?))")
        ){
            setUser(patient);
            stmnt.setString(1,patient.getAllergies());
            stmnt.setString(2,patient.getBloodType());
            stmnt.setInt(3,patient.getCost());
            stmnt.setString(4,patient.getUsername());
            stmnt.execute();
        } catch ( SQLException e) {
            e.printStackTrace();
        }
    }


}
