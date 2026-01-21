package com.qdbank.mall.order.impl;
import com.alibaba.fastjson.JSON;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.qdbank.mall.annotation.Lock;
import com.qdbank.mall.annotation.LockName;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.bo.UserDetails;
import com.qdbank.mall.common.ParamsService;
import com.qdbank.mall.constant.CouponEnum;
import com.qdbank.mall.constant.payment.event.IntegralOrderEvent;
import com.qdbank.mall.constant.payment.event.MobileReChargePayOrderEvent;
import com.qdbank.mall.constant.payment.event.OrderEventAware;
import com.qdbank.mall.constant.payment.event.RealOrderHandlerEvent;
import com.qdbank.mall.constant.payment.metrics.MetricsEnum;
import com.qdbank.mall.constant.payment.metrics.MetricsHolder;
import com.qdbank.mall.constent.payment.RefundStatausEnum;
import com.qdbank.mall.dao.coupon.CouponDao;
import com.qdbank.mall.dao.order.PaymentDao;
import com.qdbank.mall.dao.order.RefundErrorLogDao;
import com.qdbank.mall.domain.CouponMQBO;
import com.qdbank.mall.integral.IntegralService;
import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.orderrefund.OrderRefundDO;
import com.qdbank.mall.model.paymentFlow.PaymentFlowDO;
import com.qdbank.mall.model.refundErrorLog.RefundErrorLogDO;
import com.qdbank.mall.mq.CouponMqSendService;
import com.qdbank.mall.order.mapper.RefundMapper;
import com.qdbank.mall.response.coupon.CouponResDTO;
import com.qdbank.mall.request.integral.IntegralBalanceReqDTO;
import com.qdbank.mall.response.coupon.UserCouponResDTO;
import com.qdbank.mall.service.SendCouponMqService;
import com.qdbank.mall.response.feign.integral.IntegralDetail;
import com.qdbank.mall.third.BankProxySendService;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.payment.*;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.coupon.CouponService;
import com.qdbank.mall.dao.merchant.MerchantDao;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.model.merchant.MerchantDO;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.order.OrderStatusDO;
import com.qdbank.mall.order.*;
import com.qdbank.mall.order.business.CommonPaymentAware;
import com.qdbank.mall.order.mq.TimeOutHandler;
import com.qdbank.mall.request.order.CommonOrderReqDTO;
import com.qdbank.mall.request.third.payment.*;
import com.qdbank.mall.response.third.payment.AccessSignResDTO;
import com.qdbank.mall.response.third.payment.PrePayResDTO;
import com.qdbank.mall.response.third.payment.QryPayResDTO;
import com.qdbank.mall.response.third.payment.RefundResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName PaymentServiceImpl
 * @Description TODO
 * @Author hongjh
 * @Date 2021/3/17 19:41
 * @Version 1.0
 **/
@Service("paymentService")
@Slf4j
public class PaymentServiceImpl extends BaseServiceImpl implements PaymentService, InitializingBean {

    @Autowired
    BankProxySendService bankProxySendService;

    @Autowired
    OrderService orderService;

    @Autowired
    CouponService couponService;

    @Autowired
    List<CommonPaymentAware> commonPaymentAwares;

    @Autowired
    RealStateMachineUtil realStateMachineUtil;

    @Autowired
    IntegralStateMachineUtil integralStateMachineUtil;

    @Autowired
    MobileReChargePayStateMachineUtil mobileReChargePayStateMachineUtil;

    @Autowired
    TimeOutHandler timeOutHandler;

    @Autowired
    MerchantDao merchantDao;

    @Autowired
    RefundErrorLogDao refundErrorLogDao;

    @Autowired
    CouponDao couponDao;


    Map<ProductEnum, CommonPaymentAware> paymentKindMap = new HashMap<>();


    @Autowired
    ParamsService paramsService;

    @Autowired
    PaymentDao paymentDao;

    @Autowired
    RefundMapper refundMapper;
    @Autowired
    private IntegralService integralService;


    @Autowired
    private CouponMqSendService couponMqSendService;


