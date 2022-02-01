package com.revature.daos;

import com.revature.models.account.CustomerAccount;
import com.revature.models.user.Customer;
import com.revature.services.CustomerService;
import com.revature.utils.ConnectionUtil;

import java.sql.*;

public class CustomerDaoImp implements CustomerDao{

    @Override
    public boolean registerUserAccount(Customer customer, CustomerAccount ca) {
        String sql1 = "insert into person (first, last, user_type, email, date_join) values (?, ?, ?, ?, ?)";
        String sql2 = "insert into account (user_id, username, password, account_type) values ((select id from person where email = '" + customer.getEmail() + "'), ?, ?, ?)";
        try (Connection connect = ConnectionUtil.getConnection();
             PreparedStatement ps1 = connect.prepareStatement(sql1);
             PreparedStatement ps2 = connect.prepareStatement(sql2);) {

            //Insert into person
            ps1.setString(1, customer.getFirstName());
            ps1.setString(2, customer.getLastName());
            ps1.setInt(3, customer.getType().ordinal());
            ps1.setString(4, customer.getEmail());
            ps1.setString(5, customer.getDateJoin());


            //Insert into account
            ps2.setString(1, ca.getUsername());
            ps2.setString(2, ca.getPassword());
            ps2.setInt(3, ca.getType().ordinal());

            int rowsAffectedPerson = ps1.executeUpdate();
            int rowsAffectedAccount = ps2.executeUpdate();

            if(rowsAffectedAccount == 1 && rowsAffectedPerson == 1)
                return true;

        } catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public boolean applyBankAccount(CustomerAccount ca, double balance) {
        String sql = "insert into pending_approval(user_account, balance) values ((select id from account where username = '" + ca.getUsername() + "'), ?)";
        try (Connection connect = ConnectionUtil.getConnection();
             PreparedStatement ps = connect.prepareStatement(sql);) {

            ps.setDouble(1, balance);

            int rowsAffectedPending = ps.executeUpdate();
            if(rowsAffectedPending == 1)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public double viewBalance(CustomerAccount ca) {

        double fullBalance = 0.0;
        String sql = "select bank_account.balance, account.username from bank_account inner join account on bank_account.user_account = account.id and account.username = '" + ca.getUsername() + "'";
        try (Connection connect = ConnectionUtil.getConnection();
            Statement s1 = connect.createStatement();) {

            ResultSet rs = s1.executeQuery(sql);
            rs.next();

            fullBalance = rs.getDouble("balance");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fullBalance;
    }

    @Override
    public boolean deposit(CustomerAccount ca, double balance) {

        double newBalance= 0.0;
        double existingBalance = 0.0;
        String sql1 = "select bank_account.balance from bank_account inner join account on bank_account.user_account = account.id";

        try (Connection connect = ConnectionUtil.getConnection();
             Statement s1 = connect.createStatement();) {

            ResultSet rs = s1.executeQuery(sql1);
            rs.next();
            existingBalance = rs.getDouble("balance");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(existingBalance != 0)
            newBalance = existingBalance + balance;
        else
            newBalance = existingBalance;

        String sql2 = "update bank_account set balance = "  + newBalance + " from account where account.id = bank_account.user_account";

        try (Connection connect = ConnectionUtil.getConnection();
             PreparedStatement s2 = connect.prepareStatement(sql2);) {

            int rowsAffectedPending = s2.executeUpdate();
            if(rowsAffectedPending == 0)
                return true;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean withdraw(CustomerAccount ca, double balance) {

        double newBalance= 0.0;
        double existingBalance = 0.0;
        String sql1 = "select bank_account.balance from bank_account inner join account on bank_account.user_account = account.id";

        try (Connection connect = ConnectionUtil.getConnection();
             Statement s1 = connect.createStatement();) {

            ResultSet rs = s1.executeQuery(sql1);
            rs.next();
            existingBalance = rs.getDouble("balance");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(existingBalance != 0)
            newBalance = existingBalance - balance;
        else
            newBalance = existingBalance;

        String sql2 = "update bank_account set balance = "  + newBalance + " from account where account.id = bank_account.user_account";

        try (Connection connect = ConnectionUtil.getConnection();
             PreparedStatement s2 = connect.prepareStatement(sql2);) {

            int rowsAffectedPending = s2.executeUpdate();
            if(rowsAffectedPending == 0)
                return true;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
