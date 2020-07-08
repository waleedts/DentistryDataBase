package requirements;

import java.time.LocalTime;

public class Services {
    private String name;
    private LocalTime duration;
    private int price;

    public void setName(String n){
        name=new String (n);
    }
    public void setDuration(LocalTime d){
        duration=d;
    }
    public void setPrice(int p){
        price=p;
    }
    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
    public LocalTime getDuration(){
        return duration;
    }
}
