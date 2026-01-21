package com.qdbank.mall.order.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.qdbank.mall.annotation.Lock;
import com.qdbank.mall.annotation.LockName;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.DatePattern;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.PayService;
import com.qdbank.mall.order.PaymentService;
import com.qdbank.mall.request.order.OrderQueryReqDTO;
import com.qdbank.mall.request.order.OrderUpdateStatusReqDTO;
import com.qdbank.mall.request.order.PayReqDTO;
import com.qdbank.mall.request.third.payment.NoticeReqDTO;
import com.qdbank.mall.response.order.OrderQueryResDTO;
import com.qdbank.mall.response.order.OrderUpdateStatusResDTO;
import com.qdbank.mall.response.order.PayResDTO;
import com.qdbank.mall.rpc.RRException;
import com.qdbank.mall.rpc.consumer.DubboService;
import com.qdbank.mall.util.RSAUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName PayServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/8/9 10:00
 * @Version 1.0
 **/
@Service
@Slf4j
public class PayServiceImpl implements PayService {
    @Value(value = "${http.request.rsa.private.key}")
    private String privateKey;

    @Value(value = "${http.request.rsa.public.key}")
    private String publicKey;
    @Autowired
    private DubboService dubboService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private PaymentService paymentService;
    @Override
    public PayResDTO payOrder(PayReqDTO payReqDTO) {
        payReqDTO.setCardNo(RSAUtil.decrypt(privateKey,publicKey,payReqDTO.getCardNo()));
        payReqDTO.setTransAmt(RSAUtil.decrypt(privateKey,publicKey,payReqDTO.getTransAmt()));
        payReqDTO.setTranse(RSAUtil.decrypt(privateKey,publicKey,payReqDTO.getTranse()));
        payReqDTO.setCustId(RSAUtil.decrypt(privateKey,publicKey,payReqDTO.getCustId()));
        PayResDTO payResDTO = checkPwd(payReqDTO);
        if(payResDTO != null) return payResDTO;
        return pay(payReqDTO.getCustId(),payReqDTO.getOrdId(),payReqDTO);
    }

    @Override
    public OrderQueryResDTO orderQuery(OrderQueryReqDTO orderQueryReqDTO) {
        String orderId = RSAUtil.decrypt(privateKey,publicKey,orderQueryReqDTO.getOrderid());
        orderQueryReqDTO.setOrderid(orderId);
        Map<String,Object> map = BeanUtil.beanToMap(orderQueryReqDTO);
        Map resultMap = dubboService.call("CC800009",map);
        log.info("订单信息查询接口:{}", JSON.toJSON(resultMap));
        OrderQueryResDTO queryResDTO = BeanUtil.mapToBean(resultMap,OrderQueryResDTO.class,true);
        queryResDTO.setErrorCode("0");
        return queryResDTO;
    }

    @Override
    public OrderUpdateStatusResDTO updateStatus(OrderUpdateStatusReqDTO orderUpdateStatusReqDTO) {
        Map<String,Object> map = BeanUtil.beanToMap(orderUpdateStatusReqDTO);
        Map resultMap = dubboService.call("CC800005",map);
        OrderUpdateStatusResDTO orderUpdateStatusResDTO = BeanUtil.mapToBean(resultMap,OrderUpdateStatusResDTO.class,true);
        orderUpdateStatusResDTO.setErrorCode("0");
        return orderUpdateStatusResDTO;
    }

    @Override
    public void batchMakeFile() {
        dubboService.call("CC800010",new HashMap<>());
        log.info("生成批量文件接口完成: CC800010");
    }

