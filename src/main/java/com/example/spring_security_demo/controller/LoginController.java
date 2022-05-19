package com.example.spring_security_demo.controller;

import com.example.spring_security_demo.domain.dto.VerifyUserDTO;
import com.example.spring_security_demo.service.LoginService;
import com.example.spring_security_demo.util.ResultUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginController {
    @Resource
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResultUtils<Object> login(@RequestBody VerifyUserDTO verifyUserDTO) {

        return loginService.login(verifyUserDTO);
    }
}
