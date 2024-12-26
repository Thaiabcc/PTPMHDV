package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
