package com.revature.daos;

import com.revature.BankAppDriver;
import com.revature.models.account.BankAccount;
import com.revature.models.account.CustomerAccount;
import com.revature.models.account.PendingAccount;
import com.revature.models.user.Customer;
import com.revature.models.user.Employee;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.Transaction;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoImplement implements CustomerDao, EmployeeDao, BankAdminDao{

    @Override
    public boolean registerEmployee(Employee employee) {
        String sql1 = "insert into person (first, last, user_type, email, date_join) values (?, ?, ?, ?, ?)";
        try (Connection connect = ConnectionUtil.getConnection();
             PreparedStatement ps1 = connect.prepareStatement(sql1);) {

            //Insert into person
            ps1.setString(1, employee.getFirstName());
            ps1.setString(2, employee.getLastName());
            ps1.setInt(3, employee.getType().ordinal());
            ps1.setString(4, employee.getEmail());
            ps1.setString(5, employee.getDateEmployed());


            int rowsAffectedPerson = ps1.executeUpdate();

            if(rowsAffectedPerson == 1)
                return true;

        } catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean registerUser(Customer customer) {
        String sql1 = "insert into person (first, last, user_type, email, date_join) values (?, ?, ?, ?, ?)";
        try (Connection connect = ConnectionUtil.getConnection();
             PreparedStatement ps1 = connect.prepareStatement(sql1);) {

            //Insert into person
            ps1.setString(1, customer.getFirstName());
            ps1.setString(2, customer.getLastName());
            ps1.setInt(3, customer.getType().ordinal());
            ps1.setString(4, customer.getEmail());
            ps1.setString(5, customer.getDateJoin());


            int rowsAffectedPerson = ps1.executeUpdate();

            if(rowsAffectedPerson == 1)
                return true;

        } catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean registerUserAccount(CustomerAccount ca) {
        String sql2 = "insert into account (user_id, username, password, account_type, email) values ((select id from person where person.email = '" + ca.getEmail() + "'), ?, ?, ?, ?)";
        try (Connection connect = ConnectionUtil.getConnection();
             PreparedStatement ps2 = connect.prepareStatement(sql2);) {

            //Insert into account
            ps2.setString(1, ca.getUsername());
            ps2.setString(2, ca.getPassword());
            ps2.setInt(3, ca.getType().ordinal());
            ps2.setString(4, ca.getEmail());

            int rowsAffectedAccount = ps2.executeUpdate();

            if(rowsAffectedAccount == 1)
                return true;

        } catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public boolean applyBankAccount(Transaction t) {
        String sql = "insert into pending_approval(user_account, balance) values ((select id from account where username = '" + t.getUsername() + "'), ?)";
        try (Connection connect = ConnectionUtil.getConnection();
             PreparedStatement ps = connect.prepareStatement(sql);) {

            ps.setDouble(1, t.getBalance());

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
    public boolean deposit(Transaction t) {

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
            newBalance = existingBalance + t.getBalance();
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
    public boolean withdraw(Transaction t) {

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
            newBalance = existingBalance - t.getBalance();
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
    public boolean approveBankAccount(CustomerAccount ca) {
        String sql1 = "insert into bank_account(user_account, balance) values (?, ?)";
        String sql4 = "select pending_approval.user_account, pending_approval.balance, account.username from pending_approval inner join account on pending_approval.user_account = account.id and account.username = '" + ca.getUsername() + "'";
        String sql2 = "delete from pending_approval using account where pending_approval.user_account = account.id";
        String sql3 = "alter sequence pending_approval_id_seq restart with 1";

        try(Connection connect = ConnectionUtil.getConnection();
            PreparedStatement ps1 = connect.prepareStatement(sql1);
            Statement s1 = connect.createStatement();
            PreparedStatement ps2 = connect.prepareStatement(sql2);
            PreparedStatement ps3 = connect.prepareStatement(sql3);) {

            ResultSet rs = s1.executeQuery(sql4);
            rs.next();

            ps1.setInt(1, rs.getInt("user_account"));
            ps1.setDouble(2, rs.getDouble("balance"));

            ps1.executeUpdate();
            ps2.executeUpdate();
            ps3.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<PendingAccount> viewPendingAccounts() {
        String sql = "select * from pending_approval";
        List<PendingAccount> pendingList = new ArrayList<>();

        try (Connection connect = ConnectionUtil.getConnection();
             Statement s1 = connect.createStatement();) {

            ResultSet rs = s1.executeQuery(sql);

            while(rs.next()) {
                PendingAccount pa = new PendingAccount();
                int id = rs.getInt("id");
                pa.setPendingAccountID(id);

                int uid = rs.getInt("user_account");
                pa.setUserAccountID(uid);

                double balance = rs.getDouble("balance");
                pa.setBalance(balance);

                pendingList.add(pa);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return pendingList;
    }

    @Override
    public List<Employee> viewEmployees() {
        String sql = "select * from person where user_type = 1";

        List<Employee> employeeList = new ArrayList<>();

        try (Connection connect = ConnectionUtil.getConnection();
             Statement s1 = connect.createStatement();) {

            ResultSet rs = s1.executeQuery(sql);

            while(rs.next()) {
                Employee e = new Employee();
                int id = rs.getInt("id");
                e.setEmployeeID(id);;

                String first = rs.getString("first");
                e.setFirstName(first);

                String last = rs.getString("last");
                e.setLastName(last);

                String email = rs.getString("email");
                e.setEmail(email);

                String date = rs.getString("date_join");
                e.setDateEmployed(date);

                employeeList.add(e);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public boolean deleteAccount(Customer cu) {

        String email = cu.getEmail();
        String sql1 = "delete from bank_account using person, account where bank_account.user_account = account.id and account.user_id = person.id and person.email = '" + email + "'";
        String sql2 = "delete from account using person where account.user_id = person.id and person.email = '" + email + "'";
        String sql3 = "delete from person where email = '" + email + "'";

        try (Connection connect = ConnectionUtil.getConnection();
             PreparedStatement ps1 = connect.prepareStatement(sql1);
             PreparedStatement ps2 = connect.prepareStatement(sql2);
             PreparedStatement ps3 = connect.prepareStatement(sql3);) {

            ps1.executeUpdate();
            ps2.executeUpdate();
            ps3.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Customer> viewAllCustomer() {
        String sql = "select * from person where user_type = 0";

        List<Customer> customerList = new ArrayList<>();

        try (Connection connect = ConnectionUtil.getConnection();
             Statement s1 = connect.createStatement();) {

            ResultSet rs = s1.executeQuery(sql);

            while(rs.next()) {
                Customer c = new Customer();
                int id = rs.getInt("id");
                c.setCustomerID(id);;

                String first = rs.getString("first");
                c.setFirstName(first);

                String last = rs.getString("last");
                c.setLastName(last);

                String email = rs.getString("email");
                c.setEmail(email);

                String date = rs.getString("date_join");
                c.setDateJoin(date);

                customerList.add(c);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }
}
