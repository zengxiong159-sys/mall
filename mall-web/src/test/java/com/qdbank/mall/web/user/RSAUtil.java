package com.qdbank.mall.web.user;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * @ClassName RSAUtil
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/7/14 14:06
 * @Version 1.0
 **/
public class RSAUtil {
    //生成秘钥对
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }

    //获取公钥(Base64编码)
    public static String getPublicKey(KeyPair keyPair){
        PublicKey publicKey = keyPair.getPublic();
        byte[] bytes = publicKey.getEncoded();
        return byte2Base64(bytes);
    }

    //获取私钥(Base64编码)z
    public static String getPrivateKey(KeyPair keyPair){
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] bytes = privateKey.getEncoded();
        return byte2Base64(bytes);
    }

    //将Base64编码后的公钥转换成PublicKey对象
    public static PublicKey string2PublicKey(String pubStr) throws Exception{
        byte[] keyBytes = base642Byte(pubStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    //将Base64编码后的私钥转换成PrivateKey对象
    public static PrivateKey string2PrivateKey(String priStr) throws Exception{
        byte[] keyBytes = base642Byte(priStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    //公钥加密
    public static byte[] publicEncrypt(byte[] content, PublicKey publicKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    //私钥解密
    public static byte[] privateDecrypt(byte[] content, PrivateKey privateKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    //字节数组转Base64编码
    public static String byte2Base64(byte[] bytes){
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes);
    }

    //Base64编码转字节数组
    public static byte[] base642Byte(String base64Key) throws IOException{
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(base64Key);
    }

    public static void main(String[] args) {
        try {
            //===============生成公钥和私钥，公钥传给客户端，私钥服务端保留==================
            //生成RSA公钥和私钥，并Base64编码
            String publicKeyString="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDNDCdSAOZ0VtS31BpTBNxF8Yg4U/blurbM2uxF95dJeD25klWFlB1lw7omCwntKy5qV43nf7lWA+Kpu2H+PmGzBoM0bpnHQ55ETae7CnvFxT7GJy0NwqJI0u0+7Rq8mKdHFas6Yf0qhJDhfa6f29gOMwqpSaMchXWpsjHG2216EQIDAQAB";
            String privateKeyString="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM0MJ1IA5nRW1LfUGlME3EXxiDhT9uW6tsza7EX3l0l4PbmSVYWUHWXDuiYLCe0rLmpXjed/uVYD4qm7Yf4+YbMGgzRumcdDnkRNp7sKe8XFPsYnLQ3CokjS7T7tGryYp0cVqzph/SqEkOF9rp/b2A4zCqlJoxyFdamyMcbbbXoRAgMBAAECgYAs9bdBcdUWOVvXGvjSr0QlSfqWb0K5yKEAdhhMlydjAqx2HjIGYh24DJyqbs00Zb19r8j7s7PNunhewymPd9ov5Bxt3ZeHO86TCQSr1ZjrZOcgIg9K63WjdnRca70O3XFhr6o1QxQ7CfwROoZXkB8z88ho11GBLDEY8+v+3C8AAQJBAPtKa6/D936pI/+yNIQvmlEoquaa44WVlVSWcqzohaa3Lv8zse2gtFah4rdW8AP+XvQxZ/Omtt5UAFwvrHHZgAECQQDQ4+D5LAVeT8DcDk8CNFn0GEwLPk+HiqLRLnMIMgaWJrHGqHjKRgxkCRcZcFdUnLh93w0//wZiXHdCCeRk+/oRAkEAvUU2ivKdbWdUV/kPmSiJoOtynbK4ABvEmCuhQB7627FJ0qpLL+JFMqNIMjNnIHrtZhv4YgqF3juC8exuhGoAAQJANm/cdjMBpyVTlkVoU6O++XzHfHqdkS4Q5kCbm7wluUWWOLY43EXK/trbmTaMm7FH5w2Uz2OvjzyXm68mRSbhcQJAYF9SVMx6KucLeaqlAgqTEyEO7Xb8L3CD6EUsstbeBNThcgjSbsBNlpZXcpXdQtEoXVCJe8o8JMCeTHrLdc6G8A==";
            String str = "muo4RjI5gotfTtRijutgybOf3nS3IgGCz5kpEpK20feadYSOxIjl0rVJjRvHMg6GwiJJoFBKYYLV+FoCTp5Ipkkjwv3nFOcXNFJ8cpCdkkIOmJromwIDOs/GmyL2Mee1OvgLguoJfsfiEVhrO9jMoOa79B4G1QFwNhwofDPOsw==";
            PrivateKey privateKey = RSAUtil.string2PrivateKey(privateKeyString);
            byte[] base642Byte = RSAUtil.base642Byte(str);
            //用私钥解密
            byte[] privateDecrypt = RSAUtil.privateDecrypt(base642Byte, privateKey);
            //解密后的明文
            System.out.println("解密后的明文: " + new String(privateDecrypt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
