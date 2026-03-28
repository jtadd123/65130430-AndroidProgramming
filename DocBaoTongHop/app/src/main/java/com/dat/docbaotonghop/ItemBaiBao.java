package com.dat.docbaotonghop;

public class ItemBaiBao {
    private String tieuDeBaiBao;
    private String thoiDiemDangTin;   // Chuỗi đã format: "Thứ Bảy, 28/03/2026 lúc 18:02"
    private String urlAnhDaiDien;
    private String duongDan;
    private boolean daDanhDau;
    private long pubDateMillis;        // Timestamp gốc để lọc theo thời gian


    // Constructor rỗng
    public ItemBaiBao() {
    }

    // Constructor đầy đủ
    public ItemBaiBao(String tieuDeBaiBao, String thoiDiemDangTin,
                      String urlAnhDaiDien, String duongDan) {
        this.tieuDeBaiBao = tieuDeBaiBao;
        this.thoiDiemDangTin = thoiDiemDangTin;
        this.urlAnhDaiDien = urlAnhDaiDien;
        this.duongDan = duongDan;
        this.daDanhDau = false;
    }

    // Getter & Setter
    public String getTieuDeBaiBao() {
        return tieuDeBaiBao;
    }

    public void setTieuDeBaiBao(String tieuDeBaiBao) {
        this.tieuDeBaiBao = tieuDeBaiBao;
    }

    public String getThoiDiemDangTin() {
        return thoiDiemDangTin;
    }

    public void setThoiDiemDangTin(String thoiDiemDangTin) {
        this.thoiDiemDangTin = thoiDiemDangTin;
    }

    public String getUrlAnhDaiDien() {
        return urlAnhDaiDien;
    }

    public void setUrlAnhDaiDien(String urlAnhDaiDien) {
        this.urlAnhDaiDien = urlAnhDaiDien;
    }

    public String getDuongDan() {
        return duongDan;
    }

    public void setDuongDan(String duongDan) {
        this.duongDan = duongDan;
    }

    public boolean isDaDanhDau() {
        return daDanhDau;
    }

    public void setDaDanhDau(boolean daDanhDau) {
        this.daDanhDau = daDanhDau;
    }

    public long getPubDateMillis() {
        return pubDateMillis;
    }

    public void setPubDateMillis(long pubDateMillis) {
        this.pubDateMillis = pubDateMillis;
    }
}