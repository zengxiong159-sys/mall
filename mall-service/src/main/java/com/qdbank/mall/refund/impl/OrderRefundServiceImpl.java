package com.qdbank.mall.refund.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.feign.MallFeignService;
import com.qdbank.mall.replace.ReplaceService;
import com.qdbank.mall.request.CommonStringIDReqDTO;
import com.qdbank.mall.request.order.OrderIDReqDTO;
import com.qdbank.mall.response.order.OrderResDTO;
import org.springframework.beans.factory.annotation.Value;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.domain.ExportDataBO;
import com.qdbank.mall.download.AsyncDownloadService;
import com.qdbank.mall.enums.PayTypeEnum;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.download.DownloadDOMapper;
import com.qdbank.mall.mapper.orderrefund.OrderRefundDOMapper;
import com.qdbank.mall.mapper.skustock.SkustockDOMapper;
import com.qdbank.mall.model.download.DownloadDO;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.orderrefund.*;
import com.qdbank.mall.model.skustock.SkustockDO;
import com.qdbank.mall.model.trade.TradeFileDataDO;
import com.qdbank.mall.product.ProductService;
import com.qdbank.mall.refund.OrderRefundService;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.refund.OrderRefundExportReqDTO;
import com.qdbank.mall.request.refund.OrderRefundHandleReqDTO;
import com.qdbank.mall.request.refund.OrderRefundLikeQueryReqDTO;
import com.qdbank.mall.response.coupon.CouponResDTO;
import com.qdbank.mall.response.order.OrderBaseInfoResDTO;
import com.qdbank.mall.response.product.ProductCategoryID;
import com.qdbank.mall.response.product.ProductResDTO;
import com.qdbank.mall.response.refund.ExportApplyRefundResDTO;
import com.qdbank.mall.response.refund.OrderRefundDetailResDTO;
import com.qdbank.mall.response.refund.OrderRefundResDTO;
import com.qdbank.mall.response.skustock.SkustockResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.third.MallFrontService;
import com.qdbank.mall.time.TimeService;
import com.qdbank.mall.util.ExcelExportUtil;
import com.qdbank.mall.util.StringUtil;
import com.qdbank.mall.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

/**
 * OrderRefundServiceImpl
 *
 * @author shaoshihang
 * @date 2021/3/10 10:40
 * @since 1.0.0
 */
@Service
@Slf4j
@RefreshScope
public class OrderRefundServiceImpl extends BaseServiceImpl implements OrderRefundService {
    @Resource
    private OrderRefundDOMapper orderRefundDOMapper;
    @Autowired
    private ProductService productService;
    @Value(value = "${http.frontapplyrefundok:ok}")
    private String frontapplyrefundok ;
    @Value(value = "${http.frontapplyrefundno:no}")
    private String frontapplyrefundno ;
    @Autowired
    private TimeService timeService;
    @Autowired
    private SkustockDOMapper skustockDOMapper;
    @Autowired
    private MallFeignService mallFeignService;
    @Autowired
    private AsyncDownloadService asyncDownloadService;
    @Autowired
    private ReplaceService replaceService;

    @Override
    public PageInfo<OrderRefundResDTO> list(OrderRefundLikeQueryReqDTO orderRefundLikeQueryReqDTO) {
        if(orderRefundLikeQueryReqDTO.getPageNum() != null && orderRefundLikeQueryReqDTO.getPageSize() != null){
            PageHelper.startPage(orderRefundLikeQueryReqDTO.getPageNum(),orderRefundLikeQueryReqDTO.getPageSize());
        }
        List<OrderRefundResDTO> refundResDTOS = new ArrayList<>();
        OrderRefundLikeQueryDO orderRefundDO = new OrderRefundLikeQueryDO();
        BeanUtils.copyProperties(orderRefundLikeQueryReqDTO,orderRefundDO);
        timeService.setTime(orderRefundDO);
        //TODO mapstruct 处理
        if(StringUtils.hasText(orderRefundLikeQueryReqDTO.getRefundSerial())){
            orderRefundDO.setRefundSerial(orderRefundLikeQueryReqDTO.getRefundSerial());
        }
        List<OrderRefundListDO> refundListDOS = null;
        if("1".equals(orderRefundLikeQueryReqDTO.getErrorFlag())){
            refundListDOS=orderRefundDOMapper.selectErrorRefundList(orderRefundDO);
        }else{
            refundListDOS=orderRefundDOMapper.selectList(orderRefundDO);
        }

        for (OrderRefundListDO refundListDO : refundListDOS){
            OrderRefundResDTO refundResDTO = new OrderRefundResDTO();
            BeanUtils.copyProperties(refundListDO,refundResDTO);
            refundResDTO.setProductSpData(refundListDO.getSkustockDO() == null?"":StringUtil.objectToString(refundListDO.getSkustockDO().getProductSpData()));
            OrderDO orderDO =refundListDO.getOrderDO();
            refundResDTO.setPayAmount(orderDO.getPayAmount());
            refundResDTO.setRefundSerialNo(refundListDO.getRefundSerial()+"");
            refundResDTO.setOrderId(orderDO.getOrderId());
            refundResDTO.setMerchantName(orderDO.getMerchantName());
            refundResDTO.setStatus(orderDO.getStatus());
            //处理退款订单上传凭证图片
            List<OrderRefundProofPicsDO> orderRefundProofPicsDOList = orderRefundDOMapper.selectProofPicsByRefundSn(refundResDTO.getRefundSerialNo());
            if(CollectionUtil.isNotEmpty(orderRefundProofPicsDOList)) {
                refundResDTO.setProofPics(JSON.toJSONString(orderRefundProofPicsDOList));
            }

            //列表展示订单的商品规格图片
            SkustockDO skustockDO = skustockDOMapper.selectByPrimaryKey(refundResDTO.getProductSkuId());
            if(skustockDO != null && org.apache.commons.lang3.StringUtils.isNotBlank(skustockDO.getSkuPicUrl())){
                refundResDTO.setProductPic(orderRefundLikeQueryReqDTO.getMerchantNo() != null ? skustockDO.getSkuPicUrl() : replaceService.replaceUrl(skustockDO.getSkuPicUrl()));
            }
            refundResDTOS.add(refundResDTO);
        }
        return super.getPageInfo(refundListDOS,refundResDTOS);
    }

