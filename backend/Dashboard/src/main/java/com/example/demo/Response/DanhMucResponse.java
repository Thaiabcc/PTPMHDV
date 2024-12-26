package com.example.demo.Response;

import com.example.demo.model.DanhMuc;

public class DanhMucResponse extends DanhMuc {

	private int soSach;
	private double doanhThu;
	private double loiNhuan;

	public DanhMucResponse() {
		super();
	}

	public DanhMucResponse(int soSach, double doanhThu, double loiNhuan) {
		super();
		this.soSach = soSach;
		this.doanhThu = doanhThu;
		this.loiNhuan = loiNhuan;
	}

	public DanhMucResponse(String maDM, String tenDM, int soSach, double doanhThu, double loiNhuan) {
		super(maDM, tenDM);
		this.soSach = soSach;
		this.doanhThu = doanhThu;
		this.loiNhuan = loiNhuan;
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

	public int getSoSach() {
		return soSach;
	}

	public void setSoSach(int soSach) {
		this.soSach = soSach;
	}

}
