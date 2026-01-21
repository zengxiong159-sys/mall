package com.qdbank.mall.download;

import com.qdbank.mall.model.order.OrderExportDO;
import com.qdbank.mall.model.orderrefund.OrderRefundExportDO;
import com.qdbank.mall.model.usercoupon.ExportUserCouponDO;
import com.qdbank.mall.request.order.OrderExportReqDTO;
import com.qdbank.mall.request.refund.OrderRefundExportReqDTO;

import java.util.Date;
import java.util.List;

public interface AsyncDownloadService {
    /**
     * 异步生成
     * @param orderExportDOS
     * @param orderExportReqDTO
     */
    public void asyncDownloadOrder(List<OrderExportDO> orderExportDOS,  OrderExportReqDTO orderExportReqDTO);

    /**
     * 退款订单异步导出
     * @param exportDOList
     * @param orderRefundExportReqDTO
     */
    public void asyncDownloadRefund(List<OrderRefundExportDO> exportDOList, OrderRefundExportReqDTO orderRefundExportReqDTO);

    /**
     * 批次券异步导出
     */
    public void asyncDownloadIntegrationBatchCoupon(List<ExportUserCouponDO> exportUserCouponDOList,Long downloadId);

    /**
     * 商品免费兑换券异步导出
     */
    public void asyncDownloadProductCoupon(List<ExportUserCouponDO> exportUserCouponDOList,Long downloadId);

    /**
     * 指定商品现金优惠券异步导出
     * @param exportUserCouponDOS
     * @param downloadId
     */
    public void asyncDownloadCashCoupon( List<ExportUserCouponDO> exportUserCouponDOS,Long downloadId);
    /**
     * 文件处理中校验
     * @param fileName
     * @param createdBy
     */
    public void checkFileExist(String fileName,String createdBy);

    /**
     * 下载数据入库
     * @param fileName
     * @param merchantNo
     * @param createdBy
     * @param fileType
     * @param id
     */
    public void insertDownload(String fileName,Long merchantNo,String createdBy,Long fileType,Long id);
}
