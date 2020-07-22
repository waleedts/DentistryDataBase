package main.code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Third_Page_GUI.fxml")));
            primaryStage.setTitle("W.W Dentistry Service");
            primaryStage.setScene(new Scene(root, 600, 462));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
    public static void main (String[]args){
        launch(args);
    }
}
