package com.example.cao.quanlychitieu.model;

/**
 * Created by HP on 2/1/2018.
 */

public class ChiTiet extends Group{
    private String ChiTiet_MatHang;
    private double ChiTiet_GiaTri;

    public ChiTiet(String chiTiet_MatHang, double chiTiet_GiaTri) {
        ChiTiet_MatHang = chiTiet_MatHang;
        ChiTiet_GiaTri = chiTiet_GiaTri;
    }

    public ChiTiet() {

    }
    public String getChiTiet_MatHang() {
        return ChiTiet_MatHang;
    }

    public void setChiTiet_MatHang(String chiTiet_MatHang) {
        ChiTiet_MatHang = chiTiet_MatHang;
    }

    public double getChiTiet_GiaTri() {
        return ChiTiet_GiaTri;
    }

    public void setChiTiet_GiaTri(double chiTiet_GiaTri) {
        ChiTiet_GiaTri = chiTiet_GiaTri;
    }
}
