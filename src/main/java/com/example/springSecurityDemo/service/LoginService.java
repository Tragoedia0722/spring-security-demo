package com.example.springSecurityDemo.service;

import com.example.springSecurityDemo.domain.dto.VerifyUserDTO;
import com.example.springSecurityDemo.domain.Result;

public interface LoginService {
    Result<Object> login(VerifyUserDTO verifyUserDTO);
}
