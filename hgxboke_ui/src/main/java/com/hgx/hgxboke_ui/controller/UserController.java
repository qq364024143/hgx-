package com.hgx.hgxboke_ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hgx.hgxboke_ui.feign.TestFeign;

@Controller
public class UserController {
	
	@Autowired
	private TestFeign testFeign;
	
	@RequestMapping("test")
	@ResponseBody
	public void testFeign(){
		int count = testFeign.count();
		System.out.println(count+"===========================");
	}
	
	@RequestMapping("aboutme")
	public String toAboutMeJsp(){
		System.out.println("收到请求");
		return  "about_me";
	}
}
