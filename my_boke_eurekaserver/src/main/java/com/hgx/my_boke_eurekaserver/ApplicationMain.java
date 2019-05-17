package com.hgx.my_boke_eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ApplicationMain {
	
	public static void main(String[] args){
		SpringApplication.run(ApplicationMain.class, args);
	}
}
