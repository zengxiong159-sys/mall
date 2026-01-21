package com.qdbank.mall.download.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.constant.payment.orderstatus.*;
import com.qdbank.mall.domain.ExportDataBO;
import com.qdbank.mall.download.AsyncDownloadService;
import com.qdbank.mall.enums.CouponTypeEnum;
import com.qdbank.mall.enums.IdentificationEnum;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.coupon.CouponDOMapper;
import com.qdbank.mall.mapper.download.DownloadDOMapper;
import com.qdbank.mall.mapper.order.OrderDOMapper;
import com.qdbank.mall.mapper.prefecture.PrefectureDOMapper;
import com.qdbank.mall.mapper.product.ProductDOMapper;
import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.download.DownloadDO;
import com.qdbank.mall.model.download.DownloadDOExample;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.order.OrderDOExample;
import com.qdbank.mall.model.order.OrderExportDO;
import com.qdbank.mall.model.orderrefund.OrderRefundExportDO;
import com.qdbank.mall.model.prefecture.PrefectureDO;
import com.qdbank.mall.model.product.ProductDO;
import com.qdbank.mall.model.usercoupon.ExportUserCouponDO;
import com.qdbank.mall.product.ProductService;
import com.qdbank.mall.request.order.OrderExportReqDTO;
import com.qdbank.mall.request.refund.OrderRefundExportReqDTO;
import com.qdbank.mall.response.coupon.ExportCouponResDTO;
import com.qdbank.mall.response.order.ExportOrderResDTO;
import com.qdbank.mall.response.product.ProductCategoryID;
import com.qdbank.mall.response.refund.ExportApplyRefundResDTO;
import com.qdbank.mall.service.MinioService;
import com.qdbank.mall.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName AsyncDownloadServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/1/14 11:01
 * @Version 1.0
 **/
@Service
@Slf4j
@RefreshScope
public class AsyncDownloadServiceImpl implements AsyncDownloadService {
    @Autowired
    private ProductService productService;
    @Autowired
    private DownloadDOMapper downloadDOMapper;
    @Autowired
    private OrderDOMapper orderDOMapper;
    @Autowired
    private ProductDOMapper productDOMapper;
    @Autowired
    private CouponDOMapper couponDOMapper;
    private final static String FILE_PATH = "/home/aomsapp/";
    @Autowired
    private PrefectureDOMapper prefectureDOMapper;
    @Autowired
    private MinioService minioService;
    @Value(value = "${minio.domainName:}")
    private String minioUrl;
    @Override
    @Async
    public void asyncDownloadOrder(List<OrderExportDO> orderDOList, OrderExportReqDTO orderExportReqDTO) {
        try{
            ExportDataBO<ExportOrderResDTO> exportDataBO = this.buildOrderData(orderDOList,orderExportReqDTO);
            String fileName = orderExportReqDTO.getFileLocalPath()+exportDataBO.getFileName()+exportDataBO.getFileSuffix() ;
            this.generateExcle(exportDataBO,fileName);
            String storePath = uploadFileToFastDfs(fileName,exportDataBO.getFileName());
            updateFilePath(orderExportReqDTO.getDownloadId(),orderExportReqDTO.getMerchantNo(),storePath);
            deleteLocalFile(fileName);
        }catch (ApiException api){
            log.error("生成文件id:{}异常:{}",orderExportReqDTO.getDownloadId(),api);
            updateDownloadStatus(orderExportReqDTO.getDownloadId());
            throw api;
        }catch (Exception e){
            log.error("生成文件id:{}异常:{}",orderExportReqDTO.getDownloadId(),e);
            updateDownloadStatus(orderExportReqDTO.getDownloadId());
            throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
        }

    }

