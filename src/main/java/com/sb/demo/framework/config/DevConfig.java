package com.sb.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 读取 application-dev.yml 配置文件中内容
 * 
 * 一个javabean类
 * 
 * 需要加个注解@ConfigurationProperties，并加上它的prrfix。
 * 另外@Component可加可不加。
 * 另外spring-boot-configuration-processor依赖可加可不加，具体原因不详。
 * 
 * 另外需要在应用类或者application类，加EnableConfigurationProperties注解。
 * @EnableConfigurationProperties({DevConfig.class})
 * 
 * @date 2020年3月21日 下午5:44:13
 * @author jdr
 */
//@Component
@ConfigurationProperties(prefix = "dev")
public class DevConfig {
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
