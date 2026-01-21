package com.qdbank.mall.controller.third;

import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.constant.payment.event.RealOrderHandlerEvent;
import com.qdbank.mall.controller.order.CommonBsn;
import com.qdbank.mall.dao.order.OrderDao;
import com.qdbank.mall.dao.order.RefundErrorLogDao;
import com.qdbank.mall.dao.refund.RefundDao;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.refundErrorLog.RefundErrorLogDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.PaymentService;
import com.qdbank.mall.order.mapper.OrderMapper;
import com.qdbank.mall.order.mapper.RefundMapper;
import com.qdbank.mall.request.CommonStringIDReqDTO;
import com.qdbank.mall.request.order.OrderIDReqDTO;
import com.qdbank.mall.response.order.OrderResDTO;
import com.qdbank.mall.response.third.payment.RefundResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/15 15:34
 * @Version 1.0
 **/
@RestController
@Api(tags = "bankController", description = "前置订单公共管理")
@RequestMapping("/third/backmanager")
@Slf4j
public class BackManagerOrderController extends CommonBsn {

    @Autowired
    PaymentService paymentService;

    @Autowired
    OrderService orderService;

    @Autowired
    RefundMapper refundMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderDao orderDao;

    @Autowired
    RefundDao refundDao;

    @Autowired
    RefundErrorLogDao refundErrorLogDao;

    private ProductEnum PRODUCT_TYPE = ProductEnum.PAYMENT_IN_KIND;



    @ApiOperation("审核通过")
    @RequestMapping(value = "/ok", method = RequestMethod.POST)
    public CommonResult<OrderResDTO> confirmOrderReceive(@Validated @RequestBody OrderIDReqDTO commonIDReqDTO){
        log.info("退款订单：{}开始",commonIDReqDTO.getOrderSn());
        Map params = new HashMap();
        params.put("confirmStatus",true);
        Message message =paymentService.handlerOrder(commonIDReqDTO.getOrderSn()+"", RealOrderHandlerEvent.APPROVAL,params);
        CommonResult<OrderResDTO> commonResult = CommonResult.success(orderMapper.poToDo((OrderDO) message.getHeaders().get(Constant.ORDER_STR)));
        log.info("退款订单：{}响应",commonIDReqDTO.getOrderSn());
        return commonResult;
    }


    @ApiOperation("审核失败")
    @RequestMapping(value = "/no", method = RequestMethod.POST)
    public CommonResult<OrderResDTO> applyRefund(@Validated @RequestBody OrderIDReqDTO orderIDReqDTO){
        Map params = new HashMap();
        params.put("confirmStatus",false);
        Message message =paymentService.handlerOrder(orderIDReqDTO.getOrderSn()+"", RealOrderHandlerEvent.APPROVAL,params);
        return CommonResult.success(orderMapper.poToDo((OrderDO) message.getHeaders().get(Constant.ORDER_STR)));
    }


    @ApiOperation("发起异常退款")
    @RequestMapping(value = "/errorRefund", method = RequestMethod.POST)
    public CommonResult errorRefund(@Validated @RequestBody CommonStringIDReqDTO commonStringIDReqDTO){
        //判断是否存在异常记录
        RefundErrorLogDO refundErrorLog  =refundErrorLogDao.qryRefundErrorLogBySerino(commonStringIDReqDTO.getId());
        if(refundErrorLog==null || "1".equals(refundErrorLog.getStatus())){
            throw new ApiException(ResultCode.REFUND_HAS_ERROR);
        }

        RefundResDTO res =orderService.errorRefund(commonStringIDReqDTO.getId());
        if(res==null){
            throw new ApiException(ResultCode.REFUND_ERROR);
        }

        return CommonResult.success(1);
    }












}
