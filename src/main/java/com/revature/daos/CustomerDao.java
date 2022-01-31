package com.revature.daos;

import com.revature.models.user.Customer;

public interface CustomerDao {

    public boolean registerUserAccount(Customer c);
    public boolean applyBankAccount()
}
