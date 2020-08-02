package main.java.connections;

import main.java.requirements.Clinic;

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
            List<Clinic> doctorsList = new ArrayList<>();
            while (rs.next()) {
                int id=rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phoneNumber=rs.getString("phone_number");
                String doctorName = rs.getString("first_name")+" "+rs.getString("last_name");
                int balance=rs.getInt("balance");
                String type=rs.getString("type");
                Clinic clinic = new Clinic(id,name,phoneNumber,address,doctorName,type,balance);
                doctorsList.add(clinic);
            }
            return doctorsList ;
        }
    }

}
