package main.java.controllers.panes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Pane {
    @FXML
    ImageView imgV;
    @FXML
    Label name;
    @FXML
    Label dentist;
    @FXML
    Label phone;
    @FXML
    Label address;
    @FXML
    Label type;
    public void setImgV(Image img) {
        imgV.setImage(img);
    }

    public void setName(String name) {
        this.name.setText(name);

    }
    public void setType(String type) {
        this.type.setText(type);

    }

    public void setDentist(String dentist) {
        this.dentist.setText(dentist);

    }

    public void setPhone(String phone) {

        this.phone.setText(phone);
    }

    public void setAddress(String address){
        this.address.setText(address);
    }
}