    /**
     * TODO
     * 1、切面防重复提交
     * 2、切面redis锁
     *
     * 下单逻辑整合
     * @param req
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderDO  createOrder(OrderDO order,CommonOrderReqDTO req, CommonPaymentAware aware) {
        //获取对应支付明确类，进行--模板模式--开发
        ProductEnum productEnum = aware.getProductType();
        //订单主键
        order.setOrderId(super.generateId());
        //订单编号
        order.setOrderSn(Constant.ORDER_PRE+super.generateOrderSN());
        //产品类型
        order.setProductType(productEnum.productType);

        //待支付
        order.setStatus(0L);
        order.setCloseType(CloseTypeEnum.INIT.closeType);
        order.setRefundStatus(RefundStatausEnum.INIT.refundStatus);
        log.info("支付方式-订单号-主键 [{}][{}][{}]",productEnum,order.getOrderSn(),order.getOrderId());


        //step 1 优惠券判断
        order.setCouponAmount(new BigDecimal(0));
        Long userCouponId =req.getUserCouponId();
        boolean hasCoupon = userCouponId!=null && userCouponId>0;
        if(hasCoupon){
            order.setUserCouponId(userCouponId);
            //校验优惠券
            checkCoupon(req,order);
            boolean flag = couponService.activeUserCoupon(order,0L,1L);
            if(!flag){
                throw new ApiException(ResultCode.USER_COUPON_EXPIR);
            }
        }

        //step  相应品种校验
        aware.preCreateOrderCheck(order,req);

        /**
         *  step 2 行内交互---获取支付授权信息
         */
        //step 2.1 行内--准入标志
        AccessSignResDTO accessSignRes =prepare(order,Constant.THIRD_GET_TYPE_PAY,aware.payBackJumpUrl(),req.getPayFlag());
        //step 2.2 行内--下单操作
        PrePayResDTO res = saveOrderinfo(accessSignRes,order,Constant.THIRD_GET_TYPE_PAY,aware.payBackJumpUrl(),req.getPayFlag());
        //step 2.3 订单内容更新--通用字段
       this.initBankDetail(order,accessSignRes);

        //下单特殊场景-字段判断
        Date now = new Date();
        order.setCreateTime(now);
        order.setUpdateTime(now);

        //订单录入
        orderService.createOrder(order);
        //支付场景特殊处理
        aware.afterCreateOrder(order,req);
        //计算15 min处理
        timeOutHandler.setOrderTimeOut(order.getOrderSn());

