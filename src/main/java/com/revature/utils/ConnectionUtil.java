package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:postgresql://35.193.162.63:5432/postgres";
        String username = "postgres";
        String password = "Deltawarp711";
        return DriverManager.getConnection(url, username, password);
    }
}
