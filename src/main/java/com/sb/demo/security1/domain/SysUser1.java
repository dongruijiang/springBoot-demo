package com.sb.demo.security1.domain;

import java.io.Serializable;
import java.util.List;

/**
 * security 权限demo第一套
 * 
 * @date 2020年3月27日 下午11:11:36
 * @author jdr
 */
public class SysUser1 implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String userName;

	private String password;
	
	private String phonenumber;
	
	/** 角色对象 */
    private List<SysRole1> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public boolean isAdmin()
    {
        return isAdmin(this.id);
    }

    public static boolean isAdmin(Integer id)
    {
        return id != null && 1 == id;
    }

	public List<SysRole1> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole1> roles) {
		this.roles = roles;
	}
}
