package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.DonHang;
import com.example.demo.service.DonHangservice;

@Controller
@RequestMapping("/order")
public class DonHangcontroller {

	@Autowired
	private DonHangservice service;

	// Trả về trang quản lý đơn hàng
	@GetMapping
	public String showOrderPage(Model model) {
		model.addAttribute("donHangs", service.getAllDonHangs()); // Danh sách đơn hàng
		model.addAttribute("donHang", new DonHang()); // Đối tượng mới cho form
		return "books/order";
	}

	// Tạo đơn hàng mới
	@PostMapping
	public ResponseEntity<DonHang> createDonHang(@RequestBody DonHang donHang) {
		DonHang createdDonHang = service.createDonHang(donHang);
		return ResponseEntity.status(201).body(createdDonHang); // Trả về 201 Created
	}

	// Lấy tất cả đơn hàng (API)
	@GetMapping("/api")
	public ResponseEntity<List<DonHang>> getAllDonHangs() {
		List<DonHang> donHangs = service.getAllDonHangs();
		return ResponseEntity.ok(donHangs);
	}

	// Lấy đơn hàng theo ID (API)
	@GetMapping("/api/{id}")
	public ResponseEntity<DonHang> getDonHangById(@PathVariable Integer id) {
		Optional<DonHang> donHang = service.getDonHangById(id);
		return donHang.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// Cập nhật đơn hàng (API)
	@PutMapping("/api/{id}")
	public ResponseEntity<DonHang> updateDonHang(@PathVariable Integer id, @RequestBody DonHang donHang) {
		DonHang updatedDonHang = service.updateDonHang(id, donHang);
		return ResponseEntity.ok(updatedDonHang);
	}

	// Xóa đơn hàng (API)
	@DeleteMapping("/api/{id}")
	public ResponseEntity<Void> deleteDonHang(@PathVariable Integer id) {
		service.deleteDonHang(id);
		return ResponseEntity.noContent().build(); // Trả về 204 No Content
	}
}