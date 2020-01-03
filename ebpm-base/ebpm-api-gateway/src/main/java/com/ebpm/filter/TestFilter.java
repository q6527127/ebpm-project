package com.ebpm.filter;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.ebpm.util.HttpClientTool;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 过滤器
	继承ZuulFilter
	run()过滤器主体
 * @author xiaodi
 *
 */
public class TestFilter extends ZuulFilter{
	
	@Autowired
	RestTemplate res;
	
	private final Logger logger  = Logger.getLogger(getClass());
	
	public Object run() {
		
		
		RequestContext ctx= RequestContext.getCurrentContext();
		HttpServletRequest rq= ctx.getRequest();
		
		
//		if (rq.getRequestURI().contains("initializeSession")) {
//			 	ctx.setSendZuulResponse(true);// 对该请求进行路由
//		        ctx.setResponseStatusCode(200); // 返回200正确响应
//		        return null;
//		}
		//Map<String, String> params = new HashMap<String, String>();
		//params.put("name", "user1");
		//String sessionid = HttpClientTool.doGet("http://localhost:5555/v1/spring-clould-test-service/ebpm/session/initializeSession", params);
		//System.out.println("init-session:"+sessionid);
		System.out.println("session:"+rq.getSession().getId());
        ctx.setSendZuulResponse(true);// 对该请求进行路由
        ctx.setResponseStatusCode(200); // 返回200正确响应
		rq.getSession().setAttribute("request Url", rq.getRequestURL());  
//		String name = rq.getParameter("name");
//		if (StringUtils.isEmpty(name)) {
//			logger.info("name为空拦截请求");
//			ctx.setSendZuulResponse(false);//拦截实现  关闭路由
//			ctx.setResponseStatusCode(400);//错误码
//			return null;
//		}
//		logger.info("OK --- name:"+name);
		return null;
	}

	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

}
