package main.java.connections;

import main.java.entities.Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServicesDataAccessor extends DataAccessor{
    public List<Services> getServicesList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from Services")
        ){
            List<Services> servicesList=new ArrayList<>();
            while (rs.next()){
                Services service=new Services();
                service.setId(rs.getInt("id"));
                service.setDuration(rs.getInt("duration"));
                service.setPrice(rs.getInt("price"));
                service.setName(rs.getString("name"));
                servicesList.add(service);
            }
            return servicesList;
        }
    }
}
