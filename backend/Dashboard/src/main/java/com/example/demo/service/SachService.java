
package com.example.demo.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.Response.SachResponse;
import com.example.demo.model.Sach;
import com.example.demo.model.Sach1DTO;
import com.example.demo.repository.SachRepository;

@Service
public class SachService {

	@Autowired
	private SachRepository sachRepository;

	public List<Sach> searchByTenSach(String tenSach) {
		return sachRepository.findByTenSachContainingIgnoreCase(tenSach);
	}

	// Create and save
	public Sach saveBook(Sach1DTO sach1dto) {
	    if (sach1dto.getMaSach() == null || sach1dto.getMaSach().isEmpty()) {
	        throw new IllegalArgumentException("MaSach cannot be null or empty");
	    }

	    Sach sach1 = new Sach();
	    sach1.setMaSach(sach1dto.getMaSach());
	    sach1.setTenSach(sach1dto.getTenSach());
	    sach1.setGiaGoc(sach1dto.getGiaGoc());
	    sach1.setGiaKM(sach1dto.getGiaKM());
	    sach1.setTenTG(sach1dto.getTenTG());
	    sach1.setTenDoiTuong(sach1dto.getTenDoiTuong());
	    sach1.setSoTrang(sach1dto.getSoTrang());
	    sach1.setSoLuongCon(sach1dto.getSoLuongCon());
	    sach1.setLinkAnh(sach1dto.getLinkAnh());

	    return sachRepository.save(sach1);
	}

	// Phân trang
	public Page<Sach> getBooksByPage(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return sachRepository.findAll(pageable);
	}

	/*
	 * public List<Sach1> searchBooksByName(String keyword) { return
	 * sach1repository.findByTenSachContainingIgnoreCase(keyword); // Repository
	 * thực hiện tìm kiếm }
	 */

	// Update sach
	public Optional<Sach> findById(String MaSach) {
		return sachRepository.findById(MaSach);
	}

	public void updateBook(Sach1DTO sach1dto) {
		Optional<Sach> sachOpt = sachRepository.findById(sach1dto.getMaSach());
		if (sachOpt.isPresent()) {
			Sach sach1 = sachOpt.get();
			sach1.setTenSach(sach1dto.getTenSach());
			sach1.setGiaGoc(sach1dto.getGiaGoc());
			sach1.setGiaKM(sach1dto.getGiaKM());
			sach1.setTenTG(sach1dto.getTenTG());
			sach1.setTenDoiTuong(sach1dto.getTenDoiTuong());
			sach1.setSoTrang(sach1dto.getSoTrang());
			sach1.setSoLuongCon(sach1dto.getSoLuongCon());
			sach1.setLinkAnh(sach1dto.getLinkAnh());
			sach1.setMaDM(sach1dto.getMaDM());
			sachRepository.save(sach1);
		}
	}

	// Delete sach
	public void deleteBook(String MaSach) {
		sachRepository.deleteById(MaSach);
	}

	// Thong ke sach ton kho

	public Page<Sach> getBooksWithSoLuongConGreaterThan700(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size); // -1 vì Spring bắt đầu từ trang 0
		return sachRepository.findBySoLuongConGreaterThan(700, pageable);
	}

	// Thong ke sach ban chay
	public Page<Sach> getBooksWithSoLuongConLessThan300(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size); //
		return sachRepository.findBySoLuongConLessThan(300, pageable);
	}

	// Xep hang
	public List<Sach> getTopSellingBooks(int limit) {
		List<Sach> allBooks = sachRepository.findAll();
		System.out.println("Total books found: " + allBooks.size()); // Debugging

		return allBooks.stream().sorted(Comparator.comparingInt(book -> {
			try {
				return book.getSoLuongCon();
			} catch (NumberFormatException e) {
				return Integer.MAX_VALUE; // Nếu không thể chuyển đổi, cho nó vào cuối danh sách
			}
		})).limit(limit).collect(Collectors.toList());
	}

	// phan trang api sach ban chay
	public Page<Sach> getSachBanChays(int page, int size) {
		return sachRepository.findBySoLuongConLessThan(300, PageRequest.of(page, size));
	}

	// phan trang api sach ton kho
	public Page<Sach> getSachTonKhos(int page, int size) {
		return sachRepository.findBySoLuongConGreaterThan(700, PageRequest.of(page, size));
	}

	// Thống kê lợi nhuận của 1 sách
	public SachResponse profitOfBook(String maSach) {
		Random rand = new Random();
		int PTCP = rand.nextInt(21) + 20;
		Optional<Sach> book = sachRepository.findById(maSach);
		String strGiaGoc1 = book.get().getGiaGoc().substring(0, book.get().getGiaGoc().length() - 1);
		String strGiaGoc2 = strGiaGoc1.replace(",", "");
		float giaGoc = Float.parseFloat(strGiaGoc2);
		String strGiaKM1 = book.get().getGiaKM().substring(0, book.get().getGiaKM().length() - 1);
		String strGiaKM2 = strGiaKM1.replace(",", "");
		double giaKM = Float.parseFloat(strGiaKM2);
		double von = ((giaGoc / 100) * PTCP) * (1000 - book.get().getSoLuongCon());
		double doanhThu = giaKM * (1000 - book.get().getSoLuongCon());
		double loiNhuan = doanhThu - von;
		double phanTramLN = (loiNhuan / doanhThu) * 100;
		SachResponse bookRP = new SachResponse(book.get().getMaSach(), book.get().getTenSach(), book.get().getLinkAnh(),
				book.get().getGiaGoc(), book.get().getGiaKM(), book.get().getTenTG(), book.get().getTenDoiTuong(),
				book.get().getSoTrang(), book.get().getSoLuongCon(), book.get().getMaDM(), doanhThu, loiNhuan,
				phanTramLN);
		return bookRP;
	}
	
	
	  // Lấy danh sách sách 
		public List<Sach> findBookByMaDM(String maDM) {
			List<Sach> book = sachRepository.findByMaDM(maDM);
			return book;
		}
	 

}
