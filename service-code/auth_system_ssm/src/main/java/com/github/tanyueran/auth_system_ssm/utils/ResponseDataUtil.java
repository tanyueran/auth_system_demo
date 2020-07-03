package com.github.tanyueran.auth_system_ssm.utils;

import com.alibaba.fastjson.JSON;
import com.github.tanyueran.auth_system_ssm.web.vo.MyResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// HttpServletResponse 响应数据
public class ResponseDataUtil {
    // 返回信息
    public static void ResponseData(HttpServletResponse response, MyResponseBody info) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(info));
        writer.close();
    }

    // 返回未登录
    public static void ResponseNoLoginData(HttpServletResponse response, MyResponseBody info) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(401);
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(info));
        writer.close();
    }
}
