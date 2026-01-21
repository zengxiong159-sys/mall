package com.qdbank.mall.controller.merchant;

import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.merchant.MerchantService;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.merchant.MerchantLikeQueryReqDTO;
import com.qdbank.mall.request.merchant.MerchantReqDTO;
import com.qdbank.mall.request.merchant.UpdateMerchantReqDTO;
import com.qdbank.mall.request.merchant.UpdateMerchantStatusReqDTO;
import com.qdbank.mall.response.coupon.CouponResDTO;
import com.qdbank.mall.response.merchant.MerchantResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName MerchantController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/13 16:05
 * @Version 1.0
 **/
@RestController
@Api(tags = "MerchantController", description = "商户管理")
@RequestMapping("/merchant")
@Slf4j
public class MerchantController {
    @Autowired
    private MerchantService merchantService;
    @ApiOperation("商户列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<MerchantResDTO>> list(@RequestBody MerchantLikeQueryReqDTO merchantLikeQueryReqDTO) {

        return CommonResult.success(CommonPage.restPage(merchantService.list(merchantLikeQueryReqDTO)));
    }
    @ApiOperation(value = "新建商户")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@Validated @RequestBody MerchantReqDTO merchantReqDTO) {
        int count = merchantService.create(merchantReqDTO);
        if (count < 1) {
            return CommonResult.failed();
        }
        return CommonResult.success(null);
    }

    @ApiOperation(value = "编辑商户")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult update(@Validated @RequestBody UpdateMerchantReqDTO merchantReqDTO) {
        int count = merchantService.update(merchantReqDTO);
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "修改商户状态")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public CommonResult updateStatus(@Validated @RequestBody UpdateMerchantStatusReqDTO updateMerchantStatusReqDTO) {
        int count = merchantService.updateStatus(updateMerchantStatusReqDTO);
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation("商户详情->商户编号")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public CommonResult<MerchantResDTO> getItem( @Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        return CommonResult.success(merchantService.getItem(commonIDReqDTO.getId()));
    }

}
