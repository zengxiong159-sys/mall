package com.qdbank.mall.controller.productcategory;

import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.productcategory.ProductcategoryService;
import com.qdbank.mall.response.productcategory.ProductCategoryResDTO;
import com.qdbank.mall.response.productcategory.ProductCategoryWithChildrenResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "ProductCategoryController", description = "商品分类管理")
@RequestMapping("/productCategory")
public class ProductCategoryController {

    @Autowired
    private ProductcategoryService productcategoryService;

    @ApiOperation("查询所有一级分类及子分类->父ID")
    @RequestMapping(value = "/list/withChildren", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<ProductCategoryResDTO>> listWithChildren() {
        List<ProductCategoryResDTO> list = productcategoryService.listWithChildren();
        return CommonResult.success(list);
    }
}
