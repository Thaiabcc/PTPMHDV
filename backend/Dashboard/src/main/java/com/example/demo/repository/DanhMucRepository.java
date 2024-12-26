package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.DanhMuc;

public interface DanhMucRepository extends JpaRepository<DanhMuc, String> {

	DanhMuc findDanhMucByMaDM(String MaDM);
}