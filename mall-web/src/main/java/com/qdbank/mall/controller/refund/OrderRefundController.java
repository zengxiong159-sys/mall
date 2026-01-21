package com.qdbank.mall.controller.refund;

import com.qdbank.mall.annotation.ExcludeHandler;
import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.bo.AdminUserDetails;
import com.qdbank.mall.model.user.UserInfoDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.refund.OrderRefundService;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.CommonStringIDReqDTO;
import com.qdbank.mall.request.refund.OrderRefundExportReqDTO;
import com.qdbank.mall.request.refund.OrderRefundLikeQueryReqDTO;
import com.qdbank.mall.response.order.RealOrderDetailResDTO;
import com.qdbank.mall.response.refund.OrderRefundResDTO;
import com.qdbank.mall.util.LoginUserContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    private OrderService orderService;
    private final static String FILE_PATH = "/home/aomsapp/";
    @ApiOperation("退款订单列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<OrderRefundResDTO>> list(@Validated @RequestBody OrderRefundLikeQueryReqDTO orderRefundLikeQueryReqDTO) {

        return CommonResult.success(CommonPage.restPage(orderRefundService.list(orderRefundLikeQueryReqDTO)));
    }

    @ApiOperation("异常退款订单列表")
    @RequestMapping(value = "/errorlist", method = RequestMethod.POST)
    public CommonResult<CommonPage<OrderRefundResDTO>> errorlist(@Validated @RequestBody OrderRefundLikeQueryReqDTO orderRefundLikeQueryReqDTO) {
        orderRefundLikeQueryReqDTO.setErrorFlag("1");
        return CommonResult.success(CommonPage.restPage(orderRefundService.list(orderRefundLikeQueryReqDTO)));
    }

    @ApiOperation("异常退款订单再次发起退款")
    @RequestMapping(value = "/refundAgain", method = RequestMethod.POST)
    public CommonResult refundAgain(@Validated @RequestBody CommonStringIDReqDTO commonIDReqDTO) {
        orderRefundService.refundError(commonIDReqDTO.getId());
        return CommonResult.success("1");
    }

    @ApiOperation("退款订单详细信息->退款流水号")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public CommonResult<RealOrderDetailResDTO> getItem(@Validated @RequestBody CommonStringIDReqDTO commonIDReqDTO) {

        return CommonResult.success(orderService.realRefundDetail(commonIDReqDTO.getId(),true));
    }

    @ExcludeHandler
    @ApiOperation(value="退款订单导出",produces="application/octet-stream")
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public CommonResult excleDownload(HttpServletResponse response, @Validated @RequestBody OrderRefundExportReqDTO orderExportReqDTO){
        orderExportReqDTO.setFileLocalPath(FILE_PATH);
        UserDetails userDetails = LoginUserContextHolder.getUserDetails();
        AdminUserDetails adminUserDetails = (AdminUserDetails) userDetails;
        UserInfoDO loginUser = adminUserDetails.getUserInfoDO();
        orderExportReqDTO.setCreatedBy(loginUser.getNickName());
        return orderRefundService.excelExport(response,orderExportReqDTO);
    }
}
