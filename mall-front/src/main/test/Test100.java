import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import com.qdbank.mall.util.BsnUtil;
import com.qdbank.mall.util.JsonUtil;

import java.util.*;

public class Test100 {

    public static void main(String[] args) throws Exception {
//        System.out.println(BsnUtil.convertBigdecimal(11));
//        System.out.println(BsnUtil.convertBigdecimal(3L));
//        System.out.println(BsnUtil.convertBigdecimal(2d));
        System.out.println((DateUtil.thisYear()-1)+DateUtil.today().substring(4));

    }


}
