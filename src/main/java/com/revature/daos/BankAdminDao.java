package com.revature.daos;


import com.revature.models.user.*;
import java.util.List;


public interface BankAdminDao extends EmployeeDao{

    public List<Employee> viewEmployees();
    public boolean registerEmployee(Employee employee);
    public boolean deleteAccount(Customer cu);
}
