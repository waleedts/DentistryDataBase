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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.connections.CurrentUser;
import main.java.connections.Login;
import main.java.helper.Helper;
import main.java.requirements.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    JFXTextField firstNameField,lastNameField,userField,passField,addressField,phoneField;
    @FXML
    JFXButton b2,b3;
    @FXML
    JFXRadioButton r1,r2,r3;
    @FXML
    ToggleGroup t,doctorPatient;
    @FXML
    JFXRadioButton doctorRadio;
    @FXML
    JFXDatePicker birthDate;
    @FXML
    Pane p;
    @FXML
    private AnchorPane aa;
    @FXML
    ProgressIndicator progressIndicator;
    public Controller(){
        t=new ToggleGroup();
        doctorPatient=new ToggleGroup();

    }

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
        Alert alrt;
        if(birthDate.getValue()==null||firstNameField.getText()==null||lastNameField.getText()==null||addressField.getText()==null||userField.getText()==null||passField.getText()==null||phoneField.getText()==null||image==null) {
            alrt = new Alert(Alert.AlertType.ERROR);
            alrt.setTitle("Error Signing Up!");
            alrt.setContentText("The fields must not be empty!");
            alrt.showAndWait();
            return;
        }
        date=java.sql.Date.valueOf(birthDate.getValue());
        Service<Boolean> service= new Service<>() {
            @Override
            protected Task<Boolean> createTask() {
                return new Task<>() {
                    @Override
                    protected Boolean call() {
                        boolean loginSuccess = false;
                        Login login = new Login();
                        try {
                            System.out.println("out");
                            loginSuccess = login.signUp(firstNameField.getText(), lastNameField.getText(), addressField.getText(), userField.getText(), passField.getText(), phoneField.getText(), gender, date, image, doctorRadio.isSelected());
                        } catch (SQLException e) {
                            System.out.println("Exception in method (SignUpAction) in class (FourthController)" + e.getMessage());
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
                Helper.changeScene("Six_Page_GUI.fxml",b2);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Signup Error!");
                alert.setContentText("Error in Signup process!");
                alert.showAndWait();
            }
        });
        service.setOnFailed(workerStateEvent -> service.restart());
    }
    public void goBack(){
        Helper.changeScene("mainGUI.fxml",b2);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JFXDepthManager.setDepth(p, 1);
        JFXDepthManager.setDepth(b2, 5);
        JFXDepthManager.setDepth(b3, 5);
        doctorRadio.setSelected(true);
        progressIndicator.setVisible(false);
        r1.setSelected(true);
        User user=CurrentUser.getCurrentUser();
        if(user!=null){
            firstNameField.setText(user.getFirstName());
            lastNameField.setText(user.getLastName());
            userField.setText(user.getUsername());
            passField.setText(user.getPassword());
            phoneField.setText(user.getPhoneNumber());
            birthDate.setValue(Instant.ofEpochMilli(user.getBirthDate().getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());
            addressField.setText(user.getAddress());
            CurrentUser.deleteCurrentUser();
        }
    }
}
