package com.hgx.my_boke_api.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hgx.my_boke_api.common.responseResult.ResponseResult;
import com.hgx.my_boke_api.sso.entity.UserInfo;


@RequestMapping("sso")
public interface UserController {
	
	/**
	 * 用户登录    String username, String password,String loginId,String verify
	 * @param username
	 * @param password
	 * @return   
	 */
	@RequestMapping(value="login",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	ResponseResult<String> login(HttpServletRequest request,HttpServletResponse response,UserInfo userInfoParam);
	
	/**
	 * 跳转到登录页面
	 * @param request
	 * @return
	 */
	@RequestMapping("loginHtml")
	String toLoginHtml(HttpServletRequest request,Model model);
	
	@RequestMapping("getVerifyPic")
	void getVerifyPic(String type,HttpServletResponse response, HttpSession session);
}
