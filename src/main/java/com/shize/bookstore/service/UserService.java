package com.shize.bookstore.service;

import com.shize.bookstore.beans.User;

public interface UserService {

	public User login(User user);

	public User checkUsername(String username);

}
