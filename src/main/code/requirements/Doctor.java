package main.code.requirements;

public class Doctor extends User{
    private int salary;

    public void setSalary(int s){
        salary=s;
    }
    public int getSalary(){
        return salary;
    }
    public Doctor(String firstName,String lastName,String userName){
        super(firstName,lastName,userName);
    }
}
