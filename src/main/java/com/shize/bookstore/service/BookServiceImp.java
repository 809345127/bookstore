package com.shize.bookstore.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shize.bookstore.beans.Book;
import com.shize.bookstore.beans.Page;
import com.shize.bookstore.mapper.BookMapper;

@Service
public class BookServiceImp implements BookService {
	
	@Autowired
	private BookMapper bookMapper;

	@Override
	public Page<Book> getPageBooksByPrice(String pageNo, String minPrice, String maxPrice) {
		// 创建Page对象
		Page<Book> page = new Page<>();
		// 设置一个默认的页码
		int defaultPageNo = 1;
		// 设置一个默认的价格范围
		double defaultMinPrice = 0;
		double defaultMaxPrice = Double.MAX_VALUE;
		try {
			// 将传入的页码进行转换
			defaultPageNo = Integer.parseInt(pageNo);
		} catch (Exception e) {
		}
		try {
			defaultMinPrice = Double.parseDouble(minPrice);
		} catch (Exception e) {
		}
		try {
			defaultMaxPrice = Double.parseDouble(maxPrice);
		} catch (Exception e) {
		}
		// 将页码设置到page对象中
		page.setPageNo(defaultPageNo);
		
		
		Map<String, Object> param = new HashMap<>();
		param.put("defaultPageNo", defaultPageNo);
		param.put("defaultMinPrice", defaultMinPrice);
		param.put("defaultMaxPrice", defaultMaxPrice);
		param.put("startIndex", page.getStartIndex());
		param.put("pagesize", Page.PAGE_SIZE);
		
		
		int totalCount = bookMapper.selectCount(param);
		page.setTotalRecord(totalCount);
		
		List<Book> books = bookMapper.selectBooksByPageno(param);
		page.setList(books);
		
		
		return page;
	}

	@Override
	public int saveBook(Book book) {
		return bookMapper.insertBook(book);
	}

	@Override
	public Book selectBookById(String bookId) {
		return bookMapper.selectBookById(bookId);
	}

	@Override
	public int updateBookById(Book book) {
		return bookMapper.updateBookById(book);
	}

}