    @Override
    public CommonResult excelExport(HttpServletResponse response, OrderRefundExportReqDTO orderRefundExportReqDTO) {
        //判断是否大于三个月
        if (TimeUtil.judgeThreeMonths(orderRefundExportReqDTO.getStartTime(),orderRefundExportReqDTO.getEndTime())){
            throw new ApiException(ResultCode.THREE_MONTHS_EXCEL_EXPORT);
        }
        OrderRefundLikeQueryDO orderRefundDO = new OrderRefundLikeQueryDO();
        BeanUtils.copyProperties(orderRefundExportReqDTO,orderRefundDO);
        timeService.setTime(orderRefundDO);
        List<OrderRefundExportDO> exportDOList = orderRefundDOMapper.selectExport(orderRefundDO);
        if(CollUtil.isEmpty(exportDOList)) throw new ApiException(ResultCode.DATA_EMPTY);
        String fileName = Constant.ORDER_REFUND_FILE_NAME+(DateUtil.format(orderRefundExportReqDTO.getStartTime(), DatePattern.NORM_DATE_PATTERN))+"-"+(DateUtil.format(orderRefundExportReqDTO.getEndTime(),DatePattern.NORM_DATE_PATTERN));
        asyncDownloadService.checkFileExist(fileName,orderRefundExportReqDTO.getCreatedBy());
        Long downloadId = super.generateId();
        asyncDownloadService.insertDownload(fileName,orderRefundExportReqDTO.getMerchantNo(),orderRefundExportReqDTO.getCreatedBy(),1L,downloadId);
        orderRefundExportReqDTO.setDownloadId(downloadId);
        asyncDownloadService.asyncDownloadRefund(exportDOList,orderRefundExportReqDTO);
        return CommonResult.success(null);
    }

    @Override
    public OrderRefundDetailResDTO getItem(CommonIDReqDTO commonIDReqDTO) {
        OrderRefundDetailResDTO refundDetailResDTO = new OrderRefundDetailResDTO();
        OrderRefundDetailDO detailDO = orderRefundDOMapper.queryDetail(commonIDReqDTO.getId());
        //订单信息
        OrderBaseInfoResDTO resDTO = new OrderBaseInfoResDTO();
        BeanUtils.copyProperties(detailDO,resDTO);
        //规格信息
        ProductResDTO productResDTO = new ProductResDTO();
        List<SkustockResDTO> skustocks = new ArrayList<>();
        SkustockResDTO skustockResDTO = new SkustockResDTO();
        skustockResDTO.setProductSpData(detailDO.getProductSpData());
        skustockResDTO.setSkuPicUrl(detailDO.getSkuPicUrl());
        skustocks.add(skustockResDTO);
        productResDTO.setSkustocks(skustocks);
        //优惠券信息
        CouponResDTO couponResDTO = new CouponResDTO();
        BeanUtils.copyProperties(detailDO,couponResDTO);

        refundDetailResDTO.setIntegralCouponInfo(couponResDTO);
        refundDetailResDTO.setProductInfo(productResDTO);
        refundDetailResDTO.setBaseInfo(resDTO);
        return refundDetailResDTO;
    }

