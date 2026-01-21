package com.qdbank.mall.controller.user;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.qdbank.mall.annotation.ExcludeHandler;
import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.component.RepeatLoginManage;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.menu.impl.UmsRoleServiceImpl;
import com.qdbank.mall.model.user.UmsAdminLoginParam;
import com.qdbank.mall.model.user.UserInfoDO;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.user.*;
import com.qdbank.mall.response.role.RoleResDTO;
import com.qdbank.mall.response.user.UserInfoResDTO;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.util.LoginUserContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 后台用户管理
 * Created by ningyuehuai on 2020/10/26.
 */
@RestController
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
@Slf4j
public class UmsAdminController {
    @Value(value = "${jwt.tokenHeader}")
    private String tokenHeader;
    @Value(value = "${jwt.tokenHead}")
    private String tokenHead;
    @Qualifier("umsAdminServiceImpl")
    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private UmsRoleServiceImpl roleService;
    @Autowired
    RepeatLoginManage repeatLoginManage;

    @ApiOperation(value = "新建用户")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult<UserInfoDO> register(@Validated @RequestBody UserInfoReqDTO umsAdminParam) {
        UserInfoDO userInfoDO = adminService.register(umsAdminParam);
        if (userInfoDO == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(null);
    }

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

    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public CommonResult getAdminInfo(Principal principal) {
        if(principal==null){
            return CommonResult.unauthorized(null);
        }
        String username = principal.getName();
        UserInfoDO userInfoDO = adminService.getAdminByUsername(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", userInfoDO.getUsername());
        data.put("menus", roleService.getMenuList(userInfoDO.getId()));
        List<RoleResDTO> roleList = adminService.getRoleList(userInfoDO.getId());
        if(CollUtil.isNotEmpty(roleList)){
            List<String> roles = roleList.stream().map(RoleResDTO::getRoleName).collect(Collectors.toList());
            data.put("roles",roles);
        }
        return CommonResult.success(data,Constant.NOT_ENCRYPT);
    }

    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ExcludeHandler
    public CommonResult logout(HttpServletRequest request) {
        String authHeader = request.getHeader(this.tokenHeader);
        String authToken = authHeader.substring(this.tokenHead.length());
        repeatLoginManage.setTokenTimeOutExpir(authToken);
        //清除登录锁定token
        repeatLoginManage.clearToken(LoginUserContextHolder.getUserDetails().getUsername());
        return CommonResult.success(1);
    }

    @ApiOperation("用户列表查询")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<UserInfoResDTO>> list(@RequestBody UserInfoLikeQueryReqDTO userInfoLikeQueryReqDTO) {

        return CommonResult.success(CommonPage.restPage(adminService.list(userInfoLikeQueryReqDTO)));
    }

    @ApiOperation("查看详情->用户编号")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public CommonResult<UserInfoResDTO> getItem( @Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {

        return CommonResult.success(adminService.getItem(commonIDReqDTO.getId()));
    }

    @ApiOperation("编辑用户")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult update(@Validated @RequestBody UpdateUserInfoReqDTO admin) {
        int count = adminService.update(admin.getId(), admin);
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改指定用户密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public CommonResult updatePassword(@Validated @RequestBody UpdatePasswordReqDTO updatePasswordParam) {
        int status = adminService.updatePassword(updatePasswordParam);
        if (status > 0) {
            return CommonResult.success(null);
        } else if (status == -1) {
            return CommonResult.failed(ResultCode.USER_NOT_EXIST.getMessage());
        } else if (status == -2) {
            return CommonResult.failed(ResultCode.USER_NOT_EXIST.getMessage());
        } else if (status == -3) {
            return CommonResult.failed(ResultCode.OLD_PASSWORD_ERROR.getMessage());
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除指定用户信息->用户编号")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        int count = adminService.delete(commonIDReqDTO.getId());
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改帐号状态")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public CommonResult updateStatus(@RequestBody UpdateUserStatusReqDTO updateUserStatusReqDTO) {
        UpdateUserInfoReqDTO userInfoReqDTO = new UpdateUserInfoReqDTO();
        userInfoReqDTO.setStatus(updateUserStatusReqDTO.getStatus());
        int count = adminService.update(updateUserStatusReqDTO.getId(), userInfoReqDTO);
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation("分配角色")
    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
    public CommonResult updateRole(@RequestBody UpdateUserRoleReqDTO updateUserRoleReqDTO) {
        if(StringUtils.isBlank(updateUserRoleReqDTO.getRoleIds())) return CommonResult.failed();
        String [] roleIds = updateUserRoleReqDTO.getRoleIds().split(",");
        int count = adminService.updateRole(updateUserRoleReqDTO.getId(), Arrays.asList(roleIds));
        if (count >= 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取指定用户的角色->用户编号")
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public CommonResult<List<RoleResDTO>> getRoleList(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        return CommonResult.success(adminService.getRoleList(commonIDReqDTO.getId()));
    }


}
