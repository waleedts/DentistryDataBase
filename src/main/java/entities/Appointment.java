package main.java.entities;

import java.util.Date;

public class Appointment {
    Date time;
    int duration;
    int totalPrice;
    String patientName;
    String patientBloodType;
    String patientNumber;
    String patientAllergies;
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientBloodType() {
        return patientBloodType;
    }

    public void setPatientBloodType(String patientBloodType) {
        this.patientBloodType = patientBloodType;
    }

    public String getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(String patientNumber) {
        this.patientNumber = patientNumber;
    }

    public String getPatientAllergies() {
        return patientAllergies;
    }

    public void setPatientAllergies(String patientAllergies) {
        this.patientAllergies = patientAllergies;
    }

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
