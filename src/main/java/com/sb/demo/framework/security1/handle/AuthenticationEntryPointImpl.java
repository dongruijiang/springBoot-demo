package com.sb.demo.framework.security1.handle;

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.sb.demo.common.constant.HttpStatus;
import com.sb.demo.common.utils.ServletUtils;
import com.sb.demo.common.utils.StringUtils;
import com.sb.demo.framework.web.domain.AjaxResult;

/**
 * 登录后 使用token认证失败后的异常类
 * security 认证失败处理类 返回未授权
 * 
 * Headers中不包含 Authorization Bearer token
 * 
 * @date 2020年4月3日 下午2:17:51
 * @author jdr
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable
{													 
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException
    {
        int code = HttpStatus.UNAUTHORIZED;
        String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(code, msg)));
    }
}
