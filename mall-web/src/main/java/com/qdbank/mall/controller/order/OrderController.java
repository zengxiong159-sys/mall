package com.qdbank.mall.controller.order;

import com.github.pagehelper.PageInfo;
import com.qdbank.mall.annotation.ExcludeHandler;
import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.bo.AdminUserDetails;
import com.qdbank.mall.model.user.UserInfoDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.order.OrderExportReqDTO;
import com.qdbank.mall.request.order.OrderIDReqDTO;
import com.qdbank.mall.request.order.OrderLikeQueryReqDTO;
import com.qdbank.mall.response.order.*;
import com.qdbank.mall.response.order.orderdetail.OrderBaseDetailResDTO;
import com.qdbank.mall.response.send.SendReturnResDTO;
import com.qdbank.mall.send.SendReturnService;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.util.LoginUserContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
    @Autowired
    private SendReturnService sendReturnService;
    private final static String FILE_PATH = "/home/aomsapp/";
    @ApiOperation("订单列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<OrderResDTO>> list(@RequestBody OrderLikeQueryReqDTO orderLikeQueryReqDTO) {

        return CommonResult.success(CommonPage.restPage(orderService.list(orderLikeQueryReqDTO)));
    }

    @ExcludeHandler
    @ApiOperation(value="订单导出",produces="application/octet-stream")
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public CommonResult excleDownload(@Validated @RequestBody OrderExportReqDTO orderExportReqDTO){
        orderExportReqDTO.setFileLocalPath(FILE_PATH);
        UserDetails userDetails = LoginUserContextHolder.getUserDetails();
        AdminUserDetails adminUserDetails = (AdminUserDetails) userDetails;
        UserInfoDO loginUser = adminUserDetails.getUserInfoDO();
        orderExportReqDTO.setCreatedBy(loginUser.getNickName());
        return  orderService.excelExport(orderExportReqDTO);
    }

    @ApiOperation("订单详情->订单编号")
    @RequestMapping(value = "/orderDetail", method = RequestMethod.POST)
    public CommonResult<OrderBaseDetailResDTO> orderDetail(@Validated @RequestBody CommonIDReqDTO orderDetailsReqDTO){
      return CommonResult.success(orderService.orderDetail(orderDetailsReqDTO.getId()));
    }
    @RequestMapping(value = "/import/{type}",method = RequestMethod.POST)
    public CommonResult orderImport(MultipartFile file,@PathVariable("type") Integer type){
        return null;
    }

    @ApiOperation("订单撤销发货记录")
    @RequestMapping(value = "/sendReturnList", method = RequestMethod.POST)
    public CommonResult<List<SendReturnResDTO>> querySendReturnList(@RequestBody OrderIDReqDTO orderIDReqDTO){
        return CommonResult.success(sendReturnService.querySendReturn(orderIDReqDTO.getOrderSn()));
    }


}
