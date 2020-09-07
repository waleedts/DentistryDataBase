package main.java.connections;

import main.java.entities.User;

import java.sql.*;
import java.util.Base64;

public class UserDataAccessor extends DataAccessor{
    protected User getUser(String username, ResultSet rs) throws SQLException {
        rs.next();
        User user =new User(rs.getString("first_name"),rs.getString("last_name"),username);
        String clob=rs.getString("profile_pic");
        byte[]image= Base64.getDecoder().decode(clob);
        char c=0;
        String s=rs.getString("gender");
        if(s!=null)
            c=s.charAt(0);
        user.setPersonalInfo(rs.getString("address"),rs.getString("Phone_number"),c,rs.getDate("birth_date"),image);
        return user;
    }

    protected void setUser(User user) throws  SQLException {
        try(
                PreparedStatement stmnt = connection.prepareStatement("insert into \"USER\" (FIRST_NAME,LAST_NAME,BIRTH_DATE,PROFILE_PIC,USER_NAME,PASSWORD,ADDRESS,PHONE_NUMBER,GENDER) values " +
                        "((?),(?),(?),(?),(?),(?),(?),(?),(?))")

                ){
            byte[] imageInByte = user.getProfilePic();
            String st=Base64.getEncoder().encodeToString(
                    imageInByte);
            stmnt.setString(4,st);
            stmnt.setString(1, user.getFirstName());
            stmnt.setString(2, user.getLastName());
            stmnt.setDate(3, (Date) user.getBirthDate());
            stmnt.setString(5,user.getUsername());
            stmnt.setString(6,user.getPassword());
            stmnt.setString(7,user.getAddress());
            stmnt.setString(8,user.getPhoneNumber());
            stmnt.setString(9,String.valueOf(user.getGender()));
            stmnt.execute();
        }
    }


}
