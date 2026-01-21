package com.qdbank.mall.bo;

import lombok.Data;

/**
 * 状态机业务类型异常
 */
@Data
public class StateMachineError {

    /**
     * 错误标识
     */
    private boolean hasError=false;

    /**
     * 异常信息
     */
    private Exception exception;


}
