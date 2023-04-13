package com.qtummatrix.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 是否允许携带 cookie
                .allowCredentials(true)
                // 设置允许跨域请求的域名
                .allowedOrigins("*")
                // 设置允许请求类型
                .allowedMethods("GET","POST","PUT","DELETE","OPTION")
                // 设置允许的 header 属性
                .allowedHeaders("*")
                // 设置客户端缓存预检请求时间间隔
                .maxAge(3600);

    }
}
