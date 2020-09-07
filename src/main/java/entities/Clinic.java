package main.java.entities;


public class Clinic {
    private final String name;
    private final String phoneNumber;
    private final String address;
    private final int id;
    private final String type;
    private final byte[] profilePicture;
    private Doctor doctor;

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Clinic(int id, String name, String phoneNumber, String address, String type, byte[] profilePicture) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.id = id;
        this.type = type;
        this.profilePicture = profilePicture;
    }


    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getId() {
        return id;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

}