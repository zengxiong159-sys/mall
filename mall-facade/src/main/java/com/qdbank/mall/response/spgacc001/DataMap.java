package com.qdbank.mall.response.spgacc001;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zengxiong
 * @Description spgacc001接口datamap信息
 * @Date 2022/7/21 11:54
 */
@Data
public class DataMap implements Serializable {
    private static final long serialVersionUID = 743294633536419682L;
    private BaseInfo baseInfo;
    private UserInfo userInfo;
}
