package com.revature;

import com.revature.daos.*;
import com.revature.models.account.CustomerAccount;
import com.revature.models.user.Customer;
import com.revature.services.*;


import java.sql.Connection;
import java.sql.SQLException;

public class BankAppDriver {

    public static void main(String args[]) {

        CustomerDao cd1 = new CustomerDaoImp();

        CustomerService cs = new CustomerService();

        Customer cust1 = cs.createCustomer("Warren", "Ho", "warren.ho@foo.net");
        CustomerAccount custa1 = cs.createCustomerAccount("qwerty", "poiuy");

        //boolean success = cd1.deposit(custa1, 25.0);
        double balance = cd1.viewBalance(custa1);

        System.out.println("success" + balance);





       /* try {
            Connection c = ConnectionUtil.getConnection();
            System.out.println(c.getMetaData().getDriverName());
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }
}
