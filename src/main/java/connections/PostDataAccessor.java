package main.java.connections;

import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import main.java.connections.DataAccessor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

public class PostDataAccessor extends DataAccessor {
    public void setPost(byte[] i, String t) throws SQLException {
        try(
                PreparedStatement stmnt = connection.prepareStatement("insert into (select POSTS.IMAGE,Posts.TEXT from POSTS inner join CLINIC C2 on C2.ID = POSTS.POST_CLINIC_ID inner join DOCTOR D on C2.ID = D.CLINIC_ID where DOCTOR_USER_NAME =(?)) (TEXT,IMAGE) values ((?),(?));")
        ){
                stmnt.setString(1,CurrentUser.getCurrentUser().getUsername());
                stmnt.setString(2,t);
                stmnt.setString(3,Base64.getEncoder().encodeToString(i));
                stmnt.execute();
        }
    }
}
