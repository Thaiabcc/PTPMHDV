package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("category")
public class DanhMucController {

	@GetMapping("listofcate")
	public String displayListOfCate() {
		return "category/ThongKeDanhMuc";
	}
	
	@GetMapping("profit/{maDM}")
	public String displayProfitOfCate() {
		return "category/LoiNhuanDanhMuc";
	}
}
