package main.code.post;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;

public class post extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    Label l=new Label("hi");
    Image image=new Image("pic.jpg");
    @Override
    public void start(Stage primaryStage) {
       AnchorPane anchorPane = new AnchorPane();
       Pane p=new Pane();
       if(l.getText().isEmpty()&&image!=null){
           p.getChildren().add(new ImageView(image));
       }
       else if(image==null&&!(l.getText().isEmpty())){
           p.getChildren().add(l);
       }
       else if(image!=null&&!(l.getText().isEmpty())){
           p.getChildren().add(l);
           p.getChildren().add(new ImageView(image));
       }
       anchorPane.getChildren().add(p);
    }

}
