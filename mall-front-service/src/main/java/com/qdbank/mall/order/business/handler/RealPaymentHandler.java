package com.qdbank.mall.order.business.handler;

import org.springframework.beans.factory.annotation.Value;
import com.qdbank.mall.address.AreaService;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.common.FileService;
import com.qdbank.mall.common.ParamsService;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.constant.payment.CloseTypeEnum;
import com.qdbank.mall.constant.payment.PaymentTypeEnum;
import com.qdbank.mall.constant.payment.orderstatus.RealOrderStatus;
import com.qdbank.mall.constent.payment.RefundStatausEnum;
import com.qdbank.mall.dao.order.OrderDao;
import com.qdbank.mall.dao.prefecture.PrefectureDao;
import com.qdbank.mall.dao.refund.RefundDao;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.model.areafreighttemplate.AreafreighttemplateDO;
import com.qdbank.mall.model.freighttemplate.FreighttemplateListDO;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.order.OrderStatusDO;
import com.qdbank.mall.model.orderrefund.OrderRefundDO;
import com.qdbank.mall.model.product.ProductInfoQueryDO;
import com.qdbank.mall.model.product.ProductSkuDO;
import com.qdbank.mall.model.refundsetting.RefundsettingDO;
import com.qdbank.mall.model.skustock.SkustockDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.business.CommonPaymentAware;
import com.qdbank.mall.order.mapper.RefundMapper;
import com.qdbank.mall.request.order.CommonOrderReqDTO;
import com.qdbank.mall.request.order.RealOrderReqDTO;
import com.qdbank.mall.response.area.AreaResDTO;
import com.qdbank.mall.response.area.FreightFeeResDTO;
import com.qdbank.mall.response.third.payment.RefundResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.util.BsnUtil;
import com.qdbank.mall.util.JsonUtil;
import com.qdbank.mall.util.SpringContextUtils;
import com.qdbank.mall.util.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ClassName PaymentInKindHandler
 * @Description 实物支付流程
 * @Author hongjh
 * @Date 2021/3/17 19:41
 * @Version 1.0
 **/
@Component("paymentInKind")
@Slf4j
@RefreshScope
public class RealPaymentHandler extends BaseServiceImpl implements CommonPaymentAware {

    @Value(value = "${third.mall.url.paymentInKind:}")
    private String mallUrlPaymentInKindUrl;

    @Autowired
    private PrefectureDao prefectureDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderService orderService;

    @Autowired
    RefundMapper refundMapper;

    @Autowired
    AreaService areaService;

    @Autowired
    RefundDao refundDao;


    @Autowired
    FileService fileService;

    @Autowired
    ParamsService paramsService;


    /**
     * 后管触发发货，收货为定时任务处理
     *DELIVERY_TIME	发货时间
     * RECEIVE_TIME	确认收货时间
     * CONFIRM_STATUS	确认收货状态：0 未确认1 已确认
     * DELIVERY_COMPANY_NAME	物流公司名称
     * DELIVERY_SN	物流单号
     *
     *--需处理
     * DELIVERY_COMPANY_NAME	物流公司名称
     * DELIVERY_SN	物流单号
     * RECEIVER_NAME	收货人姓名
     * RECEIVER_PHONE	收货人电话
     * RECEIVER_PROVINCE	省份直辖市
     * RECEIVER_CITY	城市
     * RECEIVER_REGION	区县
     * RECEIVER_DETAIL_ADDRESS	详细地址
     * NOTE	订单备注
     *
     * @return
     */



    @Override
    public ProductEnum getProductType() {
        return ProductEnum.PAYMENT_IN_KIND;
    }



