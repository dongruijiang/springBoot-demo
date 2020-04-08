package com.sb.demo.security1.serviceimpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.demo.common.utils.StringUtils;
import com.sb.demo.security1.dao.SysRole1Mapper;
import com.sb.demo.security1.domain.SysRole1;
import com.sb.demo.security1.service.ISysRole1Service;

/**
 * security 权限demo第一套
 * 
 * @date 2020年3月27日 下午11:11:36
 * @author jdr
 */
@Service
public class SysRole1ServiceImpl implements ISysRole1Service{
	
	@Autowired
    SysRole1Mapper roleMapper;
	
	@Override
	public SysRole1 selectById(Integer id) {
		return roleMapper.selectById(id);
	}
	
	 /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectRolePermissionByUserId(Integer userId)
    {
        List<SysRole1> perms = roleMapper.selectRolePermissionByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (SysRole1 perm : perms)
        {
            if (StringUtils.isNotNull(perm))
            {
                permsSet.addAll(Arrays.asList(perm.getName().trim().split(",")));
            }
        }
        return permsSet;
    }
}
