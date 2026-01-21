package com.qdbank.mall.rpc;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DubboError implements ErrorInfo {
    NETWORK_ERROR("0001","中台通讯异常"),
    ;

    private String errorCode;
    private String errorMsg;


    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public String getCode() {
        return errorCode;
    }

    @Override
    public String moduleCode() {
        return "RPC";
    }

    public static String formateError(String errorCode){
        for(ErrorInfo errorInfo:values()){
            String _code = errorInfo.moduleCode()+errorInfo.getCode();
            if(_code.equals(errorCode)){
                return errorInfo.getErrorMsg();
            }
        }
        return null;
    }
}
