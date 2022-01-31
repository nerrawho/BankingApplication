package com.revature.models.user;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer extends Person{

    SimpleDateFormat dateFormat =  new SimpleDateFormat("MM/dd/yyyy");

    private int customerID;
    private String dateJoin;
    private UserType type = UserType.CUSTOMER;

    //No arg constructor, calls parent constructor
    public Customer() {
        super();
        Date currentDate = new Date();
        this.dateJoin = dateFormat.format(currentDate).toString();
    }

    public Customer(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
        Date currentDate = new Date();
        this.dateJoin = dateFormat.format(currentDate).toString();
    }

    public String getDateJoin() {
        return dateJoin;
    }

    public void setDateJoin(String dateJoin) {
        this.dateJoin = dateJoin;
    }
//Mutators

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", firstName=" + this.getFirstName() +
                ", lastName=" + this.getLastName() +
                ", email=" + this.getEmail() +
                ", dateJoin=" + dateJoin +
                '}';
    }

}
