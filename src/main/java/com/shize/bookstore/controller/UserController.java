package com.shize.bookstore.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shize.bookstore.beans.User;
import com.shize.bookstore.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/toLogin")
	public String toLogin() {
		return "user/login";
	}
	
	@RequestMapping("/doLogin")
	public String doLogin(User user, Map<String, Object> map) {
		user = userService.login(user);
		System.out.println(user);
		return "user/login";
	}
}
