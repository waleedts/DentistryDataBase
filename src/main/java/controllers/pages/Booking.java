package main.java.controllers.pages;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.java.helpers.JasperHandler;
import main.java.connections.*;
import main.java.helpers.Helper;
import main.java.entities.Services;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class Booking implements Initializable {
    List<Services> servicesList;
    @FXML
    JFXCheckBox c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15;
    @FXML
    Label l1,l2;
    @FXML
    JFXDatePicker jDate;
    @FXML
    JFXTimePicker jTime;
    @FXML
    JFXButton b;
    @FXML
    FontIcon goBackBtn;
    JasperHandler handler;

    public void bookAppointment(Event event){
        AppointmentDataAccessor dataAccessor=new AppointmentDataAccessor();
        ConasDataAccessor conasDataAccessor=new ConasDataAccessor();
        goBackBtn.setOnMouseClicked(e->Helper.changeScene("ClinicInfo.fxml",b));
        LocalDateTime dateTime=jTime.getValue().atDate(jDate.getValue());
        Date date=Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        try {
            dataAccessor.setAppointment(date, CurrentUser.getCurrentUser().getUsername(), SelectedClinic.getClinic().getId());
            int id=dataAccessor.getAppointment(date, CurrentUser.getCurrentUser().getUsername(), SelectedClinic.getClinic().getId());
            conasDataAccessor.setConnections(arr,id);
        new Thread(()->{
            handler=new JasperHandler();
            HashMap<String ,Object> map=new HashMap<>();
            map.put("appointmentID",id);
            map.put("TOTAL_PRI",Integer.parseInt(l1.getText()));
            map.put("TOTAL_DUR",Integer.parseInt(l2.getText()));
            handler.makeReport(map,true);
        }).start();
        Helper.changeScene("ClinicsList.fxml", (JFXButton) event.getSource());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    ArrayList<JFXCheckBox> checkBoxes;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServicesDataAccessor dataAccessor =new ServicesDataAccessor();

        checkBoxes=new ArrayList<>(Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15));
        try {
            servicesList=dataAccessor.getServicesList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        arr =new boolean[15];
        l1.setText(Integer.toString(totalPrice));
        l2.setText(Integer.toString(totalDuration));
    }
    private int totalPrice=0;
    private int totalDuration =0;
    boolean []arr;
    public void handle(Event event){
        for(int i=0;i<15;i++){
            if(event.getSource()==checkBoxes.get(i)){
                boolean isSelected=checkBoxes.get(i).isSelected();
                Services service=servicesList.get(i);
                int price=service.getPrice();
                int duration=service.getDuration();
                arr[i]=!arr[i];
                totalPrice+=isSelected?price:-price;
                totalDuration +=isSelected?duration:-duration;
            }
        }
        l1.setText(Integer.toString(totalPrice));
        l2.setText(Integer.toString(totalDuration));
    }
}
