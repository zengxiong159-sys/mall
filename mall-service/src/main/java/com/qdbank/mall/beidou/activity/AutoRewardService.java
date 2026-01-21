package com.qdbank.mall.beidou.activity;

import com.qdbank.mall.response.beidou.activity.req.CommonReqDTO;
import com.qdbank.mall.response.beidou.activity.req.CouponInvalidatedReqDTO;
import com.qdbank.mall.response.beidou.activity.req.PrefectureProductQueryReqDTO;
import com.qdbank.mall.response.beidou.activity.req.UserCouponQueryReqDTO;
import com.qdbank.mall.response.beidou.activity.res.CouponInvalidatedResDTO;
import com.qdbank.mall.response.beidou.activity.res.CouponResDTO;
import com.qdbank.mall.response.beidou.activity.res.PrefectureProductResDTO;

import java.util.List;

/**
 * @Author zengxiong
 * @Description 北斗达标活动自动奖励服务
 * @Date 2021/11/25 14:10
 */
public interface AutoRewardService {

    /**
     * 权益列表查询
     *
     * @return 权益列表
     */
    List<CouponResDTO> getCouponList();

    /**
     * 权益绑定
     *
     * @param commonReqDTO 券信息列表请求DTO
     * @return 绑定结果
     */
    boolean batchUpdateCoupon(CommonReqDTO commonReqDTO);

    /**
     * 专区商品列表查询
     *
     * @param prefectureProductQueryReqDTO 指定专区优惠券信息
     * @return 专区商品列表
     */
    List<PrefectureProductResDTO> prefectureProductList(PrefectureProductQueryReqDTO prefectureProductQueryReqDTO);

    /**
     * 用户持券状态查询
     *
     * @param userCouponQueryReqDTO 用户持券状态查询请求DTO
     * @return 优惠券使用状态
     */
    Long queryUserCoupon(UserCouponQueryReqDTO userCouponQueryReqDTO);

    /**
     * 发券
     *
     * @param couponId 券id
     * @param custNo   通联核心客户号
     * @return 发券结果 true:成功 false:失败
     */
    boolean issueCoupon(Long couponId, String custNo);

    /**
     * 批量失效
     *
     * @param commonReqDTO 优惠券失效请求DTO
     * @return 操作结果
     */
    boolean batchInvalidCoupon(CommonReqDTO commonReqDTO);

    /**
     * 批量查询优惠券是否已失效
     *
     * @param couponInvalidatedReqDTOList 优惠券id列表
     * @return 优惠券是否已失效
     */
    List<CouponInvalidatedResDTO> getCouponInvalidated(List<CouponInvalidatedReqDTO> couponInvalidatedReqDTOList);

}
