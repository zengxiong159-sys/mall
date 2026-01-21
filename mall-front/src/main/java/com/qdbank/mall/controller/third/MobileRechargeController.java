package com.qdbank.mall.controller.third;

import com.qdbank.mall.annotation.ExcludeHandler;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.constant.payment.orderstatus.MobileReChargePayOrderStatus;
import com.qdbank.mall.constant.payment.MobileRechargeStatus;
import com.qdbank.mall.order.business.handler.MobileRechargePaymenHandler;
import com.qdbank.mall.order.business.handler.VirtualPaymenHandler;
import com.qdbank.mall.request.third.virtual.VirtualGoodsNoticeReqDTO;
import com.qdbank.mall.third.MobileProxySendService;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mobile.MobileService;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.PaymentService;
import com.qdbank.mall.request.third.mobile.MobileNoticeReqDTO;
import com.qdbank.mall.third.VirsualProxySendService;
import com.qdbank.mall.util.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName PaymentController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/21 9:44
 * @Version 1.0
 **/
@RestController
@Api(tags = "mobileRechargeController", description = "支付")
@RequestMapping("/third/wx")
@Slf4j
public class MobileRechargeController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    OrderService orderService;

    @Autowired
    MobileProxySendService mobileProxySendService;

    @Autowired
    MobileService mobileService;

    @Autowired
    MobileRechargePaymenHandler mobileRechargePaymenHandler;

    @Autowired
    @Qualifier("oilCardPayment")
    VirtualPaymenHandler virtualPaymenHandler;

    @Autowired
    VirsualProxySendService virsualProxySendService;





    @ApiOperation("话费充值异步通知")
    @ExcludeHandler
    @RequestMapping(value = "/notice", method = RequestMethod.POST)
    public String notice(@RequestBody Map params) {

        log.info("请求数据[{}]", JsonUtil.toJSONString(params));
        Map<String,String> _header = (Map) params.get("header");
        Map<String,String> _body = (Map) params.get("body");
        //0812 签名处理
        _body.put("ext_content",null);
        boolean flag =mobileProxySendService.valide(_header,_body);

        log.info("验签-[{}]",flag);
        //验签不过报错
        if(!flag){
            return "fail";
        }

        String kh_order_id=_body.get("kh_order_id");
        String orderid=_body.get("order_id");
        String type=_body.get("type");
        String status=_body.get("status");
        String kh_settle_time=_body.get("kh_settle_time");
        String err_msg=_body.get("err_msg");
        String kh_settle_price=_body.get("kh_settle_price");
        String ext_content=_body.get("ext_content");

        MobileNoticeReqDTO reqDTO = new MobileNoticeReqDTO();

       reqDTO.setOrderId(orderid);
       reqDTO.setKhOrderId(kh_order_id);
        reqDTO.setType(type);
        reqDTO.setStatus(status);
        reqDTO.setKhSettlePrice(kh_settle_price);
        reqDTO.setKhSettleTime(kh_settle_time);
        reqDTO.setErrMsg(err_msg);
        reqDTO.setExtContent(ext_content);

        MobileRechargeStatus statusEnum=MobileRechargeStatus.getName(status);
        if(statusEnum != MobileRechargeStatus.CHARGE_FAIL && statusEnum != MobileRechargeStatus.CHARGE_SUCCESS){
            return "fail";
        }

        //step 1 查询订单信息
        OrderDO order =orderService.qryOrderBySn(kh_order_id);


        if(order==null){
            throw new ApiException(ResultCode.USER_ORDER_HAS_NOT_FOUND);
        }

        log.info("订单信息-[{}]",JsonUtil.toJSONString(order));
        //订单非充值中状态，报错
        if(!MobileReChargePayOrderStatus.Status.CHARGING.status.equals(order.getStatus())){
            throw new ApiException(ResultCode.ORDER_STATUS_ERROR);
        }

        order.setRechargeRemark(reqDTO.getErrMsg());

        //处理机制
       this.mobileRechargePaymenHandler.handlerNotic(order,reqDTO);
        return  "success";
    }



    @ApiOperation("产品上下架通知")
    @ExcludeHandler
    @RequestMapping(value = "/productNotic", method = RequestMethod.POST)
    public String productNotic(@RequestBody Map params) {

        log.info("请求数据[{}]", JsonUtil.toJSONString(params));
        Map<String,String> _header = (Map) params.get("header");
        Map<String,String> _body = (Map) params.get("body");

        boolean flag =mobileProxySendService.valide(_header,_body);

        log.info("验签-[{}]",flag);
        if(!flag){
            return  "false";
        }

        String provinces=_body.get("provinces");
        String operator=_body.get("operator");
        String price=_body.get("price");
        String status=_body.get("status");

        mobileService.noticMobileSkuProvinceStatus(operator,provinces,price,Long.parseLong(status));
        return  "success";
    }



    @ApiOperation("虚拟商品充值异步通知")
    @ExcludeHandler
    @RequestMapping(value = "/virtualGoodsChargeNotic", method = RequestMethod.POST)
    public String virtualGoodsNotic(@RequestBody VirtualGoodsNoticeReqDTO params) {
        String json = JsonUtil.toJSONString(params);
        log.info("请求数据[{}]", json);


        Map signMap = JsonUtil.parseObject(json,Map.class);

        boolean flag =virsualProxySendService.valide(signMap);

        log.info("验签-[{}]",flag);
        //验签不过报错
        if(!flag){
            return "fail";
        }

        MobileNoticeReqDTO reqDTO = new MobileNoticeReqDTO();

        //     reqDTO.setOrderId("");
        reqDTO.setKhOrderId(params.getCusOrderId());
        reqDTO.setType(params.getType());
        reqDTO.setStatus(params.getOrderState());
        //       reqDTO.setKhSettlePrice(kh_settle_price);
        //       reqDTO.setKhSettleTime(kh_settle_time);
        reqDTO.setErrMsg(params.getResultState());
        //       reqDTO.setExtContent(ext_content);

        MobileRechargeStatus statusEnum=MobileRechargeStatus.getName(reqDTO.getStatus());
        if(statusEnum != MobileRechargeStatus.CHARGE_FAIL && statusEnum != MobileRechargeStatus.CHARGE_SUCCESS){
            return "fail";
        }

        //step 1 查询订单信息
        OrderDO order =orderService.qryOrderBySn(reqDTO.getKhOrderId());

        if(order==null){
            throw new ApiException(ResultCode.USER_ORDER_HAS_NOT_FOUND);
        }

        log.info("订单信息-[{}]",JsonUtil.toJSONString(order));
        //订单非充值中状态，报错
        if(!MobileReChargePayOrderStatus.Status.CHARGING.status.equals(order.getStatus())){
            throw new ApiException(ResultCode.ORDER_STATUS_ERROR);
        }

        order.setRechargeRemark(reqDTO.getErrMsg());

        //处理机制
        this.mobileRechargePaymenHandler.handlerNotic(order,reqDTO);
        return  "succeed";
    }


    @ApiOperation("虚拟产品上下架通知")
    @ExcludeHandler
    @RequestMapping(value = "/virtualProductNotic", method = RequestMethod.POST)
    public String virtualProductNotic(@RequestBody Map params) {

        log.info("请求数据[{}]", JsonUtil.toJSONString(params));
        boolean flag =virsualProxySendService.valide(params);

        log.info("验签-[{}]",flag);
        //验签不过报错
        if(!flag){
            return "fail";
        }

        String kactivityId= (String) params.get("kactivityId");
        String status= (String) params.get("status");

        virtualPaymenHandler.deployProduct(kactivityId,status);
        return  "succeed";
    }









}
