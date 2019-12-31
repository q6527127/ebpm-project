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
public class TokenFilter extends ZuulFilter{

	private final Logger logger  = Logger.getLogger(getClass());
	
	public Object run() {
//		//获取当前请求上下文，在这个上下文基础上可以做很多事情了。具体自己查看API。
//        RequestContext context = RequestContext.getCurrentContext();
//        //获取原始Htpp请求，有这个对象也可以做很多事情了。自己发挥吧。
//        HttpServletRequest request = context.getRequest();
//        //获取全部参数
//        Dto inDto = Dtos.newDto(request);
//        log.info("Request请求参数列表->", JSON.toJSONString(inDto));
//        log.info("Request请求URL->", request.getRequestURL());
//        //获取指定参数，token之类的安全类参数也可以是在头信息中
//        String token = request.getParameter("accessToken");
//        //Token的检验逻辑没这么简单，这里只是给大家举个栗子
//        if (!StringUtils.isEmpty(token)) {
//            //这里可以进一步校验token的合法性、时效性，甚至对报文进行校验
//            context.setSendZuulResponse(true); //将请求往后转发
//            context.setResponseStatusCode(200);
//        } else {
//            HttpServletResponse response = context.getResponse();
//            response.setHeader("Content-Type", "application/json;charset=UTF-8");
//            context.setSendZuulResponse(false); //终止转发，返回响应报文
//            context.setResponseStatusCode(400);
//            Map<String,String> responseMap=new HashMap<String,String>();
//            responseMap.put("errorcode", "400");
//            responseMap.put("errormsg", "请求被拦截");
//            context.setResponseBody(JSON.toJSONString(responseMap));
//        }

        return null;
        }

	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

}
