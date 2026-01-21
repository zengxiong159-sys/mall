package com.qdbank.mall.financial.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.domain.ExportDataBO;
import com.qdbank.mall.financial.FinancialService;
import com.qdbank.mall.mapper.orderrefundreport.OrderRefundreportDOMapper;
import com.qdbank.mall.mapper.orderreport.OrderReportDOMapper;
import com.qdbank.mall.mapper.trade.TradeIntegrationDOMapper;
import com.qdbank.mall.merchant.MerchantService;
import com.qdbank.mall.model.orderreport.TradeMerchantInfoDO;
import com.qdbank.mall.model.orderreport.IntegrationReportDO;
import com.qdbank.mall.model.orderreport.OrderReportExportDO;
import com.qdbank.mall.model.trade.TradeIntegrationDO;
import com.qdbank.mall.request.financial.FinancialReqDTO;
import com.qdbank.mall.response.financial.IntegrationCouponResDTO;
import com.qdbank.mall.response.financial.OrderTotalResDTO;
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
 * @ClassName CouponTotalFinancialServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/21 17:16
 * @Version 1.0
 **/
@Service
public class CouponTotalFinancialServiceImpl extends BaseServiceImpl implements FinancialService<IntegrationCouponResDTO> {
    @Autowired
    private OrderReportDOMapper orderReportDOMapper;
    @Autowired
    private OrderRefundreportDOMapper orderRefundreportDOMapper;
    @Autowired
    private TradeIntegrationDOMapper tradeIntegrationDOMapper;
    @Autowired
    private MerchantService merchantService;
    @Override
    public void export(HttpServletResponse response, FinancialReqDTO financialReqDTO) {
        financialReqDTO.setPageNum(null);
        financialReqDTO.setPageSize(null);
        Date startDate = financialReqDTO.getStartDate();
       // FinancialService.changeDate(financialReqDTO);
        List<String> rowHead = CollUtil.newLinkedList("日期", "商户编码", "商户名称", "纯积分-需结算（元）",  "纯积分-不需结算","积分兑换券","汇总","汇总-需结算");
        ExportDataBO<OrderTotalResDTO> exportDataBO = new ExportDataBO<>(Constant.INTEGRATION_TOTAL_FILE_NAME,Constant.INTEGRATION_TOTAL_SHEET_NAME,Constant.COUPON_FILE_SUFFIX,rowHead);
        exportDataBO.setFileName(Constant.INTEGRATION_TOTAL_FILE_NAME+(DateUtil.format(startDate, DatePattern.NORM_DATE_PATTERN))+"-"+(DateUtil.format(financialReqDTO.getEndDate(),DatePattern.NORM_DATE_PATTERN)));
        writeData(exportIntegrationData(financialReqDTO).getList(), exportDataBO,response, StringUtils.isNotBlank(financialReqDTO.getMerchantNo()));
    }

    @Override
    public void insertTradeRecord(Date date) {
        tradeIntegrationDOMapper.deleteByCreateTime(date);
        FinancialReqDTO financialReqDTO = new FinancialReqDTO();
        financialReqDTO.setStartDate(DateUtil.offsetDay(date,- 1));
        financialReqDTO.setEndDate(DateUtil.offsetDay(date,- 1));
        FinancialService.changeDate(financialReqDTO);
        List<IntegrationCouponResDTO> integrationCouponResDTOS = this.buildCouponResDTO(financialReqDTO);
        if(CollUtil.isNotEmpty(integrationCouponResDTOS)){
            integrationCouponResDTOS.stream().forEach(integrationCouponResDTO -> {
                TradeIntegrationDO tradeIntegrationDO = new TradeIntegrationDO();
                BeanUtils.copyProperties(integrationCouponResDTO,tradeIntegrationDO);
                tradeIntegrationDO.setId(super.generateId());
                tradeIntegrationDO.setPaymentTime(DateUtil.format(DateUtil.offsetDay(date,-1),DatePattern.NORM_DATE_PATTERN));
                tradeIntegrationDO.setCreateTime(date);
                if(StringUtils.isNotBlank(tradeIntegrationDO.getMerchantNo())){
                    if(tradeIntegrationDO.getMerchantNo().equals("0")){
                        tradeIntegrationDO.setMerchantName("青岛银行信用卡商城");
                    }else{
                        MerchantResDTO merchantResDTO = merchantService.getItem(Long.valueOf(tradeIntegrationDO.getMerchantNo()));
                        if(merchantResDTO != null){
                            tradeIntegrationDO.setMerchantName(merchantResDTO.getMerchantName());
                        }
                    }
                }
                tradeIntegrationDOMapper.insert(tradeIntegrationDO);
            });
        }
    }

