package main.java.requirements;

import java.time.LocalTime;

public class Services {
    private String name;
    private int duration;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    public void setName(String n){
        name=n;
    }
    public void setDuration(int d){
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
    public int getDuration(){
        return duration;
    }
}
