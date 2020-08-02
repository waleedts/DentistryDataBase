package main.java.helper;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Helper {
    public static void changeScene(String path, JFXButton btn) {
        Stage stage =(Stage) btn.getScene().getWindow();
        try {
            Parent ro = FXMLLoader.load(Objects.requireNonNull(Helper.class.getClassLoader().getResource(path)));
            Scene scene = new Scene(ro, 1000, 400);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
