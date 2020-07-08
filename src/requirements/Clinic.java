package requirements;

import java.awt.*;

public class Clinic {
    private String name;
    private int phoneNumber;
    private String city;
    private String  street;
    private int id;
    private int profit;
    private String type;
    private Image profilePicture;

    public void setName(String n){
        name =new String (n);
    }
    public void setPhoneNumber(int p){
        phoneNumber=p;
    }
    public void setCity(String c){
        city=new String (c);
    }
    public void setStreet(String s){
        street=new String(s);
    }
    public void setId(int i){
        id=i;
    }
    public void setProfit(int r){
        profit=r;
    }
    public void setType(String t){
        type=new String(t);
    }
    public void setProfilePicture(Image e){
        profilePicture=e;
    }
    public String getName(){
        return name;
    }
    public int getPhoneNumber(){
        return phoneNumber;
    }
    public String getCity(){
        return city;
    }
    public String getStreet(){
        return street;
    }
    public String getType(){
        return type;
    }
    public int getId(){
        return id;
    }
    public int getProfit(){
        return profit;
    }
    public Image getProfilePicture(){
        return  profilePicture;
    }
}
