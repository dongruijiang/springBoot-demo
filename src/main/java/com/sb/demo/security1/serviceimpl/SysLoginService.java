package com.sb.demo.security1.serviceimpl;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.sb.demo.common.constant.Constants;
import com.sb.demo.common.exception.CustomException;
import com.sb.demo.common.exception.user.CaptchaException;
import com.sb.demo.common.exception.user.CaptchaExpireException;
import com.sb.demo.common.exception.user.UserPasswordNotMatchException;
import com.sb.demo.common.utils.MessageUtils;
//import com.ruoyi.framework.manager.AsyncManager;
//import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.sb.demo.framework.redis.RedisCache;
import com.sb.demo.framework.security1.LoginUser;
import com.sb.demo.framework.security1.SmsCodeAuthenticationToken;

/**
 * 登录校验方法
 * 
 * @date 2020年4月3日 下午11:59:49
 * @author jdr
 */
@Component
public class SysLoginService {
	@Autowired
	private TokenService tokenService;

	@Resource
	private AuthenticationManager authenticationManager;

	@Autowired
	private RedisCache redisCache;
	
	/**
	 * 登录验证--验证码---用户名---密码
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @param code  验证码
	 * @param uuid     唯一标识
	 * @return 结果
	 */
	public String login(String username, String password, String code, String uuid) {
		String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
		String captcha = redisCache.getCacheObject(verifyKey);
		redisCache.deleteObject(verifyKey);
		if (captcha == null) {
			// AsyncManager.me().execute(AsyncFactory.recordLogininfor(username,
			// Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
			throw new CaptchaExpireException();
		}
		//不区分大小写比较
		if (!code.equalsIgnoreCase(captcha)) {
			// AsyncManager.me().execute(AsyncFactory.recordLogininfor(username,
			// Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
			throw new CaptchaException();
		}
		// 用户验证
		Authentication authentication = null;
		try {
			// 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (Exception e) {
			//e.printStackTrace();
			if (e instanceof BadCredentialsException) {
				// AsyncManager.me().execute(AsyncFactory.recordLogininfor(username,
				// Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
				throw new UserPasswordNotMatchException();
			} else {
				// AsyncManager.me().execute(AsyncFactory.recordLogininfor(username,
				// Constants.LOGIN_FAIL, e.getMessage()));
				throw new CustomException(e.getMessage());
			}
		}
		// s AsyncManager.me().execute(AsyncFactory.recordLogininfor(username,
		// Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();
		// 生成token
		return tokenService.createToken(loginUser);
	}
	
	/**
	 * 登录验证--手机号---手机验证码
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @param code  验证码
	 * @param uuid     唯一标识
	 * @return 结果
	 */
	public String smsLogin(String phonenumber, String smsCode, String uuid) {
		String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
		String captcha = redisCache.getCacheObject(verifyKey);
		redisCache.deleteObject(verifyKey);
		if (captcha == null) {
			// AsyncManager.me().execute(AsyncFactory.recordLogininfor(username,
			// Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
			throw new CaptchaExpireException();
		}
		//不区分大小写比较
		if (!smsCode.equalsIgnoreCase(captcha)) {
			// AsyncManager.me().execute(AsyncFactory.recordLogininfor(username,
			// Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
			throw new CaptchaException();
		}
		// 用户验证--短信验证码验证
		Authentication authentication = null;
		try {
			// 该方法会去调用SmsUserDetailsServiceImpl.loadUserByUsername
			authentication = authenticationManager
					.authenticate(new SmsCodeAuthenticationToken(phonenumber));
		} catch (Exception e) {
			//e.printStackTrace();
			if (e instanceof BadCredentialsException) {
				// AsyncManager.me().execute(AsyncFactory.recordLogininfor(username,
				// Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
				throw new UserPasswordNotMatchException();
			} else {
				// AsyncManager.me().execute(AsyncFactory.recordLogininfor(username,
				// Constants.LOGIN_FAIL, e.getMessage()));
				throw new CustomException(e.getMessage());
			}
		}
		// s AsyncManager.me().execute(AsyncFactory.recordLogininfor(username,
		// Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();
		// 生成token
		return tokenService.createToken(loginUser);
	}
}
