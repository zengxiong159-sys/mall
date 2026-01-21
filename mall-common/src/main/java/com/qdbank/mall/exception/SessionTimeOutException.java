package com.qdbank.mall.exception;

public class SessionTimeOutException extends RuntimeException{

    public SessionTimeOutException(String message){
        super(message);
    }

}
