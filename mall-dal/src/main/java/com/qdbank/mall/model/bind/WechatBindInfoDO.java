package com.qdbank.mall.model.bind;


import lombok.Data;

import java.util.Date;

@Data
public class WechatBindInfoDO {

    /**
     * 微信OPEN_ID
     */
    private String openId;

    /**
     * 身份证号码
     */
    private String idNo;

    /**
     * 微信UNION_ID
     */
    private String unionId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 性别。F 女 M 男
     */
    private String gender;

    /**
     * 手机号码
     */
    private String mobileNo;

    /**
     * 绑定状态 0 失效 1 有效
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date updateDate;

    /**
     * 用户类型：0 白名单用户 1 行员
     */
    private String userType;
}
