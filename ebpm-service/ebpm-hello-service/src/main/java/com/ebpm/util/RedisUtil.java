package com.ebpm.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {
    private static String password="111111";
	
	//设置key/value值，
	public static void setValue(String key, Object val,RedisTemplate<String, Object> template) {  
	    template.opsForValue().set(key, val);  
	}  
	//设置key/value值,并且可以设置超时时间。
	public static void setValue(String key, Object val, int time, TimeUnit unit,RedisTemplate<String, Object> template) {  
	    template.opsForValue().set(key, val, time, unit);  
	} 
	//4.2 获取key/value值
	public static Object getValue(String key,RedisTemplate<String, Object> template) {  
	    return template.opsForValue().get(key);  
	} 
	//4.3 同时设置多个key/value
	public static void multiSet(Map<String, Object> map,RedisTemplate<String, Object> template) {  
	    template.opsForValue().multiSet(map);  
	}  
	//4.4 同时获取多个key/value
	public static  List<Object> multiGet(Collection<String> keys,RedisTemplate<String, Object> template) {  
	    return template.opsForValue().multiGet(keys);  
	}  
	//4.5 操作取号器
	public static long incr(String key, long delta,RedisTemplate<String, Object> template) {  
	    return template.opsForValue().increment(key, delta);  
	}  
	//操作队列，在高并发的情况下，如果同时上千个线程同时操作数据库，数据库很可能会因此而宕机。
	//这个时候我们可以利用redis队列，进行排队依次写入数据库。利用lpush和rpop，可以形成一个queue。
	public static void lpush(String key, String value,RedisTemplate<String, Object> template) {  
	    template.opsForList().leftPush(key, value);  
	}  
	  
	public static List<Object> range(String key, int start, int end,RedisTemplate<String, Object> template) {  
	    return template.opsForList().range(key, start, end);  
	}  
	  
	public static Object rpop(String key,RedisTemplate<String, Object> template) {  
	    return template.opsForList().rightPop(key);  
	}  
	//4.7 有时候我们不光希望保存key/value，也希望缓存某个对象，比如用户所有数据到redis。我们可以使用Redis的Hash数据结构来缓存。
	public static void setHash(String key, Map<String, Object> map,RedisTemplate<String, Object> template) {  
	    template.opsForHash().putAll(key, map);  
	}  
	  
	public static Object getHash(String key, String prop,RedisTemplate<String, Object> template) {  
	    return template.opsForHash().get(key, prop);  
	}  
	  
	public static Map getHashAll(String key,RedisTemplate<String, Object> template) {  
	    Map map = new HashMap();  
	    map.put("keys", template.opsForHash().keys(key));  
	    map.put("vals", template.opsForHash().values(key));  
	    return map;  
	}  
	
//	//切库
//	 public static RedisTemplate<String, Object> initRedis(Integer indexDb, RedisTemplate<String, Object> redisTemplate){
//	        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory(); 
//	        //redisConnectionFactory.setHostName(host);  
//	        //redisConnectionFactory.setPort(port); 
//	        redisConnectionFactory.setDatabase(indexDb);
//	        redisConnectionFactory.setPassword(password);
//	        redisConnectionFactory.afterPropertiesSet(); 
//	        redisTemplate.setConnectionFactory(redisConnectionFactory); 
//	        return redisTemplate;
//	    }
	
	//4.8 如果我们需要一个小型的消息中间件，可以选择redis的订阅/发布来实现。
//	public void subscribe(String channel) {  
//	    RedisMessageListenerContainer container = provider.getBean(RedisMessageListenerContainer.class);  
//	    MessageListenerAdapter listenerAdapter = provider.getBean(MessageListenerAdapter.class);  
//	    container.addMessageListener(listenerAdapter, new PatternTopic(channel));  
//	}  
//	  
//	public void publish(String channel, String message) throws InterruptedException {  
//	    CountDownLatch latch = provider.getBean(CountDownLatch.class);  
//	  
//	    log.info("Sending message...");  
//	    template.convertAndSend(channel, message);  
//	  
//	    latch.await();  
//	}  

}
