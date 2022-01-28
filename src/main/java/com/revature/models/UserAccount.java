package com.revature.models;

public class UserAccount extends Account{

    private String userID;
    private int accessLevelID;

    public UserAccount() {
        this();
    }

    public UserAccount(String password, String accountName, String firstName, String lastName, String accountID, String userID, int accessLevelID) {
        super(password, accountName, firstName, lastName, accountID);
        this.userID = userID;
        this.accessLevelID = accessLevelID;
    }

}
