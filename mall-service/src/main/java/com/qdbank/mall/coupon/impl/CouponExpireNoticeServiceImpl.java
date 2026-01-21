package com.qdbank.mall.coupon.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.qdbank.mall.coupon.CouponExpireNoticeService;
import com.qdbank.mall.domain.submsg.CouponExpireMsgMQBO;
import com.qdbank.mall.domain.submsg.SubMsgCommonBO;
import com.qdbank.mall.enums.SubMsgTypeEnum;
import com.qdbank.mall.model.usercoupon.UserCouponDetailDO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.submsg.SubMsgMqSendService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static com.qdbank.mall.util.Constant.DECIMALFORMAT;
import static com.qdbank.mall.util.Constant.SUB_MSG_PREFIX;
import static com.qdbank.mall.util.Constant.MALL_COUPON_JUMP_URL;

import java.text.DecimalFormat;
import java.util.List;

/**
 * @Author zengxiong
 * @Description 订阅消息优惠券到期提醒任务
 * @Date 2022/7/26 11:07
 */
@Service
@Slf4j
public class CouponExpireNoticeServiceImpl extends BaseServiceImpl implements CouponExpireNoticeService {

    @Autowired
    private SubMsgMqSendService subMsgMqSendService;

    /**
     * 批量异步发送优惠券到期提醒mq
     * @param userCouponDOList 用户优惠券信息
     */
    @Override
    @Async
    public void batchAsyncSendMq(List<UserCouponDetailDO> userCouponDOList) {
        if (CollectionUtils.isEmpty(userCouponDOList)) {
            log.info("CouponExpireNoticeServiceImpl userCouponDOList is empty");
            return;
        }
        userCouponDOList.forEach(userCouponDO -> {
            SubMsgCommonBO subMsgCommonBO = new SubMsgCommonBO();
            subMsgCommonBO.setType(SubMsgTypeEnum.COUPON_EXPIRED_MSG.getCode());
            subMsgCommonBO.setMsgCode(SUB_MSG_PREFIX + super.generateId());
            subMsgCommonBO.setCustNo(userCouponDO.getCustNo());

            CouponExpireMsgMQBO couponExpireMsgMQBO = new CouponExpireMsgMQBO();
            couponExpireMsgMQBO.setCouponName(userCouponDO.getCouponName());
            couponExpireMsgMQBO.setExpireDate(DateUtil.format(userCouponDO.getExpireDate(), DatePattern.NORM_DATETIME_FORMAT));
            couponExpireMsgMQBO.setCouponAmount("￥" + DECIMALFORMAT.format(userCouponDO.getCouponAmount()));
            couponExpireMsgMQBO.setJumpUrl(MALL_COUPON_JUMP_URL);
            subMsgCommonBO.setContent(couponExpireMsgMQBO);

            subMsgMqSendService.sendSubMsgMq(subMsgCommonBO);
        });

    }
}
