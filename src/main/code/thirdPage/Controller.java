package main.code.thirdPage;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class Controller  {
    @FXML
    JFXButton b1;
    @FXML
    JFXButton b2;
    @FXML
    JFXButton b3;
 public Controller() {

 }
 @FXML
 public void loginButtonAction(){//i thins its done
     Stage stage =(Stage)b1.getScene().getWindow();
     try {
         Parent ro = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Fourth_Page_GUI.fxml")));
         Scene scene = new Scene(ro, 400, 400);
         stage.setScene(scene);
         stage.show();
     } catch (IOException e) {
         e.printStackTrace();
     }
 }
 @FXML
    public void signinButtonAction(){
     Stage stage=(Stage)b2.getScene().getWindow();



 }
}