    @Override
    public int orderHandle(OrderRefundHandleReqDTO orderRefundHandleReqDTO) {

        OrderRefundDO orderRefundDO = new OrderRefundDO();
        orderRefundDO.setRefundSerial(orderRefundHandleReqDTO.getRefundSerialNo()+"");
        //查询订单状态，如果是驳回,则订单状态不变仍然是代发货，退款状态改为审核不通过
        OrderDO orderDO = orderRefundDOMapper.queryOrderStatus(orderRefundHandleReqDTO.getRefundSerialNo());
        if (orderDO == null){
            log.error("退款流水号："+orderRefundHandleReqDTO.getRefundSerialNo()+",订单无数据");
            throw new ApiException(ResultCode.ORDER_REFUND_NULL_EXCEPTION);
        }
        /**
         * 区分驳回或退款：0驳回，1退款
         */
        String orderSn = orderDO.getOrderSn();
        Map params = new HashMap();
        params.put("orderSn",orderSn);
        log.info("订单编号：{}",orderDO.getOrderSn());
        CommonResult<OrderResDTO> response = null;
        OrderIDReqDTO orderIDReqDTO = new OrderIDReqDTO();
        orderIDReqDTO.setOrderSn(orderSn);
        Long identification = orderRefundHandleReqDTO.getIdentification();
        if(1L == identification){
            response = mallFeignService.confirmOrderReceive(orderIDReqDTO);
        }else if(0L == identification){
             response = mallFeignService.applyRefund(orderIDReqDTO);
        }
        log.info("订单编号："+orderDO.getOrderSn()+"，调用C端返回值：{}",JSON.toJSONString(response));
        return 1;
    }


    private List<String> getFileHeader(){
        List<String> rowHead = CollUtil.newLinkedList("商品类型", "退款流水号", "客户号", "客户手机",  "退款订单创建时间","退款完成时间",
                "退款状态","退款折算金额（元）","退款积分","退款类型","退款原因","商品类目","商品编号","商品名称","商品规格","商品数量","商品售价现金金额（元）",
                "商品售价积分量","商户编号","商户名称","关联订单编号");
        return rowHead;
    }

    @Override
    public List<TradeFileDataDO> selectHandFinishTime(Date date) {
        Date start = TimeUtil.dateStartChange(DateUtil.offsetDay(date,-2));
        Date end = TimeUtil.dateStartChange(DateUtil.offsetDay(date,-1));
        OrderRefundDOExample orderRefundDOExample = new OrderRefundDOExample();
        OrderRefundDOExample.Criteria criteria = orderRefundDOExample.createCriteria();
        criteria.andHandleFinishTimeGreaterThanOrEqualTo(start);
        criteria.andHandleFinishTimeLessThan(end);
        List<OrderRefundDO> list = orderRefundDOMapper.selectByHandleFinishTime(orderRefundDOExample);
        List<TradeFileDataDO> tradeFileDataDOS = new ArrayList<>();
        for(OrderRefundDO orderRefundDO : list){
            TradeFileDataDO tradeFileDataDO = new TradeFileDataDO();
            tradeFileDataDO.setAdvicePrice(BigDecimal.ZERO);
            tradeFileDataDO.setCreateTime(date);
            tradeFileDataDO.setCustNo(orderRefundDO.getCustNo()+"");
            tradeFileDataDO.setExpendAmt(orderRefundDO.getRefundCash());
            tradeFileDataDO.setExpendPoint(orderRefundDO.getRefundIntegration()+"");
            tradeFileDataDO.setItemId(orderRefundDO.getProductId()+"");
            tradeFileDataDO.setMerCode(orderRefundDO.getMerchantNo()+"");
            tradeFileDataDO.setMerName(orderRefundDO.getMerchantName());
            //转换成double再做除非，否则精度丢失
            BigDecimal orderAmt = (orderRefundDO.getRefundCash() == null ? new BigDecimal(orderRefundDO.getOrderIntegration().doubleValue() / 100+"") : orderRefundDO.getRefundCash().add(new BigDecimal(orderRefundDO.getOrderIntegration().doubleValue()/100+"")));
            tradeFileDataDO.setOrderAmt(orderAmt);
            tradeFileDataDO.setOrderSn(orderRefundDO.getOrderSn()+"");
            tradeFileDataDO.setOrderStatus("R");
            tradeFileDataDO.setTxDt(DateUtil.format(DateUtil.offsetDay(date,-1),DatePattern.PURE_DATE_PATTERN));
            tradeFileDataDO.setTxnDate(DateUtil.format(orderRefundDO.getHandleFinishTime(),DatePattern.PURE_DATE_PATTERN));
            tradeFileDataDO.setTxnTime(DateUtil.format(orderRefundDO.getHandleFinishTime(),DatePattern.PURE_TIME_PATTERN));
            tradeFileDataDO.setPaymentMethod(PayTypeEnum.getChangeType(orderRefundDO.getPayType()));
            tradeFileDataDO.setPointSetMark(orderRefundDO.getIntegrationPayFlag() == 0 ? "N" : "Y");
            tradeFileDataDO.setId(super.generateId());
            tradeFileDataDO.setOrg("000064500000");//青岛银行机构号固定值
            tradeFileDataDOS.add(tradeFileDataDO);
        }
        return tradeFileDataDOS;
    }

    @Override
    public void refundError(String refundSeriNo){
        log.info("退款流水调用C端请求值：{}",refundSeriNo);
        CommonStringIDReqDTO commonStringIDReqDTO = new CommonStringIDReqDTO();
        commonStringIDReqDTO.setId(refundSeriNo);
        CommonResult commonResult = mallFeignService.errorRefund(commonStringIDReqDTO);
        log.info("退款流水调用C端请求值：{} 退款结果:{}",refundSeriNo,JSON.toJSONString(commonResult));
    }

}
