package main.java;

import com.jfoenix.controls.JFXButton;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import main.java.connections.Login;
import main.java.helper.Helper;

public class MainController {
    @FXML
    JFXButton b1;

    @FXML
    public void signInButtonAction(){

        Helper.changeScene("Fifth_Page_GUI.fxml",b1);
    }

    @FXML
    public void signUpButtonAction(){

        Helper.changeScene( "Fourth_Page_GUI.fxml",b1);
    }

    @FXML
    public void login() {
        Service<Boolean> service= new Service<>() {
            @Override
            protected Task<Boolean> createTask() {
                return new Task<>() {
                    @Override
                    protected Boolean call() {
                        Login login = new Login();
                        return login.facebookLogin();
                    }
                };
            }
        };
        service.start();
        service.setOnSucceeded(workerStateEvent -> {
            boolean loginSuccess = service.getValue();
            if (loginSuccess) {
                Helper.changeScene("Fourth_Page_GUI.fxml",b1);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error signing in!");
                alert.showAndWait();

            }
        });
//        service.setOnFailed(workerStateEvent -> service.restart());
    }
}
