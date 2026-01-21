package com.qdbank.mall.mapper.orderrefundreport;

import com.qdbank.mall.model.orderrefundreport.OrderRefundreportDO;
import com.qdbank.mall.model.orderrefundreport.OrderRefundreportDOExample;
import com.qdbank.mall.model.orderreport.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface OrderRefundreportDOMapper {
    long countByExample(OrderRefundreportDOExample example);

    int deleteByExample(OrderRefundreportDOExample example);

    int deleteByPrimaryKey(Long refundSerial);

    int insert(OrderRefundreportDO record);

    int insertSelective(OrderRefundreportDO record);

    List<OrderRefundreportDO> selectByExample(OrderRefundreportDOExample example);

    OrderRefundreportDO selectByPrimaryKey(Long refundSerial);

    int updateByExampleSelective(@Param("record") OrderRefundreportDO record, @Param("example") OrderRefundreportDOExample example);

    int updateByExample(@Param("record") OrderRefundreportDO record, @Param("example") OrderRefundreportDOExample example);

    int updateByPrimaryKeySelective(OrderRefundreportDO record);

    int updateByPrimaryKey(OrderRefundreportDO record);

    List<OrderTotalReportDO> selectTradeTotal(OrderReportExportDO orderReportExportDO);


    /**
     * 获取退货的积分
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
     * 获取退款订单优惠券金额
     * @param orderReportExportDO
     * @return
     */
    BigDecimal selectCouponAmount(OrderReportExportDO orderReportExportDO);

    /**
     * 查支退款订单商户
     * @param orderReportExportDO
     * @return
     */
    List<Long> selectOrderMerchantNos(OrderReportExportDO orderReportExportDO);

    /**
     * 退款积分结算汇总
     * @param orderReportExportDO
     * @return
     */
    IntegrationReportDO selectIntegrationCoupon(TradeMerchantInfoDO orderReportExportDO);

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

    List<TradeMerchantInfoDO>selectCouponMerchantInfo(OrderReportExportDO orderReportExportDO);

}