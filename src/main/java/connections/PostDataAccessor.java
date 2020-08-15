package main.java.connections;

import main.java.entities.Doctor;
import main.java.entities.Post;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class PostDataAccessor extends DataAccessor {
    public void setPost(byte[] i, String t) throws SQLException {
        Doctor doctor=(Doctor)CurrentUser.getCurrentUser();
        try(
                PreparedStatement stmnt = connection.prepareStatement("insert into POSTS (TEXT,IMAGE,POST_CLINIC_ID) values ((?),(?),(?))")
        ){

                stmnt.setString(1,t);
                stmnt.setString(2,Base64.getEncoder().encodeToString(i));
                stmnt.setInt(3,doctor.getClinicId());
                stmnt.execute();
        }
    }

    public List<Post> getPostsList(int clinicID) throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from POSTS where POST_CLINIC_ID='"+clinicID+"'")
        ){
                List<Post> postList = new ArrayList<>();
                while (rs.next()) {
                    Post post =new Post(rs.getString("Text"),Base64.getDecoder().decode(rs.getString("image")));
                    postList.add(post);
                }
                return postList;
        }
    }
}
