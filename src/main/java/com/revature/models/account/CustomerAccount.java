package com.revature.models.account;

import com.revature.models.account.Account;

public class CustomerAccount extends Account {

    private AccountType type = AccountType.CUSTOMER;

    public CustomerAccount() {
        super();
    }

    public CustomerAccount(String username, String password) {
        super(username, password);
    }

    //Mutators

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }
}
