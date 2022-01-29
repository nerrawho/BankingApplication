package com.revature.models;

public class BankAdminAccount extends Account {

    private final byte accessLevel = 1;

    public BankAdminAccount() {
        super();
    }

    public BankAdminAccount(String username, String password) {
        super(username, password);
    }

    //Mutators

    public byte getAccessLevel() {
        return accessLevel;
    }
}
