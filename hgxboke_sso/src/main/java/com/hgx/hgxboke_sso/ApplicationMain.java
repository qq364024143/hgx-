package com.hgx.hgxboke_sso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;

/**
 * spring boot应用启动入口类
 * @author hgx
 *
 */
@SpringBootApplication(scanBasePackages="com.hgx.hgxboke_sso")
@EnableEurekaClient
@PropertySource("classpath:config/datasource.properties")
@MapperScan("com.hgx.hgxboke_sso.*.dao")
public class ApplicationMain {
		
	public static void main(String[] args){
		SpringApplication.run(ApplicationMain.class, args);
	}
}
