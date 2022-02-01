package com.revature.daos;

import com.revature.models.account.CustomerAccount;
import com.revature.models.account.PendingAccount;
import com.revature.models.user.Customer;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImp extends CustomerDaoImp implements EmployeeDao{

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
}
