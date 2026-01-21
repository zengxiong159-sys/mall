package com.qdbank.mall.order.impl;

import org.springframework.beans.factory.annotation.Value;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.constant.payment.lifestatus.MobileRechargeOrderLifeStatus;
import com.qdbank.mall.constant.payment.PaymentStatusEnum;
import com.qdbank.mall.constant.payment.PaymentTypeEnum;
import com.qdbank.mall.constant.payment.metrics.MetricsHolder;
import com.qdbank.mall.coupon.CouponService;
import com.qdbank.mall.dao.order.OrderDao;
import com.qdbank.mall.dao.order.PaymentDao;
import com.qdbank.mall.dao.prefecture.PrefectureDao;
import com.qdbank.mall.dao.refund.RefundDao;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.order.OrderStatusDO;
import com.qdbank.mall.model.orderrefund.OrderRefundDO;
import com.qdbank.mall.model.orderrefund.OrderRefundWithOrderDetailDO;
import com.qdbank.mall.model.paymentFlow.PaymentFlowDO;
import com.qdbank.mall.model.product.ProductInfoQueryDO;
import com.qdbank.mall.model.product.ProductSkuDO;
import com.qdbank.mall.model.rechargestatusdetail.RechargeStatusDO;
import com.qdbank.mall.model.skustock.SkustockDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.PaymentService;
import com.qdbank.mall.order.business.handler.VirtualPaymenHandler;
import com.qdbank.mall.order.mapper.OrderMapper;
import com.qdbank.mall.order.mapper.RefundMapper;
import com.qdbank.mall.prefecture.PrefectureService;
import com.qdbank.mall.request.metrics.OrderMetricsReqDTO;
import com.qdbank.mall.request.third.payment.NoticeReqDTO;
import com.qdbank.mall.response.coupon.CouponResDTO;
import com.qdbank.mall.response.order.OrderResDTO;
import com.qdbank.mall.response.refund.OrderRefundResDTO;
import com.qdbank.mall.response.third.payment.RefundResDTO;
import com.qdbank.mall.service.PrometheusMicrometerService;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.util.BsnUtil;
import com.qdbank.mall.util.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Author hongjh
 * @Date 2021/3/17 19:41
 * @Version 1.0
 **/
