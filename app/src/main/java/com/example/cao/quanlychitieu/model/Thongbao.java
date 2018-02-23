package com.example.cao.quanlychitieu.model;

/**
 * Created by HP on 2/1/2018.
 */

public class Thongbao {
    private int Thongbao_ID;
    private int Account_ID;
    private String Thongbao_Title;
    private String Thongbao_NoiDung;

    public Thongbao() {
    }

    public Thongbao(int thongbao_ID, int account_ID, String thongbao_Title, String thongbao_NoiDung) {
        Thongbao_ID = thongbao_ID;
        Account_ID = account_ID;
        Thongbao_Title = thongbao_Title;
        Thongbao_NoiDung = thongbao_NoiDung;
    }

    public int getThongbao_ID() {
        return Thongbao_ID;
    }

    public void setThongbao_ID(int thongbao_ID) {
        Thongbao_ID = thongbao_ID;
    }

    public int getAccount_ID() {
        return Account_ID;
    }

    public void setAccount_ID(int account_ID) {
        Account_ID = account_ID;
    }

    public String getThongbao_Title() {
        return Thongbao_Title;
    }

    public void setThongbao_Title(String thongbao_Title) {
        Thongbao_Title = thongbao_Title;
    }

    public String getThongbao_NoiDung() {
        return Thongbao_NoiDung;
    }

    public void setThongbao_NoiDung(String thongbao_NoiDung) {
        Thongbao_NoiDung = thongbao_NoiDung;
    }
}
