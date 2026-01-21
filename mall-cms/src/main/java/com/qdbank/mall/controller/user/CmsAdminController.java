package com.qdbank.mall.controller.user;

import com.alibaba.fastjson.JSON;
import com.qdbank.mall.feign.FeignService;
import org.springframework.beans.factory.annotation.Value;
import com.qdbank.mall.annotation.ExcludeHandler;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.component.RepeatLoginManage;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.model.user.UmsAdminLoginParam;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.util.LoginUserContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 后台用户管理
 * Created by ningyuehuai on 2020/10/26.
 */
@RestController
@Api(tags = "CmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
@Slf4j
public class CmsAdminController {
    @Value(value = "${jwt.tokenHeader}")
    private String tokenHeader;
    @Value(value = "${jwt.tokenHead}")
    private String tokenHead;
    @Qualifier("cmsAdminServiceImpl")
    @Autowired
    private UmsAdminService adminService;
    @Value("${server.port}")
    private String port;
    @Autowired
    RepeatLoginManage repeatLoginManage;
    @Autowired
    private FeignService feignService;
    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult<Map<String, String>> login(@Validated @RequestBody UmsAdminLoginParam umsAdminLoginParam) {
       log.info("请求参数:{}", JSON.toJSONString(umsAdminLoginParam));
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed(ResultCode.USER_PASSWORD_ERROR.getMessage());
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        repeatLoginManage.setToken(umsAdminLoginParam.getUsername(),token);
        return CommonResult.success(tokenMap,Constant.ENCRYPT);
    }
    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = adminService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed(ResultCode.UNAUTHORIZED.getMessage());
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        repeatLoginManage.setToken(LoginUserContextHolder.getUserDetails().getUsername(),token);
        return CommonResult.success(tokenMap);
    }
    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ExcludeHandler
    public CommonResult logout(HttpServletRequest request) {
        String authHeader = request.getHeader(this.tokenHeader);
        String authToken = authHeader.substring(this.tokenHead.length());
        repeatLoginManage.setTokenTimeOutExpir(authToken);
        repeatLoginManage.clearToken(LoginUserContextHolder.getUserDetails().getUsername());
        return CommonResult.success(null, Constant.NOT_ENCRYPT);
    }

    @GetMapping(value = "/feign/timeout")
    public String paymentFeignTimeout(){
       return feignService.paymentFeignTimeout();
    }


}
