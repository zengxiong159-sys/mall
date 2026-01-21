package com.qdbank.mall.order;

import com.github.pagehelper.PageInfo;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.trade.TradeFileDataDO;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.order.OrderExportReqDTO;
import com.qdbank.mall.request.order.OrderLikeQueryReqDTO;
import com.qdbank.mall.request.order.SendOrderReqDTO;
import com.qdbank.mall.request.orderimport.OrderImportReqDTO;
import com.qdbank.mall.request.send.SendReturnReqDTO;
import com.qdbank.mall.response.order.*;
import com.qdbank.mall.response.order.orderdetail.OrderBaseDetailResDTO;
import com.qdbank.mall.response.order.orderdetail.OrderCouponDetailResDTO;
import com.qdbank.mall.response.order.orderdetail.OrderCouponInfoResDTO;
import com.qdbank.mall.response.order.orderdetail.OrderProductInfoResDTO;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * OrderService
 *
 * @author shaoshihang
 * @date 2021/3/8 11:35
 * @since 1.0.0
 */
public interface OrderService {

    public PageInfo<OrderResDTO> list(OrderLikeQueryReqDTO orderLikeQueryReqDTO);

    /**
     * 导出
     * @param response
     */
    public CommonResult excelExport( OrderExportReqDTO orderExportReqDTO);


    /**
     * 发货
     * @param sendOrderReqDTO
     */
    public int sendOrder(SendOrderReqDTO sendOrderReqDTO);

    /**
     * 待审核，待发货数量
     * @return
     */
    public OrderStatusResDTO orderStatus();


    RealOrderDetailResDTO realRefundDetail(String id,boolean isoms);

    RealOrderDetailResDTO realDetail(Long id);

    MobileRechargeOrderDetailResDTO mobileDetail(Long id);

    /**
     * 根据支付完成时间查询订单交易
     * @param date
     * @return
     */
    public List<TradeFileDataDO> selectByPaymentTime(Date date);


    /**
     * 获取订单商品信息
     * @param orderDO
     * @return
     */

    public OrderProductInfoResDTO getProductInfo(OrderDO orderDO);

    /**
     * 获取订单详情
     * @param id
     * @return
     */
    public OrderBaseDetailResDTO orderDetail(Long id);

    OrderCouponInfoResDTO getCouponInfoByUserCouponId(Long exchangeCouponId);

    /**
     * 获取优惠券信息
     * @param exchangeCouponId
     * @return
     */
    public OrderCouponInfoResDTO getCouponInfo(Long exchangeCouponId);

    public BatchSendOrderResDTO batchSendOrder(OrderImportReqDTO orderImportReqDTO);

    /**
     * 订单撤销发货
     * @param sendOrderReqDTO
     * @return
     */
    int sendReturnOrder(SendReturnReqDTO sendOrderReqDTO);

    /**
     * 插入历史订单数据到临时表
     * @param endDate
     * @return
     */
    @Transactional
    int insertHisoryOrder();
    int updateHistoryOrder();
}
