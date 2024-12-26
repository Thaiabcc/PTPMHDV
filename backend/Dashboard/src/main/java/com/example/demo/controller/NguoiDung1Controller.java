package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.NguoiDung;
import com.example.demo.service.NguoiDungService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/nguoidung")
public class NguoiDung1Controller {
    @Autowired
    private NguoiDungService nguoiDungService;

    @GetMapping
    public String showUserManagement(Model model) {
        List<NguoiDung> users = nguoiDungService.findAll();
        model.addAttribute("users", users);
        return "books/user-management"; 
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("nguoiDung", new NguoiDung());
        return "books/add-user"; 
    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute NguoiDung nguoiDung, BindingResult result) {
        if (result.hasErrors()) {
            return "books/add-user"; // Trả về trang thêm người dùng nếu có lỗi
        }
        nguoiDungService.register(nguoiDung);
        return "redirect:/nguoidung"; 
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable("id") Long id, Model model) {
        NguoiDung nguoiDung = nguoiDungService.findById(id);
        if (nguoiDung == null) {
            return "redirect:/nguoidung"; // Hoặc trả về trang lỗi
        }
        model.addAttribute("nguoiDung", nguoiDung);
        return "books/editUser"; 
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@Valid @ModelAttribute NguoiDung nguoiDung, BindingResult result) {
        if (result.hasErrors()) {
            return "books/editUser"; // Trả về trang chỉnh sửa nếu có lỗi
        }
        nguoiDungService.update(nguoiDung);
        return "redirect:/nguoidung"; 
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        nguoiDungService.deleteById(id);
        return "redirect:/nguoidung"; 
    }
}