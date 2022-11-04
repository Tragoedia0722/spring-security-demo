package com.example.springsecuritydemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Cors 跨域配置
 *
 * @author Tragoedia
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                // 路径
                .addMapping("/**")
                // 域名
                .allowedOriginPatterns("*")
                // cookies
                .allowCredentials(true)
                // 请求方式
                .allowedMethods("*")
                // 请求头
                .allowedHeaders("*")
                // 超时时间
                .maxAge(3600);
    }
}
