package com.qdbank.mall.cms;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.request.order.OrderLikeQueryReqDTO;
import com.qdbank.mall.response.order.OrderResDTO;
import com.qdbank.mall.service.impl.SnowFlakeService;
import com.qdbank.mall.user.UmsAdminCacheService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName RedisTest
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/2 14:50
 * @Version 1.0
 **/
public class RedisTest extends BaseSpringTest {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SnowFlakeService snowFlakeService;

    @Autowired
    private OrderService orderService;
    @Test
    public void testRedis(){
        //snowFlakeService.getId();
        boolean flag =  passwordEncoder.matches("fanf1111","$2a$10$lgeqCLHOi14S5l/9OeDNl.nWDoWnRfTH8caymB/3R7AxzNzy7V4We");
        System.out.println(flag);
    }
    @Test
    public void testOrder(){
        OrderLikeQueryReqDTO orderLikeQueryReqDTO = new OrderLikeQueryReqDTO();
        orderLikeQueryReqDTO.setOrderSn("QD202204201811420002");
        PageInfo<OrderResDTO> pageInfo = orderService.list(orderLikeQueryReqDTO);
        System.out.println(JSON.toJSONString("结果:"+pageInfo));
    }

}
