package main.code.requirements;

import javafx.scene.image.Image;

import java.util.Date;

public class User {

    public User(String firstName,String lastName,String userName){
        this.firstName=firstName;
        this.lastName=lastName;
        this.userName=userName;
    }
    public User(){}
    private String userName;
    private String passWord;
    private String phoneNumber;
    private char gender;
    private Date birthDate;
    private String address;
    private String firstName;
    private String lastName;
    private Image profilePic;
    public void setProfilePic(Image n) {
        profilePic = n;
    }
    public void setUserName(String n) {
        userName = n;
    }
    public void setPassWord(String p) {
        passWord = p;
    }
    public void setPhoneNumber(String o){
        phoneNumber=o;
    }
    public void setGender(char g){

    }
    public void setBirthDate(Date a){
        birthDate=a;
    }
    public void setAddress(String c){
        address=c;
    }
    public void setFirstName(String f){
        firstName=f;
    }
    public void setLastName(String l){
        lastName=l;
    }
    public String getUserName(){
        return userName;
    }
    public String getPassWord(){
        return passWord;
    }
    public char getGender(){
        return gender;
    }
    public String getAddress(){
        return address;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public Date getBirthDate(){
        return birthDate;
    }
    public Image getProfilePic(){
        return profilePic;
    }

}
