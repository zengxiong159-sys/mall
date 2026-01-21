package com.qdbank.mall.model.external;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author sunhaoran
 * @Date 2022/5/13 16:34
 * @Version 1.0
 */

@Data
public class ExternalDO implements Serializable {
    private String code="1";
    private String msg;
    private Object data;
}
