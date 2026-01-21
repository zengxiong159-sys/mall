package bsn;

import com.alibaba.fastjson.JSON;
import com.qdbank.mall.feign.GatewayFeignService;
import com.qdbank.mall.request.gatawayrequest.GatawayReqDTO;
import com.qdbank.mall.request.gatawayrequest.GwHeader;
import com.qdbank.mall.request.integral.IntegralBalanceReqDTO;
import com.qdbank.mall.response.feign.FeignResponse;
import com.qdbank.mall.response.feign.integral.IntegralResDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class MobileTest extends MallTest{
//
//    @Autowired
//    OrderService orderService;
//
//    @Autowired
//    PaymentService paymentService;
//
//    @Autowired
//    MobileBankProxySendServiceImpl mobileBankProxySendService;
    @Autowired
    private GatewayFeignService gatewayFeignService;
//    @Test
//    public void test(){
//        int i = 0;
//        while(i++>=0){
//            try{
//                if(i<100){
//                    PhoneResDTO res=mobileBankProxySendService.phone("12345671234");
//                    System.out.println(JsonUtil.toJSONString(res));
//                }else{
//                    PhoneResDTO res=mobileBankProxySendService.phone("18621111111");
//                    System.out.println(JsonUtil.toJSONString(res));
//                }
//
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//
//        }
//    }
    @Test
    public void testGateway(){
        GatawayReqDTO gatawayReqDTO = new GatawayReqDTO();
        GwHeader gwHeader = new GwHeader();
        gwHeader.setGwVer("1");
        gwHeader.setSvcId("17070");
        gwHeader.setTellerId("*SPGUSER");
        gatawayReqDTO.setGwSysHeader(gwHeader);
        IntegralBalanceReqDTO integralBalanceReqDTO = new IntegralBalanceReqDTO();
        integralBalanceReqDTO.setCustId("1111");
        gatawayReqDTO.setReqBody(integralBalanceReqDTO);
        try {
            FeignResponse<IntegralResDTO> object = gatewayFeignService.getIntegralByCustId(gatawayReqDTO);
            log.info("是否成功:{}",object.success());
            log.info("结果："+JSON.toJSONString(object));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
