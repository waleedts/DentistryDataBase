package main.java.connections;

import javafx.scene.image.Image;
import main.java.requirements.User;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDataAccessor extends DataAccessor{
    protected User getUser(String username, ResultSet rs) throws SQLException {
        rs.next();
        User user =new User(rs.getString("first_name"),rs.getString("last_name"),username);
        Blob blob=rs.getBlob("profile_pic");
        Image image=null;
        if(blob!=null) {
            image=new Image(blob.getBinaryStream());
        }
        char c=0;
        String s=rs.getString("gender");
        if(s!=null)
            c=s.charAt(0);
        user.setPersonalInfo(rs.getString("address"),rs.getString("Phone_number"),c,rs.getDate("birth_date"),image);
        return user;
    }
    public User getUser(String username) throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from \"USER\" where USER_NAME="+username+"")
        ){
            return getUser(username,rs);
        }
    }
//    public void setUser() throws SQLException {
//        try (
//                Statement stmnt = connection.createStatement();
//                ResultSet rs = stmnt.executeQuery("insert into \"USER\" values ()")
//        ){
//
//        }
//    }
}
