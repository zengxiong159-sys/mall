package com.qdbank.mall.controller.third;

import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.payment.*;
import com.qdbank.mall.constant.payment.orderstatus.MobileReChargePayOrderStatus;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.PaymentService;
import com.qdbank.mall.order.business.handler.MobileRechargePaymenHandler;
import com.qdbank.mall.order.business.handler.VirtualPaymenHandler;
import com.qdbank.mall.request.order.OrderIDReqDTO;
import com.qdbank.mall.request.third.mobile.MobileNoticeReqDTO;
import com.qdbank.mall.request.third.payment.NoticeReqDTO;
import com.qdbank.mall.response.third.mobile.OrderFindResDTO;
import com.qdbank.mall.response.third.payment.QryPayResDTO;
import com.qdbank.mall.util.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName PaymentController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/21 9:44
 * @Version 1.0
 **/
@RestController
@Api(tags = "paymentController", description = "支付")
@RequestMapping("/third/bank")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    OrderService orderService;


    @ApiOperation("支付异步通知")
    @RequestMapping(value = "/notice", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult  notice( NoticeReqDTO reqDTO) {
        log.info("请求数据[{}]", JsonUtil.toJSONString(reqDTO));
        String orderid =reqDTO.getOrderid();
        //step 1 查询订单信息
        OrderDO order = orderService.qryNotNoticOrderByOrderSn(orderid);

        if(order==null){
            throw new ApiException(ResultCode.USER_ORDER_HAS_NOTIC);
        }
        //通知状态
        order.setNoticeStatus(Long.parseLong(Constant.HAS_NOTIC_STATUS));

        //处理机制
        paymentService.handlerNotic(order,reqDTO);

        return CommonResult.success( "通知成功");
    }


    /**
     * 针对异常关闭场景处理
     * @param reqDTO
     * @return
     */
    @ApiOperation("定时结果查询")
    @RequestMapping(value = "/qryOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult  qryOrder(@RequestBody OrderIDReqDTO reqDTO) {
        log.info("请求数据[{}]", JsonUtil.toJSONString(reqDTO));
        String orderid =reqDTO.getOrderSn();
        //step 1 查询订单信息
        OrderDO order = orderService.qryNotNoticOrderByOrderSn(orderid);

        if(order==null){
            throw new ApiException(ResultCode.USER_ORDER_HAS_NOTIC);
        }

        if(Constant.HAS_NOTIC_STATUS.equals(order.getNoticeStatus())){
            return CommonResult.success( "通知成功");
        }

        if(0L!=order.getStatus() && 4L!=order.getStatus()) {
            return CommonResult.success( "通知成功");
        }

        QryPayResDTO qryPayRes  =paymentService.qryOrderInfo(order);

        return CommonResult.success( "通知成功");
    }


    /*@ApiOperation("超时关单逻辑")
    @RequestMapping(value = "/timeOutOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult  timeOutOrder(@RequestBody OrderIDReqDTO reqDTO) {
        log.info("请求数据[{}]", JsonUtil.toJSONString(reqDTO));
        String orderid =reqDTO.getOrderSn();
        //step 1 查询订单信息
        OrderDO order = orderService.qryNotNoticOrderByOrderSn(orderid);

        if(order==null){
            throw new ApiException(ResultCode.USER_ORDER_HAS_NOTIC);
        }

        if(Constant.HAS_NOTIC_STATUS.equals(order.getNoticeStatus())){
            throw new ApiException(ResultCode.USER_ORDER_HAS_NOTIC);
        }

        if(!"0".equals(order.getStatus())){
            throw new ApiException(ResultCode.ORDER_STATUS_ERROR);
        }

        QryPayResDTO qryPayRes  =paymentService.qryOrderInfo(order);
        NoticeReqDTO notice= new NoticeReqDTO();
        notice.setAcctType(qryPayRes.getAcctType());
        notice.setOriTransDt(qryPayRes.getOrigChlDt());
        notice.setOriTransSer(qryPayRes.getOrigChlSerNo());
        notice.setStatus(qryPayRes.getRespStatus());
        handlerNotic(order,notice);
        return CommonResult.success( "通知成功");
    }*/


    @ApiOperation("虚拟充值结果查询")
    @RequestMapping(value = "/qryRechargeOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult  qryRechargeOrder(@RequestBody OrderIDReqDTO reqDTO) {

        log.info("请求数据[{}]", JsonUtil.toJSONString(reqDTO));
        String orderid =reqDTO.getOrderSn();
        //step 1 查询订单信息
        OrderDO order = orderService.qryOrderBySn(orderid);

        VirtualPaymenHandler virtualPaymenHandler =orderService.getHandler(order);

        if(order==null){
            throw new ApiException(ResultCode.USER_ORDER_HAS_NOT_FOUND);
        }

        //订单状态判断
        if(!MobileReChargePayOrderStatus.Status.CHARGING.status.equals(order.getStatus())){
           //网信充值成功，状态发生变更
            return CommonResult.success( "通知成功");
        }

        OrderFindResDTO res  =virtualPaymenHandler.orderFind(order);
        if(res==null){
           return CommonResult.failed( "查询异常");
        }
        String status = res.getStatus();
        MobileRechargeStatus statusEnum=MobileRechargeStatus.getName(status);
        if(statusEnum != MobileRechargeStatus.CHARGE_FAIL && statusEnum != MobileRechargeStatus.CHARGE_SUCCESS){
            return CommonResult.success( "充值中");
        }

        MobileNoticeReqDTO notice= new MobileNoticeReqDTO();
        notice.setStatus(res.getStatus());
        notice.setErrMsg(res.getErrMsg());
        virtualPaymenHandler.handlerNotic(order,notice);
        return CommonResult.success( "通知成功");
    }






}