    @Override
    @Async
    public void asyncDownloadRefund(List<OrderRefundExportDO> exportDOList, OrderRefundExportReqDTO orderRefundExportReqDTO) {
       try {
           ExportDataBO<ExportApplyRefundResDTO> exportDataBO = this.buildRefundData(exportDOList,orderRefundExportReqDTO);
           String fileName = orderRefundExportReqDTO.getFileLocalPath()+exportDataBO.getFileName()+exportDataBO.getFileSuffix() ;
           this.generateExcle(exportDataBO,fileName);
           String storePath = uploadFileToFastDfs(fileName,exportDataBO.getFileName());
           updateFilePath(orderRefundExportReqDTO.getDownloadId(),orderRefundExportReqDTO.getMerchantNo(),storePath);
           deleteLocalFile(fileName);
       }catch (ApiException api){
           log.error("生成退款文件id:{}异常:{}",orderRefundExportReqDTO.getDownloadId(),api);
           updateDownloadStatus(orderRefundExportReqDTO.getDownloadId());
           throw api;
       }catch (Exception e){
           log.error("生成退款文件id:{}异常:{}",orderRefundExportReqDTO.getDownloadId(),e);
           updateDownloadStatus(orderRefundExportReqDTO.getDownloadId());
           throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
       }
    }

    @Override
    @Async
    public void asyncDownloadIntegrationBatchCoupon(List<ExportUserCouponDO> exportUserCouponDOList,Long downloadId) {
        try {
            ExportDataBO<ExportCouponResDTO> exportDataBO = this.buildIntegrationBatchData(exportUserCouponDOList);
            String fileName = FILE_PATH+exportDataBO.getFileName()+exportDataBO.getFileSuffix() ;
            this.generateExcle(exportDataBO,fileName);
            String storePath = uploadFileToFastDfs(fileName,exportDataBO.getFileName());
            updateFilePath(downloadId,null,storePath);
            deleteLocalFile(fileName);
        }catch (ApiException api){
            log.error("生成积分批次兑换券文件id:{}异常:{}",downloadId,api);
            updateDownloadStatus(downloadId);
            throw api;
        }catch (Exception e){
            log.error("生成积分批次兑换券文件id:{}异常:{}",downloadId,e);
            updateDownloadStatus(downloadId);
            throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
        }
    }

    @Override
    @Async
    public void asyncDownloadProductCoupon(List<ExportUserCouponDO> exportUserCouponDOList,Long downloadId) {
        try {
            ExportDataBO<ExportCouponResDTO> exportDataBO = this.buildProductCouponData(exportUserCouponDOList);
            String fileName = FILE_PATH+exportDataBO.getFileName()+exportDataBO.getFileSuffix() ;
            this.generateExcle(exportDataBO,fileName);
            String storePath = uploadFileToFastDfs(fileName,exportDataBO.getFileName());
            updateFilePath(downloadId,null,storePath);
            deleteLocalFile(fileName);
        }catch (ApiException api){
            log.error("生成商品指定免费兑换券文件id:{}异常:{}",downloadId,api);
            updateDownloadStatus(downloadId);
            throw api;
        }catch (Exception e){
            log.error("生成商品指定免费兑换券文件id:{}异常:{}",downloadId,e);
            updateDownloadStatus(downloadId);
            throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
        }
    }

    @Override
    @Async
    public void asyncDownloadCashCoupon(List<ExportUserCouponDO> exportUserCouponDOS, Long downloadId) {
        try {
            ExportDataBO<ExportCouponResDTO> exportDataBO = this.buildCashCouponData(exportUserCouponDOS);
            String fileName = FILE_PATH+exportDataBO.getFileName()+exportDataBO.getFileSuffix() ;
            this.generateExcle(exportDataBO,fileName);
            String storePath = uploadFileToFastDfs(fileName,exportDataBO.getFileName());
            updateFilePath(downloadId,null,storePath);
            deleteLocalFile(fileName);
        }catch (ApiException api){
            log.error("生成商品指定现金兑换券文件id:{}异常:{}",downloadId,api);
            updateDownloadStatus(downloadId);
            throw api;
        }catch (Exception e){
            log.error("生成商品指定现金兑换券文件id:{}异常:{}",downloadId,e);
            updateDownloadStatus(downloadId);
            throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
        }
    }

