package com.revature;

import com.revature.daos.*;
import com.revature.models.account.*;
import com.revature.models.user.*;
import com.revature.services.*;
import com.revature.utils.Transaction;
import io.javalin.Javalin;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankAppDriver {

    private final static Logger logger = Logger.getLogger(BankAppDriver.class);

    public static void main(String args[]) {


        logger.info("Start of Application");

        Javalin app = Javalin.create().start();

        BankAdminDao bankAdmin = new DaoImplement();
        EmployeeDao employee = new DaoImplement();
        CustomerDao customer = new DaoImplement();


        //-------BankAdmin-------
        //View all employees

        //Register employee
        app.post("/bank-admin/register-employee", ctx -> {
            Employee e1 = ctx.bodyAsClass(Employee.class);
            boolean success = bankAdmin.registerEmployee(e1);

            if(success)
                ctx.status(201);
            else
                ctx.status(400);
        });

        //View all employee
        app.get("/bank-admin/all-employee", ctx -> {
            List<Employee> empList = bankAdmin.viewEmployees();
            ctx.json(empList);
        });


        //Delete account
        app.delete("/bank-admin", ctx -> {
            Customer c = ctx.bodyAsClass(Customer.class);
            boolean success = bankAdmin.deleteAccount(c);

            if(success)
                ctx.status(200);
            else
                ctx.status(400);

        });

        //-------Employee-------
        //View all customers
        app.get("/employee/all-customer", ctx -> {
            List<Customer> c = employee.viewAllCustomer();
            ctx.json(c);
        });

        //View all pending accounts
        app.get("/employee/pending-account", ctx -> {
            List<PendingAccount> c1 = employee.viewPendingAccounts();
            ctx.json(c1);
        });

        //Approve Bank Account
        app.post("/employee/approve", ctx -> {
            CustomerAccount ca3 = ctx.bodyAsClass(CustomerAccount.class);
            boolean success = employee.approveBankAccount(ca3);

            if(success)
                ctx.status(201);
            else
                ctx.status(400);
        });


        //-------Customer-------
        //View account balance
        app.get("/customer/balance", ctx -> {
            CustomerAccount ca = ctx.bodyAsClass(CustomerAccount.class);
            double bal = customer.viewBalance(ca);
            ctx.json(bal);
        });


        //Apply for bank account
        app.post("/customer/apply", ctx -> {
            Transaction t1 = ctx.bodyAsClass(Transaction.class);
            boolean success = customer.applyBankAccount(t1);

            if(success)
                ctx.status(201);
            else
                ctx.status(400);
        });

        //Register customer
        app.post("/customer/register-user", ctx -> {
            Customer c1 = ctx.bodyAsClass(Customer.class);
            boolean success = customer.registerUser(c1);

            if(success)
                ctx.status(201);
            else
                ctx.status(400);
        });

        //Register customer account
        app.post("/customer/register-account", ctx -> {
            CustomerAccount ca2 = ctx.bodyAsClass(CustomerAccount.class);
            boolean success = customer.registerUserAccount(ca2);

            if(success)
                ctx.status(201);
            else
                ctx.status(400);
        });

        //withdraw
        app.post("/customer/withdraw", ctx -> {
            Transaction t1 = ctx.bodyAsClass(Transaction.class);
            customer.withdraw(t1);

        });

        //Deposit
        app.post("/customer/deposit", ctx -> {
            Transaction t1 = ctx.bodyAsClass(Transaction.class);
            customer.deposit(t1);
        });

    }

}
