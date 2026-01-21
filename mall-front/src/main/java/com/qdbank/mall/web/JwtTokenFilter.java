package com.qdbank.mall.web;

import org.springframework.beans.factory.annotation.Value;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.util.RSAUtil;
import com.qdbank.mall.util.UserContextHolder;
import com.qdbank.mall.bo.UserDetails;
import com.qdbank.mall.util.JsonUtil;
import com.qdbank.mall.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

/**
 * JWT登录授权过滤器
 * Created by ningyuehuai on 2020/10/26.
 */
@Slf4j
@RefreshScope
public class JwtTokenFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenFilter.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value(value = "${jwt.tokenHeader}")
    private String tokenHeader;
    @Value(value = "${jwt.tokenHead}")
    private String tokenHead;

    @Value(value = "${http.request.rsa.private.key}")
    private String privateKey;

    @Value(value = "${http.request.rsa.public.key}")
    private String publicKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader(this.tokenHeader);
        if (StringUtils.hasText(authHeader)) {
            try{
                    String authToken = authHeader.substring(this.tokenHead.length());
                //    String username = jwtTokenUtil.getUserNameFromToken(authToken);
                log.debug("header token [{}]",authToken);
                Claims c= jwtTokenUtil.getFrontByToken(authToken);
                String result = JsonUtil.toJSONString(c);
         //       String result   = decrypt(authHeader);
                log.info("转信息[{}]",result);

                Map map =JsonUtil.parseObject(result, Map.class);
                if(CollectionUtils.isEmpty(map)){
                    throw new ApiException(ResultCode.UNAUTHORIZED);
                }
                UserDetails userDetails = new UserDetails();
                userDetails.setCustName((String)map.get("name"));
                userDetails.setCustNo((String)map.get("custId"));
                userDetails.setCustMobile((String)map.get("phone"));
                UserContextHolder.setUserDetailsThreadLocal(userDetails);
                log.info("checking username:{}", JsonUtil.toJSONString(userDetails));
                chain.doFilter(request, response);
            } finally {
                UserContextHolder.clearUserDetails();
            }
        }else{
            chain.doFilter(request, response);
        }
    }


    private String decrypt(String authHeader){
        String result = null;
        try {
            result = RSAUtil.decryptRSADefault(privateKey,authHeader);
        } catch (Exception e) {
            log.error("解密异常",e);
            throw new ApiException(ResultCode.FORBIDDEN);
        }
        return result;
    }

}
