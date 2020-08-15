package main.java.connections;

import main.java.entities.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentDataAccessor extends DataAccessor {
    public List<Appointment> getAppointmentList(String username ,boolean doc) throws SQLException {
        String sqlStat;
        if(doc){
            sqlStat="select u.FIRST_NAME,u.LAST_NAME,u.PHONE_NUMBER,p.ALLERGIES,p.BLOOD_TYPE,start_time,sum(Duration) as Duration,sum(s.Price) as total from Appointment inner join CLINIC C2 on C2.ID = Appointment.CLINIC_ID  inner join DOCTOR D on APPOINTMENT.CLINIC_ID = D.CLINIC_ID inner join CONAS C2 on APPOINTMENT.ID = C2.APPOINTMENT_ID inner join SERVICES S on C2.SERVICES_ID = S.ID inner join PATIENT P on APPOINTMENT.PATIENT_USERNAME = P.PATIENT_USER_NAME inner join \"USER\" U on P.PATIENT_USER_NAME = U.USER_NAME where D.DOCTOR_USER_NAME='"+username+"' group by u.FIRST_NAME,u.LAST_NAME,u.PHONE_NUMBER,start_time,p.ALLERGIES,p.BLOOD_TYPE";
        }else{
            sqlStat="select C2.name,c2.PHONE_NUMBER,start_time,sum(Duration) as Duration,sum(s.Price) as total from Appointment inner join CLINIC C2 on C2.ID = Appointment.CLINIC_ID  inner join CONAS C2 on APPOINTMENT.ID = C2.APPOINTMENT_ID inner join SERVICES S on C2.SERVICES_ID = S.ID where Appointment.Patient_UserName='" + username + "' group by C2.name,c2.PHONE_NUMBER,start_time,APPOINTMENT.ID";
        }
        try (

            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery(sqlStat)

        ){
            List<Appointment> appointmentList=new ArrayList<>();
            while(rs.next()){
                Appointment appointment=new Appointment();
                appointment.setTime(rs.getDate("Start_time"));
                appointment.setDuration(rs.getInt("Duration"));
                appointment.setTotal(rs.getInt("total"));
                if(!doc){
                    appointment.setClinicName(rs.getString(1));
                    appointment.setClinicNumber(rs.getString(2));
                }else {
                    appointment.setPatientAllergies(rs.getString("ALLERGIES"));
                    appointment.setPatientName(rs.getString("First_Name")+" "+rs.getString("Last_Name"));
                    appointment.setPatientBloodType(rs.getString("blood_type"));
                    appointment.setPatientNumber(rs.getString("Phone_number"));
                }
                appointmentList.add(appointment);
            }
            return appointmentList;
        }
    }
    public void setAppointment(Date start_time,String patient_username,int clinic_ID) throws SQLException {
        try (
                PreparedStatement stmnt = connection.prepareStatement("insert into Appointment (start_time, patient_username, clinic_id) values ((?),(?),(?)) ")
        ){
            stmnt.setDate(1, new java.sql.Date(start_time.getTime() ));
            stmnt.setString(2,patient_username);
            stmnt.setInt(3,clinic_ID);
            stmnt.execute();
        }
    }
    public int getAppointment(Date start_time,String patient_username,int clinic_ID) throws SQLException {
        try (
                PreparedStatement stmnt = connection.prepareStatement("select ID from Appointment where START_TIME=(?) and PATIENT_USERNAME=(?) and CLINIC_ID=(?)")
        ){
            stmnt.setDate(1, new java.sql.Date(start_time.getTime()));
            stmnt.setString(2,patient_username);
            stmnt.setInt(3,clinic_ID);
            ResultSet rslt =stmnt.executeQuery();
            rslt.next();
            int i=rslt.getInt("ID");
            rslt.close();
            stmnt.close();
            return i;
        }
    }


}
