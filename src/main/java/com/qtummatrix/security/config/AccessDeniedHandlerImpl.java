package com.qtummatrix.security.config;

import com.qtummatrix.security.utils.JsonUtil;
import com.qtummatrix.security.utils.WebUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义授权失败配置
 */

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException, ServletException {

        String result = JsonUtil.JsonSerializer(HttpStatus.FORBIDDEN.value(), 0, "权限不足");
        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
        WebUtil.renderString(httpServletResponse, result);
    }
}
