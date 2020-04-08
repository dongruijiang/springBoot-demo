package com.sb.demo.security1.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.demo.security1.dao.SysUserRole1Mapper;
import com.sb.demo.security1.domain.SysUserRole1;
import com.sb.demo.security1.service.ISysUserRole1Service;

/**
 * security 权限demo第一套
 * 
 * @date 2020年3月27日 下午11:11:36
 * @author jdr
 */
@Service
public class SysUserRole1ServiceImpl implements ISysUserRole1Service{
	
	@Autowired
    private SysUserRole1Mapper userRoleMapper;
	
	public List<SysUserRole1> listByUserId(Integer userId){
		return userRoleMapper.listByUserId(userId);
	}
}