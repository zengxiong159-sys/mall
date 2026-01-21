package com.qdbank.mall.controller.product;
import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.SystemServiceEnum;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.product.ProductService;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.product.*;
import com.qdbank.mall.request.productpicurl.UpLoadPictureReqDTO;
import com.qdbank.mall.response.merchant.MerchantResDTO;
import com.qdbank.mall.response.product.ProductResDTO;
import com.qdbank.mall.response.productpicurl.UpLoadPictureResDTO;
import com.qdbank.mall.user.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @Qualifier("cmsAdminServiceImpl")
    @Autowired
    private UmsAdminService umsAdminService;
    @ApiOperation("商品列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<ProductResDTO>> list(@RequestBody ProductLikeQueryReqDTO productLikeQueryReqDTO) {
        MerchantResDTO merchantResDTO = umsAdminService.selectUserNameAndMerchantNo();
        productLikeQueryReqDTO.setMerchantNo(merchantResDTO.getMerchantNo());
        productLikeQueryReqDTO.setSystem(SystemServiceEnum.CMS_ADMIN_SERVICE.getSystem());
        return CommonResult.success(CommonPage.restPage(productService.list(productLikeQueryReqDTO)));
    }
    @ApiOperation(value = "新建商品")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create( ProductReqDTO productReqDTO) {
        MerchantResDTO merchantResDTO = umsAdminService.selectUserNameAndMerchantNo();
        BeanUtils.copyProperties(merchantResDTO,productReqDTO);
        int count = productService.create(productReqDTO);
        if (count < 1) {
            return CommonResult.failed();
        }
        return CommonResult.success(count, Constant.NOT_ENCRYPT);
    }

    @ApiOperation("商品详情-上传图片")
    @RequestMapping(value = "/uploadPicture", method = RequestMethod.POST)
    public CommonResult<UpLoadPictureResDTO> uploadPicture(UpLoadPictureReqDTO upLoadPictureReqDTO) {

        return CommonResult.success(productService.uploadPictures(upLoadPictureReqDTO));
    }

    @ApiOperation(value = "编辑商品")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult update(UpdateProductReqDTO updateProductReqDTO) {
        MerchantResDTO merchantResDTO = umsAdminService.selectUserNameAndMerchantNo();
        BeanUtils.copyProperties(merchantResDTO,updateProductReqDTO);
        int count = productService.update(updateProductReqDTO);
        if (count > 0) {
            return CommonResult.success(count, Constant.NOT_ENCRYPT);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "修改商品状态 0 待入库 1 已入库 2 已上架")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public CommonResult updateStatus(@Validated @RequestBody UpdateProductStatusReqDTO updateProductStatusReqDTO) {
        updateProductStatusReqDTO.setSystem(SystemServiceEnum.CMS_ADMIN_SERVICE.getSystem());
        int count = productService.updateStatus(updateProductStatusReqDTO);
        if (count > 0) {
            return CommonResult.success(count, Constant.NOT_ENCRYPT);
        }
        return CommonResult.failed();
    }

    @ApiOperation("商品详细信息->商品编号")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public CommonResult<ProductResDTO> getItem(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        return CommonResult.success(productService.detail(commonIDReqDTO.getId()));
    }
}
