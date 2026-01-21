package com.qdbank.mall.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @Description: 加密工具 RSA+ASE+SHA256(非对称加密（对称秘钥），对称加密数据，Sha256消息摘要，RSA签名)
 */
@Slf4j
public class RSASecurityUtil {

    public static final String KEY_ALGORITHM = "RSA/ECB/PKCS1Padding";
    /**
     * 密钥长度，用来初始化
     */
    private static final int KEYSIZE = 1024;
    // 加密数据和秘钥的编码方式
    public static final String UTF_8 = "UTF-8";

    public static final String RSA_ALGORITHM_NOPADDING = "RSA";

    /**
     * Description:默认的RSA解密方法 一般用来解密 参数 小数据
     *
     * @param privateKeyStr
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String decryptRSADefault(String privateKeyStr, String data) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM_NOPADDING);
        byte[] privateKeyArray = privateKeyStr.getBytes();
        byte[] dataArray = Base64.decodeBase64(data.getBytes());
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyArray));
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM_NOPADDING);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(Base64.decodeBase64(dataArray)), UTF_8);
    }

    public static String encryptRSADefault(String publicKeyStr, String plainText) throws Exception {
        byte[] dataArray = plainText.getBytes("UTF-8");
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM_NOPADDING);
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKeyStr));
        return new String(Base64.encodeBase64(Base64.encodeBase64(cipher.doFinal(dataArray))));
    }

    /**
     * 私钥解密
     *
     * @param aDecryptingData java.lang.String
     * @return java.lang.String
     */
    public static String decryptDataByPrivate(String privateKeyStr, String aDecryptingData) throws Exception {
        Base64 base64Decoder = new Base64();
        byte[] buffer = base64Decoder.decode(privateKeyStr.getBytes()); // base64Decoder.decode(
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPrivateKey RSAPrivateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);

        if (aDecryptingData == null) {
            return null;
        }
        aDecryptingData = aDecryptingData.replaceAll(" ", "+");
        byte[] workdata = decryptBASE64(aDecryptingData);
        if (Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
        log.info("BC:" + Security.getProvider("BC"));
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM, "BC");
        cipher.init(Cipher.DECRYPT_MODE, RSAPrivateKey);
        int blockSize = cipher.getBlockSize();
        ByteArrayOutputStream baos = new ByteArrayOutputStream(64);

        for (int i = 0; workdata.length - i * blockSize > 0; i++) {
            baos.write(cipher.doFinal(workdata, i * blockSize, blockSize));
        }
        return new String(baos.toByteArray());
    }

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        byte[] b = key.getBytes();
        Base64 base64 = new Base64();
        return base64.decode(b);
    }

    public static PublicKey getPublicKey(String publicKeyStr) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyStr));
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM_NOPADDING);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

}
