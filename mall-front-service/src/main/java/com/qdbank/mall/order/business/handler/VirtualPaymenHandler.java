package com.qdbank.mall.order.business.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.payment.MobileRechargeStatus;
import com.qdbank.mall.constant.payment.VirtualDeployEnum;
import com.qdbank.mall.constant.payment.event.MobileReChargePayOrderEvent;
import com.qdbank.mall.constant.payment.lifestatus.MobileRechargeOrderLifeStatus;
import com.qdbank.mall.dao.prefecture.PrefectureDao;
import com.qdbank.mall.dao.virtual.VirtualDao;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.exception.MobileException;
import com.qdbank.mall.mobile.MobileService;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.orderrefund.OrderRefundDO;
import com.qdbank.mall.model.product.ProductInfoQueryDO;
import com.qdbank.mall.model.product.ProductSkuDO;
import com.qdbank.mall.model.product.VirtualProductDO;
import com.qdbank.mall.model.skustock.SkustockDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.PaymentService;
import com.qdbank.mall.order.business.CommonPaymentAware;
import com.qdbank.mall.prefecture.ProductService;
import com.qdbank.mall.request.order.CommonOrderReqDTO;
import com.qdbank.mall.request.order.VirtualPaymenOrderReqDTO;
import com.qdbank.mall.request.third.mobile.ThirdNoticeReqDTO;
import com.qdbank.mall.response.product.ProductSkuResDTO;
import com.qdbank.mall.response.skustock.SkustockResDTO;
import com.qdbank.mall.response.third.mobile.OrderFindResDTO;
import com.qdbank.mall.response.third.payment.RefundResDTO;
import com.qdbank.mall.response.third.virsual.QryOrderResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.third.VirsualProxySendService;
import com.qdbank.mall.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 虚拟商品支付
 * @ClassName VirtualPaymenServiceImpl
 * @Description TODO
 * @Author hongjh
 * @Date 2021/3/17 19:41
 * @Version 1.0
 **/
@Component("virtualPaymenHandler")
@Slf4j
@RefreshScope
public abstract class VirtualPaymenHandler extends BaseServiceImpl implements CommonPaymentAware, InitializingBean {

    @Autowired
    VirtualDao virtualDao;

    @Autowired
    PaymentService paymentService;

    @Autowired
    ProductService productService;

    @Autowired
    PrefectureDao prefectureDao;

    @Autowired
    OrderService orderService;

    @Autowired
    MobileService mobileService;

    @Autowired
    VirsualProxySendService virsualProxySendService;

    @Value(value = "${third.mall.url.mobileRecharge:}")
    private String mallUrlMobileRechargeUrl;

    Map<Long,VirtualProductDO> productMap= new HashMap<>();

    /**
     * 虚拟商户加载
     */
    public void init(){
       List<VirtualProductDO> products = virtualDao.qryVirtualProducts();
        if(!CollectionUtils.isEmpty(products)){
            for(VirtualProductDO virtual : products){
                productMap.put(virtual.getProductId(),virtual);
            }
        }
    }

    public VirtualProductDO getVirtualProduct(Long productId){
        return productMap.get(productId);
    }




    public List<ProductSkuResDTO> qryVirtualProduct(){
       return productService.listProductSkuInfo(getProductType());
    }

    /**
     * 规格信息json处理
     * @param spData
     * @return
     */
    public Map formate( String spData){
        List<Map> list = JSON.parseArray(spData, Map.class);
        Map result = new HashMap();

        list.stream().forEach(item->{
            result.putAll(item);
        });
        return result;
    }



    @Override
    public void preCreateOrderCheck(OrderDO order, CommonOrderReqDTO commonOrderReq) {

        //油卡商品处理
        VirtualPaymenOrderReqDTO req = (VirtualPaymenOrderReqDTO) commonOrderReq;

        ProductInfoQueryDO query = new ProductInfoQueryDO();
        query.setProductId(req.getProductId());
        query.setProductSkuId(req.getProductSkuId());
        query.setSkuStatus(0L);

        List<ProductSkuDO> skus =prefectureDao.selectProductSkuInfo(query);

        //step 2 订单基本信息校验
        checkProduct(order,skus);
        ProductSkuDO productSku = skus.get(0);
        SkustockDO skustock = productSku.getSkustocks().get(0);


        //积分结算标识
        order.setIntegrationPayFlag(skustock.getIntegrationPayFlag());
        //通过产品获取商户信息
        order.setMerchantNo(productSku.getMerchantNo());
        order.setMerchantName(productSku.getMerchantName());
        order.setProductId(productSku.getProductId());
        order.setProductName(skustock.getProductName());
        order.setProductSkuId(skustock.getProductSkuId());
        order.setCategoryId1(productSku.getCategoryId1());
        order.setCategoryId2(productSku.getCategoryId2());
        order.setCategoryId3(productSku.getCategoryId3());
        order.setCategoryId4(productSku.getCategoryId4());
        order.setProductCount(1L);

        /**
         * 金额初始华
         */
        orderService.initOrderCash(order,skustock);

        //充值账号
       order.setRechargeMobile(req.getAccNo());

    }



