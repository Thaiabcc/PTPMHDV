//API USER
package com.example.demo.controller;

import com.example.demo.model.NguoiDung;
import com.example.demo.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/nguoidung")
public class NguoiDungController {
    @Autowired
    NguoiDungService nguoiDungService;

    @PostMapping("/register")
    public ResponseEntity<NguoiDung> register(@RequestBody NguoiDung nguoiDung) {
        NguoiDung newUser = nguoiDungService.register(nguoiDung);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody NguoiDung nguoiDung) {
        Optional<NguoiDung> authenticatedUser = nguoiDungService.login(nguoiDung.getUsername(), nguoiDung.getPassword());
        if (authenticatedUser.isPresent()) {
            return ResponseEntity.ok("Đăng nhập thành công!");
        }
        return ResponseEntity.status(401).body("Đăng nhập thất bại!");
    }
}