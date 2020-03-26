package com.sb.demo.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;

/**
 * redis--配置类
 * 
 * @date 2020年3月24日 下午10:02:09
 * @author jdr
 * @param <T>
 */
@Configuration
@EnableCaching  //开启缓存注解
public class RedisConfig extends CachingConfigurerSupport{
	
	@Bean
	@SuppressWarnings(value = { "unchecked", "rawtypes" })
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {

		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
		// 设置连接工厂
		redisTemplate.setConnectionFactory(connectionFactory);

		// 使用Fastjson2JsonRedisSerializer来序列化和反序列化redis的value值
		FastJson2JsonRedisSerializer fastjsonSerializer = new FastJson2JsonRedisSerializer(Object.class);

		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

		/**
		 * @deprecated Since 2.10 use
		 * enableDefaultTyping ---->  activateDefaultTyping
		 */
		// mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		
		// 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
		mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		// 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
		mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,ObjectMapper.DefaultTyping.NON_FINAL);
		fastjsonSerializer.setObjectMapper(mapper);
        
		// 使用StringRedisSerializer来序列化和反序列化redis的key值
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		//value序列化方式采用Fastjson工具
		redisTemplate.setValueSerializer(fastjsonSerializer);
		
		// 使用StringRedisSerializer来序列化和反序列化hash的key值
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // hash的value序列化方式采用Fastjson
        redisTemplate.setHashValueSerializer(fastjsonSerializer);
		redisTemplate.afterPropertiesSet();
		
		return redisTemplate;
	}
}