package main.java.booking;

import com.jfoenix.controls.JFXCheckBox;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.java.connections.DataAccessor;
import main.java.connections.ServicesDataAccessor;
import main.java.requirements.Services;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class controller implements Initializable {
    List<Services> servicesList;
    @FXML
    JFXCheckBox c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15;
    @FXML
    Label l1,l2;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServicesDataAccessor dataAccessor =new ServicesDataAccessor();
        try {
            servicesList=dataAccessor.getServicesList();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        l1.setText(Integer.toString(total));
        l2.setText(Integer.toString(duration));
    }
    private int total=0;
    private int duration=0;

    public void handle(Event event){
        if(event.getSource()==c1) {
            total+=c1.isSelected()?servicesList.get(0).getPrice():-servicesList.get(0).getPrice();
               duration+=c1.isSelected()?servicesList.get(0).getDuration():-servicesList.get(0).getDuration();
        }else if(event.getSource()==c2){
            total+=c2.isSelected()?servicesList.get(1).getPrice():-servicesList.get(1).getPrice();
               duration+=c2.isSelected()?servicesList.get(1).getDuration():-servicesList.get(1).getDuration();
        }else if(event.getSource()==c3) {
            total+=c3.isSelected()?servicesList.get(2).getPrice():-servicesList.get(2).getPrice();
               duration+=c3.isSelected()?servicesList.get(2).getDuration():-servicesList.get(2).getDuration();
        }else if(event.getSource()==c4){
            total+=c4.isSelected()?servicesList.get(3).getPrice():-servicesList.get(3).getPrice();
               duration+=c4.isSelected()?servicesList.get(3).getDuration():-servicesList.get(3).getDuration();
        }else if(event.getSource()==c5){
            total+=c5.isSelected()?servicesList.get(4).getPrice():-servicesList.get(4).getPrice();
               duration+=c5.isSelected()?servicesList.get(4).getDuration():-servicesList.get(4).getDuration();
        }else if(event.getSource()==c6) {
            total+=c6.isSelected()?servicesList.get(5).getPrice():-servicesList.get(5).getPrice();
               duration+=c6.isSelected()?servicesList.get(5).getDuration():-servicesList.get(5).getDuration();
        }else if(event.getSource()==c7){
            total+=c7.isSelected()?servicesList.get(6).getPrice():-servicesList.get(6).getPrice();
               duration+=c7.isSelected()?servicesList.get(6).getDuration():-servicesList.get(6).getDuration();
        }else if(event.getSource()==c8){
            total+=c8.isSelected()?servicesList.get(7).getPrice():-servicesList.get(7).getPrice();
               duration+=c8.isSelected()?servicesList.get(7).getDuration():-servicesList.get(7).getDuration();
        }else if(event.getSource()==c9) {
            total+=c9.isSelected()?servicesList.get(8).getPrice():-servicesList.get(8).getPrice();
               duration+=c9.isSelected()?servicesList.get(8).getDuration():-servicesList.get(8).getDuration();
        }else if(event.getSource()==c10){
            total+=c10.isSelected()?servicesList.get(9).getPrice():-servicesList.get(9).getPrice();
            duration+=c10.isSelected()?servicesList.get(9).getDuration():-servicesList.get(9).getDuration();
        }else if(event.getSource()==c11){
            total+=c11.isSelected()?servicesList.get(10).getPrice():-servicesList.get(10).getPrice();
            duration+=c11.isSelected()?servicesList.get(10).getDuration():-servicesList.get(10).getDuration();
        }else if(event.getSource()==c12) {
            total+=c12.isSelected()?servicesList.get(11).getPrice():-servicesList.get(11).getPrice();
            duration+=c12.isSelected()?servicesList.get(11).getDuration():-servicesList.get(11).getDuration();
        }else if(event.getSource()==c13){
            total+=c13.isSelected()?servicesList.get(12).getPrice():-servicesList.get(12).getPrice();
            duration+=c13.isSelected()?servicesList.get(12).getDuration():-servicesList.get(12).getDuration();
        }else if(event.getSource()==c14) {
            total+=c14.isSelected()?servicesList.get(13).getPrice():-servicesList.get(13).getPrice();
            duration+=c14.isSelected()?servicesList.get(13).getDuration():-servicesList.get(13).getDuration();
        }else if(event.getSource()==c15){
            total+=c15.isSelected()?servicesList.get(14).getPrice():-servicesList.get(14).getPrice();
            duration+=c15.isSelected()?servicesList.get(14).getDuration():-servicesList.get(14).getDuration();
        }
        l1.setText(Integer.toString(total));
        l2.setText(Integer.toString(duration));
    }
}
