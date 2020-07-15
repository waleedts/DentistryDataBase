package main.code.first_page;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;


public class controller implements Initializable {
    JFXDepthManager depthManager;
    @FXML
    JFXListView <Pane>list;
    Image profileImg;
    @FXML
    JFXTextField search;
    @FXML
    Rectangle rec;
    String Temp="230x230-avatar-dummy-profile-pic.jpg";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        profileImg =new Image(Temp);
        rec.setFill(new ImagePattern(profileImg));
        list.setOnMouseClicked((e)->{
            //TODO: add a mouse click
        });
        for(int i=0;i<10;i++){
            list.getItems().add(create());
        }

    }
    Pane create(){
        //TODO: Waseem it's an example delete to make the proper form.
        Pane pane=new Pane();
        TextField textField = new TextField();

        //Creating the play button
        Button playButton = new Button("Play");

        //Creating the stop button
        Button stopButton = new Button("stop");

        //Instantiating the HBox class
        HBox hbox = new HBox();

        //Setting the space between the nodes of a HBox pane
        hbox.setSpacing(10);

        //Setting the margin to the nodes
        HBox.setMargin(textField, new Insets(20, 20, 20, 20));
        HBox.setMargin(playButton, new Insets(20, 20, 20, 20));
        HBox.setMargin(stopButton, new Insets(20, 20, 20, 20));
        pane.getChildren().add(hbox);
        return pane;
    }
}
