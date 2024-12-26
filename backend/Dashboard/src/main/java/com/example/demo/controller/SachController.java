//API BOOK
package com.example.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Response.PagedResponse;
import com.example.demo.Response.SachResponse;
import com.example.demo.model.Sach;
import com.example.demo.model.Sach1DTO;
import com.example.demo.service.SachService;

@RestController
@RequestMapping(path = "/api", produces = "application/json")
public class SachController {

    private final SachService sachService;

    @Autowired
    public SachController(SachService sachService) {
        this.sachService = sachService;
    }

    // Lấy danh sách sách bán chạy
    @GetMapping("/bestsellers")
    public ResponseEntity<PagedResponse<Sach>> getBestsellers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Page<Sach> sach = sachService.getSachBanChays(page, size);
        return ResponseEntity.ok(new PagedResponse<>(sach));
    }

    // Lấy sách tồn kho nhiều
    @GetMapping("/high-stock")
    public ResponseEntity<PagedResponse<Sach>> getHighStocks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Page<Sach> sach = sachService.getSachTonKhos(page, size);
        return ResponseEntity.ok(new PagedResponse<>(sach));
    }

    // Thống kê lợi nhuận sách
    @GetMapping("/profit/{maSach}")
    public ResponseEntity<SachResponse> thongKeLoiNhuanSach(@PathVariable String maSach) {
        return ResponseEntity.ok(sachService.profitOfBook(maSach));
    }

    @GetMapping("/profit1/{maSach}")
	public SachResponse thongKeLoiNhuanSach1(@PathVariable(name = "maSach") String maSach) {
		return sachService.profitOfBook(maSach);
	}

    // Thêm sách mới
    @PostMapping("/books")
    public ResponseEntity<Sach> createBook(@RequestBody Sach1DTO sach1dto) {
        Sach sach = sachService.saveBook(sach1dto);
        return ResponseEntity.ok(sach);
    }

    // Cập nhật sách
    @PutMapping("/books/{maSach}")
    public ResponseEntity<?> updateBook(@PathVariable String maSach, @RequestBody Sach1DTO sach1dto) {
        if (maSach == null || maSach.isEmpty()) {
            return ResponseEntity.badRequest().body("MaSach cannot be null or empty");
        }

        if (!maSach.equals(sach1dto.getMaSach())) {
            return ResponseEntity.badRequest().body("Path variable MaSach must match the MaSach in the request body");
        }

        try {
            sachService.updateBook(sach1dto);
            return ResponseEntity.ok("Book updated successfully.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found with MaSach: " + maSach);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating book: " + e.getMessage());
        }
    }

    // Xóa sách
    @DeleteMapping("/books/{maSach}")
    public ResponseEntity<Void> deleteBook(@PathVariable String maSach) {
        sachService.deleteBook(maSach);
        return ResponseEntity.noContent().build();
    }

    // Tìm kiếm sách theo tên
    @GetMapping("/search")
    public ResponseEntity<List<Sach>> searchBooks(@RequestParam String tenSach) {
        List<Sach> books = sachService.searchByTenSach(tenSach);
        return ResponseEntity.ok(books);
    }
}
