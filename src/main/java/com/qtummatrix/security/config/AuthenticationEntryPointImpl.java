package com.qtummatrix.security.config;

import com.qtummatrix.security.utils.JsonUtil;
import com.qtummatrix.security.utils.WebUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义认证失败配置
 */

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {

        String result = JsonUtil.JsonSerializer(
                HttpStatus.UNAUTHORIZED.value(), 0, "认证失败，重新登陆");
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        WebUtil.renderString(httpServletResponse, result);
    }
}
