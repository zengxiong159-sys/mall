package com.qdbank.mall.controller.coupon;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qdbank.mall.annotation.CheckUser;
import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.StatusEnum;
import com.qdbank.mall.bo.UserDetails;
import com.qdbank.mall.coupon.CouponService;
import com.qdbank.mall.enums.CouponTypeEnum;
import com.qdbank.mall.model.usercoupon.UserCouponDO;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.coupon.*;
import com.qdbank.mall.response.coupon.CouponResDTO;
import com.qdbank.mall.response.coupon.UserCouponCountResDTO;
import com.qdbank.mall.response.coupon.UserCouponResDTO;
import com.qdbank.mall.response.coupon.UserCouponStatusResDTO;
import com.qdbank.mall.util.UserContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName CouponController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/12 18:44
 * @Version 1.0
 **/
@RestController
@Api(tags = "CouponController", description = "优惠券")
@RequestMapping("/coupon")
@Slf4j
public class CouponController {

    @Autowired
    CouponService couponService;


    @CheckUser
    @ApiOperation("个人优惠券券统计->客户号")
    @RequestMapping(value = "/couponCount", method = RequestMethod.POST)
    public CommonResult<UserCouponCountResDTO> couponCount() {//查询用户待使用优惠券数量
        UserCouponCountResDTO res = new UserCouponCountResDTO();
        UserDetails userDetails  =UserContextHolder.getUserDetails();

        UserCouponReqDTO userCouponReqDTO = new UserCouponReqDTO();
        //可用优惠券
        userCouponReqDTO.setStatus(0L);
        List<UserCouponResDTO> result =couponService.countUseCounpon(userDetails.getCustNo(),userCouponReqDTO);
        res.setPrepareUseCount(result!=null?result.size():0);

        //已使用优惠券
        userCouponReqDTO.setStatus(1L);
         result =couponService.countUseCounpon(userDetails.getCustNo(),userCouponReqDTO);
        res.setHasUseCount(result!=null?result.size():0);

        //可用优惠券
        userCouponReqDTO.setStatus(2L);
        result =couponService.countUseCounpon(userDetails.getCustNo(),userCouponReqDTO);
        res.setExpireCount(result!=null?result.size():0);
        return CommonResult.success(res);
    }

    @ApiOperation("优惠券详情->优惠券编号")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public CommonResult<CouponResDTO> getItem(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        return CommonResult.success(couponService.qryCouponDetailById(commonIDReqDTO.getId()));
    }

    @CheckUser
    @ApiOperation("用户卡券列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<UserCouponResDTO>> list(@RequestBody UserCouponReqDTO userCouponReqDTO) {
        //分页查询
        Page page = PageHelper.startPage(userCouponReqDTO.getPageNum(),userCouponReqDTO.getPageSize());
        List<UserCouponResDTO> resuts=couponService.qryCouponDetailPage(UserContextHolder.getUserDetails().getCustNo(),userCouponReqDTO);
        //返回用户所有卡券积分券+优惠券
        return CommonResult.success(CommonPage.restPage(page,resuts));
    }

    @CheckUser
    @ApiOperation("指定商品卡券列表")
    @RequestMapping(value = "/productList", method = RequestMethod.POST)
    public CommonResult<List<UserCouponResDTO>> productList(@Validated @RequestBody UserProductCouponReqDTO userProductCouponReqDTO) {
        String custNo = UserContextHolder.getUserDetails().getCustNo();
        Long productId = userProductCouponReqDTO.getProductId();
        Long productSkuId = userProductCouponReqDTO.getProductSkuId();

        UserCouponReqDTO userCouponReqDTO = new UserCouponReqDTO();

        userCouponReqDTO.setProductId(productId);
        userCouponReqDTO.setProductSkuId(productSkuId);
        userCouponReqDTO.setStatus(0L);
        List<UserCouponResDTO> list=couponService.qryCouponDetailPage(custNo, userCouponReqDTO);

        UserCouponDO userCouponDO = new UserCouponDO();
        userCouponDO.setProductId(productId);
        userCouponDO.setProductSkuId(productSkuId);
        userCouponDO.setCustNo(custNo);
        userCouponDO.setStatus(StatusEnum.USER_COUPON_NOT_USED.getCode());
        userCouponDO.setCouponType(CouponTypeEnum.PREFECTURE_CASH_COUPON.getCode());

        //查询该客户号对应的所有待使用状态的指定专区现金优惠券列表
        List<UserCouponResDTO> userCouponResDTOS = couponService.queryPrefectureUserCouponList(userCouponDO);
        list.addAll(userCouponResDTOS);

        //按面值排序
        if(!CollectionUtils.isEmpty(list)){
            list.sort((o1, o2) -> (o2.getCouponAmount().compareTo(o1.getCouponAmount())));
        }
        //返回用户所有卡券积分券+优惠券
        return CommonResult.success(list);
    }

    @ApiOperation("积分卡券列表")
    @RequestMapping(value = "/integralList", method = RequestMethod.POST)
    public CommonResult<List<CouponResDTO>> list() {
        CouponQueryReqDTO couponLikeQueryReqDTO = new CouponQueryReqDTO();
        couponLikeQueryReqDTO.setCouponType(0L);
        couponLikeQueryReqDTO.setProductStatus(1L);
        return CommonResult.success(couponService.qryIntegralCouponPage(couponLikeQueryReqDTO));
    }


    @ApiOperation("小程序用户商城卡券列表")
    @RequestMapping(value = "/userCouponlist", method = RequestMethod.POST)
    public CommonResult<CommonPage<UserCouponResDTO>> userCouponlist(@RequestBody UserCouponListReqDTO userCouponReqDTO) {
        //分页查询
        Page page = PageHelper.startPage(userCouponReqDTO.getPageNum(),userCouponReqDTO.getPageSize());
        List<UserCouponResDTO> resuts=couponService.selectCouponDetailPage(userCouponReqDTO);
        //返回用户所有卡券积分券+优惠券
        return CommonResult.success(CommonPage.restPage(page,resuts));
    }

    @ApiOperation("优惠券状态查询")
    @RequestMapping(value = "/couponStatus", method = RequestMethod.POST)
    public CommonResult<UserCouponStatusResDTO> couponStatus(@Validated @RequestBody UserCouponStatusReqDTO userCouponStatusReqDTO) {
        return CommonResult.success(couponService.queryCouponStatus(userCouponStatusReqDTO));
    }
}
