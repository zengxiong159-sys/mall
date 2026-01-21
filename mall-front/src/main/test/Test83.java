import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import com.alibaba.fastjson.JSONObject;
import com.qdbank.mall.config.ThirdgroupTemplateConfig;
import com.qdbank.mall.request.third.payment.AccessSignReqDTO;
import com.qdbank.mall.request.third.payment.QryPayReqDTO;
import com.qdbank.mall.response.third.payment.AccessSignResDTO;
import com.qdbank.mall.response.third.payment.QryPayResDTO;
import com.qdbank.mall.util.JsonUtil;
import com.qdbank.mall.util.RSAUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Map;

public class Test83 {

    public static void main(String[] args) throws NoSuchPaddingException, UnsupportedEncodingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {






/*
   String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDNDCdSAOZ0VtS31BpTBNxF8Yg4U/blurbM2uxF95dJeD25klWFlB1lw7omCwntKy5qV43nf7lWA+Kpu2H+PmGzBoM0bpnHQ55ETae7CnvFxT7GJy0NwqJI0u0+7Rq8mKdHFas6Yf0qhJDhfa6f29gOMwqpSaMchXWpsjHG2216EQIDAQAB";
   String privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM0MJ1IA5nRW1LfUGlME3EXxiDhT9uW6tsza7EX3l0l4PbmSVYWUHWXDuiYLCe0rLmpXjed/uVYD4qm7Yf4+YbMGgzRumcdDnkRNp7sKe8XFPsYnLQ3CokjS7T7tGryYp0cVqzph/SqEkOF9rp/b2A4zCqlJoxyFdamyMcbbbXoRAgMBAAECgYAs9bdBcdUWOVvXGvjSr0QlSfqWb0K5yKEAdhhMlydjAqx2HjIGYh24DJyqbs00Zb19r8j7s7PNunhewymPd9ov5Bxt3ZeHO86TCQSr1ZjrZOcgIg9K63WjdnRca70O3XFhr6o1QxQ7CfwROoZXkB8z88ho11GBLDEY8+v+3C8AAQJBAPtKa6/D936pI/+yNIQvmlEoquaa44WVlVSWcqzohaa3Lv8zse2gtFah4rdW8AP+XvQxZ/Omtt5UAFwvrHHZgAECQQDQ4+D5LAVeT8DcDk8CNFn0GEwLPk+HiqLRLnMIMgaWJrHGqHjKRgxkCRcZcFdUnLh93w0//wZiXHdCCeRk+/oRAkEAvUU2ivKdbWdUV/kPmSiJoOtynbK4ABvEmCuhQB7627FJ0qpLL+JFMqNIMjNnIHrtZhv4YgqF3juC8exuhGoAAQJANm/cdjMBpyVTlkVoU6O++XzHfHqdkS4Q5kCbm7wluUWWOLY43EXK/trbmTaMm7FH5w2Uz2OvjzyXm68mRSbhcQJAYF9SVMx6KucLeaqlAgqTEyEO7Xb8L3CD6EUsstbeBNThcgjSbsBNlpZXcpXdQtEoXVCJe8o8JMCeTHrLdc6G8A==";

*/


       String publicKey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCW/V7SE4xt/NPecY4iuHVXC8RwslL3dtEXkZzB9OdCqiePYo/YS3J98bsBxRCespifqpBKFY4nyFytZnNUTl1pq5MFgxaQiLKtNjrg3zoHPISjYHwdeMj5evjtVrL5qcpU/rF9ZBdd0/E2P82tp37qo44xPs4m8OFIF0S66hb0BQIDAQAB";
        String privateKey="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJb9XtITjG38095xjiK4dVcLxHCyUvd20ReRnMH050KqJ49ij9hLcn3xuwHFEJ6ymJ+qkEoVjifIXK1mc1ROXWmrkwWDFpCIsq02OuDfOgc8hKNgfB14yPl6+O1WsvmpylT+sX1kF13T8TY/za2nfuqjjjE+zibw4UgXRLrqFvQFAgMBAAECgYBGl06tiNYu1fV4Gj8JnJO/jpDY34ZKjBJVoo5XX9h/Im48ayg1R5DPsSRtP2T1zSnt/CBZgppjnxk1OKDS2ZByDFSVTcK8ubvUcCg1w5y9wX9yB6Igx2Dsj8dqMQZXbgtYZj8LTMSztOQzXfk0JJJhPjRMTEAX1w/KIeCebj8aAQJBAOn5+ke005ErQBtYrfDwbfMjpIlFclPHzPckIIMxhf8/DOc2Q6dKioY13hhjwt4c9dldWFe8rBLf5na8S/DP/IkCQQClM7MdxWB9I5TXiUBVd/mNT/0LLk9cES7IG/gRh4fQwPe8YdAqBJENGPxuKO4ls7h+nU8Si384Cv8XBMEyg3SdAkBzbSH+fAOfazHOC9qLsWDcgOnr2nnDQR8pkQYFEspjGGy6J7gKcKiT+0Ec0SJSRwE2AWnSpr5Q9WoRi2T/YOvZAkALgTI0HI6/qD6xU+mbCiPi53Mj2DHGo5uya+A2uE2JCCc4g0dP5cmEM/1AXrFXBtEOYD4leHl/maRyIe6iae0xAkAJGEMfUoGYkRIi3Z3ZIjdR88UiZj8BVVQ0brZOFvl/cfuiIfz2hF5369/Ur3fj09og3ARhw7/zxRC2STx8ppYq";


        //加密
   //     String data = RSAUtil.encrypt(publicKey,privateKey,JsonUtil.toJSONString());
        String ss=
                "bG1sZ0srRGVYaEdzMnNyUW4yM09ydHA3ejhXM1dGeGs1UGRWbWRkY1ZCUUJzWHMyUFlzWUlyUVdvcTdSL0NqbnY5L0dmVTlMVEZOd2JhMGxzNExJcXBwbmFTTU54eXNRNU5PWHg1eFduZU9ZVjVPTjk3elRxa092YlY0bjcwcXZuVHV0WWU3d1hlYjlSQTI1cWthb2ZNZEVuUWdYUVlLNWJLS2xnVUU5Z0trPQ==";





                String s = RSAUtil.decryptRSADefault(privateKey,ss);
        System.out.println(s);

        String data = "GbrR5Fzct6XX83XNsVdKUhPhYnsLskaq5L2ecXg49t14EWDeSsZ5Mrg7TtEzIrCRZSHwgvWsS96lPDGBoieyfKqqeZ8PoYXQzhR0rMnjW3gESew0+K4WZes/tIdtgEuIEOVQ6kAkLc8tWszBtXSJ5g==";


             data = new AES(Mode.ECB, Padding.PKCS5Padding, s.getBytes()).decryptStr(data);

                System.out.println(data);






    }




}
