package com.qdbank.mall.controller.order;

import com.qdbank.mall.annotation.CheckUser;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.order.PayService;
import com.qdbank.mall.request.order.OrderQueryReqDTO;
import com.qdbank.mall.request.order.OrderUpdateStatusReqDTO;
import com.qdbank.mall.request.order.PayReqDTO;
import com.qdbank.mall.response.order.OrderQueryResDTO;
import com.qdbank.mall.response.order.OrderUpdateStatusResDTO;
import com.qdbank.mall.response.order.PayResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName PayController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/8/8 16:43
 * @Version 1.0
 **/
@RestController
@Api(tags = "PayController", description = "订单支付接口")
@RequestMapping("/pay")
@Slf4j
@RefreshScope
public class PayController {
    @Autowired
    private PayService payService;
    @Value("${open.flag:}")
    private String openFlag;
    @ApiOperation("订单支付接口")
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public CommonResult<PayResDTO> payOrder(@RequestBody PayReqDTO payReqDTO){
        return CommonResult.success(payService.payOrder(payReqDTO));
    }

    @ApiOperation("支付订单详情")
    @RequestMapping(value = "/orderDetail", method = RequestMethod.POST)
    public CommonResult<OrderQueryResDTO> orderQuery(@RequestBody OrderQueryReqDTO orderQueryReqDTO){
        return CommonResult.success(payService.orderQuery(orderQueryReqDTO));
    }

    @ApiOperation("修改订单状态")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public CommonResult<OrderUpdateStatusResDTO> updateStatus(@RequestBody OrderUpdateStatusReqDTO orderUpdateStatusReqDTO){
        return CommonResult.success(payService.updateStatus(orderUpdateStatusReqDTO));
    }

    @ApiOperation(value = "批量文件上传影像平台接口",hidden = true)
    @RequestMapping(value = "/batchMakeFile", method = RequestMethod.POST)
    public CommonResult batchMakeFile(){
        payService.batchMakeFile();
        return CommonResult.success(null);
    }

    @ApiOperation("返回前端开关")
    @RequestMapping(value = "/openFlag", method = RequestMethod.POST)
    public CommonResult<String> openFlag(){
        return CommonResult.success(openFlag);
    }

    @ApiOperation("获取密码随机数")
    @RequestMapping(value = "/getSubmitRandom", method = RequestMethod.POST)
    public CommonResult<Map<String,Object> > getSubmitRandom(){
        Map<String,Object> map = new HashMap<>();
        map.put("errorCode", "0");
        map.put("random", UUID.randomUUID().toString().replaceAll("-",""));
        return CommonResult.success(map, Constant.ENCRYPT);
}
}
