package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.model.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tragoedia
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('system:hello')")
    public Result<Object> hello() {

        return Result.success();
    }
}
