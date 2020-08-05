package main.java.first_page;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import main.java.requirements.*;
import main.java.helper.*;
import main.java.connections.*;
import main.java.pane.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class controller implements Initializable {
    JFXDepthManager depthManager;
    @FXML
    JFXListView <Pane>list;
    InputStream profileImg;
    @FXML
    JFXTextField search;
    @FXML
    Rectangle rec;
    String Temp=null;
    @FXML
    JFXButton accountInfoBtn;
    @FXML
    JFXButton b2;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        BufferedImage img =CurrentUser.getCurrentUser().getProfilePic();
//        rec.setFill(new ImagePattern( SwingFXUtils.toFXImage(img,null)));//Todo : fix blob with data base
        System.out.println(rec.getFill());
        list.setDepth(3);
        try {
            ClinicDataAccessor dataAccessor=new ClinicDataAccessor();
            List<Clinic> clinics=dataAccessor.getClinicList();
            for (Clinic c: clinics) {
                list.getItems().add(createPane(c,dataAccessor));
            }
        }catch (IOException | SQLException e){
            e.printStackTrace();
        }
    }
    Pane createPane(Clinic clinic,ClinicDataAccessor dataAccessor) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        Pane pane=loader.load(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Pane.fxml")));
        pane.setId(Integer.toString(clinic.getId()));
        pane.setOnMouseClicked((e)->{
            try {
                Helper.changeScene("Second_Page_GUI.fxml",accountInfoBtn);
                SelectedClinic.setClinic(dataAccessor.getClinic(list.getSelectionModel().getSelectedItem().getId()));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        pane.setScaleZ(2);
        PaneController controller=loader.getController();
        controller.setDentist(clinic.getDoctor().getFirstName()+" "+clinic.getDoctor().getLastName());
        controller.setAddress(clinic.getAddress());
        controller.setImgV(null);
        controller.setName(clinic.getName());
        controller.setPhone(clinic.getPhoneNumber());
        return pane;
    }
    public void bill(){
        Helper.changeScene("Bills_GUI.fxml",b2);
    }
}
