package com.example.springSecurityDemo.service.impl;

import com.example.springSecurityDemo.domain.LoginUser;
import com.example.springSecurityDemo.domain.dto.VerifyUserDTO;
import com.example.springSecurityDemo.service.LoginService;
import com.example.springSecurityDemo.util.JwtUtils;
import com.example.springSecurityDemo.util.RedisCache;
import com.example.springSecurityDemo.domain.Result;
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
    public Result<Object> login(VerifyUserDTO verifyUserDTO) {
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

        return Result.success("登陆成功", map);
    }
}
