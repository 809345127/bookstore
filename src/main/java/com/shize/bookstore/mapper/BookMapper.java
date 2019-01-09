package com.shize.bookstore.mapper;

import java.util.List;
import java.util.Map;

import com.shize.bookstore.beans.Book;

public interface BookMapper {

	int selectCount(Map<String, Object> param);

	List<Book> selectBooksByPageno(Map<String, Object> param);

}
