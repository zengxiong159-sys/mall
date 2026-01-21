package com.qdbank.mall.controller.order;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qdbank.mall.address.AreaService;
import com.qdbank.mall.annotation.CheckUser;
import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.bo.UserDetails;
import com.qdbank.mall.common.FileService;
import com.qdbank.mall.common.ParamsService;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.constant.payment.orderstatus.IntegralOrderStatus;
import com.qdbank.mall.constant.payment.orderstatus.RealOrderStatus;
import com.qdbank.mall.constent.payment.RefundStatausEnum;
import com.qdbank.mall.controller.order.mapper.LifeOrderMapper;
import com.qdbank.mall.coupon.CouponService;
import com.qdbank.mall.dao.order.OrderDao;
import com.qdbank.mall.dao.refund.RefundDao;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.orderrefund.OrderRefundDO;
import com.qdbank.mall.model.product.ProductSkuDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.PaymentService;
import com.qdbank.mall.order.mapper.OrderMapper;
import com.qdbank.mall.order.mq.TimeOutHandler;
import com.qdbank.mall.prefecture.PrefectureService;
import com.qdbank.mall.request.order.*;
import com.qdbank.mall.response.coupon.CouponResDTO;
import com.qdbank.mall.response.coupon.UserCouponResDTO;
import com.qdbank.mall.response.order.LifeOrderListResDTO;
import com.qdbank.mall.response.order.OrderCountResDTO;
import com.qdbank.mall.response.order.OrderResDTO;
import com.qdbank.mall.util.UserContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @ClassName OrderController
 * @Author hongjh
 * @Date 2021/1/15 15:34
 * @Version 1.0
 **/