    @Override
    public PageInfo<IntegrationCouponResDTO> getTradeList(FinancialReqDTO financialReqDTO) {
        return this.exportIntegrationData(financialReqDTO);
    }

    private List<IntegrationCouponResDTO> buildCouponResDTO(FinancialReqDTO financialReqDTO){
        OrderReportExportDO orderReportExportDO = new OrderReportExportDO();
        BeanUtils.copyProperties(financialReqDTO,orderReportExportDO);
        List<IntegrationCouponResDTO> list = new ArrayList<>();
        List<TradeMerchantInfoDO> orderMerchantNos = orderReportDOMapper.selectIntegrationMerchantInfo(orderReportExportDO);
        List<TradeMerchantInfoDO> refundMerchantNos = orderRefundreportDOMapper.selectIntegrationMerchantInfo(orderReportExportDO);
        if(CollUtil.isNotEmpty(orderMerchantNos)){//有支付订单
            orderMerchantNos.forEach(merchantInfoDO->{
                merchantInfoDO.setStartDate(orderReportExportDO.getStartDate());
                merchantInfoDO.setEndDate(orderReportExportDO.getEndDate());
                List<IntegrationReportDO> integrationReportDOs = orderReportDOMapper.selectIntegrationCoupon(merchantInfoDO);
                for(IntegrationReportDO integrationReportDO : integrationReportDOs){
                    TradeMerchantInfoDO refundMerchantInfDO = new TradeMerchantInfoDO();
                    refundMerchantInfDO.setStartDate(orderReportExportDO.getStartDate());
                    refundMerchantInfDO.setEndDate(orderReportExportDO.getEndDate());
                    refundMerchantInfDO.setMerchantNo(integrationReportDO.getMerchantNo());
                    IntegrationReportDO refundIntegrationReportDO = orderRefundreportDOMapper.selectIntegrationCoupon(merchantInfoDO);
                    if(refundIntegrationReportDO != null){//当天有退货
                        IntegrationCouponResDTO integrationCouponResDTO = buildOrderAndRefund(integrationReportDO,refundIntegrationReportDO);
                        if(integrationCouponResDTO != null){
                            list.add(buildOrderAndRefund(integrationReportDO,refundIntegrationReportDO));
                        }
                    }else{//当天无退货
                        IntegrationCouponResDTO integrationCouponResDTO = buildOrderOrRefundResDTO(false,integrationReportDO);
                        if(integrationCouponResDTO != null){
                            list.add(integrationCouponResDTO);
                        }

                    }
                }
            });
            //过滤当天只有退货的且不没有支付订单的商户
            if(CollUtil.isNotEmpty(refundMerchantNos)){
                List<TradeMerchantInfoDO> onlyRefundMerchantNos = refundMerchantNos.stream().filter(r -> !orderMerchantNos.stream().map(o -> o.getMerchantNo()).collect(Collectors.toList()).contains(r.getMerchantNo())).collect(Collectors.toList());
                for(TradeMerchantInfoDO refundMerchantNO : onlyRefundMerchantNos){
                    refundMerchantNO.setStartDate(orderReportExportDO.getStartDate());
                    refundMerchantNO.setEndDate(orderReportExportDO.getEndDate());
                    IntegrationReportDO refundIntegrationReportDO = orderRefundreportDOMapper.selectIntegrationCoupon(refundMerchantNO);
                    IntegrationCouponResDTO integrationCouponResDTO = buildOrderOrRefundResDTO(true,refundIntegrationReportDO);
                    if(integrationCouponResDTO != null ){
                        list.add(integrationCouponResDTO);
                    }
                }
            }
        }else{
            if(CollUtil.isNotEmpty(refundMerchantNos)){
                refundMerchantNos.forEach(orderMerchantNo->{
                    orderMerchantNo.setStartDate(orderReportExportDO.getStartDate());
                    orderMerchantNo.setEndDate(orderReportExportDO.getEndDate());
                    IntegrationReportDO refundIntegrationReportDO = orderRefundreportDOMapper.selectIntegrationCoupon(orderMerchantNo);
                    IntegrationCouponResDTO integrationCouponResDTO = buildOrderOrRefundResDTO(true,refundIntegrationReportDO);
                    if(integrationCouponResDTO != null){
                        list.add(integrationCouponResDTO);
                    }
                });
            }
        }
        return list;
    }

