package com.example.demo.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Response.DanhMucResponse;
import com.example.demo.Response.SachResponse;
import com.example.demo.model.DanhMuc;
import com.example.demo.model.Sach;
import com.example.demo.repository.DanhMucRepository;

@Service
public class DanhMucService {

	@Autowired
	private DanhMucRepository danhMucRepository;
	@Autowired
	private SachService sachSrv;

	// Danh sách danh mục
	public List<DanhMuc> danhSachDanhMuc() {
		return danhMucRepository.findAll();
	}

	
	  // Tính doanh thu các sách trong 1 danh mục 
		public DanhMucResponse profitOfCategory(String maDM) {
			double doanhThu = 0;
			double loiNhuan = 0;
			DanhMuc danhMuc = danhMucRepository.findDanhMucByMaDM(maDM);
			DanhMucResponse danhMucRP = new DanhMucResponse();
			SachResponse SachRP = new SachResponse();
			List<Sach> listOfBook = sachSrv.findBookByMaDM(maDM);
			for (Sach book : listOfBook) {
				SachRP = sachSrv.profitOfBook(book.getMaSach());
				doanhThu += SachRP.getDoanhThu();
				loiNhuan += SachRP.getLoiNhuan();
			}
			danhMucRP.setMaDM(danhMuc.getMaDM());
			danhMucRP.setTenDM(danhMuc.getTenDM());
			danhMucRP.setSoSach(listOfBook.size());
			danhMucRP.setDoanhThu(doanhThu);
			danhMucRP.setLoiNhuan(loiNhuan);
			return danhMucRP;
		}
	 
}

