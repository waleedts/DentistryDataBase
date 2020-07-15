package main.code.requirements;

import java.time.LocalTime;

public class Appointment {
    LocalTime time;
    LocalTime duration;
    String locationOfTeeth;
    String description;

    public void setTime(LocalTime t){
        time=t;
    }
    public void setDuration(LocalTime d){
        duration=d;
    }
    public void setLocationOfTeeth(String l){
        locationOfTeeth=l;
    }
    public void setDescription(String s){
        description=s;
    }
    public LocalTime getTime(){
        return time;
    }
    public LocalTime getDuration(){
        return duration;
    }
    public String getLocationOfTeeth(){
        return locationOfTeeth;
    }
    public String getDescription(){
        return description;
    }

}