    @Override
    public void preCreateOrderCheck(OrderDO order, CommonOrderReqDTO commonOrderReq) {
        RealOrderReqDTO req = (RealOrderReqDTO) commonOrderReq;
        //step 1 查询商品信息
        ProductInfoQueryDO query = new ProductInfoQueryDO();
        query.setProductId(req.getProductId());
        query.setProductSkuId(req.getProductSkuId());
        query.setSkuStatus(0L);

        List<ProductSkuDO> skus =prefectureDao.selectProductSkuInfo(query);

        //step 2 订单基本信息校验
        checkProduct(req,order,skus);
        ProductSkuDO productSku = skus.get(0);
        SkustockDO skustock = productSku.getSkustocks().get(0);

        //积分结算标识--以商品为准
        order.setIntegrationPayFlag(skustock.getIntegrationPayFlag());
        /**
         * 商户-商品信息同步
         */
        order.setMerchantNo(productSku.getMerchantNo());
        order.setMerchantName(productSku.getMerchantName());
        order.setProductId(productSku.getProductId());
        order.setProductName(skustock.getProductName());
        order.setProductSkuId(skustock.getProductSkuId());
        order.setCategoryId1(productSku.getCategoryId1());
        order.setCategoryId2(productSku.getCategoryId2());
        order.setCategoryId3(productSku.getCategoryId3());
        order.setCategoryId4(productSku.getCategoryId4());
        order.setProductCount(req.getProductCount());


        // 运费金
        FreightFeeResDTO fee=this.freight(req.getReceiverProvince(),req.getProductCount().intValue(),productSku.getFreightTemplateId(),skustock);
        order.setFreightAmount(fee.getFreightAmount());

        //订单积分--此版本以商品规则为准
        Long integration =skustock.getProductIntegration();
    //    Long integration =req.getOrderIntegration();
        boolean hasIntegration = integration!=null && integration >0;


        BigDecimal num = new BigDecimal(order.getProductCount());
        //商品折算价
        order.setProductPrice(num.multiply(BsnUtil.convertBigdecimal(skustock.getProductPrice())));
        //商品售价中现金金额
        order.setProductCash(num.multiply(BsnUtil.convertBigdecimal(skustock.getProductCash())));
        //商品售价中积分量
        order.setProductIntegration(order.getProductCount()*(skustock.getProductIntegration()!=null?skustock.getProductIntegration():0));

        //订单实付款(折算价)
        BigDecimal couponAmount = order.getCouponAmount();
        BigDecimal discountAmount = order.getDiscountAmount() == null ? BigDecimal.ZERO : order.getDiscountAmount();
        //**7.17** 支付折算价 含运费逻辑
        order.setPayAmount(order.getProductPrice().subtract(couponAmount).add(order.getFreightAmount()).subtract(discountAmount));
        BigDecimal a_0 = new BigDecimal(0);

        //订单现金:包含商品售价中现金金额-优惠金额
        order.setOrderCash(order.getProductCash().subtract(couponAmount).subtract(discountAmount));
        //订单积分
        order.setOrderIntegration(hasIntegration?order.getProductCount()*integration:0L);
        //积分抵扣金额不为0
        if(req.getIntegralDeduct() != null){
            if(req.getIntegralDeduct().compareTo(BigDecimal.ZERO) > 0){//如果等于0则直接赋值0
                order.setOrderIntegration((req.getIntegralDeduct().multiply(new BigDecimal(100))).longValue());
                hasIntegration = true;
                order.setOrderCash(order.getProductCash().subtract(couponAmount).subtract(req.getIntegralDeduct()));
            }
            order.setIntegralDeduct(req.getIntegralDeduct());
        }


        /**
         * ***********************重要*****************
         */
        //商品折算价
        order.setProductPrice(skustock.getProductPrice());
        //商品售价中现金金额
        order.setProductCash(skustock.getProductCash());
        //商品售价中积分量
        order.setProductIntegration(skustock.getProductIntegration()!=null?skustock.getProductIntegration():0);

        // 支付方式 积分 和 现金判断
        if(hasIntegration && (a_0.compareTo(order.getFreightAmount().add(order.getOrderCash())) <0 )){
            //有积分 且 运费和实付款 均有钱，则为积分和钱一起
            order.setPayType(PaymentTypeEnum.MONEY_SCORE.payType);
        }else if(hasIntegration){
            order.setPayType(PaymentTypeEnum.SCORE.payType);
        }else{
            order.setPayType(PaymentTypeEnum.MONEY.payType);
        }

        /**
         * 收货地址信息--start
         */
        order.setReceiverName(req.getReceiverName());
        order.setReceiverPhone(req.getReceiverPhone());
        order.setReceiverCity(this.getAreaName(req.getReceiverCity()));
        order.setReceiverRegion(this.getAreaName(req.getReceiverRegion()));
        order.setReceiverProvince(this.getAreaName(req.getReceiverProvince()));
        order.setReceiverDetailAddress(req.getReceiverDetailAddress());
        order.setNote(req.getNote());
        /**
         * 收货地址信息--end
         */

        //TODO 库存规格数量处理--基于数据库行级锁--二次判断，以免存在误操作
        int count = prefectureDao.manageSkuStockById(req.getProductSkuId(),req.getProductCount());
        if(count<1){
            throw new ApiException(ResultCode.OUT_OF_STOCK);
        }

    }



