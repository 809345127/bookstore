package com.shize.bookstore.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shize.bookstore.beans.AjaxResult;
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
	public String doLogin(User user, HttpSession session, Map<String, Object> map) {
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
	public String doRegist(User user, String code, HttpSession session, Map<String, Object> map) {
		String kaptcha = (String) session.getAttribute("KAPTCHA_SESSION_KEY");
		session.removeAttribute("KAPTCHA_SESSION_KEY");

		AjaxResult ajaxResult = new AjaxResult();

		if (code != null && code.equals(kaptcha)) {
			// 验证用户名是否可用
			try {
				User user1 = userService.checkUsername(user.getUsername());

				if (user1 != null) {
					map.put("msg", "用户名已存在");
					return "user/regist";
				} else {
					// 用户名可用，注册
					userService.regist(user);
					return "user/regist_success";
				}
			} catch (Exception e) {
				e.printStackTrace();
				map.put("msg", "注册失败");
				return "user/regist";
			}

		} else {
			map.put("msg", "验证码错误请重新输入");
			return "user/regist";
		}

	}

	@ResponseBody
	@RequestMapping("/checkUsername")
	public AjaxResult checkUsername(String username, Map<String, Object> map) {
		User user = userService.checkUsername(username);
		AjaxResult ajaxResult = new AjaxResult();

		if (user != null) {
			ajaxResult.setMessage("用户名已存在");
		} else {
			ajaxResult.setMessage("<font style='color:green'>用户名可用！</font>");
		}
		return ajaxResult;
	}
}
