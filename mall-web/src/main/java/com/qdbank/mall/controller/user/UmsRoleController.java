package com.qdbank.mall.controller.user;

import com.alibaba.fastjson.JSON;
import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.feign.FeignService;
import com.qdbank.mall.menu.UmsRoleService;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.role.*;
import com.qdbank.mall.response.menu.MenuResDTO;
import com.qdbank.mall.response.resource.ResourceResDTO;
import com.qdbank.mall.response.role.RoleResDTO;
import com.qdbank.mall.user.RedisKeyDeleteService;
import com.qdbank.mall.util.HttpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 后台用户角色管理
 * Created by ningyuehuai on 2020/10/30.
 */
@RestController
@Api(tags = "UmsRoleController", description = "后台用户角色管理")
@RequestMapping("/role")
@RefreshScope
@Slf4j
public class UmsRoleController {
    @Autowired
    private UmsRoleService roleService;
    @Value("${bigdata.url:}")
    private String bigdataUrl;
    @Autowired
    private FeignService feignService;
    @Value(value = "${server.port}")
    String port;
    @ApiOperation("添加角色")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@Validated @RequestBody RoleReqDTO role) {
        int count = roleService.create(role);
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改角色")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult update( @RequestBody UpdateRoleReqDTO role) {
        int count = roleService.update(role.getId(), role);
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除角色")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        List<Long> ids = new ArrayList<>();
        ids.add(commonIDReqDTO.getId());
        int count = roleService.delete(ids);
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取所有角色")
    @RequestMapping(value = "/listAll", method = RequestMethod.POST)
    public CommonResult<List<RoleResDTO>> listAll() {
        return CommonResult.success(roleService.list(), Constant.NOT_ENCRYPT);
    }
    @ApiOperation("获取角色列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<RoleResDTO>> list(RoleQueryLikeReqDTO roleQueryLikeReqDTO){
        return CommonResult.success(CommonPage.restPage(roleService.list(roleQueryLikeReqDTO)));
    }
    @ApiOperation("修改角色状态")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public CommonResult updateStatus(@Validated @RequestBody UpdateRoleStatusReqDTO updateRoleStatusReqDTO) {
        UpdateRoleReqDTO updateRoleReqDTO = new UpdateRoleReqDTO();
        updateRoleReqDTO.setStatus(updateRoleStatusReqDTO.getStatus());
        int count = roleService.update(updateRoleStatusReqDTO.getId(), updateRoleReqDTO);
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取角色相关菜单->角色编号")
    @RequestMapping(value = "/listMenu", method = RequestMethod.POST)
   
    public CommonResult<List<MenuResDTO>> listMenu(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        return CommonResult.success(roleService.listMenu(commonIDReqDTO.getId()));
    }

    @ApiOperation("获取角色相关资源->角色编号")
    @RequestMapping(value = "/listResource", method = RequestMethod.POST)
   
    public CommonResult<List<ResourceResDTO>> listResource(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        return CommonResult.success( roleService.listResource(commonIDReqDTO.getId()));
    }

    @ApiOperation("给角色分配菜单")
    @RequestMapping(value = "/allocMenu", method = RequestMethod.POST)
    public CommonResult allocMenu(@RequestBody UpdateRoleMenuReqDTO updateRoleMenuReqDTO) {
        int count = roleService.allocMenu(updateRoleMenuReqDTO.getRoleId(), Arrays.stream(updateRoleMenuReqDTO.getMenuIds().split(","))
                .map(s -> Long.parseLong(s.trim())).collect(Collectors.toList()));
        if(count == 0) return CommonResult.failed();
        return CommonResult.success(null);
    }

    @ApiOperation("给角色分配资源")
    @RequestMapping(value = "/allocResource", method = RequestMethod.POST)
    public CommonResult allocResource(@Validated @RequestBody UpdateRoleResourceReqDTO updateRoleResourceReqDTO) {
        int count = roleService.allocResource(updateRoleResourceReqDTO.getRoleId(), StringUtils.isNotBlank(updateRoleResourceReqDTO.getResourceIds()) ? Arrays.stream(updateRoleResourceReqDTO.getResourceIds().split(","))
                .map(s -> Long.parseLong(s.trim())).collect(Collectors.toList()) : null);
        if(count == 0) return CommonResult.failed();
        return CommonResult.success(null);
    }

    @RequestMapping(value = "/deleteCache/{pattern}", method = RequestMethod.GET)
    public CommonResult deleteCache(@PathVariable String pattern) {
        String value = HttpUtils.getRestTemplate().getForEntity(bigdataUrl+"/getCache/"+pattern,String.class,String.class).toString();
        log.info("查询结果：{}",JSON.toJSONString(value));
        String deleteFlag = HttpUtils.getRestTemplate().getForEntity(bigdataUrl+"/deleteCache/"+pattern,String.class,String.class).toString();
        log.info("删除状态:{}",JSON.toJSONString(deleteFlag));
        return CommonResult.success("删除结果："+value+":"+deleteFlag);
    }
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return feignService.paymentFeignTimeout();
    }

    @GetMapping(value = "/feign/timeout")
    public String paymentFeignTimeout(){
        try{
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return port;
    }
}
