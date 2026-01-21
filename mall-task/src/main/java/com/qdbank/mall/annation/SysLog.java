

package com.qdbank.mall.annation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author ningyuehuai
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

	String value() default "";
}
