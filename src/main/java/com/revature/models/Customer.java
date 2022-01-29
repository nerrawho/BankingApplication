package com.revature.models;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer extends Person{

    SimpleDateFormat dateFormat =  new SimpleDateFormat("MM/dd/yyyy");

    private int customerID;
    private String dateJoin;

    //No arg constructor, calls parent constructor
    public Customer() {
        super();
        this.customerID = 0;
        Date currentDate = new Date();
        this.dateJoin = dateFormat.format(currentDate).toString();
    }

    public Customer(String firstName, String lastName, String email, int customerID) {
        super(firstName, lastName, email);
        this.customerID = customerID;
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

}
