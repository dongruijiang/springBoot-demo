package com.sb.demo.security1.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sb.demo.common.utils.StringUtils;
import com.sb.demo.framework.security1.LoginUser;
import com.sb.demo.security1.domain.SysUser1;
import com.sb.demo.security1.service.ISysUser1Service;

/**
 * security 权限demo第一套
 * 用户验证处理---短信验证码
 * 实现security 的 UserDetailsService 接口
 * 
 * @date 2020年3月27日 下午11:11:36
 * @author jdr
 */
@Service
public class SmsUserDetailsServiceImpl implements UserDetailsService {
	
	private static final Logger log = LoggerFactory.getLogger(SmsUserDetailsServiceImpl.class);
	
    @Autowired
    ISysUser1Service userService;  //用户
    
    @Autowired
    private SysPermissionService permissionService;
    
  	/**
  	 * 根据手机号码查找用户
  	 */
    @Override
    public UserDetails loadUserByUsername(String phonenumber) throws UsernameNotFoundException {
  	
        // 从数据库中取出用户信息
        SysUser1 user = userService.selectUserByPhoneNumber(phonenumber);
        //判断用户是否存在
        if (StringUtils.isNull(user)) {
              log.info("登录手机号：{} 不存在.", phonenumber);
              throw new UsernameNotFoundException("登录手机号：" + phonenumber + " 不存在");
        }
        
        return createLoginUser(user);
    }

	public UserDetails createLoginUser(SysUser1 user) {
		return new LoginUser(user, permissionService.getMenuPermission(user));
	}
}