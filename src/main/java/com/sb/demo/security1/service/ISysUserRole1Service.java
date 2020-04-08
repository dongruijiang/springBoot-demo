package com.sb.demo.security1.service;

import java.util.List;

import com.sb.demo.security1.domain.SysUserRole1;

/**
 * security 权限demo第一套
 * 
 * @date 2020年3月27日 下午11:11:36
 * @author jdr
 */
public interface ISysUserRole1Service{
	
	List<SysUserRole1> listByUserId(Integer userId);
}