package main.code.six;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class controller {

    @FXML
    JFXButton b1;
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private StackPane parentContainer;
    @FXML
    Pane p;



    public void returnBack() {

        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Fourth_Page_GUI.fxml")));
            Scene scene = b1.getScene();

            root.translateXProperty().set(-scene.getWidth());
            StackPane parentContainer = (StackPane) b1.getScene().getRoot();
            parentContainer.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(t -> parentContainer.getChildren().remove(anchorRoot));
            timeline.play();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
