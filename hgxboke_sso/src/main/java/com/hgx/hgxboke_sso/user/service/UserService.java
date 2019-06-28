package com.hgx.hgxboke_sso.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hgx.hgxboke_sso.user.dao.UserDao;
import com.hgx.my_boke_api.sso.entity.UserInfo;
import com.hgx.my_boke_api.sso.entity.UserPermission;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	public UserInfo login(String username, String password){
		return userDao.login(username, password);
	}
	
	public List<UserPermission> getPermissionByUserId(String userId){
		return userDao.getPermissionByUserId(userId);
	}
}
