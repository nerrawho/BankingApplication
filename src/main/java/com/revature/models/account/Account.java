package com.revature.models.account;

public class Account {

    private long accountID;
    private String username;
    private String password;

    public Account() {
        this(null, "password");
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //Mutators

    public long getAccountID() {
        return accountID;
    }

    public void setAccountID(long accountID) {
        this.accountID = accountID;
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
}
