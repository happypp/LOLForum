package com.lolforum.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="subject")
public class Subject {
	private Integer id;
	private String title;
	private String content;
	private Integer uid;
	private Timestamp time;
	private Integer zone;
	private Integer visits;
	private Integer topmark;
	private Integer emark;
	private Timestamp lastest;
	
	public Subject() {}
	
	public Subject(Integer id) {
		super();
		this.id = id;
	}
	
	public Subject(String title, String content, Integer uid, Timestamp time,
			Integer zone, Integer visits, Integer topmark, Integer emark,
			Timestamp lastest) {
		super();
		this.title = title;
		this.content = content;
		this.uid = uid;
		this.time = time;
		this.zone = zone;
		this.visits = visits;
		this.topmark = topmark;
		this.emark = emark;
		this.lastest = lastest;
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

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Integer getZone() {
		return zone;
	}

	public void setZone(Integer zone) {
		this.zone = zone;
	}

	public Integer getVisits() {
		return visits;
	}

	public void setVisits(Integer visits) {
		this.visits = visits;
	}

	public Integer getTopmark() {
		return topmark;
	}

	public void setTopmark(Integer topmark) {
		this.topmark = topmark;
	}

	public Integer getEmark() {
		return emark;
	}

	public void setEmark(Integer emark) {
		this.emark = emark;
	}

	public Timestamp getLastest() {
		return lastest;
	}

	public void setLastest(Timestamp lastest) {
		this.lastest = lastest;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", title=" + title + ", content="
				+ content + ", uid=" + uid + ", time=" + time + ", zone="
				+ zone + ", visits=" + visits + ", topmark=" + topmark
				+ ", emark=" + emark + ", lastest=" + lastest + "]";
	}

}
