package com.lolforum.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.lolforum.constant.Constant;

@Entity
@Table(name="user")
public class User {
	private Integer id;
	private String name;
	private String pwd;
	private String email;
	private Integer type;
	private Integer score;
	private String avatar;
	
	public User() {}
	
	public User(Integer id) {
		super();
		this.id = id;
	}

	public User(String name, String pwd, String email, Integer type,
			Integer score, String avatar) {
		super();
		this.name = name;
		this.pwd = pwd;
		this.email = email;
		this.type = type;
		this.score = score;
		this.avatar = avatar;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", pwd=" + pwd
				+ ", email=" + email + ", type=" + type + ", score=" + score
				+ ", avatar=" + avatar + "]";
	}
}
