package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "donhang")
public class DonHang {
	@Id
	@Column(name = "IDDonHang")
	private int idDonHang;
	private LocalDate ngayDatHang;
	private String trangThaiDonHang;
	private String tenKhachHang;
	private String diaChiGiaoHang;
	private String soDienThoai;
	private String email;
	private String phuongThucThanhToan;
	private double tongGiaTriDonHang;

	public DonHang() {
	}

	public DonHang(int idDonHang, LocalDate ngayDatHang, String trangThaiDonHang, String tenKhachHang,
			String diaChiGiaoHang, String soDienThoai, String email, String phuongThucThanhToan,
			double tongGiaTriDonHang) {
		this.idDonHang = idDonHang;
		this.ngayDatHang = ngayDatHang;
		this.trangThaiDonHang = trangThaiDonHang;
		this.tenKhachHang = tenKhachHang;
		this.diaChiGiaoHang = diaChiGiaoHang;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.phuongThucThanhToan = phuongThucThanhToan;
		this.tongGiaTriDonHang = tongGiaTriDonHang;
	}

	// Getters và Setters
	public int getIdDonHang() {
		return idDonHang;
	}

	public void setIdDonHang(int idDonHang) {
		this.idDonHang = idDonHang;
	}

	public LocalDate getNgayDatHang() {
		return ngayDatHang;
	}

	public void setNgayDatHang(LocalDate ngayDatHang) {
		this.ngayDatHang = ngayDatHang;
	}

	public String getTrangThaiDonHang() {
		return trangThaiDonHang;
	}

	public void setTrangThaiDonHang(String trangThaiDonHang) {
		this.trangThaiDonHang = trangThaiDonHang;
	}

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public String getDiaChiGiaoHang() {
		return diaChiGiaoHang;
	}

	public void setDiaChiGiaoHang(String diaChiGiaoHang) {
		this.diaChiGiaoHang = diaChiGiaoHang;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhuongThucThanhToan() {
		return phuongThucThanhToan;
	}

	public void setPhuongThucThanhToan(String phuongThucThanhToan) {
		this.phuongThucThanhToan = phuongThucThanhToan;
	}

	public double getTongGiaTriDonHang() {
		return tongGiaTriDonHang;
	}

	public void setTongGiaTriDonHang(double tongGiaTriDonHang) {
		this.tongGiaTriDonHang = tongGiaTriDonHang;
	}
}
