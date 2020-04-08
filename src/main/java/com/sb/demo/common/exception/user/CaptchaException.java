package com.sb.demo.common.exception.user;

/**
 * 验证码错误异常处理类
 * 
 * @date 2020年4月4日 上午12:09:21
 * @author jdr
 */
public class CaptchaException extends UserException {
    private static final long serialVersionUID = 1L;

    public CaptchaException()
    {
        super("user.jcaptcha.error", null);
    }
}
