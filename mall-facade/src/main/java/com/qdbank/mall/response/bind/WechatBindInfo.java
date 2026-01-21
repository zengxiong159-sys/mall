package com.qdbank.mall.response.bind;

import com.qdbank.mall.enums.WechatBindStatus;
import lombok.Data;

@Data
public class WechatBindInfo {

    /**
     *  是否已绑定  0。未绑定  1。绑定
     * */
    private String isBind;

    /**
     *  是否本行员工
     * */
    private String isBanker;

    /**
     * 用户类型：0 白名单用户 1 行员
     */
    private String userType;
    /**绑定明细信息
     * */
    private WechatBindDetailInfo wechatBindDetailInfo;



    public static WechatBindInfo buildNoneBindInfo(){
        WechatBindInfo wechatBindInfo=new WechatBindInfo();
        wechatBindInfo.setIsBind(WechatBindStatus.NONE_BIND.getStatus());
        return wechatBindInfo;
    }


}
