package com.sb.demo.security1.service;

import java.util.Set;

import com.sb.demo.security1.domain.SysRole1;

/**
 * security 权限demo第一套
 * 
 * @date 2020年3月27日 下午11:11:36
 * @author jdr
 */
public interface ISysRole1Service {

	 SysRole1 selectById(Integer id);
	 /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
     Set<String> selectRolePermissionByUserId(Integer userId);
}
