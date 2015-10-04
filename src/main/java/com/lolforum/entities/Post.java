package com.lolforum.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "post")
public class Post {
	private Integer id;
	private Integer sid;
	private Integer uid;
	private String content;
	private Timestamp time;
	private Integer agree;
	
	public Post() {
	}
	
	public Post(Integer id) {
		super();
		this.id = id;
	}
	
	public Post(Integer id, Integer sid, Integer uid,
			java.util.Date time) {
		super();
		this.id = id;
		this.sid = sid;
		this.uid = uid;
		this.time = (Timestamp) time;
	}
	
	public Post(Integer sid, Integer uid, String content, Timestamp time,
			Integer like) {
		super();
		this.sid = sid;
		this.uid = uid;
		this.content = content;
		this.time = time;
		this.agree = like;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getSid() {
		return sid;
	}
	
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	
	public Integer getUid() {
		return uid;
	}
	
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Timestamp getTime() {
		return time;
	}
	
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	public Integer getAgree() {
		return agree;
	}
	
	public void setAgree(Integer agree) {
		this.agree = agree;
	}
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", sid=" + sid + ", uid=" + uid
				+ ", content=" + content + ", time=" + time + ", agree="
				+ agree + "]";
	}
}
