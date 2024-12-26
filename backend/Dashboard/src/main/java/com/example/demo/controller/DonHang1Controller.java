package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.DonHang;
import com.example.demo.service.DonHangservice;

@RestController
@RequestMapping("/api/order")
public class DonHang1Controller {

    @Autowired
    private DonHangservice service;

    // Lấy tất cả đơn hàng
    @GetMapping
    public ResponseEntity<List<DonHang>> getAllDonHangs() {
        List<DonHang> donHangs = service.getAllDonHangs();
        return ResponseEntity.ok(donHangs);
    }

    // Lấy đơn hàng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<DonHang> getDonHangById(@PathVariable Integer id) {
        Optional<DonHang> donHang = service.getDonHangById(id);
        return donHang.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Tạo đơn hàng mới
    @PostMapping
    public ResponseEntity<DonHang> createDonHang(@RequestBody DonHang donHang) {
        DonHang createdDonHang = service.createDonHang(donHang);
        return ResponseEntity.status(201).body(createdDonHang); // Trả về 201 Created
    }

    // Cập nhật đơn hàng
    @PutMapping("/{id}")
    public ResponseEntity<DonHang> updateDonHang(@PathVariable Integer id, @RequestBody DonHang donHang) {
        DonHang updatedDonHang = service.updateDonHang(id, donHang);
        return ResponseEntity.ok(updatedDonHang);
    }

    // Xóa đơn hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonHang(@PathVariable Integer id) {
        service.deleteDonHang(id);
        return ResponseEntity.noContent().build(); // Trả về 204 No Content
    }
}
