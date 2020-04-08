package com.sb.demo.security1.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.sb.demo.security1.domain.SysUser1;

/**
 * security 权限demo第一套
 * 
 * @date 2020年3月27日 下午11:11:36
 * @author jdr
 */
@Mapper
public interface SysUser1Mapper {
	
	@Select("SELECT * FROM sys_user1 WHERE id = #{id}")
    @Results(id="sysUser1Map",value= {
    		@Result(id=true,property="id",column="id"),
    		@Result(property="userName",column="user_name"),
    		@Result(property="password",column="password"),
    		@Result(property="phonenumber",column="phonenumber"),
    })
	SysUser1 selectUserById(Integer id);

    @Select("SELECT * FROM sys_user1 WHERE user_name = #{name}")
    @ResultMap(value = "sysUser1Map") //引用selectUserById中的ID
    SysUser1 selectUserByUserName(String name);
    
    @Select("SELECT * FROM sys_user1 WHERE phonenumber = #{phonenumber}")
    @ResultMap(value = "sysUser1Map") //引用selectUserById中的ID
    SysUser1 selectUserByPhoneNumber(String phonenumber);
}