    @Override
    public void preCancelOrderCheck(OrderDO order) {
        //TODO 库存规格数量处理--基于数据库行级锁--二次判断，以免存在误操作
        int count = prefectureDao.manageSkuStockById(order.getProductSkuId(),-order.getProductCount());
        if(count<1){
            throw new ApiException(ResultCode.OUT_OF_STOCK);
        }

    }




    /**
     * 校验商品信息
     * @param productSkus
     */
    private void checkProduct(RealOrderReqDTO req, OrderDO orderDO, List<ProductSkuDO> productSkus) {
        if(CollectionUtils.isEmpty(productSkus)){
            //商品规格不存在
            throw new ApiException(ResultCode.PRODUCT_NOT_FOUND);
        }

        ProductSkuDO productSku = productSkus.get(0);
        if(CollectionUtils.isEmpty(productSku.getSkustocks())){
            //商品规格不存在
            throw new ApiException(ResultCode.PRODUCT_NOT_FOUND);
        }

        SkustockDO sku = productSku.getSkustocks().get(0);

        log.info("商品和规格状态:[{}]",productSku.getPublishStatus());
        //发布状态
        if(!Constant.PUBLISH_STATUS_SHELVES.equals(productSku.getPublishStatus()) ){
            throw new ApiException(ResultCode.PRODUCT_NOT_DEPLOY);
        }


        //数量判断--初次

        Long lockStock = sku.getProductLockStock()==null?0:sku.getProductLockStock();
        if(sku.getProductStock() < lockStock +req.getProductCount()){
            throw new ApiException(ResultCode.OUT_OF_STOCK);
        }

        //限购判断
        Date promotionStartTime=sku.getPromotionStartTime();
        Date promotionEndTime=sku.getPromotionEndTime();
        Date now = new Date();
        boolean promotionFlag = false;
        if(promotionStartTime!=null && promotionEndTime!=null && promotionStartTime.compareTo(now) <=0 && promotionEndTime.compareTo(now) >=0){
            //限购处理
            promotionFlag = true;
            Long limitNum=sku.getPromotionPerLimit();
            Long buyNum =req.getProductCount();
            //单次限购
            if(limitNum < buyNum){
                throw new ApiException(ResultCode.PRODUCT_LIMIT_STOCK);
            }

            Long canBuyNum =limitNum-buyNum;

            /**
             * 查看历史订单购买数量
             * 1、有效期内 --待支付的订单
             * XXXXXXX2、有效期内--已支付，退款未成功的订单
             *
             *非 关闭状态，索引生效形式，用in
             */

            String custNo =UserContextHolder.getUserDetails().getCustNo();

            //已支付
            Long hasBuyNum_0=hasBuyCount(custNo,promotionStartTime,promotionEndTime,req.getProductId(),req.getProductSkuId());
            //已支付--未退款
    //        Long hasBuyNum_1=orderDao.countHasBuyProduct_1(custNo,req.getProductId(),req.getProductSkuId(),promotionStartTime,promotionEndTime);

            if(canBuyNum < (hasBuyNum_0)){
                log.error("用户超过购买限制 客户号[{}]-商品[{}][{}][{}]-购买[当前{}][代支付{}][完成{}]",custNo,req.getProductId(),req.getProductSkuId(),limitNum,buyNum,hasBuyNum_0);
                throw new ApiException(ResultCode.PRODUCT_LIMIT_STOCK);
            }
             this.checkDiscountAmount(sku.getDiscountAmount(),req.getDiscountAmount(),req.getProductCount());
        }

    }

