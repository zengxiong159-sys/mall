package com.qdbank.mall.bind;

import com.qdbank.mall.annotation.LockName;
import com.qdbank.mall.annotation.RateLimiter;
import com.qdbank.mall.request.bind.BindRequestInfo;
import com.qdbank.mall.response.bind.UserInfoResDTO;
import com.qdbank.mall.response.bind.WechatBindInfo;

/**
 *  微信绑定服务
 * */
public interface WechatBindService {

    /**获取session*/
    String code2Session(String code);

    /**发送短信验证码*/
    String sendMessageCode(String idNo, String mobilePhoneNo);

    /**验证码验证*/
    void verifyMessageCode(String authCode,String messageCode,String mobilePhoneNo);

    /**绑定*/
    WechatBindInfo bind(BindRequestInfo bindRequestInfo);

    /**解绑*/
    void unBind(String openId);

    /**
     * 绑定信息查询
     *
     * @return
     */
    WechatBindInfo queryBindInfo(String openId);

    /**查询openId*/
    String queryOpenId(String sessionKey);

    /**sessionKet过期检查*/
    boolean sessionKeyExpired(String sessionKey);

    /**
     * 根据手机号获取员工信息
     * @param mobile
     * @return
     */
    public UserInfoResDTO getUserInfoByMobile(String mobile);
}
