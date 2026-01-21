package com.qdbank.mall.financial.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdbank.mall.api.TypeEnum;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.domain.ExportDataBO;
import com.qdbank.mall.financial.FinancialService;
import com.qdbank.mall.mapper.orderrefundreport.OrderRefundreportDOMapper;
import com.qdbank.mall.mapper.orderreport.OrderReportDOMapper;
import com.qdbank.mall.mapper.trade.TradeDetailDOMapper;
import com.qdbank.mall.merchant.MerchantService;
import com.qdbank.mall.model.orderreport.OrderExportDetailDO;
import com.qdbank.mall.model.orderreport.OrderReportExportDO;
import com.qdbank.mall.model.trade.TradeDetailDO;
import com.qdbank.mall.model.trade.TradeTotalDO;
import com.qdbank.mall.request.financial.FinancialReqDTO;
import com.qdbank.mall.response.financial.OrderDetailResDTO;
import com.qdbank.mall.response.financial.OrderTotalResDTO;
import com.qdbank.mall.response.merchant.MerchantResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.util.ExcelExportUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName TradeDetailFinancialServiceImpl
 * @Description 交易明细导出
 * @Author ningyuehuai
 * @Date 2021/3/19 11:24
 * @Version 1.0
 **/
@Service
public class TradeDetailFinancialServiceImpl extends BaseServiceImpl implements FinancialService {
    @Autowired
    private OrderReportDOMapper orderReportDOMapper;
    @Autowired
    private OrderRefundreportDOMapper refundreportDOMapper;
    @Autowired
    private TradeDetailDOMapper tradeDetailDOMapper;
    @Autowired
    private MerchantService merchantService;
    @Override
    public void export(HttpServletResponse response, FinancialReqDTO financialReqDTO) {
        financialReqDTO.setPageSize(null);
        financialReqDTO.setPageNum(null);
        Date startDate = financialReqDTO.getStartDate();

        List<String> rowHead = CollUtil.newLinkedList("订单类型", "订单号", "流水号", "支付或退款时间",  "商品实际售价(折算价)","支付或退还积分","积分是否与商户结算","支付或退还优惠券类型","支付或退还优惠券面值","优惠券批次号","支付或退还现金","运费","需结算现金","商户名称","商户号","商品编码(SPU)","商品规格编码(SKU)","优惠金额");
        ExportDataBO<OrderTotalResDTO> exportDataBO = new ExportDataBO<>(Constant.ORDER_DETAIL_FILE_NAME,Constant.ORDER_DETAIL_SHEET_NAME,Constant.COUPON_FILE_SUFFIX,rowHead);
        exportDataBO.setFileName(Constant.ORDER_DETAIL_FILE_NAME+(DateUtil.format(startDate, DatePattern.NORM_DATE_PATTERN))+"-"+(DateUtil.format(financialReqDTO.getEndDate(), DatePattern.NORM_DATE_PATTERN)));
        ExcelWriter writer = exportDataBO.getWriter();
        writer.merge(rowHead.size() - 1,Constant.ORDER_DETAIL_FILE_NAME+(DateUtil.format(startDate, "yyyy/MM/dd"))+"-"+(DateUtil.format(financialReqDTO.getEndDate(), "yyyy/MM/dd")),true);
        writeData(exportDetailData(financialReqDTO).getList(), exportDataBO,response);
    }

