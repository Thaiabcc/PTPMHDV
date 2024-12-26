package com.example.demo.service;

import com.example.demo.model.NguoiDung;
import com.example.demo.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NguoiDungService {
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    public NguoiDung register(NguoiDung nguoiDung) {
        // Lưu mật khẩu thẳng mà không mã hóa
        nguoiDung.setPassword(nguoiDung.getPassword());
        return nguoiDungRepository.save(nguoiDung);
    }

    public Optional<NguoiDung> login(String username, String password) {
        Optional<NguoiDung> nguoiDung = nguoiDungRepository.findByUsername(username);
        if (nguoiDung.isPresent() && password.equals(nguoiDung.get().getPassword())) {
            return nguoiDung; // So sánh mật khẩu thẳng
        }
        return Optional.empty();
    }

    public List<NguoiDung> findAll() {
        return nguoiDungRepository.findAll();
    }

    public NguoiDung findById(Long id) {
        return nguoiDungRepository.findById(id).orElse(null);
    }

    public NguoiDung update(NguoiDung nguoiDung) {
        if (nguoiDungRepository.existsById(nguoiDung.getId())) {
            return nguoiDungRepository.save(nguoiDung);
        }
        return null; // Hoặc ném ra một exception nếu cần
    }

    public void deleteById(Long id) {
        nguoiDungRepository.deleteById(id);
    }

    public Optional<NguoiDung> findByUsername(String username) {
        return nguoiDungRepository.findByUsername(username);
    }
}