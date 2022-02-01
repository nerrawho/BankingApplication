package com.revature.models.account;

public class PendingAccount {

    private long pendingAccountID;
    private long userAccountID;
    private double balance;
    private AccountType type = AccountType.PENDING;


    public PendingAccount() {
        this.balance = 0.0;
    }

    public PendingAccount(long pendingAccountId, long userAccountID, double balance) {
        this.pendingAccountID = pendingAccountId;
        this.userAccountID = userAccountID;
        this.balance = balance;
    }


    //Mutators
    public long getPendingAccountID() {
        return pendingAccountID;
    }

    public void setPendingAccountID(long pendingAccountID) {
        this.pendingAccountID = pendingAccountID;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public long getUserAccountID() {
        return userAccountID;
    }

    public void setUserAccountID(long userAccountID) {
        this.userAccountID = userAccountID;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "PendingAccount{" +
                "pendingAccountID=" + pendingAccountID +
                ", userAccountID=" + userAccountID +
                ", balance=" + balance +
                '}';
    }
}

