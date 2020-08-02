package main.java.connections;

import main.java.requirements.Patient;

import java.sql.*;

public class PatientDataAccessor extends UserDataAccessor{
    public Patient getPatient(String username) throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from Patient inner join \"USER\" on PATIENT.PATIENT_USER_NAME = \"USER\".USER_NAME where USER_NAME="+username+"")
        ){
            Patient patient=(Patient)getUser(username, rs);
            patient.setAllergies(rs.getString("Allergies"));
            patient.setBloodType(rs.getString("Blood_type"));
            patient.setCost(rs.getInt("Debt"));
            return patient;
        }
    }
}
