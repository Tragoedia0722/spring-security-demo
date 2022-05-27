package com.example.springSecurityDemo.controller;

import com.example.springSecurityDemo.domain.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('system:hello')")
    public Result<Object> hello() {
        return Result.success();
    }
}
