package test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//开启Hystrix熔断机制
@EnableCircuitBreaker
//声明服务提供方
@EnableDiscoveryClient
@SpringBootApplication
public class App {
	//注入调用微服务类
	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
		return new RestTemplate ();
	} 
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
