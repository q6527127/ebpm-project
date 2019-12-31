package com.ebpm;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.ebpm.config.FeignHystrixConcurrencyStrategy;

//启动类注解，启用声明式服务调用
@EnableFeignClients
//声明服务提供方
@EnableDiscoveryClient
@SpringBootApplication
public class FeignApp {
	
	public static void main(String[] args) {
		SpringApplication.run(FeignApp.class, args);
	}
	
	@Bean
	public FeignHystrixConcurrencyStrategy feignHystrixConcurrencyStrategy() {
	    return new FeignHystrixConcurrencyStrategy();
	}
}
