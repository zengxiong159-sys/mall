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
import com.qdbank.mall.mapper.trade.TradeTotalDOMapper;
import com.qdbank.mall.merchant.MerchantService;
import com.qdbank.mall.model.orderreport.OrderReportExportDO;
import com.qdbank.mall.model.orderreport.OrderTotalReportDO;
import com.qdbank.mall.model.trade.TradeTotalDO;
import com.qdbank.mall.request.financial.FinancialReqDTO;
import com.qdbank.mall.response.financial.OrderTotalResDTO;
import com.qdbank.mall.response.merchant.MerchantResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.util.ExcelExportUtil;
import com.qdbank.mall.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName TradeTotalFinancialServiceImpl
 * @Description 商城交易汇总
 * @Author ningyuehuai
 * @Date 2021/3/13 11:42
 * @Version 1.0
 **/
@Service
public class TradeTotalFinancialServiceImpl extends BaseServiceImpl implements FinancialService<OrderTotalResDTO> {
    @Autowired
    private OrderReportDOMapper orderReportDOMapper;
    @Autowired
    private OrderRefundreportDOMapper orderRefundreportDOMapper;
    @Autowired
    private TradeTotalDOMapper tradeTotalDOMapper;
    @Autowired
    private MerchantService merchantService;
    @Override
    public void export(HttpServletResponse response, FinancialReqDTO financialReqDTO) {
        financialReqDTO.setPageNum(null);
        financialReqDTO.setPageSize(null);
        Date startDate = financialReqDTO.getStartDate();
        //FinancialService.changeDate(financialReqDTO);
        List<OrderTotalResDTO> list = exportTotalData(financialReqDTO).getList();
        try {
            List<String> rowHead = CollUtil.newLinkedList("商户名称", "商户号", "类型", "订单笔数汇总",  "商品实际售价(折算价)汇总","积分汇总","优惠券类型汇总","优惠券面值汇总","现金汇总","运费汇总","现金+运费汇总","优惠金额汇总");
            ExportDataBO<OrderTotalResDTO> exportDataBO = new ExportDataBO<>(Constant.ORDER_TOTAL_FILE_NAME,Constant.ORDER_TOTAL_SHEET_NAME,Constant.COUPON_FILE_SUFFIX,rowHead);
            exportDataBO.setFileName(Constant.ORDER_TOTAL_FILE_NAME+(DateUtil.format(startDate, DatePattern.NORM_DATE_PATTERN))+"-"+(DateUtil.format(financialReqDTO.getEndDate(),DatePattern.NORM_DATE_PATTERN)));
            ExcelWriter writer = exportDataBO.getWriter();
            writer.merge(rowHead.size() - 1,Constant.ORDER_TOTAL_FILE_NAME+(DateUtil.format(startDate,"yyyy/MM/dd"))+"-"+(DateUtil.format(financialReqDTO.getEndDate(),"yyyy/MM/dd")),true);
            //定义启始行
            int merchantNamestartRow = 2;
            int merchantNOStartRow = 2;
            List<List<String>> datas = CollUtil.newLinkedList();
            //按照商户名称进行分组
            Map<String, List<OrderTotalResDTO>> merchantNameGroupMaps = list.stream().collect(Collectors.groupingBy(orderTotalDTO->orderTotalDTO.getMerchantName(), LinkedHashMap::new,Collectors.toList()));
            for (Map.Entry<String, List<OrderTotalResDTO>> listMerchantNameEntry : merchantNameGroupMaps.entrySet()) {
                List<OrderTotalResDTO> merchantNameList = listMerchantNameEntry.getValue();
                //根据数据条数设置合并单元格信息
                if(merchantNameList.size() == 1){//一条数据不合并
                    merchantNamestartRow = merchantNamestartRow + merchantNameList.size();
                    merchantNOStartRow = merchantNOStartRow + merchantNameList.size();
                }else {
                    //规则编写:合并某行的单元格，并写入对象到单元格
                    writer.merge(merchantNamestartRow, merchantNamestartRow + merchantNameList.size() - 1, 0, 0, null, true);
                    merchantNamestartRow = merchantNamestartRow + merchantNameList.size();
                    //按照商户编号进行分组
                    Map<String, List<OrderTotalResDTO>> merchantNoGroupMaps = merchantNameList.stream().collect(Collectors.groupingBy(orderTotalDTO->orderTotalDTO.getMerchantNo(),LinkedHashMap::new,Collectors.toList()));
                    for (Map.Entry<String, List<OrderTotalResDTO>> listMerchantNoEntry : merchantNoGroupMaps.entrySet()) {
                        List<OrderTotalResDTO> merchantNoList = listMerchantNoEntry.getValue();
                        //根据数据条数设置合并单元格信息
                        if(merchantNoList.size() == 1){//一条数据不合并
                            merchantNOStartRow = merchantNOStartRow + merchantNoList.size();
                        }else {
                            //规则编写：合并某行的单元格，并写入对象到单元格
                            writer.merge(merchantNOStartRow, merchantNOStartRow + merchantNoList.size() - 1, 1, 1, null, true);
                            merchantNOStartRow = merchantNOStartRow + merchantNoList.size();
                        }
                    }
                }
                //组装数据
                merchantNameList.forEach( orderTotalResDTO ->{
                    List<String> data = CollUtil.newLinkedList(orderTotalResDTO.getMerchantName(), orderTotalResDTO.getMerchantNo(), orderTotalResDTO.getOrderType(), orderTotalResDTO.getOrderCount(), orderTotalResDTO.getProductPrice(),orderTotalResDTO.getIntegrationCount(),orderTotalResDTO.getCouponTypeDesc(),orderTotalResDTO.getCouponCount(),orderTotalResDTO.getOrderCash(),orderTotalResDTO.getFreightAmount(),orderTotalResDTO.getCashFreightAmount(),orderTotalResDTO.getDiscountAmount());
                    datas.add(data);
                });
            }
            exportDataBO.setData(datas);
            ExcelExportUtil.exportExcelByHuTools(response,exportDataBO);
        }catch (Exception e){
            //TODO
            e.printStackTrace();
        }
    }

