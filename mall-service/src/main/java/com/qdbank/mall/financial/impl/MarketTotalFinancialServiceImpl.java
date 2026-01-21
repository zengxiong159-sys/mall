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
import com.qdbank.mall.mapper.trade.TradeMarketFeeDOMapper;
import com.qdbank.mall.merchant.MerchantService;
import com.qdbank.mall.model.orderreport.TradeMerchantInfoDO;
import com.qdbank.mall.model.orderreport.OrderReportExportDO;
import com.qdbank.mall.model.orderreport.ProductCouponDO;
import com.qdbank.mall.model.trade.TradeDetailDO;
import com.qdbank.mall.model.trade.TradeMarketFeeDO;
import com.qdbank.mall.request.financial.FinancialReqDTO;
import com.qdbank.mall.response.financial.OrderTotalResDTO;
import com.qdbank.mall.response.financial.ProductCouponResDTO;
import com.qdbank.mall.response.merchant.MerchantResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.util.ExcelExportUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName MarketTotalFinancialServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/22 15:57
 * @Version 1.0
 **/
@Service
public class MarketTotalFinancialServiceImpl extends BaseServiceImpl implements FinancialService<ProductCouponResDTO> {
    @Autowired
    private OrderReportDOMapper orderReportDOMapper;
    @Autowired
    private OrderRefundreportDOMapper orderRefundreportDOMapper;
    @Autowired
    private TradeMarketFeeDOMapper tradeMarketFeeDOMapper;
    @Autowired
    private MerchantService merchantService;
    @Override
    public void export(HttpServletResponse response, FinancialReqDTO financialReqDTO) {
        financialReqDTO.setPageSize(null);
        financialReqDTO.setPageNum(null);
        Date startDate = financialReqDTO.getStartDate();
        //FinancialService.changeDate(financialReqDTO);
        OrderReportExportDO orderReportExportDO = new OrderReportExportDO();
        BeanUtils.copyProperties(financialReqDTO,orderReportExportDO);
        orderReportExportDO.setStartDate(DateUtil.offsetDay(orderReportExportDO.getStartDate(),-1));
        List<String> rowHead = CollUtil.newLinkedList("日期", "商户编码", "商户名称","优惠券类型", "优惠券批次号",  "优惠券名称","优惠券面值(已使用)");
        ExportDataBO<OrderTotalResDTO> exportDataBO = new ExportDataBO<>(Constant.PRODUCT_TOTAL_FILE_NAME,Constant.PRODUCT_TOTAL_SHEET_NAME,Constant.COUPON_FILE_SUFFIX,rowHead);
        exportDataBO.setFileName(Constant.PRODUCT_TOTAL_FILE_NAME+(DateUtil.format(startDate, DatePattern.NORM_DATE_PATTERN))+"-"+(DateUtil.format(financialReqDTO.getEndDate(),DatePattern.NORM_DATE_PATTERN)));
        writeData(exportMarketData(financialReqDTO).getList(), exportDataBO,response, StringUtils.isNotBlank(financialReqDTO.getMerchantNo()));
    }

    @Override
    public PageInfo<ProductCouponResDTO> getTradeList(FinancialReqDTO financialReqDTO) {
        return this.exportMarketData(financialReqDTO);
    }

