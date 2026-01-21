package com.qdbank.mall.controller.productattribute;

import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.productattribute.ProductAttributeReqDTO;
import com.qdbank.mall.response.productattribute.ProductAttributeResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName ProductAttributeController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2020/12/10 9:41
 * @Version 1.0
 **/
@Controller
@Api(tags = "ProductAttributeController", description = "商品属性管理")
@RequestMapping("/productAttribute")
public class ProductAttributeController {

    @ApiOperation("根据分类查询属性列表或参数列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "0表示属性，1表示参数", required = true, paramType = "query", dataType = "integer")})
    @RequestMapping(value = "/list/{cid}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<ProductAttributeResDTO>> getList(@PathVariable Long cid,
                                                                        @RequestParam(value = "type") Integer type,
                                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<ProductAttributeResDTO> productAttributeList = null;
        return CommonResult.success(CommonPage.restPage(productAttributeList));
    }

    @ApiOperation("添加商品属性信息")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody ProductAttributeReqDTO productAttributeParam) {
        int count = 0;
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改商品属性信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody ProductAttributeReqDTO productAttributeReqDTO) {
        int count = 0;
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("查询单个商品属性->商品编号")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<ProductAttributeResDTO> getItem( @Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        ProductAttributeResDTO productAttribute = null;
        return CommonResult.success(productAttribute);
    }

    @ApiOperation("批量删除商品属性")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = 0;
        if (count > 0) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed();
        }
    }

}
