package com.sb.demo.redis.dao;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * 测试redis一些功能- dao
 * @date 2020年3月23日 下午9:34:31
 * @author jdr
 */
@Repository
public class RedisDao {
	
	//使用StringRedisTemplate必须 <String,String>
	@Autowired
	StringRedisTemplate strRedisTemplate;
	
	public void set(String key,String value) {
		ValueOperations<String, String> ops = strRedisTemplate.opsForValue();
		ops.set(key, value, 1, TimeUnit.MINUTES);//1分钟过期
	}
	
	public String get(String key) {
		ValueOperations<String, String> ops = strRedisTemplate.opsForValue();
		return ops.get(key);
	}
}
