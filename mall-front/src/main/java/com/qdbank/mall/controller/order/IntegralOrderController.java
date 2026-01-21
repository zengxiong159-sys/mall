package com.qdbank.mall.controller.order;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qdbank.mall.annotation.CheckUser;
import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.constant.payment.event.IntegralOrderEvent;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.PaymentService;
import com.qdbank.mall.order.mapper.OrderMapper;
import com.qdbank.mall.request.order.IntegralOrderReqDTO;
import com.qdbank.mall.request.order.OrderIDReqDTO;
import com.qdbank.mall.request.order.QryUserIntegralOrderReqDTO;
import com.qdbank.mall.response.order.GenerateOrderResDTO;
import com.qdbank.mall.response.order.OrderResDTO;
import com.qdbank.mall.util.UserContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
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
@Api(tags = "integralOrderController", description = "积分兑换订单管理")
@RequestMapping("/order/integral")
@Slf4j
public class IntegralOrderController extends CommonBsn{

    @Autowired
    PaymentService paymentService;

    @Autowired
    OrderService orderService;


    @Autowired
    OrderMapper orderMapper;

    private ProductEnum PRODUCT_TYPE = ProductEnum.INTEGRAL;

    @CheckUser
    @ApiOperation("用户积分兑换券列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<OrderResDTO>> list(@RequestBody QryUserIntegralOrderReqDTO userOrderReqDTO) {
        //分页查询
        Page page = PageHelper.startPage(userOrderReqDTO.getPageNum(),userOrderReqDTO.getPageSize());
        //获取产品类型
        List<OrderResDTO> results =orderService.qryOrders(UserContextHolder.getUserDetails().getCustNo(),userOrderReqDTO.getStatus()+"",PRODUCT_TYPE);
        return CommonResult.success(CommonPage.restPage(page,results));
    }

   /* @CheckUser
    @ApiOperation("订单详细信息->订单编号")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public CommonResult getItem(@Validated @RequestBody OrderIDReqDTO commonIDReqDTO) {
        //获取产品类型
       OrderDO orderDO =orderService.qryOrderBySn(commonIDReqDTO.getOrderSn());
        return CommonResult.success(orderMapper.poToDo(orderDO));
    }*/
    @CheckUser
    @ApiOperation("订单取消->订单编号")
    @RequestMapping(value = "/cancle", method = RequestMethod.POST)
    public CommonResult<OrderResDTO> cancleOrder(@Validated @RequestBody OrderIDReqDTO commonIDReqDTO){
        Message message =paymentService.handlerOrder(commonIDReqDTO.getOrderSn()+"",IntegralOrderEvent.CANCEL,null);
        return CommonResult.success(orderMapper.poToDo((OrderDO) message.getHeaders().get(Constant.ORDER_STR)));
    }

    @CheckUser
    @ApiOperation("积分兑换生成订单")
    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    public CommonResult<GenerateOrderResDTO> generateOrder(@Validated @RequestBody IntegralOrderReqDTO orderReqDTO) {
        initUserDetail(orderReqDTO);
        Map params = new HashMap();
        params.put("integralOrder",orderReqDTO);
        Message message =paymentService.handlerOrder(null, IntegralOrderEvent.CREATE,params);
        OrderDO order = (OrderDO) message.getHeaders().get(Constant.ORDER_STR);
        //跳转地址
        String resUrl = order.getReqUrl();
        GenerateOrderResDTO result = new GenerateOrderResDTO();
        result.setJumpUrl(resUrl);
        return CommonResult.success(result, "下单成功");
    }













}
