package com.qdbank.mall.order.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.OrderDetailServiceEnum;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.dao.coupon.CouponDao;
import com.qdbank.mall.dao.order.OrderDao;
import com.qdbank.mall.dao.prefecture.PrefectureDao;
import com.qdbank.mall.dao.refund.RefundDao;
import com.qdbank.mall.domain.submsg.OrderStatusMsgMQBO;
import com.qdbank.mall.domain.submsg.SubMsgCommonBO;
import com.qdbank.mall.download.AsyncDownloadService;
import com.qdbank.mall.enums.PayTypeEnum;
import com.qdbank.mall.enums.SubMsgTypeEnum;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.coupon.CouponDOMapper;
import com.qdbank.mall.mapper.order.OrderDOMapper;
import com.qdbank.mall.mapper.orderrefund.OrderRefundDOMapper;
import com.qdbank.mall.mapper.product.ProductDOMapper;
import com.qdbank.mall.mapper.send.SendReturnDOMapper;
import com.qdbank.mall.mapper.skustock.SkustockDOMapper;
import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.merchant.MerchantDO;
import com.qdbank.mall.model.order.*;
import com.qdbank.mall.model.orderrefund.OrderRefundDO;
import com.qdbank.mall.model.orderrefund.OrderRefundProofPicsDO;
import com.qdbank.mall.model.product.ProductInfoQueryDO;
import com.qdbank.mall.model.product.ProductSkuDO;
import com.qdbank.mall.model.send.SendReturnDO;
import com.qdbank.mall.model.send.SendReturnDOExample;
import com.qdbank.mall.model.skustock.SkustockDO;
import com.qdbank.mall.model.trade.TradeFileDataDO;
import com.qdbank.mall.order.OrderDetailService;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.replace.ReplaceService;
import com.qdbank.mall.submsg.SubMsgMqSendService;
import com.qdbank.mall.paramsconfig.ParamsConfigService;
import com.qdbank.mall.recharge.RechargeService;
import com.qdbank.mall.request.order.OrderExportReqDTO;
import com.qdbank.mall.request.order.OrderLikeQueryReqDTO;
import com.qdbank.mall.request.order.SendOrderReqDTO;
import com.qdbank.mall.request.orderimport.OrderImportReqDTO;
import com.qdbank.mall.request.send.SendReturnReqDTO;
import com.qdbank.mall.response.coupon.CouponResDTO;
import com.qdbank.mall.response.order.*;
import com.qdbank.mall.response.order.orderdetail.OrderBaseDetailResDTO;
import com.qdbank.mall.response.order.orderdetail.OrderCouponInfoResDTO;
import com.qdbank.mall.response.order.orderdetail.OrderProductInfoResDTO;
import com.qdbank.mall.response.paramsconfig.ParamsConfigResDTO;
import com.qdbank.mall.response.product.ProductResDTO;
import com.qdbank.mall.response.refund.OrderRefundListResDTO;
import com.qdbank.mall.response.refund.OrderRefundResDTO;
import com.qdbank.mall.response.skustock.SkustockResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.time.TimeService;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.util.SpringContextUtils;
import com.qdbank.mall.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.qdbank.mall.util.Constant.DECIMALFORMAT;
import static com.qdbank.mall.util.Constant.SUB_MSG_PREFIX;

/**
 * OrderServiceImpl
 *
 * @author shaoshihang
 * @date 2021/3/8 11:36
 * @since 1.0.0
 */
@Service
@Slf4j
@RefreshScope
public class OrderServiceImpl extends BaseServiceImpl implements OrderService {

    @Resource
    private OrderDOMapper orderDOMapper;
    @Qualifier("cmsAdminServiceImpl")
    @Autowired
    private UmsAdminService cmsAdminService;
    @Autowired
    OrderDao orderDao;

    @Autowired
    PrefectureDao prefectureDao;

    @Autowired
    CouponDao couponDao;

    @Autowired
    RefundDao refundDao;

    @Autowired
    private ProductDOMapper productDOMapper;

    @Autowired
    private SkustockDOMapper skustockDOMapper;

    @Autowired
    private CouponDOMapper couponDOMapper;
    @Autowired
    private TimeService timeService;
    @Autowired
    private RechargeService rechargeService;

    @Resource
    private OrderRefundDOMapper orderRefundDOMapper;
    @Autowired
    private AsyncDownloadService asyncDownloadService;
    @Autowired
    private ParamsConfigService paramsConfigService;
    private static final String SEND_RENTURN_TIME = "sendReturnTime";
    @Autowired
    private SendReturnDOMapper sendReturnDOMapper;

