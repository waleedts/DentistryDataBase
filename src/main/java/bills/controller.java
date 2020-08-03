package main.java.bills;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import main.java.connections.ClinicDataAccessor;
import main.java.connections.SelectedClinic;
import main.java.helper.Helper;
import main.java.pane.PaneController;
import main.java.paneWithNumb.withNumbConroller;
import main.java.requirements.Clinic;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class controller implements Initializable {
    @FXML
    JFXButton b1;
    @FXML
    JFXButton b2;
    @FXML
    JFXListView<Pane> list;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list.setDepth(3);
        try {
            ClinicDataAccessor dataAccessor=new ClinicDataAccessor();
            List<Clinic> clinics=dataAccessor.getClinicList();
            list.setOnMouseClicked((e)->{
                try {
                    SelectedClinic.setClinic(dataAccessor.getClinic(list.getSelectionModel().getSelectedItem().getId()));
                    Helper.changeScene("Second_Page_GUI.fxml",b1);
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
        Pane pane = loader.load(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("withNumb.fxml")));
        withNumbConroller controller=loader.getController();
        controller.setName(clinic.getName());
        controller.setPhone(clinic.getPhoneNumber());
        controller.setDate(clinic.getDate()+" "+clinic.getTime());
        controller.setDes(clinic.getDes());
        controller.setPrice(clinic.getPrice());
        return pane;
    }
    public void account(){
        Helper.changeScene("accountInfo_GUI.fxml",b1);
    }
    public void clinic(){
        Helper.changeScene("First_Page_GUI.fxml",b2);
    }
}
