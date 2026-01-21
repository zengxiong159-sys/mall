package com.qdbank.mall.feign;

import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.request.CommonStringIDReqDTO;
import com.qdbank.mall.request.order.OrderIDReqDTO;
import com.qdbank.mall.request.product.ProductIdsReqDTO;
import com.qdbank.mall.response.order.OrderResDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "wechat-front")
public interface MallFeignService {
    @RequestMapping(value = "/wechatmall/third/bank/qryOrder", method = RequestMethod.POST)
    public CommonResult qryOrder(@RequestBody OrderIDReqDTO reqDTO);

    @RequestMapping(value = "/wechatmall/third/bank/qryRechargeOrder", method = RequestMethod.POST)
    public CommonResult  qryRechargeOrder(@RequestBody OrderIDReqDTO reqDTO);

    @RequestMapping(value = "/wechatmall/third/backmanager/errorRefund", method = RequestMethod.POST)
    public CommonResult errorRefund(@RequestBody CommonStringIDReqDTO commonStringIDReqDTO);

    @RequestMapping(value = "/wechatmall/third/backmanager/ok", method = RequestMethod.POST)
    public CommonResult<OrderResDTO> confirmOrderReceive(@RequestBody OrderIDReqDTO commonIDReqDTO);

    @RequestMapping(value = "/wechatmall/third/backmanager/no", method = RequestMethod.POST)
    public CommonResult<OrderResDTO> applyRefund(@RequestBody OrderIDReqDTO orderIDReqDTO);

    @RequestMapping(value = "/wechatmall/external/productDown", method = RequestMethod.POST)
    public CommonResult<OrderResDTO> productDown(@RequestBody ProductIdsReqDTO orderIDReqDTO);

    /**
     * 中台交易上传影像平台任务
     * @return
     */
    @RequestMapping(value = "/wechatmall/pay/batchMakeFile", method = RequestMethod.POST)
    public CommonResult batchMakeFile();
}
