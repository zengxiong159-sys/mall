package com.qdbank.mall.controller.order;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qdbank.mall.address.AreaService;
import com.qdbank.mall.annotation.CheckUser;
import com.qdbank.mall.annotation.LockName;
import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.common.FileService;
import com.qdbank.mall.common.ParamsService;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.constant.payment.event.RealOrderHandlerEvent;
import com.qdbank.mall.constent.payment.RefundStatausEnum;
import com.qdbank.mall.coupon.CouponService;
import com.qdbank.mall.dao.refund.RefundDao;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.orderrefund.OrderRefundDO;
import com.qdbank.mall.model.refundsetting.RefundsettingDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.PaymentService;
import com.qdbank.mall.order.business.handler.RealPaymentHandler;
import com.qdbank.mall.order.mapper.OrderMapper;
import com.qdbank.mall.order.mapper.RefundMapper;
import com.qdbank.mall.prefecture.PrefectureService;
import com.qdbank.mall.request.order.*;
import com.qdbank.mall.request.refund.ApplyOrderRefundReqDTO;
import com.qdbank.mall.response.area.FreightFeeResDTO;
import com.qdbank.mall.response.order.GenerateOrderResDTO;
import com.qdbank.mall.response.order.OrderResDTO;
import com.qdbank.mall.response.refund.OrderRefundResDTO;
import com.qdbank.mall.response.refundsetting.RefundSettingResDTO;
import com.qdbank.mall.util.UserContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/15 15:34
 * @Version 1.0
 **/
@RestController
@Api(tags = "realOrderController", description = "实物订单管理")
@RequestMapping("/order/real")
@Slf4j
public class RealOrderController extends CommonBsn{

    @Autowired
    PaymentService paymentService;

    @Autowired
    OrderService orderService;

    @Autowired
    RealPaymentHandler realPaymentHandler;

    @Autowired
    RefundMapper refundMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    AreaService areaService;

    @Autowired
    PrefectureService prefectureService;

    @Autowired
    CouponService couponService;

    @Autowired
    FileService fileService;

    @Autowired
    ParamsService paramsService;

    @Autowired
    RefundDao refundDao;

    private ProductEnum PRODUCT_TYPE = ProductEnum.PAYMENT_IN_KIND;

    @ApiOperation("订单列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<OrderResDTO>> list(@Validated@RequestBody QryUserRealOrderReqDTO userOrderReqDTO) {
        //分页查询
        Page page = PageHelper.startPage(userOrderReqDTO.getPageNum(),userOrderReqDTO.getPageSize());
        //获取产品类型
        List<OrderResDTO> results =orderService.qryOrders(UserContextHolder.getUserDetails().getCustNo(),userOrderReqDTO.getStatus(),PRODUCT_TYPE);
        return CommonResult.success(CommonPage.restPage(page,results));
    }

    @ApiOperation("退款订单列表")
    @RequestMapping(value = "/refundList", method = RequestMethod.POST)
    public CommonResult<CommonPage<OrderRefundResDTO>> refundList(@RequestBody QryUserRealOrderReqDTO userOrderReqDTO) {
        //分页查询
        Page page = PageHelper.startPage(userOrderReqDTO.getPageNum(),userOrderReqDTO.getPageSize());
        //获取产品类型
        List<OrderRefundResDTO> results =orderService.qryRefundOrderList(UserContextHolder.getUserDetails().getCustNo());
        return CommonResult.success(CommonPage.restPage(page,results));
    }

   /* private void handlerRefund(OrderResDTO item) {
        Long refundStatus =item.getRefundStatus();
        if(!refundStatus.equals(com.qdbank.mall.constent.payment.RefundStatausEnum.INIT.refundStatus)){
            List<ProductPicUrlDO> urllist=fileService.qryPicById(item.getRefundSerialNo());
            if(!CollectionUtils.isEmpty(urllist)){
                String[] urls = new String[urllist.size()];
                for(int i =0;i<urls.length;i++){
                    urls[i]=urllist.get(i).getPicUrl();
                }
                item.setProofPics(urls);
            }
        }
    }*/

    /*@CheckUser
    @ApiOperation("待收货订单数量查询->客户号")
    @RequestMapping(value = "/orderCount", method = RequestMethod.POST)
    public Integer orderCount(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        UserOrderReqDTO userOrderReqDTO = new UserOrderReqDTO();

        userOrderReqDTO.setCustNo(UserContextHolder.getUserDetails().getCustNo());
        //已发货状态
        userOrderReqDTO.setStatus(2L);
        //获取产品类型
        List<OrderResDTO> results =orderService.qryOrders(PRODUCT_TYPE,userOrderReqDTO);
        if(results!=null){
            return results.size();
        }
        return 0;
    }*/




