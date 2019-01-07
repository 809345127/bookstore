package com.shize.bookstore.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shize.bookstore.beans.Book;
import com.shize.bookstore.beans.Page;
import com.shize.bookstore.service.BookService;

@RequestMapping("/manager")
@Controller
public class BookManagerController {

	private BookService bookService;

	@RequestMapping("/manager")
	public String index() {
		return "manager/manager";
	}

	@RequestMapping("/getBooks")
	public String getBooks(@RequestParam(value="pageNo",required = false) int pageNo,
			@RequestParam(value="minPrice",required = false) int minPrice,
			@RequestParam(value="maxPrice",required = false) int maxPrice, Map<String, Object> map) {
		// 调用bookService中获取带分页及价格范围的图书的方法
		/*Page<Book> pageBooks = bookService.getPageBooksByPrice(pageNo, minPrice, maxPrice);
		map.put("page", pageBooks);*/
		return "manager/book_manager";
	}
	
	
}