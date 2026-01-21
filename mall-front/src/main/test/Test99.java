import com.qdbank.mall.response.auth.AuthResDTO;
import com.qdbank.mall.util.JsonUtil;
import io.jsonwebtoken.Claims;

import java.math.BigDecimal;
import java.util.Map;

public class Test99 {

    public static void main(String[] args)  {
        double a = 1.0;

        BigDecimal reuslt =new BigDecimal(a).multiply(new BigDecimal(100));

        String s = a*100+"";

        System.out.println(reuslt.intValue()+"");




    }




}
