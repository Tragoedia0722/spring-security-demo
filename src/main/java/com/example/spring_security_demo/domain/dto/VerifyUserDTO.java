package com.example.spring_security_demo.domain.dto;

import lombok.Data;

@Data
public class VerifyUserDTO {
    private String username;
    private String password;
}
