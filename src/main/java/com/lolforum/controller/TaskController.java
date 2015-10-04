package com.lolforum.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lolforum.entities.Article;
import com.lolforum.service.ArticleService;
import com.lolforum.task.spider.ArticleSpider;

@Controller
public class TaskController {
	@Resource
	private ArticleService articleService;
	
	@RequestMapping("/startSpider/{spiderId}/{num}")
	public void startSpider(@PathVariable int spiderId,@PathVariable int num) {
		switch (spiderId) {
		case 1:			//localhost:8080/LOLForum/startSpider/1/1
			ArticleSpider spider = ArticleSpider.startSpider(num);
			for(Article a : spider.getList()) {
				articleService.save(a);
			}
			break;
		default:
			break;
		}
	}
	
	@RequestMapping("/lucene/{taskId}")
	public void lucene(@PathVariable int taskId) {
		switch (taskId) {
		case 1:// test search
//			QueryResult qr = dao.highLight("aaaa", 0, 1000);
//			for (Object o : qr.getList()) {
//				Article a = (Article) o;
//				System.out.println(a.toString());
//			}
			break;
		case 2:
//			dao.save(new Article(11159, "aaaa", Date.valueOf("2015-01-01"), "11111"));
			break;
		default:
			break;
		}
		
	}
}
