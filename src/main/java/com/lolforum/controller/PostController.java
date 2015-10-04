package com.lolforum.controller;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lolforum.constant.Constant;
import com.lolforum.entities.Post;
import com.lolforum.entities.Subject;
import com.lolforum.entities.User;
import com.lolforum.service.PostService;
import com.lolforum.service.SubjectService;
import com.lolforum.service.UserService;
import com.lolforum.util.Page;
import com.lolforum.util.Util;
import com.lolforum.vo.PostVO;

@Controller
@RequestMapping("/discuss/subject")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SubjectService subjectService;
	
	@RequestMapping("/posts/{id}/{pageIndex}")
	public ModelAndView post(@PathVariable Integer id,
			@PathVariable Integer pageIndex) {
		ModelAndView mv = new ModelAndView("post");
		
		Subject subject = subjectService.find("from Subject where id = ?",
				new Object[] { id }).get(0);
		List<Post> posts = postService.find(
				"from Post where sid = ? order by time asc",
				new Object[] { subject.getId() }, pageIndex,
				Constant.LIST_ITEM_NUM);
		User author = userService.find("from User where id = ?",
				new Object[] { subject.getUid() }).get(0);
		
		List<PostVO> pvos = new ArrayList<PostVO>();
		// 回帖用户信息
		for (Post post : posts) {
			User user = userService.getById(post.getUid());
			if (user != null) {
				PostVO pvo = new PostVO(user, post);
				pvos.add(pvo);
			}
		}
		
		List<Post> list = postService.getRecentPost(id, 1);
		if (list != null && list.size() != 0) {
			User user = userService.getById(list.get(0).getUid());
			PostVO pov = new PostVO(user, list.get(0));
			mv.addObject("lastPost", pov);
		}
		// 取得回帖的总数量
		Long count = postService.count(
				"select count(*) from Post where sid = ?",
				new Object[] { subject.getId() });
		
		if (count.intValue() > 0) {
			mv.addObject("count", count);
		}
		
		// 主题信息
		author.setAvatar(Util.realAvatarUrl(author.getAvatar()));
		
		subjectService.executeHql(
				"update Subject set visits =visits+ 1 where id=?",
				new Object[] { id });
		
		mv.addObject("pvos", pvos);
		mv.addObject("createTime", Util.getSimpleTimeStr(subject.getTime()));
		mv.addObject("subject", subject);
		mv.addObject("author", author);
		mv.addObject(
				"page",
				new Page(pageIndex, postService.count(
						"select count(*) from Post where sid = ?",
						new Object[] { subject.getId() }).intValue()));
		mv.addObject("urlSubfix", "/discuss/subject/posts/" + id);
		
		return mv;
	}
	
	@RequestMapping("/savePost")
	public @ResponseBody
	Integer editor(@RequestBody Post post) throws Exception {
		String content = post.getContent().replace("|", "\"");
		System.out.println("Content: " + content);
		post.setContent(content);
		post.setAgree(0);
		Timestamp time = Timestamp.valueOf(Util.nowTime());
		post.setTime(time);
		postService.save(post);
		subjectService.executeHql("update Subject set lastest = ? where id=?",
				new Object[] { time, post.getSid() });
		return 1;
	}
	
}
