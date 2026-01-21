package com.qdbank.mall.controller.order;

import com.qdbank.mall.annotation.ExcludeHandler;
import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.model.order.OrderImportDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.order.OrderExportReqDTO;
import com.qdbank.mall.request.order.OrderLikeQueryReqDTO;
import com.qdbank.mall.request.order.SendOrderReqDTO;
import com.qdbank.mall.request.orderimport.OrderImportReqDTO;
import com.qdbank.mall.request.send.SendReturnReqDTO;
import com.qdbank.mall.response.merchant.MerchantResDTO;
import com.qdbank.mall.response.order.*;
import com.qdbank.mall.response.send.SendReturnResDTO;
import com.qdbank.mall.send.SendReturnService;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.util.TimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/15 15:34
 * @Version 1.0
 **/
@RestController
@Api(tags = "OrderController", description = "订单管理")
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Qualifier("cmsAdminServiceImpl")
    @Autowired
    private UmsAdminService umsAdminService;
    private static final String FILE_PATH = "/home/acmsapp/";
    @Autowired
    private SendReturnService sendReturnService;
    @ApiOperation("订单列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<OrderResDTO>> list(@RequestBody OrderLikeQueryReqDTO orderLikeQueryReqDTO) {
        MerchantResDTO merchantResDTO = umsAdminService.selectUserNameAndMerchantNo();
        Date begin =orderLikeQueryReqDTO.getStartTime();
        Date end =orderLikeQueryReqDTO.getEndTime();
        check(begin,end,false);
        orderLikeQueryReqDTO.setMerchantNo(merchantResDTO.getMerchantNo());
        return CommonResult.success(CommonPage.restPage(orderService.list(orderLikeQueryReqDTO)));
    }

    @ApiOperation("订单详细信息->订单编号")
    @RequestMapping(value = "/realDetail", method = RequestMethod.POST)
    public CommonResult<RealOrderDetailResDTO> realDetail(@Validated @RequestBody CommonIDReqDTO orderDetailsReqDTO) {
        return CommonResult.success(orderService.realDetail(orderDetailsReqDTO.getId()));
    }

    @ApiOperation("订单详细信息->订单编号")
    @RequestMapping(value = "/mobileChargedetail", method = RequestMethod.POST)
    public CommonResult<MobileRechargeOrderDetailResDTO> mobileDetail(@Validated @RequestBody CommonIDReqDTO orderDetailsReqDTO) {
        return CommonResult.success(orderService.mobileDetail(orderDetailsReqDTO.getId()));
    }

    @ExcludeHandler
    @ApiOperation(value="订单导出",produces="application/octet-stream")
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public CommonResult excleDownload(@Validated @RequestBody OrderExportReqDTO orderExportReqDTO){
        Date begin =orderExportReqDTO.getStartTime();
        Date end =orderExportReqDTO.getEndTime();
        check(begin,end,true);
        MerchantResDTO merchantResDTO = umsAdminService.selectUserNameAndMerchantNo();
        orderExportReqDTO.setMerchantNo(merchantResDTO.getMerchantNo());
        orderExportReqDTO.setCreatedBy(merchantResDTO.getAdminName());
        orderExportReqDTO.setFileLocalPath(FILE_PATH);
        return orderService.excelExport(orderExportReqDTO);
    }

    private void check(Date begin, Date end,boolean must) {
        if(begin==null && must){
            throw new ApiException(ResultCode.BEGAIN_TIEM_ERROR);
        }

        if(end == null && must){
            throw new ApiException(ResultCode.END_TIEM_ERROR);
        }

        if(begin != null && end!=null){
            if(begin.compareTo(end)>0){
                throw new ApiException(ResultCode.BEGAIN_END_TIEM_ERROR);
            }

            //超过90天
            if(must && TimeUtil.overDate(begin,end, Calendar.DATE,90)){
                throw new ApiException(ResultCode.BEGAIN_END_TIEM_OVER_ERROR);
            }
        }
    }


    @ApiOperation("订单发货")
    @RequestMapping(value = "/sendOrder", method = RequestMethod.POST)
    public CommonResult sendOrder(@Validated @RequestBody SendOrderReqDTO sendOrderReqDTO) {
        return CommonResult.success(orderService.sendOrder(sendOrderReqDTO),Constant.NOT_ENCRYPT);
    }

    @ApiOperation("订单待审核和代发货")
    @RequestMapping(value = "/orderStatus", method = RequestMethod.POST)
    public CommonResult<OrderStatusResDTO> orderStatus() {
        return CommonResult.success(orderService.orderStatus(),Constant.NOT_ENCRYPT);
    }

    @ApiOperation("订单批量发货")
    @RequestMapping(value = "/batchSendOrder", method = RequestMethod.POST)
    public CommonResult batchSendOrder(OrderImportReqDTO orderImportReqDTO) {
        return CommonResult.success(orderService.batchSendOrder(orderImportReqDTO),Constant.NOT_ENCRYPT);
    }

    @ApiOperation("订单撤销发货")
    @RequestMapping(value = "/sendReturnOrder", method = RequestMethod.POST)
    public CommonResult sendReturnOrder(@Validated @RequestBody SendReturnReqDTO sendReturnReqDTO) {
        return CommonResult.success(orderService.sendReturnOrder(sendReturnReqDTO),Constant.NOT_ENCRYPT);
    }

    @ApiOperation("订单撤销发货记录")
    @RequestMapping(value = "/sendReturnList/{orderSN}", method = RequestMethod.POST)
    public CommonResult<List<SendReturnResDTO>> querySendReturnList(@PathVariable("orderSN") String orderSN){
        return CommonResult.success(sendReturnService.querySendReturn(orderSN));
    }

}
