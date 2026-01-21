import com.qdbank.mall.util.JsonUtil;
import com.qdbank.mall.util.JwtTokenUtil;

import java.util.HashMap;
import java.util.Map;

public class TokenTest {

    public static void main(String[] args) {

        /**
         * tokenHeader: Authorization #JWT存储的请求头
         *   secret: mall-front-secret #JWT加解密使用的密钥
         *   expiration: 604800 #JWT的超期限时间(60*60*24*7)
         *   tokenHead: 'Bearer '  #JWT负载中拿到开头
         */

        String json = "{\"custMobile\":\"15934960263\",\"custNo\":\"7\"}";
        Map map = JsonUtil.parseObject(json,Map.class);

        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        jwtTokenUtil.setTokenHead("Bearer ");
        jwtTokenUtil.setExpiration(604800L);
        jwtTokenUtil.setSecret("mall-front-secret");
        String s =jwtTokenUtil.generateFrontToken(map);
        System.out.println(s);



    }


}