    /**
     * 校验优惠金额是否一致
     * @param skuDiscountAmount
     * @param reqDiscountAmout
     * @param productCount
     */
    private void checkDiscountAmount(BigDecimal skuDiscountAmount,BigDecimal reqDiscountAmout,Long productCount){
        if(skuDiscountAmount != null){
            BigDecimal skuDiscountAmounts = skuDiscountAmount.multiply(new BigDecimal(productCount)).setScale(2,BigDecimal.ROUND_DOWN);
            log.info("请求优惠金额：{} 规格优惠金额：{}, 购买数量：{}",reqDiscountAmout,skuDiscountAmounts,productCount);
            if(skuDiscountAmounts.compareTo(reqDiscountAmout) != 0) throw new ApiException(ResultCode.DISCOUNT_AMOUNT_DIFF);
        }
    }
    public Long hasBuyCount(String custNo,Date promotionStartTime,Date promotionEndTime,Long productId,Long productSkuId){
        //限购判断
        Date now = new Date();
        boolean promotionFlag = false;
        if(promotionStartTime!=null && promotionEndTime!=null && promotionStartTime.compareTo(now) <=0 && promotionEndTime.compareTo(now) >=0) {
            //限购处理
            promotionFlag = true;
            /**
             * 查看历史订单购买数量
             * 1、有效期内 --待支付的订单
             * XXXXXXX2、有效期内--已支付，退款未成功的订单
             *
             *非 关闭状态，索引生效形式，用in
             */
            //已支付
            Long hasBuyNum_0 = orderDao.countHasBuyProduct_0(custNo, productId, productSkuId, promotionStartTime, promotionEndTime);
            return hasBuyNum_0;
        }
        return 0L;
    }


    /**
     * 退款申请
     * @param
     */
    @Transactional(rollbackFor = Exception.class)
    public void commitReviewed(OrderRefundDO orderRefundDO,OrderDO orderDO){

        // 更新退款状态
        OrderStatusDO orderStatusDO = new OrderStatusDO();
        orderStatusDO.setBeforeApproveStatus(orderDO.getRefundStatus());
        orderStatusDO.setBeforeCloseType(CloseTypeEnum.INIT.closeType);
        orderStatusDO.setBeforeStatus(orderDO.getStatus());

        orderStatusDO.setAfterApproveStatus(RefundStatausEnum.PREPARE_APPROVE.refundStatus);
        orderStatusDO.setAfterCloseType(CloseTypeEnum.INIT.closeType);
        orderStatusDO.setAfterStatus(orderDO.getStatus());


        Long status= orderDO.getStatus();
        //积分
        orderRefundDO.setRefundIntegration(orderDO.getOrderIntegration());

        //仅退款
        if(RealOrderStatus.Status.HAS_FINISH.status.equals(status)){
            //申请退款时效控制
            Date receiveTime =orderDO.getReceiveTime();
            boolean overTimeOut = paramsService.isRefundTimeOut(receiveTime);
            if(overTimeOut){
                throw new ApiException(ResultCode.APPLAY_REFUND_EXPIR);
            }
            orderRefundDO.setRefundCash(orderDO.getOrderCash());
        }else if(RealOrderStatus.Status.STAY_DELIVER_GOODS.status.equals(status)){
            //退货退款：订单+运费
            orderRefundDO.setRefundCash(orderDO.getOrderCash().add(orderDO.getFreightAmount()));
        }

        orderRefundDO.setRefundAmount(orderRefundDO.getRefundCash().add(BsnUtil.integraToAmt(orderRefundDO.getRefundIntegration())));


        //运费
        orderRefundDO.setFreightAmount(orderDO.getFreightAmount());


        Date now = new Date();
        orderRefundDO.setCreateTime(now);
        orderRefundDO.setUpdateTime(now);




        /**
         * 分布式锁、且已经使用状态机机制，不需要做是否存在退款订单状态
         */
        orderRefundDO.setRefundSerial(super.generateId()+"");

        //图片上传
        String[] pics=orderRefundDO.getProofPicsUrl();
        if(pics!=null && pics.length>0){
            fileService.addFile(Long.valueOf(orderRefundDO.getRefundSerial()),pics,orderDO.getCustNo()+"",false);
        }

        log.info("新增记录[{}]",JsonUtil.toJSONString(orderRefundDO));
        orderService.createApplyRefund(orderRefundDO);

        //查询是否存在历史退款记录
      /*  OrderRefundDO old_orderRefund =orderService.qryApplyRefund(orderDO.getOrderSn());
        Long refundSerial= null;
        boolean hasOld = false;
        if(old_orderRefund==null){
              orderRefundDO.setRefundSerial(super.generateId()+"");
              refundSerial = orderRefundDO.getRefundSerial();
        }else{
              refundSerial=old_orderRefund.getRefundSerial();
            hasOld = true;
        }

        //图片上传处理
        String[] pics=orderRefundDO.getProofPicsUrl();
        if(pics!=null && pics.length>0){
             fileService.addFile(refundSerial,pics,orderDO.getCustNo()+"",hasOld);
        }

        //存在历史记录更新，不存在新建；---只有一条退款记录
        if(hasOld){
           //更新
            log.info("历史记录更新[{}]",JsonUtil.toJSONString(old_orderRefund));
            orderService.updateRefundStatus(old_orderRefund,orderDO.getCustNo()+"",orderDO.getOrderSn(),old_orderRefund.getRefundStatus(),RefundStatausEnum.PREPARE_APPROVE.refundStatus);
        }else{
            log.info("新增记录[{}]",JsonUtil.toJSONString(orderRefundDO));
            orderService.createApplyRefund(orderRefundDO);
        }*/

        //step 2 订单状态翻退款状态
        log.info("退款信息[{}]", JsonUtil.toJSONString(orderRefundDO));
        int result =orderDao.updateUserOrderStatus(new OrderDO(),orderDO.getCustNo()+"",orderDO.getOrderSn(),orderStatusDO);
        //更新失败
        if(result<1){
            throw new ApiException(ResultCode.ORDER_STATUS_ERROR);
        }
    }


