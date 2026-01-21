package com.qdbank.mall.coupon;

import com.github.pagehelper.PageInfo;
import com.qdbank.mall.domain.CouponMQBO;
import com.qdbank.mall.request.coupon.UpdateUserCouponStatusReqDTO;
import com.qdbank.mall.request.coupon.UserCouponQueryReqDTO;
import com.qdbank.mall.response.coupon.UserCouponResDTO;

/**
 * @Author zengxiong
 * @Description 用户持券信息服务
 * @Date 2021/8/19 15:48
 */
public interface UserCouponService {
    /**
     * 分页查询用户持券信息列表
     *
     * @param userCouponQueryReqDTO 筛选条件
     * @return 用户持券信息列表
     */
    PageInfo<UserCouponResDTO> list(UserCouponQueryReqDTO userCouponQueryReqDTO);

    /**
     * 更新用户使用券状态
     *
     * @param updateUserCouponStatusReqDTO 用户使用券信息
     * @return 更新结果
     */
    int updateStatus(UpdateUserCouponStatusReqDTO updateUserCouponStatusReqDTO);

}
