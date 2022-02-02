package com.revature.services;

import com.revature.models.account.CustomerAccount;
import com.revature.models.account.PendingAccount;
import com.revature.models.user.*;

public class CustomerService {

    //Customer
    public Customer createCustomer(String firstName, String lastName, String email) {
        Customer customer = new Customer(firstName, lastName, email);
        return customer;
    }

    public CustomerAccount createCustomerAccount(String username, String password, String email) {
        CustomerAccount ca = new CustomerAccount(username, password, email);
        return ca;
    }

    public PendingAccount applyBankAccount(long pendingAccountID, long userAccountID, double balance) {
        PendingAccount pa = new PendingAccount(pendingAccountID, userAccountID, balance);
        return pa;
    }

    public double addition(double x, double y) {
        return x+y;
    }
}
