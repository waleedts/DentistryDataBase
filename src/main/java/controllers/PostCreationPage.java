package main.java.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import main.java.connections.PostDataAccessor;
import main.java.helper.Helper;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;

public class PostCreationPage {
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
        image =Helper.toUpload(aa);
        i.setImage(new Image(new ByteArrayInputStream(image)));
    }
    public void post(){
        try {
            new PostDataAccessor().setPost(image,t.getText());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void goBack(){
        Helper.changeScene("Bills_GUI.fxml",uploadPic);
    }
}
