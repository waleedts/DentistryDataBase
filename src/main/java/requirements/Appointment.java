package main.java.requirements;

import java.time.LocalTime;
import java.util.Date;

public class Appointment {
    Date time;
    int duration;
    String locationOfTeeth;
    String description;
    int totalPrice;

    public void setTime(java.sql.Date t){
        time=t;
    }
    public void setDuration(int d){
        duration=d;
    }
    public void setLocationOfTeeth(String l){
        locationOfTeeth=l;
    }
    public void setDescription(String s){
        description=s;
    }
    public Date getTime(){
        return time;
    }
    public int getDuration(){
        return duration;
    }
    public String getLocationOfTeeth(){
        return locationOfTeeth;
    }
    public String getDescription(){
        return description;
    }

    public void setTotal(int totalPrice) {
        this.totalPrice=totalPrice;
    }
}
