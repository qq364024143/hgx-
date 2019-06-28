package com.hgx.hgxboke_sso.user.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.hgx.hgxboke_sso.common.RedisUtil;
import com.hgx.hgxboke_sso.user.service.UserService;
import com.hgx.hgxboke_sso.valide_code.Captcha;
import com.hgx.hgxboke_sso.valide_code.GifCaptcha;
import com.hgx.my_boke_api.common.responseResult.ResponseResult;
import com.hgx.my_boke_api.sso.controller.UserController;
import com.hgx.my_boke_api.sso.entity.UserInfo;
import com.hgx.my_boke_api.sso.entity.UserPermission;

@Controller
public class UserControllerImpl implements UserController{
	
	private Logger log = LoggerFactory.getLogger(UserControllerImpl.class);
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private UserService userService;
	
	@Override
	public ResponseResult<String> login(HttpServletRequest request, HttpServletResponse response,@RequestBody UserInfo userInfoParam) {
		ResponseResult<String> result = new ResponseResult<String>();
		if(StringUtils.isEmpty((String)request.getSession().getAttribute("login_code"))||!((String)request.getSession().getAttribute("login_code")).equalsIgnoreCase(userInfoParam.getVerify())){
			result.setCode("500");
			result.setMessage("验证码错误");
			result.setData("验证码错误");
			return result;
		}
		if(StringUtils.isEmpty((String)request.getSession().getAttribute("loginId"))||!((String)request.getSession().getAttribute("loginId")).equals(userInfoParam.getLoginId())){
			result.setCode("500");
			result.setMessage("请勿重复提交");
			return result;
		}
		
		UserInfo userInfo = userService.login(userInfoParam.getUsername(), userInfoParam.getPassword());
		
		if(null == userInfo){
			result.setCode("500");
			result.setMessage("账号密码错误");
			return result;
		}
		
		//查询用户权限
		List<UserPermission> userPermissions = userService.getPermissionByUserId(userInfo.getUserId());
		userInfo.setUserPermissions(userPermissions);
		//登录成功，创建sessionId，保存到redis
		String sessionID = request.getSession().getId();
		redisUtil.set(sessionID, userInfo,30*60*1000L);//30分钟有效期
		
		Cookie cookie = new Cookie("sysSessionID",sessionID);
		cookie.setDomain("127.0.0.1");//cookie共享域
		cookie.setPath("/");
		cookie.setHttpOnly(false);//仅http请求有效：false，ajax请求也有效
		response.addCookie(cookie);
		/*Cookie cookie2 = new Cookie("sysSessionID",sessionID);
		cookie2.setDomain("http://127.0.0.1:1003/bokeAdmin");//cookie共享域
		response.addCookie(cookie2);*/
		
		result.setCode("200");
		result.setData("");
		result.setMessage("登录成功");
		return result;
	}

	@Override
	public String toLoginHtml(HttpServletRequest request, Model model) {//将model中的值放到request中
		//防止表单重复提交
		DateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
		String loginId = fmt.format(new Date());
		request.getSession().setAttribute("loginId", loginId);
		model.addAttribute("loginId", loginId);
		model.addAttribute("redirectUrl",request.getAttribute("redirectUrl"));
		log.info("loginID为："+loginId);
		return "loginHtml";
	}

	@Override
	public void getVerifyPic(@RequestParam(value="type",required=true)String type,HttpServletResponse response, HttpSession session) {

		response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        response.setContentType("image/gif");  
        
        Captcha captcha = new GifCaptcha(146,33,4);
        try {
			captcha.out(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        log.info(type+"获取验证码 ："+captcha.text());
        //存入Session
        session.setAttribute(type+"_code",captcha.text());
	}
	
	

}
