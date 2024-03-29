package main.java;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import main.java.connections.Login;
import main.java.helpers.Helper;

public class MainController {
    @FXML
    JFXButton b1;

    @FXML
    public void signInButtonAction(){

        Helper.changeScene("Login.fxml",b1);
    }

    @FXML
    public void signUpButtonAction(){

        Helper.changeScene( "Reg.fxml",b1);
    }

    @FXML
    public void login()  {
        Login login = new Login();
        try {
            login.facebookLogin(b1);
        }catch (IllegalStateException e){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error login in!");
                alert.show();
        }
    }
}
