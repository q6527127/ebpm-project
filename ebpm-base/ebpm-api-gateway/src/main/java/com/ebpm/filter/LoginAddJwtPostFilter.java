package com.ebpm.filter;

import com.ebpm.common.Result;
import com.ebpm.config.DataFilterConfig;
import com.ebpm.util.JwtUtil;
import com.ebpm.util.PathUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoginAddJwtPostFilter extends ZuulFilter {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    DataFilterConfig dataFilterConfig;

	private final Logger log  = Logger.getLogger(getClass());

    
    /**
     * pre：路由之前
     * routing：路由之时
     * post： 路由之后
     * error：发送错误调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    /**
     * filterOrder：过滤的顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 2;
    }

    /**
     * shouldFilter：这里可以写逻辑判断，是否要过滤
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String uri = ctx.getRequest().getRequestURI();
        //登陆成功后进入过滤器，生成token
        if (StringUtils.indexOf(uri, "login")!=-1) {
        	return true;
	    }
        //路径与配置的相匹配，则执行过滤
//        for (String pathPattern : dataFilterConfig.getUserLoginPath()) {
//            if (PathUtil.isPathMatch(pathPattern, ctx.getRequest().getRequestURI())) {
//                return true;
//            }
//        }
        return false;
    	
    }

    /**
     * 执行过滤器逻辑，登录成功时给响应内容增加token
     *
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        try {
            InputStream stream = ctx.getResponseDataStream();
            String body = StreamUtils.copyToString(stream, StandardCharsets.UTF_8);
            final Result<HashMap<String, Object>> result = objectMapper.readValue(body, new TypeReference<Result<HashMap<String, Object>>>() {
            });
            //result.getCode() == 0 表示登录成功
            if (result.getCode() == 0) {
                HashMap<String, Object> jwtClaims = new HashMap<String, Object>() {{
                    put("userId", result.getData().get("userId"));
                    put("companyCode", "ZZ000097");
                }};
                Date expDate = DateTime.now().plusDays(7).toDate(); //过期时间 7 天
                String token = jwtUtil.createJWT(expDate, jwtClaims);
                //body json增加token
                result.getData().put("token", token);
                //序列化body json,设置到响应body中
                body = objectMapper.writeValueAsString(result);
                ctx.setResponseBody(body);

                //响应头设置token
                ctx.addZuulResponseHeader("token", token);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
