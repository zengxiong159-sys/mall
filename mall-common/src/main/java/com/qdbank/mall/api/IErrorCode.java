package com.qdbank.mall.api;

/**
 * 封装API的错误码
 * Created by ningyuehuai on 2020/10/19.
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
