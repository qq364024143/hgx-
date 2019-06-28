package com.hgx.my_boke_api.sso.entity;

import java.io.Serializable;
import java.util.List;

public class UserInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String username;
	private String password;
	private String nickname;
	private String email;
	private String telNumber;
	private String job;
	private String beGoodAt;
	private String userPicture;
	private String roleName;
	private String createTime;
	
	private List<UserPermission> userPermissions;
	
	//登录提交表单项相关
	private String verify;
	private String loginId;
	
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserInfo(String userId, String username, String password,
			String nickname, String email, String telNumber, String job,
			String beGoodAt, String userPicture, String roleName,
			String createTime) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.telNumber = telNumber;
		this.job = job;
		this.beGoodAt = beGoodAt;
		this.userPicture = userPicture;
		this.roleName = roleName;
		this.createTime = createTime;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelNumber() {
		return telNumber;
	}
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getBeGoodAt() {
		return beGoodAt;
	}
	public void setBeGoodAt(String beGoodAt) {
		this.beGoodAt = beGoodAt;
	}
	public String getUserPicture() {
		return userPicture;
	}
	public void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public List<UserPermission> getUserPermissions() {
		return userPermissions;
	}
	public void setUserPermissions(List<UserPermission> userPermissions) {
		this.userPermissions = userPermissions;
	}
	public String getVerify() {
		return verify;
	}
	public void setVerify(String verify) {
		this.verify = verify;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	
}
