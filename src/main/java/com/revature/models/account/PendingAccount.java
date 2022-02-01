package com.revature.models.account;

public class PendingAccount {

    private long pendingAccountID;
    private double balance;
    private int type;


    public PendingAccount() {
        this.balance = 0.0;
    }

    public PendingAccount(double balance) {
        type = AccountType.BANK.ordinal();
        this.balance = balance;
    }

    public PendingAccount(String username, String password) {
        type = AccountType.EMPLOYEE.ordinal();

    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

