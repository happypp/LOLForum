package com.lolforum.vo;


import com.lolforum.entities.Post;
import com.lolforum.entities.User;
import com.lolforum.util.Util;

public class PostVO {
	private Integer uid;
	private Integer pid;
	private String userName;
	private String userAvatar;
	private String content;
	private String postTime;
	
	public PostVO() {
		super();
	}
	
	public PostVO(User u, Post p) {
		this.uid = u.getId();
		this.pid = p.getId();
		this.userName = u.getName();
		this.userAvatar = Util.realAvatarUrl(u.getAvatar());
		this.content = p.getContent();
		this.postTime = Util.getSimpleTimeStr(p.getTime());
	}
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAvatar() {
		return userAvatar;
	}
	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPostTime() {
		return postTime;
	}
	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}
	
	
}
