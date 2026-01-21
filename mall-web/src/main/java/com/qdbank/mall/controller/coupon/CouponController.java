package com.qdbank.mall.controller.coupon;

import com.qdbank.mall.annotation.ExcludeHandler;
import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.api.ServiceEnum;
import com.qdbank.mall.coupon.CouponService;
import com.qdbank.mall.enums.CouponDistributeWayEnum;
import com.qdbank.mall.enums.CouponTypeEnum;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.request.coupon.*;
import com.qdbank.mall.response.coupon.CouponExportReqDTO;
import com.qdbank.mall.response.coupon.CouponResDTO;
import com.qdbank.mall.task.ITaskService;
import com.qdbank.mall.task.TaskService;
import com.qdbank.mall.util.SpringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ClassName CouponController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/12 18:44
 * @Version 1.0
 **/
@Controller
@Api(tags = "CouponController", description = "优惠券")
@RequestMapping("/coupon")
@Slf4j
public class CouponController {
    @ApiOperation("优惠券列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<CouponResDTO>> list(@RequestBody CouponLikeQueryReqDTO couponLikeQueryReqDTO) {
        if(CouponDistributeWayEnum.getEnumByCode(couponLikeQueryReqDTO.getDistributeWay()) == null) {
            throw new ApiException(ResultCode.VALIDATE_FAILED);
        }
        Long couponType = couponLikeQueryReqDTO.getCouponType();

        CouponService couponService;
        //查询全部
        if(couponType == null){
            couponService = this.getCouponServiceByType(CouponTypeEnum.PRODUCT_FREE_COUPON.getCode());
        }else{
            couponService = this.getCouponServiceByType(couponType);
        }

        //前端传入的券类型为对应的券服务枚举,对于积分兑换批次券列表查询,需修正优惠券类型为积分兑换券
        if(ServiceEnum.INTEGRATION_BATCH_COUPON_SERVICE.getCouponType().equals(couponType)) {
            couponLikeQueryReqDTO.setCouponType(CouponTypeEnum.INTEGRATION_COUPON.getCode());
        }
        return CommonResult.success(CommonPage.restPage(couponService.list(couponLikeQueryReqDTO)));
    }

    @ApiOperation("积分兑换券列表")
    @RequestMapping(value = "/couponList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<CouponResDTO>> couponList(@RequestBody CouponLikeQueryReqDTO couponLikeQueryReqDTO) {
        if(CouponDistributeWayEnum.getEnumByCode(couponLikeQueryReqDTO.getDistributeWay()) == null) {
            throw new ApiException(ResultCode.VALIDATE_FAILED);
        }
        Long couponType = couponLikeQueryReqDTO.getCouponType();

        CouponService couponService;
        //查询全部
        if(couponType == null){
            couponService = this.getCouponServiceByType(CouponTypeEnum.PRODUCT_FREE_COUPON.getCode());
        }else{
            couponService = this.getCouponServiceByType(couponType);
        }

        //前端传入的券类型为对应的券服务枚举,对于积分兑换批次券列表查询,需修正优惠券类型为积分兑换券
        if(ServiceEnum.INTEGRATION_BATCH_COUPON_SERVICE.getCouponType().equals(couponType)) {
            couponLikeQueryReqDTO.setCouponType(CouponTypeEnum.INTEGRATION_COUPON.getCode());
        }
        return CommonResult.success(CommonPage.restPage(couponService.list(couponLikeQueryReqDTO)));
    }
    @ApiOperation(value = "新建优惠券")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(CouponReqDTO couponReqDTO) {
        CouponService couponService = this.getCouponServiceByType(couponReqDTO.getCouponType());
        int count = couponService.create(couponReqDTO);
        if (count < 1) {
            return CommonResult.failed();
        }
        return CommonResult.success(null);
    }
    @ApiOperation(value = "新建积分兑换券优惠券")
    @RequestMapping(value = "/createCoupon", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createCoupon(CouponReqDTO couponReqDTO) {
        CouponService couponService = this.getCouponServiceByType(couponReqDTO.getCouponType());
        int count = couponService.create(couponReqDTO);
        if (count < 1) {
            return CommonResult.failed();
        }
        return CommonResult.success(null);
    }
    @ApiOperation(value = "编辑优惠券")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@Validated @RequestBody UpdateCouponReqDTO updateCouponReqDTO) {
        CouponService couponService = this.getCouponServiceByType(updateCouponReqDTO.getCouponType());
        int count = couponService.update(updateCouponReqDTO,null);
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }
    @ApiOperation(value = "积分优惠券编辑")
    @RequestMapping(value = "/integrationUpdate", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult integrationUpdate(@Validated @RequestBody UpdateCouponReqDTO updateCouponReqDTO) {
        CouponService couponService = this.getCouponServiceByType(updateCouponReqDTO.getCouponType());
        int count = couponService.update(updateCouponReqDTO,null);
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }
    @ApiOperation(value = "编辑优惠券->支持修改文件上传")
    @RequestMapping(value = "/updateUpload", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateUpload(UpdateCouponPicReqDTO updateCouponReqDTO) {
        CouponService couponService = this.getCouponServiceByType(updateCouponReqDTO.getCouponType());
        int count = couponService.update(updateCouponReqDTO,updateCouponReqDTO.getFile());
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "修改优惠券状态")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@Validated @RequestBody UpdateCouponStatusReqDTO updateCouponStatusReqDTO) {
        CouponService couponService = this.getCouponServiceByType(updateCouponStatusReqDTO.getCouponType());
        int count = couponService.updateStatus(updateCouponStatusReqDTO);
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }
    @ApiOperation(value = "修改积分兑换券状态")
    @RequestMapping(value = "/updateCouponStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateCouponStatus(@Validated @RequestBody UpdateCouponStatusReqDTO updateCouponStatusReqDTO) {
        CouponService couponService = this.getCouponServiceByType(updateCouponStatusReqDTO.getCouponType());
        int count = couponService.updateStatus(updateCouponStatusReqDTO);
        if (count > 0) {
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }
    @ApiOperation("积分兑换优惠券详情->券商品编号")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CouponResDTO> getIntegrationCouponById(@Validated @RequestBody CouponDetailQueryReqDTO couponDetailQueryReqDTO) {
        CouponService couponService = this.getCouponServiceByType(couponDetailQueryReqDTO.getCouponType());
        return CommonResult.success(couponService.detail(couponDetailQueryReqDTO));
    }

    @ApiOperation(value="优惠券使用明细导出->批次号",produces="application/octet-stream")
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult excelDownload(@Validated @RequestBody CouponExportReqDTO couponExportReqDTO){
        CouponService couponService = this.getCouponServiceByType(couponExportReqDTO.getCouponType());
        return couponService.exportCoupons(couponExportReqDTO.getBatchNo(),couponExportReqDTO.getCouponType());
    }

    private CouponService getCouponServiceByType(Long couponType){
        if(couponType == 2L){
            couponType = 1L;
        }
        String beanName = ServiceEnum.getServiceName(couponType);
        if(StringUtils.isEmpty(beanName)) throw new ApiException(ResultCode.COUPON_TYPE_ERROR);
        CouponService couponService = SpringUtil.getBean(beanName,CouponService.class);
        if(couponService == null) throw new ApiException(ResultCode.COUPON_TYPE_ERROR);
        return couponService;
    }


}
