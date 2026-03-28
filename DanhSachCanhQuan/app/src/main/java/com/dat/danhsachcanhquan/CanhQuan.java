package com.dat.danhsachcanhquan;

public class CanhQuan {
    private String ten;
    private String diaDiem;
    private String moTa;
    private int hinhAnh; // resource id

    public CanhQuan(String ten, String diaDiem, String moTa, int hinhAnh) {
        this.ten = ten;
        this.diaDiem = diaDiem;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
    }

    public String getTen() { return ten; }
    public String getDiaDiem() { return diaDiem; }
    public String getMoTa() { return moTa; }
    public int getHinhAnh() { return hinhAnh; }
}
