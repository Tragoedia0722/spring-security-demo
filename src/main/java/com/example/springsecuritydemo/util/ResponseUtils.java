package com.example.springsecuritydemo.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JSON返回工具类
 *
 * @author Tragoedia
 */
public class ResponseUtils {
    private ResponseUtils() {
    }

    public static void write(HttpServletResponse response, Object object) {
        try {
            String jsonString = JSON.toJSONString(object);
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
