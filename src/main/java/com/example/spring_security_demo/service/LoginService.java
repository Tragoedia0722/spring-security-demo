package com.example.spring_security_demo.service;

import com.example.spring_security_demo.domain.dto.VerifyUserDTO;
import com.example.spring_security_demo.util.ResultUtils;

public interface LoginService {
    ResultUtils<Object> login(VerifyUserDTO verifyUserDTO);
}