    /**
     * 只包含支付或退款
     * @param refundFlag
     * @param refundIntegrationReportDO
     * @return
     */
    private IntegrationCouponResDTO buildOrderOrRefundResDTO(boolean refundFlag,IntegrationReportDO refundIntegrationReportDO){
        if(refundIntegrationReportDO.getIntegrationCoupon().compareTo(BigDecimal.ZERO) == 0
                && refundIntegrationReportDO.getNeedPayIntegration().compareTo(BigDecimal.ZERO) == 0
                && refundIntegrationReportDO.getNotNeedPayIntegration().compareTo(BigDecimal.ZERO) == 0){
            return  null;
        }
        IntegrationCouponResDTO integrationCouponResDTO = new IntegrationCouponResDTO();
        integrationCouponResDTO.setPaymentTime(DateUtil.format(refundIntegrationReportDO.getPaymentTime(),DatePattern.NORM_DATE_PATTERN));
        integrationCouponResDTO.setIntegrationCoupon(refundFlag && refundIntegrationReportDO.getIntegrationCoupon().compareTo(BigDecimal.ZERO) > 0 ? "-" + refundIntegrationReportDO.getIntegrationCoupon() : refundIntegrationReportDO.getIntegrationCoupon()+"");
        integrationCouponResDTO.setMerchantName(refundIntegrationReportDO.getMerchantName());
        integrationCouponResDTO.setMerchantNo(refundIntegrationReportDO.getMerchantNo()+"");
        integrationCouponResDTO.setNeedPayIntegration(refundFlag && refundIntegrationReportDO.getNeedPayIntegration().compareTo(BigDecimal.ZERO) > 0 ? "-" + refundIntegrationReportDO.getNeedPayIntegration() : refundIntegrationReportDO.getNeedPayIntegration()+"");
        integrationCouponResDTO.setNotNeedPayIntegration(refundFlag && refundIntegrationReportDO.getNotNeedPayIntegration().compareTo(BigDecimal.ZERO) > 0? "-" + refundIntegrationReportDO.getNotNeedPayIntegration() : refundIntegrationReportDO.getNotNeedPayIntegration()+"");
        integrationCouponResDTO.setNeedPayTotalIntegration(new BigDecimal(integrationCouponResDTO.getNeedPayIntegration()).add(new BigDecimal(integrationCouponResDTO.getIntegrationCoupon()))+"");
        integrationCouponResDTO.setTotalIntegration(new BigDecimal(integrationCouponResDTO.getNeedPayIntegration()).add(new BigDecimal(integrationCouponResDTO.getIntegrationCoupon())).add(new BigDecimal(integrationCouponResDTO.getNotNeedPayIntegration()))+"");
        return integrationCouponResDTO;
    }

