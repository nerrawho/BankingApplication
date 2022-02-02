package com.revature.models.user;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BankAdmin extends Person{

    SimpleDateFormat dateFormat =  new SimpleDateFormat("MM/dd/yyyy");

    private int bankAdminID;
    private String dateEmployed;
    private UserType type = UserType.ADMIN;

    //No arg constructor, calls parent constructor
    public BankAdmin() {
        super();
        this.bankAdminID = 0;
        Date currentDate = new Date();
        this.dateEmployed = dateFormat.format(currentDate).toString();
    }

    public BankAdmin(String firstName, String lastName, String email, int bankAdminID) {
        super(firstName, lastName, email);
        this.bankAdminID = bankAdminID;
        Date currentDate = new Date();
        this.dateEmployed = dateFormat.format(currentDate).toString();
    }

    public int getBankAdminID() {
        return bankAdminID;
    }

    public void setBankAdminID(int employeeID) {
        this.bankAdminID = employeeID;
    }

    public String getDateEmployed() {
        return dateEmployed;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public void setDateEmployed(String dateEmployed) {
        this.dateEmployed = dateEmployed;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "bankAdminID=" + bankAdminID +
                ", firstName=" + this.getFirstName() +
                ", lastName=" + this.getLastName() +
                ", email=" + this.getEmail() +
                ", dateEmployed=" + dateEmployed +
                '}';
    }
}

