package requirements;

public class User {
    private String userName;
    private String passWord;
    private int phoneNumber;
    private String gender;
    private int age;
    private String city;
    private String street;
    private String firstName;
    private String lastName;

    public void setUserName(String n) {
        userName = new String(n);
    }

    public void setPassWord(String p) {
        passWord = new String(p);
    }
    public void setPhoneNumber(int o){
        phoneNumber=o;
    }
    public void setGender(String g){
        gender=new String(g);
    }
    public void setAge(int a){
        age=a;
    }
    public void setCity(String c){
        city=new String(c);
    }
    public void setStreet(String s){
        street=new String(s);
    }
    public void setFirstName(String f){
        firstName=new String(f);
    }
    public void setLastName(String l){
        lastName=new String(l);
    }
    public String getUserName(){
        return userName;
    }
    public String getPassWord(){
        return passWord;
    }
    public String getGender(){
        return gender;
    }
    public String getCity(){
        return city;
    }
    public String getStreet(){
        return street;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public int getPhoneNumber(){
        return phoneNumber;
    }
    public int getAge(){
        return age;
    }
}
