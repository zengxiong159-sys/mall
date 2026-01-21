package com.qdbank.mall.mapper.order;

import com.qdbank.mall.model.order.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
@Repository
public interface OrderDOMapper {
    long countByExample(OrderDOExample example);

    int deleteByExample(OrderDOExample example);

    int insert(OrderDO record);

    int insertSelective(OrderDO record);

    List<OrderDO> selectByExample(OrderDOExample example);

    List<OrderListDO> selectList(OrderLikeQueryDO record);

    List<OrderExportDO> selectListByTime(OrderLikeQueryDO orderDO);

    List<OrderDetailsDO> selectOrderDetail(OrderDO record);

    int updateByExampleSelective(@Param("record") OrderDO record, @Param("example") OrderDOExample example);

    int updateByExampleSelectiveForMachineStatus(@Param("record") OrderDO record, @Param("example") OrderDOExample example);

    int updateByOrderId(@Param("record") OrderDO record);

    OrderDO selectOrderByOrderSn(@Param("record") OrderDO record);

    int updateByExample(@Param("record") OrderDO record, @Param("example") OrderDOExample example);

    Long selectSaleCount(@Param("record") OrderDO record);

    List<Long> selectStatusCount(OrderDO record);

    long countProductByExample(OrderDOExample example);

    List<OrderAndRefundDetailDO> selectRefundOrderList(@Param("orderId")String orderId, @Param("orderSn") String orderSn ,@Param("refundSerial") String refundSerial );

    OrderCouponDetailDO selectCouponOrderDetail(@Param("orderId")Long orderId);

    OrderDO selectByPrimaryKey(Long id);

    /**
     * 查询待通知订单
     * @return
     */
    List<OrderDO>selectNotNoticeOrders();

    /**
     * 查询已支付，但仍为话费为充值中待通知订单
     * @return
     */
    List<OrderDO>selectMobileNotNoticeOrders();


    int updateByPrimaryKeySelective(OrderDO record);

    /**
     * 自动确认收货
     * @return
     */
    int updateOrderStatus(@Param("days") Integer days);

    void batchUpdate(List<OrderDO> list);

    /**
     * 修改过期的积分兑换券状态
     * @param list
     * @return
     */
    int batchUpdateExpireOrder(List<String> list);

    /**
     * 通过积分订单查询被使用的订单号
     * @param orderSn
     * @return
     */
    OrderDO selectHasOrderByIntegrealOrderSn(@Param("orderSn") String orderSn);

    void batchInsertOrder(List<OrderDO> list);

    OrderDO selectByRefundSerino(@Param("refundSerino") String refundSerino);

    int updateSendOrderByOrderSn(OrderImportDO orderImportDO);

    int updateStatusByOrderId(@Param("record") OrderDO record );

    int insertHistoryOrder(@Param("endDate")String endDate);

    List<OrderDO> selectHistoryOrders(@Param("endDate")String endDate);

    void updateUserInfo(@Param("record") OrderDO record);

    int insertHistoryBak();

    List<OrderDO> selectHistoryBak();


}