package com.revature.models.user;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee extends Person{

    SimpleDateFormat dateFormat =  new SimpleDateFormat("MM/dd/yyyy");

    private int employeeID;
    private String dateEmployed;
    private UserType type = UserType.EMPLOYEE;

    //No arg constructor, calls parent constructor
    public Employee() {
        super();
        this.employeeID = 0;
        Date currentDate = new Date();
        this.dateEmployed = dateFormat.format(currentDate).toString();
    }

    public Employee(String firstName, String lastName, String email, int employeeID) {
        super(firstName, lastName, email);
        this.employeeID = employeeID;
        Date currentDate = new Date();
        this.dateEmployed = dateFormat.format(currentDate).toString();
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getDateEmployed() {
        return dateEmployed;
    }

    public void setDateEmployed(String dateEmployed) {
        this.dateEmployed = dateEmployed;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "employeeID=" + employeeID +
                ", firstName=" + this.getFirstName() +
                ", lastName=" + this.getLastName() +
                ", email=" + this.getEmail() +
                ", dateEmployed=" + dateEmployed +
                '}';
    }
}
