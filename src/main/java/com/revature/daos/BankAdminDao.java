package com.revature.daos;


import com.revature.models.user.*;
import java.util.List;


public interface BankAdminDao extends EmployeeDao{

    public List<Employee> viewEmployees();
    public void deleteAccount(Customer cu);
}
