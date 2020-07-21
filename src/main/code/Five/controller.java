package main.code.Five;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class controller {
    @FXML
    JFXButton b1;
    @FXML
    JFXButton b2;
    @FXML
    JFXRadioButton r1;
    @FXML
    JFXRadioButton r2;

    @FXML
    ToggleGroup t = new ToggleGroup();
    public controller(){

    }
    @FXML
    public void reurnBack(){
        Stage stage =(Stage)b1.getScene().getWindow();
        try {
            Parent ro = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Third_Page_GUI.fxml")));
            Scene scene = new Scene(ro, 600, 400);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

