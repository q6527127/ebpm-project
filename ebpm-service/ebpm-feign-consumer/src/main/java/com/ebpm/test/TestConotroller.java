package com.ebpm.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


 

@RestController
public class TestConotroller {
	
	@Autowired
	TestService helloService;
	
	@RequestMapping(value="/getDataToDb",method=RequestMethod.GET)
	public String getDataToDb(HttpServletRequest request,HttpServletResponse response){
		return helloService.loadAllDbToDb(request.getParameter("dataSourceKey")); 
	}
	
	@RequestMapping(value="/getSession",method=RequestMethod.GET)
	public String getSession(HttpServletRequest request,HttpServletResponse response){
		System.out.println("client:"+request.getSession().getId());
		return helloService.get();
	}
	
	@RequestMapping(value="/setSession",method=RequestMethod.GET)
	public String setSession(){
		return helloService.set();
	}
}
