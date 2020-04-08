package com.sb.demo.security1.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sb.demo.security1.domain.SysUserRole1;

/**
 * security 权限demo第一套
 * 
 * @date 2020年3月27日 下午11:11:36
 * @author jdr
 */
@Mapper
public interface SysUserRole1Mapper {
	
	 @Select("SELECT user_id as userId,role_id as roleId FROM sys_user_role1 WHERE user_id = #{userId}")
	 List<SysUserRole1> listByUserId(@Param("userId") Integer userId);
	 
}
