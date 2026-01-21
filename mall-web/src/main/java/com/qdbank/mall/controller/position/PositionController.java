package com.qdbank.mall.controller.position;

import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.position.PositionService;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.position.*;
import com.qdbank.mall.response.position.PositionResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(tags = "PositionController", description = "金刚位配置")
@RequestMapping("/openapi/position")
@Slf4j
public class PositionController {
    @Autowired
    private PositionService positionService;
    @ApiOperation("金刚位列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<PositionResDTO>> list(@Validated @RequestBody PositionLikeReqDTO positionLikeReqDTO) {

        return CommonResult.success(CommonPage.restPage(positionService.list(positionLikeReqDTO)));
    }
    @ApiOperation(value = "新建金刚位")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create( PositionReqDTO positionReqDTO) {
        int count = positionService.create(positionReqDTO);
        if (count < 1) {
            return CommonResult.failed();
        }
        return CommonResult.success(null);
    }

    @ApiOperation(value = "编辑金刚位")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult update(@Validated @RequestBody UpdatePositionReqDTO updatePositionReqDTO) {
        int count = positionService.update(updatePositionReqDTO,null);
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }
    @ApiOperation(value = "编辑金刚位-修改图片")
    @RequestMapping(value = "/updatePic", method = RequestMethod.POST)
    public CommonResult update(UpdatePositionPicReqDTO updatePositionPicReqDTO) {
        int count = positionService.update(updatePositionPicReqDTO,updatePositionPicReqDTO.getFile());
        if (count > 0) {
            return CommonResult.success(count, Constant.NOT_ENCRYPT);
        }
        return CommonResult.failed();
    }
    @ApiOperation(value = "修改金刚位状态")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public CommonResult updateStatus(@Validated @RequestBody UpdateStatusPositionReqDTO updateStatusPositionReqDTO) {
        int count = positionService.updateStatus(updateStatusPositionReqDTO);
        if (count > 0) {
            return CommonResult.success(count, Constant.NOT_ENCRYPT);
        }
        return CommonResult.failed();
    }

    @ApiOperation("金刚位详情->编号")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public CommonResult<PositionResDTO> getItem(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        return CommonResult.success(positionService.detail(commonIDReqDTO.getId()));
    }


}
