package com.example.springSecurityDemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 路径
                .allowedOriginPatterns("*") // 域名
                .allowCredentials(true) // cookies
                .allowedMethods("*") // 请求方式
                .allowedHeaders("*") // 请求头
                .maxAge(3600); // 超时时间
    }
}
