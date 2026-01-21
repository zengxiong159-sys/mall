package com.qdbank.mall.controller.productcategory;

import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.productcategory.ProductcategoryService;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.productcategory.ProductCategoryReqDTO;
import com.qdbank.mall.request.productcategory.UpdateProductCategoryReqDTO;
import com.qdbank.mall.response.productcategory.ProductCategoryResDTO;
import com.qdbank.mall.response.productcategory.ProductCategoryWithChildrenResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "ProductCategoryController", description = "商品分类管理")
@RequestMapping("/productCategory")
public class ProductCategoryController {
    @Autowired
    private ProductcategoryService productcategoryService;
    @ApiOperation("添加产品分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@Validated @RequestBody ProductCategoryReqDTO productCategoryReqDTO) {
        //TODO
        int count = productcategoryService.create(productCategoryReqDTO);
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }
    @ApiOperation("修改商品分类")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@Validated @RequestBody UpdateProductCategoryReqDTO productCategoryParam) {
        //TODO 修改分类表名称同时还需要修改商品表对应分类名称
        int count = productcategoryService.update(productCategoryParam);
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }
    @ApiOperation("分页查询商品父级分类->父ID")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<ProductCategoryResDTO>> getList(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        //TODO 分页查询分类数据
        List<ProductCategoryResDTO> productCategoryList = productcategoryService.list(commonIDReqDTO.getId());
        return CommonResult.success(productCategoryList);
    }

    @ApiOperation("商品分类详情->商品分类编号")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<ProductCategoryResDTO> getItem(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        ProductCategoryResDTO productCategory = productcategoryService.detail(commonIDReqDTO.getId());
        return CommonResult.success(productCategory);
    }

    @ApiOperation("删除商品分类->商品分类编号")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        int count = productcategoryService.delete(commonIDReqDTO.getId());
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("查询所有一级分类及子分类->父ID")
    @RequestMapping(value = "/list/withChildren", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<ProductCategoryResDTO>> listWithChildren() {
        List<ProductCategoryResDTO> list = productcategoryService.listWithChildren();
        return CommonResult.success(list);
    }

    @ApiOperation("查询优惠券分类类目")
    @RequestMapping(value = "/list/couponCategory", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<ProductCategoryResDTO>> couponCategory() {
        return CommonResult.success(productcategoryService.couponCategory());
    }
}