    /**
     * 校验商品信息
     * @param productSkus
     */
    private void checkProduct(OrderDO orderDO, List<ProductSkuDO> productSkus) {
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

    }


    /**
     * 虚拟商品充值接口
     * @param order
     * @return
     */
    public String charge(OrderDO order){
        ProductInfoQueryDO query = new ProductInfoQueryDO();
        query.setProductId(order.getProductId());
        query.setProductSkuId(order.getProductSkuId());
        query.setSkuStatus(0L);

        List<ProductSkuDO> skus =prefectureDao.selectProductSkuInfo(query);

        //step 2 订单基本信息校验
        ProductSkuDO productSku = skus.get(0);
        String spadata = productSku.getSkustocks().get(0).getProductSpData();
        Map data =this.formate(spadata);
        String productId= (String) data.get("规格");

        String wxOrderId =virsualProxySendService.chargeOrderProcess(order,productId);
        mobileService.createMobileFlow(order.getCustNo(),order.getRechargeMobile(),order.getProductSkuId(),order.getOrderSn(), MobileRechargeStatus.CHARGEING,wxOrderId);
        log.info("话费充值信息[{}]-[{}]",wxOrderId, JsonUtil.toJSONString(order));
        orderService.createProcessStatus(order.getOrderSn(), MobileRechargeOrderLifeStatus.CHARGEING.status,MobileRechargeOrderLifeStatus.CHARGEING.status);
        return wxOrderId;
    };


    /**
     * 虚拟商品查询
     * @param order
     * @return
     */
    public OrderFindResDTO orderFind(OrderDO order){
        OrderFindResDTO res1 = new OrderFindResDTO();
        try{
            QryOrderResDTO  res  =this.virsualProxySendService.orderFind(order.getOrderSn());
            res1.setErrMsg(res.getResultState());
            res1.setStatus(res.getOrderState());
        }catch (MobileException e){
            /**
             *交易不存在(下单半小时之后出现该状态在判定为失败)
             * 2 min冗余
             */
            if("1100".equals(e.getCode())){
                Calendar now = Calendar.getInstance();
                now.add(Calendar.MINUTE,-32);
                if(now.getTime().compareTo(order.getPaymentTime()) > 0){
                    log.error("订单支付成功后超时30min，显示不存在交易[{}]-[{}]",order.getOrderSn(),order.getPaymentTime());
                    res1 = new OrderFindResDTO();
                    res1.setStatus(MobileRechargeStatus.CHARGE_FAIL.code);
                }
            }
        }
        return res1;
    }


    /**
     * 通知处理
     * @param order
     * @param reqDTO
     */
    public void handlerNotic(OrderDO order, ThirdNoticeReqDTO reqDTO){
        Map params = new HashMap();
        params.put("mobileNoticeReq",reqDTO);
        paymentService.handlerOrder(order, MobileReChargePayOrderEvent.CHARGE_NOTICE,params);
    }


    /**
     * 退款逻辑处理
     * @param order
     * @param orderRefundDO
     * @param res
     */
    @Override
    public void handlerRefund(OrderDO order, OrderRefundDO orderRefundDO, RefundResDTO res) {
        orderRefundDO.setRefundCash(order.getOrderCash());
        // 退款折算价
        orderRefundDO.setRefundAmount(order.getPayAmount());
        //积分--不存在
        orderRefundDO.setRefundIntegration(order.getOrderIntegration());

        log.info("新增记录[{}]", JsonUtil.toJSONString(orderRefundDO));
        orderService.createApplyRefund(orderRefundDO);
    }


    @Override
    public void afterCreateOrder(OrderDO order, CommonOrderReqDTO commonOrderReq) {
        orderService.createProcessStatus(order.getOrderSn(), MobileRechargeOrderLifeStatus.INIT.status,MobileRechargeOrderLifeStatus.INIT.status);
        orderService.createProcessStatus(order.getOrderSn(), MobileRechargeOrderLifeStatus.PREPARE_PAY.status,MobileRechargeOrderLifeStatus.PREPARE_PAY.status);
        orderService.createProcessStatus(order.getOrderSn(),MobileRechargeOrderLifeStatus.PAY.status,MobileRechargeOrderLifeStatus.PAY.status);
    }



    public void deployProduct(String productId,String status){

        List<ProductSkuResDTO> list =productService.listProductSkuInfo(getProductType());

        Long skuId = null;

        product:for(ProductSkuResDTO product : list){
            stock:for(SkustockResDTO stock :product.getSkustocks()){
                Map spa =formate(stock.getProductSpData());
                String _productId= (String) spa.get("规格");
                if(_productId.equals(productId)){
                    skuId = stock.getProductSkuId();
                    break product;
                }
            }
        }

        //存在更新
        if(skuId!=null){
           prefectureDao.updateSkuStockStatusById(skuId,VirtualDeployEnum.getEnumByWxStatus(status).getBankStatus());
        }

    }


    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    @Override
    public String payBackJumpUrl() {
        return mallUrlMobileRechargeUrl;
    }

}
