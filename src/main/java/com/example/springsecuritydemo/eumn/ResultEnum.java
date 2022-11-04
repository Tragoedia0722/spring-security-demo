package com.example.springsecuritydemo.eumn;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * JSON 返回结果枚举
 *
 * @author Tragoedia
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
    // 操作成功
    SUCCESS(200, "操作成功"),
    // 操作失败
    FAILURE(400, "操作失败"),
    // 错误操作
    ERROR(500, "服务器错误"),
    // 认证失败
    UNAUTHORIZED(401, "用户认证失败，请检查登录状态"),
    // 权限不足
    FORBIDDEN(403, "用户权限不足，禁止访问");

    private final Integer code;
    private final String message;
}