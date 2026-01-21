package com.qdbank.mall.controller.refundsetting;

import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.refundsetting.RefundSettingService;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.refundsetting.RefundSettingReqDTO;
import com.qdbank.mall.request.refundsetting.UpdateRefundReasonReqDTO;
import com.qdbank.mall.request.refundsetting.UpdateRefundStatusReqDTO;
import com.qdbank.mall.response.refundsetting.RefundSettingResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName RefundSettingController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/12 13:56
 * @Version 1.0
 **/
@RestController
@Api(tags = "RefundMobileReChargePayOrderEventController", description = "退款设置")
@RequestMapping("/refundsetting")
@Slf4j
public class RefundSettingController {
    @Autowired
    private RefundSettingService refundSettingService;
    @ApiOperation("退款设置列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<List<RefundSettingResDTO>> list() {
        return CommonResult.success(refundSettingService.list());
    }

    @ApiOperation(value = "新建退款设置")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@Validated @RequestBody RefundSettingReqDTO refundSettingReqDTO) {
        int count = refundSettingService.create(refundSettingReqDTO);
        if (count < 1) {
            return CommonResult.failed();
        }
        return CommonResult.success(null);
    }

    @ApiOperation(value = "编辑退款设置")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult update(@RequestBody UpdateRefundReasonReqDTO updateRefundReasonReqDTO) {
        int count = refundSettingService.update(updateRefundReasonReqDTO);
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "修改退款设置状态")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public CommonResult updateStatus(@RequestBody UpdateRefundStatusReqDTO updateRefundStatusReqDTO) {
        int count = refundSettingService.updateStatus(updateRefundStatusReqDTO);
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }
    @ApiOperation("退款设置详情->主键")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public CommonResult<RefundSettingResDTO> getItem(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        return CommonResult.success(refundSettingService.detail(commonIDReqDTO.getId()));
    }

    @ApiOperation(value = "删除退款设置->主键")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        int count = refundSettingService.delete(commonIDReqDTO.getId());
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }
}
