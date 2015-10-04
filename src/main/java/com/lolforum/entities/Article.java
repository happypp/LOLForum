package com.lolforum.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "article")
public class Article {
	private Integer id;
	private String title;
	private String content;
	private String source;
	private Date time;
	private Integer visits;
	
	public Article() {
	}
	
	public Article(String title, String content, String source, Date time) {
		super();
		this.title = title;
		this.content = content;
		this.source = source;
		this.time = time;
		this.visits = 0;
	}
	
	public Article(String title, String content, String source, Date time, Integer visits) {
		super();
		this.title = title;
		this.content = content;
		this.source = source;
		this.time = time;
		this.visits = visits;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getVisits() {
		return visits;
	}

	public void setVisits(Integer visits) {
		this.visits = visits;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content="
				+ content + ", source=" + source + ", time=" + time
				+ ", visits=" + visits + "]";
	}
}
