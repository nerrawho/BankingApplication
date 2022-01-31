package com.revature;

import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class BankAppDriver {

    public static void main(String args[]) {

        try {
            Connection c = ConnectionUtil.getConnection();
            System.out.println(c.getMetaData().getDriverName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
