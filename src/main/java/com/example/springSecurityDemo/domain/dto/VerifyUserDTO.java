package com.example.springSecurityDemo.domain.dto;

import lombok.Data;

@Data
public class VerifyUserDTO {
    private String username;
    private String password;
}
