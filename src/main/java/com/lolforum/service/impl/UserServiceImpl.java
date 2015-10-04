package com.lolforum.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lolforum.dao.impl.BaseDaoImpl;
import com.lolforum.entities.User;
import com.lolforum.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseDaoImpl<User> implements UserService {

	@Override
	public User login(User user) {
		String hql = "from User where email=? and pwd=?";
		List<User> list = this.find(hql, new Object[]{user.getEmail(),user.getPwd()});
		return list.size() == 0 ? null : list.get(0);
	}
	
}
