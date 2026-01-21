package com.qdbank.mall.util;


/**
 * @author Qdccb
 */
public class ContextHolder {

    /**
     * AES key
     */
    private static final ThreadLocal<String> AES_KEY = new ThreadLocal<>();

    /**
     * new interface flag
     */
    private static final ThreadLocal<Boolean> REQUEST_FLAG = new ThreadLocal<>();


    public static void setAesKey(String value) {
        AES_KEY.set(value);
    }

    public static String getAesKey() {
        return AES_KEY.get();
    }

    public static void removeAesKey() {
        AES_KEY.remove();
    }

    public static void setRequestFlag(Boolean flag) {
        REQUEST_FLAG.set(flag);
    }

    public static Boolean getRequestFlag() {
        return REQUEST_FLAG.get();
    }

    public static void removeRequestFlag() {
        REQUEST_FLAG.remove();
    }
}
