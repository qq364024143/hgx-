package com.hgx.my_boke_configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient//注册到服务中心
public class ApplicationMain {
	
	public static void main(String[] args){
		SpringApplication.run(ApplicationMain.class, args);
	}
}
