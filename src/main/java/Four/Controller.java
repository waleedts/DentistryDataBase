package main.java.Four;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.connections.Login;
import main.java.helper.Helper;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    JFXTextField firstNameField,lastNameField,userField,passField,addressField,phoneField;
    @FXML
    JFXButton b1,b2,b3;
    @FXML
    JFXRadioButton r1,r2,r3;
    @FXML
    ToggleGroup t,doctorPatient;
    @FXML
    JFXRadioButton doctorRadio;
    int depth = 5;
    private StackPane parentContainer;
    @FXML
    JFXDatePicker birthDate;
    @FXML
    Pane p;
    @FXML
    private AnchorPane aa;
    @FXML
    ProgressIndicator progressIndicator;
    private Desktop desktop = Desktop.getDesktop();

    public Controller(){
        t=new ToggleGroup();
        doctorPatient=new ToggleGroup();

    }

    Image image;
    public void toUpload(){
        Platform.runLater(() -> {
            try {
                final FileChooser d = new FileChooser();
                Stage s = (Stage) aa.getScene().getWindow();
                File file = d.showOpenDialog(s);
                if (file != null) {
                    desktop.open(file);
                    image= new Image(file.toURI().toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    Date date=null;
    public void signUpAction() {
        char gender;
        if(r1.isSelected()){
            gender='m';
        }else if(r2.isSelected()){
            gender='f';
        }else{
            gender='n';
        }


        if(birthDate.getValue()!=null){
            date=java.sql.Date.valueOf(birthDate.getValue());
        }
        Service<Boolean> service=new Service<Boolean>() {
            @Override
            protected Task<Boolean> createTask() {
                return new Task<Boolean>() {
                    @Override
                    protected Boolean call() {
                        boolean loginSuccess=false;
                        Login login=new Login();
                        try {
                            System.out.println("out");
                             loginSuccess=login.signUp(firstNameField.getText(),lastNameField.getText(),addressField.getText(),userField.getText(),passField.getText(),phoneField.getText(),gender,date,image, doctorRadio.isSelected());
                        }catch (SQLException e){
                            System.out.println("Exception in method (SignIn) in class (loginController)"+e.getMessage());
                        }
                        return loginSuccess;
                    }
                };
            }
        };
        service.start();
        progressIndicator.visibleProperty().bind(service.runningProperty());
        service.setOnSucceeded(workerStateEvent -> {
            boolean loginSuccess = service.getValue();
            if (loginSuccess) {
                Helper.changeScene("First_Page_GUI.fxml",b1);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error signing in!");
                alert.setContentText("The username password combination does not match!");
                alert.showAndWait();
            }
        });
        // FIXME: 7/25/2020 ix:print statements are a miss
        service.setOnFailed(workerStateEvent -> service.restart());
    }
    public void returnBack(){
        Helper.changeScene("mainGUI.fxml",b1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JFXDepthManager.setDepth(p, 1);
        JFXDepthManager.setDepth(b2, 5);
        JFXDepthManager.setDepth(b3, 5);
        doctorRadio.setSelected(true);
        progressIndicator.setVisible(false);
        r1.setSelected(true);
    }
}