    /**
     *
     */
    @Override
    public void insertTradeRecord(Date date) {
        tradeTotalDOMapper.deleteByCreateTime(date);
        FinancialReqDTO financialReqDTO = new FinancialReqDTO();
        financialReqDTO.setStartDate(DateUtil.offsetDay(date,- 1));
        financialReqDTO.setEndDate(DateUtil.offsetDay(date,- 1));
        FinancialService.changeDate(financialReqDTO);
        List<OrderTotalResDTO> orderTotalResDTOS = this.makeExportData(financialReqDTO);
        if(CollUtil.isNotEmpty(orderTotalResDTOS)){
            orderTotalResDTOS.stream().forEach(orderTotalResDTO -> {
                TradeTotalDO tradeTotalDO = new TradeTotalDO();
                BeanUtils.copyProperties(orderTotalResDTO,tradeTotalDO);
                tradeTotalDO.setId(super.generateId());
                tradeTotalDO.setPaymentTime(DateUtil.format(DateUtil.offsetDay(date,-1),DatePattern.NORM_DATE_PATTERN));
                tradeTotalDO.setCreateTime(date);
                tradeTotalDO.setDiscountAmount(orderTotalResDTO.getDiscountAmount() == null ? BigDecimal.ZERO : new BigDecimal(orderTotalResDTO.getDiscountAmount()));
                if(StringUtils.isNotBlank(tradeTotalDO.getMerchantNo())){
                    if(tradeTotalDO.getMerchantNo().equals("0")){
                        tradeTotalDO.setMerchantName("青岛银行信用卡商城");
                    }else{
                        MerchantResDTO merchantResDTO = merchantService.getItem(Long.valueOf(tradeTotalDO.getMerchantNo()));
                        if(merchantResDTO != null){
                            tradeTotalDO.setMerchantName(merchantResDTO.getMerchantName());
                        }
                    }
                }
                tradeTotalDOMapper.insert(tradeTotalDO);
            });
        }
    }

    @Override
    public PageInfo<OrderTotalResDTO> getTradeList(FinancialReqDTO financialReqDTO) {
        return this.exportTotalData(financialReqDTO);
    }

