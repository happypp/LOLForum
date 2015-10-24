package com.lolforum.service.impl;

import com.lolforum.dao.impl.BaseDaoImpl;
import com.lolforum.entities.Post;
import com.lolforum.service.PostService;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("postService")
public class PostServiceImpl extends BaseDaoImpl<Post> implements
		PostService {
	
	@Override
	public List<Post> getRecentPost(int sid, int num) {
		String hql = "from Post where sid = ? order by time desc";
		List<Post> list = (List<Post>) this.getCurrentSession()
				.createQuery(hql).setParameter(0, sid).setFirstResult(0).setMaxResults(num).list();
		return list;
	}

	@Override
	public List<Post> getRecentByUser(int sid, int num) {
		Session session = this.getCurrentSession();
		String sql = "select new Post(id,sid,uid,max(time)) from Post where sid=? group by uid order by max(time) desc";
		List<Post> list = session.createQuery(sql).setInteger(0, sid).setFirstResult(0).setMaxResults(num).list();
		return list;
	}
	
}
