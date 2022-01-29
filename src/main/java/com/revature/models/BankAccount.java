package com.revature.models;

public class BankAccount {

    //For incrementing bank account numbers.
    public static long bankAccountNoTracker = 1;

    private long bankAccountNo;
    private double balance;


    public BankAccount() {
        this.balance = 0.0;
        this.bankAccountNo = bankAccountNoTracker++;
    }

    public BankAccount(double balance) {
        this.balance = balance;
        this.bankAccountNo = bankAccountNoTracker++;
    }

    public static long getBankAccountNoTracker() {
        return bankAccountNoTracker;
    }

    public long getBankAccountNo() {
        return bankAccountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
