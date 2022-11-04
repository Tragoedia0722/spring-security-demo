package com.example.springsecuritydemo.model;

import com.example.springsecuritydemo.eumn.ResultEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * JSON 返回结果
 *
 * @author Tragoedia
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "返回结果集")
@Data
@SuppressWarnings("unused")
public class Result<T> {
    @Schema(description = "响应码")
    private Integer code;
    @Schema(description = "提示信息")
    private String msg;
    @Schema(description = "响应数据")
    private T data;

    private Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> result(Integer code, String msg, T data) {
        return new Result<>(code, msg, data);
    }

    public static <T> Result<T> result(Integer code, String msg) {
        return new Result<>(code, msg);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), msg, data);
    }

    public static <T> Result<T> success(String msg) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> success() {
        return new Result<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
    }

    public static <T> Result<T> failure(String msg) {
        return new Result<>(ResultEnum.FAILURE.getCode(), msg);
    }

    public static <T> Result<T> failure() {
        return new Result<>(ResultEnum.FAILURE.getCode(), ResultEnum.FAILURE.getMessage());
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(ResultEnum.ERROR.getCode(), msg);
    }

    public static <T> Result<T> error() {
        return new Result<>(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMessage());
    }

    public static <T> Result<T> unAuthorized() {
        return new Result<>(ResultEnum.UNAUTHORIZED.getCode(), ResultEnum.UNAUTHORIZED.getMessage());
    }

    public static <T> Result<T> forbidden() {
        return new Result<>(ResultEnum.FORBIDDEN.getCode(), ResultEnum.FORBIDDEN.getMessage());
    }

}