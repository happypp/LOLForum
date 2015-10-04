package com.lolforum.vo;

import java.sql.Timestamp;
import java.util.List;

import com.lolforum.entities.Post;
import com.lolforum.entities.User;
import com.lolforum.util.Util;

public class SubjectVO {
	private Integer sid;
	private String title;
	private UserPost author;
	private List<UserPost> users;
	private Integer reply;
	private Integer visits;
	private String lastReply;
	
	public SubjectVO() {
	}
	
	public SubjectVO(Integer sid, String title, UserPost author,
			List<UserPost> users, Integer reply, Integer visits,
			String lastReply) {
		super();
		this.sid = sid;
		this.title = title;
		this.author = author;
		this.users = users;
		this.reply = reply;
		this.visits = visits;
		this.lastReply = lastReply;
	}
	
	public static UserPost newUserPost(User user, Timestamp time) {
		return new UserPost(user.getId(), user.getName(), Util.realAvatarUrl(user.getAvatar()),
				Util.getSimpleTimeStr(time));
	}
	
	public static UserPost newUserPost(User user, Post post) {
		return new UserPost(user.getId(), user.getName(),Util.realAvatarUrl(user.getAvatar()),
				Util.getSimpleTimeStr(post.getTime()));
	}
	
	public static class UserPost {
		private Integer userId;
		private String userName;
		private String userAvatar;
		private String postTime;
		
		private UserPost() {
			super();
		}
		
		public UserPost(Integer userId, String userName, String userAvatar,
				String postTime) {
			super();
			this.userId = userId;
			this.userName = userName;
			this.userAvatar = userAvatar;
			this.postTime = postTime;
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
		
		public String getPostTime() {
			return postTime;
		}
		
		public void setPostTime(String postTime) {
			this.postTime = postTime;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}
	}
	
	@Override
	public String toString() {
		return "SubjectVO [sid=" + sid + ", title=" + title + ", author="
				+ author + ", users=" + users + ", reply=" + reply
				+ ", visits=" + visits + ", lastReply=" + lastReply + "]";
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public UserPost getAuthor() {
		return author;
	}

	public void setAuthor(UserPost author) {
		this.author = author;
	}

	public List<UserPost> getUsers() {
		return users;
	}

	public void setUsers(List<UserPost> users) {
		this.users = users;
	}

	public Integer getReply() {
		return reply;
	}

	public void setReply(Integer reply) {
		this.reply = reply;
	}

	public Integer getVisits() {
		return visits;
	}

	public void setVisits(Integer visits) {
		this.visits = visits;
	}

	public String getLastReply() {
		return lastReply;
	}

	public void setLastReply(String lastReply) {
		this.lastReply = lastReply;
	}
}
