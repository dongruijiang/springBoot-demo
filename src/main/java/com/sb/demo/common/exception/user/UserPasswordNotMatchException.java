package com.sb.demo.common.exception.user;

/**
 * 用户名、密码不正确异常处理类
 * 
 * 用户密码不正确或不符合规范异常类 ？？
 * 
 * @date 2020年4月4日 上午12:13:05
 * @author jdr
 */
public class UserPasswordNotMatchException extends UserException {
	private static final long serialVersionUID = 1L;

	public UserPasswordNotMatchException() {
		super("user.password.not.match", null);
	}
}