    private List<OrderTotalResDTO> makeExportData(FinancialReqDTO financialReqDTO){
        OrderReportExportDO orderReportExportDO = new OrderReportExportDO();
        BeanUtils.copyProperties(financialReqDTO,orderReportExportDO);
        List<Long> orderMerchantNos = orderReportDOMapper.selectOrderMerchantNos(orderReportExportDO);
        List<Long> refundMerchantNos = orderRefundreportDOMapper.selectOrderMerchantNos(orderReportExportDO);
        List<OrderTotalResDTO> list =  new ArrayList<>();
        if(CollUtil.isNotEmpty(orderMerchantNos)){//有支付订单商户
            for(Long merchantNo : orderMerchantNos){
                OrderReportExportDO exportDO = new OrderReportExportDO();
                BeanUtils.copyProperties(financialReqDTO,exportDO);
                exportDO.setMerchantNo(merchantNo+"");
                List<OrderTotalReportDO> orderTotalReportDO = orderReportDOMapper.selectTradeTotal(exportDO);
                List<OrderTotalReportDO> refundTotal = orderRefundreportDOMapper.selectTradeTotal(exportDO);
                buildExportData_list(list,orderTotalReportDO,refundTotal,exportDO);
            }
            if(CollUtil.isNotEmpty(refundMerchantNos)){
                //过滤只有退款无支付商户
                List<Long> onlyRefundMerchantNos = refundMerchantNos.stream().filter(merchantNo -> !orderMerchantNos.contains(merchantNo)).collect(Collectors.toList());
                for(Long merchantNo : onlyRefundMerchantNos){
                    this.onlyRefundMerchant(list,financialReqDTO,merchantNo);
                }
            }
            return list;
        }else{//无支付订单商户只有退款商户
            for(Long refundMerchantNo : refundMerchantNos){
                this.onlyRefundMerchant(list,financialReqDTO,refundMerchantNo);
            }
        }
        return list;
    }
    //仅只有退款商户
    private void onlyRefundMerchant(List<OrderTotalResDTO> list,FinancialReqDTO financialReqDTO,Long merchantNo){
        OrderReportExportDO exportDO = new OrderReportExportDO();
        BeanUtils.copyProperties(financialReqDTO,exportDO);
        exportDO.setMerchantNo(merchantNo+"");
        List<OrderTotalReportDO> refundTotal = orderRefundreportDOMapper.selectTradeTotal(exportDO);
        //支付订单
        OrderTotalReportDO payOrder = new OrderTotalReportDO();
        payOrder.setMerchantName(refundTotal.get(0).getMerchantName());
        payOrder.setMerchantNo(refundTotal.get(0).getMerchantNo());
        List<OrderTotalReportDO> payTotal = new ArrayList<>();
        payTotal.add(payOrder);//支付订单

        this.buildExportData_list(list,payTotal,refundTotal,null);
    }

    private List<OrderTotalResDTO> buildExportData_list( List<OrderTotalResDTO> orderTotalResDTOS,List<OrderTotalReportDO> orderTotalReports,List<OrderTotalReportDO> refunds,OrderReportExportDO orderReportExportDO){
        OrderTotalReportDO finalData = new OrderTotalReportDO();
        //此为含支付订单逻辑
        for(int i =0;i<orderTotalReports.size();i++){
            OrderTotalReportDO item = orderTotalReports.get(i);
            OrderTotalResDTO res_item = buildOrderResDTO(Constant.PAYMENT_ORDER_TYPE,item);
            if(i==0){
                BeanUtils.copyProperties(item,finalData);
            }else{
                buildNeedBalance(finalData,item,true);
            }
            orderTotalResDTOS.add(res_item);
        }

        //退款订单判断
        if(!CollectionUtils.isEmpty(refunds)){
            for(int i=0;i<refunds.size();i++){
                OrderTotalReportDO item = refunds.get(i);
                OrderTotalResDTO res_item = buildOrderResDTO(Constant.REFUND_ORDER_TYPE,item);
                //结算相减
                buildNeedBalance(finalData,item,false);
                orderTotalResDTOS.add(res_item);
            }
        }else{
            //需退款为0  需结算为支付金额
            OrderTotalResDTO refundTotalResDTO = new OrderTotalResDTO();
            refundTotalResDTO.setMerchantNo(finalData.getMerchantNo()+"");
            refundTotalResDTO.setMerchantName(finalData.getMerchantName());
            refundTotalResDTO.setOrderType(Constant.REFUND_ORDER_TYPE);
            orderTotalResDTOS.add(refundTotalResDTO);
        }

        //结算订单
        finalData.setCouponType(null);
        finalData.setDistributeWay(null);
        orderTotalResDTOS.add(buildOrderResDTO(Constant.NEED_BALANCE,finalData));
        return orderTotalResDTOS;
    }


