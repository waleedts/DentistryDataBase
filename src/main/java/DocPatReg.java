package main.java;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import main.java.connections.*;

import java.net.URL;
import java.util.ResourceBundle;

public class DocPatReg implements Initializable {
    @FXML
    Pane doctorShadow;
    @FXML
    Pane patientShadow;
    @FXML
    JFXButton patientBtn;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(CurrentUser.isDoctor()){
            doctorShadow.setVisible(false);
        }else{
            patientShadow.setVisible(false);
        }
    }
}
