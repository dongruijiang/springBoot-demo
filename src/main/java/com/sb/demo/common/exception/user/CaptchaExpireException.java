package com.sb.demo.common.exception.user;

/**
 * 验证码失效异常处理类
 * 
 * @date 2020年4月4日 上午12:11:53
 * @author jdr
 */
public class CaptchaExpireException extends UserException {
	private static final long serialVersionUID = 1L;

	public CaptchaExpireException() {
		super("user.jcaptcha.expire", null);
	}
}
