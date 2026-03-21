package com.dat.danhsachdoibong;

public class DoiBong {
    private String tenDoi;
    private String namVoDich;
    private String co;        // emoji cờ quốc gia
    private int soLanVoDich;

    public DoiBong(String tenDoi, String namVoDich, String co, int soLanVoDich) {
        this.tenDoi = tenDoi;
        this.namVoDich = namVoDich;
        this.co = co;
        this.soLanVoDich = soLanVoDich;
    }

    public String getTenDoi() { return tenDoi; }
    public String getNamVoDich() { return namVoDich; }
    public String getCo() { return co; }
    public int getSoLanVoDich() { return soLanVoDich; }
}
