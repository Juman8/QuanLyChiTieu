package com.example.cao.quanlychitieu.model;

/**
 * Created by HP on 3/9/2018.
 */

public class Group_Gmail {
    private String GroupID;
    //private String User_Gmail;
    private int Role;

    public Group_Gmail() {
    }

    public Group_Gmail( String gmail, int role) {
        GroupID = gmail;
        Role = role;
    }

    public String getGmail() {
        return GroupID;
    }

    public void setGmail(String gmail) {
        GroupID = gmail;
    }

    public int getRole() {
        return Role;
    }

    public void setRole(int role) {
        Role = role;
    }
}
