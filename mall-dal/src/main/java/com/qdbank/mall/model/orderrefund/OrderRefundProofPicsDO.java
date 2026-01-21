package com.qdbank.mall.model.orderrefund;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zengxiong
 * @Description 上传凭证图片DO
 * @Date 2021/5/24 10:32
 */
@Data
public class OrderRefundProofPicsDO implements Serializable {
    private static final long serialVersionUID = 7840114641946646698L;

    /**
     * 退款凭证图片名称
     */
    private String proofPicName;

    /**
     * 退款凭证图片url
     */
    private String proofPicUrl;
}
