package com.hgx.hgxboke_sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * spring boot应用启动入口类
 * @author hgx
 *
 */
@SpringBootApplication(scanBasePackages="com.hgx.hgxboke_sso")
@EnableEurekaClient
public class ApplicationMain {
		
	public static void main(String[] args){
		SpringApplication.run(ApplicationMain.class, args);
	}
}
