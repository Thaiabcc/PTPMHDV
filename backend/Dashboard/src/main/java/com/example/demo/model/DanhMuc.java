package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DanhMuc")
public class DanhMuc {

	@Id
	@Column(name = "MaDM", columnDefinition = "varchar(25)")
	private String maDM;
	@Column(name = "TenDM", columnDefinition = "nvarchar(200)")
	private String TenDM;
	
	public DanhMuc() {
	}

	public DanhMuc(String MaDM, String tenDM) {
		this.maDM = MaDM;
		this.TenDM = tenDM;
	}

	public String getMaDM() {
		return maDM;
	}

	public void setMaDM(String MaDM) {
		this.maDM = MaDM;
	}

	public String getTenDM() {
		return TenDM;
	}

	public void setTenDM(String tenDM) {
		this.TenDM = tenDM;
	}

}