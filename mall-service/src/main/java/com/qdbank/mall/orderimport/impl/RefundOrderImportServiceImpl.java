package com.qdbank.mall.orderimport.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.constant.payment.MobileRechargeStatus;
import com.qdbank.mall.mapper.order.OrderDOMapper;
import com.qdbank.mall.mapper.orderrefund.OrderRefundDOMapper;
import com.qdbank.mall.mapper.rechargeMobileFlow.RechargeMobileFlowDOMapper;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.orderrefund.OrderRefundDO;
import com.qdbank.mall.model.orderrefund.OrderRefundImportDO;
import com.qdbank.mall.model.rechargeMobileFlow.RechargeMobileFlowDO;
import com.qdbank.mall.orderimport.OrderImportService;
import com.qdbank.mall.request.orderimport.OrderImportReqDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName RefundOrderImportServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/5/27 17:04
 * @Version 1.0
 **/
@Service
@Slf4j
public class RefundOrderImportServiceImpl extends BaseServiceImpl implements OrderImportService {
    @Autowired
    private OrderRefundDOMapper orderRefundDOMapper;
    @Autowired
    private OrderDOMapper orderDOMapper;
    @Autowired
    private RechargeMobileFlowDOMapper rechargeMobileFlowDOMapper;
    @Override
    public CommonResult orderImport(OrderImportReqDTO orderImportReqDTO) {
        /**
         * 退款单号	退款单创建时间	退货完成时间	退款用户	退款状态	退款总金额
         * 退款总积分	退款理由	来源机构	来源商户	商户编号	商品类型	商品编号	商品名称
         * 商品规格	商品数量	商品金额	商品积分	关联订单号
         *
         * 退款状态 0待审核1审核通过2退款成功3审核不通过
         *
         * 退款成功 关闭  审核驳回  待审核  审核通过  退货退款申请通过
         */
        Long start = System.currentTimeMillis();
        try {
            ExcelReader reader = ExcelUtil.getReader(orderImportReqDTO.getFile().getInputStream());
            reader.addHeaderAlias("退款单号","refundSerial");
            reader.addHeaderAlias("退款单创建时间","createTime");
            reader.addHeaderAlias("退货完成时间","handleFinishTime");
            reader.addHeaderAlias("退款用户","custMobile");
            reader.addHeaderAlias("退款状态","refundStatus");
            reader.addHeaderAlias("退款总金额","refundAmount");
            reader.addHeaderAlias("退款总积分","orderIntegration");
            reader.addHeaderAlias("退款理由","reason");
            reader.addHeaderAlias("来源商户","merchantName");
            reader.addHeaderAlias("商品编号","productId");
            reader.addHeaderAlias("商品名称","productName");
            reader.addHeaderAlias("关联订单号","orderSn");
            reader.addHeaderAlias("商品金额","orderCash");
            reader.addHeaderAlias("商品积分","orderIntegration");
            reader.addHeaderAlias("商品类型","productType");
            reader.addHeaderAlias("商品数量","productCount");
            List<OrderRefundImportDO> orderDOList = reader.readAll(OrderRefundImportDO.class);
            for(OrderRefundImportDO o : orderDOList){
                if(o.getRefundSerial() == null ) continue;
                //虚拟充值退款订单入充值失败记录表
                if(o.getProductType().equals("jiayouka") || o.getProductType().equals("huafei") || o.getProductType().equals("shipinchongzhi") || o.getProductType().equals("jifenquan")){
                    RechargeMobileFlowDO rechargeMobileFlowDO = new RechargeMobileFlowDO();
                    rechargeMobileFlowDO.setRechargeMobileFolwId(super.generateId());
                    rechargeMobileFlowDO.setCreateTime(o.getCreateTime());
                    rechargeMobileFlowDO.setOrderSn(o.getOrderSn());
                    rechargeMobileFlowDO.setRechargeRemark("酷屏2");
                    rechargeMobileFlowDO.setStatus(MobileRechargeStatus.CHARGE_FAIL.code);
                    rechargeMobileFlowDOMapper.insert(rechargeMobileFlowDO);
                    OrderDO record = new OrderDO();
                    record.setOrderSn(o.getOrderSn());
                    OrderDO orderDO = orderDOMapper.selectOrderByOrderSn(record);
                    if(orderDO != null){
                        if("退款成功".equals(o.getRefundStatus())){
                            orderDO.setCloseType(3L);
                            orderDOMapper.updateByPrimaryKeySelective(orderDO);
                        }
                    }
                }else{//实物订单退款落退款订单表
                    OrderRefundDO or = new OrderRefundDO();
                    or.setRefundSerial(o.getRefundSerial());
                    or.setCreateTime(o.getCreateTime());
                    or.setHandleFinishTime(o.getHandleFinishTime());
                    or.setCustMobile(o.getCustMobile());
                    or.setRefundStatus(getRefundStatus(o.getRefundStatus()));
                    //订单金额
                   // BigDecimal orderCash = o.getOrderCash().multiply(new BigDecimal(o.getProductCount()));
                    //积分金额
                    BigDecimal integrationCash = new BigDecimal(o.getOrderIntegration()).divide(new BigDecimal(100));
                    //运费计算
                    //BigDecimal freightAmount = o.getRefundAmount().subtract(orderCash).subtract(integrationCash);
                    //freightAmount = freightAmount.compareTo(BigDecimal.ZERO) == 1 ? freightAmount : BigDecimal.ZERO;
                    or.setRefundCash(o.getRefundAmount().subtract(integrationCash));
                    //or.setFreightAmount(freightAmount);
                    //有运费：退货退款
                    or.setRefundType(1L);
                    //退款金额折算价
                    or.setRefundAmount(o.getRefundAmount());
                    or.setRefundIntegration(o.getOrderIntegration());
                    or.setReason(o.getReason());
                    or.setProductCount(o.getProductCount());
                    or.setMerchantNo(10000L);
                    or.setOrderSn(o.getOrderSn());
                    or.setCreatedBy("酷屏2");
                    or.setUpdatedBy("酷屏2");
                    or.setMerchantName("上海酷屏信息技术有限公司");
                    OrderDO record = new OrderDO();
                    record.setOrderSn(or.getOrderSn());
                    OrderDO orderDO = orderDOMapper.selectOrderByOrderSn(record);
                    if(orderDO != null){
                        or.setCustNo(orderDO.getCustNo()+"");
                        or.setCustName(orderDO.getCustName());
                        or.setProductId(orderDO.getProductId());
                        or.setIntegrationPayFlag(0L);
                        or.setProductSkuId(orderDO.getProductSkuId());
                        or.setProductType(orderDO.getProductType());
                        or.setOrderCash(orderDO.getOrderCash());
                        or.setOrderIntegration(orderDO.getOrderIntegration());
                        or.setFreightAmount(orderDO.getFreightAmount());
                        orderDO.setRefundStatus(or.getRefundStatus());
                        if("退款成功".equals(o.getRefundStatus())){
                            orderDO.setCloseType(3L);
                        }

                        orderDOMapper.updateByPrimaryKeySelective(orderDO);
                    }
                    or.setProductName(o.getProductName());
                    orderRefundDOMapper.insert(or);
                }
            }
        log.info("解析退款订单数据条数：{} 耗时：{}", CollUtil.isNotEmpty(orderDOList) ? orderDOList.size() : 0,System.currentTimeMillis() - start);
        }catch (Exception e){
            log.error("解析退款订单数据表异常:{}",e);
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(null);
    }
    //退款状态0待审核1审核通过2退款成功3退款失败
    private Long getRefundStatus(String refundStatus){
        if(refundStatus.equals("待审核") || refundStatus.equals("审核通过") || refundStatus.equals("退货退款申请通过") || refundStatus.equals("退款成功")) return 2L;
        if(refundStatus.equals("审核驳回") || refundStatus.equals("关闭")) return 3L;
        return 3L;
    }

    private Long getStatus(String refundStatus){
        if(refundStatus.equals("退款成功")) return 4L;
        return 3L;
    }
}
