package com.qdbank.mall.response.spgacc001;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zengxiong
 * @Description 用户信息
 * @Date 2022/7/21 11:53
 */
@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -6436315640620641456L;

    /**
     * unionid
     */
    private String unionId;

    /**
     * 微信头像
     */
    private String avatarUrl;

}
