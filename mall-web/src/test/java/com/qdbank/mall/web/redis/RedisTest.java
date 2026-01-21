package com.qdbank.mall.web.redis;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.cmsuser.CmsAdminCacheService;
import com.qdbank.mall.config.IgnoreUrlsConfig;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.financial.FinancialService;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.user.UserInfoDO;
import com.qdbank.mall.order.OrderDetailService;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.request.financial.FinancialReqDTO;
import com.qdbank.mall.request.order.OrderLikeQueryReqDTO;
import com.qdbank.mall.response.order.orderdetail.OrderBaseDetailResDTO;
import com.qdbank.mall.service.RedisService;
import com.qdbank.mall.service.impl.SnowFlakeService;
import com.qdbank.mall.trade.TradeService;
import com.qdbank.mall.user.UmsAdminCacheService;
import com.qdbank.mall.web.BaseSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import springfox.documentation.spring.web.json.Json;

import java.util.Date;

/**
 * @ClassName RedisTest
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/2 14:50
 * @Version 1.0
 **/
public class RedisTest extends BaseSpringTest {
    @Autowired
    private SnowFlakeService snowFlakeService;
    @Autowired
    private UmsAdminCacheService umsAdminCacheService;
    //tradeDetailFinancialServiceImpl
    //couponTotalFinancialServiceImpl
    //marketTotalFinancialServiceImpl
    //tradeTotalFinancialServiceImpl
    @Qualifier("marketTotalFinancialServiceImpl")
    @Autowired
    private FinancialService financialService;
    @Qualifier("orderRealDetailServiceImpl")
    @Autowired
    private OrderDetailService orderDetailService;
    @Qualifier("tradeServiceImpl")
    @Autowired
    private TradeService tradeService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;
    @Test
    public void testRedis(){
//        for (int i = 0 ;i < 10 ; i++){
//            System.out.println(umsAdminCacheService.getRedisIncrID("usernameTest",1L));
//        }
        String key = "3408281991080840506";
        Long increment = redisTemplate.opsForValue().increment(key, 1);
        Object object = redisTemplate.opsForValue().get(key);
        if(object != null){
            Long verifyCount = Long.valueOf(object.toString());
            System.out.println(verifyCount);
        }
    }
    @Test
    public void delCache(){
        UserInfoDO userInfoDO = umsAdminCacheService.getAdmin("caicei@qq.com");
        System.out.println(JSON.toJSONString(userInfoDO));
        boolean flag = umsAdminCacheService.deleteByUsername("caicei@qq.com");
        System.out.println(flag);
    }
    @Test
    public  void testId(){
        //DateUtil.offsetDay(new Date(),-4)
        financialService.insertTradeRecord(DateUtil.offsetDay(new Date(),-3));
        //financialService.insertTradeRecord(new Date());

//          System.out.println(JSON.toJSONString(pageInfo));
//        FinancialReqDTO financialReqDTO = new FinancialReqDTO();
//        financialReqDTO.setStartDate(DateUtil.offsetDay(new Date(),-1));
//        financialReqDTO.setEndDate(new Date());
//        PageInfo pageInfo = financialService.getTradeList(financialReqDTO);
//        System.out.println(JSON.toJSONString(pageInfo));

    }
    @Test
    public void testOrderSN(){
        OrderDO orderDO = new OrderDO();
        orderDO.setOrderSn("kp2015372369299272");
        OrderBaseDetailResDTO orderBaseDetailResDTO = orderDetailService.orderDetail(orderDO);
        System.out.println(orderBaseDetailResDTO);
    }
    @Test
    public void testDate(){
        String uri = "/oms/department/getCache/20230131/CCXY_7";
        PathMatcher pathMatcher = new AntPathMatcher();
        for (String path : ignoreUrlsConfig.getUrls()) {
            if(pathMatcher.match(path,uri)){
                System.out.println("匹配成功");
                return;
            }
        }
    }
}
