package main.java.secPage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.effects.JFXDepthManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import main.java.helper.Helper;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    JFXDepthManager depthManager;
    @FXML
    JFXListView<Pane> postsList;
    Image doctorImage;
    Image clinicImage;
    @FXML
    JFXButton bookBtn;
    @FXML
    Circle doctorImageCircle;
    @FXML
    Circle clinicImageCircle;
    @FXML
    Rectangle rec;
    String temp ="230x230-avatar-dummy-profile-pic.jpg";
    String tempPhoto ="Photo.png";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        doctorImage =new Image(temp);
//        clinicImage =new Image(temp);
//        doctorImageCircle.setFill(new ImagePattern(doctorImage));
//        clinicImageCircle.setFill(new ImagePattern(clinicImage));
        for(int i=0;i<10;i++){
            postsList.getItems().add(create());
        }

    }
    String dummyText="Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibu";
    Pane create(){
        Pane pane=new Pane();
        JFXTextArea postBody=new JFXTextArea();
        postBody.setText(dummyText);
        postBody.setEditable(false);
        Image image=new Image(tempPhoto);
        pane.getChildren().add(postBody);
        pane.getChildren().add(new ImageView(image));
        return pane;
    }
    public void goBack(){
        Helper.changeScene("First_Page_GUI.fxml",bookBtn);
    }
}
