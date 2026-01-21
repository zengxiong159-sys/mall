package com.qdbank.mall.controller.center;

import com.github.pagehelper.PageInfo;
import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.center.CenterServcie;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.product.ProductService;
import com.qdbank.mall.replace.ReplaceService;
import com.qdbank.mall.request.center.CenterLikeReqDTO;
import com.qdbank.mall.request.center.CenterReqDTO;
import com.qdbank.mall.request.center.UpdateStatusCenterReqDTO;
import com.qdbank.mall.request.productpicurl.UpLoadPictureReqDTO;
import com.qdbank.mall.response.advertisement.AdvertisementResDTO;
import com.qdbank.mall.response.center.CenterResDTO;
import com.qdbank.mall.response.productpicurl.UpLoadPictureResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "CenterController", description = "配置管理")
@RequestMapping("/openapi/center")
@Slf4j
public class CenterController {
    @Autowired
    private CenterServcie centerServcie;
    @Autowired
    private ProductService productService;
    @Autowired
    private ReplaceService replaceService;
    @ApiOperation("配置列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<CenterResDTO>>  list(@Validated @RequestBody CenterLikeReqDTO centerLikeReqDTO){
        return CommonResult.success(CommonPage.restPage(centerServcie.list(centerLikeReqDTO)));
    }

    @ApiOperation(value = "新建配置")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(CenterReqDTO centerReqDTO) {
        int count = centerServcie.create(centerReqDTO);
        if (count < 1) {
            return CommonResult.failed();
        }
        return CommonResult.success(null);
    }
    @ApiOperation(value = "编辑配置")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult update(CenterReqDTO centerReqDTO) {
        int count = centerServcie.update(centerReqDTO);
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "修改配置状态")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public CommonResult updateStatus(@Validated @RequestBody UpdateStatusCenterReqDTO updateStatusCenterReqDTO) {
        int count = centerServcie.updateStatus(updateStatusCenterReqDTO);
        if (count > 0) {
            return CommonResult.success(count, Constant.NOT_ENCRYPT);
        }
        return CommonResult.failed();
    }

    @ApiOperation("新闻中心-上传图片")
    @RequestMapping(value = "/uploadPicture", method = RequestMethod.POST)
    public CommonResult<UpLoadPictureResDTO> uploadPicture(UpLoadPictureReqDTO upLoadPictureReqDTO) {
        UpLoadPictureResDTO upLoadPictureResDTO = productService.uploadPictures(upLoadPictureReqDTO);
        if(upLoadPictureResDTO != null && StringUtils.isNotBlank(upLoadPictureResDTO.getPicUrl())){
            upLoadPictureResDTO.setPicUrl(replaceService.replaceUrl(upLoadPictureResDTO.getPicUrl()));
        }
        return CommonResult.success(upLoadPictureResDTO);
    }
}