    @Override
    public void checkFileExist(String fileName, String createdBy) {
        DownloadDOExample downloadDOExample = new DownloadDOExample();
        downloadDOExample.createCriteria().andFileNameEqualTo(fileName).andCreatedByEqualTo(createdBy).andStatusEqualTo(0L);
        List<DownloadDO> downloadDOS = downloadDOMapper.selectByExample(downloadDOExample);
        if(CollUtil.isNotEmpty(downloadDOS)) throw new ApiException(ResultCode.FILE_DOWN_LOADING);
    }

    @Override
    public void insertDownload(String fileName,Long merchantNo,String createdBy,Long fileType,Long id) {
        DownloadDO downloadDO = new DownloadDO();
        downloadDO.setFileName(fileName);
        downloadDO.setMerchantNo(merchantNo);
        downloadDO.setId(id);
        downloadDO.setCreatedBy(createdBy);
        downloadDO.setCreateTime(new Date());
        downloadDO.setUpdateTime(new Date());
        downloadDO.setFileType(fileType);
        downloadDO.setStatus(0L);
        downloadDOMapper.insert(downloadDO);
    }

    private ExportDataBO<ExportCouponResDTO> buildProductCouponData(List<ExportUserCouponDO> exportUserCouponDOList){
        ExportDataBO<ExportCouponResDTO> exportDataBO = new ExportDataBO<ExportCouponResDTO>(Constant.COUPON_FILE_NAME, Constant.COUPON_FILE_NAME, Constant.COUPON_FILE_SUFFIX, getProductCouponFileHeader());
        List<List<String>> datas = CollUtil.newLinkedList();
        exportUserCouponDOList.forEach(exportUserCouponDO -> {
                    OrderDOExample orderDOExample = new OrderDOExample();
                    orderDOExample.createCriteria().andUserCouponIdEqualTo(exportUserCouponDO.getUserCouponId()).andStatusNotEqualTo(RealOrderStatus.Status.HAS_CLOSE.status);
                    List<OrderDO> orderDOS = orderDOMapper.selectByExample(orderDOExample);
                    String orderSn = "";
                    String productName;
                    if (CollUtil.isNotEmpty(orderDOS)) {
                        orderSn = orderDOS.get(0).getOrderSn();
                    }
                    //指定商品免费|现金兑换券查询商品信息
                    ProductDO productDO = productDOMapper.selectByPrimaryKey(exportUserCouponDO.getProductId());
                    productName = productDO.getProductName();

                    List<String> data = CollUtil.newLinkedList(
                            exportUserCouponDO.getCustNo() + "",
                            exportUserCouponDO.getBatchNo() + "",
                            exportUserCouponDO.getCouponTypeValue(),
                            exportUserCouponDO.getUserCouponId() + "",
                            exportUserCouponDO.getCouponName(),
                            StringUtil.formateAmt(exportUserCouponDO.getCouponAmount()),
                            DateUtil.format(exportUserCouponDO.getSendTime(), DatePattern.NORM_DATETIME_PATTERN),
                            exportUserCouponDO.getStatusValue(),
                            DateUtil.format(exportUserCouponDO.getUpdateTime(), DatePattern.NORM_DATETIME_PATTERN),
                            orderSn,
                            exportUserCouponDO.getProductId() + "",
                            productName
                    );
                    datas.add(data);
                }
        );
        exportDataBO.setFileName(Constant.COUPON_FILE_NAME + exportUserCouponDOList.get(0).getBatchNo());
        exportDataBO.setData(datas);
        return exportDataBO;
    }
    private ExportDataBO<ExportApplyRefundResDTO> buildRefundData(List<OrderRefundExportDO> exportDOList,OrderRefundExportReqDTO orderRefundExportReqDTO){
        ExportDataBO<ExportApplyRefundResDTO> exportDataBO = new ExportDataBO<ExportApplyRefundResDTO>(Constant.ORDER_REFUND_FILE_NAME,Constant.ORDER_REFUND_SHEET_NAME,Constant.COUPON_FILE_SUFFIX,this.getRefundFileHeader());
        List<List<String>> datas = CollUtil.newLinkedList();
        String dateFormat = "yyyy/MM/dd HH:mm:ss";
        for (OrderRefundExportDO exportDO : exportDOList){
            List<String> data = new ArrayList<>();
            ProductEnum productEnum =ProductEnum.getProductByType(exportDO.getProductType());
            data.add(productEnum.desc);
            data.add(StringUtil.objectToString(exportDO.getRefundSerial()));
            data.add(StringUtil.objectToString(exportDO.getCustNo()));
            data.add(StringUtil.objectToString(exportDO.getCustMobile()));
            data.add(DateUtil.format(exportDO.getCreateTime(),dateFormat));
            data.add(DateUtil.format(exportDO.getHandleFinishTime(),dateFormat));
            //0待审核1审核通过2退款成功3审核不通过
            com.qdbank.mall.constent.payment.RefundStatausEnum refundStatausEnum =com.qdbank.mall.constent.payment.RefundStatausEnum.getRefundStatausEnumByCode(exportDO.getRefundStatus());
            data.add(refundStatausEnum==null?"":refundStatausEnum.msg);
            data.add(StringUtil.formateAmt(exportDO.getRefundAmount()));
            data.add(StringUtil.objectToString(exportDO.getRefundIntegration()));
            //0仅退款(无需退货)1退货退款
            data.add("".equals(StringUtil.objectToString(exportDO.getRefundType()))?"":Constant.REFUND_TYPE.equals(exportDO.getRefundType())?"仅退款(无需退货)":Constant.REFUND_TYPE_GOOD.equals(exportDO.getRefundType())?"退货退款":"");
            data.add(StringUtil.objectToString(exportDO.getReason()));
            ProductCategoryID productCategoryID = new ProductCategoryID();
            productCategoryID.setCategoryId1(exportDO.getCategoryId1());
            productCategoryID.setCategoryId2(exportDO.getCategoryId2());
            productCategoryID.setCategoryId3(exportDO.getCategoryId3());
            productCategoryID.setCategoryId4(exportDO.getCategoryId4());
            String ctateGoryName = productService.productCategoryName(productCategoryID);
            data.add(ctateGoryName);
            data.add(StringUtil.objectToString(exportDO.getProductId()));
            data.add(StringUtil.objectToString(exportDO.getProductName()));
            data.add(StringUtil.objectToString(exportDO.getProductSpData()));
            data.add(StringUtil.objectToString(exportDO.getProductCount()));
            data.add(StringUtil.formateAmt(exportDO.getProductCash()));
            data.add(StringUtil.objectToString(exportDO.getProductIntegration()));
            data.add(StringUtil.objectToString(exportDO.getMerchantNo()));
            data.add(StringUtil.objectToString(exportDO.getMerchantName()));
            data.add(StringUtil.objectToString(exportDO.getOrderSn()));
            datas.add(data);
        }
        exportDataBO.setFileName(Constant.ORDER_REFUND_FILE_NAME+(DateUtil.format(orderRefundExportReqDTO.getStartTime(),DatePattern.NORM_DATE_PATTERN))+"-"+(DateUtil.format(orderRefundExportReqDTO.getEndTime(),DatePattern.NORM_DATE_PATTERN)));
        exportDataBO.setData(datas);
        return exportDataBO;
    }
    /**
     * 组装订单数据
     * @param orderDOList
     * @param orderExportReqDTO
     * @return
     */
    private ExportDataBO<ExportOrderResDTO> buildOrderData(List<OrderExportDO> orderDOList, OrderExportReqDTO orderExportReqDTO){
        List<List<String>> datas = CollUtil.newLinkedList();
        ExportDataBO<ExportOrderResDTO> exportDataBO = new ExportDataBO<>(Constant.ORDER_FILE_NAME,Constant.ORDER_SHEET_NAME,Constant.COUPON_FILE_SUFFIX,this.getOrderFileHeader());
        String dateFormat = "yyyy/MM/dd HH:mm:ss";
        for (OrderExportDO exportDO : orderDOList){
            List<String> data = new ArrayList<>();
            ProductEnum productEnum =ProductEnum.getProductByType(exportDO.getProductType());
            data.add(productEnum.desc);
            data.add(IdentificationEnum.getDesc(exportDO.getIdentification()));
            data.add(StringUtil.objectToString(exportDO.getOrderSn()));
            data.add(StringUtil.objectToString(exportDO.getCustNo()));
            data.add(StringUtil.objectToString(exportDO.getCustMobile()));
            data.add(DateUtil.format(exportDO.getCreateTime(),dateFormat));
            data.add(DateUtil.format(exportDO.getPaymentTime(),dateFormat));
            data.add(StringUtil.objectToString(exportDO.getReceiverPhone()));
            data.add(StringUtil.objectToString(exportDO.getReceiverName()));
            //收款地址
            StringBuilder sb = new StringBuilder();
            sb.append(StringUtil.objectToString(exportDO.getReceiverProvince()))
                    .append(StringUtil.objectToString(exportDO.getReceiverCity()))
                    .append(StringUtil.objectToString(exportDO.getReceiverRegion()))
                    .append(StringUtil.objectToString(exportDO.getReceiverDetailAddress()));
            data.add(sb.toString());

            if (productEnum==null){
                data.add("");
            }else {
                //如果是实物
                if (productEnum==ProductEnum.PAYMENT_IN_KIND){
                    RealOrderStatus.Status _status=RealOrderStatus.Status.getStatusByCode(exportDO.getStatus());
                    data.add(_status==null?"":_status.msg);
                    //如果是话费
                }else if (productEnum==ProductEnum.MOBILE_RECHARGE){
                    MobileReChargePayOrderStatus.Status _status=MobileReChargePayOrderStatus.Status.getStatusByCode(exportDO.getStatus());
                    data.add(_status==null?"":_status.msg);
                    //积分兑换券-商户不存在
                }else if(productEnum==ProductEnum.INTEGRAL){
                    IntegralOrderStatus.Status _status= IntegralOrderStatus.Status.getStatusByCode(exportDO.getStatus());
                    data.add(_status==null?"":_status.msg);
                }else if(productEnum==ProductEnum.OIL_CARD){
                    OilOrderStatus.Status _status= OilOrderStatus.Status.getStatusByCode(exportDO.getStatus());
                    data.add(_status==null?"":_status.msg);
                }else if(productEnum==ProductEnum.VIDEO){
                    VideoOrderStatus.Status _status= VideoOrderStatus.Status.getStatusByCode(exportDO.getStatus());
                    data.add(_status==null?"":_status.msg);
                }
            }
            BigDecimal payment = exportDO.getPayAmount();
            BigDecimal freightAmount = exportDO.getFreightAmount();
            if(payment==null){
                payment=new BigDecimal(0);
            }
            if(freightAmount==null){
                freightAmount=new BigDecimal(0);
            }
            data.add(StringUtil.formateAmt(payment));
            data.add(StringUtil.objectToString(exportDO.getDiscountAmount()));
            data.add(StringUtil.objectToString(exportDO.getOrderIntegration()));
            data.add(StringUtil.formateAmt(exportDO.getFreightAmount()));
            data.add(StringUtil.formateAmt(exportDO.getCouponAmount()));
            ProductCategoryID productCategoryID = new ProductCategoryID();
            productCategoryID.setCategoryId1(exportDO.getCategoryId1());
            productCategoryID.setCategoryId2(exportDO.getCategoryId2());
            productCategoryID.setCategoryId3(exportDO.getCategoryId3());
            productCategoryID.setCategoryId4(exportDO.getCategoryId4());
            String ctateGoryName = productService.productCategoryName(productCategoryID);
            data.add(ctateGoryName);
            data.add(StringUtil.objectToString(exportDO.getProductId()));
            data.add(StringUtil.objectToString(exportDO.getProductName()));
            data.add(StringUtil.objectToString(exportDO.getProductSpData()));
            data.add(StringUtil.objectToString(exportDO.getProductCount()));
            data.add(StringUtil.formateAmt(exportDO.getProductCash()));
            data.add(StringUtil.objectToString(exportDO.getProductIntegration()));
            data.add(StringUtil.objectToString(exportDO.getMerchantNo()));
            data.add(StringUtil.objectToString(exportDO.getMerchantName()));
            data.add(StringUtil.objectToString(exportDO.getRefundSerial()));
            datas.add(data);
        }
        exportDataBO.setFileName(Constant.ORDER_FILE_NAME+(DateUtil.format(orderExportReqDTO.getStartTime(), DatePattern.NORM_DATE_PATTERN))+"-"+(DateUtil.format(orderExportReqDTO.getEndTime(),DatePattern.NORM_DATE_PATTERN)));
        exportDataBO.setData(datas);
        return exportDataBO;
    }

