package com.lolforum.service;

import com.lolforum.dao.BaseDao;
import com.lolforum.entities.User;

public interface UserService extends BaseDao<User> {
	public User login(User user);
}
