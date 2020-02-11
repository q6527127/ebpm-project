package com.ebpm;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

//启动类注解，启用声明式服务调用
@EnableFeignClients
//启动服务提供方
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceApp 
{
    public static void main( String[] args )
    {
        SpringApplication.run(ServiceApp.class, args);
    }
}
