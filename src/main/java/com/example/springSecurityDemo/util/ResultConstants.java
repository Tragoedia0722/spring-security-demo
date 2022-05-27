package com.example.springSecurityDemo.util;

public class ResultConstants {
    public static final Integer RESULT_STATUS_SUCCESS = 1;
    public static final Integer RESULT_STATUS_FAILURE = -1;
    public static final String RESULT_MSG_SUCCESS = "操作成功";
    public static final String RESULT_MSG_FAILURE = "操作失败";
    public static final String RESULT_MSG_ERROR = "服务器错误";
    public static final String RESULT_MSG_UNAUTHORIZED = "用户认证失败，请检查登录状态";
    public static final String RESULT_MSG_FORBIDDEN = "用户权限不足，禁止访问";
}