    /**
     * 校验支付密码
     */
    private PayResDTO checkPwd(PayReqDTO payReqDTO){
        PayResDTO payResDTO = new PayResDTO();
        try {
            Map<String,Object> map = BeanUtil.beanToMap(payReqDTO);
            Map resultMap = dubboService.call("SC500001",map);
            log.info("密码校验结果:{}", JSON.toJSON(resultMap));
        }catch (Exception e){
            payResDTO.setErrorCode(ResultCode.PASSWORD_ERROR.getCode()+"");
            payResDTO.setErrorMsg(ResultCode.PASSWORD_ERROR.getMessage());
            return payResDTO;
        }
            return null;
    }
    @Lock(leaseTime = 30, timeUnit = TimeUnit.SECONDS )
    private PayResDTO pay(@LockName String custNo, @LockName String orderSn,PayReqDTO payReqDTO){
        PayResDTO payResDTO = new PayResDTO();
        Map<String,Object> map = BeanUtil.beanToMap(payReqDTO);
        NoticeReqDTO noticeReqDTO = new NoticeReqDTO();
        OrderDO order = new OrderDO();
        try {
            order = orderService.qryNotNoticOrderByOrderSn(payReqDTO.getOrdId());
            if(order==null){
                throw new ApiException(ResultCode.USER_ORDER_HAS_NOTIC);
            }
            Map resultMap = dubboService.call("CC800002",map);
            payResDTO = BeanUtil.mapToBean(resultMap,PayResDTO.class,true);
            log.info("订单号:{} 订单支付结果:{}", payReqDTO.getOrdId(),JSON.toJSON(resultMap));
            if(StringUtils.isNotBlank(payResDTO.getStatus()) && "00".equals(payResDTO.getStatus())){
                payResDTO.setErrorCode("0");
                //通知状态
                order.setNoticeStatus(Long.parseLong(Constant.HAS_NOTIC_STATUS));
                this.paySuccess(noticeReqDTO,payReqDTO,payResDTO.getId());
            }else{
                order.setNoticeStatus(Long.parseLong(Constant.HAS_NOTIC_STATUS));
                payResDTO.setErrorCode(ResultCode.PAY_FAIL.getCode()+"");
                this.payFail(noticeReqDTO,payReqDTO,payResDTO.getId());
            }
        }catch (RRException e){
            payResDTO.setErrorCode(ResultCode.PAY_FAIL.getCode()+"");
            this.payFail(noticeReqDTO,payReqDTO,payResDTO.getId());
            payResDTO.setErrorCode(e.getErrorCode());
            payResDTO.setErrorMsg(e.getMsg());
        }
        paymentService.handlerNotic(order,noticeReqDTO);
        return payResDTO;
    }
    //支付成功
    private void paySuccess(NoticeReqDTO noticeReqDTO,PayReqDTO payReqDTO,String transSer){
        noticeReqDTO.setStatus("S");
        noticeReqDTO.setTimestamp(new Date().getTime()+"");
        noticeReqDTO.setAcctType("22");
        noticeReqDTO.setCustId(payReqDTO.getCustId());
        noticeReqDTO.setOrderid(payReqDTO.getOrdId());
        noticeReqDTO.setOriTransDt(DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN));
        noticeReqDTO.setOriTransSer(transSer);
        noticeReqDTO.setQueryType(payReqDTO.getFlag());
        noticeReqDTO.setTransAmt(payReqDTO.getTransAmt());
        noticeReqDTO.setTranse(payReqDTO.getTranse());
    }
    //支付失败
    private void payFail(NoticeReqDTO noticeReqDTO,PayReqDTO payReqDTO,String transSer){
        noticeReqDTO.setCustId(payReqDTO.getCustId());
        noticeReqDTO.setOrderid(payReqDTO.getOrdId());
        noticeReqDTO.setAcctType("22");
        noticeReqDTO.setStatus("L");
        noticeReqDTO.setOriTransSer(transSer);
        noticeReqDTO.setQueryType(payReqDTO.getFlag());
        noticeReqDTO.setTransAmt(payReqDTO.getTransAmt());
        noticeReqDTO.setTranse(payReqDTO.getTranse());
    }
}
