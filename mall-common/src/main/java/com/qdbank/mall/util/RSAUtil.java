package com.qdbank.mall.util;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.alibaba.cloud.commons.lang.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;


/**
 * @author Qdccb
 */
@Slf4j
public class RSAUtil {

  public static final String RSA_ALGORITHM_NOPADDING = "RSA";

  public static String encrypt(String publicKey, String privateKey, String data) {
    RSA rsa = new RSA(privateKey, publicKey);
    return rsa.encryptBase64(data.getBytes(StandardCharsets.UTF_8), KeyType.PublicKey);
  }

  public static String decrypt(String privateKey, String publicKey, String data) {
    if(StringUtils.isEmpty(data)) return "";
    RSA rsa = new RSA(privateKey, publicKey);
    return rsa.decryptStr(data, KeyType.PrivateKey);
  }




  /**
   * 小程序系统RSA加解密
   * @param privateKeyStr
   * @param data
   * @return
   * @throws NoSuchAlgorithmException
   * @throws InvalidKeySpecException
   * @throws NoSuchPaddingException
   * @throws InvalidKeyException
   * @throws IllegalBlockSizeException
   * @throws BadPaddingException
   * @throws UnsupportedEncodingException
   */
  public static String decryptRSADefault(String privateKeyStr,String data) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
    KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM_NOPADDING);
    byte[] privateKeyArray = privateKeyStr.getBytes();
    byte[] dataArray = Base64.decodeBase64(data.getBytes());
    PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyArray));
    PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    Cipher cipher = Cipher.getInstance(RSA_ALGORITHM_NOPADDING);
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    return new String(cipher.doFinal(Base64.decodeBase64(dataArray)), "UTF-8");
  }

}