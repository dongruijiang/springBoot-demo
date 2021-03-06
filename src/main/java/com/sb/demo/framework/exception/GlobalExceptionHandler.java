package com.sb.demo.framework.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.sb.demo.common.constant.HttpStatus;
import com.sb.demo.common.exception.BaseException;
import com.sb.demo.common.exception.CustomException;
import com.sb.demo.common.utils.StringUtils;
import com.sb.demo.framework.web.domain.AjaxResult;

/**
 * 全局异常拦截器
 * 
 * @date 2020年3月26日 下午4:48:47
 * @author jdr
 */
//@ControllerAdvice
//@ResponseBody
@RestControllerAdvice //--@RestControllerAdvice = @ControllerAdvice + @ResponseBody
public class GlobalExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 自定义异常--基础异常
     */
    @ExceptionHandler(BaseException.class)
    public AjaxResult baseException(BaseException e)
    {
    	 //log.error(e.getMessage(), e);
        return AjaxResult.error(e.getMessage());
    }

    /**
     *  自定义异常-- 业务异常
     */
    @ExceptionHandler(CustomException.class)
    public AjaxResult businessException(CustomException e)
    {
    	 //log.error(e.getMessage(), e);
        if (StringUtils.isNull(e.getCode()))
        {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.error(e.getCode(), e.getMessage());
    }
    
    /**
     *  如静态资源类无法获取
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public AjaxResult handlerNoFoundException(Exception e)
    {
        log.error(e.getMessage(), e);
        return AjaxResult.error(HttpStatus.NOT_FOUND, "路径不存在，请检查路径是否正确");
    }
    
    /**
     *  spring security的权限异常
     */
    @ExceptionHandler(AccessDeniedException.class)
    public AjaxResult handleAuthorizationException(AccessDeniedException e)
    {
        log.error(e.getMessage());
        return AjaxResult.error(HttpStatus.FORBIDDEN, "没有权限，请联系管理员授权");
    }
    
    /**
     *  spring security的帐户失效时异常
     */				   
    @ExceptionHandler(AccountExpiredException.class)
    public AjaxResult handleAccountExpiredException(AccountExpiredException e)
    {
        log.error(e.getMessage(), e);
        return AjaxResult.error(e.getMessage());
    }
    
    /**
     * spring security-用户名不存在
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public AjaxResult handleUsernameNotFoundException(UsernameNotFoundException e)
    {
        log.error(e.getMessage(), e);
        return AjaxResult.error(e.getMessage());
    }
    
    /**
     *  其他类别异常--不太好细分类
     */
    @ExceptionHandler(Exception.class)
    public AjaxResult handleException(Exception e)
    {
        log.error(e.getMessage(), e);
        return AjaxResult.error(e.getMessage());
    }

    /**
     * 自定义验证异常：如表单数据
     */
    @ExceptionHandler(BindException.class)
    public AjaxResult validatedBindException(BindException e)
    {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return AjaxResult.error(message);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object validExceptionHandler(MethodArgumentNotValidException e)
    {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return AjaxResult.error(message);
    }
}
