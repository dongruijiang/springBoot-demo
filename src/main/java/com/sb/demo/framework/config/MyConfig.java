package com.sb.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 读取 application.yml 配置文件中内容
 * 一个javabean类
 * 
 * 需要加个注解@ConfigurationProperties，并加上它的prrfix。
 * 另外@Component可加可不加。
 * 另外spring-boot-configuration-processor依赖可加可不加，具体原因不详。
 * 
 * 另外需要在应用类或者application类，加EnableConfigurationProperties注解。
 * @EnableConfigurationProperties({MyConfig.class})
 * 
 * @date 2020年3月21日 下午4:47:26
 * @author jdr
 */
//@Component
@ConfigurationProperties(prefix = "my")
public class MyConfig {
	
	private String name;
	private String version;
	private String copyrightYear;
	private String uuid;
	private String greeting;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getCopyrightYear() {
		return copyrightYear;
	}
	public void setCopyrightYear(String copyrightYear) {
		this.copyrightYear = copyrightYear;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getGreeting() {
		return greeting;
	}
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	
	
}
