package com.qdbank.mall.order.business.handler;

import org.springframework.beans.factory.annotation.Value;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.bo.VirtualConfig;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.payment.*;
import com.qdbank.mall.constant.payment.lifestatus.MobileRechargeOrderLifeStatus;
import com.qdbank.mall.exception.MobileException;
import com.qdbank.mall.order.PaymentService;
import com.qdbank.mall.order.business.VirtualConfigHandler;
import com.qdbank.mall.third.MobileProxySendService;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mobile.MobileService;
import com.qdbank.mall.model.mobileSku.MobileSkuDO;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.product.VirtualProductDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.request.order.CommonOrderReqDTO;
import com.qdbank.mall.request.order.MobileRechargePaymenOrderReqDTO;
import com.qdbank.mall.response.third.mobile.OrderFindResDTO;
import com.qdbank.mall.response.third.mobile.PhoneResDTO;
import com.qdbank.mall.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;

/**
 * 虚拟商品支付
 * @ClassName MobileRechargePaymenHandler
 * @Description TODO
 * @Author hongjh
 * @Date 2021/3/17 19:41
 * @Version 1.0
 **/
@Component("mobileRechargePayment")
@Slf4j
@RefreshScope
public  class MobileRechargePaymenHandler extends VirtualPaymenHandler {


    @Value(value = "${third.mall.url.mobileRecharge:}")
    private String mallUrlMobileRechargeUrl;

    @Autowired
    MobileService mobileService;

    @Autowired
    OrderService orderService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    MobileProxySendService mobileProxySendService;

    @Autowired
    VirtualConfigHandler virtualConfigHandler;


    @Override
    public ProductEnum getProductType() {
        return ProductEnum.MOBILE_RECHARGE;
    }

    @Override
    public void preCreateOrderCheck(OrderDO order, CommonOrderReqDTO commonOrderReq) {
        MobileRechargePaymenOrderReqDTO req = (MobileRechargePaymenOrderReqDTO) commonOrderReq;
        //手机规格
        Long mobileSkuId=req.getMobileSkuId();
        //运营商code
        String supplierType=req.getSupplierType();

        //获取信息
        MobileSkuDO mobileSku=mobileService.qryMobileSkuByMobileSkuId(supplierType,mobileSkuId);
        if (mobileSku==null) {
            throw new ApiException(ResultCode.PRODUCT_NOT_DEPLOY);
        }

        //积分结算标识
        order.setIntegrationPayFlag(Constant.INTEGRATION_FLAG_NOT);
        //通过产品获取商户信息
        VirtualProductDO virtualProduct  =this.getVirtualProduct(mobileSku.getProductId());
        order.setMerchantNo(virtualProduct.getMerchantNo());
        order.setMerchantName(virtualProduct.getMerchantName());
        order.setProductId(mobileSku.getProductId());
        //地区+产品名称
        order.setProductName(req.getMobileAddressDesc()+mobileSku.getProductName());
        order.setProductSkuId(mobileSkuId);


        VirtualConfig config = virtualConfigHandler.getVirtualConfig(getProductType());
        Map categegoty = config.getCategegoty();
        order.setCategoryId1(Long.parseLong(categegoty.get("cid1").toString()));
        order.setCategoryId2(Long.parseLong(categegoty.get("cid2").toString()));
        order.setCategoryId3(Long.parseLong(categegoty.get("cid3").toString()));
        order.setCategoryId4(Long.parseLong(categegoty.get("cid4").toString()));

        order.setProductCount(1L);

        //现金支付
        order.setPayType(PaymentTypeEnum.MONEY.payType);

        //商品折算价
        order.setProductPrice(mobileSku.getProductPrice());
        //商品售价中现金金额
        order.setProductCash(mobileSku.getProductPrice());
        //商品售价中积分量
        order.setProductIntegration(0L);

        order.setFreightAmount(new BigDecimal(0));

        //订单实付款(折算价)
        BigDecimal couponAmount =order.getCouponAmount();
        order.setPayAmount(order.getProductPrice().subtract(couponAmount));
        BigDecimal a_0 = new BigDecimal(0);
        //如果支付金额小于0
        if(order.getPayAmount().compareTo(a_0)<0){
            //TODO 如何处理
        }

        //订单现金:包含商品售价中现金金额-优惠金额
        order.setOrderCash(order.getProductCash().subtract(couponAmount));
        order.setOrderIntegration(0L);

        //兑换优惠券编号
    //    order.setUserCouponId(req.getUserCouponId());

        //充值手机号
        order.setRechargeMobile(req.getRechargeMobile());
        //号码归属地-
        order.setMobileAddress(req.getMobileAddressDesc());

    }


    /**
     * 充值
     * @param orderDO
     * @return
     */
    public String chargeOrderProcess(OrderDO orderDO){

        String wxOrderId =mobileProxySendService.chargeOrderProcess(orderDO);
        return wxOrderId;
    }

    /**
     * 手机归属ra
     * @param phone
     * @return
     */
    public PhoneResDTO phone(String phone){
        PhoneResDTO res  =mobileProxySendService.phone(phone);
        return res;
    }

    /**
     * 订单号查询
     * @param order
     * @return
     */
    @Override
    public OrderFindResDTO orderFind(OrderDO order){
        OrderFindResDTO res = null;
        try{
             res  =mobileProxySendService.orderFind(order.getOrderSn());
        }catch (MobileException e){
            /**
             *交易不存在(下单半小时之后出现该状态在判定为失败)
             * 2 min冗余
             */
            if("220005".equals(e.getCode())){
                Calendar now = Calendar.getInstance();
                now.add(Calendar.MINUTE,-32);
                if(now.getTime().compareTo(order.getPaymentTime()) > 0){
                    log.error("订单支付成功后超时30min，显示不存在交易[{}]-[{}]",order.getOrderSn(),order.getPaymentTime());
                    res = new OrderFindResDTO();
                    res.setStatus(MobileRechargeStatus.CHARGE_FAIL.code);
                }
            }
        }
        return res;
    }


    @Override
    public String charge(OrderDO order){
        //step 2 对接话费充值
        String wxOrderId=chargeOrderProcess(order);

        mobileService.createMobileFlow(order.getCustNo(),order.getRechargeMobile(),order.getProductSkuId(),order.getOrderSn(), MobileRechargeStatus.CHARGEING,wxOrderId);
        log.info("话费充值信息[{}]-[{}]",wxOrderId, JsonUtil.toJSONString(order));
        orderService.createProcessStatus(order.getOrderSn(), MobileRechargeOrderLifeStatus.CHARGEING.status,MobileRechargeOrderLifeStatus.CHARGEING.status);
        return wxOrderId;
    }




    @Override
    public String payBackJumpUrl() {
        return mallUrlMobileRechargeUrl;
    }



}
