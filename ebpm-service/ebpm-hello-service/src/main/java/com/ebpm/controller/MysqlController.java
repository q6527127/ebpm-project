package com.ebpm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ebpm.common.base.baseController;
import com.ebpm.common.pojo.Product;
import com.ebpm.common.pojo.User3;
import com.ebpm.service.ProductService;
import com.ebpm.common.util.RandomUtil;
import com.ebpm.common.util.RedisUtil;

/**
 * 数据库测试controller
 * @author xiaodi
 * @date 2017年11月16日
 */
@Controller //证明是controller层并且返回json
@EnableAutoConfiguration
@RequestMapping("/mysql")
//@ComponentScan(basePackages={"com.example.service"})//添加的注解
public class MysqlController extends baseController{
    //依赖注入
	@Autowired(required = false)  
	ProductService productService;
    
    
    /**
     * 查询数据并返回在页面上
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "mySql",method = RequestMethod.GET)
    public  ModelAndView queryProduct(HttpServletRequest request,HttpServletResponse response) {
    	ModelAndView model = new ModelAndView();
    	String name  = request.getParameter("name");
        Map<Object, Object> p = productService.queryProductByName(name);
        model.addObject("p",p);
        model.addObject("time",new Date());  
        model.addObject("message","hi,hello ......"); 
        model.setViewName("mysqlIndex");
        return model;
    }
    
    
    /**
     * 查询所有
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "loadAllDb0",method = RequestMethod.GET)
    @ResponseBody
    public  Object loadAllDb0(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	request.getSession().setAttribute("dataSourceKey", "dynamic_db0");
        return productService.loadAll();
    }
    
    
    @RequestMapping(value = "loadAllDbToDb",method = RequestMethod.GET)
    @ResponseBody
    public  Object loadAllDbToDb(HttpServletRequest request,HttpServletResponse response) throws Exception{
        return productService.loadAllToDb(request.getParameter("dataSourceKey"));
    }
    
    
    /**
     * 测试事务
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "mySqlTestTransaction",method = RequestMethod.GET)
    @ResponseBody
    public  ModelAndView mySqlTestTransaction(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	productService.add();
        return null;
    }
    /**
     * 测试自动返回主键
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "add",method = RequestMethod.GET)
    @ResponseBody
    public  int add(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	User3 user= new User3(0, "111", 5);
    	int count  = productService.addOne(user);
    	System.out.println(user.getId());
        return count;
    }
    
    /**
     * 批量插入返回KEY map 自动插入ID
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "addList",method = RequestMethod.GET)
    @ResponseBody
    public  int addList(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	List<HashMap<String,Object>>list = new ArrayList<HashMap<String,Object>>();
    	HashMap<String,Object> map1 = new HashMap<String, Object>();
    	map1.put("name", "qqq");
    	map1.put("age", 222);
    	HashMap<String,Object> map2 = new HashMap<String, Object>();
    	map2.put("name", "www");
    	map2.put("age", 222);
    	HashMap<String,Object> map3 = new HashMap<String, Object>();
    	map3.put("name", "eee");
    	map3.put("age", 222);
    	list.add(map1);
    	list.add(map2);
    	list.add(map3);
    	int count  = productService.addList(list);
    	System.out.println(list.toString());
        return count;
    }
    
    
    /**
     * 多线程批量插入返回KEY map 自动插入ID
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "addList2",method = RequestMethod.GET)
    @ResponseBody
    public  int addList2(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	long time=new Date().getTime();
    	
    	for (int i = 0; i < 10; i++) {
    		new AddDataThread(getList(10000), "Thread"+i).start();
		}
    	
		//new AddDataThread(getList(100000), "Thread1").start();
		//获取耗时
		time=new Date().getTime()-time;

    	//int count  = productService.addList2(list);
    	//System.out.println(list);
    	return 0;
    }
    /**
     * 获取指定数量数据集合
     * @param count
     * @return
     */
    private List<HashMap<String,Object>> getList(int count) {
    	List<HashMap<String,Object>>list = new ArrayList<HashMap<String,Object>>();
    	for (int i = 0; i < count; i++) {
	    	HashMap<String,Object> map1 = new HashMap<String, Object>();
	    	map1.put("name", RandomUtil.getRandomStr(1));
	    	map1.put("age", RandomUtil.getRandomStr(2));
	    	list.add(map1);
    	}
    	return  list ;
	}
    
    /**
     * 线程跑批量插入数据
     * @author xiaodi
     *
     */
    class AddDataThread extends Thread{
    	List<HashMap<String,Object>> list = null;
    	/**
    	 * @param list 需插入的集合
    	 * @param name 线程名字
    	 */
    	public AddDataThread(List<HashMap<String,Object>> list,String name) {
    		super(name);
    		this.list=list;
    		
		}
    	@Override
    	public void run() {
    		try {
    			long time=new Date().getTime();
    			
				int count  = productService.addList2(list);
				//获取耗时
				time=new Date().getTime()-time;
				System.out.println("ThreadName:"+Thread.currentThread().getName()+"  执行总条数："+count+" 总耗时："+time+"毫秒");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}
