package com.qdbank.mall.mapper.orderreport;

import com.qdbank.mall.model.orderreport.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface OrderReportDOMapper {
    long countByExample(OrderReportDOExample example);

    int deleteByExample(OrderReportDOExample example);

    int deleteByPrimaryKey(Long orderId);

    int insert(OrderReportDO record);

    int insertSelective(OrderReportDO record);

    List<OrderReportDO> selectByExample(OrderReportDOExample example);

    OrderReportDO selectByPrimaryKey(Long orderId);

    int updateByExampleSelective(@Param("record") OrderReportDO record, @Param("example") OrderReportDOExample example);

    int updateByExample(@Param("record") OrderReportDO record, @Param("example") OrderReportDOExample example);

    int updateByPrimaryKeySelective(OrderReportDO record);

    int updateByPrimaryKey(OrderReportDO record);

    /**
     * 根据商户分组导出商户交易信息
     * @param orderReportExportDO
     * @return
     */
    List<OrderTotalReportDO> selectTradeTotal(OrderReportExportDO orderReportExportDO);

    /**
     * 获取优惠券金额
     * @param orderReportExportDO
     * @return
     */
    BigDecimal selectCouponAmount(OrderReportExportDO orderReportExportDO);

    /**
     * 获取需要结算的积分
     * @param orderReportExportDO
     * @return
     */
    Long selectOrderIntegration(OrderReportExportDO orderReportExportDO);

    /**
     * 导出明细
     * @param orderReportExportDO
     * @return
     */
    List<OrderExportDetailDO> selectTradeDetail(OrderReportExportDO orderReportExportDO);

    /**
     * 查支付订单商户
     * @param orderReportExportDO
     * @return
     */
    List<Long> selectOrderMerchantNos(OrderReportExportDO orderReportExportDO);

    /**
     * 积分结算汇总
     * @param orderReportExportDO
     * @return
     */
    List<IntegrationReportDO> selectIntegrationCoupon(TradeMerchantInfoDO orderReportExportDO);

    /**
     * 商户信息查询
     * @param orderReportExportDO
     * @return
     */
    List<TradeMerchantInfoDO> selectIntegrationMerchantInfo(OrderReportExportDO orderReportExportDO);

    /**
     * 指定商品券按日期汇总
     * @param orderReportExportDO
     * @return
     */
    ProductCouponDO selectProductCoupon(TradeMerchantInfoDO orderReportExportDO);

    List<TradeMerchantInfoDO> selectCouponMerchantInfo(OrderReportExportDO orderReportExportDO);
}