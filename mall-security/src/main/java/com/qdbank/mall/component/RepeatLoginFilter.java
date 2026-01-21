package com.qdbank.mall.component;

import org.springframework.beans.factory.annotation.Value;
import com.qdbank.mall.config.IgnoreUrlsConfig;
import com.qdbank.mall.util.LoginUserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 *
 */
@Slf4j
public class RepeatLoginFilter extends OncePerRequestFilter  {

    @Value(value = "${jwt.tokenHeader}")
    private String tokenHeader;
    @Value(value = "${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private RepeatLoginManage repeatLoginManage;

    @Autowired
    IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    public void doFilterInternal(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain chain) throws IOException, ServletException {

        //白名单请求直接放行
        String url = request.getServletPath();
        PathMatcher pathMatcher = new AntPathMatcher();
         for (String path : ignoreUrlsConfig.getUrls()) {
            if(pathMatcher.match(path,url)){
                chain.doFilter(request, response);
                return;
            }
        }

        String authHeader = request.getHeader(this.tokenHeader);
        String authToken = authHeader.substring(this.tokenHead.length());
        //此处会调用AccessDecisionManager中的decide方法进行鉴权操作
        try {
            //check token
            repeatLoginManage.checkOut(authToken);
           UserDetails userDetails  = LoginUserContextHolder.getUserDetails();
           if(userDetails==null){
               throw new AccountExpiredException("token 已失效");
           }
           String username = userDetails.getUsername();
           //校验互踢
            repeatLoginManage.checkRepeat(username,authToken);
            chain.doFilter(request, response);
        } finally {
        }
    }





}
