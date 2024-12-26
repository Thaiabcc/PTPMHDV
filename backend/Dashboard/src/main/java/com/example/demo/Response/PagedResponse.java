package com.example.demo.Response;

import java.util.List;

import org.springframework.data.domain.Page;

public class PagedResponse<T> {
	private List<T> items;
	private int totalItems;
	private int totalPages;
	private int currentPage;

	public PagedResponse(Page<T> page) {
		this.items = page.getContent();
		this.totalItems = (int) page.getTotalElements();
		this.totalPages = page.getTotalPages();
		this.currentPage = page.getNumber();
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

}