    @Override
    public void insertTradeRecord(Date date) {
        tradeDetailDOMapper.deleteByCreateTime(date);
        FinancialReqDTO financialReqDTO = new FinancialReqDTO();
        financialReqDTO.setStartDate(DateUtil.offsetDay(date,- 1));
        financialReqDTO.setEndDate(DateUtil.offsetDay(date,- 1));
        FinancialService.changeDate(financialReqDTO);
        List<OrderDetailResDTO> detailResDTOS = this.buildDetailData(financialReqDTO);
        if(CollUtil.isNotEmpty(detailResDTOS)){
            detailResDTOS.stream().forEach(orderDetailResDTO -> {
                TradeDetailDO tradeDetailDO = new TradeDetailDO();
                BeanUtils.copyProperties(orderDetailResDTO,tradeDetailDO);
                tradeDetailDO.setId(super.generateId());
                tradeDetailDO.setCreateTime(date);
                tradeDetailDO.setDiscountAmount(orderDetailResDTO.getDiscountAmount() == null ? BigDecimal.ZERO : new BigDecimal(orderDetailResDTO.getDiscountAmount()));
                if(StringUtils.isNotBlank(tradeDetailDO.getMerchantNo())){
                    if(tradeDetailDO.getMerchantNo().equals("0")){
                        tradeDetailDO.setMerchantName("青岛银行信用卡商城");
                    }else{
                        MerchantResDTO merchantResDTO = merchantService.getItem(Long.valueOf(tradeDetailDO.getMerchantNo()));
                        if(merchantResDTO != null){
                            tradeDetailDO.setMerchantName(merchantResDTO.getMerchantName());
                        }
                    }
                }
                tradeDetailDOMapper.insert(tradeDetailDO);
            });
        }
    }

    @Override
    public PageInfo<OrderDetailResDTO> getTradeList(FinancialReqDTO financialReqDTO) {
        return this.exportDetailData(financialReqDTO);
    }

