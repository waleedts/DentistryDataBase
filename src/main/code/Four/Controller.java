package main.code.Four;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class Controller {
    @FXML
    JFXButton b1;
    @FXML
    JFXButton b2;
    @FXML
    JFXButton b3;
    @FXML
    JFXRadioButton r1;
    @FXML
    JFXRadioButton r2;
    @FXML
    JFXRadioButton r3;
    @FXML
    ToggleGroup t;
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private StackPane parentContainer;

    public Controller(){
        t=new ToggleGroup();


    }
    public void returnBack(){

        try {

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Third_Page_GUI.fxml"));
            Scene scene = b1.getScene();

            root.translateXProperty().set(-scene.getWidth());
            StackPane parentContainer = (StackPane) b1.getScene().getRoot();
            parentContainer.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(t -> {
                parentContainer.getChildren().remove(anchorRoot);
            });
            timeline.play();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
