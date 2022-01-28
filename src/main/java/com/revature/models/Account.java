package com.revature.models;

//Abstract to disable instantiation of Account objects.
public abstract class Account {

    private String accountID;
    private String password;
    private String accountName;
    private String firstName;
    private String lastName;

    public Account() {
        this(null, null, null, null, null);
    }

    public Account(String password, String accountName, String firstName, String lastName, String accountID) {
        this.password = password;
        this.accountName = accountName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountID = accountID;
    }

    //Mutators
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }
}
