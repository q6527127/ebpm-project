package com.ebpm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController  
@RequestMapping("/session")
public class SessionSharedTestController {  
	
    @RequestMapping(value = "/set", method = RequestMethod.GET)  
    public Map<String, Object> firstResp (HttpServletRequest request){  
        Map<String, Object> map = new HashMap();  
        HttpSession session= request.getSession();
        session.setAttribute("request Url", request.getRequestURL());  
        map.put("sessionId", session.getId());  
        map.put("message", request.getRequestURL());  
        return map;  
    }  
  
    @RequestMapping(value = "/get", method = RequestMethod.GET)  
    public Object sessions (HttpServletRequest request){  
		System.out.println("server:"+request.getSession().getId());
        Map<String, Object> map = new HashMap();  
        map.put("sessionId", request.getSession().getId());  
        map.put("message", request.getSession().getAttribute("request Url"));  
        return map;  
    }  
}  