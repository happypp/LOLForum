package com.lolforum.service;

import java.util.List;

import com.lolforum.dao.BaseDao;
import com.lolforum.entities.Post;
import com.lolforum.entities.User;

public interface PostService extends BaseDao<Post> {
	public List<Post> getRecentPost(int sid, int num);
	
	public List<Post> getRecentByUser(int sid, int num);
}
