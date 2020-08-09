package main.java.connections;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javafx.scene.control.Alert;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource ds;
    static{
        String username;
        String url;
        String password;
        String driver;
        Properties prop = null;
        try (InputStream input = new FileInputStream("target/classes/config.properties")) {
            prop = new Properties();
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert prop != null;
        username=prop.getProperty("db.user");
        url=prop.getProperty("db.url");
        password=prop.getProperty("db.password");
        driver=prop.getProperty("db.driver");
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        ds = new HikariDataSource(config);
    }
    public static Connection getConnection() {
        Connection connection=null;
        try {
            connection= ds.getConnection();

        }catch (SQLException e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Connection Time Out!");
            alert.show();
        }
        return connection;
    }
    private Connector(){}
}
