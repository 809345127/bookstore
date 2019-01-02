package com.shize.bookstore.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

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
	public String doLogin(User user, HttpSession session,Map<String, Object> map) {
		user = userService.login(user);
		
		if (user == null) {
			map.put("msg", "用户名或密码错误");
			return "user/login";
		} else {
			session.setAttribute("user", user);
			return "user/login_success";
		}
		
	}
	
	
	@RequestMapping("/toRegist")
	public String toRegist() {
		return "user/regist";
	}
	
	@RequestMapping("/doRegist")
	public String doRegist(User user,String code,HttpSession session,Map<String, Object> map) {
		String kaptcha = (String) session.getAttribute("KAPTCHA_SESSION_KEY");
		session.removeAttribute("KAPTCHA_SESSION_KEY");
		
		
		if (code != null && code.equals(kaptcha)) {
			/*// 调用userService的注册的方法
			boolean regist = userService.regist(user);
			if (regist) {
				// 用户名已存在，设置一个错误消息并放到request域中
				request.setAttribute("msg", "用户名已存在！");
				// 转发到注册页面
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
			} else {
				// 用户名可用，将用户保存到数据库中
				userService.saveUser(user);
				// 重定向到注册成功页面
				response.sendRedirect(request.getContextPath() + "/pages/user/regist_success.jsp");
			}*/
			return null;
		}else {
			map.put("msg", "验证码错误请重新输入");
			return "user/regist";
		}
		
		
		
	}
	
	@RequestMapping("/checkUsername")
	public String checkUsername(String username) {
		return "user/regist";
	}
}