    private  ExportDataBO<ExportCouponResDTO> buildIntegrationBatchData(List<ExportUserCouponDO> exportUserCouponDOList){
        List<List<String>> datas = CollUtil.newLinkedList();
        ExportDataBO<ExportCouponResDTO> exportDataBO = new ExportDataBO<ExportCouponResDTO>(Constant.COUPON_FILE_NAME, Constant.COUPON_FILE_NAME, Constant.COUPON_FILE_SUFFIX, getProductCouponFileHeader());
        exportUserCouponDOList.forEach(exportUserCouponDO -> {
            OrderDOExample orderDOExample = new OrderDOExample();
            orderDOExample.createCriteria().andUserCouponIdEqualTo(exportUserCouponDO.getUserCouponId()).andStatusNotEqualTo(RealOrderStatus.Status.HAS_CLOSE.status);
            List<OrderDO> orderDOS = orderDOMapper.selectByExample(orderDOExample);
            String orderSn = "";
            if (CollUtil.isNotEmpty(orderDOS)) {
                orderSn = orderDOS.get(0).getOrderSn();
            }

            CouponDO couponDO = couponDOMapper.selectByPrimaryKey(exportUserCouponDO.getRelCouponId());
            ProductEnum productTypeEnum = ProductEnum.getProductByType(couponDO.getProductType());

            //积分兑换批次券查询积分兑换券信息
            List<String> data = CollUtil.newLinkedList(
                    exportUserCouponDO.getCustNo() + "",
                    exportUserCouponDO.getBatchNo() + "",
                    exportUserCouponDO.getCouponTypeValue(),
                    exportUserCouponDO.getUserCouponId() + "",
                    exportUserCouponDO.getCouponName(),
                    StringUtil.formateAmt(exportUserCouponDO.getCouponAmount()),
                    DateUtil.format(exportUserCouponDO.getSendTime() == null ? exportUserCouponDO.getCreateTime() : exportUserCouponDO.getSendTime(), DatePattern.NORM_DATETIME_PATTERN),
                    exportUserCouponDO.getStatusValue(),
                    DateUtil.format(exportUserCouponDO.getUpdateTime(), DatePattern.NORM_DATETIME_PATTERN),
                    orderSn,
                    couponDO.getProductId() + "",
                    productTypeEnum.desc
            );
            datas.add(data);
        }
        );
        exportDataBO.setFileName(Constant.COUPON_FILE_NAME + exportUserCouponDOList.get(0).getBatchNo());
        exportDataBO.setData(datas);
        return exportDataBO;
    }
    private ExportDataBO<ExportCouponResDTO> buildCashCouponData(List<ExportUserCouponDO> exportUserCouponDOList){
        List<List<String>> datas = CollUtil.newLinkedList();
        ExportDataBO<ExportCouponResDTO> exportDataBO = new ExportDataBO<>(Constant.COUPON_FILE_NAME, Constant.COUPON_FILE_NAME, Constant.COUPON_FILE_SUFFIX, this.getCashCouponFileHeader());
        if (CollectionUtil.isEmpty(exportUserCouponDOList)) {
            datas.add(CollUtil.newLinkedList());
        } else {
            exportUserCouponDOList.forEach(exportUserCouponDO -> {
                        OrderDOExample orderDOExample = new OrderDOExample();
                        orderDOExample.createCriteria().andUserCouponIdEqualTo(exportUserCouponDO.getUserCouponId()).andStatusNotEqualTo(RealOrderStatus.Status.HAS_CLOSE.status);
                        List<OrderDO> orderDOS = orderDOMapper.selectByExample(orderDOExample);
                        String orderSn = "";
                        if (CollUtil.isNotEmpty(orderDOS)) {
                            orderSn = orderDOS.get(0).getOrderSn();
                        }
                        PrefectureDO prefectureDO = prefectureDOMapper.selectByPrimaryKey(exportUserCouponDO.getPrefectureId());
                        //特殊处理发放时间,行发取sendTime,自行兑换取createTime
                        if (isBeidouDesignatedPrefectureCoupon(exportUserCouponDO.getCouponType(), exportUserCouponDO.getSubActivityId())) {
                            exportUserCouponDO.setSendTime(exportUserCouponDO.getCreateTime());
                        }
                        List<String> data = CollUtil.newLinkedList(
                                exportUserCouponDO.getCustNo() + "",
                                exportUserCouponDO.getBatchNo() + "",
                                exportUserCouponDO.getCouponTypeValue(),
                                exportUserCouponDO.getUserCouponId() + "",
                                exportUserCouponDO.getCouponName(),
                                StringUtil.formateAmt(exportUserCouponDO.getCouponAmount()),
                                DateUtil.format(exportUserCouponDO.getSendTime(), DatePattern.NORM_DATETIME_PATTERN),
                                exportUserCouponDO.getStatusValue(),
                                DateUtil.format(exportUserCouponDO.getUpdateTime(), DatePattern.NORM_DATETIME_PATTERN),
                                orderSn,
                                prefectureDO.getId() + "",
                                prefectureDO.getPrefectureName()
                        );
                        datas.add(data);
                    }
            );
            exportDataBO.setFileName(Constant.COUPON_FILE_NAME + exportUserCouponDOList.get(0).getBatchNo());
        }
        exportDataBO.setData(datas);
        return exportDataBO;
    }
    private List<String> getOrderFileHeader(){
        List<String> rowHead = CollUtil.newLinkedList("商品类型", "商品标识","订单编号", "客户号", "客户手机",  "订单创建时间","支付完成时间",
                "收货人电话","收货人姓名","收货地址","订单状态","订单实付款折算金额（元）","订单优惠金额","订单积分","运费（元）","优惠券面值（元）","商品类目","商品编号","商品名称",
                "商品规格","商品数量","商品售价现金金额（元）","商品售价积分量","商户编号","商户名称","关联退款流水号");
        return rowHead;
    }
    private List<String> getRefundFileHeader(){
        List<String> rowHead = CollUtil.newLinkedList("商品类型", "退款流水号", "客户号", "客户手机",  "退款订单创建时间","退款完成时间",
                "退款状态","退款折算金额（元）","退款积分","退款类型","退款原因","商品类目","商品编号","商品名称","商品规格","商品数量","商品售价现金金额（元）",
                "商品售价积分量","商户编号","商户名称","关联订单编号");
        return rowHead;
    }

