package com.qdbank.mall.coupon.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.api.StatusEnum;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.coupon.CouponMqSendService;
import com.qdbank.mall.coupon.UserCouponService;
import com.qdbank.mall.domain.CouponMQBO;
import com.qdbank.mall.enums.CouponTypeEnum;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.usercoupon.UserCouponDOMapper;
import com.qdbank.mall.model.usercoupon.UserCouponDO;
import com.qdbank.mall.request.coupon.UpdateUserCouponStatusReqDTO;
import com.qdbank.mall.request.coupon.UserCouponQueryReqDTO;
import com.qdbank.mall.response.coupon.UserCouponResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author zengxiong
 * @Description 用户持券信息管理服务实现类
 * @Date 2021/8/19 15:51
 */
@Service
@Slf4j
public class UserCouponServiceImpl extends BaseServiceImpl implements UserCouponService {
    @Autowired
    private UserCouponDOMapper userCouponDOMapper;

    @Qualifier("umsAdminServiceImpl")
    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private CouponMqSendService couponMqSendService;
    @Override
    public PageInfo<UserCouponResDTO> list(UserCouponQueryReqDTO userCouponQueryReqDTO) {
        PageHelper.startPage(userCouponQueryReqDTO.getPageNum(), userCouponQueryReqDTO.getPageSize());

        if (userCouponQueryReqDTO.getStartSendTime() != null && userCouponQueryReqDTO.getEndSendTime() != null) {
            userCouponQueryReqDTO.setStartSendTime(TimeUtil.dateZeroChange(userCouponQueryReqDTO.getStartSendTime()));
            userCouponQueryReqDTO.setEndSendTime(TimeUtil.dateEndChange(userCouponQueryReqDTO.getEndSendTime()));
        }
        UserCouponDO userCouponDO = new UserCouponDO();
        BeanUtils.copyProperties(userCouponQueryReqDTO, userCouponDO);
        List<UserCouponDO> userCouponDOList = userCouponDOMapper.selectUserCouponList(userCouponDO);

        List<UserCouponResDTO> couponResDTOList = Lists.newArrayList();
        for (UserCouponDO userCoupon : userCouponDOList) {
            //优惠券开始时间默认取实际的发放时间
            userCoupon.setStartTime(userCoupon.getSendTime());

            //根据优惠券类型,特殊处理优惠券开始时间和结束时间
            long couponType = userCoupon.getCouponType();
            if (isBeidouDesignatedPrefectureCoupon(couponType, userCoupon.getSubActivityId())
                    || isUserExchangedIntegrationCoupon(couponType, userCoupon.getBatchNo())) {
                userCoupon.setStartTime(userCoupon.getCreateTime());
                userCoupon.setEndTime(userCoupon.getExpireDate());
                userCoupon.setSendTime(userCoupon.getCreateTime());
            }

            //补全核销订单信息
            if (StatusEnum.USER_COUPON_USED.getCode().equals(userCoupon.getStatus())) {
                UserCouponDO writeOffUserCouponDO = userCouponDOMapper.selectWriteOffOrderInfo(userCoupon.getUserCouponId());
                if (writeOffUserCouponDO != null) {
                    userCoupon.setOrderId(writeOffUserCouponDO.getOrderId());
                    userCoupon.setWriteOffOrderSn(writeOffUserCouponDO.getWriteOffOrderSn());
                    userCoupon.setProductType(writeOffUserCouponDO.getProductType());
                }
            }

            UserCouponResDTO userCouponResDTO = new UserCouponResDTO();
            BeanUtils.copyProperties(userCoupon, userCouponResDTO);
            if (StringUtils.isNotBlank(userCoupon.getCouponAmount())) {
                userCouponResDTO.setCouponAmount(new BigDecimal(userCoupon.getCouponAmount()));
            }
            couponResDTOList.add(userCouponResDTO);
        }
        return getPageInfo(userCouponDOList, couponResDTOList);
    }

    @Override
    public int updateStatus(UpdateUserCouponStatusReqDTO updateUserCouponStatusReqDTO) {
        //与原数据状态相同,不允许更新
        long userCouponId = updateUserCouponStatusReqDTO.getUserCouponId();
        UserCouponDO userCouponDO = userCouponDOMapper.selectByPrimaryKey(userCouponId);

        long oldStatus = userCouponDO.getStatus();
        long newStatus = updateUserCouponStatusReqDTO.getStatus();
        if (oldStatus == newStatus) {
            throw new ApiException(ResultCode.USER_COUPON_STATUS_ERROR);
        }

        int count = 0;
        //仅未使用状态可以操作作废
        if (StatusEnum.USER_COUPON_NOT_USED.getCode().equals(oldStatus)
                && StatusEnum.USER_COUPON_INVLALID.getCode().equals(newStatus)) {
            UserCouponDO couponDO = new UserCouponDO();
            BeanUtils.copyProperties(updateUserCouponStatusReqDTO, couponDO);
            umsAdminService.injectUpdateUserValue(couponDO);
            count = userCouponDOMapper.updateByPrimaryKeySelective(couponDO);
            CouponMQBO couponMQDTO = new CouponMQBO();
            couponMQDTO.setCouponId(updateUserCouponStatusReqDTO.getUserCouponId()+"");
            couponMQDTO.setOperateType(Constant.OPERATE_TYPE_DELETE);
            if(count > 0 ) couponMqSendService.couponMqSend(couponMQDTO);
        }
        return count;
    }

    /**
     * 判断是否是指定专区现金优惠券(发放方式为北斗)
     *
     * @param couponType    优惠券类型
     * @param subActivityId 子活动id
     * @return true:是 false:否
     */
    private boolean isBeidouDesignatedPrefectureCoupon(long couponType, String subActivityId) {
        return (CouponTypeEnum.PREFECTURE_CASH_COUPON.getCode().equals(couponType) || CouponTypeEnum.PREFECTURE_CASH_COUPON.getCode().equals(couponType) || CouponTypeEnum.INTEGRATION_COUPON.getCode().equals(couponType))
                && StringUtils.isNotEmpty(subActivityId);
    }

    /**
     * 判断是否是积分兑换券(自行兑换)
     *
     * @param couponType 优惠券类型
     * @param batchNo    批次号
     * @return true:是 false:否
     */
    private boolean isUserExchangedIntegrationCoupon(long couponType, String batchNo) {
        //设置用户自行兑换积分兑换券有效期,创建时间为开始时间,过期时间为结束时间
        return CouponTypeEnum.INTEGRATION_COUPON.getCode().equals(couponType)
                && StringUtils.isEmpty(batchNo);
    }
}