@Service("orderService")
@Slf4j
@RefreshScope
public class OrderServiceImpl extends BaseServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    PaymentDao paymentDao;

    @Autowired
    RefundDao refundDao;

    @Autowired
    PrefectureService prefectureService;

    @Autowired
    PrefectureDao prefectureDao;

    @Autowired
    RefundMapper refundMapper;

    @Autowired
    PaymentService paymentService;

    @Autowired
    PrometheusMicrometerService prometheusMicrometerService;

    @Value(value = "${es.url}")
    private String esUrl;

    @Value(value = "${es.index.order}")
    private String orderEsIndex;

    @Autowired
    CouponService couponService;

    @Override
    public Integer createOrder(OrderDO orderDO) {
        //数据兜底
        orderDO.setProductPrice(BsnUtil.convertBigdecimal(orderDO.getProductPrice()));
        orderDO.setProductCash(BsnUtil.convertBigdecimal(orderDO.getProductCash()));
        orderDO.setProductIntegration(BsnUtil.convertLong(orderDO.getProductIntegration()));
        orderDO.setPayAmount(BsnUtil.convertBigdecimal(orderDO.getPayAmount()));
        orderDO.setOrderCash(BsnUtil.convertBigdecimal(orderDO.getOrderCash()));
        orderDO.setOrderIntegration(BsnUtil.convertLong(orderDO.getOrderIntegration()));
        orderDO.setFreightAmount(BsnUtil.convertBigdecimal(orderDO.getFreightAmount()));
        orderDO.setCouponAmount(BsnUtil.convertBigdecimal(orderDO.getCouponAmount()));
        orderDO.setDiscountAmount(BsnUtil.convertBigdecimal(orderDO.getDiscountAmount()));
        return orderDao.createOrder(orderDO);
    }

    @Override
    public OrderDO qryNotNoticOrderByOrderSn(String orderSn) {
        //查询未通知的订单号
        List<OrderDO> orders = orderDao.qryOrderByOrderStatusSn(orderSn, Constant.NOT_NOTIC_STATUS);
        if (!CollectionUtils.isEmpty(orders)) {
            return orders.get(0);
        }
        return null;
    }

    @Override
    public OrderDO qryOrderBySn(String orderSn) {
        //查询未通知的订单号
        List<OrderDO> orders = orderDao.qryOrderBySn(orderSn);
        if (!CollectionUtils.isEmpty(orders)) {
            return orders.get(0);
        }
        return null;
    }

    /**
     * 通过客户号和订单号更新相应状态
     *
     * @param custNo
     * @param oldOrder
     * @param orderStatusDO
     * @return
     */
    @Override
    public boolean updateUserOrderStatus(OrderDO uptOrderDO, String custNo, OrderDO oldOrder, OrderStatusDO orderStatusDO) {
        boolean flag = orderDao.updateUserOrderStatus(uptOrderDO, custNo, oldOrder.getOrderSn(), orderStatusDO) == 1;
        if (!flag) {
            throw new ApiException(ResultCode.ORDER_STATUS_ERROR);
        }
        //更新成功处理
        oldOrder.setStatus(orderStatusDO.getAfterStatus());
        oldOrder.setCloseType(orderStatusDO.getAfterCloseType());
        oldOrder.setRefundStatus(orderStatusDO.getAfterApproveStatus());
        return flag;
    }

    /**
     * 通过客户号和订单号更新支付时间
     *
     * @param custNo
     * @param orderSn
     * @param paymentTime
     * @return
     */
    @Override
    public boolean updatePaymentTime(String custNo, String orderSn, Date paymentTime) {
        boolean flag = orderDao.updatePaymentTime(custNo, orderSn, paymentTime) == 1;
        return flag;
    }


    /**
     * 通过客户号和订单号更新相应状态
     *
     * @param custNo
     * @param orderSn
     * @param noticFlag
     * @return
     */
    @Override
    public int noticOrderStatus(String custNo, String orderSn, Long noticFlag, NoticeReqDTO reqDTO) {
        //支付流水更新
        PaymentFlowDO flow = new PaymentFlowDO();
        flow.setUpdateTime(new Date());
        flow.setQueryType(reqDTO.getQueryType());
        flow.setAcctType(reqDTO.getAcctType());
        flow.setOriTransDt(reqDTO.getOriTransDt());
        flow.setOriTransser(reqDTO.getOriTransSer());
        String status = reqDTO.getStatus();
        Date paymentTime = null;
        if ("S".equals(status)) {
            status = PaymentStatusEnum.PAY_SUCCESS.payType;
            //如果支付成功，更新支付时间
            paymentTime = formatePaymentTime(reqDTO.getTimestamp());
        } else if ("L".equals(status)) {
            status = PaymentStatusEnum.PAY_FAIL.payType;
        }

        flow.setStatus(status);
        paymentDao.updatePaymentFlow(flow, orderSn);
        //更新通知状态&&支付时间
        return orderDao.updateUserOrderStatus(custNo, orderSn, noticFlag, paymentTime);
    }


    @Override
    public List<OrderResDTO> qryOrders(String custNo, String status, ProductEnum... productEnums) {
       if(org.apache.commons.lang3.StringUtils.isBlank(custNo)) return new ArrayList<OrderResDTO>();
        List<Long> pEnums = new ArrayList<>();
        for (ProductEnum productEnum : productEnums) {
            pEnums.add(productEnum.productType);
        }
        List<OrderDO> list = orderDao.qryOrderList(custNo, pEnums, Long.parseLong(status), true);
        List<OrderResDTO> res = orderMapper.poToDTOList(list);
        if (!CollectionUtils.isEmpty(res)) {
            res.forEach(item -> {
                ProductEnum productEnum =ProductEnum.getProductByType(item.getProductType());
                if (ProductEnum.PAYMENT_IN_KIND == productEnum) {
                    //获取商品规格信息
                    Long productId = item.getProductId();
                    Long productSkuId = item.getProductSkuId();
                    ProductInfoQueryDO query = new ProductInfoQueryDO();
                    query.setProductId(productId);
                    query.setProductSkuId(productSkuId);
                    if (productSkuId != null && productId != null) {
                        ProductSkuDO sku = prefectureService.qryProductSku(productId, productSkuId, false);
                        if (sku != null) {
                            item.setSpData(sku.getSkustocks().get(0).getProductSpData());
                            //规格图片
                            String skuPicUrl = sku.getSkustocks().get(0).getSkuPicUrl();
                            if (!StringUtils.hasText(skuPicUrl)) {
                                skuPicUrl = sku.getMailPicUrl();
                            }
                            item.setPicUrl(skuPicUrl);
                            item.setSingleProductCash(item.getProductCash());
                            item.setSingleProductIntegration(item.getProductIntegration());
                            item.setProductCash(item.getSingleProductCash().multiply(new BigDecimal(item.getProductCount())));
                            item.setProductIntegration(item.getProductCount() * item.getSingleProductIntegration());
                        }
                    }
                }
                else if(ProductEnum.INTEGRAL==productEnum){
                    CouponResDTO coupon = couponService.qryCouponDetailById(item.getExchangeCouponId());
                    item.setCouponRes(coupon);
                }
            });
        }
        return res;
    }


    @Override
    public List<OrderRefundResDTO> qryRefundOrderList(String custNo) {
        if(org.apache.commons.lang3.StringUtils.isBlank(custNo)) return new ArrayList<OrderRefundResDTO>();
        List<OrderRefundWithOrderDetailDO> list = orderDao.qryRefundOrderList(custNo, true);
        List<OrderRefundResDTO> res = refundMapper.refundPotoWithOrderDoList(list);
        if (!CollectionUtils.isEmpty(res)) {
            res.forEach(item -> {
                //获取商品规格信息
                Long productId = item.getProductId();
                Long productSkuId = item.getProductSkuId();
                ProductInfoQueryDO query = new ProductInfoQueryDO();
                query.setProductId(productId);
                query.setProductSkuId(productSkuId);
                if (productSkuId != null && productId != null) {
                    ProductSkuDO sku = prefectureService.qryProductSku(productId, productSkuId, false);
                    if (sku != null) {
                        item.setSpData(sku.getSkustocks().get(0).getProductSpData());
                        //规格图片
                        String skuPicUrl = sku.getSkustocks().get(0).getSkuPicUrl();
                        if (!StringUtils.hasText(skuPicUrl)) {
                            skuPicUrl = sku.getMailPicUrl();
                        }
                        item.setPicUrl(skuPicUrl);
                     /*   item.setSingleProductCash(sku.getSkustocks().get(0).getProductCash());
                        item.setSingleProductIntegration(sku.getSkustocks().get(0).getProductIntegration());*/
                    }
                }
                boolean hasIntegration = item.getSingleProductIntegration() != null && item.getSingleProductIntegration() > 0;
                // 支付方式 积分 和 现金判断
                BigDecimal a_0 = new BigDecimal(0);
                if (hasIntegration && (a_0.compareTo(item.getSingleProductCash()) < 0)) {
                    //有积分 且 运费和实付款 均有钱，则为积分和钱一起
                    item.setPayType(PaymentTypeEnum.MONEY_SCORE.payType);
                } else if (hasIntegration) {
                    item.setPayType(PaymentTypeEnum.SCORE.payType);
                } else {
                    item.setPayType(PaymentTypeEnum.MONEY.payType);
                }
            });
        }
        return res;
    }


    @Override
    public boolean updateRefundStatus(OrderRefundDO orderRefundDO, String custNo, String orderSn, Long beforeStatus, Long afterStatus) {
        boolean flag = refundDao.updateRefundStatus(orderRefundDO, custNo, orderSn, beforeStatus, afterStatus) == 1;
        if (!flag) {
            throw new ApiException(ResultCode.APPROVE_ORDER_STATUS_ERROR);
        }
        return flag;
    }

    @Override
    public void createApplyRefund(OrderRefundDO orderRefund) {
        refundDao.createApplyRefund(orderRefund);
    }

    @Override
    public OrderRefundWithOrderDetailDO qryApplyRefund(String refundSeriNo) {
        return refundDao.qryOrderRefundBySeriNo(refundSeriNo);
    }


    /**
     * 创建状态流程
     *
     * @param orderSn
     * @param detailLevel
     * @param status
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void createProcessStatus(String orderSn, Long detailLevel, Long status) {
        RechargeStatusDO dto = new RechargeStatusDO();
        Date now = new Date();
        dto.setCreateTime(now);
        dto.setOrderSn(orderSn);
        dto.setDetailLevel(detailLevel);
        dto.setStatus(status);
        dto.setId(super.generateId());
        int count = orderDao.createProcessStatus(dto);
        if (count == 0) {
            throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
        }
    }


    /**
     * 删除状态流程
     *
     * @param orderSn
     * @param status
     */
    @Override
    public void rmProcessStatus(String orderSn, Long status) {
        int count = orderDao.rmProcessStatus(orderSn, status);
    }


    /**
     * 查询流水号
     *
     * @param orderSn
     */
    @Override
    public List<RechargeStatusDO> qryProcessStatus(String orderSn) {
        return orderDao.qryProcessStatus(orderSn);
    }


    /**
     * 统计订单产品类型及枚举
     *
     * @param status
     * @param productEnums
     * @return
     */
    @Override
    public Long countOrderByProductTypeAndStatus(String custNo, Long status, ProductEnum... productEnums) {
        Long[] productTypes = null;
        if (productEnums != null) {
            productTypes = new Long[productEnums.length];
            for (int i = 0; i < productEnums.length; i++) {
                productTypes[i] = productEnums[i].productType;
            }
        }
        return orderDao.countOrderByProductTypeAndStatus(custNo, status, productTypes);
    }


    @Override
    public OrderRefundResDTO realRefundDetail(String refundSeriNo) {

        OrderRefundWithOrderDetailDO refundDO = this.qryApplyRefund(refundSeriNo);

        if (refundDO == null) {
            throw new ApiException(ResultCode.USER_ORDER_HAS_NOT_FOUND);
        }

        OrderRefundResDTO res = refundMapper.refundPotoWithOrderDo(refundDO);


        BigDecimal resFreightAmount = res.getFreightAmount();
        BigDecimal refundCash = res.getRefundCash();

        resFreightAmount = resFreightAmount == null ? new BigDecimal(0) : resFreightAmount;
        refundCash = refundCash == null ? new BigDecimal(0) : refundCash;
        //支付类型
        OrderDO order = this.qryOrderBySn(refundDO.getOrderSn());
        res.setPayType(order.getPayType());

        //查询商品规格信息--肯定存在
        Long productId = refundDO.getProductId();
        Long productSkuId = refundDO.getProductSkuId();

        if (productId != null && productSkuId != null) {
            ProductSkuDO sku = prefectureService.qryProductSku(productId, productSkuId, false);
            if (sku != null) {
                res.setSpData(sku.getSkustocks().get(0).getProductSpData());
                //规格图片
                String skuPicUrl = sku.getSkustocks().get(0).getSkuPicUrl();
                if (!StringUtils.hasText(skuPicUrl)) {
                    skuPicUrl = sku.getMailPicUrl();
                }
                res.setPicUrl(skuPicUrl);
               /* res.setSingleProductCash(sku.getSkustocks().get(0).getProductCash());
                res.setSingleProductIntegration(sku.getSkustocks().get(0).getProductIntegration());*/
            }
        }

        return res;
    }


    /**
     * 发起post请求调用
     *
     * @param order 请求参数DTO
     */
    @Override
    public void sendPost(OrderDO order, MetricsHolder holder, String msg) {
        if (holder == null) {
            return;
        }

        OrderMetricsReqDTO request = new OrderMetricsReqDTO();
        //索引
        request.setIndex(orderEsIndex);
        request.setOrderSn(order.getOrderSn());

        ProductEnum productEnum = ProductEnum.getProductByType(order.getProductType());
        request.setOrderProductType("" + productEnum.productType);
        request.setOrderProductTypeDesc(productEnum.desc);

        //主信息
        request.setOrderMetricsType(holder.getMetricsEnum().metricsType);
        request.setOrderMetricsTypeDesc(holder.getMetricsEnum().desc);

        //次信息
        if (holder.getOrderRefundMetricsEnum() != null) {
            request.setOrderRefundMetricsType(holder.getOrderRefundMetricsEnum().metricsType);
            request.setOrderRefundMetricsTypeDesc(holder.getOrderRefundMetricsEnum().desc);
        }

        request.setErrorMsg(msg);


        this.prometheusMicrometerService.sendPost(esUrl, request);
    }


    private Date formatePaymentTime(String timeTamp) {
        try {
            Long time = Long.parseLong(timeTamp);
            Date date = new Date(time);
            return date;
        } catch (Exception e) {
            log.error("日期解析失败[{}-{}]", new Object[]{timeTamp}, e);
        }
        return new Date();
    }


    @Override
   public RefundResDTO errorRefund(String id){
        OrderService orderService = (OrderService) SpringContextUtils.getBean("orderService");
       OrderRefundDO orderRefundDO = refundDao.qryOrderRefund(Long.parseLong(id));
       OrderDO order =orderDao.qryOrderByRefundSerino(id);
       log.info("发起退款:[{}]",order.getOrderSn());
       RefundResDTO res  =paymentService.refundOrder(orderRefundDO,order,null);

       ProductEnum productEnum = ProductEnum.getProductByType(order.getProductType());
       if(res!=null){
           if(productEnum==ProductEnum.MOBILE_RECHARGE || productEnum==ProductEnum.OIL_CARD){
               orderService.createProcessStatus(order.getOrderSn(), MobileRechargeOrderLifeStatus.CREATE_REFUND.status,MobileRechargeOrderLifeStatus.CREATE_REFUND.status);
               orderService.createProcessStatus(order.getOrderSn(), MobileRechargeOrderLifeStatus.REFUND_SUCCESS.status,MobileRechargeOrderLifeStatus.REFUND_SUCCESS.status);
           }
           //更新完成时间
            refundDao.updateRefundHandlerTime(Long.parseLong(id));
       }else{
           //退款失败
           if(productEnum==ProductEnum.MOBILE_RECHARGE || productEnum==ProductEnum.OIL_CARD){
               orderService.createProcessStatus(order.getOrderSn(), MobileRechargeOrderLifeStatus.CREATE_REFUND.status,MobileRechargeOrderLifeStatus.CREATE_REFUND.status);
               orderService.createProcessStatus(order.getOrderSn(), MobileRechargeOrderLifeStatus.REFUND_FAIL.status,MobileRechargeOrderLifeStatus.REFUND_FAIL.status);
           }
       }
       return res;
   }



    /**
     *
     * @param productPrice  商品折算价
     * @param productCash 商品金额
     * @param productIntegration 商品积分
     * @param productCount 购买数量
     * @param couponAmount 优惠券面值
     * @param freightAmount 运费
     * @return
     */
    @Override
    public  void orderAmt(OrderDO order,Long productCount,BigDecimal productPrice,BigDecimal productCash,Long productIntegration,BigDecimal couponAmount,BigDecimal freightAmount) {
        if (productPrice == null) {
            productPrice = new BigDecimal(0);
        }

        if (productCash == null) {
            productCash = new BigDecimal(0);
        }

        if (productIntegration == null) {
            productIntegration = 0L;
        }

        if (productCount == null || productCount < 1) {
            throw new RuntimeException("数量大于0");
        }

        if (couponAmount == null) {
            couponAmount = new BigDecimal(0);
        }
        order.setCouponAmount(couponAmount);

        if (freightAmount == null) {
            freightAmount = new BigDecimal(0);
        }
        order.setFreightAmount(freightAmount);

        //订单积分--此版本以商品规则为准
        boolean hasIntegration = true;
        if (productIntegration == 0L) {
            hasIntegration = false;
        }

        BigDecimal num = new BigDecimal(productCount);
        //商品折算价
        order.setProductPrice(num.multiply(BsnUtil.convertBigdecimal(productPrice)));
        //商品售价中现金金额
        order.setProductCash(num.multiply(BsnUtil.convertBigdecimal(productCash)));
        //商品售价中积分量
        order.setProductIntegration(productCount * (productIntegration));

        //订单实付款(折算价)
        //**7.17** 支付折算价 含运费逻辑
        order.setPayAmount(productPrice.subtract(couponAmount).add(freightAmount));
        BigDecimal a_0 = new BigDecimal(0);

        //订单现金:包含商品售价中现金金额-优惠金额
        order.setOrderCash(productCash.subtract(couponAmount));
        //订单积分
        order.setOrderIntegration(productIntegration);

        /**
         * ***********************重要*****************
         */
        //商品折算价
        order.setProductPrice(productPrice);
        //商品售价中现金金额
        order.setProductCash(productCash);
        //商品售价中积分量
        order.setProductIntegration(productIntegration);

        // 支付方式 积分 和 现金判断
        if (hasIntegration && (a_0.compareTo(freightAmount.add(order.getOrderCash())) < 0)) {
            //有积分 且 运费和实付款 均有钱，则为积分和钱一起
            order.setPayType(PaymentTypeEnum.MONEY_SCORE.payType);
        } else if (hasIntegration) {
            order.setPayType(PaymentTypeEnum.SCORE.payType);
        } else {
            order.setPayType(PaymentTypeEnum.MONEY.payType);
        }
    }



    @Override
    public void initOrderCash(OrderDO order, SkustockDO skustock){
        orderAmt( order,order.getProductCount(),skustock.getProductPrice(),skustock.getProductCash(),skustock.getProductIntegration(),order.getCouponAmount(),order.getFreightAmount());
    }







    @Override
    public VirtualPaymenHandler getHandler(OrderDO order){
        ProductEnum productEnum = ProductEnum.getProductByType(order.getProductType());
        VirtualPaymenHandler virtualPaymenHandler = (VirtualPaymenHandler) SpringContextUtils.getBean(productEnum.handlerName);
        return virtualPaymenHandler;
    }


}
