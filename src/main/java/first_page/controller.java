package main.java.first_page;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import main.java.connections.ClinicDataAccessor;
import main.java.connections.SelectedClinic;
import main.java.helper.Helper;
import main.java.pane.PaneController;
import main.java.requirements.Clinic;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
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
    String Temp=null;
    @FXML
    JFXButton accountInfoBtn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list.setDepth(3);
        try {
            ClinicDataAccessor dataAccessor=new ClinicDataAccessor();
            List<Clinic> clinics=dataAccessor.getClinicList();
            list.setOnMouseClicked((e)->{
                try {
                    SelectedClinic.setClinic(dataAccessor.getClinic(list.getSelectionModel().getSelectedItem().getId()));
                    Helper.changeScene("Second_Page_GUI.fxml",accountInfoBtn);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });
            for (Clinic c: clinics) {
                list.getItems().add(createPane(c));
            }
        }catch (IOException | SQLException e){
            e.printStackTrace();
        }
    }
    Pane createPane(Clinic clinic) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Pane pane=loader.load(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Pane.fxml")));
        PaneController controller=loader.getController();
        controller.setDentist(clinic.getDoctor().getFirstName()+" "+clinic.getDoctor().getLastName());
        controller.setAddress(clinic.getAddress());
        controller.setImgV(null);
        controller.setName(clinic.getName());
        controller.setPhone(clinic.getPhoneNumber());
        pane.setId(Integer.toString(clinic.getId()));
        return pane;
    }
}
