package main.java.requirements;

import javafx.scene.image.Image;

import java.util.Date;

public class User {

    public User(String firstName,String lastName,String username){
        this.firstName=firstName;
        this.lastName=lastName;
        this.username = username;
    }
    public User(){}
    private String username;
    private String password;
    private String phoneNumber;
    private char gender;
    private Date birthDate;
    private String address;
    private String firstName;
    private String lastName;
    private Image profilePic;
    public void setProfilePic(Image profilePic) {
        this.profilePic=profilePic;
    }
    public void setUsername(String n) {
        username = n;
    }
    public void setPassWord(String p) {
        password = p;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;

    }
    public void setGender(char gender){
        this.gender=gender;
    }
    public void setPersonalInfo(String address,String phoneNumber,char gender,Date birthDate,Image profilePic){
        this.gender=gender;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.birthDate=birthDate;
        this.profilePic=profilePic;
    }

    public void setBirthDate(Date birthDate){
        this.birthDate=birthDate;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public void setFirstName(String f){
        firstName=f;
    }
    public void setLastName(String l){
        lastName=l;
    }
    public String getUsername(){
        return username;
    }
    public String getPassWord(){
        return password;
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