    @Override
    public void insertTradeRecord(Date date) {
        tradeMarketFeeDOMapper.deleteByCreateTime(date);
        FinancialReqDTO financialReqDTO = new FinancialReqDTO();
        //获取前一天数据
        financialReqDTO.setStartDate(DateUtil.offsetDay(date,- 1));
        financialReqDTO.setEndDate(DateUtil.offsetDay(date,- 1));
        FinancialService.changeDate(financialReqDTO);
        List<ProductCouponResDTO> list = buildMarketFeeResDTO(financialReqDTO);
        if(CollUtil.isNotEmpty(list)){
            list.stream().forEach(productCouponResDTO -> {
                TradeMarketFeeDO tradeMarketFeeDO = new TradeMarketFeeDO();
                BeanUtils.copyProperties(productCouponResDTO,tradeMarketFeeDO);
                tradeMarketFeeDO.setId(super.generateId());
                tradeMarketFeeDO.setPaymentTime(DateUtil.format(DateUtil.offsetDay(date,-1),DatePattern.NORM_DATE_PATTERN));
                tradeMarketFeeDO.setCreateTime(date);
                if(StringUtils.isNotBlank(tradeMarketFeeDO.getMerchantNo())){
                    if(tradeMarketFeeDO.getMerchantNo().equals("0")){
                        tradeMarketFeeDO.setMerchantName("青岛银行信用卡商城");
                    }else{
                        MerchantResDTO merchantResDTO = merchantService.getItem(Long.valueOf(tradeMarketFeeDO.getMerchantNo()));
                        if(merchantResDTO != null){
                            tradeMarketFeeDO.setMerchantName(merchantResDTO.getMerchantName());
                        }
                    }
                }
                tradeMarketFeeDOMapper.insert(tradeMarketFeeDO);
            });
        }
    }


