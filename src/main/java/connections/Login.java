package main.java.connections;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;
import com.sun.javafx.application.PlatformImpl;
import javafx.application.Platform;
import javafx.scene.image.Image;
import main.java.requirements.User;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Date;
import java.util.Properties;

import static com.restfb.logging.RestFBLogger.CLIENT_LOGGER;

public class Login{
    private  Connection connection;
    private  BCryptPasswordEncoder passwordEncoder;
    public  boolean loginSuccess(String username,String password,boolean isDoctor) throws SQLException {
        try (
                PreparedStatement stmnt = connection.prepareStatement("select PASSWORD from \"USER\" where USER_NAME='"+username+"'");
                ResultSet rs = stmnt.executeQuery()
        ){
            if(rs.next())
            {
                System.out.println(passwordEncoder.encode(password));
                boolean matches =passwordEncoder.matches(password,rs.getString("password"));
                if (matches) {
                    CurrentUser.setCurrentUser(username, isDoctor);
                }
                return matches;
            }else{
                return false;
            }
        }
    }
    public boolean signUp(String firstName, String LastName, String address, String username, String password, String phoneNumber, char gender,Date date, Image image, boolean isDoctor)  throws SQLException {
        try (
                PreparedStatement stmnt = connection.prepareStatement("select * from \"USER\" where USER_NAME="+username+"");
                ResultSet rs = stmnt.executeQuery()
        ){
            System.out.println("im innn");
            if(rs.next()){
                return false;
            }
            User user=new User(firstName,LastName,username);
            user.setPersonalInfo(address,phoneNumber,gender,date,image);
            user.setPassWord(passwordEncoder.encode(password));
            CurrentUser.setCurrentUser(user,isDoctor);
            System.out.println("im ouuuut");
            return true;
        }
    }
    public Login(){
        Properties prop;
        try (InputStream input = new FileInputStream("target/classes/config.properties")) {
            passwordEncoder= new BCryptPasswordEncoder();
            prop = new Properties();
            prop.load(input);
            connection=Connector.getConnection();
            appId =prop.getProperty("fb.appId");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }
    private  final String SUCCESS_URL = "https://www.facebook.com/connect/login_success.html";
    void  saveUserData(String token){
        FacebookClient facebookClient =new DefaultFacebookClient(token,Version.LATEST);
        com.restfb.types.User user = facebookClient.fetchObject("me", com.restfb.types.User.class, Parameter.with("fields", "picture,location,email,birthday,gender"));
        System.out.println(user.getLocation()+"\n"+user.getEmail()+"\n"+user.getBirthday()+"\n"+user.getGender());
        try (
                PreparedStatement stmnt = connection.prepareStatement("select * from \"USER\" where USER_NAME='" +user.getEmail()+"'");
                ResultSet rs = stmnt.executeQuery()
        ){
            if(rs.next()){
                CurrentUser.setCurrentUser(user.getEmail(),false);
            }else {
                //Todo:implement signUp
                System.out.println("I have not seen you before");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    boolean loginSucceeded=false;
    private String appId;
    public  boolean facebookLogin(){
        PlatformImpl.runAndWait(()->{
            Stage stage = new Stage();
            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();
            // create the scene
            stage.setTitle("Facebook Login Example");
            // use quite a wide window to handle cookies popup nicely
            stage.setScene(new Scene(new VBox(webView), 800, 400, Color.web("#666970")));
            stage.show();
            ScopeBuilder scope =new ScopeBuilder();
            scope.addPermission(FacebookPermissions.EMAIL);
            scope.addPermission(FacebookPermissions.USER_GENDER);
            scope.addPermission(FacebookPermissions.USER_BIRTHDAY);
            scope.addPermission(FacebookPermissions.USER_LOCATION);
            System.out.println(scope.toString());
            // obtain Facebook access token by loading login page
            DefaultFacebookClient facebookClient = new DefaultFacebookClient(Version.VERSION_7_0);
            String loginDialogUrl = facebookClient.getLoginDialogUrl(appId, SUCCESS_URL, scope);
            webEngine.load(loginDialogUrl + "&display=popup&response_type=token");
            webEngine.locationProperty().addListener((property, oldValue, newValue) -> {
                if (newValue.startsWith(SUCCESS_URL)) {
                    // extract access token
                    CLIENT_LOGGER.debug(newValue);
                    int codeOffset = newValue.indexOf("token=");

                    String []token = newValue.substring(codeOffset + "token=".length()).split("&");
                    saveUserData(token[0]);
                    stage.close();
                    loginSucceeded=true;
                } else if ("https://www.facebook.com/dialog/close".equals(newValue)) {
                    throw new IllegalStateException("dialog closed");
                }
            });

        });
        return loginSucceeded;
    }

}
