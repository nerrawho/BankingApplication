package com.revature.daos;

import com.revature.models.account.*;
import com.revature.models.user.*;

import java.util.List;

public interface EmployeeDao extends CustomerDao{

    public boolean approveBankAccount(CustomerAccount ca);
    public List<PendingAccount> viewPendingAccounts();
    public List<Customer> viewAllCustomer();
}
