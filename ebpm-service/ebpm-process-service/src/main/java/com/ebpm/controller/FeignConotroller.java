package com.ebpm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ebpm.common.commonFeign.TestService;

@RestController
@RequestMapping("/feign")
public class FeignConotroller {
	
	@Autowired
	TestService testService;
	
	@RequestMapping(value="/getDataToDb",method=RequestMethod.GET)
	public String getDataToDb(HttpServletRequest request,HttpServletResponse response){
		return testService.loadAllDbToDb(request.getParameter("dataSourceKey")); 
	}
	
	@RequestMapping(value="/getSession",method=RequestMethod.GET)
	public String getSession(HttpServletRequest request,HttpServletResponse response){
		System.out.println("client:"+request.getSession().getId());
		return testService.get();
	}
	
	@RequestMapping(value="/setSession",method=RequestMethod.GET)
	public String setSession(){
		return testService.set();
	}
	
	@RequestMapping(value="/loadAllDb0Page",method=RequestMethod.GET)
	public String loadAllDb0Page(){
		return testService.loadAllDb0Page();
	}
	
}
