package com.sb.demo.security1.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sb.demo.security1.serviceimpl.SysLoginService;
import com.sb.demo.common.constant.Constants;
import com.sb.demo.domain.result;
import com.sb.demo.framework.web.controller.BaseController;
import com.sb.demo.framework.web.domain.AjaxResult;

/**
 * security 权限demo第一套
 * 
 * @date 2020年3月27日 下午11:11:36
 * @author jdr
 */
@Controller
@RequestMapping("/s1")
public class LoginController extends BaseController{
	
	 @Autowired
	 private SysLoginService loginService;
	
	/**
	 * 登录成功后跳转的页面
	 * @return
	 */
	@RequestMapping("/")
	@PreAuthorize("@ss.hasPermi('tool:gen:home')")
    public String showHome() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("当前登陆用户：" + name);
        return "security1/home";
    }
	/**
	 * 进入登录页面
	 * @return
	 */
    @RequestMapping("/login")
    public String showLogin() {
    	log.info("/login.html");
        return "security1/login";
    }
    
    /**
     * 登录方法--用户名密码--提交表单
     * @param username
     * @param password
     * @param code
     * @param uuid
     * @return
     */
    @PostMapping("/doLogin")
    @ResponseBody
    public AjaxResult doLogin(String username, String password, String code, String uuid) {
    	log.info("post - doLogin");
    	AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(username, password, code, uuid);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }
    
    /**
     * 登录方法--短信验证码登录--提交表单
     * @param username
     * @param password
     * @param code
     * @param uuid
     * @return
     */
    @PostMapping("/doSmsLogin")
    @ResponseBody
    public AjaxResult doSmsLogin(String phonenumber, String smsCode, String smsUuid) {
    	log.info("post - doLogin");
    	AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.smsLogin(phonenumber, smsCode, smsUuid);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }
    
    @RequestMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public result printAdmin() {
        return new result("200", "如果你看见这句话，说明你有ROLE_ADMIN角色!");
    }

    @RequestMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseBody
    public result printUser() {
        return new result("200", "如果你看见这句话，说明你有ROLE_USER角色!");
    }
    
    @RequestMapping("/error")
    @ResponseBody
    public result loginError(HttpServletRequest request) {
    	AuthenticationException exception =
    			(AuthenticationException) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        return new result("401", "用户名或密码错误！"+exception.toString());
    }
    
    @RequestMapping("/admin1")
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
   // @PreAuthorize("hasPermi('tool:gen:list')")
    @PreAuthorize("@ss.hasPermi('tool:gen:list')")
    @ResponseBody
    public result printAdmin1() {
        return new result("200", "jdr如果你看见这句话，说明你有ROLE_ADMIN角色!");
    }
}
