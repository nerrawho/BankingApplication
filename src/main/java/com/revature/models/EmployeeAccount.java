package com.revature.models;

public class EmployeeAccount extends Account {

    private final byte accessLevel = 0;

    public EmployeeAccount() {
        super();
    }

    public EmployeeAccount(String username, String password) {
        super(username, password);
    }

    //Mutators

    public byte getAccessLevel() {
        return accessLevel;
    }
}
