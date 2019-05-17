package com.hgx.my_boke_api.boke.entity;

import java.io.Serializable;

public class UserVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String username;
	private String userPassword;
	private String nickname;
	public UserVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserVo(String userId, String username, String userPassword,
			String nickname) {
		super();
		this.userId = userId;
		this.username = username;
		this.userPassword = userPassword;
		this.nickname = nickname;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}
