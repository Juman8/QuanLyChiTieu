package com.example.cao.quanlychitieu.model;

/**
 * Created by HP on 2/1/2018.
 */

public class Account {
    private String Account_Passwords;
    private int Account_Ruler;

    public Account() {
    }

    public Account(String account_ID, int user_ID, String account_Passwords, int account_Ruler) {
        Account_Passwords = account_Passwords;
        Account_Ruler = account_Ruler;
    }

    public String getAccount_Passwords() {
        return Account_Passwords;
    }

    public void setAccount_Passwords(String account_Passwords) {
        Account_Passwords = account_Passwords;
    }

    public int getAccount_Ruler() {
        return Account_Ruler;
    }

    public void setAccount_Ruler(int account_Ruler) {
        Account_Ruler = account_Ruler;
    }
}
