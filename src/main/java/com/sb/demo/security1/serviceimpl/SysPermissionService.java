package com.sb.demo.security1.serviceimpl;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sb.demo.security1.domain.SysUser1;
import com.sb.demo.security1.service.ISysRole1Service;


/**
 * 用户权限处理
 * 
 * @date 2020年4月4日 下午1:59:24
 * @author jdr
 */
@Component
public class SysPermissionService
{
    @Autowired
    private ISysRole1Service roleService;

   // @Autowired
   // private ISysMenuService menuService;

    /**
     * 获取角色数据权限
     * 
     * @param user 用户信息
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(SysUser1 user)
    {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin())
        {
            roles.add("admin");
            roles.add("ROLE_ADMIN");
        }
        else
        {
            roles.addAll(roleService.selectRolePermissionByUserId(user.getId()));
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     * 
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(SysUser1 user)
    {
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin())
        {
            perms.add("*:*:*");
        }
        else
        {
            //perms.addAll(menuService.selectMenuPermsByUserId(user.getUserId()));
        }
        return perms;
    }
}
