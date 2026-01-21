/*
package com.qdbank.mall.controller.refund;

import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.refund.OrderRefundService;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.refund.ApplyRefundReqDTO;
import com.qdbank.mall.request.refund.OrderRefundHandleReqDTO;
import com.qdbank.mall.request.refund.OrderRefundLikeQueryReqDTO;
import com.qdbank.mall.response.refund.ApplyRefundResDTO;
import com.qdbank.mall.response.refund.OrderRefundDetailResDTO;
import com.qdbank.mall.response.refund.OrderRefundResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

*/
/**
 * @ClassName OrderRefundController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/17 19:21
 * @Version 1.0
 **//*

@RestController
@Api(tags = "OrderRefundController", description = "退款订单管理")
@RequestMapping("/refund")
@Slf4j
public class OrderRefundController {
    @Autowired
    private OrderRefundService orderRefundService;

    @ApiOperation("退款订单列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<OrderRefundResDTO>> list(@RequestBody OrderRefundLikeQueryReqDTO orderRefundLikeQueryReqDTO) {

        return CommonResult.success(null);
    }

    @ApiOperation("退款订单详细信息")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public CommonResult<OrderRefundDetailResDTO> getItem(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {

        return CommonResult.success(null);
    }
    @ApiOperation("申请退款标识判断 0 申请页面 1 详情页面")
    @RequestMapping(value = "/applyRefundFlag", method = RequestMethod.POST)
    public Integer applyRefundFlag (@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        //TODO 点击申请退款按钮之前先调用该接口判断跳转申请页面还是详情页面
        return 0;
    }

    @ApiOperation("申请退款-。")
    @RequestMapping(value = "/applyOrderRefund", method = RequestMethod.POST)
    public CommonResult<ApplyRefundResDTO> applyOrderRefund (@Validated @RequestBody OrderRefundHandleReqDTO applyRefundReqDTO) {
        return null;
    //    return CommonResult.success(orderRefundService.applyRefund(applyRefundReqDTO));
    }

    @ApiOperation("退款-。")
    @RequestMapping(value = "/applyRefund", method = RequestMethod.POST)
    public CommonResult<ApplyRefundResDTO> applyRefund (@Validated @RequestBody ApplyRefundReqDTO applyRefundReqDTO) {
        return CommonResult.success(orderRefundService.applyRefund(applyRefundReqDTO));
    }
    @ApiOperation("撤销申请->订单编号")
    @RequestMapping(value = "/cancleRefund", method = RequestMethod.POST)
    public CommonResult cancleRefund (@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {

        return CommonResult.success(null);
    }

}
*/
