package main.java.requirements;


import javafx.scene.image.Image;

public class Clinic {
    private String name;
    private String phoneNumber;
    private String address;
    private String doctorName;
    private int id;
    private int balance;
    private String type;
    private Image profilePicture;
    private Doctor doctor;
    public void setDoctor(Doctor doctor){
        this.doctor=doctor;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Clinic(int id, String name, String phoneNumber, String address, String type, int balance){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.id = id;
        this.balance = balance;
        this.type = type;
//        this.profilePicture = profilePicture;
    }
    public void setName(String n){
        name =n;
    }
    public void setPhoneNumber(String p){
        phoneNumber=p;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public String getAddress(){
        return address;
    }
    public void setId(int i){
        id=i;
    }
    public void setBalance(int r){
        balance=r;
    }
    public void setType(String t){
        type=t;
    }
    public void setProfilePicture(Image e){
        profilePicture=e;
    }
    public String getName(){
        return name;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getType(){
        return type;
    }
    public int getId(){
        return id;
    }
    public int getBalance(){
        return balance;
    }
    public Image getProfilePicture(){
        return  profilePicture;
    }
}