    @Autowired
    private SubMsgMqSendService subMsgMqSendService;
    @Autowired
    private ReplaceService replaceService;
    @Override
    public PageInfo<OrderResDTO> list(OrderLikeQueryReqDTO orderLikeQueryReqDTO) {
        List<OrderResDTO> resDTOS = new ArrayList<>();
        if (orderLikeQueryReqDTO.getPageNum() != null && orderLikeQueryReqDTO.getPageSize() != null) {
            PageHelper.startPage(orderLikeQueryReqDTO.getPageNum(), orderLikeQueryReqDTO.getPageSize());
        }
        OrderLikeQueryDO orderDO = new OrderLikeQueryDO();
        BeanUtils.copyProperties(orderLikeQueryReqDTO, orderDO);
        timeService.setTime(orderDO);
        List<OrderListDO> orderDOList = orderDOMapper.selectList(orderDO);
        ParamsConfigResDTO paramsConfigResDTO = paramsConfigService.queryByParamName(SEND_RENTURN_TIME);
        for (OrderListDO order : orderDOList) {
            OrderResDTO resDTO = new OrderResDTO();
            BeanUtils.copyProperties(order, resDTO);
            resDTO.setSpData(order.getSkustockDO() == null ? "" : order.getSkustockDO().getProductSpData());
            resDTO.setMerchantName(order.getMerchantName());
            //列表图片展示商品规格图片
            SkustockDO skustockDO = skustockDOMapper.selectByPrimaryKey(resDTO.getProductSkuId());
            if (skustockDO != null && StringUtils.isNotBlank(skustockDO.getSkuPicUrl())) {
                resDTO.setPicUrl(orderLikeQueryReqDTO.getMerchantNo() != null ? skustockDO.getSkuPicUrl() : replaceService.replaceUrl(skustockDO.getSkuPicUrl()));
            }
            //发货7天后时间是否大于当前时间
            if (order.getProductType() == 0L && order.getStatus() == 2L && this.getSendReturnFlag(paramsConfigResDTO, order.getDeliveryTime())) {
                resDTO.setReturnFlag(1);
            }
            // 0\1\2 获取最新退款流水
            if (order.getProductType().equals(ProductEnum.PAYMENT_IN_KIND.productType)) {
                Long refundStatus = order.getRefundStatus();
                if (com.qdbank.mall.constent.payment.RefundStatausEnum.PREPARE_APPROVE.refundStatus.equals(refundStatus) ||
                        com.qdbank.mall.constent.payment.RefundStatausEnum.APPROVE_YES.refundStatus.equals(refundStatus) ||
                        com.qdbank.mall.constent.payment.RefundStatausEnum.REFUND.refundStatus.equals(refundStatus)
                ) {
                    OrderRefundDO refund = refundDao.qryNewOrderRefundByOrderSn(order.getOrderSn());
                    order.setRefundSerialNo(refund.getRefundSerial());
                    resDTO.setRefundSerialNo(refund.getRefundSerial());
                }
            }
            resDTOS.add(resDTO);
        }
        return super.getPageInfo(orderDOList, resDTOS);
    }

    @Override
    public CommonResult excelExport(OrderExportReqDTO orderExportReqDTO) {
        if (TimeUtil.judgeThreeMonths(orderExportReqDTO.getStartTime(), orderExportReqDTO.getEndTime())) {
            throw new ApiException(ResultCode.THREE_MONTHS_EXCEL_EXPORT);
        }
        OrderLikeQueryDO orderDO = new OrderLikeQueryDO();
        BeanUtils.copyProperties(orderExportReqDTO, orderDO);
        timeService.setTime(orderDO);
        List<OrderExportDO> orderDOList = orderDOMapper.selectListByTime(orderDO);
        if (CollUtil.isEmpty(orderDOList)) throw new ApiException(ResultCode.DATA_EMPTY);
        String fileName = Constant.ORDER_FILE_NAME + (DateUtil.format(orderExportReqDTO.getStartTime(), DatePattern.NORM_DATE_PATTERN)) + "-" + (DateUtil.format(orderExportReqDTO.getEndTime(), DatePattern.NORM_DATE_PATTERN));
        asyncDownloadService.checkFileExist(fileName, orderExportReqDTO.getCreatedBy());
        Long downloadId = super.generateId();
        asyncDownloadService.insertDownload(fileName, orderExportReqDTO.getMerchantNo(), orderExportReqDTO.getCreatedBy(), 0L, downloadId);
        orderExportReqDTO.setDownloadId(downloadId);
        asyncDownloadService.asyncDownloadOrder(orderDOList, orderExportReqDTO);
        return CommonResult.success(null);
    }

    @Override
    public int sendOrder(SendOrderReqDTO sendOrderReqDTO) {
        OrderDO orignOrder = orderDOMapper.selectByPrimaryKey(sendOrderReqDTO.getOrderId());
        if (orignOrder == null) throw new ApiException(ResultCode.ORDER_NOT_EXIST);
        //更新订单表
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(sendOrderReqDTO, orderDO);
        orderDO.setUpdateTime(new Date());
        orderDO.setStatus(2L);
        orderDO.setDeliveryTime(new Date());
        int count = orderDOMapper.updateByOrderId(orderDO);
        if (count > 0) {
            //发送订阅消息kafka
            //sendOrderSubMsgMq(orignOrder.getOrderSn(), sendOrderReqDTO.getDeliveryCompanyName(), sendOrderReqDTO.getDeliverySn(), orignOrder);
            this.insertSendReturnOrder(orignOrder.getOrderSn(), sendOrderReqDTO.getDeliveryCompanyName(), sendOrderReqDTO.getDeliverySn(), 0L, "", orderDO.getDeliveryTime());
        }
        return count;
    }

