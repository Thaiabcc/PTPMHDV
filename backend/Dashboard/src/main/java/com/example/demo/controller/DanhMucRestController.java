package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Response.DanhMucResponse;
import com.example.demo.model.DanhMuc;

import com.example.demo.service.DanhMucService;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
public class DanhMucRestController {

	@Autowired
	private DanhMucService danhMucService;

	@GetMapping("/listOfCate")
	public List<DanhMuc> displayListOfCate() {
		return danhMucService.danhSachDanhMuc();
	}

	@GetMapping("/profitOfCate/{maDM}")
	public DanhMucResponse thongKeLoiNhuanSachTrongDM(@PathVariable(name = "maDM") String maDM) {
		return danhMucService.profitOfCategory(maDM);
	}

}
