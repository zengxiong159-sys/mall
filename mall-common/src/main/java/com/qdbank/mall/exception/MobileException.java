package com.qdbank.mall.exception;


import com.qdbank.mall.api.IErrorCode;

/**
 * 自定义API异常
 * Created by ningyuehuai on 2020/10/27.
 */
public class MobileException extends RuntimeException {
    private IErrorCode errorCode;

    private String code;

    public MobileException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public MobileException(String code,String message) {
        super(message);
        this.code=code;
    }

    public MobileException(String message) {
        super(message);
    }

    public MobileException(Throwable cause) {
        super(cause);
    }

    public MobileException(String message, Throwable cause) {
        super(message, cause);
    }
    public IErrorCode getErrorCode() {
        return errorCode;
    }

    public String getCode() {
        return code;
    }
}
