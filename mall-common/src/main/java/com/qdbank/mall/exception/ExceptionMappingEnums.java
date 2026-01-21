package com.qdbank.mall.exception;

import com.qdbank.mall.api.IErrorCode;
import com.qdbank.mall.api.ResultCode;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 *  异常与错误码映射关系
 * */
public enum ExceptionMappingEnums {

    MESSAGE_VERIFY_ERROR(MessageVerifyErrorException.class,ResultCode.MESSAGE_CODE_ERROR),
    MESSAGE_VERIFY_TIMEOUT_ERROR(MessageVerifyTimeOutErrorException.class,ResultCode.MESSAGE_CODE_ERROR),
    MOBILE_PHONE_NO_INCONSISTENT(MobilePhoneNoInconsistentException.class,ResultCode.MOBILE_PHONE_NO_INCONSISTENT),
    NONE_BANK_EMPLOYEE(NoneBankEmployeeException.class,ResultCode.NOT_BANK_EMPLOYEE),
    SESSION_TIMEOUT(SessionTimeOutException.class,ResultCode.SESSION_KEY_TIMEOUT),
    NOT_EXISTS_AVAILABLE_ACCOUNT(AvailableAccountNotExistsException.class,ResultCode.NOT_EXISTS_AVAILABLE_ACCOUNT),

    DEFAULT(Exception.class, ResultCode.SYSTEM_EXCEPTION)
    ;

    Class<? extends Throwable> exceptionClass;
    IErrorCode errorCode;

    ExceptionMappingEnums(Class<? extends Throwable> exceptionClass, IErrorCode errorCode){
       this.exceptionClass=exceptionClass;
       this.errorCode=errorCode;
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }

    public Class<? extends Throwable> getExceptionClass() {
        return exceptionClass;
    }

    public static IErrorCode getErrorCode(Class exceptionClass){
        return Arrays.stream(values())
                .filter(mapping-> StringUtils.equals(mapping.getExceptionClass().getName(),exceptionClass.getName()))
                .findFirst().orElse(DEFAULT).getErrorCode();
    }
}
