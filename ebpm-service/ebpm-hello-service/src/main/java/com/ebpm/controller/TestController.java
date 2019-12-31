package com.ebpm.controller;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {
	
    @Value("${testValue}")
    private String fromValue;

  
	private final Logger logger  = Logger.getLogger(getClass());
	
	//获取调用方信息用
	@Autowired
	private DiscoveryClient client;
	/**
	 * 被调用服务
	 * @return
	 */
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String index(){
		ServiceInstance  instance =  client.getLocalServiceInstance();
		logger.info("/hello, host:"+instance.getHost()+", service_id:"+instance.getServiceId());
		return "Hello service";
	}
	
	  /**
     * 返回配置文件中的值
     */
	@RequestMapping(value="/from",method=RequestMethod.GET)
    public String returnFormValue(){
        return fromValue;
    }
}
