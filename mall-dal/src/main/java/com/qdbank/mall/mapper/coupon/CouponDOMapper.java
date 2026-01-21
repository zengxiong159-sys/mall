package com.qdbank.mall.mapper.coupon;

import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.coupon.CouponDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface CouponDOMapper {
    long countByExample(CouponDOExample example);

    int deleteByExample(CouponDOExample example);

    int deleteByPrimaryKey(Long couponId);

    int insert(CouponDO record);

    int insertSelective(CouponDO record);

    List<CouponDO> selectByExample(CouponDOExample example);

    CouponDO selectByPrimaryKey(Long couponId);

    int updateByExampleSelective(@Param("record") CouponDO record, @Param("example") CouponDOExample example);

    int updateByExample(@Param("record") CouponDO record, @Param("example") CouponDOExample example);

    int updateByPrimaryKeySelective(CouponDO record);

    int updateByPrimaryKey(CouponDO record);

    int updateExpireCoupon(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    List<String> selectExpireBatchNos(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    int updateTotalCount(@Param("allTotal") Long allTotal, @Param("couponId") Long couponId);

    CouponDO qryCouponDetailByUserCouponId(@Param("userCouponId") Long userCouponId);

    List<String> selectExpireIntegrationCouponBatchNos(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    int updateExpireIntegrationCoupon(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 批量更新优惠券信息
     *
     * @param list 优惠券列表信息
     */
    void batchUpdate(List<CouponDO> list);

    /**
     * 查询所有指定专区现金优惠券(白名单导入方式)批次号
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return  批次号
     */
    List<String> selectDesignatedPrefectureCouponBatchNos(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 更新待生效、已生效指定专区现金优惠券为已失效
     * @param map 参数map
     * @return  操作结果
     */
    int updateExpirePrefectureCoupon(Map<String, Object> map);

    /**
     * 优惠券all total自增
     * @param couponDO  券信息
     * @return  操作结果
     */
    int updateCouponAllTotal(CouponDO couponDO);

    /**
     * 自动失效优惠券
     * @param startDate
     * @param endDate
     * @return
     */
    int updateInvalidationCoupon(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 修改优惠券信息
     * @param couponDO
     * @return
     */
    int updateById(CouponDO couponDO);
}