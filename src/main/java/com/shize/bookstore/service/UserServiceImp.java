package com.shize.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shize.bookstore.beans.User;
import com.shize.bookstore.mapper.UserMapper;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public User login(User user) {
		return userMapper.selectUserForLogin(user);
	}

	@Override
	public User checkUsername(String username) {
		
		return userMapper.selectUserByName(username);
	}

	@Override
	public void regist(User user) {
		userMapper.insertUser(user);
	}

}
