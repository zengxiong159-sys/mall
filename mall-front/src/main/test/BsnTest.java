import com.qdbank.mall.constant.payment.PaymentTypeEnum;
import com.qdbank.mall.util.BsnUtil;

import java.math.BigDecimal;

public class BsnTest {


    public static void main(String[] args) {
       BigDecimal refundAmt = new BigDecimal("79.8");

        if(refundAmt==null){
            refundAmt=new BigDecimal(0);
        }


        PaymentTypeEnum payment =BsnUtil.initPayType(refundAmt,0L);
        System.out.println(payment);


    }
}
