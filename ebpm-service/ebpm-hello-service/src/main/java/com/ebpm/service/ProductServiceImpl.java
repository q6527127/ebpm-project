package com.ebpm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Reference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ebpm.dao.Product2Dao;
import com.ebpm.dao.ProductDao;
import com.ebpm.pojo.Product;
import com.ebpm.pojo.User3;

/**
 * 产品业务层实现类
 * @author XSWL pengfei.xiong
 * @date 2017年11月16日
 */
@Service
//@Service(value="productService")
//Scan("com.example.dao") //与dao层的@Mapper二选一写上即可(主要作用是扫包)
public class ProductServiceImpl implements ProductService {
    //依赖注入
	@Autowired(required = false)  
    ProductDao mapper;
	@Autowired(required = false)  
    Product2Dao mapper2;

    public Map<Object, Object> queryProductByName(String name) {
    	Map<Object, Object>pro = mapper.selectProductByName(name);
    	System.out.println(pro);
        return pro;
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public void add() throws Exception{
    	for (int i = 0; i < 5; i++) {
			if (i<=2) {
		    	mapper.add(i+"");
			}else {
                //throw new RuntimeException();  
			}
		}
	}
    
    public int addOne(User3 user) throws Exception{
		    return	mapper.addOne(user);
	}

	public int addList(List<HashMap<String, Object>> list) throws Exception {
	    return	mapper.addList(list);
	}

	public int addList2(List<HashMap<String, Object>> list) throws Exception {
		// TODO Auto-generated method stub
		return mapper2.addList2(list);
	}

	public List<Map<Object, Object>> loadAll() {
		// TODO Auto-generated method stub
		return mapper.loadAll();
	}
	public List<Map<Object, Object>> loadAllToDb(String targetSource) {
		// TODO Auto-generated method stub
		return mapper.loadAll();
	}
}
