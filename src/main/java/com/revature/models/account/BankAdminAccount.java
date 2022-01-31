package com.revature.models.account;

import com.revature.models.account.Account;

public class BankAdminAccount extends Account {

    private AccountType type = AccountType.ADMIN;

    public BankAdminAccount() {
        super();
    }

    public BankAdminAccount(String username, String password) {
        super(username, password);
    }

    //Mutators
}
