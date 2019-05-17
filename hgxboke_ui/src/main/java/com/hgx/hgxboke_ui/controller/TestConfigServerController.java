package com.hgx.hgxboke_ui.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope//动态刷新远程配置的值
public class TestConfigServerController {

	
	//@Value("${name}")
	private String name;
	//@Value("${age}")
	private String age;
	
	/**
	 * 响应json,produces = MediaType.APPLICATION_JSON_VALUE
	 * @return
	 */
	@RequestMapping(value="getConfigServerProperties",produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String,String> getConfigServerProperties(){
		Map<String,String> m = new HashMap<String,String>();
		m.put("name", name);
		m.put("age", age);
		return m;
	}
}
