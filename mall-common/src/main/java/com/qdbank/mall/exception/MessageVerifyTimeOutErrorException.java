package com.qdbank.mall.exception;

public class MessageVerifyTimeOutErrorException extends RuntimeException{

    public MessageVerifyTimeOutErrorException(String message){
        super(message);
    }
}
