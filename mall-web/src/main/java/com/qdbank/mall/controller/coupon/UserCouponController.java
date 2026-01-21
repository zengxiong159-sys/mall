package com.qdbank.mall.controller.coupon;

import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.coupon.UserCouponService;
import com.qdbank.mall.request.coupon.UpdateUserCouponStatusReqDTO;
import com.qdbank.mall.request.coupon.UserCouponQueryReqDTO;
import com.qdbank.mall.response.coupon.UserCouponResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author zengxiong
 * @Description 用户优惠券查询
 * @Date 2021/8/19 14:33
 */
@Controller
@Api(tags = "UserCouponController", description = "用户优惠券管理")
@RequestMapping("/userCoupon")
@Slf4j
public class UserCouponController {
    @Autowired
    private UserCouponService userCouponService;

    @ApiOperation("用户优惠券列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<UserCouponResDTO>> list(@RequestBody UserCouponQueryReqDTO userCouponQueryReqDTO) {
        return CommonResult.success(CommonPage.restPage(userCouponService.list(userCouponQueryReqDTO)));
    }

    @ApiOperation(value = "更新用户使用券状态")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@Validated @RequestBody UpdateUserCouponStatusReqDTO updateUserCouponStatusReqDTO) {
        int count = userCouponService.updateStatus(updateUserCouponStatusReqDTO);
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }
}
