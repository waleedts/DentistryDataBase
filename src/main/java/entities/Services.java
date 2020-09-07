package main.java.entities;

public class Services {
    private int duration;
    private int price;

    public void setDuration(int d){
        duration=d;
    }
    public void setPrice(int p){
        price=p;
    }

    public int getPrice(){
        return price;
    }
    public int getDuration(){
        return duration;
    }
}
