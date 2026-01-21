package com.qdbank.mall.annotation;

/**
 * @author hongjh
 * 分布式锁处理
 */
public interface LockKey {

    /**
     * 组合key
     * @return
     */
    String keyName();

}
