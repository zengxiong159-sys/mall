package com.qdbank.mall.coupon;

import com.github.pagehelper.Page;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.usercoupon.UserCouponDO;
import com.qdbank.mall.request.coupon.*;
import com.qdbank.mall.response.coupon.CouponResDTO;
import com.qdbank.mall.response.coupon.UserCouponResDTO;
import com.qdbank.mall.response.coupon.UserCouponStatusResDTO;

import java.util.List;

public interface CouponService {


    UserCouponResDTO qryCouponDetail(String custNo, Long userCouponId);

    /**
     * 查询券码
     * @param req
     * @return
     */
    List<UserCouponResDTO> qryCouponDetailPage(String custNo,UserCouponReqDTO req);

    CouponResDTO qryCouponDetailById(Long counponId);

    /**
     * 查询你积分列表
     * @param req
     * @return
     */
    List<CouponResDTO> qryIntegralCouponPage(CouponQueryReqDTO req);

    /**
     * 获取用户优惠券列表
     * @param req
     * @return
     */
    List<UserCouponResDTO> countUseCounpon(String custNo,UserCouponReqDTO req);


    /**
     * 校验券状态
     * @param order
     */
    void checkUserCoupon(OrderDO order);

    void resetCouponStatus(OrderDO order,Long afterStatus);

    /**
     * 更新券状态
     * @param order
     * @param beforeStatus
     * @param afterStatus
     * @return
     */
    boolean activeUserCoupon(OrderDO order,Long beforeStatus,Long afterStatus);

    /**
     * 创建用户优惠券
     * @param userCoupon
     * @return
     */
    Integer createUserCoupon(UserCouponDO userCoupon);

    /**
     * 根据客户号查询指定专区现金优惠券列表
     * @param userCouponDO    用户持券信息
     * @return  指定专区现金优惠券列表
     */
    List<UserCouponResDTO> queryPrefectureUserCouponList(UserCouponDO userCouponDO);

    /**
     * 查询用户优惠券状态
     * @param userCouponStatusReqDTO
     * @return
     */
    UserCouponStatusResDTO queryCouponStatus(UserCouponStatusReqDTO userCouponStatusReqDTO);

    /**
     * 查询券码
     * @param
     * @return
     */
    List<UserCouponResDTO> selectCouponDetailPage(UserCouponListReqDTO userCouponReqDTO);

}
