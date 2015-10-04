package com.lolforum.task.spider;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.helper.StringUtil;
import org.springframework.util.Log4jConfigurer;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import com.lolforum.entities.Article;
import com.lolforum.util.Util;

public class ArticleSpider implements PageProcessor {
	private String urlRaw;
	private String urlHead = "http://wangyou.pcgames.com.cn";
	private LinkedList<String> urls;
	private int pageListNums = 1;
	
	private List<Article> list = new ArrayList<Article>();
	
	public ArticleSpider(int pageNum) {
		urls = new LinkedList<String>();
		pageListNums = pageNum;
		urlRaw = "http://wangyou.pcgames.com.cn/zhuanti/lol/news/%s";
		for (int i = 1; i <= pageListNums; i++) {
			if (1 == i)
				urls.add(String.format(urlRaw, ""));
			else
				urls.add(String.format(urlRaw, "index_" + (i - 1))+".html");
		}
	}
	
	public static Logger logger = Logger.getLogger(ArticleSpider.class);
	static {
		try {
			Log4jConfigurer
					.initLogging("classpath:com/tfemg/config/log4j.properties");
		} catch (FileNotFoundException ex) {
			System.err.println("Cannot Initialize log4j");
		}
	}
	
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1500);
	
	@Override
	public void process(Page page) {
		System.out.println(page.getUrl());
		if (urls.contains(page.getUrl().toString())) {
			System.out.println(page.getUrl() + " addRequests");
			page.addTargetRequests(page.getHtml()
					.xpath("//div[@class='dTitle']/b").links().all());
			return;
		} else {
			System.out.println(page.getUrl() + " getContent");
			String title = null;
			title = page.getHtml().xpath("//div[@class='artCon']/h1/text()")
					.toString();
			if (title == null) {
				System.out.println("链接为空！");
				return;
			}
			System.out.println(title);
			String time = page.getHtml()
					.xpath("//p[@class='info']/span[1]/text()").toString();
			String source = null;
			if(!StringUtil.isBlank(time)) {
				time = time.substring(0,10);
				source = page.getHtml()
						.xpath("//p[@class='info']/span[2]").toString();
			} else {
				time = page.getHtml()
						.xpath("//p[@class='info']/text()").toString().substring(1,10);
				source = "其他";
			}
			
			String content = null;
			content = page.getHtml().xpath("//div[@id='artArea']/outerHtml()").toString();
			list.add(new Article(title, content, source,Date.valueOf(time)));
//			page.putField("article", new Article(title, content, source,Date.valueOf(time)));
		}
	}
	
	public static ArticleSpider startSpider(int pageNum) {
		ArticleSpider spider = new ArticleSpider(pageNum);
		System.out.println("[AccidentSpider]Spider is start...");
		logger.info("[AccidentSpider]Spider is start...");
		Spider.create(spider).addPipeline(new ArticlePipeline())
				.startUrls(spider.getUrls()).thread(1).run();
		System.out.println("[AccidentSpider]done");
		logger.info("[AccidentSpider]done");
		return spider;
	}
	
	@Override
	public Site getSite() {
		return site;
	}
	
	public LinkedList<String> getUrls() {
		return urls;
	}
	
	public void setUrls(LinkedList<String> urls) {
		this.urls = urls;
	}
	
	public String addUrlHead(String url) {
		return urlHead + url;
	}
	
	public List<String> addUrlHeads(List<String> l) {
		for (int i = l.size() - 1; i >= 0; i--) {
			l.set(i, urlHead + l.get(i));
		}
		return l;
	}

	public List<Article> getList() {
		return list;
	}

	public void setList(List<Article> list) {
		this.list = list;
	}
	
	
	
}
