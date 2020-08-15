package main.java.controllers;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Post {
    @FXML
    Text text;
    @FXML
    ImageView image;
    public void setText(String text){
        this.text.setText(text);
    }
    public void setImage(Image image){
        this.image.setImage(image);
        this.image.setFitHeight(340);
        this.image.setFitWidth(700);
    }
}
