package main.java.connections;

import main.java.requirements.Appointment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDataAccessor extends DataAccessor {
    List<Appointment> getAppointmentList(String username) throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select start_time,sum(Duration) as Duration,teeth_location,Description,sum(Services.Price) as total from Appointment inner join Services on Appointment.id=Services.Appointment_ID  where Appointment.Patient_UserName='"+username+"'")
        ){
            List<Appointment> appointmentList=new ArrayList<>();
            while(rs.next()){
                Appointment appointment=new Appointment();
                appointment.setDescription(rs.getString("Description"));
                appointment.setTime(rs.getDate("Start_time"));
                appointment.setDuration(rs.getInt("Duration"));
                appointment.setLocationOfTeeth(rs.getString("teeth_location"));
                appointment.setTotal(rs.getInt("total"));
                appointmentList.add(appointment);
            }
            return appointmentList;
        }
    }
}
