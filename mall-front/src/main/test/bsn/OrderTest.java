package bsn;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.constant.payment.CloseTypeEnum;
import com.qdbank.mall.constant.payment.lifestatus.MobileRechargeOrderLifeStatus;
import com.qdbank.mall.constant.payment.orderstatus.RealOrderStatus;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.order.OrderDOMapper;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.order.OrderStatusDO;
import com.qdbank.mall.model.orderrefund.OrderRefundDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.PayService;
import com.qdbank.mall.order.PaymentService;
import com.qdbank.mall.order.business.handler.IntegralPaymentHandler;
import com.qdbank.mall.order.business.handler.RealPaymentHandler;
import com.qdbank.mall.order.business.handler.VirtualPaymenHandler;
import com.qdbank.mall.request.order.*;
import com.qdbank.mall.request.third.payment.AccessSignReqDTO;
import com.qdbank.mall.response.order.OrderResDTO;
import com.qdbank.mall.response.third.payment.AccessSignResDTO;
import com.qdbank.mall.response.third.payment.RefundResDTO;
import com.qdbank.mall.rpc.consumer.DubboService;
import com.qdbank.mall.spg.SpgService;
import com.qdbank.mall.third.BankProxySendService;
import com.qdbank.mall.util.JsonUtil;
import com.qdbank.mall.util.SpringContextUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
public class OrderTest extends MallTest{

    @Autowired
    OrderService orderService;

    @Autowired
    PaymentService paymentService;
    @Autowired
    private DubboService dubboService;
    @Autowired
    private BankProxySendService bankProxySendService;
    @Autowired
    RealPaymentHandler realPaymentHandler;
    @Autowired
    private PayService payService;
    @Autowired
    private OrderDOMapper orderDOMapper;
    @Autowired
    IntegralPaymentHandler integralPaymentHandler;
    @Autowired
    private SpgService spgService;
    @Test
    public void test(){
        OrderDO order  =orderService.qryOrderBySn("QD202107271150340039");
        paymentService.qryOrderInfo(order);

        OrderDO order1  =orderService.qryOrderBySn("QD202107221124360021");
        paymentService.qryOrderInfo(order1);
    }

