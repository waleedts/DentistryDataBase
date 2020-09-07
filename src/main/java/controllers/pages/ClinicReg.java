package main.java.controllers.pages;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import main.java.helpers.LimitedTextField;
import main.java.connections.ClinicDataAccessor;
import main.java.helpers.Helper;

import java.sql.SQLException;

public class ClinicReg {
    @FXML
    private AnchorPane aa;


    @FXML
    private JFXButton regBtn;
    @FXML
    private LimitedTextField nameField,typeField,addressField,phoneField;
    byte[] image;
    public void toUpload(){
        image=Helper.toUpload(aa);
    }
    public void registerClinic(){
        if(image==null||nameField.getText()==null||typeField.getText()==null||addressField.getText()==null||phoneField.getText()==null){
            Alert alert =new Alert(Alert.AlertType.WARNING);
            alert.setContentText("All fields must be filled before registration");
        }else{
            ClinicDataAccessor dataAccessor=new ClinicDataAccessor();
            try {
                dataAccessor.setClinic(nameField.getText(), typeField.getText(), phoneField.getText(), addressField.getText(), image);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Helper.changeScene("Six_Page_GUI.fxml",regBtn);
        }
    }
    public void goBack(){
        Helper.changeScene("Six_Page_GUI.fxml",regBtn);
    }

}