@RestController
@Api(tags = "commonOrderController", description = "订单管理")
@RequestMapping("/order/common")
public class CommonOrderController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    TimeOutHandler timeOutHandler;

    @Autowired
    OrderService orderService;

    @Autowired
    LifeOrderMapper lifeOrderMapper;

    @Autowired
    FileService fileService;

    @Autowired
    CouponService couponService;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    AreaService areaService;

    @Autowired
    PrefectureService prefectureService;

    @Autowired
    ParamsService paramsService;

    @Autowired
    OrderDao orderDao;

    @Autowired
    RefundDao refundDao;


   /* @ApiOperation("自动取消超时订单->15分钟之后未支付")
    @RequestMapping(value = "/cancelTimeOutOrder", method = RequestMethod.POST)
    public CommonResult cancleTimeOutOrder(OrderIDReqDTO orderIDReqDTO){
        timeOutHandler.setOrderTimeOut(orderIDReqDTO.getOrderSn());
        return CommonResult.success(null);
    }

    @ApiOperation("订单状态同步：5分钟之后15分钟之内待支付状态订单")
    @RequestMapping(value = "/orderStatusSynchro", method = RequestMethod.POST)
    public CommonResult orderStatusSynchro(){
        return CommonResult.success(null);
    }

    @ApiOperation("取消订单未收到异步通知状态同步->30分钟之后订单状态为已取消但未收到异步通知订单")
    @RequestMapping(value = "/cancleAndNoticeOrder", method = RequestMethod.POST)
    public CommonResult cancleAndNoticeOrder(){
        return CommonResult.success(null);
    }*/

    @CheckUser
    @ApiOperation("生活订单列表")
    @RequestMapping(value = "/lifeList", method = RequestMethod.POST)
    public CommonResult<CommonPage<LifeOrderListResDTO>> list(@RequestBody LifeOrderListReqDTO userOrderReqDTO) {
        //分页查询
        Page page = PageHelper.startPage(userOrderReqDTO.getPageNum(),userOrderReqDTO.getPageSize());
        //获取产品类型
        List<OrderResDTO> results =orderService.qryOrders(UserContextHolder.getUserDetails().getCustNo(),userOrderReqDTO.getStatus(),ProductEnum.MOBILE_RECHARGE,ProductEnum.OIL_CARD,ProductEnum.VIDEO);
        List<LifeOrderListResDTO> list=lifeOrderMapper.orderToLifeList(results);

        return CommonResult.success(CommonPage.restPage(page,list));
    }


    @ApiOperation("订单汇总统计")
    @RequestMapping(value = "/countUserOrder", method = RequestMethod.POST)
    public CommonResult<OrderCountResDTO> countUserOrder(){
        OrderCountResDTO res = new OrderCountResDTO();
        String custNo =UserContextHolder.getUserDetails().getCustNo();
        if(org.apache.commons.lang3.StringUtils.isBlank(custNo)) return CommonResult.success(res);
        //已发货状
        Long o_results =orderService.countOrderByProductTypeAndStatus(custNo,2L,ProductEnum.PAYMENT_IN_KIND);
        //商城待收货
        res.setMallUnReceiveCount(o_results!=null?o_results:0);

        //step 2 商城待支付
        o_results =orderService.countOrderByProductTypeAndStatus(custNo,0L,ProductEnum.PAYMENT_IN_KIND);
        res.setMallUnPaymentCount(o_results!=null?o_results:0);

        //step 3 生活待支付
        o_results =orderService.countOrderByProductTypeAndStatus(custNo,0L,ProductEnum.MOBILE_RECHARGE,ProductEnum.OIL_CARD,ProductEnum.VIDEO);
        res.setVitualUnPaymentCount(o_results!=null?o_results:0);
        return CommonResult.success(res);
    }



    @ApiOperation("订单详细信息->订单编号")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public CommonResult<OrderResDTO> getItem(@Validated @RequestBody  OrderIDReqDTO commonIDReqDTO) {
        UserDetails userDetails = UserContextHolder.getUserDetails();
        String custNo =userDetails.getCustNo();
        if(org.apache.commons.lang3.StringUtils.isBlank(custNo))  return CommonResult.success(new OrderResDTO());
        //获取产品类型
        OrderDO orderDO =orderService.qryOrderBySn(commonIDReqDTO.getOrderSn());

        //超时判断
        orderDO =checkTimeOut(orderDO);

        OrderResDTO item = orderMapper.poToDo(orderDO);


        //待支付状态--可取消
        Long status=item.getStatus();
        if(0L==status){
            Calendar cc = Calendar.getInstance();
            //订单创建时间
            cc.setTime(item.getCreateTime());
            cc.add(Calendar.MINUTE, paramsService.getOrderTimeOut());
            Date now = new Date();
            item.setCloeLeftTime(cc.getTime().getTime()-now.getTime());
        }else if(RealOrderStatus.Status.DELIVER_GOODS.status.equals(status)){
            //已发货
            Calendar cc = Calendar.getInstance();
            //订单创建时间
            cc.setTime(item.getDeliveryTime());
            cc.add(Calendar.DATE,paramsService.getOrderDelivery());
            Date now = new Date();
            item.setCloeLeftTime(cc.getTime().getTime()-now.getTime());
        }

        ProductEnum productEnum= ProductEnum.getProductByType(item.getProductType());
        //实物订单处理
        if(ProductEnum.PAYMENT_IN_KIND==productEnum){
            Long productId=item.getProductId();
            Long productSkuId=item.getProductSkuId();

            if(productId!=null && productSkuId != null ){
                ProductSkuDO sku =prefectureService.qryProductSku(productId,productSkuId,false);
                if(sku!=null){
                    item.setSpData(sku.getSkustocks().get(0).getProductSpData());
                    //规格图片
                    String skuPicUrl =sku.getSkustocks().get(0).getSkuPicUrl();
                    if(!StringUtils.hasText(skuPicUrl)){
                        skuPicUrl=sku.getMailPicUrl();
                    }
                    item.setPicUrl(skuPicUrl);
                    item.setSingleProductCash(item.getProductCash());
                    item.setSingleProductIntegration(item.getProductIntegration());
                    item.setProductCash(item.getSingleProductCash().multiply(new BigDecimal(item.getProductCount())));
                    item.setProductIntegration(item.getProductCount()*item.getSingleProductIntegration());
                    item.setMaxIntegralDeduct(sku.getSkustocks().get(0).getMaxIntegralDeduct());
                    item.setMinIntegralDeduct(sku.getSkustocks().get(0).getMinIntegralDeduct());
                    item.setIntegralDeduct(orderDO.getIntegralDeduct());
                }
            }
            // 0\1\2 获取最新退款流水
            Long refundStatus =item.getRefundStatus();
            if(RefundStatausEnum.PREPARE_APPROVE.refundStatus.equals(refundStatus) ||
                RefundStatausEnum.APPROVE_YES.refundStatus.equals(refundStatus) ||
                    RefundStatausEnum.REFUND.refundStatus.equals(refundStatus)
            ){
                OrderRefundDO refund  =refundDao.qryNewOrderRefundByOrderSn(orderDO.getOrderSn());
                item.setRefundSerialNo(refund.getRefundSerial());
            }

            //已完成
            boolean overTimeOut = false;
            if(RealOrderStatus.Status.HAS_FINISH.status.equals(orderDO.getStatus())){
                 overTimeOut = paramsService.isRefundTimeOut(orderDO.getReceiveTime());
            }
            item.setApplayRefundOverTime(overTimeOut?"1":"0");

            //省市区描述处理ProductController
      //      this.formateAreaDetail(item);
        } else if (ProductEnum.INTEGRAL==productEnum) {
            //积分兑换券
            Long exchangeCouponId =orderDO.getExchangeCouponId();
            CouponResDTO res = couponService.qryCouponDetailById(exchangeCouponId);
            item.setCouponRes(res);

            //积分兑换券处理&& 已使用
            if(IntegralOrderStatus.Status.HAS_FINISH.status.equals(item.getStatus())){
                OrderDO userOrderDO =orderDao.selectHasOrderByIntegrealOrderSn(item.getOrderSn());
                item.setUseIntegralOrderSn(userOrderDO.getOrderSn());
            }

        }

        //客服电话处理
        String suppiorMobile =paramsService.handlerSuppiorMobile(productEnum,orderDO.getMerchantNo());
        item.setCustomerServicePhone(suppiorMobile);


        Long userCouponId = orderDO.getUserCouponId();
        if(userCouponId!=null){
           UserCouponResDTO res = couponService.qryCouponDetail(custNo,userCouponId);
            item.setUserCouponRes(res);
        }
        return CommonResult.success(item);
    }


    /**
     * 补偿判断
     * @param orderDO
     * @return
     */
    private OrderDO checkTimeOut(OrderDO orderDO) {

        Calendar cc = Calendar.getInstance();
        //订单创建时间
        cc.setTime(orderDO.getCreateTime());
        cc.add(Calendar.MINUTE, paramsService.getOrderTimeOut());
        Date now = new Date();

        //待支付状态并且支付时间超过30min--关闭订单
        if(0L==orderDO.getStatus() && cc.getTime().compareTo(now)<=0){
            //处理支付超时逻辑
            timeOutHandler.handlerTimeOutOrder(orderDO.getOrderSn());
            //重新刷新支付信息
            return orderService.qryOrderBySn(orderDO.getOrderSn());
        }
        return orderDO;
    }

    @ApiOperation("待支付订单统计")
    @RequestMapping(value = "/countOrder", method = RequestMethod.POST)
    public CommonResult<OrderCountResDTO> countOrder(@Validated @RequestBody OrderCountReqDTO orderCountReqDTO){
        OrderCountResDTO res = new OrderCountResDTO();
        Long o_results = orderService.countOrderByProductTypeAndStatus(orderCountReqDTO.getCustNo(),0L,ProductEnum.PAYMENT_IN_KIND,ProductEnum.MOBILE_RECHARGE,ProductEnum.OIL_CARD,ProductEnum.VIDEO);
        res.setUnPaymentCount(o_results != null ? o_results : 0);
        return CommonResult.success(res);
    }
}
