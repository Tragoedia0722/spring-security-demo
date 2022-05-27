package com.example.springSecurityDemo.controller;

import com.example.springSecurityDemo.domain.LoginUser;
import com.example.springSecurityDemo.domain.dto.VerifyUserDTO;
import com.example.springSecurityDemo.service.LoginService;
import com.example.springSecurityDemo.util.RedisCache;
import com.example.springSecurityDemo.domain.Result;
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
    public Result<Object> login(@RequestBody VerifyUserDTO verifyUserDTO) {

        return loginService.login(verifyUserDTO);
    }

    @GetMapping("/user/logout")
    public Result<Object> logout() {
        // 获取userId
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        // 删除redis信息
        redisCache.deleteObject("login:" + userId);
        return Result.success("注销成功！");
    }
}