    @Override
    public OrderStatusResDTO orderStatus() {
        MerchantDO merchantDO = cmsAdminService.queryUserNameAndMerchantNo();
        OrderStatusResDTO orderStatusResDTO = new OrderStatusResDTO();
        OrderDO orderDO = new OrderDO();
        orderDO.setMerchantNo(merchantDO.getMerchantNo());
        List<Long> status = orderDOMapper.selectStatusCount(orderDO);
        orderStatusResDTO.setConsignment(status.get(1));
        orderStatusResDTO.setReviewed(status.get(0));
        return orderStatusResDTO;
    }


    @Override
    public RealOrderDetailResDTO realRefundDetail(String id,boolean isoms) {
        OrderAndRefundDetailDO order = orderDao.qryRefundOrder(id);
        if (order == null) {
            throw new ApiException(ResultCode.USER_ORDER_HAS_NOT_FOUND);
        }

        //查询商品规格信息--肯定存在
        long productSkuId = order.getProductSkuId();
        ProductInfoQueryDO query = new ProductInfoQueryDO();
        query.setProductId(order.getProductId());
        query.setProductSkuId(productSkuId);
        List<ProductSkuDO> skus = prefectureDao.selectProductSkuInfo(query);
        //商户和规格--肯定存在
        ProductSkuDO sku = skus.get(0);

        //商品图片信息显示为当前订单的商品规格图片
        SkustockDO skustockDO = skustockDOMapper.selectByPrimaryKey(productSkuId);
        if (skustockDO != null && StringUtils.isNotBlank(skustockDO.getSkuPicUrl())) {
            sku.setMailPicUrl(isoms ? replaceService.replaceUrl(skustockDO.getSkuPicUrl())  : skustockDO.getSkuPicUrl());
        }
        ProductResDTO productResDTO = new ProductResDTO();
        BeanUtils.copyProperties(sku, productResDTO);

        SkustockDO stock = sku.getSkustocks().get(0);
        SkustockResDTO skustockResDTO = new SkustockResDTO();
        BeanUtils.copyProperties(stock, skustockResDTO);

        //查询券信息--不一定存在
        Long userCouponId = order.getUserCouponId();
        CouponResDTO couponResDTO = null;
        if (userCouponId != null && userCouponId > 0) {
            CouponDO couponDO = couponDao.qryCouponDetailByUserCouponId(userCouponId);
            //优惠券处理
            if (couponDO != null) {
                couponResDTO = new CouponResDTO();
                BeanUtils.copyProperties(couponDO, couponResDTO);
            }
        }

        //退款
        List<OrderRefundListResDTO> refunds = new ArrayList<>();

        OrderRefundListResDTO dto = new OrderRefundListResDTO();
        BeanUtils.copyProperties(order, dto);
        //退款订单状态
        dto.setRefundStatus(order.getRefundStatus1());

        //处理退款订单上传凭证图片
        List<OrderRefundProofPicsDO> orderRefundProofPicsDOList = orderRefundDOMapper.selectProofPicsByRefundSn(dto.getRefundSerialNo() + "");
        if (CollectionUtil.isNotEmpty(orderRefundProofPicsDOList)) {
            if(isoms){
                for(OrderRefundProofPicsDO orderRefundProofPicsDO : orderRefundProofPicsDOList){
                    orderRefundProofPicsDO.setProofPicUrl(replaceService.replaceUrl(orderRefundProofPicsDO.getProofPicUrl()));
                }
            }
            dto.setProofPics(JSON.toJSONString(orderRefundProofPicsDOList));
        }

        refunds.add(dto);

        //商品类型：0实物1话费充值2油卡充值3视频会员充值4积分兑换券
        RealOrderDetailResDTO realOrderDetailResDTO = new RealOrderDetailResDTO();
        BeanUtils.copyProperties(order, realOrderDetailResDTO);
        realOrderDetailResDTO.setIntegralCouponInfo(couponResDTO);
        List<SkustockResDTO> list = new ArrayList<>();
        list.add(skustockResDTO);
        productResDTO.setSkustocks(list);
        realOrderDetailResDTO.setProductInfo(productResDTO);
        realOrderDetailResDTO.setOrderRefundResDTOs(refunds);
        return realOrderDetailResDTO;
    }

