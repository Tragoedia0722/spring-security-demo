package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.model.dto.VerifyUserDTO;
import com.example.springsecuritydemo.model.Result;

/**
 * 登录业务层
 *
 * @author Tragoedia
 */
public interface LoginService {
    /**
     * 登录
     *
     * @param verifyUserDTO 用户验证对象
     * @return Result
     */
    Result<Object> login(VerifyUserDTO verifyUserDTO);
}
