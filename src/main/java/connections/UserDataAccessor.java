package main.java.connections;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import main.java.requirements.User;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class UserDataAccessor extends DataAccessor{
    protected User getUser(String username, ResultSet rs) throws SQLException {
        rs.next();
        User user =new User(rs.getString("first_name"),rs.getString("last_name"),username);
        Blob blob=rs.getBlob("profile_pic");
        BufferedImage image=null;
        try {
            if(blob!=null) {
                image=ImageIO.read(blob.getBinaryStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
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
                ResultSet rs = stmnt.executeQuery("select * from \"USER\" where USER_NAME='"+username+"'")
        ){
            return getUser(username,rs);
        }
    }
    protected void setUser(User user) throws IOException, SQLException {
        try(
                PreparedStatement stmnt = connection.prepareStatement("insert into \"USER\" (FIRST_NAME,LAST_NAME,BIRTH_DATE,PROFILE_PIC,USER_NAME,PASSWORD,ADDRESS,PHONE_NUMBER,GENDER) values " +
                        "((?),(?),(?),(?),(?),(?),(?),(?),(?))")

                ){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( user.getProfilePic(), "jpg", baos );
            byte[] imageInByte = baos.toByteArray();
            stmnt.setBytes(4,imageInByte);
            stmnt.setString(1, user.getFirstName());
            stmnt.setString(2, user.getLastName());
            stmnt.setDate(3, (Date) user.getBirthDate());
            stmnt.setString(5,user.getUsername());
            stmnt.setString(6,user.getPassword());
            stmnt.setString(7,user.getAddress());
            stmnt.setString(8,user.getPhoneNumber());
            stmnt.setString(9,String.valueOf(user.getGender()));
            stmnt.execute();
            baos.flush();
            baos.close();
        }
    }


}
