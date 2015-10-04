package com.lolforum.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.lolforum.vo.SubjectVO;
import com.lolforum.vo.SubjectVO.UserPost;

@Controller
@RequestMapping("/discuss")
public class SubjectController {
	@Resource
	private UserService userService;
	@Resource
	private PostService postService;
	@Resource
	private SubjectService subjectService;
	
	@RequestMapping("/subjects/{pageIndex}")
	public ModelAndView subjects(@PathVariable Integer pageIndex) {
		ModelAndView mv = new ModelAndView("subject");
		
		List<Subject> subjects = subjectService.find("from Subject order by lastest desc", null, pageIndex, Constant.LIST_ITEM_NUM);
		List<SubjectVO> svos = new ArrayList<SubjectVO>();
		for(Subject s : subjects) {
			svos.add(getSVO(s));
		}
		
		mv.addObject("svos", svos);
		mv.addObject("page", new Page(pageIndex, subjectService.count("select count(*) from Subject").intValue()));
		mv.addObject("urlSubfix", "/discuss/subjects");
		
		return mv;
	}
	
	@RequestMapping("/saveSubject")
	public @ResponseBody
	Integer saveSubject(@RequestBody Subject subject) {
		Timestamp time = Timestamp.valueOf(Util.nowTime());
		subject.setTime(time);
		subject.setLastest(time);
		subject.setEmark(0);
		subject.setTopmark(0);
		subject.setVisits(0);
		subject.setZone(0);
		subjectService.save(subject);
		return 1;
	}
	
	/**
	 * 根据Subject获取SubjectVO
	 * @param sid 主题ID
	 * @return
	 */
	public SubjectVO getSVO(Subject subject) {
		int sid = subject.getId();
		User user = userService.get(subject.getUid());
		UserPost author = SubjectVO.newUserPost(user, subject.getTime());
		List<UserPost> users = new ArrayList<UserPost>();
		for(Post p : postService.getRecentByUser(sid, 3)) {
			User u = userService.find("from User where id = ?", new Object[]{p.getUid()}).get(0);
			users.add(SubjectVO.newUserPost(u, p));
		}
		int reply = postService.count(String.format("select count(*) from Post where sid=%s", sid)).intValue();
		String lastReply = Util.getSimpleTimeStr(subject.getLastest());
		 return new SubjectVO(sid, subject.getTitle(), author, users, reply,
		 subject.getVisits(), lastReply);
	}
}