    private List<OrderDetailResDTO> buildDetailData(FinancialReqDTO financialReqDTO){
        OrderReportExportDO orderReportExportDO = new OrderReportExportDO();
        BeanUtils.copyProperties(financialReqDTO,orderReportExportDO);
        orderReportExportDO.setStartDate(orderReportExportDO.getStartDate());
        List<OrderExportDetailDO> orderExportDetailDOS = orderReportDOMapper.selectTradeDetail(orderReportExportDO);
        List<OrderExportDetailDO> refundDetailDOS = refundreportDOMapper.selectTradeDetail(orderReportExportDO);
        List<OrderExportDetailDO> dataList = new ArrayList<>();
        if(CollUtil.isNotEmpty(orderExportDetailDOS) && CollUtil.isNotEmpty(refundDetailDOS)){
            orderExportDetailDOS.addAll(refundDetailDOS);
            dataList = orderExportDetailDOS;
        }
        if(CollUtil.isNotEmpty(orderExportDetailDOS) && CollUtil.isEmpty(refundDetailDOS)){
            dataList = orderExportDetailDOS;
        }
        if(CollUtil.isEmpty(orderExportDetailDOS) && CollUtil.isNotEmpty(refundDetailDOS)){
            dataList = refundDetailDOS;
        }
        List<OrderDetailResDTO> orderDetailResDTOS = new ArrayList<>();
        for(OrderExportDetailDO orderExportDetailDO : dataList){
            OrderDetailResDTO orderDetailResDTO = new OrderDetailResDTO();
            orderDetailResDTO.setBatchNo(orderExportDetailDO.getBatchNo());
            orderDetailResDTO.setCouponType(orderExportDetailDO.getCouponType()+"");
            orderDetailResDTO.setFreightAmount(orderExportDetailDO.getFreightAmount()+"");
            orderDetailResDTO.setIntegrationCouponAmount(orderExportDetailDO.getIntegrationCouponAmount() == null ? "0" :orderExportDetailDO.getIntegrationCouponAmount()+ "");
            orderDetailResDTO.setIntegrationPayFlag(orderExportDetailDO.getIntegrationPayFlag());
            orderDetailResDTO.setMerchantName(orderExportDetailDO.getMerchantName());
            orderDetailResDTO.setMerchantNo(orderExportDetailDO.getMerchantNo()+"");
            orderDetailResDTO.setOrderCash(orderExportDetailDO.getOrderCash()+"");
            orderDetailResDTO.setOrderId(orderExportDetailDO.getOrderId()+"");
            orderDetailResDTO.setPaymentTime(DateUtil.format(orderExportDetailDO.getPaymentTime(), DatePattern.NORM_DATETIME_PATTERN));
            orderDetailResDTO.setProductCouponAmount(orderExportDetailDO.getProductCouponAmount() == null ? "0": orderExportDetailDO.getProductCouponAmount()+"");
            orderDetailResDTO.setOrderIntegration(orderExportDetailDO.getOrderIntegration()+"");
            orderDetailResDTO.setOrderSn(orderExportDetailDO.getOrderSn());
            orderDetailResDTO.setProductSkuId(orderExportDetailDO.getProductSkuId() == null ? "": orderExportDetailDO.getProductSkuId()+"");
            orderDetailResDTO.setProductId(orderExportDetailDO.getProductId()+"");
            orderDetailResDTO.setOrderType(orderExportDetailDO.getOrderType());
            orderDetailResDTO.setProductPrice(orderExportDetailDO.getProductPrice()+"");
            orderDetailResDTO.setCashFreightAmount(orderExportDetailDO.getCashAndFreightAmount()+"");
            orderDetailResDTO.setDiscountAmount(orderExportDetailDO.getDiscountAmount()+"");
            orderDetailResDTOS.add(orderDetailResDTO);
        }
        return orderDetailResDTOS;
    }
    private void writeData(List<OrderDetailResDTO> orderDetailResDTOS,ExportDataBO<OrderTotalResDTO> exportDataBO,HttpServletResponse response){
        List<List<String>> datas = CollUtil.newLinkedList();
        orderDetailResDTOS.forEach(o -> {
            List<String> data = CollUtil.newLinkedList(o.getOrderType(),o.getOrderSn(),o.getOrderId(),o.getPaymentTime(),o.getProductPrice(),o.getOrderIntegration(),o.getIntegrationPayFlag(),
                    o.getCouponTypeDesc(),
                    o.getIntegrationCouponAmount(),o.getBatchNo(),o.getOrderCash(),o.getFreightAmount(),o.getCashFreightAmount(),o.getMerchantName(),o.getMerchantNo(),o.getProductId(),o.getProductSkuId(),o.getDiscountAmount());
            datas.add(data);
        });
        exportDataBO.setData(datas);
        ExcelExportUtil.exportExcelByHuTools(response,exportDataBO);
    }
    private PageInfo<OrderDetailResDTO> exportDetailData(FinancialReqDTO financialReqDTO){
        if(financialReqDTO.getPageNum() != null && financialReqDTO.getPageSize() != null){
            PageHelper.startPage(financialReqDTO.getPageNum(),financialReqDTO.getPageSize());
        }
        FinancialService.changeDate(financialReqDTO);
        List<OrderDetailResDTO> orderDetailResDTOS = new ArrayList<>();
        OrderReportExportDO orderReportExportDO = new OrderReportExportDO();
        BeanUtils.copyProperties(financialReqDTO,orderReportExportDO);
        List<TradeDetailDO> detailDOS = tradeDetailDOMapper.selectTradeDetailResult(orderReportExportDO);
        if(CollUtil.isNotEmpty(detailDOS)){
            detailDOS.stream().forEach(detailDO->{
                OrderDetailResDTO orderDetailResDTO = new OrderDetailResDTO();
                BeanUtils.copyProperties(detailDO,orderDetailResDTO);
                orderDetailResDTO.setCouponTypeDesc(getCouponType(detailDO));
                orderDetailResDTO.setDiscountAmount(detailDO.getDiscountAmount()+"");
                orderDetailResDTOS.add(orderDetailResDTO);
            });
        }
        return super.getPageInfo(detailDOS,orderDetailResDTOS);
    }


    private String getCouponType(TradeDetailDO tradeTotal){
        if("0".equals(tradeTotal.getCouponType()) && !org.springframework.util.StringUtils.hasText(tradeTotal.getBatchNo())) return "积分兑换券";
        if("0".equals(tradeTotal.getCouponType()) && org.springframework.util.StringUtils.hasText(tradeTotal.getBatchNo())) return "行发积分兑换券";
        if("1".equals(tradeTotal.getCouponType()) && org.springframework.util.StringUtils.hasText(tradeTotal.getBatchNo())) return "指定商品免费兑换券";
        if("2".equals(tradeTotal.getCouponType()) && org.springframework.util.StringUtils.hasText(tradeTotal.getBatchNo())) return "指定商品现金优惠券";
        if("4".equals(tradeTotal.getCouponType()) && StringUtils.isNotBlank(tradeTotal.getBatchNo())) return "指定专区现金优惠券";
        if("5".equals(tradeTotal.getCouponType()) && StringUtils.isNotBlank(tradeTotal.getBatchNo())) return "全场通用现金优惠券";
        return "";
    }
}
