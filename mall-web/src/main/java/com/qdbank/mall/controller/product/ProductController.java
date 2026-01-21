package com.qdbank.mall.controller.product;
import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.SystemServiceEnum;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.product.ProductService;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.CommonStringReqDTO;
import com.qdbank.mall.request.product.ProductIdsReqDTO;
import com.qdbank.mall.request.product.ProductLikeQueryReqDTO;
import com.qdbank.mall.request.product.UpdateProductStatusReqDTO;
import com.qdbank.mall.response.product.ProductIdsResDTO;
import com.qdbank.mall.response.product.ProductPublishStatusResDTO;
import com.qdbank.mall.response.product.ProductResDTO;
import com.qdbank.mall.response.product.ProductSkuResDTO;
import com.qdbank.mall.response.skustock.SkustockResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName ProductController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/14 19:14
 * @Version 1.0
 **/
@RestController
@Api(tags = "ProductController", description = "商品管理")
@RequestMapping("/product")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;
    @ApiOperation("商品列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<ProductResDTO>> list(@RequestBody ProductLikeQueryReqDTO productLikeQueryReqDTO) {
        //设置银行后管系统标识
        productLikeQueryReqDTO.setSystem(SystemServiceEnum.UMS_ADMIN_SERVICE.getSystem());

        if(productLikeQueryReqDTO.getPublishStatus() == null){
            //银行端默认只能查看已入库和已上架状态商品
            productLikeQueryReqDTO.setMulStatus("1,2");
        }
        return CommonResult.success(CommonPage.restPage(productService.list(productLikeQueryReqDTO)));
    }

    @ApiOperation(value = "修改商品状态")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public CommonResult updateStatus(@RequestBody UpdateProductStatusReqDTO updateProductStatusReqDTO) {
        updateProductStatusReqDTO.setSystem(SystemServiceEnum.UMS_ADMIN_SERVICE.getSystem());
        int count = productService.updateStatus(updateProductStatusReqDTO);
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation("商品详细信息->商品编号")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public CommonResult<ProductResDTO> getItem(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        return CommonResult.success(productService.detail(commonIDReqDTO.getId()));
    }

    @ApiOperation("商品上架设置")
    @RequestMapping(value = "/upProduct", method = RequestMethod.POST)
    public CommonResult upProduct(@Validated @RequestBody CommonStringReqDTO commonStringReqDTO){
       int count = productService.upProduct(commonStringReqDTO);
        if(count < 1) return CommonResult.failed();
        return CommonResult.success(null);
    }

    @ApiOperation("查看商品上架设置")
    @RequestMapping(value = "/checkUpProduct", method = RequestMethod.POST)
    public CommonResult<List<SkustockResDTO>> checkUpProduct(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO){
        return CommonResult.success(productService.skustockList(commonIDReqDTO.getId()));
    }

    @ApiOperation("获取商品库存信息列表")
    @RequestMapping(value = "/productSkuInfoList", method = RequestMethod.POST)
    public CommonResult<CommonPage<ProductSkuResDTO>> listProductSku(@RequestBody ProductLikeQueryReqDTO productLikeQueryReqDTO){
        return CommonResult.success(CommonPage.restPage(productService.listProductSkuInfo(productLikeQueryReqDTO)));
    }

    @ApiOperation("获取商品库存详情")
    @RequestMapping(value = "/productSkuItem", method = RequestMethod.POST)
    public CommonResult<ProductSkuResDTO> productSkuItem(@RequestBody ProductLikeQueryReqDTO productLikeQueryReqDTO){
        return  CommonResult.success(productService.getProductSkuDetail(productLikeQueryReqDTO));
    }

    @ApiOperation("专区商品列表h")
    @RequestMapping(value = "/prefectrurProductList", method = RequestMethod.POST)
    public CommonResult<CommonPage<ProductResDTO>> listNew(@RequestBody ProductLikeQueryReqDTO productLikeQueryReqDTO) {
        //设置银行后管系统标识
        productLikeQueryReqDTO.setSystem(SystemServiceEnum.UMS_ADMIN_SERVICE.getSystem());

        if(productLikeQueryReqDTO.getPublishStatus() == null){
            //银行端默认只能查看已入库和已上架状态商品
            productLikeQueryReqDTO.setMulStatus("1,2");
        }
        return CommonResult.success(CommonPage.restPage(productService.listNew(productLikeQueryReqDTO)));
    }

    @ApiOperation(value = "商品上架状态->商品编号",hidden = true)
    @RequestMapping(value = "/publishStatus", method = RequestMethod.POST)
    public CommonResult<ProductPublishStatusResDTO> getPublishStatus(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        return CommonResult.success(productService.getPublishStatus(commonIDReqDTO.getId()));
    }
    @ApiOperation(value = "商品列表-提供北斗查询",hidden = true)
    @RequestMapping(value = "/productByIds", method = RequestMethod.POST)
    public CommonResult<List<ProductIdsResDTO>> queryProductByIds(@RequestBody ProductIdsReqDTO productIdsReqDTO){
        return  CommonResult.success(productService.queryProductByIds(productIdsReqDTO), Constant.NOT_ENCRYPT);
    }
}
