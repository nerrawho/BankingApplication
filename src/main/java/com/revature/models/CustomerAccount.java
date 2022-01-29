package com.revature.models;

public class CustomerAccount extends Account {

    private final byte accessLevel = -1;

    public CustomerAccount() {
        super();
    }

    public CustomerAccount(String username, String password) {
        super(username, password);
    }

    //Mutators

    public int getAccessLevel() {
        return accessLevel;
    }

}
