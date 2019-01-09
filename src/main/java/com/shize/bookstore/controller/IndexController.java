package com.shize.bookstore.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shize.bookstore.beans.Book;
import com.shize.bookstore.beans.Page;
import com.shize.bookstore.service.BookService;

@Controller
public class IndexController {

	@Autowired
	private BookService bookService;

	@RequestMapping("/index")
	public String index(@RequestParam(value = "pageNo", required = false, defaultValue = "null") String pageNo,
			@RequestParam(value = "minPrice", required = false, defaultValue = "null") String minPrice,
			@RequestParam(value = "maxPrice", required = false, defaultValue = "null") String maxPrice,
			Map<String, Object> map) {
		// 调用bookService中获取带分页及价格范围的图书的方法
		Page<Book> pageBooks = bookService.getPageBooksByPrice(pageNo, minPrice, maxPrice);
		map.put("page", pageBooks);
		return "client/books";
	}
}
