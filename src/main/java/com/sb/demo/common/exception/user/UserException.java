package com.sb.demo.common.exception.user;

import com.sb.demo.common.exception.BaseException;

/**
 * 用户信息异常类---用户异常基类
 * 
 * @date 2020年4月4日 上午12:07:09
 * @author jdr
 */
public class UserException extends BaseException {
	
	private static final long serialVersionUID = 1L;
	
	public UserException(String code, Object[] args) {
		super("user", code, args, null);
	}


}
