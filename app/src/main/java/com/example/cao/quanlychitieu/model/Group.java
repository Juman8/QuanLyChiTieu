package com.example.cao.quanlychitieu.model;

/**
 * Created by HP on 2/1/2018.
 */

public class Group {
    private String ID;
    private String Group_Title;
    private int Group_SoDu;
    private String User_Gmail;
    private String Group_GhiChu;
    private String Group_Ngaydang;
    private int Group_SoDanhMuc;
    private int Group_TongChiTieu;
    private int Group_Thuoctinh;

    public Group(String ID, String group_Title, int group_SoDu, String group_GhiChu, String group_Ngaydang, int group_SoDanhMuc, int group_TongChiTieu, int group_Thuoctinh) {
        this.ID = ID;
        Group_Title = group_Title;
        Group_SoDu = group_SoDu;
        Group_GhiChu = group_GhiChu;
        Group_Ngaydang = group_Ngaydang;
        Group_SoDanhMuc = group_SoDanhMuc;
        Group_TongChiTieu = group_TongChiTieu;
        Group_Thuoctinh = group_Thuoctinh;
    }

    public String getUser_Gmail() {
        return User_Gmail;
    }

    public void setUser_Gmail(String user_Gmail) {
        User_Gmail = user_Gmail;
    }

    public String getGroup_Ngaydang() {
        return Group_Ngaydang;
    }

    public void setGroup_Ngaydang(String group_Ngaydang) {
        Group_Ngaydang = group_Ngaydang;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getGroup_Thuoctinh() {
        return Group_Thuoctinh;
    }

    public void setGroup_Thuoctinh(int group_Thuoctinh) {
        Group_Thuoctinh = group_Thuoctinh;
    }

    public Group() {

    }

    public String getGroup_Title() {
        return Group_Title;
    }

    public void setGroup_Title(String group_Title) {
        Group_Title = group_Title;
    }

    public int getGroup_SoDu() {
        return Group_SoDu;
    }

    public void setGroup_SoDu(int group_SoDu) {
        Group_SoDu = group_SoDu;
    }

    public String getGroup_GhiChu() {
        return Group_GhiChu;
    }

    public void setGroup_GhiChu(String group_GhiChu) {
        Group_GhiChu = group_GhiChu;
    }

    public int getGroup_SoDanhMuc() {
        return Group_SoDanhMuc;
    }

    public void setGroup_SoDanhMuc(int group_SoDanhMuc) {
        Group_SoDanhMuc = group_SoDanhMuc;
    }

    public int getGroup_TongChiTieu() {
        return Group_TongChiTieu;
    }

    public void setGroup_TongChiTieu(int group_TongChiTieu) {
        Group_TongChiTieu = group_TongChiTieu;
    }
}
