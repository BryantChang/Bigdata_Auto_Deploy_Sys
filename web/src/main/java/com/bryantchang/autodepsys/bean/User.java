package com.bryantchang.autodepsys.bean;

import java.sql.Timestamp;

/**
 * 
 * 用户实体类
 *
 */

public class User {

	private Long id = null;
	private String username = null;
	private String password = null;
	private Long role = null;
	private String mobile = null;
	private String email = null;
	private Timestamp ctime = null;

	public User() {
	}

	public User(Long id, String username, String password, Long role, String mobile, String email, Timestamp ctime) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.mobile = mobile;
		this.email = email;
		this.ctime = ctime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRole() {
		return role;
	}

	public void setRole(Long role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();
		buffer.append("uid:");
		buffer.append(id);
		buffer.append(" username:");
		buffer.append(username);
		buffer.append(" mobile:");
		buffer.append(mobile);
		buffer.append(" email:");
		buffer.append(email);
		return buffer.toString();
	}

}
