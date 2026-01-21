package com.qdbank.mall.mapper.usercoupon;

import com.qdbank.mall.model.usercoupon.ExportUserCouponDO;
import com.qdbank.mall.model.usercoupon.UserCouponDO;
import com.qdbank.mall.model.usercoupon.UserCouponDOExample;
import com.qdbank.mall.model.usercoupon.UserCouponDetailDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
@Repository
public interface UserCouponDOMapper {
    long countByExample(UserCouponDOExample example);

    int deleteByExample(UserCouponDOExample example);

    int insert(UserCouponDO record);

    int insertSelective(UserCouponDO record);

    List<UserCouponDO> selectByExample(UserCouponDOExample example);

    int updateByExampleSelective(@Param("record") UserCouponDO record, @Param("example") UserCouponDOExample example);

    int updateByExample(@Param("record") UserCouponDO record, @Param("example") UserCouponDOExample example);

    int batchInsertCoupon(List<UserCouponDO> list);

    List<ExportUserCouponDO> selectCouponDOByBatchNo(@Param("batchNo") String batchNo);

    int deleteByBatchNo(@Param("batchNo") String batchNo);

    int updateStatus(UserCouponDO userCouponDO);

    int deleteByPrimaryKey(Long userCouponId);

    UserCouponDO selectByPrimaryKey(Long userCouponId);

    int updateByPrimaryKeySelective(UserCouponDO record);

    int updateByPrimaryKey(UserCouponDO record);

    List<UserCouponDetailDO> qryCouponDetail(@Param("custNo") String custNo, @Param("userCouponStatus") Long status, @Param("userCouponId") Long userCouponId, @Param("productId") Long productId, @Param("productSkuId") Long productSkuId);

    /**
     * 修改过期的指定商品免费兑换券状态
     *
     * @param list
     * @return
     */
    int updateProductCouponExpireStatus(List<String> list);

    /**
     * 修改积分兑换券过期状态
     *
     * @return
     */
    int updateIntegrationCouponExpireStatus(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 查询已过期的积分兑换订单号
     *
     * @param startDate
     * @param endDate
     * @return
     */
    List<String> selectExpireOrderSN(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 根据券id查询指定批次已使用和已过期数量
     *
     * @param couponId 券id
     * @param isBatch  是否是批次券
     * @return 已使用和已过期数量
     */
    List<HashMap<BigDecimal, BigDecimal>> selectCountByCouponId(@Param("couponId") Long couponId, @Param("isBatch") Boolean isBatch);

    /**
     * 根据券id查询积分兑换优惠券已兑换数量
     *
     * @param couponId 券id
     * @return 积分兑换优惠券已兑换数量
     */
    Long selectExchangeCount(@Param("couponId") Long couponId);

    /**
     * 更新指定批次的用户持券信息数据
     *
     * @param userCouponDO 用户持券信息
     * @return 更新结果
     */
    int updateByBatchNo(UserCouponDO userCouponDO);

    /**
     * 根据批次号查询该批次用户使用券数量(按使用券状态分组)
     *
     * @param batchNo 批次号
     * @return 使用券数量
     */
    List<HashMap<BigDecimal, BigDecimal>> selectCountByBatchNo(@Param("batchNo") String batchNo);

    /**
     * 查询用户优惠券使用信息列表
     *
     * @param userCouponDO 用户优惠券信息
     * @return 用户优惠券使用列表
     */
    List<UserCouponDO> selectUserCouponList(UserCouponDO userCouponDO);

    /**
     * 根据优惠券批次号查询用户持券表数据数量
     *
     * @param batchNo 批次号
     * @return 用户持券数据数量
     */
    Long selectCouponCountByBatchNo(@Param("batchNo") String batchNo);

    /**
     * 查询核销订单信息
     *
     * @param userCouponId 用户持券表id
     * @return 核销订单信息
     */
    UserCouponDO selectWriteOffOrderInfo(@Param("userCouponId") Long userCouponId);

    /**
     * 批量更新用户持券数据为作废
     *
     * @param userCouponDO 用户持券信息
     * @return 更新结果
     */
    int updateUserCouponInvalidByCouponId(UserCouponDO userCouponDO);

    /**
     * 批量更新用户持券数据为作废
     *
     * @param userCouponDO 用户持券信息
     * @return 更新结果
     */
    int updateUserCouponInvalidByBatchNo(UserCouponDO userCouponDO);

    /**
     * 根据客户号查询指定专区现金优惠券列表
     *
     * @param userCouponDO 用户持券信息
     * @return 指定专区现金优惠券列表
     */
    List<UserCouponDetailDO> selectPrefectureUserCouponList(UserCouponDO userCouponDO);

    /**
     * 更新指定日期时间端用户持券的状态为过期
     *
     * @param startDate  开始日期
     * @param endDate    结束日期
     * @param couponType 券类型
     * @return 操作结果
     */
    int updateExpireUserCoupon(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("couponType") Long couponType);

    /**
     * 查询过期优惠券信息
     *
     * @param startDate
     * @param endDate
     * @param couponType
     * @return
     */
    List<UserCouponDO> queryExpireUserCoupon(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("couponType") Long couponType);

    /**
     * 查询所有存量待使用优惠券
     *
     * @return
     */
    List<UserCouponDetailDO> selectHistoryUserCouponList();

    /**
     * 根据主键查询优惠券信息
     *
     * @param userCouponId
     * @return
     */
    UserCouponDetailDO selecUserCouponDetailById(@Param("userCouponId") Long userCouponId);

    /**
     * 查询用户已使用和已过期优惠券列表
     *
     * @param custNo
     * @return
     */
    List<UserCouponDetailDO> selectCouponList(@Param("custNo") String custNo, @Param("status") Long status);

    /**
     * 查询指定时间段即将过期的(名单文件发放)优惠券信息
     *
     * @param endDate   结束日期
     * @return 优惠券列表
     */
    List<UserCouponDetailDO> selectFileExpiringCoupon( @Param("endDate") Date endDate);

    /**
     * 查询指定时间段即将过期的(北斗发放)优惠券信息
     *
     * @param endDate   结束日期
     * @return 优惠券列表
     */
    List<UserCouponDetailDO> selectBeidouExpiringCoupon(@Param("endDate") Date endDate);

    /**
     * 查询指定时间段即将过期的(自行兑换积分兑换券)优惠券信息
     *
     * @param endDate   结束日期
     * @return 优惠券列表
     */
    List<UserCouponDetailDO> selectSelfExchangeExpiringCoupon(@Param("endDate") Date endDate);

    List<UserCouponDetailDO> selectCouponList(@Param("custNo") String custNo,@Param("status") Long status,@Param("startTime") Date statTime,@Param("endTime")Date endTime);
}