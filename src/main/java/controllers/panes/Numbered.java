package main.java.controllers.panes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Numbered {
    @FXML
    Label patientName;
    @FXML
    Label phone;
    @FXML
    Label allergies;
    @FXML
    Label allerg;
    @FXML
    Label date;
    @FXML
    Label price;
    @FXML
    Label bloodType;
    @FXML
    Label duration;

    public void setDoctor(){
        bloodType.setVisible(false);
        allerg.setVisible(false);
        allergies.setVisible(false);
    }

    public void setDuration(String duration) {
        this.duration.setText(duration);
    }
    public void setName(String patientName) {
        this.patientName.setText(patientName);
    }
    public void setPhone(String phone) {
        this.phone.setText(phone);
    }
    public void setBloodType(String bloodType) {
        this.bloodType.setText(bloodType);
    }
    public void setDate(String date) {

        this.date.setText(date);
    }
    public void setAllergies(String allergies) {

        this.allergies.setText(allergies);
    }
    public void setPrice(String price) {

        this.price.setText(price);
    }
}
