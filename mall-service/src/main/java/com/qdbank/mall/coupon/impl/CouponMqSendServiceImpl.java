package com.qdbank.mall.coupon.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.coupon.CouponMqSendService;
import com.qdbank.mall.domain.CouponMQBO;
import com.qdbank.mall.enums.CouponTypeEnum;
import com.qdbank.mall.mapper.coupon.CouponDOMapper;
import com.qdbank.mall.mapper.send.MqSendDOMapper;
import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.coupon.CouponDOExample;
import com.qdbank.mall.model.send.MqSendDO;
import com.qdbank.mall.model.usercoupon.UserCouponDO;
import com.qdbank.mall.model.usercoupon.UserCouponDetailDO;
import com.qdbank.mall.service.SendCouponMqService;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static cn.hutool.core.collection.IterUtil.isNotEmpty;

/**
 * @ClassName CouponMqSendServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/5/23 11:44
 * @Version 1.0
 **/
@Service
public class CouponMqSendServiceImpl extends BaseServiceImpl implements CouponMqSendService {
    @Autowired
    private MqSendDOMapper mqSendDOMapper;
    @Autowired
    private SendCouponMqService sendCouponMqService;
    @Autowired
    private CouponDOMapper couponDOMapper;
    @Override
    public void couponMqSend(CouponMQBO couponMQBO) {
        int succFlag = sendCouponMqService.sendCouponMessage(couponMQBO);
        if(succFlag == 0){//mq写入失败
            MqSendDO mqSendDO = new MqSendDO();
            mqSendDO.setId(super.generateId());
            mqSendDO.setCreateTime(new Date());
            mqSendDO.setUniqueId(couponMQBO.getCouponId());
            mqSendDOMapper.insert(mqSendDO);
        }
    }

    @Override
    public void couponListMqSend(List<UserCouponDO> userCouponDOList) {
        if(CollUtil.isEmpty(userCouponDOList)) return;
        userCouponDOList.stream().forEach(obj->{
            CouponMQBO couponMQBO = new CouponMQBO();
            couponMQBO.setCouponId(obj.getUserCouponId()+"");
            couponMQBO.setOperateType(Constant.OPERATE_TYPE_DELETE);
            this.couponMqSend(couponMQBO);
        });
    }

    @Override
    public void userCouponDetailMqSend(UserCouponDetailDO userCoupon) {
        userCoupon = buildTime(userCoupon);
        CouponMQBO couponMQBO = new CouponMQBO();
        couponMQBO.setCouponId(userCoupon.getUserCouponId()+"");
        couponMQBO.setAvailableBeginTime(DateUtil.format(userCoupon.getStartTime() , DatePattern.NORM_DATETIME_PATTERN));
        couponMQBO.setAvailableEndTime(DateUtil.format(userCoupon.getEndTime()  ,DatePattern.NORM_DATETIME_PATTERN));
        couponMQBO.setCouponAmount(userCoupon.getCouponAmount());
        couponMQBO.setCouponNotice("");
        couponMQBO.setCreateTime(DateUtil.format(userCoupon.getSendTime() != null ? userCoupon.getSendTime() : userCoupon.getStartTime() , DatePattern.NORM_DATETIME_PATTERN));
        couponMQBO.setCustNo(userCoupon.getCustNo());
        couponMQBO.setDescription(userCoupon.getCouponDescription());
        couponMQBO.setMallCouponType(userCoupon.getCouponType()+"");
        couponMQBO.setStatus(userCoupon.getStatus() == 0L ? "20" : "40");
        couponMQBO.setOperateType(userCoupon.getStatus() == 0L ? Constant.OPERATE_TYPE_INSERT : Constant.OPERATE_TYPE_DELETE);
        couponMQBO.setCouponName(userCoupon.getCouponName());
        this.couponMqSend(couponMQBO);
    }

    private boolean isBeidouDesignatedPrefectureCoupon(long couponType, String subActivityId) {
        return CouponTypeEnum.PREFECTURE_CASH_COUPON.getCode().equals(couponType)
                && StringUtils.isNotEmpty(subActivityId);
    }

    private boolean isUserExchangedIntegrationCoupon(long couponType, String batchNo) {
        //设置用户自行兑换积分兑换券有效期,创建时间为开始时间,过期时间为结束时间
        return CouponTypeEnum.INTEGRATION_COUPON.getCode().equals(couponType)
                && StringUtils.isEmpty(batchNo);
    }

    private UserCouponDetailDO buildTime(UserCouponDetailDO userCouponDetail){
        //特殊处理优惠券开始时间和结束时间
        if(userCouponDetail != null) {
                String batchNo = userCouponDetail.getBatchNo();
                if(StringUtils.isNotEmpty(batchNo)) {
                    //先判断是否是指定专区现金优惠券(北斗发放,batchNo有值)
                    if(isBeidouDesignatedPrefectureCoupon(userCouponDetail.getCouponType(), userCouponDetail.getSubActivityId())) {
                        userCouponDetail.setStartTime(userCouponDetail.getCreateTime());
                        userCouponDetail.setEndTime(userCouponDetail.getExpireDate());
                    } else {
                        CouponDOExample couponDOExample = new CouponDOExample();
                        couponDOExample.createCriteria().andBatchNoEqualTo(batchNo);
                        List<CouponDO> couponDOList = couponDOMapper.selectByExample(couponDOExample);
                        if(CollectionUtil.isNotEmpty(couponDOList)) {
                            CouponDO couponDO = couponDOList.get(0);
                            //开始时间默认取实际的发放时间
                            userCouponDetail.setStartTime(couponDO.getSendTime());
                            userCouponDetail.setEndTime(couponDO.getEndTime());
                        }
                    }
                } else {
                    //目前仅积分兑换券自行兑换场景
                    userCouponDetail.setStartTime(userCouponDetail.getCreateTime());
                    userCouponDetail.setEndTime(userCouponDetail.getExpireDate());
                }
        }
        return userCouponDetail;
    }


}
