package com.lolforum.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lolforum.dao.impl.BaseDaoImpl;
import com.lolforum.entities.Post;
import com.lolforum.entities.Subject;
import com.lolforum.entities.User;
import com.lolforum.service.SubjectService;
import com.lolforum.service.UserService;
import com.lolforum.vo.SubjectVO;
import com.lolforum.vo.SubjectVO.UserPost;

@Service("subjectService")
public class SubjectServiceImpl extends BaseDaoImpl<Subject> implements
		SubjectService {

	@Override
	public List<Subject> getRecent(String order, int num) {
		String hql = "from Subject order by %s desc";
		hql = String.format(hql, order);
		List<Subject> list = (List<Subject>) this.getCurrentSession()
				.createQuery(hql).setFirstResult(0).setMaxResults(num).list();
		return list;
	}
	
}
