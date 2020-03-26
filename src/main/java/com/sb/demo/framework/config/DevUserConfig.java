package com.sb.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 读取 dev-user.properties 配置文件中内容
 * 一个javabean类
 * 
 * 需要加个注解@@Configuration
 * 需要加个注解@PropertySource(value = "classpath:dev-user.properties"),并加上文件路径
 * 需要加个注解@ConfigurationProperties，并加上它的prrfix。
 * 
 * @Configuration用于定义配置类，可替换xml配置文件，被注解的类内部包含有一个或多个被@Bean注解的方法，
 * 这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类
 * 进行扫描，并用于构建bean定义，初始化Spring容器。
 * 
 * @date 2020年3月21日 下午5:44:13
 * @author jdr
 */
@Configuration
@PropertySource(value = "classpath:dev-user.properties")
@ConfigurationProperties(prefix = "dev-user")
public class DevUserConfig {
	private String uname;
	private int age;
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
