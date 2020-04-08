package com.sb.demo.framework.security1;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.sb.demo.framework.security1.filter.JwtAuthenticationTokenFilter;
import com.sb.demo.framework.security1.handle.AuthenticationEntryPointImpl;
import com.sb.demo.framework.security1.handle.LogoutSuccessHandlerImpl;
import com.sb.demo.security1.serviceimpl.SmsUserDetailsServiceImpl;
import com.sb.demo.security1.serviceimpl.UserDetailsServiceImpl;

/**
 * security 权限demo第一套
 * 
 * 该类是 Spring Security 的配置类，
 * 该类的三个注解分别是标识该类是:
 * 1. 配置类;
 * 2. 开启 Security 服务;
 * 3. 开启全局 Securtiy 注解;
 * 
 * @date 2020年3月27日 下午11:11:36
 * @author jdr
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   /**
     * 自定义用户认证逻辑--用户名 密码
     */
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    
    /**
     * token认证失败处理类
     */
    @Autowired
    private AuthenticationEntryPointImpl unauthorizedHandler;
    
    /**
     * 点击退出处理类
     */
    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;
    
    /**
     * token认证过滤器
     */
    @Autowired
    private JwtAuthenticationTokenFilter authenticationTokenFilter;
//    
//    @Bean
//    public SmsCodeAuthenticationProvider smsCodeAuthenticationProvider() {
//        return new SmsCodeAuthenticationProvider();
//    }
    @Autowired
    SmsCodeAuthenticationProvider smsCodeAuthenticationProvider;
    
    /**
     * 解决 无法直接注入 AuthenticationManager
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

	
    /**
     * 身份认证接口--1
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    	String psd = new BCryptPasswordEncoder().encode("123");
//    	System.out.println("________________________________________");
//    	System.out.println("________________________________________");
//    	System.out.println(psd);
//    	System.out.println("________________________________________");
//    	System.out.println("________________________________________");
    
    	/** 
    	 * 用户名-密码登录
    	 * 密码不加密认证  BCrypt
    	 */
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(BCryptPasswordEncoderIng());
        
        /** 密码不加密认证 */
//        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
//			
//			@Override
//			public boolean matches(CharSequence rawPassword, String encodedPassword) {
//				return encodedPassword.equals(rawPassword.toString());
//			}
//			
//			@Override
//			public String encode(CharSequence rawPassword) {
//				return rawPassword.toString();
//			}
//		});
        
        /**
         * 短信登录
         * 无需加密
         */
        auth.authenticationProvider(smsCodeAuthenticationProvider);
    }

	
    /**
     * 身份认证接口--2   1和2作用同等，具体意义未细研究
     */
//	@Autowired
//	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//		/** 
//		 * Security 5.X后，密码必须加密，不然会报错  ---passwordEncoder(new MyPasswordEncoder())
//		 * BCrypt 加密
//		 * .authorities("read") /不能同时存在  .roles("ADMIN")
//		 * 
//		 */ 
//		String psd = new BCryptPasswordEncoder().encode("123");
//		auth.inMemoryAuthentication().passwordEncoder(BCryptPasswordEncoderIng())
//			.withUser("admin").password(psd).roles("ROLE_ADMIN");
//		auth.inMemoryAuthentication().passwordEncoder(BCryptPasswordEncoderIng())
//			.withUser("user").password(psd).roles("ROLE_USER");
//	}

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    	httpSecurity
    	//.cors().and() //跨域问题
    	// CRSF禁用，因为不使用session
        .csrf().disable()
        // token认证失败处理类
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
        /** 
         * spring security配置中禁用session
         *  本系统基于token认证，所以不需要session
         *  前后端分离
         */ 
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        
        // 过滤请求
        .authorizeRequests()
        /** 允许无权限访问配置 */
        .antMatchers("/captchaImage").permitAll() //验证码访问
        .antMatchers("/s1/doLogin").permitAll() //登录验证方法--用户名、密码
        
        .antMatchers("/s1/doSmsLogin").permitAll() //登录验证方法-- 手机号、手机验证码
        
        .antMatchers("/s1/login").permitAll() //进入登录页面--测试用
       
        
//        // 对于登录login 验证码captchaImage 允许匿名访问
       // .antMatchers("/login", "/captchaImage").anonymous()
//        .antMatchers(
//                HttpMethod.GET,
//                "/*.html",
//                "/favicon.ico",
//                "/**/*.html",
//                "/**/*.css",
//                "/**/*.js"
//        ).permitAll()
//        .antMatchers("/profile/**").anonymous()
//        .antMatchers("/common/download**").anonymous()
//        .antMatchers("/swagger-ui.html").anonymous()
//        .antMatchers("/swagger-resources/**").anonymous()
//        .antMatchers("/webjars/**").anonymous()
//        .antMatchers("/*/api-docs").anonymous()
//        .antMatchers("/druid/**").anonymous()
        // 除上面外的所有请求全部需要鉴权认证
        .anyRequest().authenticated()
        
        /** 用户登录等 处理方法 
         * 本处为前后端分离，故注释掉
         */
//        .and()
//        .formLogin()  //开启登录
//        .loginPage("/s1/login")// 指定登录请求的 url
//        .defaultSuccessUrl("/s1/") // 登录成功后的 url
//        //.failureUrl("/s1/error") // 登录失败Url ---1 
//        .permitAll()//别所有权限
    	
        /** 用户退出 处理方法 
         * 配置自定义方法后注释掉 
         */
//    	.and()
//    	.logout()  //开启注销
//        .logoutUrl("/logout") //指定注销请求的 url (default is "/logout"). 
//        .logoutSuccessUrl("/s1/login") // 注销成功后的 url
//        .permitAll()
        
        // 自动登录 方法1
        //.and().rememberMe();//记住我，下次自动登录 ,Cookie存储法配置,只需要这一步；
    	
    	// 自动登录 方法2 Cookie只存ID
//        .and().rememberMe()
//        .tokenRepository(persistentTokenRepository())
//            // Cookie失效时间 有效时间：单位s
//        .tokenValiditySeconds(60)
//        .userDetailsService(userDetailsService);
    	;
    	 /**
     	 * 添加JWT filter , tosken认证
     	 * 在security的第一道验证UsernamePassword前先进行过滤
     	 */
         httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
         /** 自定义 用户退出 登录处理类 */
         httpSecurity.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
      
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/css/**", "/js/**","/favicon.ico");
    }
    
    /**
     * 强散列哈希加密实现
     * BCrypt 加密
     */
    @Bean
    public BCryptPasswordEncoder BCryptPasswordEncoderIng() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * 使用 remember-me ，数据库存储方式使用到
     * 注入dataSource ，创建一个 PersistentTokenRepository 的Bean
     * remember-me --- dataSource --- bg
     */
	@Autowired
	private DataSource dataSource;

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		// 如果token表不存在，使用下面语句可以初始化该表；若存在，请注释掉这条语句，否则会报错。
		//persistent_logins Security默认表名
		//tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}
     /**  remember-me --- dataSource --- end */
    
}
