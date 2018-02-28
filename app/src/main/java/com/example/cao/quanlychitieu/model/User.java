package com.example.cao.quanlychitieu.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HP on 2/1/2018.
 */

public class User {
    private String ID;
    private String User_Avatar;
    private String User_Name;
    private String User_date;
    private String User_gioitinh;
    private String User_Gmail;
    private String User_NumberPhone;

    public User() {
    }

    public User(String ID, String user_Avatar, String user_Name, String user_date, String user_gioitinh, String user_Gmail, String user_NumberPhone) {
        this.ID = ID;
        User_Avatar = user_Avatar;
        User_Name = user_Name;
        User_date = user_date;
        User_gioitinh = user_gioitinh;
        User_Gmail = user_Gmail;
        User_NumberPhone = user_NumberPhone;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUser_Avatar() {
        return User_Avatar;
    }

    public void setUser_Avatar(String user_Avatar) {
        User_Avatar = user_Avatar;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getUser_date() {
        return User_date;
    }

    public void setUser_date(String user_date) {
        User_date = user_date;
    }

    public String getUser_gioitinh() {
        return User_gioitinh;
    }

    public void setUser_gioitinh(String user_gioitinh) {
        User_gioitinh = user_gioitinh;
    }

    public String getUser_Gmail() {
        return User_Gmail;
    }

    public void setUser_Gmail(String user_Gmail) {
        User_Gmail = user_Gmail;
    }

    public String getUser_NumberPhone() {
        return User_NumberPhone;
    }

    public void setUser_NumberPhone(String user_NumberPhone) {
        User_NumberPhone = user_NumberPhone;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("User_Avatar", User_Avatar);
        result.put("User_Name", User_Name);
        result.put("User_date", User_date);
        result.put("User_gioitinh", User_gioitinh);
        result.put("User_NumberPhone", User_NumberPhone);

        return result;
    }
}