    //支付/退款订单
    private OrderTotalResDTO buildOrderResDTO(String orderType,OrderTotalReportDO orderTotalReportDO){
        OrderTotalResDTO orderTotalResDTO = new OrderTotalResDTO();
        orderTotalResDTO.setCashFreightAmount(orderTotalReportDO.getCashAndFreightAmount()+"");
        orderTotalResDTO.setCouponCount(orderTotalReportDO.getCouponCount()+"");
   //     orderTotalResDTO.setProductCouponCount(orderTotalReportDO.getProductCouponCount()+"");
        orderTotalResDTO.setFreightAmount(orderTotalReportDO.getFreightAmount()+"");
        orderTotalResDTO.setOrderType(orderType);
        orderTotalResDTO.setOrderCash(orderTotalReportDO.getOrderCash()+"");
        orderTotalResDTO.setMerchantName(orderTotalReportDO.getMerchantName());
        orderTotalResDTO.setIntegrationCount(new BigDecimal(orderTotalReportDO.getOrderIntegration()).divide(new BigDecimal(100))+"");
        orderTotalResDTO.setOrderCount(orderTotalReportDO.getTotal()+"");
        orderTotalResDTO.setProductPrice(orderTotalReportDO.getProductPrice()+"");
        orderTotalResDTO.setMerchantNo(orderTotalReportDO.getMerchantNo()+"");
        orderTotalResDTO.setCouponType(orderTotalReportDO.getCouponType());
        orderTotalResDTO.setDistributeWay(orderTotalReportDO.getDistributeWay());
        orderTotalResDTO.setDiscountAmount(orderTotalReportDO.getDiscountAmount()+"");
        return orderTotalResDTO;
    }

    /**
     * 既有支付又有退款需结算
     */
    private OrderTotalResDTO buildNeedBalance(OrderTotalReportDO orderTotalReportDO,OrderTotalReportDO refundDO){
        OrderTotalResDTO orderTotalResDTO = new OrderTotalResDTO();
        orderTotalResDTO.setCashFreightAmount(orderTotalReportDO.getCashAndFreightAmount().subtract(refundDO.getCashAndFreightAmount())+"");
        orderTotalResDTO.setCouponCount((orderTotalReportDO.getCouponCount().subtract(refundDO.getCouponCount()))+"");
        orderTotalResDTO.setProductCouponCount((orderTotalReportDO.getProductCouponCount().subtract(refundDO.getProductCouponCount()))+"");
        orderTotalResDTO.setFreightAmount(orderTotalReportDO.getFreightAmount().subtract(refundDO.getFreightAmount())+"");
        orderTotalResDTO.setOrderType(Constant.NEED_BALANCE);
        orderTotalResDTO.setOrderCash(orderTotalReportDO.getOrderCash().subtract(refundDO.getOrderCash())+"");
        orderTotalResDTO.setMerchantName(orderTotalReportDO.getMerchantName());
        orderTotalResDTO.setIntegrationCount(new BigDecimal(orderTotalReportDO.getOrderIntegration()-refundDO.getOrderIntegration()).divide(new BigDecimal(100))+"");
        orderTotalResDTO.setOrderCount((orderTotalReportDO.getTotal() + refundDO.getTotal())+"");
        orderTotalResDTO.setProductPrice(orderTotalReportDO.getProductPrice().subtract(refundDO.getProductPrice())+"");
        orderTotalResDTO.setMerchantNo(orderTotalReportDO.getMerchantNo()+"");
        return orderTotalResDTO;
    }

