package com.sb.demo.security1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sb.demo.security1.domain.SysRole1;

/**
 * security 权限demo第一套
 * 
 * @date 2020年3月27日 下午11:11:36
 * @author jdr
 */
@Mapper
public interface SysRole1Mapper {
	
	@Select("SELECT * FROM sys_role1 WHERE id = #{id}")
    SysRole1 selectById(Integer id);
	
	 /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 角色列表
     */
	@Select("SELECT * FROM sys_role1 WHERE id = #{id}")
    public List<SysRole1> selectRolePermissionByUserId(Integer userId);
}
