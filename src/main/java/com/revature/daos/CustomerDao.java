package com.revature.daos;

import com.revature.models.account.CustomerAccount;
import com.revature.models.user.Customer;

public interface CustomerDao {

    public boolean registerUserAccount(Customer c, CustomerAccount ca);
    public boolean applyBankAccount(CustomerAccount ca, double balance);
    public double viewBalance(CustomerAccount ca);
    public boolean deposit(CustomerAccount ca,double balance);
    public boolean withdraw(CustomerAccount ca,double balance);


}
