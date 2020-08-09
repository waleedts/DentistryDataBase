package main.java.clinicReg;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.LimitedTextField;
import main.java.connections.ClinicDataAccessor;
import main.java.helper.Helper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller {
    @FXML
    private AnchorPane aa;


    @FXML
    private JFXButton regBtn;
    @FXML
    private LimitedTextField nameField,typeField,addressField,phoneField;
    byte[] image;
    public void toUpload(){
        Platform.runLater(() -> {
            final FileChooser d = new FileChooser();
            Stage s = (Stage) aa.getScene().getWindow();
            File file = d.showOpenDialog(s);
            if (file != null) {
                try {
                    image = Files.readAllBytes(file.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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
