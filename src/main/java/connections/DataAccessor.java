package main.java.connections;

import java.sql.Connection;

public class DataAccessor {
    protected final Connection connection;

    protected DataAccessor(){
            connection=Connector.getConnection();
    }
}
