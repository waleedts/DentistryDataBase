package requirements;

public class Patient extends User{
    private String bloodType;
    private String allergies;
    private int cost;

    public void setBloodType(String b){
        bloodType=new String(b);
    }
    public void setAllergies(String a){
        allergies=new String(a);
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
