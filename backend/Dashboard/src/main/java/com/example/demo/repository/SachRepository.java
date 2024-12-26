package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Sach;

public interface SachRepository extends JpaRepository<Sach, String> {

	List<Sach> findByTenSachContainingIgnoreCase(String tenSach);

	Page<Sach> findBySoLuongConGreaterThan(int quantity, Pageable pageable);

	Page<Sach> findBySoLuongConLessThan(int quantity, Pageable pageable);

	 List<Sach> findTop10ByOrderBySoLuongConAsc();
	 
	 List<Sach> findAll(Sort sort);
	 
	 //Bieu do
	 Sach findByMaSach(String maSach);
	 List<Sach> findByMaDM(String MaDM); 
		 
	}
