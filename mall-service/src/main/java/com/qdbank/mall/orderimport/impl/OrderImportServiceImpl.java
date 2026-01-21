package com.qdbank.mall.orderimport.impl;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.coupon.AsyncInsertCouponService;
import com.qdbank.mall.mapper.order.OrderDOMapper;
import com.qdbank.mall.mapper.product.ProductDOMapper;
import com.qdbank.mall.mapper.skustock.SkustockDOMapper;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.order.OrderImportDO;
import com.qdbank.mall.model.product.ProductDO;
import com.qdbank.mall.model.skustock.SkustockDO;
import com.qdbank.mall.orderimport.OrderImportService;
import com.qdbank.mall.request.orderimport.OrderImportReqDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName OrderImportServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/5/26 18:58
 * @Version 1.0
 **/
@Service
@Slf4j
public class OrderImportServiceImpl extends BaseServiceImpl implements OrderImportService {
    @Autowired
    private OrderDOMapper orderDOMapper;
    @Autowired
    private ProductDOMapper productDOMapper;
    @Autowired
    private SkustockDOMapper skustockDOMapper;
    @Autowired
    private AsyncInsertCouponService asyncInsertCouponService;
    @Override
    public CommonResult orderImport(OrderImportReqDTO orderImportReqDTO) {
        try {
            Long start = System.currentTimeMillis();
            ExcelReader reader = ExcelUtil.getReader(orderImportReqDTO.getFile().getInputStream());
            //订单号	订单创建时间	支付完成时间	下单用户	姓名	收货地址	客户号	订单状态	订单总金额	订单总积分		来源商户	商户编号	配送费用	活动优惠	商品类型	商品编号	商品名称	商品规格	商品数量	商品金额	商品积分	关联退款订单
            //订单号	订单创建时间	支付完成时间	下单用户	姓名	收货地址	客户号	订单状态	订单总金额	订单总积分		来源商户	商户编号	配送费用	活动优惠	商品类型	商品编号	商品名称	商品规格	商品数量	商品金额	商品积分	关联退款订单
            //订单号	订单创建时间	支付完成时间	下单用户	姓名	收货地址	客户号	订单状态	订单总金额	订单总积分		来源商户	商户编号	配送费用	活动优惠	商品类型	商品编号	商品名称	商品规格	商品数量	商品金额	商品积分	关联退款订单
            reader.addHeaderAlias("订单号","orderSn");
            reader.addHeaderAlias("订单创建时间","createTime");
            reader.addHeaderAlias("支付完成时间","paymentTime");
            reader.addHeaderAlias("下单用户","custMobile");
            reader.addHeaderAlias("姓名","custName");
            reader.addHeaderAlias("收货地址","receiverDetailAddress");
            reader.addHeaderAlias("客户号","custNo");
            reader.addHeaderAlias("订单状态","status");
            reader.addHeaderAlias("订单总金额","payAmount");
            reader.addHeaderAlias("订单总积分","orderIntegration");
            reader.addHeaderAlias("来源商户","merchantName");
            reader.addHeaderAlias("商户编号","merchantNo");
            reader.addHeaderAlias("配送费用","freightAmount");
            reader.addHeaderAlias("活动优惠","couponAmount");
            reader.addHeaderAlias("商品类型","productType");
            reader.addHeaderAlias("商品编号","productId");
            reader.addHeaderAlias("商品名称","productName");
            reader.addHeaderAlias("商品数量","productCount");
            reader.addHeaderAlias("商品金额","productCash");//订单金额=商品金额-优惠价格
            reader.addHeaderAlias("商品积分","productIntegration");
            reader.addHeaderAlias("商品规格","skuAttribute");
            reader.addHeaderAlias("优惠券编号","userCouponId");
            List<OrderImportDO> orderDOList = reader.readAll(OrderImportDO.class);
            for(OrderImportDO o : orderDOList){
                if(StringUtils.isBlank(o.getOrderSn())) continue;
                OrderDO orderDO = new OrderDO();
                orderDO.setOrderId(super.generateId());
                orderDO.setOrderSn(o.getOrderSn());
                orderDO.setCreateTime(o.getCreateTime());
                orderDO.setPaymentTime(o.getPaymentTime());
                if(o.getPaymentTime() != null){
                    orderDO.setReceiveTime(o.getPaymentTime());
                }
                if(liveOrder(o.getProductType())){
                    orderDO.setRechargeMobile(o.getCustMobile());
                }else{
                    if("huafei".equals(o.getProductType())){
                        orderDO.setRechargeMobile(o.getCustMobile());
                    }
                    orderDO.setRechargeMobile(o.getCustMobile());
                    orderDO.setCustMobile(Long.valueOf(o.getCustMobile()));
                    orderDO.setReceiverName(o.getCustName());
                    orderDO.setReceiverPhone(o.getCustMobile());
                }
                if(StringUtils.isNotBlank(o.getUserCouponId())){
                    orderDO.setUserCouponId(Long.valueOf(o.getUserCouponId()));
                }
                orderDO.setCustName(o.getCustName());
                orderDO.setReceiverDetailAddress(o.getReceiverDetailAddress());
                orderDO.setCustNo(o.getCustNo());
                orderDO.setStatus(getStatus(o.getStatus()));
                if(getStatus(o.getStatus()) == 4){
                    orderDO.setCloseType(0L);
                }else {
                    orderDO.setCloseType(-1L);
                }
                if(StringUtils.isNotBlank(o.getOrderIntegration()) && !"0".equals(o.getOrderIntegration()) && !"0.00".equals(o.getOrderIntegration())){
                    if(o.getOrderIntegration().indexOf(".") > 0 ){
                        orderDO.setOrderIntegration(Long.valueOf(o.getOrderIntegration().substring(0,o.getOrderIntegration().indexOf(".") )));
                    }else{
                        orderDO.setOrderIntegration(Long.valueOf(o.getOrderIntegration()));
                    }
                }else{
                    orderDO.setOrderIntegration(0L);
                }
                BigDecimal payAmount = o.getPayAmount();
                BigDecimal integration = o.getOrderIntegration() == null ? BigDecimal.ZERO : new BigDecimal(o.getOrderIntegration()).divide(new BigDecimal(100));
                orderDO.setMerchantName("上海酷屏信息技术有限公司");
                orderDO.setMerchantNo(10000L);
                orderDO.setFreightAmount(o.getFreightAmount() == null ? BigDecimal.ZERO : o.getFreightAmount() );
                orderDO.setOrderCash(payAmount.subtract(integration).subtract(orderDO.getFreightAmount()));
                orderDO.setCouponAmount(o.getCouponAmount() == null ? BigDecimal.ZERO : o.getCouponAmount());
                orderDO.setProductType(getProductType(o.getProductType()));
                orderDO.setProductCash(o.getProductCash() == null ? BigDecimal.ZERO : o.getProductCash());
                orderDO.setProductCount(StringUtils.isNotBlank(o.getProductCount()) ? Long.valueOf(o.getProductCount()) : 0L);
                orderDO.setProductIntegration(o.getProductIntegration() == null ? 0L : o.getProductIntegration());
                orderDO.setPayAmount(o.getPayAmount());
                orderDO.setCreatedBy("酷屏2");
                orderDO.setUpdatedBy("酷屏2");
                orderDO.setDeliveryTime(o.getPaymentTime());
                orderDO.setProductPrice(orderDO.getProductCash().add(new BigDecimal(orderDO.getProductIntegration()).divide(new BigDecimal(100))));
                orderDO.setUpdateTime(o.getCreateTime());
                if(StringUtils.isNotBlank(o.getProductName())){
                    if(o.getProductName().length() > 50){
                        orderDO.setProductName(o.getProductName().substring(0,40));
                    }else{
                        orderDO.setProductName(o.getProductName());
                    }
                }else {
                    orderDO.setProductName("");
                }

                orderDO.setProductId(o.getProductId() == null ? 100031L : o.getProductId());

                if(orderDO.getOrderIntegration() > 0 && (orderDO.getOrderCash().add(orderDO.getFreightAmount()).compareTo(BigDecimal.ZERO) == 1)){
                    orderDO.setPayType(2L);
                }
                if(orderDO.getOrderIntegration() > 0  &&  (orderDO.getOrderCash().add(orderDO.getFreightAmount()).compareTo(BigDecimal.ZERO) == 0)){
                    orderDO.setPayType(0L);
                }
                if(orderDO.getOrderIntegration() == 0 && orderDO.getOrderCash().add(orderDO.getFreightAmount()).compareTo(BigDecimal.ZERO) == 1){
                    orderDO.setPayType(1L);
                }
                orderDO.setRefundStatus(-1L);
                orderDO.setNoticeStatus(1L);
                //获取积分券订单的券编号
                Long couponId = getExchangeId(o.getProductName());
                    if(couponId > 0L){
                        orderDO.setExchangeCouponId(couponId);
                    }
                //话费、加油卡、视频充值订单 关联积分兑换券
                if(StringUtils.isNotBlank(o.getUserCouponId()) && Long.valueOf(o.getUserCouponId()) > 0){
                    //说明使用了优惠券
                    orderDO.setUserCouponId(Long.valueOf(o.getUserCouponId()));
                    //指定券ID
                    orderDO.setExchangeCouponId(getCouponId(orderDO.getCouponAmount(),o.getProductType()));
                }else{
                    //实物订单直接通过优惠券进行关联
                }
                if(orderDO.getProductId() != null){//创建商品
                    ProductDO productDO = productDOMapper.selectByPrimaryKey(orderDO.getProductId());
                    if(productDO == null){
                        productDO = new ProductDO();
                        productDO.setProductId(orderDO.getProductId());
                        productDO.setPublishStatus(0L);
                        productDO.setMerchantNo(10000L);
                        productDO.setMerchantName("上海酷屏信息技术有限公司");
                        productDO.setProductName(orderDO.getProductName());
                        productDO.setCreatedBy("酷屏2");
                        productDO.setUpdatedBy("酷屏2");
                        productDO.setCreateTime(new Date());
                        productDO.setUpdateTime(new Date());
                        productDOMapper.insert(productDO);
                        SkustockDO skustockDO = new SkustockDO();
                        skustockDO.setStatus(1L);
                        skustockDO.setProductId(orderDO.getProductId());
                        skustockDO.setProductSkuId(super.generateId());
                        skustockDO.setProductCash(orderDO.getProductCash());
                        skustockDO.setProductIntegration(orderDO.getOrderIntegration());
                        skustockDO.setProductPrice(orderDO.getProductCash().add(new BigDecimal(orderDO.getOrderIntegration()).divide(new BigDecimal(100))));
                        skustockDO.setProductName(orderDO.getProductName());
                        skustockDO.setIntegrationPayFlag(0l);
                        if(getProductType(o.getProductType()) == 0){
                            skustockDO.setProductSpData("[{\"规格\":\""+o.getSkuAttribute()+"\"}]");
                        }
                        skustockDO.setCreatedBy("酷屏2");
                        skustockDO.setUpdatedBy("酷屏2");
                        skustockDOMapper.insert(skustockDO);
                        orderDO.setProductSkuId(skustockDO.getProductSkuId());
                    }else{
                        List<SkustockDO> list =  skustockDOMapper.selectByProductId(orderDO.getProductId());
                        SkustockDO skustockDO = list.get(0);
                        if(skustockDO != null){
                            orderDO.setProductSkuId(skustockDO.getProductSkuId());
                        }
                    }
                }else{
                    //积分券兑换订单无商品信息
                }
                //orderDOS.add(orderDO);
                orderDOMapper.insert(orderDO);
            }
           // asyncInsertCouponService.batchInsertOrder(orderDOS);
            log.info("解析条数：{} 总耗时：{}分钟",reader.getRowCount(),(System.currentTimeMillis() - start)/1000);
        }catch (Exception e){
            log.error("解析支付订单数据表异常:{}",e);
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(null);
            }

    /**
     * 订单状态：(根据商品类型区分）实物订单：0 待支付   1 待发货 2 已发货 3已完成  4 已关闭  话费订单：0 待支付 6 充值中  3 已完成 4 已关闭 积分券兑换订单：0 待支付 7 待使用 8 已使用 4 已关闭 5 已过期
     * @param status
     * @return
     */
    private Long getStatus(String status){
        if("已取消".equals(status)|| "关闭".equals(status) || "已关闭".equals(status)) return 4L;
        if("已完成".equals(status) || "审核驳回".equals(status) ) return 3L;
        if("待审核".equals(status) || "退款中".equals(status) || "审核通过".equals(status) || "退货退款申请通过".equals(status) || "退款成功".equals(status) || "退款失败".equals(status) || "已退款".equals(status)) return 4L;
        if("待使用".equals(status)) return 7L;
        if("已使用".equals(status)) return 8L;
        if("已过期".equals(status))return 5L;
        if("待发货".equals(status)) return 1L;
        if("待收货".equals(status)) return 2L;
        return 3L;
    }

    /**
     * 0 待审核 1 审核通过 2 退款成功 3审核不通过
     * @param status
     * @return
     */
    private Long getRefundStatus(String status){
        if( "等待退款".equals(status) || "退款中".equals(status)|| "退款失败".equals(status) || "审核通过".equals(status) || "退货退款申请通过".equals(status) || "退款成功".equals(status) ) return 2L;
        return 3L;
    }
    private Long getProductType(String productType){
        //0实物1话费充值2油卡充值3视频会员充值4积分兑换券
        if("huafei".equals(productType)) return 1L;
        if("jiayouka".equals(productType)) return 2L;
        if("shipinchongzhi".equals(productType)) return 3L;
        if("jifenquan".equals(productType)) return 4L;
        return 0L;
    }

    private Long getExchangeId(String productName){
        if("10元话费充值券".equals(productName)) return 10000L;
        if("10元油卡充值券".equals(productName)) return 10001L;
        if("15元话费充值券".equals(productName)) return   10002L;
        if("30元油卡充值券".equals(productName)) return  10003L ;
        if("3元话费充值券".equals(productName)) return   10004L;
        if("3元视频会员充值券".equals(productName)) return  10005L ;
        if("50元油卡充值券".equals(productName)) return  10006L ;
        if("5元话费充值券".equals(productName)) return   10007L;
        if("5元视频会员充值券".equals(productName)) return 10008L;
        return 0L;
    }
    private Long getCouponId(BigDecimal couponAmount,String productType){
        if("huafei".equals(productType) && couponAmount.compareTo(new BigDecimal("10")) == 0) return 10000L;
        if("huafei".equals(productType) && couponAmount.compareTo(new BigDecimal("15")) == 0) return 10002L;
        if("huafei".equals(productType) && couponAmount.compareTo(new BigDecimal("3")) == 0) return 10004L;
        if("huafei".equals(productType) && couponAmount.compareTo(new BigDecimal("5")) == 0) return 10007L;
        if("jiayouka".equals(productType) && couponAmount.compareTo(new BigDecimal("10")) == 0) return 10001L;
        if("jiayouka".equals(productType) && couponAmount.compareTo(new BigDecimal("30")) == 0) return 10003L;
        if("jiayouka".equals(productType) && couponAmount.compareTo(new BigDecimal("50")) == 0) return 10006L;
        if("shipinchongzhi".equals(productType) && couponAmount.compareTo(new BigDecimal("5")) == 0) return 10008L;
        if("shipinchongzhi".equals(productType) && couponAmount.compareTo(new BigDecimal("3")) == 0) return 10005L;
        return 10000L;
    }
    private boolean liveOrder(String productType){
        return "jiayouka".equals(productType) || "shipinchongzhi".equals(productType);
    }
}

