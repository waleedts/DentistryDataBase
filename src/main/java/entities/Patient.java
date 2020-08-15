package main.java.entities;

public class Patient extends User{
    private String bloodType;
    private String allergies;
    private int cost;

    public void setBloodType(String b){
        bloodType=b;
    }
    public void setAllergies(String a){
        allergies=a;
    }
    public void setCost(int c){
        cost=c;
    }
    public String getBloodType(){
        return bloodType;
    }
    public String getAllergies(){
        return allergies;
    }
    public int getCost(){
        return cost;
    }
    public Patient(String firstName,String lastName,String userName){
        super(firstName,lastName,userName);
    }
    public Patient(User user){
        super(user);
    }
    public Patient(Patient patient){
        super(patient);
        this.allergies=patient.allergies;
        this.bloodType=patient.bloodType;
        this.cost=patient.cost;
    }
}
