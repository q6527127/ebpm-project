package com.ebpm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.ebpm.common.pojo.Product;
import com.ebpm.common.pojo.User3;


/**
 * 产品数据层接口
 * @author pengfei.xiong
 * @date 2017年11月16日
 */
public interface ProductDao {
    /**
     * 根据名称查询产品
     * @param name 名称
     * @return 返回产品实体对象
     */
    Map<Object, Object> selectProductByName(String name);
    List<Map<Object, Object>> loadAll();
    
    void add(String name);
    int addOne(User3 user);
    int addList(List<HashMap<String,Object>>list);

}