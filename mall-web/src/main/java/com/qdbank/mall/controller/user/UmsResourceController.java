package com.qdbank.mall.controller.user;


import cn.hutool.core.collection.CollUtil;
import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.component.DynamicSecurityMetadataSource;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.resource.ResourceListReqDTO;
import com.qdbank.mall.request.resource.ResourceReqDTO;
import com.qdbank.mall.response.resource.ResourceResDTO;
import com.qdbank.mall.user.UmsResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 后台资源管理Controller
 * Created by ningyuehuai on 2020/10/4.
 */
@Controller
@Api(tags = "UmsResourceController", description = "后台资源管理")
@RequestMapping("/resource")
public class UmsResourceController {

    @Autowired
    private UmsResourceService resourceService;
    @Autowired
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    @ApiOperation("添加后台资源")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@Validated @RequestBody ResourceReqDTO umsResource) {
        int count = resourceService.create(umsResource);
        dynamicSecurityMetadataSource.clearDataSource();
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改后台资源")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@Validated  @RequestBody ResourceReqDTO resourceReqDTO) {
        int count = resourceService.update(resourceReqDTO.getId(), resourceReqDTO);
        dynamicSecurityMetadataSource.clearDataSource();
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("获取资源详情->资源编号")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<ResourceResDTO> getItem( @Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        return CommonResult.success(resourceService.getItem(commonIDReqDTO.getId()));
    }

    @ApiOperation("删除后台资源->资源编号")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        int count = resourceService.delete(commonIDReqDTO.getId());
        dynamicSecurityMetadataSource.clearDataSource();
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("分页模糊查询后台资源")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<ResourceResDTO>> list(@Validated @RequestBody ResourceListReqDTO resourceListReqDTO) {
        return CommonResult.success(CommonPage.restPage(resourceService.list(resourceListReqDTO.getCategoryId(),resourceListReqDTO.getResourceName(), resourceListReqDTO.getResourceUrl(), resourceListReqDTO.getPageSize(), resourceListReqDTO.getPageNum())));
    }

    @ApiOperation("查询所有后台资源")
    @RequestMapping(value = "/listAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<ResourceResDTO>> listAll() {
        List<ResourceResDTO> resourceList = resourceService.listAll();
        if(CollUtil.isNotEmpty(resourceList)){
            resourceList.removeIf(e-> e.getId().equals(214469071173869568L));
            resourceList.removeIf(e-> e.getId().equals(214469218180030464L));
            resourceList.removeIf(e-> e.getId().equals(214469407926149120L));
        }
        return CommonResult.success(resourceList);
    }
}