    private void buildNeedBalance(OrderTotalReportDO finalOrder,OrderTotalReportDO tempOrder,boolean addFlag){
        if(addFlag){
            finalOrder.setCashAndFreightAmount(finalOrder.getCashAndFreightAmount().add(tempOrder.getCashAndFreightAmount()));
            finalOrder.setCouponCount((finalOrder.getCouponCount().add(tempOrder.getCouponCount())));
            finalOrder.setProductCouponCount((finalOrder.getProductCouponCount().add(tempOrder.getProductCouponCount())));
            finalOrder.setFreightAmount(finalOrder.getFreightAmount().add(tempOrder.getFreightAmount()));
     //       finalOrder.setO(Constant.NEED_BALANCE);
            finalOrder.setOrderCash(finalOrder.getOrderCash().add(tempOrder.getOrderCash()));
            finalOrder.setMerchantName(finalOrder.getMerchantName());
            finalOrder.setOrderIntegration(finalOrder.getOrderIntegration()+tempOrder.getOrderIntegration());
            finalOrder.setTotal((finalOrder.getTotal() + tempOrder.getTotal()));
            finalOrder.setProductPrice(finalOrder.getProductPrice().add(tempOrder.getProductPrice()));
            finalOrder.setMerchantNo(finalOrder.getMerchantNo());
            finalOrder.setDiscountAmount(finalOrder.getDiscountAmount().add(tempOrder.getDiscountAmount()));
        }else{
            finalOrder.setCashAndFreightAmount(finalOrder.getCashAndFreightAmount().subtract(tempOrder.getCashAndFreightAmount()));
            finalOrder.setCouponCount((finalOrder.getCouponCount().subtract(tempOrder.getCouponCount())));
            finalOrder.setProductCouponCount((finalOrder.getProductCouponCount().subtract(tempOrder.getProductCouponCount())));
            finalOrder.setFreightAmount(finalOrder.getFreightAmount().subtract(tempOrder.getFreightAmount()));
            //       finalOrder.setO(Constant.NEED_BALANCE);
            finalOrder.setOrderCash(finalOrder.getOrderCash().subtract(tempOrder.getOrderCash()));
            finalOrder.setMerchantName(finalOrder.getMerchantName());
            finalOrder.setOrderIntegration(finalOrder.getOrderIntegration()-tempOrder.getOrderIntegration());
            finalOrder.setTotal((finalOrder.getTotal() + tempOrder.getTotal()));
            finalOrder.setProductPrice(finalOrder.getProductPrice().subtract(tempOrder.getProductPrice()));
            finalOrder.setMerchantNo(finalOrder.getMerchantNo());
            finalOrder.setDiscountAmount(finalOrder.getDiscountAmount().subtract(tempOrder.getDiscountAmount()));
        }
    }