        if(hasCoupon){
            //优惠券已使用 记录MQ
            CouponMQBO couponMQBO = new CouponMQBO();
            couponMQBO.setCouponId(userCouponId+"");
            couponMQBO.setOperateType(Constant.OPERATE_TYPE_DELETE);;
            couponMqSendService.couponMqSend(couponMQBO);
        }
        return order;
    }


    /**
     * 订单取消
     * @param order
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void cancelOrder(OrderDO order, OrderStatusDO orderStatusDO, CommonPaymentAware aware){
        Long productType = order.getProductType();
        ProductEnum productEnum =ProductEnum.getProductByType(productType);

        //自身业务逻辑处理--如实物订单的库存还原
        aware.preCancelOrderCheck(order);

        //优惠券还原
        Long userCouponId =order.getUserCouponId();
        boolean hasCoupon = userCouponId!=null && userCouponId>0;
        Long afterStatus = null;
        UserCouponResDTO res = null;
        if(hasCoupon){
            res =couponService.qryCouponDetail(order.getCustNo()+"",userCouponId);
            CouponEnum couponEnum =CouponEnum.getCouponType(order.getCouponType());
            afterStatus = checkCouponStatus(res,couponEnum);

            //对应积分兑换券订单状态更新--若为积分兑换券对应状态翻转
            if(!StringUtils.hasText(res.getBatchNo())){
                //重置状态
                couponService.resetCouponStatus(order,afterStatus);
            }

            //优惠券状态----已使用--待使用--过期时间由定时任务处理，已过期，列表查询会过滤掉，所以不做处理
            boolean flag = couponService.activeUserCoupon(order,1L,afterStatus);
        }
        //更新相应状态
        orderService.updateUserOrderStatus(null,order.getCustNo()+"",order,orderStatusDO);

        if(hasCoupon && afterStatus == 0L){//变成待使用状态时才写入MQ
            //优惠券已使用 记录MQ
            CouponMQBO couponMQBO = new CouponMQBO();
            couponMQBO.setOperateType(Constant.OPERATE_TYPE_INSERT);;
            couponMQBO.setCouponId(userCouponId+"");
            couponMQBO.setStatus("20");
            couponMQBO.setDescription(res.getCouponDescription());
            couponMQBO.setCustNo(order.getCustNo()+"");
            couponMQBO.setCreateTime(DateUtil.format(res.getSendTime() == null ? res.getStartTime() : res.getSendTime(), DatePattern.NORM_DATETIME_PATTERN));
            couponMQBO.setAvailableBeginTime(DateUtil.format(res.getStartTime(),DatePattern.NORM_DATETIME_PATTERN));
            couponMQBO.setAvailableEndTime(DateUtil.format(res.getEndTime(),DatePattern.NORM_DATETIME_PATTERN));
            couponMQBO.setCouponNotice("");
            couponMQBO.setMallCouponType(res.getCouponType()+"");
            couponMQBO.setCouponAmount(res.getCouponAmount());
            couponMQBO.setCouponName(res.getCouponName());
            couponMQBO.setTransactionMinimum("");
            couponMQBO.setProductType(productType+"");
            couponMqSendService.couponMqSend(couponMQBO);
        }

    }

    private Long checkCouponStatus(UserCouponResDTO res, CouponEnum couponEnum) {
        Date endTime =res.getEndTime();
        Long afterStatus = 0L;
        //已过期
        if(endTime.compareTo(new Date())<=0){
            afterStatus = 2L;
        }
        //判断主信息
        if(couponEnum.group==CouponEnum.CouponGroup.APPOINT){
            Long batchStatus =res.getBatchStatus();
            if(batchStatus==2L || batchStatus==3L){
                afterStatus=batchStatus;
            }
        /*}else if(couponEnum.group==CouponEnum.CouponGroup.EXCHANGE){
            afterStatus =res.getProductStatus();*/
        }else if(StringUtils.hasText(res.getBatchNo())){
            //积分兑换券存在批次号
            CouponDO couponDO  =couponDao.qryCouponDetailByBatchNo(res.getBatchNo());
            Long batchStatus =couponDO.getBatchStatus();
            if(batchStatus==2L || batchStatus==3L){
                afterStatus=batchStatus;
            }
        }
        return afterStatus;
    }

    /**
     * 退款
     * @param order
     */
    @Override
    public RefundResDTO refundOrderWithException(OrderDO order, CommonPaymentAware aware){
        OrderRefundDO orderRefundDO=this.initOrderRefund(order,"支付成功后异常，退金额");
        //统一异常处理逻辑：全量退，不涉及运费部分，用
        orderRefundDO.setRefundCash(BsnUtil.convertBigdecimal(order.getOrderCash()).add(BsnUtil.convertBigdecimal(order.getFreightAmount())));
        //积分
        orderRefundDO.setRefundIntegration(BsnUtil.convertLong(order.getOrderIntegration()));
        // 退款折算价
        orderRefundDO.setRefundAmount(orderRefundDO.getRefundCash().add(BsnUtil.integraToAmt(orderRefundDO.getRefundIntegration())));
        //运费
        orderRefundDO.setFreightAmount(BsnUtil.convertBigdecimal(order.getFreightAmount()));

        RefundResDTO refundRes = this.refundOrder(orderRefundDO,order,aware);
        aware.handlerRefund(order,orderRefundDO,refundRes);
        return refundRes;
    }


    /**
     * 退款
     * @param order
     */
    @Override
    public RefundResDTO refundOrder(OrderRefundDO orderRefundDO,OrderDO order, CommonPaymentAware aware){

        //准入
       AccessSignResDTO res= this.prepare(order,Constant.THIRD_GET_TYPE_REFUND,null,"0");

       //只推一次
       RefundResDTO refundRes =this.refund(orderRefundDO,res,order,Constant.THIRD_GET_TYPE_REFUND);
        //退款成功
        if(refundRes!=null){
            orderRefundDO.setRefundStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.REFUND.refundStatus);
            orderRefundDO.setHandleFinishTime(new Date());
        }else{
            //退款失败
            orderRefundDO.setRefundStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.REFUND.refundStatus);
        //    orderRefundDO.setRefundStatus(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus);
        }
       return refundRes;
    }




    /**
     * 优惠券判断
     * @param req
     * @param order
     */
    private void checkCoupon(CommonOrderReqDTO req, OrderDO order) {
        couponService.checkUserCoupon(order);
    }



    /**
     * 查询订单信息
     * @param order
     * @return
     */
    @Override
    public QryPayResDTO qryOrderInfo(OrderDO order){
        //step 2.1 行内--准入标志
        AccessSignResDTO accessSignRes =prepare(order,Constant.THIRD_GET_TYPE_QUERY,null,"0");
        //step 2.2 行内--下单操作
        QryPayResDTO res = orderType(accessSignRes,order,Constant.THIRD_GET_TYPE_QUERY);

        String flag = res.getRespStatus();
        log.info("订单支付结果情况[{}]--[{}]",order.getOrderSn(),flag);

        //未更新更新状态&& 且为状态正常 -- 收银台失败状态统一
        if(Constant.NOT_NOTIC_STATUS.equals(order.getNoticeStatus()+"") && "S".equals(flag)){
            //重要***查询的订单，可能信息不全，退款可能无法成功；后期待处理，场景较少
            NoticeReqDTO notice= new NoticeReqDTO();
            notice.setAcctType(res.getAcctType());
            notice.setOriTransDt(res.getOrigChlDt());
            notice.setOriTransSer(res.getOrigChlSerNo());
            notice.setStatus(res.getRespStatus());
            this.handlerNotic(order,notice);
        }
        return res;
    }


    /**
     * 检查是否通知
     * @param order
     * @param flag
     */
    public void checkPayStatus(OrderDO order, String flag){
        //未通知进行逻辑处理
        if(Constant.NOT_NOTIC_STATUS.equals(order.getNoticeStatus())){
            Long productType =order.getProductType();
            ProductEnum productEnum =ProductEnum.getProductByType(productType);
            log.info("订单类型[{}]-[{}]",order.getOrderSn(),productEnum);
            Map params = new HashMap();
            params.put("payFlag",flag);
            //实物订单
            if(productEnum==ProductEnum.PAYMENT_IN_KIND){
                 this.handlerOrder(order, RealOrderHandlerEvent.NOTIC,params);
            }else if(productEnum==ProductEnum.INTEGRAL){
                //积分兑换
                this.handlerOrder(order, IntegralOrderEvent.NOTIC,params);
            }
        }
    }

    @Override
    public void handlerNotic(OrderDO order, NoticeReqDTO notice){

        //step 2 更新通知状态--强制性
        if(!StringUtils.hasText(notice.getTimestamp()) && order.getPaymentTime()!=null){
            notice.setTimestamp(order.getPaymentTime().getTime()+"");
        }
        orderService.noticOrderStatus(order.getCustNo()+"",order.getOrderSn(),Long.parseLong(Constant.HAS_NOTIC_STATUS),notice);
        //通知状态
        order.setNoticeStatus(Long.parseLong(Constant.HAS_NOTIC_STATUS));

        ProductEnum productEnum =ProductEnum.getProductByType(order.getProductType());
        Map params = new HashMap();
        params.put("noticeReq",notice);
        //实物订单通知
        if(ProductEnum.PAYMENT_IN_KIND == productEnum){
            handlerOrder(order, RealOrderHandlerEvent.NOTIC,params);
        }else if(ProductEnum.INTEGRAL==productEnum){
            //积分兑换
            handlerOrder(order, IntegralOrderEvent.NOTIC,params);
        }else if(ProductEnum.MOBILE_RECHARGE==productEnum || ProductEnum.OIL_CARD==productEnum){
            //话费充值
            handlerOrder(order, MobileReChargePayOrderEvent.NOTIC,params);
        }
    }


    /**
     * 下单操作，行内字段
     * @param order
     * @param res
     */
    protected void initBankDetail(OrderDO order,AccessSignResDTO res){
        /**
         * -------行内系统-----
         * NOTICE_STATUS	通知状态 0 未通知 1 已通知
         * REQ_URL	行内准入接口返回url
         * ACCESS_SIGN_ID	行内准入接口返回准入标识
         */
        String url=res.getRequrl();
        if(!StringUtils.hasText(url)){//url没有实际作用，只用于前端拼接参数使用
            url = "http://spg.qdccb.cn/accCheck25/ifpService/payPage/index.html";
        }
        Map response  = new LinkedHashMap();
        IntegralDetail integralDetail = null;
        if(order.getOrderIntegration() != null && order.getOrderIntegration() > 0){
            IntegralBalanceReqDTO integralBalanceReqDTO = new IntegralBalanceReqDTO();
            integralBalanceReqDTO.setCustId(order.getCustNo()+"");
            integralDetail = integralService.getPointBal(integralBalanceReqDTO);
        }
        response.put("orderId",order.getOrderSn());
        response.put("orderTime",System.currentTimeMillis());
        response.put("custId",order.getCustNo());
        response.put("timeOut",paramsService.getOrderTimeOut());
        response.put("pointBal",integralDetail == null ? "" : integralDetail.getPointBal());
        String jump_url = this.formateUrl(url+"#/index",response);

        String accessSignid =res.getAccessSignid();
        //行内准入标识
        order.setAccessSignId(accessSignid);
        //行内准入url
        order.setReqUrl(jump_url);
        //通知状态
        order.setNoticeStatus(Long.parseLong(Constant.NOT_NOTIC_STATUS));

    }






    /**
     * 行内对接授权
     * @param order
     * @param getType
     * @param reqUrl
     * @return
     */
    AccessSignResDTO prepare(OrderDO order,String getType,String reqUrl,String payFlag){
        AccessSignReqDTO accReq = new AccessSignReqDTO();
        /**
         * 00：获取支付页面
         * 01：退款
         * 02：获取订单状态
         * 03：预通知订单信息
         */
        accReq.setGetType(getType);
        accReq.setFlag("B");
        accReq.setReqURL(StringUtils.hasText(reqUrl)?reqUrl+"?id="+order.getOrderSn():"");
        accReq.setPayFlag(payFlag);
        AccessSignResDTO res= bankProxySendService.accessSign(accReq);
        return res;
    }


    /**
     * 订单存储
     * @param accessSignRes
     * @param order
     * @param getType
     * @return
     */
    public PrePayResDTO saveOrderinfo(AccessSignResDTO accessSignRes,OrderDO order,String getType,String reqUrl,String payFlag){
        PrePayReqDTO prePayReq = new PrePayReqDTO();
        prePayReq.setAccessSign(accessSignRes.getAccessSign());
        prePayReq.setAccessSignid(accessSignRes.getAccessSignid());
        prePayReq.setAccessSignFlag("A");
        //商品id
        prePayReq.setCommodityCode(order.getProductId()+"");
        //商品名称
        prePayReq.setTradeName(order.getProductName());
        //商户号
        prePayReq.setMerchantNumber(order.getMerchantNo()+"");
        //商户名称
        prePayReq.setNameBusiness(order.getMerchantName());
        prePayReq.setQueryType("A");
        //查询商户账号--用户结算
        MerchantDO merchant =merchantDao.qryMerchantById(order.getMerchantNo());
        if(merchant!=null){
            prePayReq.setBusinessNumber(merchant.getBankNo());
        }else{
            //积分兑换上送 0 -- 本行结算
            if(ProductEnum.getProductByType(order.getProductType())==ProductEnum.INTEGRAL){
                prePayReq.setBusinessNumber("0");
            }else{
                throw new ApiException(ResultCode.MERCHANT_NOT_FOUND_ERROR);
            }
        }

        //订单号
        prePayReq.setOrderid(order.getOrderSn());
        prePayReq.setGetType(getType);
        prePayReq.setUrl(StringUtils.hasText(reqUrl)?reqUrl+"?id="+order.getOrderSn():"");
        Long payType =order.getPayType();
        PaymentTypeEnum payTypeEnum = PaymentTypeEnum.getPayEnumByPaytype(payType);
        //支付方式
        prePayReq.setFlag(payTypeEnum.value);
        prePayReq.setTranse("0");
        prePayReq.setTrxndirect("");
        if(payTypeEnum== PaymentTypeEnum.MONEY_SCORE || payTypeEnum== PaymentTypeEnum.SCORE){
            //积分兑换
            prePayReq.setTrxndirect("E");
            prePayReq.setTranse(order.getOrderIntegration()+"");
            prePayReq.setTransAmt("0.00");
        }
        if(payTypeEnum== PaymentTypeEnum.MONEY_SCORE || payTypeEnum== PaymentTypeEnum.MONEY){
            BigDecimal freightAmount=order.getFreightAmount();
            if(freightAmount==null){
                order.setFreightAmount(new BigDecimal(0));
            }
            //现金支付
            prePayReq.setTransAmt(StringUtil.formateAmt(order.getFreightAmount().add(order.getOrderCash())));
        }
        prePayReq.setPayFlag(payFlag);
        PrePayResDTO preRes=this.bankProxySendService.saveOrderinfo(prePayReq);

        //创建流水
        PaymentFlowDO flow = new PaymentFlowDO();
        Date now = new Date();
        flow.setAccessSignId(accessSignRes.getAccessSignid());
    //      flow.setAcctType();
        flow.setCustNo(order.getCustNo());
        flow.setOrderSn(order.getOrderSn());
        flow.setPaymentFolwId(super.generateId());
    //    flow.setQueryType(prePayReq.get);
        flow.setStatus("0");
        flow.setTransAmt(prePayReq.getTransAmt());
        flow.setTranse(prePayReq.getTranse());
        flow.setCreateTime(now);
        flow.setUpdateTime(now);
        paymentDao.createPaymentFlow(flow);
        return preRes;
    }


    /**
     * 订单查询
     * @param accessSignRes
     * @param order
     * @param getType
     * @return
     */
    public RefundResDTO refund(OrderRefundDO orderRefund,AccessSignResDTO accessSignRes, OrderDO order, String getType){
        //查询支付流水信息
       List<PaymentFlowDO> paymentFlows =paymentDao.qryPaymentFlowByOrderSn(order.getOrderSn());

       //校验是否可以进行退款
        PaymentFlowDO paymentFlow=checkRefundStatus(paymentFlows);

       if(paymentFlow==null){
           throw new ApiException(ResultCode.ORDER_STATUS_ERROR);
       }

        PaymentFlowDO updateFlow = new PaymentFlowDO();
        BeanUtils.copyProperties(paymentFlow,updateFlow);
        updateFlow.setPaymentFolwId(super.generateId());
        updateFlow.setAccessSignId(accessSignRes.getAccessSignid());
        updateFlow.setCreateTime(new Date());
        updateFlow.setUpdateTime(new Date());
   //     updateFlow.setStatus(PaymentStatusEnum.REFUND_ING.payType);

        RefundReqDTO req = new RefundReqDTO();

        req.setCurrNum("RMB");
        req.setOrdId(order.getOrderSn());
        req.setProdId(order.getProductId()+"");
        req.setMerchantNumber(order.getMerchantNo()+"");
        req.setGetType(getType);
        req.setCustId(order.getCustNo()+"");


        req.setAccessSign(accessSignRes.getAccessSign());
        req.setAccessSignid(accessSignRes.getAccessSignid());
        req.setAccessSignFlag("A");

        //渠道流水号
        req.setOriTransSer(paymentFlow.getOriTransser());
        //渠道日期
        req.setOriTransDt(paymentFlow.getOriTransDt());
        //积分账户类型
        req.setAcctType(paymentFlow.getAcctType());
        //积分调整值
        req.setTranse(order.getOrderIntegration()+"");
        req.setOriOrdId(order.getOrderSn());
        //金额
        BigDecimal refundAmt = order.getOrderCash();


        Long status= order.getStatus();

        refundAmt = orderRefund.getRefundCash();

        if(refundAmt==null){
            refundAmt=new BigDecimal(0);
        }

        req.setTransAmt(refundAmt.toString());

        //支付方式
        PaymentTypeEnum payTypeEnum = BsnUtil.initPayType(refundAmt,order.getOrderIntegration());
        req.setQueryType(payTypeEnum.value);
        //针对含积分类型，如果ACCTYPE为空做，补录操作
        if(payTypeEnum==PaymentTypeEnum.MONEY_SCORE || payTypeEnum==PaymentTypeEnum.SCORE){
            if(!StringUtils.hasText(req.getAcctType())){
                //********特殊处理：针对积分兑换券场景，做字段补录
                req.setAcctType(payTypeEnum.accType);
                log.error("含积分类型逻辑异常[{}][{}]", JSON.toJSONString(paymentFlow),JSON.toJSONString(req));
            }
        }
        try{
            //退款成功
            RefundResDTO preRes= bankProxySendService.refund(req);
            updateFlow.setStatus(PaymentStatusEnum.REFUND_SUCCESS.payType);
            //成功如果存在异常记录翻新
            this.logRefundErrorSuccess(orderRefund,updateFlow);
            return preRes;
        }catch (Exception e){
            //退款失败，只有一次处理
            /**
             *  0825 版本 退款逻辑--将退款流水拆分
             */
            updateFlow.setStatus(PaymentStatusEnum.REFUND_FAIL.payType);
            //插入或更新支付异常记录 log
            this.logRefundError(orderRefund,updateFlow);
        }finally {
            //不更新，直接新建记录
       //     paymentDao.(updateFlow,order.getOrderSn());
            paymentDao.createPaymentFlow(updateFlow);
        }
        return null;
    }

    /**
     * 记录退款异常日志
     * @param updateFlow
     */
    private void logRefundError(OrderRefundDO  orderRefundDO,PaymentFlowDO updateFlow) {
        RefundErrorLogDO refundErrorLog =refundErrorLogDao.qryRefundErrorLogBySerino(orderRefundDO.getRefundSerial());
        if(refundErrorLog==null){
            Date now = new Date();
            RefundErrorLogDO neRefund = new RefundErrorLogDO();
            neRefund.setRefundErrorLogId(super.generateId());
            neRefund.setRefundSerial(Long.parseLong(orderRefundDO.getRefundSerial()));
            neRefund.setCustNo(updateFlow.getCustNo());
            neRefund.setCreateTime(now);
            neRefund.setUpdateTime(now);
            neRefund.setOrderSn(orderRefundDO.getOrderSn());
            neRefund.setStatus("0");
            refundErrorLogDao.createRefundErrorLog(neRefund);
        }else{
            /*refundErrorLog.setUpdateTime(now);
            refundErrorLogDao.*/
        }
    }

    private void logRefundErrorSuccess(OrderRefundDO orderRefund,PaymentFlowDO updateFlow) {
        RefundErrorLogDO refundErrorLog =refundErrorLogDao.qryRefundErrorLogBySerino(orderRefund.getRefundSerial());
        if(refundErrorLog!=null){
            Date now = new Date();
            refundErrorLog.setStatus("1");
            refundErrorLog.setUpdateTime(now);
            refundErrorLogDao.updateRefundErrorLog(refundErrorLog,orderRefund.getRefundSerial());
        }
    }


    private PaymentFlowDO checkRefundStatus(List<PaymentFlowDO> paymentFlows) {
        PaymentFlowDO successPaymentFlow = null;
        if(!CollectionUtils.isEmpty(paymentFlows)){
            for(PaymentFlowDO paymentFlow : paymentFlows){
                //存在已退款流水，不能再退款了，退款中的状态 情况特殊，一般不存在（同步接口）
                if(PaymentStatusEnum.REFUND_SUCCESS.payType.equals(paymentFlow.getStatus())
                        || PaymentStatusEnum.REFUND_ING.payType.equals(paymentFlow.getStatus())){
                    throw new ApiException(ResultCode.HAS_REFUND_ERROR);
                }
                //支付成功才具备退款条件
                if(PaymentStatusEnum.PAY_SUCCESS.payType.equals(paymentFlow.getStatus())){
                    successPaymentFlow=paymentFlow;
                }
            }
        }
       return successPaymentFlow;
    }

    /**
     * 订单查询
     * @param accessSignRes
     * @param order
     * @param getType
     * @return
     */
    public QryPayResDTO orderType(AccessSignResDTO accessSignRes, OrderDO order, String getType){
        QryPayReqDTO req = new QryPayReqDTO();
        req.setAccessSign(accessSignRes.getAccessSign());
        req.setAccessSignid(accessSignRes.getAccessSignid());
        req.setAccessSignFlag("A");

        //订单号
        req.setOrdId(order.getOrderSn());
        req.setGetType(getType);

        req.setFlag("B");

        //支付方式
        Long payType =order.getPayType();
        PaymentTypeEnum payTypeEnum = PaymentTypeEnum.getPayEnumByPaytype(payType);
        req.setPayFlage(payTypeEnum.value);
        req.setFlag(payTypeEnum.value);
        QryPayResDTO preRes= bankProxySendService.orderType(req);
        return preRes;
    }


    public void qryRefundFailOrder(){
        PaymentStatusEnum enum1 = PaymentStatusEnum.REFUND_FAIL;
    }


    @Override
    public Message handlerOrder(Object orderObj, OrderEventAware _event, Map params ){
        Message message =  this.handlerBsnOrder(orderObj,_event,params);
        return message;
    }


    public Message handlerBsnOrder(Object orderObj, OrderEventAware _event, Map params ){
        OrderDO order = null;
        String custNo = null;
        if(orderObj instanceof OrderDO){
            order = (OrderDO) orderObj;
        }else if(orderObj instanceof String){
            String orderSN = orderObj.toString();
            if(StringUtil.hasText(orderSN)){
                order= orderService.qryOrderBySn(orderSN);
            }
        }else{
            order = new OrderDO();
            UserDetails userDetails = UserContextHolder.getUserDetails();
            order.setCustMobile(Long.parseLong(userDetails.getCustMobile()));
            order.setCustName(userDetails.getCustName());
            order.setCustNo(Long.parseLong(userDetails.getCustNo()));
        }

        if(order==null){
            throw new ApiException(ResultCode.USER_ORDER_HAS_NOT_FOUND);
        }
        PaymentService paymentService = SpringContextUtils.getBean("paymentService",PaymentService.class);

        try{
      //      Message message =  this.handlerBsnOrder(orderObj,_event,params);
            Message message = paymentService.handlerBsn(order.getCustNo(),order.getOrderSn(),order,_event,params);
            return message;
        }catch (ApiException e){
            //系统报错
            orderService.sendPost(order, new MetricsHolder(MetricsEnum.ERROR,null),e.getErrorCode()+":"+e.getMessage());
            throw e;
        }catch (Exception e1){
            orderService.sendPost(order, new MetricsHolder(MetricsEnum.ERROR,null),e1.getMessage());
            throw e1;
        }
    }



    @Override
    @Lock(leaseTime = 30, timeUnit = TimeUnit.SECONDS )
    public Message  handlerBsn(@LockName Long custNo,@LockName String orderSn, OrderDO order, OrderEventAware _event, Map params){
        log.info("订单号[{}]--[{}]--[{}]",_event,order.getOrderSn(), JsonUtil.toJSONString(order));
        if(params==null){
            params = new HashMap();
        }
        params.put(Constant.ORDER_STR,order);

        Message message = null;
        if(_event instanceof RealOrderHandlerEvent){
            log.info("实物订单状态机处理-{}",order.getOrderSn());
            message  =realStateMachineUtil.initMessage((RealOrderHandlerEvent) _event,params);
            realStateMachineUtil.execute(order,message);
        }else if(_event instanceof IntegralOrderEvent){
            log.info("积分兑换订单状态机处理-{}",order.getOrderSn());
            message =integralStateMachineUtil.initMessage((IntegralOrderEvent) _event,params);
            integralStateMachineUtil.execute(order,message);
        }else if(_event instanceof MobileReChargePayOrderEvent){
            log.info("话费订单状态机处理-{}",order.getOrderSn());
            message =mobileReChargePayStateMachineUtil.initMessage((MobileReChargePayOrderEvent) _event,params);
            mobileReChargePayStateMachineUtil.execute(order,message);
        }
        return message;
    }

    @Override
    public OrderRefundDO initOrderRefund(OrderDO orderDO, String note){
        OrderRefundDO orderRefundDO  =refundMapper.orderToRefund(orderDO);
        orderRefundDO.setRefundType(2L);
        //退款说明
        orderRefundDO.setRefundNote(note);
        //待审核
        orderRefundDO.setRefundStatus(0L);


        Date now = new Date();
        orderRefundDO.setCreateTime(now);
        orderRefundDO.setUpdateTime(now);

        /**
         * 分布式锁、且已经使用状态机机制，不需要做是否存在退款订单状态
         */
        orderRefundDO.setRefundSerial(super.generateId()+"");
        return orderRefundDO;
    }







    @Override
    public void afterPropertiesSet() throws Exception {
        for(CommonPaymentAware aware : commonPaymentAwares){
            paymentKindMap.put(aware.getProductType(),aware);
        }

    }

    public  String formateUrl(String url, Map<String,String> params){
        StringBuilder sb = new StringBuilder(url);
        if(!CollectionUtils.isEmpty(params)){
            sb.append("?");
            for(String key : params.keySet()){
                Object obj=params.get(key);
                sb.append(key).append("=");
                if(obj != null){
                    sb.append(obj.toString());
                }
                sb.append("&");
            }
            return sb.substring(0,sb.length()-1);
        }
        return sb.toString();
    }
}
