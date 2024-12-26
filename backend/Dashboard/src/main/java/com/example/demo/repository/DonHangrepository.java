package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DonHang;

@Repository
public interface DonHangrepository extends JpaRepository<DonHang, Integer> {

}
