package com.qdbank.mall.web.task;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.qdbank.mall.api.ServiceEnum;
import com.qdbank.mall.cmsuser.CmsAdminCacheService;
import com.qdbank.mall.financial.FinancialService;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.user.UserInfoDO;
import com.qdbank.mall.order.OrderDetailService;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.request.financial.FinancialReqDTO;
import com.qdbank.mall.response.financial.ProductCouponResDTO;
import com.qdbank.mall.response.order.orderdetail.OrderBaseDetailResDTO;
import com.qdbank.mall.service.RedisService;
import com.qdbank.mall.service.impl.SnowFlakeService;
import com.qdbank.mall.trade.TradeService;
import com.qdbank.mall.util.SpringContextUtils;
import com.qdbank.mall.web.BaseSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName RedisTest
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/2 14:50
 * @Version 1.0
 **/
public class TaskTest extends BaseSpringTest {

    @Test
    public void task(){
        Map<String,FinancialService> map=SpringContextUtils.getApplicationContext().getBeansOfType(FinancialService.class);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE,-5);
        for(String key :map.keySet()){
            System.out.println(key);
            try{
                map.get(key).insertTradeRecord(c.getTime());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Test
    public void taskTotal(){
        try {
            //TradeDetailFinancialServiceImpl
            //tradeTotalFinancialServiceImpl
            FinancialService financialService = (FinancialService) SpringContextUtils.getBean("tradeDetailFinancialServiceImpl");

            Calendar c = Calendar.getInstance();
            //    c.add(Calendar.DATE,1);

            financialService.insertTradeRecord(c.getTime());
        }catch (Exception e){
            e.printStackTrace();
        }


    }


}
