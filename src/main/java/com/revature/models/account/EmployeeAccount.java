package com.revature.models.account;

import com.revature.models.account.Account;

public class EmployeeAccount extends Account {

    private AccountType type = AccountType.EMPLOYEE;

    public EmployeeAccount() {
        super();
    }

    public EmployeeAccount(String username, String password, String email) {
        super(username, password, email);
    }

    //Mutators
}