    @Override
    public RealOrderDetailResDTO realDetail(Long id) {
        List<OrderAndRefundDetailDO> list = orderDao.qryRefundOrder(id + "", null);
        if (CollectionUtils.isEmpty(list)) {
            throw new ApiException(ResultCode.USER_ORDER_HAS_NOT_FOUND);
        }

        OrderAndRefundDetailDO order = list.get(0);
        //查询商品规格信息--肯定存在
        ProductInfoQueryDO query = new ProductInfoQueryDO();
        query.setProductId(order.getProductId());
        query.setProductSkuId(order.getProductSkuId());
        List<ProductSkuDO> skus = prefectureDao.selectProductSkuInfo(query);

        //商户和规格--肯定存在
        ProductSkuDO sku = skus.get(0);

        //商品图片信息显示为当前订单的商品规格图片
        SkustockDO skustockDO = skustockDOMapper.selectByPrimaryKey(order.getProductSkuId());
        if (skustockDO != null) {
            sku.setMailPicUrl(skustockDO.getSkuPicUrl());
        }
        ProductResDTO productResDTO = new ProductResDTO();
        BeanUtils.copyProperties(sku, productResDTO);

        if (CollectionUtil.isNotEmpty(sku.getSkustocks())) {
            List<SkustockResDTO> skustocks = new ArrayList<>();
            sku.getSkustocks().stream().forEach(item -> {
                SkustockResDTO skustockResDTO = new SkustockResDTO();
                BeanUtils.copyProperties(item, skustockResDTO);
                skustocks.add(skustockResDTO);
            });
            productResDTO.setSkustocks(skustocks);
        }
        //查询券信息--不一定存在
        Long userCouponId = order.getUserCouponId();
        CouponResDTO couponResDTO = null;
        if (userCouponId != null && userCouponId > 0) {
            CouponDO couponDO = couponDao.qryCouponDetailByUserCouponId(userCouponId);
            //优惠券处理
            if (couponDO != null) {
                couponResDTO = new CouponResDTO();
                BeanUtils.copyProperties(couponDO, couponResDTO);
            }
        }

        //退款
        List<OrderRefundListResDTO> refunds = null;
        boolean createList = false;
        for (OrderAndRefundDetailDO item : list) {
            if (item.getRefundSerial() != null) {
                if (!createList) {
                    refunds = new ArrayList<>();
                    createList = true;
                }
                OrderRefundListResDTO dto = new OrderRefundListResDTO();
                BeanUtils.copyProperties(item, dto);
                dto.setRefundStatus(item.getRefundStatus1());
                refunds.add(dto);
            }
        }

        //商品类型：0实物1话费充值2油卡充值3视频会员充值4积分兑换券
        RealOrderDetailResDTO realOrderDetailResDTO = new RealOrderDetailResDTO();
        BeanUtils.copyProperties(order, realOrderDetailResDTO);
        realOrderDetailResDTO.setIntegralCouponInfo(couponResDTO);
        realOrderDetailResDTO.setProductInfo(productResDTO);
        realOrderDetailResDTO.setOrderRefundResDTOs(refunds);
        if (order.getProductType() == 0L && this.getSendReturnDataFlag(order.getOrderSn())) {
            realOrderDetailResDTO.setSendReturnDataFlag(1);
        }
        return realOrderDetailResDTO;
    }


    @Override
    public MobileRechargeOrderDetailResDTO mobileDetail(Long id) {
        List<OrderAndRefundDetailDO> list = orderDao.qryRefundOrder(id + "", null);
        //    OrderDO order = orderDao.qryOrderByOrderId(id);
        if (CollectionUtils.isEmpty(list)) {
            throw new ApiException(ResultCode.USER_ORDER_HAS_NOT_FOUND);
        }

        OrderAndRefundDetailDO order = list.get(0);

        //查询券信息--不一定存在
        Long userCouponId = order.getUserCouponId();
        CouponResDTO couponResDTO = null;
        if (userCouponId != null && userCouponId > 0) {
            CouponDO couponDO = couponDao.qryCouponDetailByUserCouponId(userCouponId);
            //优惠券处理
            if (couponDO != null) {
                couponResDTO = new CouponResDTO();
                BeanUtils.copyProperties(couponDO, couponResDTO);
            }
        }

        //退款
        List<OrderRefundResDTO> refunds = null;
        boolean createList = false;
        for (OrderAndRefundDetailDO item : list) {
            if (item.getRefundSerial() != null) {
                if (!createList) {
                    refunds = new ArrayList<>();
                }
                OrderRefundResDTO dto = new OrderRefundResDTO();
                BeanUtils.copyProperties(item, dto);
                refunds.add(dto);
            }
        }
        ;

        MobileRechargeOrderDetailResDTO mobileRechargeOrderDetailResDTO = new MobileRechargeOrderDetailResDTO();
        //优惠券逻辑
        BeanUtils.copyProperties(order, mobileRechargeOrderDetailResDTO);
        mobileRechargeOrderDetailResDTO.setRechargeMobile(order.getRechargeMobile() + "");
        mobileRechargeOrderDetailResDTO.setIntegralCouponInfo(couponResDTO);

        BeanUtils.copyProperties(order, mobileRechargeOrderDetailResDTO);
        //充值信息
        List<RechargeStatusDetailResDTO> rechargeStatus = rechargeService.getRechargeStatus(order.getOrderSn());
        mobileRechargeOrderDetailResDTO.setRechargeStatus(rechargeStatus);

        //商品信息
        OrderProductInfoResDTO orderProductInfoResDTO = new OrderProductInfoResDTO(); //orderService.getProductInfo(orderDO);
        orderProductInfoResDTO.setProductName(order.getMobileAddress());
        orderProductInfoResDTO.setProductSpData(order.getMobileAddress());
        mobileRechargeOrderDetailResDTO.setProductInfoResDTO(orderProductInfoResDTO);

        return mobileRechargeOrderDetailResDTO;
    }


