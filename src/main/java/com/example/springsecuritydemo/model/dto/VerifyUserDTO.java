package com.example.springsecuritydemo.model.dto;

import lombok.Data;

/**
 * 用户登录验证
 *
 * @author Tragoedia
 */
@Data
public class VerifyUserDTO {
    private String username;
    private String password;
}