    private List<ProductCouponResDTO> buildMarketFeeResDTO(FinancialReqDTO financialReqDTO){
        OrderReportExportDO orderReportExportDO = new OrderReportExportDO();
        BeanUtils.copyProperties(financialReqDTO,orderReportExportDO);
        List<ProductCouponResDTO> list = new ArrayList<>();
        List<TradeMerchantInfoDO> orderMerchantNos = orderReportDOMapper.selectCouponMerchantInfo(orderReportExportDO);
        List<TradeMerchantInfoDO> refundMerchantNos = orderRefundreportDOMapper.selectCouponMerchantInfo(orderReportExportDO);
        if(CollUtil.isNotEmpty(orderMerchantNos)){//有支付订单
            orderMerchantNos.forEach(merchantInfoDO->{
                merchantInfoDO.setStartDate(orderReportExportDO.getStartDate());
                merchantInfoDO.setEndDate(orderReportExportDO.getEndDate());
                ProductCouponDO productCouponDO = orderReportDOMapper.selectProductCoupon(merchantInfoDO);
                if(productCouponDO == null) return;
                TradeMerchantInfoDO refundMerchantInfoDO = new TradeMerchantInfoDO();
                refundMerchantInfoDO.setBatchNo(productCouponDO.getBatchNo());
                refundMerchantInfoDO.setMerchantNo(merchantInfoDO.getMerchantNo());
                refundMerchantInfoDO.setStartDate(orderReportExportDO.getStartDate());
                refundMerchantInfoDO.setEndDate(orderReportExportDO.getEndDate());
                refundMerchantInfoDO.setCouponType(productCouponDO.getCouponType());
                ProductCouponDO refundProductReportDO = orderRefundreportDOMapper.selectProductCoupon(refundMerchantInfoDO);
               if(refundProductReportDO != null){//当天有退货
                   ProductCouponResDTO productCouponResDTO = buildOrderAndRefund(productCouponDO,refundProductReportDO);
                   if(productCouponResDTO != null){
                       list.add(productCouponResDTO);
                   }
               }else{//当天无退货
                   ProductCouponResDTO productCouponResDTO = buildOrderOrRefundResDTO(false,productCouponDO);
                   if(productCouponResDTO != null){
                       list.add(productCouponResDTO);
                   }

               }
            });
            //过滤商户号不同 当天只有退货的且不没有支付订单的商户
            if(CollUtil.isNotEmpty(refundMerchantNos)){
                List<TradeMerchantInfoDO> onlyRefundMerchantNos = refundMerchantNos.stream().filter(r -> !orderMerchantNos.stream().map(o -> o.getMerchantNo()).collect(Collectors.toList()).contains(r.getMerchantNo())).collect(Collectors.toList());
                for(TradeMerchantInfoDO refundMerchantNO : onlyRefundMerchantNos){
                    this.getRefundResDTO(refundMerchantNO,list,orderReportExportDO);
                }
            }
            //过滤商户号相同 批次号不同
            if(CollUtil.isNotEmpty(refundMerchantNos)){
                List<TradeMerchantInfoDO> onlyRefundMerchantNos = refundMerchantNos.stream().filter(r -> orderMerchantNos.stream().map(o -> o.getMerchantNo()).collect(Collectors.toList()).contains(r.getMerchantNo())).collect(Collectors.toList());
                if(CollUtil.isNotEmpty(onlyRefundMerchantNos)){
                    List<TradeMerchantInfoDO> onlyRefundMerchantNonew = onlyRefundMerchantNos.stream().filter(r -> !orderMerchantNos.stream().map(o -> o.getBatchNo()).collect(Collectors.toList()).contains(r.getBatchNo())).collect(Collectors.toList());
                    for(TradeMerchantInfoDO refundMerchantNO : onlyRefundMerchantNonew){
                        this.getRefundResDTO(refundMerchantNO,list,orderReportExportDO);
                    }
                }
            }
        }else{//仅仅只有退货
            if(CollUtil.isNotEmpty(refundMerchantNos)){
                refundMerchantNos.forEach(orderMerchantNo->{
                   this.getRefundResDTO(orderMerchantNo,list,orderReportExportDO);
                });
            }
        }
        return list;
    }
    private void getRefundResDTO(TradeMerchantInfoDO tradeMerchantInfoDO,List<ProductCouponResDTO> list,OrderReportExportDO orderReportExportDO){
        TradeMerchantInfoDO refundMerchantInfoDO = new TradeMerchantInfoDO();
        refundMerchantInfoDO.setMerchantNo(tradeMerchantInfoDO.getMerchantNo());
        refundMerchantInfoDO.setStartDate(orderReportExportDO.getStartDate());
        refundMerchantInfoDO.setEndDate(orderReportExportDO.getEndDate());
        refundMerchantInfoDO.setBatchNo(tradeMerchantInfoDO.getBatchNo());
        refundMerchantInfoDO.setCouponType(tradeMerchantInfoDO.getCouponType());
        ProductCouponDO refundProductReportDO = orderRefundreportDOMapper.selectProductCoupon(refundMerchantInfoDO);
        if(refundProductReportDO != null){
            ProductCouponResDTO productCouponResDTO = buildOrderOrRefundResDTO(true,refundProductReportDO);
            if(productCouponResDTO != null){
                list.add(productCouponResDTO);
            }
        }
    }
    /**
     * 包含支付和退款
     * @param productCouponDO
     * @param refundProductReportDO
     * @return
     */
    private ProductCouponResDTO buildOrderAndRefund(ProductCouponDO productCouponDO,ProductCouponDO refundProductReportDO){
        if(productCouponDO.getProductCoupon().compareTo(refundProductReportDO.getProductCoupon()) == 0) return  null;
        ProductCouponResDTO productCouponResDTO = new ProductCouponResDTO();
        productCouponResDTO.setMerchantName(productCouponDO.getMerchantName());
        productCouponResDTO.setMerchantNo(productCouponDO.getMerchantNo()+"");
        productCouponResDTO.setPaymentTime(DateUtil.format(productCouponDO.getPaymentTime(),DatePattern.NORM_DATE_PATTERN));
        productCouponResDTO.setProductCoupon(productCouponDO.getProductCoupon().subtract(refundProductReportDO.getProductCoupon())+"");
        productCouponResDTO.setBatchNo(productCouponDO.getBatchNo());
        productCouponResDTO.setCouponName(productCouponDO.getCouponName());
        productCouponResDTO.setCouponType(productCouponDO.getCouponType());
        return productCouponResDTO;
    }
    /**
     * 只包含支付或退款
     * @param refundFlag
     * @param productCouponDO
     * @return
     */
    private ProductCouponResDTO buildOrderOrRefundResDTO(boolean refundFlag,ProductCouponDO productCouponDO){
        if(productCouponDO.getProductCoupon().compareTo(BigDecimal.ZERO) == 0) return  null;
        ProductCouponResDTO productCouponResDTO = new ProductCouponResDTO();
        productCouponResDTO.setMerchantName(productCouponDO.getMerchantName());
        productCouponResDTO.setMerchantNo(productCouponDO.getMerchantNo()+"");
        productCouponResDTO.setPaymentTime(DateUtil.format(productCouponDO.getPaymentTime(),DatePattern.NORM_DATE_PATTERN));
        productCouponResDTO.setProductCoupon(refundFlag &&  productCouponDO.getProductCoupon().compareTo(BigDecimal.ZERO) > 0 ?  "-"+productCouponDO.getProductCoupon() : productCouponDO.getProductCoupon()+"");
        productCouponResDTO.setBatchNo(productCouponDO.getBatchNo());
        productCouponResDTO.setCouponName(productCouponDO.getCouponName());
        productCouponResDTO.setCouponType(productCouponDO.getCouponType());
        return productCouponResDTO;
    }
    private void writeData(List<ProductCouponResDTO> orderDetailResDTOS, ExportDataBO<OrderTotalResDTO> exportDataBO, HttpServletResponse response,boolean isSingleMerchant){
       if(CollUtil.isEmpty(orderDetailResDTOS)) orderDetailResDTOS = new ArrayList<>();
        List<List<String>> datas = CollUtil.newLinkedList();
        BigDecimal total = new BigDecimal(0);
        for(ProductCouponResDTO  o : orderDetailResDTOS){
            List<String> data = CollUtil.newLinkedList(o.getPaymentTime(),o.getMerchantNo(),o.getMerchantName(),
                    o.getCouponTypeDesc()
                    ,o.getBatchNo(),o.getCouponName(),o.getProductCoupon());
            datas.add(data);
            total = total.add(new BigDecimal(o.getProductCoupon()));
        }
        exportDataBO.setData(datas);
        if(isSingleMerchant){
            //往最后一行进行汇总
            List<String> data = CollUtil.newLinkedList("汇总","","","","",total+"");
            datas.add(data);
        }
        ExcelExportUtil.exportExcelByHuTools(response,exportDataBO);
    }

