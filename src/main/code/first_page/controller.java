package main.code.first_page;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import com.sun.javafx.font.Glyph;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;


public class controller implements Initializable {
    JFXDepthManager depthManager;
    Image profileImg;
    @FXML
    JFXTextField search;
    @FXML
    Rectangle rec;
    String Temp="230x230-avatar-dummy-profile-pic.jpg";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        profileImg =new Image(Temp);
        rec.setFill(new ImagePattern(profileImg));

    }
}
