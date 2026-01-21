package com.qdbank.mall.controller.advertisment;

import com.qdbank.mall.advertisment.AdvertismentService;
import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.advertisement.*;
import com.qdbank.mall.response.advertisement.AdvertisementResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName AdvertismentController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/11 10:09
 * @Version 1.0
 **/
@RestController
@Api(tags = "AdvertismentController", description = "广告配置")
@RequestMapping("/advertisment")
@Slf4j
public class AdvertismentController {
    @Autowired
    private AdvertismentService advertismentService;
    @ApiOperation("广告列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<AdvertisementResDTO>> list(@Validated @RequestBody AdvertismentLikeQueryReqDTO advertismentLikeQueryReqDTO) {
        return CommonResult.success(CommonPage.restPage(advertismentService.list(advertismentLikeQueryReqDTO)));
    }

    @ApiOperation(value = "新建广告")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult<AdvertismentReqDTO> create(AdvertismentReqDTO advertismentReqDTO) {
        int count = advertismentService.create(advertismentReqDTO);
        if (count < 1) {
            return CommonResult.failed();
        }
        return CommonResult.success(null);
    }

    @ApiOperation(value = "编辑广告")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult update(@Validated @RequestBody UpdateAdvertisementReqDTO updateAdvertisementReqDTO) {
        int count = advertismentService.update(updateAdvertisementReqDTO,null);
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }
    @ApiOperation(value = "编辑广告->修改图片")
    @RequestMapping(value = "/updatePic", method = RequestMethod.POST)
    public CommonResult update(UpdateAdvertisementPicReqDTO updateAdvertisementReqDTO) {
        int count = advertismentService.update(updateAdvertisementReqDTO,updateAdvertisementReqDTO.getFile());
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }
    @ApiOperation(value = "修改广告状态")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public CommonResult updateStatus(@Validated @RequestBody UpdateAdvertisementStatusReqDTO updateAdvertisementStatusReqDTO) {
        int count = advertismentService.updateStatus(updateAdvertisementStatusReqDTO);
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation("广告详情->广告编号")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public CommonResult<AdvertisementResDTO> getItem(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        return CommonResult.success(advertismentService.detail(commonIDReqDTO.getId()));
    }
}
