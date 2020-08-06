package main.java.clinicReg;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Controller {
    @FXML
    private AnchorPane aa;


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
}
