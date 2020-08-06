package main.java.bills;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import main.java.connections.*;
import main.java.helper.Helper;
import main.java.paneWithNumb.withNumbConroller;
import main.java.requirements.Appointment;

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
    JFXButton b2,accountInfoBtn;
    @FXML
    JFXListView<Pane> list;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list.setDepth(3);
        list.setMouseTransparent(true);
        if(CurrentUser.isDoctor()){
            b2.setText("Create Post");
        }
        try {
            AppointmentDataAccessor dataAccessor=new AppointmentDataAccessor();
            List<Appointment> clinics=dataAccessor.getAppointmentList(CurrentUser.getCurrentUser().getUsername());
            for (Appointment c: clinics) {
                list.getItems().add(createPane(c));
            }
        }catch (IOException | SQLException e){
            e.printStackTrace();
        }
    }

    Pane createPane(Appointment appointment) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Pane pane = loader.load(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("withNumb.fxml")));
        withNumbConroller controller=loader.getController();
        controller.setName(appointment.getClinicName());
        controller.setPhone(appointment.getClinicNumber());
        controller.setPrice(Integer.toString(appointment.getTotalPrice()));
        return pane;
    }
    public void account(){
        Helper.changeScene("accountInfo_GUI.fxml",b1);
    }
    public void clinic(){
        if(CurrentUser.isDoctor()){
            Helper.changeScene("CP_GUI.fxml",b1);
        }else{
            Helper.changeScene("First_Page_GUI.fxml",b2);
        }
    }
    public void signOut(){
        Login login=new Login();
        login.signOut();
        Helper.changeScene("mainGUI.fxml",b2);
    }
}

