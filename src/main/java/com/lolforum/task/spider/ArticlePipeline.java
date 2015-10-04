package com.lolforum.task.spider;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import com.lolforum.entities.Article;

public class ArticlePipeline implements Pipeline {
	
	@Override
	public void process(ResultItems resultItems, Task task) {
		Article article = (Article) resultItems.get("article");
		if (article != null) {
			System.out.println(article);
		}
	}
	
}
