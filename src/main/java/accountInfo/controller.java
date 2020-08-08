package main.java.accountInfo;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import main.java.connections.CurrentUser;
import main.java.connections.Login;
import main.java.helper.Helper;

import java.net.URL;
import java.util.ResourceBundle;

public class controller implements Initializable {
    @FXML
    JFXButton b2;
    @FXML
    JFXButton clinicsBtn;
    public void signOut(){
        Login login=new Login();
        login.signOut();
        Helper.changeScene("mainGUI.fxml",b2);
    }

    public void bill(ActionEvent actionEvent) {
        Helper.changeScene("Bills_GUI.fxml",b2);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(CurrentUser.isDoctor()){
            clinicsBtn.setText("Create Post");
        }
        clinicsBtn.setOnMouseClicked(e->{
            Helper.changeScene("CP_GUI.fxml",clinicsBtn);
        });
    }
}
