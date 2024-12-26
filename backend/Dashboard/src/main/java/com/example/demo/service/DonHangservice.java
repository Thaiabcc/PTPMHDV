package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DonHang;
import com.example.demo.repository.DonHangrepository;

@Service
public class DonHangservice {
	@Autowired
	private DonHangrepository re;

	// Tạo đơn mới
	public DonHang createDonHang(DonHang donHang) {
		return re.save(donHang);
	}

	// Lấy tất cả đơn hàng
	public List<DonHang> getAllDonHangs() {
		return re.findAll();
	}

	// Lấy đơn hàng theo ID
	public Optional<DonHang> getDonHangById(Integer id) {
		return re.findById(id);
	}

	// Cập nhật đơn hàng
	public DonHang updateDonHang(Integer id, DonHang donHang) {
		donHang.setIdDonHang(id);
		return re.save(donHang);
	}

	// Xóa đơn hàng
	public void deleteDonHang(Integer id) {
		re.deleteById(id);
	}

}
