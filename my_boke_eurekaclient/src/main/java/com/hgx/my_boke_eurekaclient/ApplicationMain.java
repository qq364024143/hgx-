package com.hgx.my_boke_eurekaclient;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages="com.hgx.my_boke_eurekaclient")
@EnableEurekaClient
@MapperScan("com.hgx.my_boke_eurekaclient.*.dao")
public class ApplicationMain {
	
	public static void main(String[] args){
		SpringApplication.run(ApplicationMain.class, args);
	}
}
