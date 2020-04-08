package com.sb.demo.security1.service;

import com.sb.demo.security1.domain.SysUser1;

/**
 * security 权限demo第一套
 * 
 * @date 2020年3月27日 下午11:11:36
 * @author jdr
 */
public interface ISysUser1Service{
	
	SysUser1 selectUserById(Integer id);

    SysUser1 selectUserByUserName(String name) ;
    
    SysUser1 selectUserByPhoneNumber(String phonenumber);
}