    @Test
    public void test1(){
        List<OrderResDTO>  results=orderService.qryOrders("144","1", ProductEnum.PAYMENT_IN_KIND);
        System.out.println(results);
    }
    @Test
    public void testDubbo(){
//        AccessSignReqDTO accReq = new AccessSignReqDTO();
//        /**
//         * 00：获取支付页面
//         * 01：退款
//         * 02：获取订单状态
//         * 03：预通知订单信息
//         */
//        accReq.setGetType("00");
//        accReq.setFlag("B");
//        accReq.setReqURL("http://localhost:8090/oms?id="+ UUID.randomUUID().toString());
//        AccessSignResDTO res= bankProxySendService.accessSign(accReq);
        try {
            AccessSignReqDTO accReq = new AccessSignReqDTO();
            /**
             * 00：获取支付页面
             * 01：退款
             * 02：获取订单状态
             * 03：预通知订单信息
             */
            accReq.setGetType("00");
            accReq.setFlag("B");
            accReq.setReqURL(StringUtils.hasText("http://localhost:8090/")?"?id=111111" : "");
            Map<String,Object> map = BeanUtil.beanToMap(accReq);
            dubboService.call("CC800007",new HashMap<>());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Test
    public void testPayQuery(){
        OrderQueryReqDTO orderQueryReqDTO = new OrderQueryReqDTO();
        orderQueryReqDTO.setOrderid("QD202208111617330020");
        System.out.println("订单信息："+JSON.toJSONString(payService.orderQuery(orderQueryReqDTO)));
    }
    @Test
    public void testPwd(){
//        try {
            PayReqDTO payReqDTO = new PayReqDTO();
            payReqDTO.setCardNo("6252180010000273");
            payReqDTO.setPwdRandomNum(UUID.randomUUID().toString().replaceAll("-",""));
            payReqDTO.setPwd("0CIqRy5TTA1FrYvtaKp08Vt1BmaEReGGk8DR44HH9KTw9K5hD4YOsYI1GDhWC9fSPGkIpcPmL+58SrztK/VXytPXCukSVYfVsFHg/uC3LNmY2KYvRj6Mv8S4FwLzzH9tmnsNmpfQqalrJW9jbhBsUnBLyRyK6OKNKrUvtyxHHpD8SWyY1tXB3Pz1Q9v3BwrLNSY6kJhDAgDyNyQoIrR/3eBHRLXdNUYSPRWQCM5UOloP0NXnJspyqLvfVysWFYyawbdQJ1OBEhvSDxN+EGz84IKpJhzbiQeef1ajLw8dHsA=");
            Map<String,Object> map = BeanUtil.beanToMap(payReqDTO);
            Map resultMap = dubboService.call("SC500001",map);
            log.info("密码校验结果:{}", JSON.toJSON(resultMap));
//        }catch (Exception e){
//            log.error("密码验证错误：{}",e);
//            throw new ApiException(ResultCode.PASSWORD_ERROR);
//        }
    }
    @Test
    public void testPay(){
        PayReqDTO payReqDTO = new PayReqDTO();
        payReqDTO.setPwd("FAY4e5+o2qo5Wf5T8emR1V7oXDqMWKC1TbAedgL+yZVx2hXEAKyzZzgtawS5ukIBkfa8JF0hv5w1kQpXnVnIAYjdNGEoGC+Sch11GpE2d7Xb+afzfxXzOnXTUsSJB+P5egY2DAB33opikwMUgjSeAZn4N/blgjCMwDxCk+NasP7Su7YabQXX03mNo2XSnqhjTNDvWP5yPQ55Em7ga54PGi2SFNmiyO+d1HRnQnLFvI142quKcgbEk0LUtVhQfQSM5KqjELp+HNz5ZQZG3QvBL/FaHLKXcfgkvnew66goq2k=, cardNo=HU5i2q4+wE4LHCnJtacnLApil43MSlln3VhfG6RXAsVvVp3uvY94h6LVm+Mv+gji0nruGFIgXJDAl6t7laUivNYDGSjmtzeEpJYx0u93uCW56C5ZiD1XYtlcRbF/xp+PiLmbNHHucTNV0oNLMc3swDxAvs+j5bwSMRtZLTF1O/E=");
        payReqDTO.setPwdRandomNum("fe0570dd50b44da4a2d7a8151c097a84");
        payReqDTO.setCardNo("6252180000002198");
        payReqDTO.setAcctType("22");
        payReqDTO.setCustId("643");
        payReqDTO.setFlag("C");
        payReqDTO.setMerchantNumber("10104");
        payReqDTO.setInptTpye("");
        payReqDTO.setNameBusiness("xujun");
        payReqDTO.setCommodityCode("189083086433226752");
        payReqDTO.setOrdId("QD202208240941120002");
        payReqDTO.setTradeName("测试商品");
        payReqDTO.setTransAmt("9.00");
        payReqDTO.setTranse("0");
        payReqDTO.setTrxndirect("E");
        payReqDTO.setUserId("");
        payReqDTO.setTransSrc("SPG");
        payService.payOrder(payReqDTO);
    }
    @Test
    public void testCreateProductOrder(){
        OrderReqDTO orderReqDTO = new OrderReqDTO();
        orderReqDTO.setProductCount(1L);
        orderReqDTO.setProductId(189083086433226752L);
        orderReqDTO.setProductSkuId(189083086454198272L);
        orderReqDTO.setReceiverCity("110100");
        orderReqDTO.setReceiverDetailAddress("是是是是是是");
        orderReqDTO.setReceiverName("饭饭");
        orderReqDTO.setReceiverPhone("13666666666");
        orderReqDTO.setReceiverProvince("110000");
        orderReqDTO.setReceiverRegion("110112");
        orderReqDTO.setCustNo(643L);
        orderReqDTO.setCustName("李四");
        orderReqDTO.setCustMobile(15821512401L);
        RealOrderReqDTO realOrder  = new RealOrderReqDTO();
        realOrder.setPayFlag("0");
        BeanUtils.copyProperties(orderReqDTO,realOrder);
        OrderDO order = new OrderDO();
        //商品类型初始化，优惠券校验使用
        order.setProductId(realOrder.getProductId());
        order.setProductSkuId(realOrder.getProductSkuId());
        order.setCustNo(643L);
        order.setCustName("里斯");
        order.setCustMobile(15821512401L);
        OrderDO orderDO = paymentService.createOrder(order,realOrder,realPaymentHandler);
        log.info("创建订单响应结果:{}",JSON.toJSONString(orderDO));

    }
    @Test
    public void testIntegralOrder(){
        IntegralOrderReqDTO integralOrderReqDTO = new IntegralOrderReqDTO();
        integralOrderReqDTO.setExchangeCouponId(251067674558300160L);
        integralOrderReqDTO.setPayFlag("0");
        OrderDO order = new OrderDO();
        order.setCustNo(643L);
        order.setCustName("里斯");
        order.setCustMobile(15821512401L);
        OrderDO orderDO = paymentService.createOrder(order,integralOrderReqDTO,integralPaymentHandler);
        log.info("创建订单响应结果:{}",JSON.toJSONString(orderDO));
    }
    @Test
    public void testMobileOrder(){
        /**
         * {"orderReqDTO":
         * {"mobileAddress":"10029"
         * ,"mobileAddressDesc":"新疆",，
         * "mobileSkuId":1004,
         * "rechargeMobile":"18899595231",
         * "supplierType":"CM"}}
         */
        MobileRechargePaymenOrderReqDTO mobileRechargePaymenOrderReqDTO = new MobileRechargePaymenOrderReqDTO();
        mobileRechargePaymenOrderReqDTO.setMobileAddress("10029");
        mobileRechargePaymenOrderReqDTO.setMobileAddressDesc("新疆");
        mobileRechargePaymenOrderReqDTO.setMobileSkuId(1004L);
        mobileRechargePaymenOrderReqDTO.setRechargeMobile("18899595231");
        mobileRechargePaymenOrderReqDTO.setSupplierType("CM");
        mobileRechargePaymenOrderReqDTO.setPayFlag("0");
        OrderDO order = new OrderDO();
        order.setCustNo(643L);
        order.setCustName("里斯");
        order.setCustMobile(15821512401L);
        OrderDO orderDO = paymentService.createOrder(order,mobileRechargePaymenOrderReqDTO, SpringContextUtils.getBean(ProductEnum.MOBILE_RECHARGE.handlerName, VirtualPaymenHandler.class));
        log.info("话费充值订单响应结果:{}",JSON.toJSONString(orderDO));
    }

    @Test
    public void testOilCreateOrder(){
        OilRechargePaymenOrderReqDTO oilRechargePaymenOrderReqDTO = new OilRechargePaymenOrderReqDTO();
        oilRechargePaymenOrderReqDTO.setAccNo("18939303030030304");
        oilRechargePaymenOrderReqDTO.setPayFlag("0");
        oilRechargePaymenOrderReqDTO.setProductId(100032L);
        oilRechargePaymenOrderReqDTO.setProductSkuId(100032001L);
        OrderDO order = new OrderDO();
        order.setCustNo(643L);
        order.setCustName("里斯");
        order.setProductId(100032L);
        order.setProductSkuId(100032001L);
        OrderDO orderDO = paymentService.createOrder(order,oilRechargePaymenOrderReqDTO, SpringContextUtils.getBean(ProductEnum.OIL_CARD.handlerName, VirtualPaymenHandler.class));
        log.info("油卡充值订单响应结果:{}",JSON.toJSONString(orderDO));
    }
    @Test
    public void testRefund() {

        OrderDO order = orderService.qryOrderBySn("QD202208240941120002");
        paymentService.refundOrderWithException(order, realPaymentHandler);
    }
    @Test
    public void testOrder(){
        try {
            OrderDO orderDO = orderService.qryOrderBySn("QD202210251711530007");
            //System.out.println(JSON.toJSONString(orderDO));
            System.out.println(orderDO.getCreateTime().getTime());
            System.out.println(DateUtil.parse ((DateUtil.thisYear()-1)+DateUtil.today().substring(4)+" 23:59:59").getTime());
            System.out.println(orderDO.getCreateTime().getTime() <= (DateUtil.parse ((DateUtil.thisYear()-1)+DateUtil.today().substring(4)+" 23:59:59").getTime()) );
            if((orderDO.getCloseType() == 0 || orderDO.getCloseType() == 1 || orderDO.getCloseType() == 2 || orderDO.getCloseType() == 3 || orderDO.getCloseType() == 4) && (orderDO.getCreateTime().getTime() < (DateUtil.parse ((DateUtil.thisYear()-1)+DateUtil.today().substring(4)+" 23:59:59")).getTime())  ){
                System.out.println("成功");
            }
//            orderDOMapper.selectByExample(null);
          //  System.out.println(JSON.toJSONString( orderService.qryOrders( "7342","-1",ProductEnum.MOBILE_RECHARGE,ProductEnum.OIL_CARD,ProductEnum.VIDEO)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void integralQuery(){
       // System.out.println("结果：{} "+spgService.decryptData("==GTKV/RFONTGRH9AZS7JPZT186A04D5FDC","CC800001_3"));
    }
}
