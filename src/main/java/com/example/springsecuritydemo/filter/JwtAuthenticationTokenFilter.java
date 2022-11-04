package com.example.springsecuritydemo.filter;

import com.example.springsecuritydemo.model.LoginUser;
import com.example.springsecuritydemo.util.JwtUtils;
import com.example.springsecuritydemo.util.RedisCache;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Jwt 配置
 *
 * @author Tragoedia
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 解析token获取userId
        String userId;
        try {
            Claims claims = JwtUtils.parseJwt(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.getStackTrace();
            throw new JwtException("token非法");
        }

        // 查询redis获取user信息
        String redisKey = "login:" + userId;
        LoginUser loginUser = (LoginUser) redisCache.getCacheObject(redisKey);
        if (Objects.isNull(loginUser)) {
            throw new JwtException("用户未登录");
        }

        // 存入SecurityContextHolder
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        // 放行
        filterChain.doFilter(request, response);
    }
}
