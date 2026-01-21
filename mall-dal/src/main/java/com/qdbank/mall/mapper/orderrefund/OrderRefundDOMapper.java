package com.qdbank.mall.mapper.orderrefund;

import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.orderrefund.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRefundDOMapper {
    long countByExample(OrderRefundDOExample example);

    int deleteByExample(OrderRefundDOExample example);

    int insert(OrderRefundDO record);

    int insertSelective(OrderRefundDO record);

    List<OrderRefundDO> selectByExample(OrderRefundDOExample example);

    List<OrderRefundListDO> selectList(OrderRefundLikeQueryDO record);

    List<OrderRefundListDO> selectErrorRefundList(OrderRefundLikeQueryDO record);

    OrderDO queryOrderStatus(@Param("refundSerial") String refundSerial);

    OrderRefundDO queryOrderRefundByOrderSn(OrderRefundDO record);

    List<OrderRefundExportDO> selectExport(OrderRefundLikeQueryDO orderRefundDO);

    OrderRefundDetailDO queryDetail(@Param("refundSerial") Long refundSerial);

    int updateByExampleSelective(@Param("record") OrderRefundDO record, @Param("example") OrderRefundDOExample example);

    int updateByRefundSerial(OrderRefundDO record);

    int updateByExample(@Param("record") OrderRefundDO record, @Param("example") OrderRefundDOExample example);

    List<OrderRefundDO> selectByHandleFinishTime(OrderRefundDOExample example);

    /**
     * 查询退款订单对应的凭证图片
     * @param refundSerialNo    退款流水号
     * @return  凭证图片列表
     */
    List<OrderRefundProofPicsDO> selectProofPicsByRefundSn(String refundSerialNo);


    List<OrderRefundWithOrderDetailDO> selectRefundWithOrderByExample(@Param("custNo") String custNo,@Param("seriNo") String seriNo);

}