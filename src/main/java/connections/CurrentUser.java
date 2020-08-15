package main.java.connections;

import main.java.entities.User;

import java.sql.SQLException;

public class CurrentUser{
    private static boolean isDoctor;
    private static User user;
    public static void setCurrentUser(String username,boolean isDoctor) throws SQLException {
        CurrentUser.isDoctor=isDoctor;
        if(!isDoctor) {
            PatientDataAccessor provider = new PatientDataAccessor();
            user =provider.getPatient(username);
        }else{
            DoctorDataAccessor provider =new DoctorDataAccessor();
            user =provider.getDoctor(username);
        }
    }
    public static void setCurrentUser(User user,boolean isDoctor){
        CurrentUser.user=user;
        CurrentUser.isDoctor=isDoctor;
    }
    public static void deleteCurrentUser(){
        user=null;
    }
    public static boolean isDoctor(){
        return isDoctor;
    }

    public static User getCurrentUser()  {
        return user;
    }


}
