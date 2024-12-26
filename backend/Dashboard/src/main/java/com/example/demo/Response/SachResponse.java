package com.example.demo.Response;

import com.example.demo.model.Sach;

public class SachResponse extends Sach {

	private double doanhThu;
	private double loiNhuan;
	private double phanTramLN;

	public SachResponse() {
		super();
	}

	public SachResponse(double doanhThu, double loiNhuan, double phanTramLN) {
		super();
		this.doanhThu = doanhThu;
		this.loiNhuan = loiNhuan;
		this.phanTramLN = phanTramLN;
	}

	public SachResponse(String maSach, String tenSach, String linkAnh, String giaGoc, String giaKM, String tenTG,
			String tenDoiTuong, String soTrang, int soLuongCon, String maDM, double doanhThu, double loiNhuan,
			double phanTramLN) {
		super(maSach, tenSach, linkAnh, giaGoc, giaKM, tenTG, tenDoiTuong, soTrang, soLuongCon, maDM);
		this.doanhThu = doanhThu;
		this.loiNhuan = loiNhuan;
		this.phanTramLN = phanTramLN;
	}

	public double getDoanhThu() {
		return doanhThu;
	}

	public void setDoanhThu(double doanhThu) {
		this.doanhThu = doanhThu;
	}

	public double getLoiNhuan() {
		return loiNhuan;
	}

	public void setLoiNhuan(double loiNhuan) {
		this.loiNhuan = loiNhuan;
	}

	public double getPhanTramLN() {
		return phanTramLN;
	}

	public void setPhanTramLN(double phanTramLN) {
		this.phanTramLN = phanTramLN;
	}

}
