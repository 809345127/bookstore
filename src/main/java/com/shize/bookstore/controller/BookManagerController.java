package com.shize.bookstore.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shize.bookstore.beans.AjaxResult;
import com.shize.bookstore.beans.Book;
import com.shize.bookstore.beans.Page;
import com.shize.bookstore.service.BookService;

@RequestMapping("/manager")
@Controller
public class BookManagerController {

	@Autowired
	private BookService bookService;

	@RequestMapping("/manager")
	public String index() {
		return "manager/manager";
	}

	@RequestMapping("/getBooks")
	public String getBooks(@RequestParam(value="pageNo",required = false) String pageNo, Map<String, Object> map) {
//		 调用bookService中获取带分页及价格范围的图书的方法
		Page<Book> pageBooks = bookService.getPageBooksByPrice(pageNo, null, null);
		map.put("page", pageBooks);
		
		return "manager/book_manager";
	}
	
	@RequestMapping("/toAdd")
	public String toAdd(Map<String, Object> map) {
		return "manager/book_edit";
	}
	
	@ResponseBody
	@RequestMapping("/doAdd")
	public AjaxResult doAdd(Book book,Map<String, Object> map) {
		AjaxResult ajaxResult = new AjaxResult();
		int i = bookService.saveBook(book);
		if (i==1) {
			ajaxResult.setSuccess(true);
		}else {
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
	
	@RequestMapping("/toUpdate")
	public String toUpdate(String bookId,Map<String, Object> map) {
		
		Book book = bookService.selectBookById(bookId);
		map.put("book", book);
		return "manager/book_edit2";
	}
	
	@ResponseBody
	@RequestMapping("/doUpdate")
	public AjaxResult doUpdate(Book book,Map<String, Object> map) {
		
		AjaxResult ajaxResult = new AjaxResult();
		int i = bookService.updateBookById(book);
		if (i==1) {
			ajaxResult.setSuccess(true);
		}else {
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
	
	
	
}
