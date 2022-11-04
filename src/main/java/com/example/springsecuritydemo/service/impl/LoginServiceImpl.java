package com.example.springsecuritydemo.service.impl;

import com.example.springsecuritydemo.exception.SystemException;
import com.example.springsecuritydemo.model.LoginUser;
import com.example.springsecuritydemo.model.dto.VerifyUserDTO;
import com.example.springsecuritydemo.service.LoginService;
import com.example.springsecuritydemo.util.JwtUtils;
import com.example.springsecuritydemo.util.RedisCache;
import com.example.springsecuritydemo.model.Result;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 登陆业务实现
 *
 * @author Tragoedia
 */
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
            throw new SystemException("登陆失败");
        }

        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        String id = String.valueOf(loginUser.getUser().getId());
        String jwt = JwtUtils.createJwt(id);
        Map<String, String> map = new HashMap<>(1);
        map.put("token", jwt);

        redisCache.setCacheObject("login:" + id, loginUser);

        return Result.success("登陆成功", map);
    }
}