    /**
     * 换算运费
     * @param skustockDO
     */
    public FreightFeeResDTO freight(String province,Integer count,Long freightTemplateId,SkustockDO skustockDO){
        log.info("运费模板运算[{}]=[{}]=[{}]",new Object[]{province,count,freightTemplateId});
        FreighttemplateListDO freighttemplate =areaService.qryTemplatesById(freightTemplateId);

        //step 1 判断是否为指定区域
        List<AreafreighttemplateDO> areaTemplates=freighttemplate.getAreafreighttemplateDO();
        AreafreighttemplateDO result = checkAreaTemplate(province,areaTemplates);

        //step 2 默认数据
        //快递方式
        Long transferType=freighttemplate.getTransferType();
        //默认件数
        String defaultProductCount=freighttemplate.getDefaultProductCount();
        //默认费用
        String defaultCharge=freighttemplate.getDefaultCharge().toString();
        //续费件数
        String defaultAddProductCount=freighttemplate.getDefaultAddProductCount();
        //续费费用
        String defaultAddProductCharge=freighttemplate.getDefaultAddProductCharge().toString();
        //计费规则
        Long chargeRule=freighttemplate.getChargeRule();


        Long transferFlag = 0L;
        Long hitFlag = 0L;
        FreightFeeResDTO fee = new FreightFeeResDTO();
        //指定区域
        if(result!=null){
            //是否配送
            transferFlag=result.getTransferFlag();

            //默认件数
             defaultProductCount=result.getCountUnit()+"";
            //默认费用
             defaultCharge = result.getChargeUnit();
            //续费件数
             defaultAddProductCount=result.getAddCount()+"";
            //续费费用
             defaultAddProductCharge= result.getAddCharge();
             //命中
            hitFlag = 1L;
        }

        //不支持配送
        if(transferFlag==1L){
            throw new ApiException(ResultCode.NOT_SUPPORT_SEND_ERROR);
        }



        //快递处理
        fee.setTransferType(transferType);
        //是否支持配送
        fee.setTransferFlag(transferFlag);
        //是否命中
        fee.setHitFlag(hitFlag);
        //step 1 是否包邮处理
        Long freeFlag =freighttemplate.getFreeFlag();
        fee.setFreeFlag(freeFlag);
        fee.setChargeRule(chargeRule);

        BigDecimal freightAmt = null;
        //step 3 运费换算
        if(0L==chargeRule){
            //按件
            freightAmt=byProductCount(count,defaultProductCount,new BigDecimal(defaultCharge),defaultAddProductCount,new BigDecimal(defaultAddProductCharge));
        }else if(1L==chargeRule){
            //按梳理

        }
        fee.setFreightAmount(freightAmt);
        return fee;
    }

