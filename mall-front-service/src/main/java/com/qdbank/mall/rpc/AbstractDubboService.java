package com.qdbank.mall.rpc;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hongjianhua
 */
public abstract class AbstractDubboService implements MgtDubboService{

    /**
     * SUCCESS
     */
    public static final String SUCCESS= "0";

    /**
     * FAIL
     */
    public static final String FAIL= "999999";

    /**
     * dataMap
     */
    public static final String DATA_MAP = "dataMap";

    /**
     * errorCode
     */
    public static final String ERROR_CODE = "errorCode";

    /**
     * errorMsg
     */
    public static final String ERROR_MSG = "errorMsg";

    /**
     * 返回成功
     */
    protected static Map<String, Object> responseOk(Object data) {
        return new HashMap<String, Object>(2) {{
            put(DubboConstant.ERROR_CODE, "0");
            put(DubboConstant.DATA_MAP, data);
        }};
    }

    /**
     * 返回失败
     * @param code 错误码
     * @param msg  错误描述
     */
    protected static Map<String, Object> responseError(String code, String msg) {
        if (StringUtils.isBlank(code)) {
            code = DubboConstant.FAIL;
        }
        String finalCode = code;
        return new HashMap<String, Object>(3) {{
            put(DubboConstant.ERROR_CODE, finalCode);
            put(DubboConstant.ERROR_MSG, msg);
            put(DubboConstant.DATA_MAP, null);
        }};
    }

    /**
     * 返回dataMap
     */
    @SuppressWarnings("unchecked")
    protected static Map<String, Object> dataMap(Map<String, Object> map) {
        return (Map<String, Object>) map.get("dataMap");
    }

}
