package main.java.controllers;

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
import javafx.scene.layout.Pane;
import main.java.connections.*;
import main.java.helper.*;
import org.kordamp.ikonli.javafx.FontIcon;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginPage implements Initializable {
    @FXML
    private FontIcon b1;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXRadioButton r1;
    @FXML
    private LimitedPasswordField passwordField;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private Pane progressPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        r1.setSelected(true);
        progressIndicator.setVisible(false);
        progressPane.setVisible(false);
        b1.setOnMouseClicked(e-> Helper.changeScene("mainGUI.fxml",b2));
        b2.setOnMouseClicked(e->{
            Service<Boolean> service= new Service<>() {
                @Override
                protected Task<Boolean> createTask() {
                    return new Task<>() {
                        @Override
                        protected Boolean call() {
                            main.java.connections.Login login = new main.java.connections.Login();
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
                    if(CurrentUser.isDoctor()){
                        Helper.changeScene("Bills_GUI.fxml",b2);
                    }else{
                        Helper.changeScene("ClinicsList.fxml",b2);
                    }
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error signing in!");
                    alert.setContentText("The username password combination does not match!");
                    alert.showAndWait();
                }
            });
            service.setOnFailed(workerStateEvent -> service.restart());
        });
    }
}

