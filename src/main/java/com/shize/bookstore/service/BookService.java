package com.shize.bookstore.service;

import com.shize.bookstore.beans.Book;
import com.shize.bookstore.beans.Page;

public interface BookService {

	Page<Book> getPageBooksByPrice(String pageNo, String minPrice, String maxPrice);

	int saveBook(Book book);

	Book selectBookById(String bookId);

	int updateBookById(Book book);


}
