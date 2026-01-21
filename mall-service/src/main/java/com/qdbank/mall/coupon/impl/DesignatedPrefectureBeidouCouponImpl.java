package com.qdbank.mall.coupon.impl;

import cn.hutool.core.date.DateUtil;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.coupon.AbstractCouponService;
import com.qdbank.mall.coupon.CouponMqSendService;
import com.qdbank.mall.enums.CouponTypeEnum;
import com.qdbank.mall.mapper.usercoupon.UserCouponDOMapper;
import com.qdbank.mall.model.usercoupon.UserCouponDO;
import com.qdbank.mall.request.coupon.CouponDetailQueryReqDTO;
import com.qdbank.mall.request.coupon.CouponReqDTO;
import com.qdbank.mall.request.coupon.UpdateCouponReqDTO;
import com.qdbank.mall.response.coupon.CouponResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @Author zengxiong
 * @Description 指定专区现金优惠券(北斗)
 * @Date 2022/1/26 11:43
 */
@Service
@Slf4j
public class DesignatedPrefectureBeidouCouponImpl extends AbstractCouponService {

    @Autowired
    private UserCouponDOMapper userCouponDOMapper;

    @Override
    public int create(CouponReqDTO couponReqDTO) {
        return 0;
    }

    @Override
    public int update(UpdateCouponReqDTO updateCouponReqDTO, MultipartFile file) {
        return 0;
    }

    @Override
    public CommonResult exportCoupons(String batchNo, Long couponType) {
        return null;
    }
    @Autowired
    private CouponMqSendService couponMqSendService;
    @Override
    public int updateExpireCoupon(Date date) {
        Date weekAgoDate = DateUtil.offsetDay(date, -7);
        int count = userCouponDOMapper.updateExpireUserCoupon(weekAgoDate, date, CouponTypeEnum.PREFECTURE_CASH_COUPON.getCode());
        if(count > 0){
            List<UserCouponDO> userCouponDOList = userCouponDOMapper.queryExpireUserCoupon(weekAgoDate, date, CouponTypeEnum.PREFECTURE_CASH_COUPON.getCode());
            couponMqSendService.couponListMqSend(userCouponDOList);
        }
        return count;
    }

    @Override
    public CouponResDTO detail(CouponDetailQueryReqDTO couponDetailQueryReqDTO) {
        return null;
    }

    public static void main(String[] args) {
        Date weekAgoDate = DateUtil.offsetDay(new Date(), -7);
        System.out.println(weekAgoDate);
    }
}
