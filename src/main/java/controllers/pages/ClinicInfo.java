package main.java.controllers.pages;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import main.java.connections.PostDataAccessor;
import main.java.connections.SelectedClinic;
import main.java.controllers.panes.Post;
import main.java.helpers.Helper;
import main.java.entities.Clinic;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ClinicInfo implements Initializable {
    @FXML
    JFXListView<Pane> postsList;
    @FXML
    JFXButton bookBtn;
    @FXML
    Circle doctorImageCircle,clinicImageCircle;

    @FXML
    Label clinicLabel,addressLabel,phoneLabel,dentistLabel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        bookBtn.setOnMouseClicked(e-> Helper.changeScene("booking_GUI.fxml",bookBtn));
        Clinic clinic=SelectedClinic.getClinic();
        doctorImageCircle.setFill(new ImagePattern(new Image(new ByteArrayInputStream(clinic.getDoctor().getProfilePic()))));
        clinicImageCircle.setFill(new ImagePattern(new Image(new ByteArrayInputStream(clinic.getProfilePicture()))));
        phoneLabel.setText(clinic.getPhoneNumber());
        clinicLabel.setText(clinic.getName());
        addressLabel.setText(clinic.getAddress());
        dentistLabel.setText("Dr."+clinic.getDoctor().getFirstName()+" "+clinic.getDoctor().getLastName());
        PostDataAccessor dataAccessor=new PostDataAccessor();
        try {
            List<main.java.entities.Post> postList=dataAccessor.getPostsList(clinic.getId());
            if(postList!=null) {
                List<Pane> panes = new ArrayList<>();
                for (main.java.entities.Post post : postList) {
                    panes.add(create(post));
                }
                postsList.setItems(FXCollections.observableList(panes));
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }
    final String dummy="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque pretium leo a enim porta, dictum convallis enim aliquet. In turpis orci, facilisis nec pretium sed, tempus quis sem. Aliquam efficitur orci enim, a venenatis lacus vulputate ac. Aliquam elementum, odio at fermentum feugiat, orci nisi pulvinar turpis, dignissim laoreet nunc turpis in tortor. Vestibulum aliquam nisi vitae enim congue malesuada. Fusce eleifend mollis nisi non bibendum. Duis blandit tincidunt enim et lobortis. ";
    Pane create(main.java.entities.Post post) throws IOException {
        Image image=new Image(new ByteArrayInputStream(post.getImage()));
        FXMLLoader loader = new FXMLLoader();
        Pane pane=loader.load(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Post_GUI.fxml")));
        Post controller=loader.getController();
        controller.setImage(image);
        controller.setText(dummy);
        return pane;
    }
    public void goBack(){
        Helper.changeScene("ClinicsList.fxml",bookBtn);
    }
}
