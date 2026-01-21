package com.qdbank.mall.controller.third;

import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.coupon.CouponService;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.coupon.UserCouponReqDTO;
import com.qdbank.mall.request.coupon.XcxCouponReqDTO;
import com.qdbank.mall.response.coupon.UserCouponCountForXcxResDTO;
import com.qdbank.mall.response.coupon.UserCouponResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName PaymentController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/21 9:44
 * @Version 1.0
 **/
@RestController
@Api(tags = "xcxCouponController", description = "小程序")
@RequestMapping("/third/xcx")
@Slf4j
public class XcxCouponController {

    @Autowired
    CouponService couponService;



    @ApiOperation("个人优惠券券统计->客户号")
    @RequestMapping(value = "/couponCount", method = RequestMethod.POST)
    public CommonResult<UserCouponCountForXcxResDTO> couponCount(@Valid @RequestBody XcxCouponReqDTO req) {//查询用户待使用优惠券数量

        // 查询类型,1:可用优惠券,2:已用优惠券,3:过期优惠券,4:所有类型优惠券
        UserCouponReqDTO userCouponReqDTO = new UserCouponReqDTO();

        //可用优惠券
        userCouponReqDTO.setStatus(convertStataus(req.getType()));
        List<UserCouponResDTO> result =couponService.countUseCounpon(req.getUserId(),userCouponReqDTO);

        UserCouponCountForXcxResDTO res = new UserCouponCountForXcxResDTO();
        res.setCouponNum(result!=null?result.size():0);
        return CommonResult.success(res);
    }


    /**
     * xcx
     * 1:可用优惠券,  0 待使用
     * 2:已用优惠券, 1 已使用
     * 3:过期优惠券, 2 已过期
     * 4:所有类型优惠券
     *                  3 已作废
     */
    private Long convertStataus(String beforeStatus){
        if("1".equals(beforeStatus)){
            return 0L;
        }else if("2".equals(beforeStatus)){
            return 1L;
        }else if("3".equals(beforeStatus)){
            return 2L;
        }else if("4".equals(beforeStatus)){
            return null;
        }
        return null;
    }














}
