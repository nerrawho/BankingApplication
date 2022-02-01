package com.revature;

import com.revature.daos.*;
import com.revature.models.account.*;
import com.revature.models.user.*;
import com.revature.services.*;
import io.javalin.Javalin;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankAppDriver {

    public static void main(String args[]) {
        Javalin app = Javalin.create().start();

        BankAdminDao bankAdmin = new DaoImplement();

        app.get("/bank-admin", ctx -> {
            List<Employee> employee = bankAdmin.viewEmployees();
            ctx.json(employee);
        });

        app.delete("/bank-admin", ctx -> {

        })
    }
}