    /**
     * 金额换算
     * @param count
     * @param _defaultProductCount
     * @param defaultCharge
     * @param _defaultAddProductCount
     * @param defaultAddProductCharge
     * @return
     */
    public BigDecimal byProductCount(Integer count, String _defaultProductCount, BigDecimal defaultCharge, String _defaultAddProductCount, BigDecimal defaultAddProductCharge) {
        log.info("换算数据[{}][{}][{}][{}][{}]",count,_defaultProductCount,defaultCharge,_defaultAddProductCount,defaultAddProductCharge);
        Integer defaultProductCount =StringUtils.hasText(_defaultProductCount)? Integer.parseInt(_defaultProductCount):0;
        Integer defaultAddProductCount =StringUtils.hasText(_defaultAddProductCount)? Integer.parseInt(_defaultAddProductCount):0;

        //购买件数大于默认设置件数，需做换算
        if(count>defaultProductCount){
            //剩余数量
            int leftCount=count-defaultProductCount;
            if(defaultAddProductCount!=0){
                //计算累加次数。向上走
                double addCount=(new BigDecimal(leftCount).divide(new BigDecimal(defaultAddProductCount),2)).doubleValue();
                double result =Math.ceil(addCount);
                log.info("重量换算[{}]-[{}]",addCount,result);
                defaultCharge=defaultCharge.add(defaultAddProductCharge.multiply(new BigDecimal(result)));
            }else{
                //为0 直接累加
                defaultCharge= defaultCharge.add(defaultAddProductCharge);
            }
        }

        if(defaultCharge==null){
            defaultCharge= new BigDecimal(0);
        }
        return defaultCharge;
    }

    private AreafreighttemplateDO checkAreaTemplate(String receiverProvince, List<AreafreighttemplateDO> areaTemplates) {
        if(!StringUtils.hasText(receiverProvince)){
            return null;
        }

        if(!CollectionUtils.isEmpty(areaTemplates)){
            for(AreafreighttemplateDO template : areaTemplates){
                String[] pros = template.getProvinceIds().split(",");
                if(Arrays.asList(pros).contains(receiverProvince)){
                    log.info("命中区域[{}][{}]",receiverProvince,template.getProvinceIds(),JsonUtil.toJSONString(template));
                    return template;
                }
            }
        }
        return null;
    }


    private void byProductWeight(){

    }


    public  List<RefundsettingDO> refundSettings(){
        List<RefundsettingDO> list = refundDao.qrySettings(1L);
        return list;
    }



    public  RefundsettingDO refundSettings(Long id){
        RealPaymentHandler realPaymentHandler = (RealPaymentHandler) SpringContextUtils.getBean("paymentInKind");
        List<RefundsettingDO> settings =realPaymentHandler.refundSettings();
        if(!CollectionUtils.isEmpty(settings)){
            for(RefundsettingDO refundsettingDO : settings){
                if(refundsettingDO.getId().equals(id)){
                    return refundsettingDO;
                }
            }
        }
        return null;
    }




    private String getAreaName(String code){
        AreaResDTO res  =areaService.getAreaByCode(code);
        if(res!=null){
            return res.getAddressName();
        }
        return code;
    }





    @Override
    public String payBackJumpUrl() {
        return mallUrlPaymentInKindUrl;
    }

    @Override
    public void handlerRefund(OrderDO order, OrderRefundDO orderRefundDO, RefundResDTO res) {
        orderRefundDO.setRefundNote("支付后超时异常关闭");
        orderRefundDO.setRefundCash(order.getOrderCash().add(order.getFreightAmount()));
        //积分
        orderRefundDO.setRefundIntegration(order.getOrderIntegration());
        // 退款折算价
        orderRefundDO.setRefundAmount(orderRefundDO.getRefundCash().add(BsnUtil.integraToAmt(orderRefundDO.getRefundIntegration())));
        //运费
        orderRefundDO.setFreightAmount(order.getFreightAmount());
        log.info("新增记录[{}]", JsonUtil.toJSONString(orderRefundDO));
        orderService.createApplyRefund(orderRefundDO);
    }
}
