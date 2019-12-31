package com.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//启动类注解，启用配置中心
@EnableConfigServer
@SpringBootApplication
public class ConfigApp {
	
	public static void main(String[] args) {
		SpringApplication.run(ConfigApp.class, args);
	}
}
