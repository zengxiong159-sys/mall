package com.qdbank.mall.response.spgacc001;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zengxiong
 * @Description spgacc001接口响应接口DTO
 * @Date 2022/7/21 11:44
 */
@Data
public class SpgAcc001ResDTO implements Serializable {

    private static final long serialVersionUID = 8596909480527854180L;

    /**
     * datamap信息
     */
    private DataMap dataMap;

}
