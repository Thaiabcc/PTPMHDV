package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Sach;
import com.example.demo.model.Sach1DTO;
import com.example.demo.service.SachService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/books")
public class Sach1Controller {

    @Autowired
    private SachService sachService;

    @GetMapping("/home")
    public String showDashboard(Model model) {
        List<Sach> topSellingBooks = sachService.getTopSellingBooks(10); // Top 10 sách bán chạy
        model.addAttribute("topSellingBooks", topSellingBooks);
        return "books/home";
    }
    @GetMapping("/thongke")
    public String thongke() {
    	return "books/thongke";
    }
    @GetMapping("/chartbest-seller")
    public String banchay() {
    	return "books/chartbest-seller";
    }
    @GetMapping("/charthigh-stock")
    public String tonkho() {
    	return "books/charthigh-stock";
    }

    @GetMapping("/search")
    public String search(@RequestParam String tenSach, Model model) {
        List<Sach> books = sachService.searchByTenSach(tenSach);
        model.addAttribute("books", books);
        return "books/search";
    }

    @GetMapping
    public String listBooks(Model model, 
                            @RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "10") int size) {
        Page<Sach> booksPage = sachService.getBooksByPage(page, size);
        model.addAttribute("books", booksPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", booksPage.getTotalPages());
        model.addAttribute("size", size);
        return "books/index3";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("sach1dto", new Sach1DTO());
        return "books/createbook";
    }

    @PostMapping("/create")
    public String createBook(@Valid @ModelAttribute("sach1dto") Sach1DTO sach1dto, BindingResult result) {
        if (result.hasErrors()) {
            return "books/createbook";
        }
        sachService.saveBook(sach1dto);
        return "redirect:/books";
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam String maSach) {
        return sachService.findById(maSach)
                          .map(sach -> {
                              Sach1DTO sach1dto = convertToDTO(sach);
                              model.addAttribute("sach1dto", sach1dto);
                              return "books/edit";
                          }).orElse("redirect:/books");
    }

    @PostMapping("/edit")
    public String editBook(@Valid @ModelAttribute("sach1dto") Sach1DTO sach1dto, BindingResult result) {
        if (result.hasErrors()) {
            return "books/edit";
        }
        sachService.updateBook(sach1dto);
        return "redirect:/books";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam String maSach) {
        sachService.deleteBook(maSach);
        return "redirect:/books";
    }

    @GetMapping("/high-stock")
    public String showHighStockBooks(Model model, 
                                     @RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "30") int size) {
        Page<Sach> booksPage = sachService.getBooksWithSoLuongConGreaterThan700(page, size);
        model.addAttribute("books", booksPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", booksPage.getTotalPages());
        return "books/high-stock";
    }

    @GetMapping("/best-seller")
    public String showBestSellerBooks(Model model, 
                                      @RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "25") int size) {
        Page<Sach> booksPage = sachService.getBooksWithSoLuongConLessThan300(page, size);
        model.addAttribute("books", booksPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", booksPage.getTotalPages());
        return "books/best-seller";
    }

    @GetMapping("/flash-sales")
    public String showFlashSalesBooks(Model model, 
                                      @RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "25") int size) {
        Page<Sach> booksPage = sachService.getBooksWithSoLuongConGreaterThan700(page, size);
        model.addAttribute("books", booksPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", booksPage.getTotalPages());
        return "books/flash-sales";
    }

    private Sach1DTO convertToDTO(Sach sach) {
        Sach1DTO sach1dto = new Sach1DTO();
        sach1dto.setMaSach(sach.getMaSach());
        sach1dto.setTenSach(sach.getTenSach());
        sach1dto.setGiaGoc(sach.getGiaGoc());
        sach1dto.setGiaKM(sach.getGiaKM());
        sach1dto.setTenTG(sach.getTenTG());
        sach1dto.setTenDoiTuong(sach.getTenDoiTuong());
        sach1dto.setSoTrang(sach.getSoTrang());
        sach1dto.setSoLuongCon(sach.getSoLuongCon());
        sach1dto.setLinkAnh(sach.getLinkAnh());
        sach1dto.setMaDM(sach.getMaDM());
        return sach1dto;
    }
    
    @GetMapping("/profit1/{maSach}")
	public String profitOfBook() {
		return "books/LoiNhuanBook";
	}
}
