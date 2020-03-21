package com.sb.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 读取 application.yml 配置文件中内容
 * @date 2020年3月21日 下午4:25:55
 * @author jdr
 */
@RestController
public class MyController {
	
	@Value("${my.name}")
	private String name;
	@Value("${my.version}")
	private String version;
	
	@RequestMapping(value = "/my")
	public String my() {
		return name+";"+version;
	}

}
