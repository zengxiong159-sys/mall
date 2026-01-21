package bsn;

import com.alibaba.fastjson.JSON;
import com.qdbank.mall.MallFrontMainApplication;
import com.qdbank.mall.bind.WechatBindService;
import com.qdbank.mall.controller.bind.WechatBindController;
import com.qdbank.mall.integral.impl.IntegralServiceImpl;
import com.qdbank.mall.request.bind.BindRequestInfo;
import com.qdbank.mall.request.bind.UserInfoReqDTO;
import com.qdbank.mall.request.integral.IntegralBalanceReqDTO;
import com.qdbank.mall.response.bind.UserInfoResDTO;
import com.qdbank.mall.response.feign.integral.IntegralDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = MallFrontMainApplication.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("sit2")
public class MallTest {

    @Autowired
    IntegralServiceImpl integralService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private WechatBindService wechatBindService;
    @Autowired
    private WechatBindController wechatBindController;
    @Test
    public void connectService(){
        String key = "340828199108083340501";
//        Object object = redisTemplate.opsForValue().get(key);
//        Long increment = redisTemplate.opsForValue().increment(key, 1);
//        System.out.println("object ："+increment);
//        if(object != null){
//
//            Long verifyCount = Long.valueOf(object.toString());
//            System.out.println("新增后值："+verifyCount);
//            System.out.println(verifyCount);
//        }
       boolean b = redisTemplate.delete(key);
        System.out.println(b);
    }
    @Test
    public void testSendMessage(){
        for(int i = 0 ; i < 3;i++){
            wechatBindService.sendMessageCode("120103199610220725","17602496652");
        }
    }
    @Test
    public void testBind(){
        BindRequestInfo bindRequestInfo = new BindRequestInfo();
        bindRequestInfo.setAuthCode("1111");
        bindRequestInfo.setIdCde("340828199108084024");
        bindRequestInfo.setMobileNo("15821512401");
        bindRequestInfo.setSessionKey("1111");
        wechatBindController.bind(bindRequestInfo);
    }
    @Test
    public void queryUserInfo(){
        try {

            UserInfoReqDTO userInfoReqDTO = new UserInfoReqDTO();
            userInfoReqDTO.setMobile("15822449874");
            System.out.println(JSON.toJSONString(wechatBindController.queryUserInfo(userInfoReqDTO))) ;
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
