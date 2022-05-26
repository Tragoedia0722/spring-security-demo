package com.example.spring_security_demo.controller;

import com.example.spring_security_demo.domain.LoginUser;
import com.example.spring_security_demo.domain.dto.VerifyUserDTO;
import com.example.spring_security_demo.service.LoginService;
import com.example.spring_security_demo.util.RedisCache;
import com.example.spring_security_demo.util.ResultUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginController {
    @Resource
    private LoginService loginService;
    @Resource
    private RedisCache redisCache;

    @PostMapping("/user/login")
    public ResultUtils<Object> login(@RequestBody VerifyUserDTO verifyUserDTO) {

        return loginService.login(verifyUserDTO);
    }

    @GetMapping("/user/logout")
    public ResultUtils<Object> logout() {
        // 获取userId
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        // 删除redis信息
        redisCache.deleteObject("login:" + userId);
        return ResultUtils.success("注销成功！");
    }
}
