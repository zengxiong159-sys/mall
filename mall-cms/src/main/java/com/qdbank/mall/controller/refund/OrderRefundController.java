package com.qdbank.mall.controller.refund;

import com.qdbank.mall.annotation.ExcludeHandler;
import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.refund.OrderRefundService;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.CommonStringIDReqDTO;
import com.qdbank.mall.request.refund.OrderRefundExportReqDTO;
import com.qdbank.mall.request.refund.OrderRefundHandleReqDTO;
import com.qdbank.mall.request.refund.OrderRefundLikeQueryReqDTO;
import com.qdbank.mall.response.merchant.MerchantResDTO;
import com.qdbank.mall.response.order.RealOrderDetailResDTO;
import com.qdbank.mall.response.refund.OrderRefundResDTO;
import com.qdbank.mall.user.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName OrderRefundController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/17 19:21
 * @Version 1.0
 **/
@RestController
@Api(tags = "OrderRefundController", description = "退款订单管理")
@RequestMapping("/refund")
@Slf4j
public class OrderRefundController {
    @Autowired
    private OrderRefundService orderRefundService;

    @Autowired
    OrderService orderService;
    private static final String FILE_PATH = "/home/acmsapp/";
    @Autowired
    @Qualifier("cmsAdminServiceImpl")
    private UmsAdminService umsAdminService;
    @ApiOperation("退款订单列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<OrderRefundResDTO>> list(@Validated @RequestBody OrderRefundLikeQueryReqDTO orderRefundLikeQueryReqDTO) {
        MerchantResDTO merchantResDTO = umsAdminService.selectUserNameAndMerchantNo();
        orderRefundLikeQueryReqDTO.setMerchantNo(merchantResDTO.getMerchantNo());
        orderRefundLikeQueryReqDTO.setRefundSerial(orderRefundLikeQueryReqDTO.getRefundSerialNo());
        return CommonResult.success(CommonPage.restPage(orderRefundService.list(orderRefundLikeQueryReqDTO)));
    }

    @ApiOperation("退款订单详细信息->退款流水号")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public CommonResult<RealOrderDetailResDTO> getItem(@Validated @RequestBody CommonStringIDReqDTO commonIDReqDTO) {
        return CommonResult.success(orderService.realRefundDetail(commonIDReqDTO.getId(),false));
    }

    @ExcludeHandler
    @ApiOperation(value="退款订单导出",produces="application/octet-stream")
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public CommonResult excleDownload(HttpServletResponse response, @Validated @RequestBody OrderRefundExportReqDTO OrderRefundExportReqDTO){
        MerchantResDTO merchantResDTO = umsAdminService.selectUserNameAndMerchantNo();
        OrderRefundExportReqDTO.setMerchantNo(merchantResDTO.getMerchantNo());
        OrderRefundExportReqDTO.setMerchantNo(merchantResDTO.getMerchantNo());
        OrderRefundExportReqDTO.setCreatedBy(merchantResDTO.getAdminName());
        OrderRefundExportReqDTO.setFileLocalPath(FILE_PATH);
        return orderRefundService.excelExport(response,OrderRefundExportReqDTO);
    }

    @ApiOperation("退款处理->退款流水号")
    @RequestMapping(value = "/orderHandle", method = RequestMethod.POST)
    public CommonResult  orderHandle(@Validated @RequestBody OrderRefundHandleReqDTO orderRefundHandleReqDTO) {
        log.info("退款流水号:{}处理开始",orderRefundHandleReqDTO.getRefundSerialNo());
        MerchantResDTO merchantResDTO = umsAdminService.selectUserNameAndMerchantNo();
        BeanUtils.copyProperties(merchantResDTO,orderRefundHandleReqDTO);
        int count = orderRefundService.orderHandle(orderRefundHandleReqDTO);
        if (count == 1){
            return CommonResult.success(count);
        }
        log.info("退款流水号:{}处理结束",orderRefundHandleReqDTO.getRefundSerialNo());
        return CommonResult.failed();
    }
}
