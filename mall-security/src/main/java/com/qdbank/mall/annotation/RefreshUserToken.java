package com.qdbank.mall.annotation;

import java.lang.annotation.*;

/**
 *
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RefreshUserToken {

    /**
     * 1 更新
     * 0清除
     * @return
     */
    int type()default 1;

}
