package com.example.spring_security_demo.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "返回结果集")
@Data
public class ResultUtils<T> {
    @Schema(description = "成功状态")
    private Integer status;
    @Schema(description = "响应码")
    private Integer code;
    @Schema(description = "提示信息")
    private String msg;
    @Schema(description = "响应数据")
    private T data;

    private ResultUtils(Integer status, Integer code, String msg) {
        this.status = status;
        this.code = code;
        this.msg = msg;
    }

    private ResultUtils(Integer status, Integer code, String msg, T data) {
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResultUtils<T> result(Integer status, Integer code, String msg, T data) {
        return new ResultUtils(status, code, msg, data);
    }

    public static <T> ResultUtils<T> result(Integer status, Integer code, String msg) {
        return new ResultUtils(status, code, msg);
    }

    public static <T> ResultUtils<T> success(String msg, T data) {
        return new ResultUtils(ResultConstants.RESULT_STATUS_SUCCESS, ResultConstants.RESULT_CODE_OK, msg, data);
    }

    public static <T> ResultUtils<T> success(String msg) {
        return new ResultUtils(ResultConstants.RESULT_STATUS_SUCCESS, ResultConstants.RESULT_CODE_OK, msg);
    }

    public static <T> ResultUtils<T> success(T data) {
        return new ResultUtils(ResultConstants.RESULT_STATUS_SUCCESS, ResultConstants.RESULT_CODE_OK, ResultConstants.RESULT_MSG_SUCCESS, data);
    }

    public static <T> ResultUtils<T> success() {
        return new ResultUtils(ResultConstants.RESULT_STATUS_SUCCESS, ResultConstants.RESULT_CODE_OK, ResultConstants.RESULT_MSG_SUCCESS);
    }

    public static <T> ResultUtils<T> failure(String msg) {
        return new ResultUtils(ResultConstants.RESULT_STATUS_FAILURE, ResultConstants.RESULT_CODE_OK, msg);
    }

    public static <T> ResultUtils<T> failure() {
        return new ResultUtils(ResultConstants.RESULT_STATUS_FAILURE, ResultConstants.RESULT_CODE_OK, ResultConstants.RESULT_MSG_FAILURE);
    }

    public static <T> ResultUtils<T> error(String msg) {
        return new ResultUtils(ResultConstants.RESULT_STATUS_FAILURE, ResultConstants.RESULT_CODE_ERROR, msg);
    }

    public static <T> ResultUtils<T> error() {
        return new ResultUtils(ResultConstants.RESULT_STATUS_FAILURE, ResultConstants.RESULT_CODE_ERROR, ResultConstants.RESULT_MSG_ERROR);
    }
}