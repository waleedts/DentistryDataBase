package main.java.first_page;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import main.java.connections.ClinicDataAccessor;
import main.java.connections.CurrentUser;
import main.java.connections.Login;
import main.java.connections.SelectedClinic;
import main.java.helper.Helper;
import main.java.pane.PaneController;
import main.java.requirements.Clinic;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class controller implements Initializable {
    @FXML
    JFXListView <Pane>list;
    @FXML
    JFXTextField searchField;
    @FXML
    Rectangle rec;

    @FXML
    JFXButton b2;
    @FXML
    MenuButton filterBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image image=new Image(new ByteArrayInputStream(CurrentUser.getCurrentUser().getProfilePic()));
        rec.setFill(new ImagePattern(image));
        list.setDepth(3);
        search();
    }
    public void search(){
        try {
            ClinicDataAccessor dataAccessor=new ClinicDataAccessor();
            List<Clinic> clinics=dataAccessor.getClinicList();
            List<Pane> panes=new ArrayList<>();
            for (Clinic c: clinics) {
                panes.add(createPane(c));
            }
            list.setItems(FXCollections.observableList(panes));

        }catch (IOException | SQLException e){
            e.printStackTrace();
        }
    }
    Pane createPane(Clinic clinic) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        Pane pane=loader.load(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Pane.fxml")));
        pane.setId(Integer.toString(clinic.getId()));
        pane.setOnMouseClicked((e)->{
            SelectedClinic.setClinic(clinic);
            Helper.changeScene("Second_Page_GUI.fxml",b2);
        });
        PaneController controller=loader.getController();
        controller.setDentist(clinic.getDoctor().getFirstName()+" "+clinic.getDoctor().getLastName());
        controller.setAddress(clinic.getAddress());
        controller.setImgV(new Image(new ByteArrayInputStream(clinic.getProfilePicture())));
        controller.setName(clinic.getName());
        controller.setPhone(clinic.getPhoneNumber());
        controller.setType(clinic.getType());
        return pane;
    }
    public void bill(){
        Helper.changeScene("Bills_GUI.fxml",b2);
    }
    public void signOut(){
        Login login=new Login();
        login.signOut();
        Helper.changeScene("mainGUI.fxml",b2);
    }
}
