package main.java.requirements;

import java.time.LocalTime;
import java.util.Date;

public class Appointment {
    Date time;
    int duration;
    int totalPrice;

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    String clinicName;

    public String getClinicNumber() {
        return clinicNumber;
    }

    public void setClinicNumber(String clinicNumber) {
        this.clinicNumber = clinicNumber;
    }

    String clinicNumber;
    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public void setTime(java.sql.Date t){
        time=t;
    }
    public void setDuration(int d){
        duration=d;
    }
    public Date getTime(){
        return time;
    }
    public int getDuration(){
        return duration;
    }

    public void setTotal(int totalPrice) {
        this.totalPrice=totalPrice;
    }
}
