package main.java.connections;

import main.java.requirements.Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DoctorDataAccessor extends UserDataAccessor{
    public Doctor getDoctor(String username) throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from Doctor inner join \"USER\" on Doctor.DOCTOR_USER_NAME = \"USER\".USER_NAME where USER_NAME="+username+"")
        ){
            Doctor doctor = (Doctor) getUser(username, rs);
            doctor.setSalary(rs.getInt("salary"));
            doctor.setClinicId(rs.getInt("clinic_id"));
            return doctor;
        }
    }


    public void setDoctor(){

    }
}