    /**
     * 仅仅只有支付或者退款需结算
     * @param refundFlag
     * @param orderTotalReportDO
     * @return
     */
    private OrderTotalResDTO buildOrderTotalResDTO(boolean refundFlag,OrderTotalReportDO orderTotalReportDO){
        OrderTotalResDTO orderTotalResDTO = new OrderTotalResDTO();
        orderTotalResDTO.setCashFreightAmount(refundFlag && orderTotalReportDO.getCashAndFreightAmount().compareTo(BigDecimal.ZERO) > 0 ? "-"+orderTotalReportDO.getCashAndFreightAmount()+"" : orderTotalReportDO.getCashAndFreightAmount()+"");
        orderTotalResDTO.setCouponCount(refundFlag && orderTotalReportDO.getCouponCount().compareTo(BigDecimal.ZERO) == 1 ? "-"+orderTotalReportDO.getCouponCount() : orderTotalReportDO.getCouponCount()+"");
        orderTotalResDTO.setProductCouponCount(refundFlag && orderTotalReportDO.getProductCouponCount().compareTo(BigDecimal.ZERO) == 1 ? "-"+orderTotalReportDO.getProductCouponCount() : orderTotalReportDO.getProductCouponCount()+"");
        orderTotalResDTO.setFreightAmount(refundFlag && orderTotalReportDO.getFreightAmount().compareTo(BigDecimal.ZERO) > 0 ? "-"+orderTotalReportDO.getFreightAmount()+"" : orderTotalReportDO.getFreightAmount()+"");
        orderTotalResDTO.setOrderType(Constant.NEED_BALANCE);
        orderTotalResDTO.setOrderCash(refundFlag && orderTotalReportDO.getOrderCash().compareTo(BigDecimal.ZERO) > 0 ? "-"+orderTotalReportDO.getOrderCash()+"" :orderTotalReportDO.getOrderCash()+"" );
        orderTotalResDTO.setMerchantName(orderTotalReportDO.getMerchantName());
        orderTotalResDTO.setIntegrationCount(refundFlag && orderTotalReportDO.getOrderIntegration() != 0 ? "-"+ new BigDecimal(orderTotalReportDO.getOrderIntegration()).divide(new BigDecimal(100))+"" : new BigDecimal(orderTotalReportDO.getOrderIntegration()).divide(new BigDecimal(100))+"");
        orderTotalResDTO.setOrderCount(orderTotalReportDO.getTotal()+"" );
        orderTotalResDTO.setProductPrice(refundFlag && orderTotalReportDO.getProductPrice().compareTo(BigDecimal.ZERO) > 0 ? "-"+orderTotalReportDO.getProductPrice()+"": orderTotalReportDO.getProductPrice()+"");
        orderTotalResDTO.setMerchantNo(orderTotalReportDO.getMerchantNo()+"");
        return orderTotalResDTO;
    }
    private PageInfo<OrderTotalResDTO> exportTotalData(FinancialReqDTO financialReqDTO){
        if(financialReqDTO.getPageNum() != null && financialReqDTO.getPageSize() != null){
            PageHelper.startPage(financialReqDTO.getPageNum(),financialReqDTO.getPageSize());
        }
        List<OrderTotalResDTO> orderTotalResDTOS = new ArrayList<>();
        OrderReportExportDO orderReportExportDO = new OrderReportExportDO();
        BeanUtils.copyProperties(financialReqDTO,orderReportExportDO);
        List<TradeTotalDO> tradeTotalDOS = tradeTotalDOMapper.selectTradeTotalResult(orderReportExportDO);
        if(CollUtil.isNotEmpty(tradeTotalDOS)){
            tradeTotalDOS.stream().forEach(tradeTotalDO -> {
                OrderTotalResDTO orderTotalResDTO = new OrderTotalResDTO();
                BeanUtils.copyProperties(tradeTotalDO,orderTotalResDTO);
                orderTotalResDTO.setIntegrationCount(this.calcAmount(tradeTotalDO.getIntegrationCount()));
                orderTotalResDTO.setProductPrice(this.calcAmount(tradeTotalDO.getProductPrice()));
                orderTotalResDTO.setCouponCount(this.calcAmount(tradeTotalDO.getCouponCount()));
                orderTotalResDTO.setProductCouponCount(this.calcAmount(tradeTotalDO.getProductCouponCount()));
                orderTotalResDTO.setOrderCash(this.calcAmount(tradeTotalDO.getOrderCash()));
                orderTotalResDTO.setFreightAmount(this.calcAmount(tradeTotalDO.getFreightAmount()));
                orderTotalResDTO.setCashFreightAmount(this.calcAmount(tradeTotalDO.getCashFreightAmount()));
                orderTotalResDTO.setOrderType(getOrderType(tradeTotalDO.getOrderType()));
                orderTotalResDTO.setCouponType(tradeTotalDO.getCouponType());
                orderTotalResDTO.setCouponTypeDesc(getCouponType(tradeTotalDO));
                orderTotalResDTO.setDiscountAmount(tradeTotalDO.getDiscountAmount()+"");
                orderTotalResDTOS.add(orderTotalResDTO);
            });
        }
        return super.getPageInfo(tradeTotalDOS,orderTotalResDTOS);
    }
    private String getOrderType(String orderType){
        if("1".equals(orderType)) return "支付订单";
        if("2".equals(orderType)) return "退款订单";
        if("3".equals(orderType)) return "需结算";
        return "";
    }

    private String getCouponType(TradeTotalDO tradeTotal){
        if("0".equals(tradeTotal.getCouponType()) && "0".equals(tradeTotal.getDistributeWay())) return "积分兑换券";
        if("0".equals(tradeTotal.getCouponType()) && "1".equals(tradeTotal.getDistributeWay())) return "行发积分兑换券";
        if("1".equals(tradeTotal.getCouponType()) && "1".equals(tradeTotal.getDistributeWay())) return "指定商品免费兑换券";
        if("2".equals(tradeTotal.getCouponType()) && "1".equals(tradeTotal.getDistributeWay())) return "指定商品现金优惠券";
        if("4".equals(tradeTotal.getCouponType()) && "1".equals(tradeTotal.getDistributeWay())) return "指定专区现金优惠券";
        if("5".equals(tradeTotal.getCouponType()) && "1".equals(tradeTotal.getDistributeWay())) return "全场通用现金优惠券";
        return "";
    }

    private String calcAmount(String amount){
        if(StringUtils.isNoneBlank(amount) && amount.contains(".")){
            String[] strs = amount.split("\\.");
            if(StringUtils.isBlank(strs[0])){
                amount = "0"+amount;
            }else if("-".equals(strs[0])){
                amount = "-0."+strs[1];
            }
        }
        return amount;
    }
}
