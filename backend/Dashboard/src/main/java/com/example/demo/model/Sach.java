package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name="SachEntity")
@Table(name ="newsach")
public class Sach {

    @Id
    @Column(name = "MaSach", columnDefinition = "varchar(25)")
    private String maSach;

    @Column(name = "TenSach", columnDefinition = "nvarchar(200)")
    private String tenSach;

    @Column(name = "LinkAnh", columnDefinition = "varchar(MAX)")
    private String linkAnh;

    @Column(name = "GiaGoc", columnDefinition = "varchar(30)")
    private String giaGoc;

    @Column(name = "GiaKM", columnDefinition = "varchar(30)")
    private String giaKM;

    @Column(name = "TenTacGia", columnDefinition = "nvarchar(150)")
    private String tenTG;

    @Column(name = "TenDoiTuong", columnDefinition = "nvarchar(150)")
    private String tenDoiTuong;

    @Column(name = "SoTrang", columnDefinition = "varchar(10)")
    private String soTrang;

    @Column(name = "SoLuongCon")
    private int soLuongCon;

    @Column(name = "MaDM", columnDefinition = "varchar(25)")
    private String maDM;

    public Sach() {
    }

    public Sach(String maSach, String tenSach, String linkAnh, String giaGoc, String giaKM, String tenTG,
                String tenDoiTuong, String soTrang, int soLuongCon, String maDM) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.linkAnh = linkAnh;
        this.giaGoc = giaGoc;
        this.giaKM = giaKM;
        this.tenTG = tenTG;
        this.tenDoiTuong = tenDoiTuong;
        this.soTrang = soTrang;
        this.soLuongCon = soLuongCon;
        this.maDM = maDM;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        this.linkAnh = linkAnh;
    }

    public String getGiaGoc() {
        return giaGoc;
    }

    public void setGiaGoc(String giaGoc) {
        this.giaGoc = giaGoc;
    }

    public String getGiaKM() {
        return giaKM;
    }

    public void setGiaKM(String giaKM) {
        this.giaKM = giaKM;
    }

    public String getTenTG() {
        return tenTG;
    }

    public void setTenTG(String tenTG) {
        this.tenTG = tenTG;
    }

    public String getTenDoiTuong() {
        return tenDoiTuong;
    }

    public void setTenDoiTuong(String tenDoiTuong) {
        this.tenDoiTuong = tenDoiTuong;
    }

    public String getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(String soTrang) {
        this.soTrang = soTrang;
    }

    public int getSoLuongCon() {
        return this.soLuongCon;
    }

    public void setSoLuongCon(int soLuongCon) {
        this.soLuongCon = soLuongCon;
    }

    public String getMaDM() {
        return maDM;
    }

    public void setMaDM(String maDM) {
        this.maDM = maDM;
    }

    public int getSoLuongBan() {
        return (1000 - this.soLuongCon);
    }

    public String getGiamGia() {
        String sales = "";
        if (this.soLuongCon > 700 & this.soLuongCon < 800) {
            sales = "10%";
        } else if (this.soLuongCon < 900 & this.soLuongCon >= 800) {
            sales = "20%";
        } else if (this.soLuongCon <= 1000 & this.soLuongCon >= 900) {
            sales = "30%";
        } else
            sales = "0%";
        return sales;
    }

    public float getGiaSales() {
        float salesCost = 0;
        try {
            String price = giaKM.substring(0, giaKM.length() - 1).replace(",", "");
            float price2 = Float.parseFloat(price);
            if (this.soLuongCon > 700 & this.soLuongCon < 800) {
                salesCost = price2 * 90 / 100;
            } else if (this.soLuongCon < 900 & this.soLuongCon >= 800) {
                salesCost = price2 * 80 / 100;
            } else if (this.soLuongCon <= 1000 & this.soLuongCon >= 900) {
                salesCost = price2 * 70 / 100;
            } else
                salesCost = price2;
        } catch (NumberFormatException e) {
            e.printStackTrace(); // Handle the exception in case of invalid number format
        }
        return salesCost;
    }
}
