package com.revature.models.account;

public class Account {

    //Used to increment account number.
    private static long accountNoTracker = 1;

    private long accountNo;
    private String username;
    private String password;

    public Account() {
        this(null, "password");
        this.accountNo = accountNoTracker++;
    }

    public Account(String username, String password) {
        this.accountNo = accountNoTracker++;
        this.username = username;
        this.password = password;
    }

    //Mutators

    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static long getAccountNoTracker() {
        return accountNoTracker;
    }
}
