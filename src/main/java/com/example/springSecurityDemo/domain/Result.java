package com.example.springSecurityDemo.domain;

import com.example.springSecurityDemo.util.ResultConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings(value = {"unused"})
@Schema(description = "返回结果集")
@Data
public class Result<T> {
    @Schema(description = "成功状态")
    private Integer status;
    @Schema(description = "响应码")
    private Integer code;
    @Schema(description = "提示信息")
    private String msg;
    @Schema(description = "响应数据")
    private T data;

    private Result(Integer status, Integer code, String msg) {
        this.status = status;
        this.code = code;
        this.msg = msg;
    }

    private Result(Integer status, Integer code, String msg, T data) {
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> result(Integer status, Integer code, String msg, T data) {
        return new Result<>(status, code, msg, data);
    }

    public static <T> Result<T> result(Integer status, Integer code, String msg) {
        return new Result<>(status, code, msg);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(ResultConstants.RESULT_STATUS_SUCCESS, HttpStatus.OK.value(), msg, data);
    }

    public static <T> Result<T> success(String msg) {
        return new Result<>(ResultConstants.RESULT_STATUS_SUCCESS, HttpStatus.OK.value(), msg);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultConstants.RESULT_STATUS_SUCCESS, HttpStatus.OK.value(), ResultConstants.RESULT_MSG_SUCCESS, data);
    }

    public static <T> Result<T> success() {
        return new Result<>(ResultConstants.RESULT_STATUS_SUCCESS, HttpStatus.OK.value(), ResultConstants.RESULT_MSG_SUCCESS);
    }

    public static <T> Result<T> failure(String msg) {
        return new Result<>(ResultConstants.RESULT_STATUS_FAILURE, HttpStatus.BAD_REQUEST.value(), msg);
    }

    public static <T> Result<T> failure() {
        return new Result<>(ResultConstants.RESULT_STATUS_FAILURE, HttpStatus.BAD_REQUEST.value(), ResultConstants.RESULT_MSG_FAILURE);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(ResultConstants.RESULT_STATUS_FAILURE, HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    public static <T> Result<T> error() {
        return new Result<>(ResultConstants.RESULT_STATUS_FAILURE, HttpStatus.INTERNAL_SERVER_ERROR.value(), ResultConstants.RESULT_MSG_ERROR);
    }

    public static <T> Result<T> unAuthorized() {
        return new Result<>(ResultConstants.RESULT_STATUS_FAILURE, HttpStatus.UNAUTHORIZED.value(), ResultConstants.RESULT_MSG_UNAUTHORIZED);
    }

    public static <T> Result<T> forbidden() {
        return new Result<>(ResultConstants.RESULT_STATUS_FAILURE, HttpStatus.FORBIDDEN.value(), ResultConstants.RESULT_MSG_FORBIDDEN);
    }

}