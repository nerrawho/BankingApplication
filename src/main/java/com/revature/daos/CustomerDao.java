package com.revature.daos;

import com.revature.models.account.CustomerAccount;
import com.revature.models.user.Customer;
import com.revature.utils.Transaction;

public interface CustomerDao {

    public boolean registerUser(Customer customer);
    public boolean registerUserAccount(CustomerAccount ca);
    public boolean applyBankAccount(Transaction t);
    public double viewBalance(CustomerAccount ca);
    public boolean deposit(Transaction t);
    public boolean withdraw(Transaction t);


}
