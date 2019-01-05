package com.shize.bookstore.mapper;

import com.shize.bookstore.beans.User;

public interface UserMapper {

	public User selectUserForLogin(User user);

	public User selectUserByName(String username);

	public void insertUser(User user);

}
