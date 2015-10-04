package com.lolforum.service;

import java.util.List;

import com.lolforum.dao.BaseDao;
import com.lolforum.entities.Article;
import com.lolforum.entities.Post;
import com.lolforum.entities.User;

public interface ArticleService extends BaseDao<Article> {
	public List<Article> getRecent(String order, int num);
}
