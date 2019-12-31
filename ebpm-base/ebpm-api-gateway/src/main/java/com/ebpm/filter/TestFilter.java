package com.ebpm.filter;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

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

	private final Logger logger  = Logger.getLogger(getClass());
	
	public Object run() {
		RequestContext ctx= RequestContext.getCurrentContext();
		HttpServletRequest rq= ctx.getRequest();
		logger.info("进来过滤器了");
		System.out.println(rq.getSession().getId());
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
