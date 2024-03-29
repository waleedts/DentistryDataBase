package main.java;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("mainGUI.fxml")));
            // System.out.println(getClass().getResource("/main.java.first_page/ClinicsList.fxml"));
            Scene scene = new Scene(root, 1200, 600);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
