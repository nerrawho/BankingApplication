package com.revature.models.account;

import com.revature.models.account.Account;

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
