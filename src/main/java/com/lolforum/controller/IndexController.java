package com.lolforum.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lolforum.entities.Article;
import com.lolforum.entities.User;
import com.lolforum.service.ArticleService;
import com.lolforum.service.PostService;
import com.lolforum.service.SubjectService;
import com.lolforum.service.UserService;
import com.lolforum.util.Util;

@Controller
public class IndexController {
	@Resource
	private ArticleService articleService;
	
	@Resource
	private SubjectService subjectService;
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("index");
		System.out.println("\n-----A client has connected : " + Util.getClientIp(request));
		User loginUser = (User)request.getSession().getAttribute("user");
		System.out.println("已登录的用户:"+(loginUser == null ? null : loginUser.getName()));
		
		mv.addObject("lastArt", articleService.getRecent("time", 12));
		mv.addObject("hotArt", articleService.getRecent("visits", 14));
		mv.addObject("hotSub", subjectService.getRecent("visits", 7));
		return mv;
	}
	
	@RequestMapping("/registUI")
	public ModelAndView registUI(){
		ModelAndView mv = new ModelAndView("regist");
		return mv;
	}
	
}
