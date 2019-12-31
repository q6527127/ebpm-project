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



/**
 * 产品数据层接口
 * @author pengfei.xiong
 * @date 2017年11月16日
 */
public interface Product2Dao {
    int addList2(List<HashMap<String,Object>>list);

}