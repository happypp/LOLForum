package com.lolforum.controller;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lolforum.constant.Constant;
import com.lolforum.entities.User;
import com.lolforum.service.UserService;
import com.lolforum.util.Util;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/login")
	public @ResponseBody
	Integer login(@RequestParam(value = "email") String email,
			@RequestParam(value = "pwd") String pwd, HttpSession session) {
		User user = new User();
		user.setEmail(email);
		user.setPwd(Util.encryptMD5(pwd));
		User result = userService.login(user);
		if (result != null) {
			user = new User();
			user.setId(result.getId());
			user.setName(result.getName());
			user.setType(result.getType());
			user.setScore(result.getScore());
			user.setAvatar(Util.realAvatarUrl(result.getAvatar()));
			session.setAttribute("user", user);
		}
		return result != null ? 1 : 0;
	}
	
	@RequestMapping("/logout")
	public @ResponseBody
	Integer logout(HttpSession session) {
		session.removeAttribute("user");
		return 1;
	}
	
	@RequestMapping("/regist")
	public String regist(User user) {
		user.setPwd(Util.encryptMD5(user.getPwd()));
		user.setScore(0);
		user.setType(0);
		user.setAvatar("default.png");
		userService.save(user);
		return "redirect:/index";
	}
	
	@ResponseBody
	@RequestMapping(value = "/registVal/rpwd", method = RequestMethod.POST)
	public void rpwd(User user,
			@RequestParam(value = "rpwd", required = false) String rpwd,
			HttpServletResponse resp) {
		String info = "";
		if (!user.getPwd().equals(rpwd.trim())) {
			info = "两次密码不相同";
		} else {
			info = "";
		}
		resp.setCharacterEncoding("UTF-8"); // 输出流使用utf-8编码
		try {
			resp.getWriter().print(info);// 输出返回值
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/registVal/pwd", method = RequestMethod.POST)
	public void pwd(User user, HttpServletResponse resp) {
		String info = "";
		if (user.getPwd().length() < 11 && user.getPwd().length() > 4) {
			info = "";
		} else {
			info = "密码长度不正确(5~10)";
		}
		resp.setCharacterEncoding("UTF-8"); // 输出流使用utf-8编码
		try {
			resp.getWriter().print(info);// 输出返回值
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/registVal/name", method = RequestMethod.POST)
	public void name(User user, HttpServletResponse resp) {
		String info = "";
		if (user.getName().length() < 16 && user.getName().length() > 0) {
			List<User> users = userService.find("from User where name = ?",
					new Object[] { user.getName() });
			if (users.isEmpty()) {
				info = "";
			} else {
				info = "该昵称已被注册！";
			}
		} else {
			info = "昵称长度不符合要求(1~15)";
		}
		resp.setCharacterEncoding("UTF-8"); // 输出流使用utf-8编码
		try {
			resp.getWriter().print(info);// 输出返回值
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/registVal/email", method = RequestMethod.POST)
	public void email(User user, HttpServletResponse resp) {
		String info = "";
		// 邮箱格式
		Pattern p = Pattern
				.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
		Matcher m = p.matcher(user.getEmail());
		if (m.matches()) {
			List<User> users = userService.find("from User where email = ?",
					new Object[] { user.getEmail() });
			if (users.isEmpty()) {
				info = "";
			} else {
				info = "邮箱已存在请重新填写";
			}
		} else {
			info = "邮箱格式不正确";
		}
		resp.setCharacterEncoding("UTF-8"); // 输出流使用utf-8编码
		try {
			resp.getWriter().print(info);// 输出返回值
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
