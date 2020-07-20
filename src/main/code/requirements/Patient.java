package main.code.requirements;

public class Patient {
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
}
