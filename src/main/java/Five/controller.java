package main.java.Five;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import main.java.*;
import main.java.connections.*;
import main.java.helper.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class controller implements Initializable {
    @FXML
    JFXButton b1;
    @FXML
    JFXButton b2;
    @FXML
    JFXRadioButton r1;
    @FXML
    JFXRadioButton r2;
    @FXML
    ToggleGroup t;
    @FXML
    LimitedTextField passwordField;
    @FXML
    JFXTextField usernameField;
    @FXML
    ProgressIndicator progressIndicator;
    @FXML
    Pane progressPane;
    @FXML
    public void signIn(){
        Service<Boolean> service= new Service<>() {
            @Override
            protected Task<Boolean> createTask() {
                return new Task<>() {
                    @Override
                    protected Boolean call() {
                        Login login = new Login();
                        boolean loginSuccess = false;
                        try {
                            loginSuccess = login.loginSuccess(usernameField.getText(), passwordField.getText(), r1.isSelected());
                        } catch (SQLException e) {
                            System.out.println("Exception in method (SignIn) in class (loginController)" + e.getMessage());
                        }
                        return loginSuccess;
                    }
                };
            }
        };
        service.start();
        progressIndicator.visibleProperty().bind(service.runningProperty());
        progressPane.visibleProperty().bind(service.runningProperty());
        service.setOnSucceeded(workerStateEvent -> {
            boolean loginSuccess = service.getValue();
            if (loginSuccess) {
                Helper.changeScene("Six_Page_GUI.fxml",b1);
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error signing in!");
                alert.setContentText("The username password combination does not match!");
                alert.showAndWait();
            }
        });
        service.setOnFailed(workerStateEvent -> service.restart());
    }
    @FXML
    public void reurnBack(){
        Helper.changeScene("mainGUI.fxml",b1);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        r1.setSelected(true);
        t = new ToggleGroup();
        progressIndicator=new ProgressIndicator();
        progressIndicator.setVisible(false);
        progressPane.setVisible(false);
    }
}

