package com.revature.models.account;

public class BankAccount {

    private long bankAccountID;
    private double balance;


    public BankAccount() {
        this.balance = 0.0;
    }

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
