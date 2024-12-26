package com.example.demo.model;

import jakarta.validation.constraints.*;

public class Sach1DTO {
	@NotBlank(message = "Mã sách không được để trống")
	private String MaSach;

	@NotBlank(message = "Tên sách không được để trống")
	private String tenSach;

	@NotNull(message = "Giá sách không được để trống")
	@Min(value = 1000, message = "Giá sách phải lớn hơn 1000 VNĐ")
	private String GiaGoc;

	@NotNull(message = "Giá sách không được để trống")
	@Min(value = 1000, message = "Giá sách phải lớn hơn 1000 VNĐ")
	private String GiaKM;

	@NotEmpty(message = "Tên Tác gải không để trống")
	private String TenTG;

	@Size(min = 10, message = "Ít thì 5 từ")
	@Size(max = 50, message = "Nhiều thì 50 từ")
	private String TenDoiTuong;

	@Pattern(regexp = "\\d+", message = "Nhập số nguyên")
	private String SoTrang;

	@Positive(message = "Số lượng còn phải lớn hơn 0")
	private int SoLuongCon;

	@NotEmpty(message = "Nhập Link Ảnh")
	private String LinkAnh;

	@NotEmpty(message = "Nhập theo dạng DMABCB với ABCD là số")
	private String MaDM;

	/*
	 * public Sach1DTO() {} public Sach1DTO(String MaSach, String TenSach, double
	 * GiaGoc, double GiaKM, String TenTG, String TenDoiTuong, String SoTrang,
	 * String SoLuongCon, String LinkAnh) { this.MaSach=MaSach;
	 * this.TenSach=TenSach; this.GiaGoc=GiaGoc; this.GiaKM=GiaKM; this.TenTG=TenTG;
	 * this.TenDoiTuong=TenDoiTuong; this.SoTrang=SoTrang;
	 * this.SoLuongCon=SoLuongCon; this.LinkAnh=LinkAnh; }
	 */

	public String getMaSach() {
		return MaSach;
	}

	public String getMaDM() {
		return MaDM;
	}

	public void setMaDM(String maDM) {
		MaDM = maDM;
	}

	public void setMaSach(String maSach) {
		MaSach = maSach;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public String getGiaGoc() {
		return GiaGoc;
	}

	public void setGiaGoc(String giaGoc) {
		GiaGoc = giaGoc;
	}

	public String getGiaKM() {
		return GiaKM;
	}

	public void setGiaKM(String giaKM) {
		GiaKM = giaKM;
	}

	public String getTenTG() {
		return TenTG;
	}

	public void setTenTG(String tenTG) {
		TenTG = tenTG;
	}

	public String getTenDoiTuong() {
		return TenDoiTuong;
	}

	public void setTenDoiTuong(String tenDoiTuong) {
		TenDoiTuong = tenDoiTuong;
	}

	public String getSoTrang() {
		return SoTrang;
	}

	public void setSoTrang(String soTrang) {
		SoTrang = soTrang;
	}

	public int getSoLuongCon() {
		return SoLuongCon;
	}

	public void setSoLuongCon(int soLuongCon) {
		SoLuongCon = soLuongCon;
	}

	public String getLinkAnh() {
		return LinkAnh;
	}

	public void setLinkAnh(String linkAnh) {
		LinkAnh = linkAnh;
	}

}
