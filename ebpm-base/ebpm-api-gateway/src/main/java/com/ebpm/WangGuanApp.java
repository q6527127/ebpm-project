package com.ebpm;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.ebpm.filter.TestFilter;


//声明启动类
@EnableZuulProxy
//声明服务提供方
@EnableDiscoveryClient
@SpringCloudApplication
public class WangGuanApp 
{
    public static void main( String[] args )
    {
    	new SpringApplicationBuilder(WangGuanApp.class).web(true).run(args);
    }
    
    //启动类加载测试过滤器
    @Bean
    public TestFilter testFilter(){
    	return new TestFilter();
    }
    
//    //启动类加载TOKEN过滤器
//    @Bean
//    public TokenFilter tokenFilter(){
//    	return new TokenFilter();
//    }
    
    

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
      return new RestTemplate();
    }
      
    /**
     * Zuul使用正则表达式指定路由规则
     * 您可以使用regexmapper提供serviceId和路由之间的约定。它使用名为组的正则表达式从serviceId中提取变量，并将其注入到路由模式中。
     * #此处主要是通过正则表达式定义路由规则：
	 * #比如：此处路由到用户微服务，改下用户微服务appliaction.name为：spring-cloud-user-v1
	 * #那么访问的路径就为：http://10.138.5.48:8040/v1/spring-cloud-user/user/1
     * @return
     */
    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        return new PatternServiceRouteMapper(
            "(?<name>^.+)-(?<version>v.+$)",
            "${version}/${name}");
    }

}