    /**
     * 包含支付和退款
     * @param integrationReportDO
     * @param refundIntegrationReportDO
     * @return
     */
    private IntegrationCouponResDTO buildOrderAndRefund(IntegrationReportDO integrationReportDO,IntegrationReportDO refundIntegrationReportDO){
        if(integrationReportDO.getIntegrationCoupon().compareTo(refundIntegrationReportDO.getIntegrationCoupon()) == 0
                && integrationReportDO.getNeedPayIntegration().compareTo(refundIntegrationReportDO.getNeedPayIntegration()) == 0
                && integrationReportDO.getNotNeedPayIntegration().compareTo(refundIntegrationReportDO.getNotNeedPayIntegration()) == 0){
                return  null;
        }
        IntegrationCouponResDTO integrationCouponResDTO = new IntegrationCouponResDTO();
        integrationCouponResDTO.setPaymentTime(DateUtil.format(integrationReportDO.getPaymentTime(),DatePattern.NORM_DATE_PATTERN));
        integrationCouponResDTO.setIntegrationCoupon(integrationReportDO.getIntegrationCoupon().subtract(refundIntegrationReportDO.getIntegrationCoupon())+"");
        integrationCouponResDTO.setMerchantName(integrationReportDO.getMerchantName());
        integrationCouponResDTO.setMerchantNo(integrationReportDO.getMerchantNo()+"");
        integrationCouponResDTO.setNeedPayIntegration(integrationReportDO.getNeedPayIntegration().subtract(refundIntegrationReportDO.getNeedPayIntegration())+"");
        integrationCouponResDTO.setNotNeedPayIntegration(integrationReportDO.getNotNeedPayIntegration().subtract(refundIntegrationReportDO.getNotNeedPayIntegration())+"");
        integrationCouponResDTO.setNeedPayTotalIntegration(new BigDecimal(integrationCouponResDTO.getNeedPayIntegration()).add(new BigDecimal(integrationCouponResDTO.getIntegrationCoupon()))+"");
        integrationCouponResDTO.setTotalIntegration(new BigDecimal(integrationCouponResDTO.getNeedPayIntegration()).add(new BigDecimal(integrationCouponResDTO.getIntegrationCoupon())).add(new BigDecimal(integrationCouponResDTO.getNotNeedPayIntegration()))+"");
        return integrationCouponResDTO;
    }
    private void writeData(List<IntegrationCouponResDTO> orderDetailResDTOS, ExportDataBO<OrderTotalResDTO> exportDataBO, HttpServletResponse response,boolean isSingleMerchant){
       if(CollUtil.isEmpty(orderDetailResDTOS)) orderDetailResDTOS = new ArrayList<>();
        List<List<String>> datas = CollUtil.newLinkedList();
        BigDecimal needBalanceTotal = new BigDecimal(0);//需结算纵向汇总
        BigDecimal notNeedBalanceTotal = new BigDecimal(0);//不需要结算纵向汇总
        BigDecimal couponTotal = new BigDecimal(0);//积分券纵向汇总
        BigDecimal total = new BigDecimal(0);//纵向汇总
        BigDecimal totalBalance = new BigDecimal(0);//需结算纵向汇总
        for(IntegrationCouponResDTO o : orderDetailResDTOS){
            List<String> data = CollUtil.newLinkedList(o.getPaymentTime(),o.getMerchantNo(),o.getMerchantName(),o.getNeedPayIntegration(),o.getNotNeedPayIntegration(),o.getIntegrationCoupon(),o.getTotalIntegration(),o.getNeedPayTotalIntegration());
            datas.add(data);
            needBalanceTotal = needBalanceTotal.add(new BigDecimal(o.getNeedPayIntegration()));
            notNeedBalanceTotal = notNeedBalanceTotal.add(new BigDecimal(o.getNotNeedPayIntegration()));
            couponTotal = couponTotal.add(new BigDecimal(o.getIntegrationCoupon()));
            total=  total.add(new BigDecimal(o.getTotalIntegration()));
            totalBalance = totalBalance.add(new BigDecimal(o.getNeedPayTotalIntegration()));
        }
        exportDataBO.setData(datas);
        if(isSingleMerchant){//单商户需要纵向汇总
            //往最后一行进行汇总
            List<String> data = CollUtil.newLinkedList("汇总",orderDetailResDTOS.get(0).getMerchantNo(),orderDetailResDTOS.get(0).getMerchantName(),needBalanceTotal+"",notNeedBalanceTotal+"",couponTotal+"",total+"",totalBalance+"");
            datas.add(data);
        }
        ExcelExportUtil.exportExcelByHuTools(response,exportDataBO);
    }
    private PageInfo<IntegrationCouponResDTO> exportIntegrationData(FinancialReqDTO financialReqDTO){
        List<IntegrationCouponResDTO> integrationCouponResDTOS = new ArrayList<>();
        if(financialReqDTO.getPageSize() != null && financialReqDTO.getPageNum() != null){
            PageHelper.startPage(financialReqDTO.getPageNum(),financialReqDTO.getPageSize());
        }
        OrderReportExportDO orderReportExportDO = new OrderReportExportDO();
        BeanUtils.copyProperties(financialReqDTO,orderReportExportDO);
        List<TradeIntegrationDO> list = tradeIntegrationDOMapper.selectTradeIntegrationResult(orderReportExportDO);
        if(CollUtil.isNotEmpty(list)){
            list.stream().forEach(tradeIntegrationDO -> {
                IntegrationCouponResDTO integrationCouponResDTO = new IntegrationCouponResDTO();
                BeanUtils.copyProperties(tradeIntegrationDO,integrationCouponResDTO);
                integrationCouponResDTOS.add(integrationCouponResDTO);
            });
        }
        return super.getPageInfo(list,integrationCouponResDTOS);
    }
}
