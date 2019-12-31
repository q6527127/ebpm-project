package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HelloServiceImpl implements HelloService{
	
	@Autowired
	RestTemplate res;
	//设置容错回调函数
	@HystrixCommand(fallbackMethod="helloFallBack")
	public String helloService() {
//		get请求
//		responseEntity<String> re = rt.getForEntity("http://xxx/user?name={1}"(或者?name={name}),String.class(返回类型),"参数"(或者Map));
//		String a = re.getBody();
//
//		String a = rt.getForObject(uri,string.class);
//
//		post请求（同上）
//		postForEntity
//		postForObject
		return res.getForObject("http://HELLO-SERVICE/hello", String.class);
	}
	
	public String helloFallBack() {
		return "error";
	}
}
