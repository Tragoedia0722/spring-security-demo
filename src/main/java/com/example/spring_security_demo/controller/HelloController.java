package com.example.spring_security_demo.controller;

import com.example.spring_security_demo.util.ResultUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('test')")
    public ResultUtils<Object> hello() {
        return ResultUtils.success();
    }
}
