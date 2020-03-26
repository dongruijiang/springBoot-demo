package com.sb.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sb.demo.framework.config.DevConfig;
import com.sb.demo.framework.config.DevUserConfig;
import com.sb.demo.framework.config.MyConfig;

/**
 * 通过实体读取配置文件中的信息
 * 
 * 另外需要在应用类或者application类，加EnableConfigurationProperties注解。
 * @EnableConfigurationProperties({MyConfig.class})
 * 
 * @date 2020年3月21日 下午4:57:15
 * @author jdr
 */
@RestController
@EnableConfigurationProperties({MyConfig.class,DevUserConfig.class,DevConfig.class})
public class MyConfigController {
	
	@Autowired
	MyConfig myconfig;
	
	@Autowired
	DevUserConfig devUserConfig;
	
	@Autowired
	DevConfig devConfig;
	
	/**
	 * 读取yml文件
	 * @return
	 */
	@RequestMapping(value = "/myConfigStr")
	public String myConfigStr() {
		StringBuilder str = new StringBuilder();
		str.append(">>Name:").append(myconfig.getName())
		   .append(">>Version:").append(myconfig.getVersion())
		   .append(">>CopyrightYear:").append(myconfig.getCopyrightYear())
		   .append(">>Uuid:").append(myconfig.getUuid())
		   .append(">>Greeting:").append(myconfig.getGreeting());
		return str.toString();
	}
	/**
	 * 读取yml文件
	 * @return MyConfig -json
	 */
	@RequestMapping(value = "/myConfig")
	public MyConfig myConfig() {
		return myconfig;
	}
	
	/**
	 * 读取properties文件
	 * @return devUserConfig -json
	 */
	@RequestMapping(value = "/devUserConfigstr")
	public String devUserConfigstr() {
		StringBuilder str = new StringBuilder();
		str.append(">>Uname:").append(devUserConfig.getUname())
		   .append(">>Age:").append(devUserConfig.getAge());
		return str.toString();
	}
	
	/**
	 * 读取properties文件
	 * @return devUserConfig -json
	 */
	@RequestMapping(value = "/devUserConfig")
	public DevUserConfig devUserConfig() {
		DevUserConfig df = new DevUserConfig();
		df.setUname(devUserConfig.getUname());
		df.setAge(devUserConfig.getAge());
		return df;
	}
	
	/**
	 * 读取yml文件
	 * @return devConfig -json
	 */
	@RequestMapping(value = "/devConfigstr")
	public String devConfigstr() {
		StringBuilder str = new StringBuilder();
		str.append(">>Uname:").append(devConfig.getUname())
		   .append(">>Age:").append(devConfig.getAge());
		return str.toString();
	}
	
	/**
	 * 读取yml文件
	 * @return devConfig -json
	 */
	@RequestMapping(value = "/devConfig")
	public DevConfig devConfig() {
		DevConfig df = new DevConfig();
		df.setUname(devConfig.getUname());
		df.setAge(devConfig.getAge());
		return df;
	}
}