    public List<String> getProductCouponFileHeader(){
        List<String> rowHead = CollUtil.newLinkedList("通联客户号", "批次号", "优惠券类型", "券编号",  "券名称","券面值","发放成功时间","当前状态","状态更新时间","关联订单号","关联商品编号","关联商品名称");
        return rowHead;
    }

    public List<String> getCashCouponFileHeader(){
        List<String> rowHead = CollUtil.newLinkedList("通联客户号", "批次号", "优惠券类型", "券编号",  "券名称","券面值","发放成功时间","当前状态","状态更新时间","关联订单号","指定专区编号","指定专区名称");
        return rowHead;
    }
    /**
     * 生成excle
     * @param exportDataBO
     * @param fileName
     * @param <T>
     */
    private   <T> void generateExcle (ExportDataBO<T> exportDataBO ,String fileName) {
        long startTime = System.currentTimeMillis();
        try {
            deleteLocalFile(fileName);
            ExcelWriter writer = ExcelUtil.getWriter(fileName);
            writer.renameSheet(exportDataBO.getSheetName());
            writer.writeHeadRow(exportDataBO.getHeards());
            writer.write(exportDataBO.getData(), true);
            writer.setOnlyAlias(true);
            writer.close();
            long endTime = System.currentTimeMillis();
            log.info("写入记录耗时：{} 秒",(endTime - startTime) / 1000);
        } catch (Exception e) {

            deleteLocalFile(fileName);
            log.error("######导出  excel异常  ：{}", e);
            throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
        }

    }

