package com.github.tanyueran.auth_system_springboot.utils;

import com.alibaba.fastjson.JSON;
import com.github.tanyueran.auth_system_springboot.vo.ResponseResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HttpResponseUtil {
    public static void response(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        ResponseResult error = ResponseResult.error(msg);
        writer.write(JSON.toJSONString(error));
        writer.close();
    }
}
