package com.lolforum.service;

import java.util.List;

import com.lolforum.dao.BaseDao;
import com.lolforum.entities.Post;
import com.lolforum.entities.Subject;
import com.lolforum.vo.SubjectVO;

public interface SubjectService extends BaseDao<Subject> {
	public List<Subject> getRecent(String order, int num);
}
