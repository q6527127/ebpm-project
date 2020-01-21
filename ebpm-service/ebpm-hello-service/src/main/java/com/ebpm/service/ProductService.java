package com.ebpm.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ebpm.pojo.User3;


/**
 * 产品业务层接口
 * @author pengfei.xiong
 * @date 2017年11月16日
 */
public interface ProductService {
    public Map<Object, Object> queryProductByName(String name);
    void add() throws Exception;
    int addOne(User3 user) throws Exception;
    int addList(List<HashMap<String,Object>>list) throws Exception;
    int addList2(List<HashMap<String,Object>>list) throws Exception;
    public List<Map<Object, Object>> loadAll();
    public List<Map<Object, Object>> loadAllToDb(String targetSource);
    public List<Map<Object, Object>> loadAllToDbPage(String targetSource, Integer page, Integer limit);

}