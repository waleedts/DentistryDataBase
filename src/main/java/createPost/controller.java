package main.java.createPost;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.connections.PostDataAccessor;
import main.java.helper.Helper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

public class controller {
    @FXML
    JFXButton uploadPic,Post;
    @FXML
    AnchorPane aa;
    @FXML
    ImageView i;
    @FXML
    TextArea t;
    byte[] image;
    public void toUpload(){
        Platform.runLater(() -> {
            final FileChooser d = new FileChooser();
            Stage s = (Stage) aa.getScene().getWindow();
            File file = d.showOpenDialog(s);
            if (file != null) {
                try {
                    image = Files.readAllBytes(file.toPath());
                    i.setImage(new Image(new ByteArrayInputStream(image)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    String l = t.getText();
    public void post(){
        try {
            new PostDataAccessor().setPost(image,l);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void goBack(){
        Helper.changeScene("accountInfo_GUI.fxml",uploadPic);
    }
}