    @Override
    public List<TradeFileDataDO> selectByPaymentTime(Date date) {
        Date start = TimeUtil.dateStartChange(DateUtil.offsetDay(date, -2));
        Date end = TimeUtil.dateStartChange(DateUtil.offsetDay(date, -1));
        OrderDOExample orderDOExample = new OrderDOExample();
        OrderDOExample.Criteria criteria = orderDOExample.createCriteria();
        criteria.andPaymentTimeGreaterThanOrEqualTo(start);
        criteria.andPaymentTimeLessThan(end);
        List<OrderDO> list = orderDOMapper.selectByExample(orderDOExample);
        List<TradeFileDataDO> tradeFileDataDOS = new ArrayList<>();
        for (OrderDO orderDO : list) {
            TradeFileDataDO tradeFileDataDO = new TradeFileDataDO();
            tradeFileDataDO.setAdvicePrice(BigDecimal.ZERO);
            tradeFileDataDO.setCreateTime(date);
            tradeFileDataDO.setCustNo(orderDO.getCustNo() + "");
            tradeFileDataDO.setExpendAmt(orderDO.getOrderCash().add(orderDO.getFreightAmount() == null ? BigDecimal.ZERO : orderDO.getFreightAmount()));
            tradeFileDataDO.setExpendPoint(orderDO.getOrderIntegration() + "");
            tradeFileDataDO.setItemId(orderDO.getProductId() + "");
            tradeFileDataDO.setMerCode(orderDO.getMerchantNo() + "");
            tradeFileDataDO.setMerName(orderDO.getMerchantName());
            BigDecimal orderAmt = (orderDO.getOrderCash() == null ? new BigDecimal(orderDO.getOrderIntegration().doubleValue() / 100 + "") : orderDO.getOrderCash().add(new BigDecimal(orderDO.getOrderIntegration().doubleValue() / 100 + ""))).add(orderDO.getFreightAmount() == null ? BigDecimal.ZERO : orderDO.getFreightAmount());
            tradeFileDataDO.setOrderAmt(orderAmt);
            tradeFileDataDO.setOrderSn(orderDO.getOrderSn());
            tradeFileDataDO.setOrderStatus("S");
            tradeFileDataDO.setTxDt(DateUtil.format(DateUtil.offsetDay(date, -1), DatePattern.PURE_DATE_PATTERN));
            tradeFileDataDO.setTxnDate(DateUtil.format(orderDO.getPaymentTime(), DatePattern.PURE_DATE_PATTERN));
            tradeFileDataDO.setTxnTime(DateUtil.format(orderDO.getPaymentTime(), DatePattern.PURE_TIME_PATTERN));
            tradeFileDataDO.setPaymentMethod(PayTypeEnum.getChangeType(orderDO.getPayType()));
            tradeFileDataDO.setPointSetMark(orderDO.getIntegrationPayFlag() == 0 ? "N" : "Y");
            tradeFileDataDO.setId(super.generateId());
            tradeFileDataDO.setOrg("000064500000");//青岛银行机构号固定值
            tradeFileDataDOS.add(tradeFileDataDO);
        }
        return tradeFileDataDOS;
    }


    @Override
    public OrderProductInfoResDTO getProductInfo(OrderDO orderDO) {
        ProductInfoQueryDO productInfoQueryDO = new ProductInfoQueryDO();
        productInfoQueryDO.setProductId(orderDO.getProductId());
        productInfoQueryDO.setProductSkuId(orderDO.getProductSkuId());
        List<ProductSkuDO> productSkuDOS = productDOMapper.selectProductSkuInfo(productInfoQueryDO);
        OrderProductInfoResDTO productInfoResDTO = new OrderProductInfoResDTO();
        if (CollUtil.isNotEmpty(productSkuDOS)) {
            ProductSkuDO productSkuDO = productSkuDOS.get(0);
            BeanUtils.copyProperties(productSkuDO, productInfoResDTO);
            SkustockDO skustockDO = productSkuDO.getSkustocks().get(0);
            if (skustockDO != null) {
                productInfoResDTO.setProductSpData(skustockDO.getProductSpData());
                productInfoResDTO.setProductCash(skustockDO.getProductCash());
                productInfoResDTO.setProductIntegration(skustockDO.getProductIntegration());
            }
        }
        return productInfoResDTO;
    }

    @Override
    public OrderBaseDetailResDTO orderDetail(Long id) {
        OrderDO orderDO = orderDOMapper.selectByPrimaryKey(id);
        if (orderDO == null) throw new ApiException(ResultCode.ORDER_NOT_EXIST);
        Long productType = orderDO.getProductType();
        if (productType != 0 && productType != 1 && productType != 4) {
            productType = 1L;
        }
        OrderDetailService orderDetailService = SpringContextUtils.getBean(OrderDetailServiceEnum.getServiceName(productType), OrderDetailService.class);
        if (orderDetailService == null) throw new ApiException(ResultCode.TRADE_TYPE_ERROR);
        OrderBaseDetailResDTO orderBaseDetailResDTO = orderDetailService.orderDetail(orderDO);
        if (orderDO.getProductType() == 0L) {
            if (this.getSendReturnDataFlag(orderDO.getOrderSn())) {
                orderBaseDetailResDTO.setSendReturnDataFlag(1);
            }
        }
        return orderBaseDetailResDTO;
    }

