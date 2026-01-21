import com.qdbank.mall.order.business.handler.RealPaymentHandler;
import com.qdbank.mall.response.coupon.UserCouponResDTO;
import com.qdbank.mall.util.RSASignature;
import com.qdbank.mall.util.RSAUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Test2 {

    public static void main(String[] args) throws IOException {

        RealPaymentHandler realPaymentHandler  = new RealPaymentHandler();

        realPaymentHandler.byProductCount(4,"3",new BigDecimal(0),"3",new BigDecimal(5));









    }



}
