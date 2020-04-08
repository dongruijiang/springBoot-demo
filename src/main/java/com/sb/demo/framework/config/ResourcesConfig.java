package com.sb.demo.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 通用配置 --资源路径跳转等
 * 
 * @date 2020年3月28日 下午11:28:33
 * @author jdr
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) { 
		
		/** 静态资源路径配置 */
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

		/** swagger配置 */
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}