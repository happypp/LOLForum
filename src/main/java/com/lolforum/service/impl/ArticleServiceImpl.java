package com.lolforum.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.lolforum.dao.impl.BaseDaoImpl;
import com.lolforum.entities.Article;
import com.lolforum.entities.Post;
import com.lolforum.service.ArticleService;

@Service("articleService")
public class ArticleServiceImpl extends BaseDaoImpl<Article> implements
		ArticleService {

	@Override
	public List<Article> getRecent(String order, int num) {
		String hql = "from Article order by %s desc";
		hql = String.format(hql, order);
		List<Article> list = (List<Article>) this.getCurrentSession()
				.createQuery(hql).setFirstResult(0).setMaxResults(num).list();
		return list;
	}
	
}
