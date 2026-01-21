package com.qdbank.mall.controller.third;

import org.springframework.beans.factory.annotation.Value;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.mapper.order.OrderDOMapper;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.order.OrderDOExample;
import com.qdbank.mall.order.mq.MobileRechargeRefundHandler;
import com.qdbank.mall.order.mq.TimeOutHandler;
import com.qdbank.mall.request.CommonStringIDReqDTO;
import com.qdbank.mall.request.order.MobileRechargePaymenOrderReqDTO;
import com.qdbank.mall.request.third.mobile.MobileNoticeReqDTO;
import com.qdbank.mall.request.third.payment.NoticeReqDTO;
import com.qdbank.mall.third.impl.BankProxySendServiceImpl;
import com.qdbank.mall.util.SpringContextUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.com.lmax.disruptor.TimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName PaymentController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/21 9:44
 * @Version 1.0
 **/
@RestController
@Api(tags = "paymentController", description = "支付")
@RequestMapping("/test")
@Slf4j
@RefreshScope
public class TestController {

    @Value(value = "${third.bank.url.refund:}")
    private String temp ;

    private AtomicInteger atomicInteger=  new AtomicInteger(1);


    @Autowired
    OrderDOMapper orderDOMapper ;

    @Autowired
    TimeOutHandler timeoutHandler;




    @ApiOperation("test")
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult  test() {

        BankProxySendServiceImpl impl =SpringContextUtils.getBean("bankProxySendServiceImpl",BankProxySendServiceImpl.class);
        atomicInteger.addAndGet(1);
        if(atomicInteger.get()%2==0){
           impl.setRefundUrl(temp+"32433333344321");
        }else{
            impl.setRefundUrl(temp);
        }

       log.info("now switch is [{}]",impl.getRefundUrl());
       return CommonResult.success(impl.getRefundUrl());
    }


    @ApiOperation("timeout")
    @RequestMapping(value = "/timeout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult  timeout() {
        //获取订单
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,-40);
        OrderDOExample example = new OrderDOExample();
        OrderDOExample.Criteria criteria =example.createCriteria();
        criteria.andStatusEqualTo(0L);
        criteria.andCreateTimeLessThan(calendar.getTime());
        List<OrderDO> orders  =orderDOMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(orders)){
            for(OrderDO order : orders){
                try{
                    timeoutHandler.handlerTimeOutOrder(order.getOrderSn());
                }catch (Exception e){
                    log.error("error ..[{}]",order.getOrderSn());
                }
            }
        }
        return CommonResult.success(1);
    }



}
