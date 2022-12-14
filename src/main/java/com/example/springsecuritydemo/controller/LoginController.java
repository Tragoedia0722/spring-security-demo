package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.model.LoginUser;
import com.example.springsecuritydemo.model.dto.VerifyUserDTO;
import com.example.springsecuritydemo.service.LoginService;
import com.example.springsecuritydemo.util.RedisCache;
import com.example.springsecuritydemo.model.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 登陆控制器
 *
 * @author Tragoedia
 */
@RestController
public class LoginController {
    @Resource
    private LoginService loginService;
    @Resource
    private RedisCache redisCache;

    /**
     * 登录
     *
     * @param verifyUserDTO 用户验证DTO
     * @return Result
     */
    @PostMapping("/user/login")
    public Result<Object> login(@RequestBody VerifyUserDTO verifyUserDTO) {
        return loginService.login(verifyUserDTO);
    }

    /**
     * 注销
     *
     * @return Result
     */
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
