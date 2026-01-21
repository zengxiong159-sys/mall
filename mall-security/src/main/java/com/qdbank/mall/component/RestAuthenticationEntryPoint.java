package com.qdbank.mall.component;

import com.alibaba.fastjson.JSON;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.error.RepeatLoginException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义返回结果：未登录或登录过期
 * Created by ningyuehuai on 2020/10/14.
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        if(authException instanceof RepeatLoginException){
            response.getWriter().println(JSON.toJSONString(CommonResult.repeatLogin(authException.getMessage())));
        }else{
            response.getWriter().println(JSON.toJSONString(CommonResult.unauthorized(authException.getMessage())));
        }
        response.getWriter().flush();
    }
}
