package com.qdbank.mall.order.action.integral;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.qdbank.mall.annotation.StateError;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.CouponEnum;
import com.qdbank.mall.constant.payment.*;
import com.qdbank.mall.constant.payment.event.IntegralOrderEvent;
import com.qdbank.mall.constant.payment.lifestatus.IntegralOrderLifeStatus;
import com.qdbank.mall.constant.payment.orderstatus.IntegralOrderStatus;
import com.qdbank.mall.constent.payment.RefundStatausEnum;
import com.qdbank.mall.coupon.CouponService;
import com.qdbank.mall.domain.CouponMQBO;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.order.OrderStatusDO;
import com.qdbank.mall.model.usercoupon.UserCouponDO;
import com.qdbank.mall.mq.CouponMqSendService;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.response.coupon.CouponResDTO;
import com.qdbank.mall.service.SendCouponMqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@Slf4j
public class CreateCouponAction implements Action<IntegralOrderStatus, IntegralOrderEvent> {

    @Autowired
    CouponService couponService;

    @Autowired
    OrderService orderService;
    @Autowired
    private CouponMqSendService couponMqSendService;

        @Override
        @StateError
        @Transactional(rollbackFor = Exception.class)
        public void execute(StateContext<IntegralOrderStatus, IntegralOrderEvent> context) {
            OrderDO order =  context.getMessage().getHeaders().get(Constant.ORDER_STR,OrderDO.class);
            //step 1 通过指定优惠券编号获取优惠券信息
            CouponResDTO res = couponService.qryCouponDetailById(order.getExchangeCouponId());
            //积分兑换积分支付成功后，处理优惠券
            UserCouponDO userCoupon = new UserCouponDO();
            //积分兑换券
            userCoupon.setCouponType(CouponEnum.COUPON_TYPE_EXCHANGE.couponType);
            //订单号--被使用的订单号，非兑换的订单号--订单生成的
            userCoupon.setOrderSn(order.getOrderSn());
            //客户号
            userCoupon.setCustNo(order.getCustNo()+"");
            //券码
            userCoupon.setCouponId(res.getCouponId());
            //销售积分
            userCoupon.setOrderIntegration(res.getProductIntegration());
            //券名字
            userCoupon.setCouponName(res.getCouponName());
            //待使用状态
            userCoupon.setStatus(0L);
            userCoupon.setBatchNo(res.getBatchNo());
            Calendar now = Calendar.getInstance();
            userCoupon.setCreateTime(now.getTime());
            userCoupon.setUpdateTime(now.getTime());
            //计算优惠券过期时间
            now.add(Calendar.DATE,res.getExpireDays().intValue());
            //过期时间：23:59:59
            now.set(now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DATE),23,59,59);
            userCoupon.setExpireDate(now.getTime());
            //创建用户优惠券
            couponService.createUserCoupon(userCoupon);
            //step 2 订单处理
            OrderStatusDO orderStatusDO = new OrderStatusDO();
            orderStatusDO.setBeforeApproveStatus(RefundStatausEnum.INIT.refundStatus);
            orderStatusDO.setBeforeCloseType(CloseTypeEnum.INIT.closeType);
            orderStatusDO.setBeforeStatus(IntegralOrderStatus.Status.PREPARE_PAY.status);

            orderStatusDO.setAfterApproveStatus(RefundStatausEnum.INIT.refundStatus);
            orderStatusDO.setAfterCloseType(CloseTypeEnum.INIT.closeType);
            orderStatusDO.setAfterStatus(IntegralOrderStatus.Status.PREPARE_USE.status);

    //        OrderDO updateOrder = new OrderDO();
     //       updateOrder.setPaymentTime(new Date());
            boolean flag = orderService.updateUserOrderStatus(null,order.getCustNo()+"",order,orderStatusDO);
            //更新状态流程
            orderService.createProcessStatus(order.getOrderSn(), IntegralOrderLifeStatus.PAY_SUCCESS.status,IntegralOrderLifeStatus.PAY_SUCCESS.status);
            orderService.createProcessStatus(order.getOrderSn(), IntegralOrderLifeStatus.PREPARE_USE.status,IntegralOrderLifeStatus.PREPARE_USE.status);

            //更新状态
            log.info("积分兑换，创建操作[{}]",order.getOrderSn());
            //积分兑换券订单下单成功 写入MQ
            CouponMQBO couponMQBO = new CouponMQBO();
            couponMQBO.setOperateType(Constant.OPERATE_TYPE_INSERT);;
            couponMQBO.setCouponId(userCoupon.getUserCouponId()+"");
            couponMQBO.setStatus("20");
            couponMQBO.setDescription(res.getCouponDescription());
            couponMQBO.setCustNo(order.getCustNo()+"");
            couponMQBO.setCreateTime(DateUtil.format(userCoupon.getCreateTime(), DatePattern.NORM_DATETIME_PATTERN));
            couponMQBO.setAvailableBeginTime(DateUtil.format(userCoupon.getCreateTime(), DatePattern.NORM_DATETIME_PATTERN));
            couponMQBO.setAvailableEndTime(DateUtil.format(userCoupon.getExpireDate(), DatePattern.NORM_DATETIME_PATTERN));
            couponMQBO.setCouponNotice("");
            couponMQBO.setMallCouponType(userCoupon.getCouponType()+"");
            couponMQBO.setCouponAmount(res.getCouponAmount());
            couponMQBO.setCouponName(res.getCouponName());
            couponMQBO.setTransactionMinimum("");
            couponMQBO.setProductType(order.getProductType()+"");
            couponMqSendService.couponMqSend(couponMQBO);
        }



}
