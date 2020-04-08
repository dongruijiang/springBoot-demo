package com.sb.demo.security1.serviceimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sb.demo.common.utils.StringUtils;
import com.sb.demo.framework.security1.LoginUser;
import com.sb.demo.security1.domain.SysRole1;
import com.sb.demo.security1.domain.SysUser1;
import com.sb.demo.security1.domain.SysUserRole1;
import com.sb.demo.security1.service.ISysRole1Service;
import com.sb.demo.security1.service.ISysUser1Service;
import com.sb.demo.security1.service.ISysUserRole1Service;

/**
 * security 权限demo第一套
 * 用户验证处理
 * 实现security 的 UserDetailsService 接口
 * 
 * @date 2020年3月27日 下午11:11:36
 * @author jdr
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
    @Autowired
    ISysUser1Service userService;  //用户

    @Autowired
    ISysRole1Service roleService;  //角色

    @Autowired
    ISysUserRole1Service userRoleService;  //用户角色对应
    
    @Autowired
    private SysPermissionService permissionService;
    
  	/**
  	 * 根据用户名查找用户
  	 */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  	
    	//GrantedAuthority 授予
    	Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 从数据库中取出用户信息
        SysUser1 user = userService.selectUserByUserName(username);
        //判断用户是否存在
        if (StringUtils.isNull(user)) {
              log.info("登录用户：{} 不存在.", username);
              throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        
        // 添加角色权限--用户角色对应
        List<SysUserRole1> userRoles = userRoleService.listByUserId(user.getId());
        for (SysUserRole1 userRole : userRoles) {
            SysRole1 role = roleService.selectById(userRole.getRoleId());
            authorities.add(new SimpleGrantedAuthority(role.getName()));//授予用户角色权限
        }

        // 返回UserDetails实现类
       // return new User(user.getUserName(), user.getPassword(), authorities);
        
        return createLoginUser(user);
    }

	public UserDetails createLoginUser(SysUser1 user) {
		return new LoginUser(user, permissionService.getMenuPermission(user));
	}
}