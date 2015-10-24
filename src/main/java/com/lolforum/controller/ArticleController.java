package com.lolforum.controller;

import com.lolforum.constant.Constant;
import com.lolforum.entities.Article;
import com.lolforum.service.ArticleService;
import com.lolforum.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Resource
	private ArticleService articleService;
	
	@RequestMapping("/list/{pageIndex}")
	public ModelAndView articleList(@PathVariable Integer pageIndex){
		ModelAndView mv = new ModelAndView("articleList");
		List<Article> list = articleService.find("from Article order by time desc", null, pageIndex, Constant.LIST_ITEM_NUM);
		
		mv.addObject("list", list);
		mv.addObject("page", new Page(pageIndex, articleService.count("select count(*) from Article").intValue()));
		mv.addObject("urlSubfix", "/article/list");
		return mv;
	}
	
	@RequestMapping("/show/{id}")
	public ModelAndView article(@PathVariable Integer id){
		ModelAndView mv = new ModelAndView("article");
		Article a = articleService.get(id);
		
		articleService.executeHql(
				"update Article set visits =visits+ 1 where id=?",
				new Object[] { id });
		
		mv.addObject("article", a);
		return mv;
	}
	
}