    @Override
    public OrderCouponInfoResDTO getCouponInfoByUserCouponId(Long exchangeCouponId) {
        //优惠信息
        CouponDO couponDO = couponDOMapper.qryCouponDetailByUserCouponId(exchangeCouponId);
        OrderCouponInfoResDTO couponResDTO = new OrderCouponInfoResDTO();
        if (couponDO != null) {
            BeanUtils.copyProperties(couponDO, couponResDTO);
        }
        return couponResDTO;
    }

    @Override
    public OrderCouponInfoResDTO getCouponInfo(Long exchangeCouponId) {
        //优惠信息
        CouponDO couponDO = couponDOMapper.selectByPrimaryKey(exchangeCouponId);
        OrderCouponInfoResDTO couponResDTO = new OrderCouponInfoResDTO();
        if (couponDO != null) {
            BeanUtils.copyProperties(couponDO, couponResDTO);
        }
        return couponResDTO;
    }

    @Override
    @Transactional
    public BatchSendOrderResDTO batchSendOrder(OrderImportReqDTO orderImportReqDTO) {
        MerchantDO merchantDO = cmsAdminService.queryUserNameAndMerchantNo();
        BatchSendOrderResDTO batchSendOrderResDTO = new BatchSendOrderResDTO();
        try {
            checkFileType(orderImportReqDTO.getFile().getOriginalFilename());
            checkFileSize(orderImportReqDTO.getFile().getSize(), 5, "M");
            Long start = System.currentTimeMillis();
            ExcelReader reader = ExcelUtil.getReader(orderImportReqDTO.getFile().getInputStream());
            reader.addHeaderAlias("订单编号", "orderSn");
            reader.addHeaderAlias("物流公司", "deliveryCompanyName");
            reader.addHeaderAlias("物流单号", "deliverySn");
            List<OrderImportDO> orderDOList = reader.readAll(OrderImportDO.class);
            if (CollUtil.isEmpty(orderDOList)) throw new ApiException(ResultCode.FILE_EMPTY);
            if (orderDOList.size() > 1000) throw new ApiException(ResultCode.ORDER_SIZE_OUT);
            for (OrderImportDO o : orderDOList) {
                if (StringUtils.isEmpty(o.getOrderSn()) || StringUtils.isEmpty(o.getDeliveryCompanyName()) || StringUtils.isEmpty(o.getDeliverySn()))
                    throw new ApiException(ResultCode.FILE_EMPTY);
                o.setOrderSn(o.getOrderSn().replaceAll(" ", ""));
                o.setDeliverySn(o.getDeliverySn().replaceAll(" ", ""));
                //checkOrderStart(o.getOrderSn());
                checkCompany(o.getDeliveryCompanyName());
                checkCompanySn(o.getDeliverySn());

            }
            List<String> orderSns = orderDOList.stream().map(OrderImportDO::getOrderSn).distinct().collect(Collectors.toList());
            List<String> companySns = orderDOList.stream().map(OrderImportDO::getDeliverySn).distinct().collect(Collectors.toList());
            List<Integer> successCount = new ArrayList<>();
            List<Integer> failCount = new ArrayList<>();
            if (orderSns.size() != orderDOList.size() || companySns.size() != orderDOList.size())
                throw new ApiException(ResultCode.COUPON_FILE_EXIST_REPEATE_DATE);
            orderDOList.forEach(orderImportDO -> {
                orderImportDO.setMerchantNo(merchantDO.getMerchantNo() + "");
                int count = orderDOMapper.updateSendOrderByOrderSn(orderImportDO);
                if (count == 1) {
                    //发送订阅消息kafka
                    OrderDO orderDO = new OrderDO();
                    orderDO.setOrderSn(orderImportDO.getOrderSn());
                    //OrderDO orignOrder = orderDOMapper.selectOrderByOrderSn(orderDO);
                    //sendOrderSubMsgMq(orderImportDO.getOrderSn(), orderImportDO.getDeliveryCompanyName(), orderImportDO.getDeliverySn(), orignOrder);

                    successCount.add(count);
                    this.insertSendReturnOrder(orderImportDO.getOrderSn(), orderImportDO.getDeliveryCompanyName(), orderImportDO.getDeliverySn(), 0L, "", new Date());
                }
                if (count == 0) failCount.add(count);
            });
            batchSendOrderResDTO.setSuccessCount(CollUtil.isEmpty(successCount) ? 0L : Long.valueOf(successCount.size()));
            batchSendOrderResDTO.setFailCount(CollUtil.isEmpty(failCount) ? 0L : Long.valueOf(failCount.size()));
            log.info("解析条数：{} 总耗时：{}分钟", reader.getRowCount(), (System.currentTimeMillis() - start) / 1000);
           // if (CollUtil.isNotEmpty(failCount)) throw new ApiException(ResultCode.ORDER_BATCH_SEND_ERROR);
        } catch (ApiException apiException) {
            throw apiException;
        } catch (Exception e) {
            log.error("解析批量发货订单数据异常:{}", e);
            throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
        }
        return batchSendOrderResDTO;
    }