    @CheckUser
    @ApiOperation("订单取消->订单编号")
 //   @Lock(leaseTime = 10, timeUnit = TimeUnit.SECONDS,name = "ORDER_LOCK")
    @RequestMapping(value = "/cancle", method = RequestMethod.POST)
    public CommonResult<OrderResDTO> cancleOrder(@Validated @RequestBody @LockName OrderIDReqDTO commonIDReqDTO){
        Message message =paymentService.handlerOrder(commonIDReqDTO.getOrderSn()+"", RealOrderHandlerEvent.CANCEL,null);
        return CommonResult.success(orderMapper.poToDo((OrderDO) message.getHeaders().get(Constant.ORDER_STR)));
    }
    @CheckUser
    @ApiOperation("确认收货")
    @RequestMapping(value = "/confirmOrderReceive", method = RequestMethod.POST)
    public CommonResult<OrderResDTO> confirmOrderReceive(@Validated @RequestBody OrderIDReqDTO commonIDReqDTO){
        Message message =paymentService.handlerOrder(commonIDReqDTO.getOrderSn()+"", RealOrderHandlerEvent.USER_CHECK,null);
        return CommonResult.success(orderMapper.poToDo((OrderDO) message.getHeaders().get(Constant.ORDER_STR)));
    }
    @CheckUser
    @ApiOperation("申请退款")
    @RequestMapping(value = "/applyRefund", method = RequestMethod.POST)
    public CommonResult<OrderRefundResDTO> applyRefund(@Validated @RequestBody ApplyOrderRefundReqDTO applyOrderRefundReqDTO){

        OrderDO orderDO =orderService.qryOrderBySn(applyOrderRefundReqDTO.getOrderSn());
        if (orderDO==null){
            throw new ApiException(ResultCode.USER_ORDER_HAS_NOT_FOUND);
        }
        OrderRefundDO orderRefundDO  =refundMapper.orderToRefund(orderDO);

        //退款原因

        RefundsettingDO refundsetting =realPaymentHandler.refundSettings(Long.parseLong(applyOrderRefundReqDTO.getReason()));
        if(refundsetting!=null){
             orderRefundDO.setReason(refundsetting.getRefundReason());
        }
        //退款类型
        orderRefundDO.setRefundType(Long.parseLong(applyOrderRefundReqDTO.getRefundType()));
        //凭证图片
        String pics =applyOrderRefundReqDTO.getProofPics();
        if(StringUtils.hasText(pics)){
            orderRefundDO.setProofPicsUrl(pics.split(","));
        }
        //退款说明
        orderRefundDO.setRefundNote(applyOrderRefundReqDTO.getRefundNote());
        //待审核
        orderRefundDO.setRefundStatus(0L);

        Map params = new HashMap();
        params.put("applyOrderRefundReqDTO",orderRefundDO);

        Message message =paymentService.handlerOrder(orderDO, RealOrderHandlerEvent.APPLY_REFUND,params);
        return CommonResult.success(refundMapper.refundPotoDo(orderRefundDO));
    }
    @CheckUser
    @ApiOperation("撤销申请退款")
    @RequestMapping(value = "/cancelApplyRefund", method = RequestMethod.POST)
    public CommonResult<OrderResDTO> applyRefund(@Validated @RequestBody OrderIDReqDTO orderIDReqDTO){
        Message message =paymentService.handlerOrder(orderIDReqDTO.getOrderSn()+"", RealOrderHandlerEvent.CANCEL_REFUND,null);
        return CommonResult.success(orderMapper.poToDo((OrderDO) message.getHeaders().get(Constant.ORDER_STR)));
    }
    @CheckUser
    @ApiOperation("实物生成订单")
    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    public CommonResult<GenerateOrderResDTO> generateOrder(@Validated @RequestBody RealOrderReqDTO orderReqDTO) {
        initUserDetail(orderReqDTO);
        Map params = new HashMap();
        params.put("realOrder",orderReqDTO);
        Message message =paymentService.handlerOrder(null, RealOrderHandlerEvent.CREATE,params);
        OrderDO order = (OrderDO) message.getHeaders().get(Constant.ORDER_STR);
        if(orderReqDTO.getIntegralDeduct() != null ){
            order.setIntegralDeduct(orderReqDTO.getIntegralDeduct());
        }
        //跳转地址
        String resUrl = order.getReqUrl();
        GenerateOrderResDTO result = new GenerateOrderResDTO();
        result.setJumpUrl(resUrl);
        return CommonResult.success(result, "下单成功");
    }


    @ApiOperation("运费换算")
    @RequestMapping(value = "/getfreightAmt", method = RequestMethod.POST)
    public CommonResult<FreightFeeResDTO> freight(@Validated @RequestBody FreightFeeReqDTO freightFeeReqDTO){
        FreightFeeResDTO fee =realPaymentHandler.freight(freightFeeReqDTO.getReceiverProvince(),freightFeeReqDTO.getProductCount(),freightFeeReqDTO.getFreightTemplateId(),null);
        return CommonResult.success(fee);
    }

    @ApiOperation("退款原因列表")
    @RequestMapping(value = "/qryRefundSettings", method = RequestMethod.POST)
    public CommonResult<List<RefundSettingResDTO>> qryRefundSettings(){
        List<RefundsettingDO> list=realPaymentHandler.refundSettings();
        return CommonResult.success(refundMapper.refundSettingPoToDoList(list));
    }


    @ApiOperation("退款订单详细信息->退款流水号")
    @RequestMapping(value = "/refundDetail", method = RequestMethod.POST)
    public CommonResult<OrderRefundResDTO> getItem(@Validated @RequestBody OrderRefundIDReqDTO commonIDReqDTO) {
        String refundSerialNo=commonIDReqDTO.getRefundSerialNo();
        String orderSn =commonIDReqDTO.getOrderSn();
        //上传订单号获取成功那笔
        if(!StringUtils.hasText(refundSerialNo) && StringUtils.hasText(orderSn)){
            OrderRefundDO refund  =refundDao.qryNewOrderRefundByOrderSn(orderSn);
            if(refund==null || !RefundStatausEnum.REFUND.refundStatus.equals(refund.getRefundStatus())){
                throw new ApiException(ResultCode.ORDER_REFUND_NULL_EXCEPTION);
            }
            refundSerialNo = refund.getRefundSerial();
        }

        OrderRefundResDTO res = orderService.realRefundDetail(refundSerialNo);
        return CommonResult.success(res);
    }


















}