    private PageInfo<ProductCouponResDTO> exportMarketData(FinancialReqDTO financialReqDTO){
        List<ProductCouponResDTO> productCouponResDTOS = new ArrayList<>();
        if(financialReqDTO.getPageSize() != null && financialReqDTO.getPageNum() != null){
            PageHelper.startPage(financialReqDTO.getPageNum(),financialReqDTO.getPageSize());
        }
        OrderReportExportDO orderReportExportDO = new OrderReportExportDO();
        BeanUtils.copyProperties(financialReqDTO,orderReportExportDO);
        List<TradeMarketFeeDO> list = tradeMarketFeeDOMapper.selectTradeMarketFeeResult(orderReportExportDO);
        if(CollUtil.isNotEmpty(list)){
            list.stream().forEach(tradeMarketFeeDO -> {
                ProductCouponResDTO productCouponResDTO = new ProductCouponResDTO();
                BeanUtils.copyProperties(tradeMarketFeeDO,productCouponResDTO);
                productCouponResDTO.setCouponTypeDesc(getCouponType(tradeMarketFeeDO));
                productCouponResDTOS.add(productCouponResDTO);
            });
        }
        return super.getPageInfo(list,productCouponResDTOS);
    }

    private String getCouponType(TradeMarketFeeDO tradeTotal){
        if(0L==tradeTotal.getCouponType() && !org.springframework.util.StringUtils.hasText(tradeTotal.getBatchNo())) return "积分兑换券";
        if(0L==tradeTotal.getCouponType() && org.springframework.util.StringUtils.hasText(tradeTotal.getBatchNo())) return "行发积分兑换券";
        if(1L==tradeTotal.getCouponType() && org.springframework.util.StringUtils.hasText(tradeTotal.getBatchNo())) return "指定商品免费兑换券";
        if(2L==tradeTotal.getCouponType() && org.springframework.util.StringUtils.hasText(tradeTotal.getBatchNo())) return "指定商品现金优惠券";
        if(4L==tradeTotal.getCouponType() && org.springframework.util.StringUtils.hasText(tradeTotal.getBatchNo())) return "指定专区现金优惠券";
        if(5L==tradeTotal.getCouponType() && org.springframework.util.StringUtils.hasText(tradeTotal.getBatchNo())) return "全场通用现金优惠券";
        return "";
    }

}
