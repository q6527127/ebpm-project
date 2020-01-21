package com.ebpm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ebpm.base.baseController;
import com.ebpm.bean.ResponseBean;
import com.ebpm.pojo.Product;
import com.ebpm.pojo.User3;
import com.ebpm.service.ProductService;
import com.ebpm.util.RandomUtil;
import com.ebpm.util.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 数据库测试分页controller
 * @author xiaodi
 * @date 2017年11月16日
 */
@Controller //证明是controller层并且返回json
@EnableAutoConfiguration
@RequestMapping("/mysqlPager")
//@ComponentScan(basePackages={"com.example.service"})//添加的注解
public class MysqlPageController extends baseController{
	
	private final Logger logger  = Logger.getLogger(getClass());

	
    //依赖注入
	@Autowired(required = false)  
	ProductService productService;
    
    /**
     * 查询所有
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "loadAllDb0",method = RequestMethod.GET)
    @ResponseBody
    public  Object loadAllDb0(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	 JSONObject map = new JSONObject();//创建JSONmap来存放JSON数据传到前台
         PageHelper.startPage(1, 15);//设置数据库分页查询的范围
    	 List<Map<Object, Object>> list = productService.loadAllToDb("dynamic_db0");
    	 //PageInfo<Map<Object, Object>> pageInfo=new PageInfo(list);
         map.put("count",new PageInfo<Map<Object, Object>>(list).getTotal());//获取查询总条数
         map.put("data",list);
         return ResponseBean.ok("查询成功",map);
    }
    
}
