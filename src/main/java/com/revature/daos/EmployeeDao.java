package com.revature.daos;

import com.revature.models.account.CustomerAccount;
import com.revature.models.account.PendingAccount;

import java.util.List;

public interface EmployeeDao extends CustomerDao{

    public boolean approveBankAccount(CustomerAccount ca);
    public List<PendingAccount> viewPendingAccounts();

}
