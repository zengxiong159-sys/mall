package com.qdbank.mall.controller.user;


import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.resourcecategory.ResourceCategoryReqDTO;
import com.qdbank.mall.response.resourcecategory.ResourceCategoryResDTO;
import com.qdbank.mall.user.UmsResourceCategoryService;
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
 * 后台资源分类管理Controller
 * Created by ningyuehuai on 2020/10/5.
 */
@Controller
@Api(tags = "UmsResourceCategoryController", description = "后台资源分类管理")
@RequestMapping("/resourceCategory")
public class UmsResourceCategoryController {
    @Autowired
    private UmsResourceCategoryService resourceCategoryService;

    @ApiOperation("查询所有后台资源分类")
    @RequestMapping(value = "/listAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<ResourceCategoryResDTO>> listAll() {
        List<ResourceCategoryResDTO> resourceList = resourceCategoryService.listAll();
        return CommonResult.success(resourceList);
    }

    @ApiOperation("添加后台资源分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody ResourceCategoryReqDTO resourceCategoryReqDTO) {
        int count = resourceCategoryService.create(resourceCategoryReqDTO);
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改后台资源分类")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@Validated @RequestBody ResourceCategoryReqDTO resourceCategoryReqDTO) {
        int count = resourceCategoryService.update(resourceCategoryReqDTO.getId(), resourceCategoryReqDTO);
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除后台资源->资源编号")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        int count = resourceCategoryService.delete(commonIDReqDTO.getId());
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }
}
