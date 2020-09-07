package main.java.controllers.pages;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import main.java.controllers.panes.Numbered;
import main.java.helpers.JasperHandler;
import main.java.connections.Login;
import main.java.connections.*;
import main.java.helpers.Helper;
import main.java.entities.Appointment;
import main.java.entities.Doctor;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Bills implements Initializable {
    @FXML
    private JFXButton b2,reportBtn;
    @FXML
    JFXListView<Pane> list;
    @FXML
    Rectangle rec;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list.setDepth(3);
        rec.setFill(new ImagePattern(new Image(new ByteArrayInputStream(CurrentUser.getCurrentUser().getProfilePic()))));
        try {
            AppointmentDataAccessor dataAccessor=new AppointmentDataAccessor();
            List<Appointment> appointments;
            if(CurrentUser.isDoctor()){
                b2.setText("Create Post");
                reportBtn.setVisible(true);
                reportBtn.setOnMouseClicked(e->{
                    HashMap<String ,Object> map=new HashMap<>();
                    Doctor doctor=(Doctor)CurrentUser.getCurrentUser();
                    map.put("clinicID",doctor.getClinicId());
                    new JasperHandler().makeReport(map,false);
                });
                appointments=dataAccessor.getAppointmentList(CurrentUser.getCurrentUser().getUsername(),true);
            }else{
                appointments=dataAccessor.getAppointmentList(CurrentUser.getCurrentUser().getUsername(),false);
            }
            for (Appointment c: appointments) {
                list.getItems().add(createPane(c));
            }
        }catch (IOException | SQLException e){
            e.printStackTrace();
        }
    }

    Pane createPane(Appointment appointment) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Pane pane = loader.load(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("withNumb.fxml")));
        Numbered controller=loader.getController();
        controller.setDate(appointment.getTime().toString());
        controller.setDuration(Integer.toString(appointment.getDuration()));
        if(!CurrentUser.isDoctor()) {
            controller.setDoctor();
            controller.setName(appointment.getClinicName());
            controller.setPhone(appointment.getClinicNumber());
        }else {
            controller.setAllergies(appointment.getPatientAllergies());
            controller.setBloodType(appointment.getPatientBloodType());
            controller.setName(appointment.getPatientName());
            controller.setPhone(appointment.getPatientNumber());
        }
        controller.setPrice(Integer.toString(appointment.getTotalPrice()));
        return pane;
    }

    public void clinic(){
        if(CurrentUser.isDoctor()){
            Helper.changeScene("CP_GUI.fxml",b2);
        }else{
            Helper.changeScene("ClinicsList.fxml",b2);
        }
    }
    public void signOut(){
        Login login= new Login();
        login.signOut();
        Helper.changeScene("mainGUI.fxml",b2);
    }
}

