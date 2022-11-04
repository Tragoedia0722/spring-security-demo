package com.example.springsecuritydemo.handler;

import com.example.springsecuritydemo.model.Result;
import com.example.springsecuritydemo.util.ResponseUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录异常处理
 *
 * @author Tragoedia
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        ResponseUtils.write(response, Result.unAuthorized());
    }
}
