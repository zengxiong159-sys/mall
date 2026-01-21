package com.qdbank.mall.filter;

import com.qdbank.mall.util.ContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.FilterConfig;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.IOException;


/**
 * @author Qdccb
 */
@Slf4j
public class AntiReplayFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("AntiReplayFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
      try{
          if(servletRequest instanceof HttpServletRequest){
              String method = ((HttpServletRequest) servletRequest).getMethod();
              //以下方法不支持
              if("OPTIONS||PUT||DELETE||TRACE||CONNECT".contains(method)){
                  throw new ServletException("不支持如下方法:OPTIONS||PUT||DELETE||TRACE||CONNECT");
              }
          }


          if(!StringUtils.isEmpty(servletRequest.getContentType()) && servletRequest.getContentType().contains(MediaType.MULTIPART_FORM_DATA_VALUE)){
              filterChain.doFilter(servletRequest, servletResponse);
          }else{
              HttpRequestWrapper wrapper = new HttpRequestWrapper((HttpServletRequest) servletRequest);
              filterChain.doFilter(wrapper, servletResponse);
          }
      }finally {
          //清空localthread
          ContextHolder.removeAesKey();
          ContextHolder.removeRequestFlag();
      }


    }

    @Override
    public void destroy() {

    }
}
