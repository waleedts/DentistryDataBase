package main.java.requirements;

public class Doctor extends User{
    private int salary;
    private int clinicId;
    public void setSalary(int salary){
        this.salary=salary;
    }
    public void setClinicId(int clinicId){
        this.clinicId=clinicId;
    }
    public int getSalary(){
        return salary;
    }
    public Doctor(String firstName,String lastName,String userName){
        super(firstName,lastName,userName);
    }
    public Doctor(User user){
        super(user.getFirstName(),user.getLastName(),user.getUsername());
        user.setPersonalInfo(user.getAddress(),user.getPhoneNumber(), user.getGender(), user.getBirthDate(), user.getProfilePic());
    }
}
