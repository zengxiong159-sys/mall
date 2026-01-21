package com.qdbank.mall.refund;

import com.github.pagehelper.PageInfo;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.model.orderrefund.OrderRefundDO;
import com.qdbank.mall.model.trade.TradeFileDataDO;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.refund.OrderRefundExportReqDTO;
import com.qdbank.mall.request.refund.OrderRefundHandleReqDTO;
import com.qdbank.mall.request.refund.OrderRefundLikeQueryReqDTO;
import com.qdbank.mall.response.refund.OrderRefundDetailResDTO;
import com.qdbank.mall.response.refund.OrderRefundResDTO;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * OrderRefundService
 *
 * @author shaoshihang
 * @date 2021/3/10 10:40
 * @since 1.0.0
 */
public interface OrderRefundService {

    /**
     * 退款列表
     * @param orderRefundLikeQueryReqDTO
     * @return
     */
    public PageInfo<OrderRefundResDTO> list(OrderRefundLikeQueryReqDTO orderRefundLikeQueryReqDTO);

    /**
     * 导出
     * @param response
     */
    public CommonResult excelExport(HttpServletResponse response, OrderRefundExportReqDTO OrderRefundExportReqDTO);

    /**
     * 查询详情
     * @param commonIDReqDTO
     * @return
     */
    public OrderRefundDetailResDTO getItem(CommonIDReqDTO commonIDReqDTO);

    /**
     * 退款处理
     * @param orderRefundHandleReqDTO
     */
    public int orderHandle(OrderRefundHandleReqDTO orderRefundHandleReqDTO);

    /**
     * 根据退款完成时间查询退款交易
     * @return
     */
    public List<TradeFileDataDO> selectHandFinishTime(Date date);

    void refundError(String refundSeriNo);
}
