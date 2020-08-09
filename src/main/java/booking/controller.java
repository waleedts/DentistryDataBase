package main.java.booking;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.java.JasperHandler;
import main.java.connections.*;
import main.java.helper.Helper;
import main.java.requirements.Services;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class controller implements Initializable {
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
    public void bookAppointment(Event event){
        AppointmentDataAccessor dataAccessor=new AppointmentDataAccessor();
        ConasDataAccessor conasDataAccessor=new ConasDataAccessor();
        goBackBtn.setOnMouseClicked(e->Helper.changeScene("Second_Page_GUI.fxml",b));
        LocalDateTime dateTime=jTime.getValue().atDate(jDate.getValue());
        Date date=Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        try {
            dataAccessor.setAppointment(date, CurrentUser.getCurrentUser().getUsername(), SelectedClinic.getClinic().getId());
            int id=dataAccessor.getAppointment(date, CurrentUser.getCurrentUser().getUsername(), SelectedClinic.getClinic().getId());
            conasDataAccessor.setConnections(arr,id);
        new Thread(()->{
            JasperHandler handler=new JasperHandler();
            handler.makeReport(id);
            while (!handler.isFinished()){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Helper.changeScene("First_Page_GUI.fxml", (JFXButton) event.getSource());
        }).start();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServicesDataAccessor dataAccessor =new ServicesDataAccessor();

        try {
            servicesList=dataAccessor.getServicesList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        arr =new boolean[15];
        l1.setText(Integer.toString(total));
        l2.setText(Integer.toString(duration));
    }
    private int total=0;
    private int duration=0;
    boolean []arr;
    public void handle(Event event){
        if(event.getSource()==c1) {
            arr[0]=!arr[0];
            total+=c1.isSelected()?servicesList.get(0).getPrice():-servicesList.get(0).getPrice();
            duration+=c1.isSelected()?servicesList.get(0).getDuration():-servicesList.get(0).getDuration();
        }else if(event.getSource()==c2){
            arr[1]=!arr[1];
            total+=c2.isSelected()?servicesList.get(1).getPrice():-servicesList.get(1).getPrice();
            duration+=c2.isSelected()?servicesList.get(1).getDuration():-servicesList.get(1).getDuration();
        }else if(event.getSource()==c3) {
            arr[2]=!arr[2];
            total+=c3.isSelected()?servicesList.get(2).getPrice():-servicesList.get(2).getPrice();
            duration+=c3.isSelected()?servicesList.get(2).getDuration():-servicesList.get(2).getDuration();
        }else if(event.getSource()==c4){
            arr[3]=!arr[3];
            total+=c4.isSelected()?servicesList.get(3).getPrice():-servicesList.get(3).getPrice();
            duration+=c4.isSelected()?servicesList.get(3).getDuration():-servicesList.get(3).getDuration();
        }else if(event.getSource()==c5){
            arr[4]=!arr[4];
            total+=c5.isSelected()?servicesList.get(4).getPrice():-servicesList.get(4).getPrice();
            duration+=c5.isSelected()?servicesList.get(4).getDuration():-servicesList.get(4).getDuration();
        }else if(event.getSource()==c6) {
            arr[5]=!arr[5];
            total+=c6.isSelected()?servicesList.get(5).getPrice():-servicesList.get(5).getPrice();
            duration+=c6.isSelected()?servicesList.get(5).getDuration():-servicesList.get(5).getDuration();
        }else if(event.getSource()==c7){
            arr[6]=!arr[6];
            total+=c7.isSelected()?servicesList.get(6).getPrice():-servicesList.get(6).getPrice();
            duration+=c7.isSelected()?servicesList.get(6).getDuration():-servicesList.get(6).getDuration();
        }else if(event.getSource()==c8){
            arr[7]=!arr[7];
            total+=c8.isSelected()?servicesList.get(7).getPrice():-servicesList.get(7).getPrice();
            duration+=c8.isSelected()?servicesList.get(7).getDuration():-servicesList.get(7).getDuration();
        }else if(event.getSource()==c9) {
            arr[8]=!arr[8];
            total+=c9.isSelected()?servicesList.get(8).getPrice():-servicesList.get(8).getPrice();
            duration+=c9.isSelected()?servicesList.get(8).getDuration():-servicesList.get(8).getDuration();
        }else if(event.getSource()==c10){
            arr[9]=!arr[9];
            total+=c10.isSelected()?servicesList.get(9).getPrice():-servicesList.get(9).getPrice();
            duration+=c10.isSelected()?servicesList.get(9).getDuration():-servicesList.get(9).getDuration();
        }else if(event.getSource()==c11){
            arr[10]=!arr[10];
            total+=c11.isSelected()?servicesList.get(10).getPrice():-servicesList.get(10).getPrice();
            duration+=c11.isSelected()?servicesList.get(10).getDuration():-servicesList.get(10).getDuration();
        }else if(event.getSource()==c12) {
            arr[11]=!arr[11];
            total+=c12.isSelected()?servicesList.get(11).getPrice():-servicesList.get(11).getPrice();
            duration+=c12.isSelected()?servicesList.get(11).getDuration():-servicesList.get(11).getDuration();
        }else if(event.getSource()==c13){
            arr[12]=!arr[12];
            total+=c13.isSelected()?servicesList.get(12).getPrice():-servicesList.get(12).getPrice();
            duration+=c13.isSelected()?servicesList.get(12).getDuration():-servicesList.get(12).getDuration();
        }else if(event.getSource()==c14) {
            arr[13]=!arr[13];
            total+=c14.isSelected()?servicesList.get(13).getPrice():-servicesList.get(13).getPrice();
            duration+=c14.isSelected()?servicesList.get(13).getDuration():-servicesList.get(13).getDuration();
        }else if(event.getSource()==c15){
            arr[14]=!arr[14];
            total+=c15.isSelected()?servicesList.get(14).getPrice():-servicesList.get(14).getPrice();
            duration+=c15.isSelected()?servicesList.get(14).getDuration():-servicesList.get(14).getDuration();
        }
        l1.setText(Integer.toString(total));
        l2.setText(Integer.toString(duration));
    }
}
