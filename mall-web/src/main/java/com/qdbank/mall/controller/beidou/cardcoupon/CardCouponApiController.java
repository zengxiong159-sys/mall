package com.qdbank.mall.controller.beidou.cardcoupon;

import com.github.pagehelper.PageInfo;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.coupon.UserCouponService;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.response.coupon.UserCouponResDTO;
import com.qdbank.mall.response.order.orderdetail.OrderBaseDetailResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.qdbank.mall.request.coupon.UserCouponQueryReqDTO;

/**
 * @Author zengxiong
 * @Description 商城用户优惠券查询api
 * @Date 2021/11/24 15:43
 */
@RestController
@RequestMapping("/api/cardCoupon")
@Slf4j
public class CardCouponApiController {

    @Autowired
    private UserCouponService userCouponService;

    @Autowired
    private OrderService orderService;


    @RequestMapping(value = "/getCouponList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<PageInfo<UserCouponResDTO>> getCouponList(@RequestBody UserCouponQueryReqDTO dto) {
        return CommonResult.success(userCouponService.list(dto), Constant.NOT_ENCRYPT);
    }

    @RequestMapping(value = "/getOrderDetail", method = RequestMethod.POST)
    public CommonResult<OrderBaseDetailResDTO> orderDetail(@Validated @RequestBody CommonIDReqDTO orderDetailsReqDTO){
        return CommonResult.success(orderService.orderDetail(orderDetailsReqDTO.getId()),  Constant.NOT_ENCRYPT);
    }
}