    @Override
    public int sendReturnOrder(SendReturnReqDTO sendOrderReqDTO) {
        OrderDO orignrderDO = orderDOMapper.selectByPrimaryKey(sendOrderReqDTO.getOrderId());
        if (orignrderDO == null) throw new ApiException(ResultCode.ORDER_NOT_EXIST);
        ParamsConfigResDTO paramsConfigResDTO = paramsConfigService.queryByParamName(SEND_RENTURN_TIME);
        if (orignrderDO.getProductType() != 0L || orignrderDO.getStatus() != 2L || !this.getSendReturnFlag(paramsConfigResDTO, orignrderDO.getDeliveryTime())) {
            throw new ApiException(ResultCode.SEND_RETURN_OUT);
        }
        //更新订单表
        OrderDO orderDO = new OrderDO();
        orderDO.setUpdateTime(new Date());
        orderDO.setStatus(1L);
        orderDO.setDeliveryTime(null);
        orderDO.setDeliveryCompanyName("");
        orderDO.setDeliverySn("");
        orderDO.setOrderId(sendOrderReqDTO.getOrderId());
        int count = orderDOMapper.updateStatusByOrderId(orderDO);
        if (count > 0) {
            this.insertSendReturnOrder(orignrderDO.getOrderSn(), "", "", 1L, sendOrderReqDTO.getReturnReason(), new Date());
        }
        return count;
    }

    @Override
    public int insertHisoryOrder() {
        String date = (DateUtil.thisYear()-1)+DateUtil.today().substring(4);
        orderDOMapper.insertHistoryOrder(date);
        List<OrderDO> orderDOList = orderDOMapper.selectHistoryOrders(date);
        this.updateOrderList(orderDOList);
        return 0;
    }

    @Override
    public int updateHistoryOrder() {
       //orderDOMapper.insertHistoryBak();
        List<OrderDO> orderDOList = orderDOMapper.selectHistoryBak();
        this.updateOrderList(orderDOList);
        return 0;
    }

    private void updateOrderList(List<OrderDO> orderDOList){
        if(CollUtil.isNotEmpty(orderDOList)){
            orderDOList.stream().forEach(orderDO -> {
                if(StringUtils.isNotBlank(orderDO.getReceiverPhone()) && !orderDO.getReceiverPhone().contains("*")){
                    orderDO.setReceiverPhone(this.getAcctNoCodeValue(orderDO.getReceiverPhone()));
                }
                if(StringUtils.isNotBlank(orderDO.getReceiverName()) && !orderDO.getReceiverName().contains("*")){
                    orderDO.setReceiverName(this.getReceiveNameCodeValue(orderDO.getReceiverName()));
                }
                if(StringUtils.isNotBlank(orderDO.getReceiverDetailAddress()) && !orderDO.getReceiverDetailAddress().contains("*")){
                    orderDO.setReceiverDetailAddress(this.getDetailAddressCodeValue(orderDO.getReceiverDetailAddress()));
                }
                if(StringUtils.isNotBlank(orderDO.getRechargeMobile()) && !orderDO.getRechargeMobile().contains("*")){
                    orderDO.setRechargeAccNo(this.getAcctNoCodeValue(orderDO.getRechargeMobile()+""));
                }
                try {
                    orderDOMapper.updateUserInfo(orderDO);
                }catch (Exception e){
                    log.error("更新历史数据失败：订单号：{},异常:{}",orderDO.getOrderSn(),e);
                }

            });
        }
    }

    private void checkOrderStart(String orderSN) {
        if (!orderSN.startsWith("QD")) throw new ApiException(ResultCode.ORDER_SN_START_ERROR);
    }

    private void checkCompany(String companyName) {
        Pattern pattern1 = Pattern.compile("^[a-z0-9A-Z\\u4e00-\\u9fa5]{1,10}$");
        Matcher matcher1 = pattern1.matcher(companyName);
        if (!matcher1.find()) throw new ApiException(ResultCode.COMPANY_NAME_ERROR);
    }

    private void checkCompanySn(String companySn) {
        Pattern pattern2 = Pattern.compile("^[A-Za-z\\d]{1,30}$");
        Matcher matcher2 = pattern2.matcher(companySn);
        if (!matcher2.find()) throw new ApiException(ResultCode.COMPANY_SN_ERROR);
    }

    /**
     * 判断订单发货7天后时间和当前时间进行比较
     *
     * @param deliveryTime
     * @return
     */
    private boolean getSendReturnFlag(ParamsConfigResDTO paramsConfigResDTO, Date deliveryTime) {
        if (deliveryTime == null) return false;
        //发货7天后时间是否大于当前时间
        boolean flag = DateUtil.offsetDay(deliveryTime, Integer.valueOf(paramsConfigResDTO.getParamValue())).isAfter(new Date());
        log.info("参数值:{} ,发货时间：{}，撤销发货标识:{}", JSON.toJSONString(paramsConfigResDTO), deliveryTime, flag);
        return flag;
    }

    private void insertSendReturnOrder(String orderSN, String deliveryCompanyName, String deliverySn, Long status, String returnReason, Date deliveryTime) {
        SendReturnDO sendReturnDO = new SendReturnDO();
        sendReturnDO.setId(super.generateId());
        sendReturnDO.setCreateTime(deliveryTime);
        sendReturnDO.setDeliveryCompanyName(deliveryCompanyName);
        sendReturnDO.setDeliverySn(deliverySn);
        sendReturnDO.setOrderSn(orderSN);
        sendReturnDO.setReturnStatus(status);
        sendReturnDO.setUpdateTime(new Date());
        sendReturnDO.setReturnReason(returnReason);
        sendReturnDOMapper.insert(sendReturnDO);
    }

