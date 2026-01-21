package com.qdbank.mall.rpc;

/**
 * @author hongjianhua
 */
public interface ErrorInfo {

    /**
     * 获取错误信息
     * @return
     */
    String getErrorMsg();

    /**
     * 获取错误码
     * @return
     */
    default String getErrorCode(){return moduleCode()+getCode();}


    /**
     * 获取错误码
     * @return
     */
    String getCode();

    /**
     * 业务模块信息
     * @return
     */
    String moduleCode();

}