    /**
     * 上传到文件服务器
     * @param filePath
     * @return
     */
    private String  uploadFileToFastDfs(String filePath,String originName){

        try {

            File file = new File(filePath);
           return minioService.uploadFileByInputstream(file,originName);
        }catch (Exception e){
            log.error("上传文件异常：{}",e);
        }finally {
        }
        throw new ApiException(ResultCode.UPLOAD_FILE_EXCEPTION);
    }

    /**
     * 更新下载列表
     * @param downloadId
     * @param merchantNo
     * @param storePath
     */
    private void updateFilePath(Long downloadId,Long merchantNo,String storePath){
        String [] uploadReturn = new String[3];
        uploadReturn[0] = storePath;
        uploadReturn[1] = storePath;
        uploadReturn[2] = minioUrl + uploadReturn[1];
        DownloadDO downloadDO = new DownloadDO();
        downloadDO.setId(downloadId);
        downloadDO.setUpdateTime(new Date());
        downloadDO.setFileGroup(uploadReturn[0]);
        downloadDO.setFilePath(uploadReturn[1]);
        downloadDO.setFileUrl(uploadReturn[2]);
        downloadDO.setMerchantNo(merchantNo);
        downloadDO.setStatus(1L);
        downloadDOMapper.updateByPrimaryKeySelective(downloadDO);
    }

    /**
     * 删除本地文件
     * @param fileName
     */
    private void deleteLocalFile(String fileName){
        if(FileUtil.exist(fileName)){//如果本地文件存在，则先删除再重新上传
            log.info("delete file:{} result:{}",fileName,FileUtil.del(fileName));
        }
    }
    private void updateDownloadStatus(Long downloadId){
        DownloadDO downloadDO = new DownloadDO();
        downloadDO.setId(downloadId);
        downloadDO.setStatus(3L);
        downloadDO.setUpdateTime(new Date());
        downloadDOMapper.updateByPrimaryKeySelective(downloadDO);
    }

    /**
     * 判断是否是指定专区现金优惠券(发放方式为北斗)
     *
     * @param couponType    优惠券类型
     * @param subActivityId 子活动id
     * @return true:是 false:否
     */
    private boolean isBeidouDesignatedPrefectureCoupon(long couponType, String subActivityId) {
        return CouponTypeEnum.PREFECTURE_CASH_COUPON.getCode().equals(couponType)
                && org.apache.commons.lang3.StringUtils.isNotEmpty(subActivityId);
    }
}
