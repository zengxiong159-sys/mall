package com.qdbank.mall.controller.productattributecategory;

import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.response.productattributecategory.ProductAttributeCategoryItemResDTO;
import com.qdbank.mall.response.productattributecategory.ProductAttributeCategoryResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "ProductAttributeCategoryController", description = "商品属性分类管理")
@RequestMapping("/productAttribute/category")
public class ProductAttributeCategoryController {
    @ApiOperation("添加商品属性分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestParam String name) {
        /**
         * 属性分类直接往属性分类表中插入数据，判断是否违反唯一约束
         */
        int count = 0;
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改商品属性分类")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestParam String name) {
        int count = 0;
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("删除单个商品属性分类")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = 0;
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("获取单个商品属性分类信息")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<ProductAttributeCategoryResDTO> getItem(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        ProductAttributeCategoryResDTO productAttributeCategory = null;
        return CommonResult.success(productAttributeCategory);
    }

    @ApiOperation("分页获取所有商品属性分类")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<ProductAttributeCategoryResDTO>> getList(@RequestParam(defaultValue = "5") Integer pageSize, @RequestParam(defaultValue = "1") Integer pageNum) {
        List<ProductAttributeCategoryResDTO> productAttributeCategoryList = null;
        return CommonResult.success(CommonPage.restPage(productAttributeCategoryList));
    }

    @ApiOperation("获取所有商品属性分类及其下属性")
    @RequestMapping(value = "/list/withAttr", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<ProductAttributeCategoryItemResDTO>> getListWithAttr() {
        List<ProductAttributeCategoryItemResDTO> productAttributeCategoryResultList = null;
        return CommonResult.success(productAttributeCategoryResultList);
    }
}
