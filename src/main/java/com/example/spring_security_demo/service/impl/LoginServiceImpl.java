package com.example.spring_security_demo.service.impl;

import com.example.spring_security_demo.domain.LoginUser;
import com.example.spring_security_demo.domain.dto.VerifyUserDTO;
import com.example.spring_security_demo.service.LoginService;
import com.example.spring_security_demo.util.JwtUtils;
import com.example.spring_security_demo.util.RedisCache;
import com.example.spring_security_demo.util.ResultUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisCache redisCache;

    @Override
    public ResultUtils<Object> login(VerifyUserDTO verifyUserDTO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(verifyUserDTO.getUsername(), verifyUserDTO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登陆失败");
        }

        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        String id = String.valueOf(loginUser.getUser().getId());
        String jwt = JwtUtils.createJWT(id);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);

        redisCache.setCacheObject("login:" + id, loginUser);

        return ResultUtils.success("登陆成功", map);
    }
}
