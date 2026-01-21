package com.qdbank.mall.order.business.handler;

import org.springframework.beans.factory.annotation.Value;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.constant.payment.lifestatus.IntegralOrderLifeStatus;
import com.qdbank.mall.constant.payment.PaymentTypeEnum;
import com.qdbank.mall.coupon.CouponService;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.orderrefund.OrderRefundDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.business.CommonPaymentAware;
import com.qdbank.mall.request.order.CommonOrderReqDTO;
import com.qdbank.mall.request.order.IntegralOrderReqDTO;
import com.qdbank.mall.response.coupon.CouponResDTO;
import com.qdbank.mall.response.third.payment.RefundResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.util.BsnUtil;
import com.qdbank.mall.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @ClassName IntegralPaymentHandler
 * @Description TODO
 * @Author hongjh
 * @Date 2021/3/17 19:41
 * @Version 1.0
 **/
@Component("integralPaymentHandler")
@Slf4j
@RefreshScope
public class IntegralPaymentHandler extends BaseServiceImpl implements CommonPaymentAware {

    @Value(value = "${third.mall.url.integral:}")
    private String mallUrlIntegralUrl;

    @Autowired
    CouponService couponService;

    @Autowired
    OrderService orderService;


    @Override
    public ProductEnum getProductType() {
        return ProductEnum.INTEGRAL;
    }

    @Override
    public void preCreateOrderCheck(OrderDO order, CommonOrderReqDTO commonOrderReq) {
        IntegralOrderReqDTO req = (IntegralOrderReqDTO) commonOrderReq;

        //获取券码对应信息
        Long exchangeCouponId =req.getExchangeCouponId();
        CouponResDTO res =couponService.qryCouponDetailById(exchangeCouponId);

        if(1L!=res.getProductStatus()){
            throw new ApiException(ResultCode.PRODUCT_NOT_DEPLOY);
        }

        //积分支付
        order.setPayType(PaymentTypeEnum.SCORE.payType);



        BigDecimal init = new BigDecimal(0L);
        //商品折算价
        order.setProductPrice(BsnUtil.integraToAmt(res.getProductIntegration()));
        //商品售价中现金金额
        order.setProductCash(init);
        //商品售价中积分量
        order.setProductIntegration(res.getProductIntegration());


        //订单实付款(折算价)--不存在优惠券，所以直接使用商品折算价
        order.setPayAmount(BsnUtil.integraToAmt(res.getProductIntegration()));
        //订单现金:包含商品售价中现金金额-优惠金额
        order.setOrderCash(init);
        order.setOrderIntegration(res.getProductIntegration());

        order.setFreightAmount(new BigDecimal(0));


        order.setProductName(res.getCouponName());
        order.setProductId(res.getCouponId());
        order.setMerchantNo(0L);
        order.setMerchantName("青岛银行信用卡商城");
        //数量固定1
        order.setProductCount(1L);
 //       order.setMerchantNo(res);

        //兑换优惠券编号
        order.setExchangeCouponId(exchangeCouponId);
        //积分结算标识--兑换券不结算
        order.setIntegrationPayFlag(Constant.INTEGRATION_FLAG_NOT);

    }

    @Override
    public void afterCreateOrder(OrderDO order, CommonOrderReqDTO commonOrderReq) {
        orderService.createProcessStatus(order.getOrderSn(),IntegralOrderLifeStatus.INIT.status,IntegralOrderLifeStatus.INIT.status);
        orderService.createProcessStatus(order.getOrderSn(), IntegralOrderLifeStatus.PREPARE_PAY.status,IntegralOrderLifeStatus.PREPARE_PAY.status);
        orderService.createProcessStatus(order.getOrderSn(),IntegralOrderLifeStatus.PAY.status,IntegralOrderLifeStatus.PAY.status);
    }

    @Override
    public String payBackJumpUrl() {
        return mallUrlIntegralUrl;
    }


    @Override
    public void handlerRefund(OrderDO order, OrderRefundDO orderRefundDO, RefundResDTO res) {
        orderRefundDO.setRefundNote("支付后超时异常关闭");
        orderRefundDO.setRefundCash(order.getOrderCash());
        // 退款折算价
        orderRefundDO.setRefundAmount(order.getPayAmount());
        //积分--不存在
        orderRefundDO.setRefundIntegration(order.getOrderIntegration());

        log.info("新增记录[{}]", JsonUtil.toJSONString(orderRefundDO));
        orderService.createApplyRefund(orderRefundDO);
    }
}
