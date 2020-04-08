package com.sb.demo.security1.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.demo.security1.dao.SysUser1Mapper;
import com.sb.demo.security1.domain.SysUser1;
import com.sb.demo.security1.service.ISysUser1Service;

/**
 * security 权限demo第一套
 * 
 * @date 2020年3月27日 下午11:11:36
 * @author jdr
 */
@Service
public class SysUser1ServiceImpl implements ISysUser1Service{
	@Autowired
    SysUser1Mapper userMapper;
	
	public SysUser1 selectUserById(Integer id) {
		return userMapper.selectUserById(id);
	}

    public SysUser1 selectUserByUserName(String name) {
		return userMapper.selectUserByUserName(name);
    }
    
    public SysUser1 selectUserByPhoneNumber(String phonenumber) {
    	return userMapper.selectUserByPhoneNumber(phonenumber);
    }
}