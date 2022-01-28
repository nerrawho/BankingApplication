package com.revature.models;

public class BankAccount extends Account {

    private int bankAccountID;
    private int bankAccountNumber;
    private double balance;
    private int bankAccountTypeID;

    public BankAccount() {
        super();
        this.bankAccountNumber = 0;
        this.balance = 0;
        this.bankAccountTypeID = 0;
    }

    public BankAccount(String password, String accountName, String firstName, String lastName, String accountID, double balance, int bankAccountType) {
        super(password, accountName, firstName, lastName, accountID);
        this.bankAccountNumber = 0;
        this.balance = balance;
        this.bankAccountTypeID = bankAccountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getBankAccountType() {
        return bankAccountTypeID;
    }

    public void setBankAccountType(int bankAccountType) {
        this.bankAccountTypeID = bankAccountType;
    }
}


