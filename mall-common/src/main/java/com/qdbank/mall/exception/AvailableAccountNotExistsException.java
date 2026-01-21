package com.qdbank.mall.exception;

/**
 *  可用账户不存在
 * */
public class AvailableAccountNotExistsException extends RuntimeException{

    public AvailableAccountNotExistsException(String message){
        super(message);
    }

}
