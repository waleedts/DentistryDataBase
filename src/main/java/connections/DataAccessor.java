package main.java.connections;

import java.sql.Connection;
import java.sql.SQLException;

public class DataAccessor {
    protected Connection connection;

    protected DataAccessor(){
        try {
            connection=Connector.getConnection();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
