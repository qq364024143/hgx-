package com.hgx.hgxboke_ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient//此注解表示将此服务注册到注册中心
public class ApplicationMain {
	
	public static void main(String[] args){
		SpringApplication.run(ApplicationMain.class, args);
	}
}
