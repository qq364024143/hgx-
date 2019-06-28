package com.hgx.hgxboke_sso.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hgx.my_boke_api.sso.entity.UserInfo;
import com.hgx.my_boke_api.sso.entity.UserPermission;

@Repository
public interface UserDao {
	
	UserInfo login(@Param("username")String username, @Param("password")String password);
	
	List<UserPermission> getPermissionByUserId(String userId);
}
