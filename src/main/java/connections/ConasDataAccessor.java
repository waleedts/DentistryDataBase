package main.java.connections;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConasDataAccessor extends DataAccessor{
    public void setConnections(boolean[]services,int AppointmentID){
        try (
                PreparedStatement stmnt= connection.prepareStatement("insert into CONAS (APPOINTMENT_ID, SERVICES_ID) values ((?),(?))")
        ){
            stmnt.setInt(1,AppointmentID);
            for(int i=0;i<15;i++){
                if(services[i]){
                    stmnt.setInt(2,i+1);
                    stmnt.execute();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
