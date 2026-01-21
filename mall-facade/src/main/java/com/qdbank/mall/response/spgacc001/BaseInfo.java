package com.qdbank.mall.response.spgacc001;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zengxiong
 * @Description 用户基础信息
 * @Date 2022/7/21 11:52
 */
@Data
public class BaseInfo implements Serializable {
    private static final long serialVersionUID = -5164690603934177161L;

    /**
     * 手机号
     */
    private String mobilePhone;

    /**
     * 通联客户号
     */
    private String custId;

}