    private boolean getSendReturnDataFlag(String orderSn) {
        SendReturnDOExample sendReturnDOExample = new SendReturnDOExample();
        sendReturnDOExample.createCriteria().andOrderSnEqualTo(orderSn);
        List<SendReturnDO> sendReturnDOList = sendReturnDOMapper.selectByExample(sendReturnDOExample);
        return CollectionUtil.isNotEmpty(sendReturnDOList) && sendReturnDOList.size() > 1;
    }

    /**
     * 发送订单状态提醒kafka消息
     *
     * @param orderSn 订单号
     * @param deliveryCompanyName 物流公司
     * @param deliverySn 物流编号
     * @param orignOrder 订单原始信息
     * @return 发送结果 1:成功 0:失败
     */
//    private int sendOrderSubMsgMq(String orderSn, String deliveryCompanyName, String deliverySn, OrderDO orignOrder) {
//        SubMsgCommonBO subMsgCommonBO = assembleMQBO(orignOrder);
//        OrderStatusMsgMQBO orderStatusMsgMqBO = (OrderStatusMsgMQBO)subMsgCommonBO.getContent();
//        orderStatusMsgMqBO.setOrderSn(orderSn);
//        orderStatusMsgMqBO.setServiceName(deliveryCompanyName + "-" + deliverySn);
//        return subMsgMqSendService.sendSubMsgMq(subMsgCommonBO);
//    }

    /**
     * 组装mq基础信息
     * @param orignOrder    原始订单信息
     * @return  mq基础信息
     */
    private SubMsgCommonBO assembleMQBO(OrderDO orignOrder) {
        SubMsgCommonBO subMsgCommonBO = new SubMsgCommonBO();
        subMsgCommonBO.setMsgCode(SUB_MSG_PREFIX + super.generateId());
        subMsgCommonBO.setType(SubMsgTypeEnum.ORDER_STATUS_MSG.getCode());
        subMsgCommonBO.setCustNo(orignOrder.getCustNo() + "");

        OrderStatusMsgMQBO orderStatusMsgMqBo = new OrderStatusMsgMQBO();
        orderStatusMsgMqBo.setTitle("订单状态提醒");
        orderStatusMsgMqBo.setOrderStatusDesc("已发货");

        //订单金额(实付款)
        BigDecimal orderCash = orignOrder.getOrderCash();
        BigDecimal freightAmount = orignOrder.getFreightAmount();
        Long orderIntegration = orignOrder.getOrderIntegration();
        BigDecimal total = orderCash.add(freightAmount);
        if (total.compareTo(new BigDecimal(0)) > 0) {
            //现金+积分
            if (orderIntegration > 0L) {
                orderStatusMsgMqBo.setOrderAmount("￥" + DECIMALFORMAT.format(total) + " " + orderIntegration + "积分");
            } else {
                //纯现金
                orderStatusMsgMqBo.setOrderAmount("￥" + DECIMALFORMAT.format(total));
            }
        } else {
            //纯积分
            if (orderIntegration > 0) {
                orderStatusMsgMqBo.setOrderAmount(orderIntegration + "积分");
            }
        }
        orderStatusMsgMqBo.setCreateTime(DateUtil.format(orignOrder.getCreateTime(), DatePattern.NORM_DATETIME_FORMAT));
        orderStatusMsgMqBo.setJumpUrl("id=" + orignOrder.getOrderSn());
        subMsgCommonBO.setContent(orderStatusMsgMqBo);
        return subMsgCommonBO;
    }

    private String getMobileCodeValue(String codeValue){
        if(StringUtils.isNotBlank(codeValue)) codeValue = codeValue.substring(0,3).concat("****").concat(codeValue.substring(codeValue.length()-4,codeValue.length()));
        return codeValue;
    }

    private String getDetailAddressCodeValue(String detailAddress){
        if(StringUtils.isNotBlank(detailAddress)){
            if(detailAddress.length() >= 6){
                return "******";
            }else {
               return this.getCode(detailAddress.length());
            }
        }
        return detailAddress;
    }

    private String getReceiveNameCodeValue(String receiveName){
        if(StringUtils.isNotBlank(receiveName)){
            return receiveName.substring(0,1).concat(this.getCode(receiveName.length() - 1));
        }
        return receiveName;
    }

    private String getAcctNoCodeValue(String acctNo){
        if(StringUtils.isNotBlank(acctNo)){
            if(acctNo.length() == 11){
                acctNo = acctNo.substring(0,3).concat("****").concat(acctNo.substring(acctNo.length()-4,acctNo.length()));
            }else{
                acctNo = acctNo.substring(0,4).concat("********").concat(acctNo.substring(acctNo.length()-4,acctNo.length()));
            }
        }
        return acctNo;
    }

    private String getCode(int length){
        String s = "";
        for(int i = 0; i < length; i++){
            s += "*";
        }
        return s;
    }
}
