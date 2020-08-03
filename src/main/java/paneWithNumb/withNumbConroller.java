package main.java.paneWithNumb;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class withNumbConroller {
    @FXML
    Label name;
    @FXML
    Label phone;
    @FXML
    Label des;
    @FXML
    Label date;
    @FXML
    Label price;
    @FXML
    Label time;

    public void setName(String name) {
        this.name.setText(name);
    }
    public void setPhone(String phone) {

        this.phone.setText(phone);
    }
    public void setDes(String des) {

        this.des.setText(des);
    }
    public void setDate(String date) {

        this.date.setText(date);
    }
    public void setPrice(String price) {

        this.price.setText(price);
    }
    public void setTime(String time) {

        this.time.setText(time);
    }
}
