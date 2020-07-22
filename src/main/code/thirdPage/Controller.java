package main.code.thirdPage;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.code.connections.Login;

public class Controller {
    @FXML
    JFXButton b1;
    @FXML
    JFXButton b2;
    @FXML
    JFXButton b3;
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private StackPane parentContainer;

    public Controller() {

    }

    @FXML
    public void loginButtonAction() {
        try {

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Fifth_Page_GUI.fxml"));
            Scene scene = b1.getScene();

            root.translateXProperty().set(-scene.getWidth());

            parentContainer.getChildren().add(root);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(t -> {
                parentContainer.getChildren().remove(anchorRoot);
            });
            timeline.play();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


 @FXML
    public void signinButtonAction(){
     try {
         Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Fourth_Page_GUI.fxml"));
         Scene scene=b2.getScene();;


         root.translateXProperty().set(scene.getWidth());

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
    @FXML
    public void login() {
        Login login = new Login();
        try {
            login.facebookLogin();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}