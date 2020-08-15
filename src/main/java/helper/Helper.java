package main.java.helper;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class Helper {
    public static void changeScene(String path, JFXButton btn) {
        Stage stage =(Stage) btn.getScene().getWindow();
        try {
            Parent ro = FXMLLoader.load(Objects.requireNonNull(Helper.class.getClassLoader().getResource(path)));
            Scene scene = new Scene(ro, 1200, 600);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static byte[] toUpload(AnchorPane aa){
        byte[] image=null;
        final FileChooser d = new FileChooser();
        Stage s = (Stage) aa.getScene().getWindow();
        File file = d.showOpenDialog(s);
        if (file != null) {
            try {
                image= Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }
}
