package main.code.connections;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.User;
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
import java.util.Properties;

import static com.restfb.logging.RestFBLogger.CLIENT_LOGGER;

public class Login {
    private Connection connection;
    private BCryptPasswordEncoder passwordEncoder;
    boolean loginSuccess(String username,String password) throws SQLException {
        try (
                PreparedStatement stmnt = connection.prepareStatement("select PASSWORD from \"USER\" where USER_NAME="+username+"");
                ResultSet rs = stmnt.executeQuery()
        ){
            return passwordEncoder.matches(password,rs.getString("password"));
        }
    }
    boolean signUp(String username)  throws SQLException {
        try (
                PreparedStatement stmnt = connection.prepareStatement("select * from \"USER\" where USER_NAME="+username+"");
                ResultSet rs = stmnt.executeQuery()
        ){
            if(rs.next()){
                return false;
            }
            return true;
        }
    }
    public Login(){
        Properties prop;
        try (InputStream input = new FileInputStream("target/classes/config.properties")) {
//            passwordEncoder= new BCryptPasswordEncoder();
            prop = new Properties();
            prop.load(input);
            connection=Connector.getConnection();
            Object key;
            appId =prop.getProperty("fb.appId");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }
    private static final String SUCCESS_URL = "https://www.facebook.com/connect/login_success.html";
    void saveUserData(String token){
        FacebookClient facebookClient =new DefaultFacebookClient(token,Version.LATEST);
        User user = facebookClient.fetchObject("me", User.class, Parameter.with("fields", "picture,user_location,email,user_birthday,location,user_gender"));
        System.out.println(user.getEmail());
        try (
                PreparedStatement stmnt = connection.prepareStatement("select * from \"USER\" where USER_NAME="+user.getEmail()+"");
                ResultSet rs = stmnt.executeQuery()
        ){
            if(rs.next()){
                //Todo:implement signIn
            }else {
                //Todo:implement signUp
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private String appId;
    public void facebookLogin() throws IllegalStateException{
        // parse arguments
        Stage stage = new Stage();
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // create the scene
        stage.setTitle("Facebook Login Example");
        // use quite a wide window to handle cookies popup nicely
        stage.setScene(new Scene(new VBox(webView), 1000, 600, Color.web("#666970")));
        stage.show();
        ScopeBuilder scope =new ScopeBuilder();
        scope.addPermission(FacebookPermissions.EMAIL);
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
                        throw new IllegalStateException("dialog done");
                    } else if ("https://www.facebook.com/dialog/close".equals(newValue)) {
                        throw new IllegalStateException("dialog closed");
                    }
                });
    }
}
