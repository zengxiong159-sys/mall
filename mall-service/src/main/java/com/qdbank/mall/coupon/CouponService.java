package com.qdbank.mall.coupon;

import com.github.pagehelper.PageInfo;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.request.coupon.*;
import com.qdbank.mall.response.coupon.CouponResDTO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public interface CouponService {
    /**
     * 新建优惠券
     * @param couponReqDTO
     * @return
     */
    @Transactional
    public int create(CouponReqDTO couponReqDTO);

    /**
     * 获取优惠券列表
     * @param couponLikeQueryReqDTO
     * @return
     */
    public PageInfo<CouponResDTO> list(CouponLikeQueryReqDTO couponLikeQueryReqDTO);

    /**
     * 修改优惠券信息
     * @param updateCouponReqDTO
     * @return
     */
    @Transactional
    public int update(UpdateCouponReqDTO updateCouponReqDTO, MultipartFile file);

    /**
     * 修改优惠券状态
     * @param couponStatusReqDTO
     * @return
     */
    public int updateStatus(UpdateCouponStatusReqDTO couponStatusReqDTO);

    /**
     * 导出优惠券信息
     * @param batchNo   批次号
     */
    CommonResult exportCoupons( String batchNo,Long couponType);

    /**
     * 定时任务扫描更新过期优惠券状态
     * @return
     */
    @Transactional
    public int updateExpireCoupon(Date date);

    /**
     * 优惠券信息详情
     * @param couponDetailQueryReqDTO   优惠券信息详情请求参数DTO
     * @return  优惠券详情
     */
    CouponResDTO detail(CouponDetailQueryReqDTO couponDetailQueryReqDTO);
}
