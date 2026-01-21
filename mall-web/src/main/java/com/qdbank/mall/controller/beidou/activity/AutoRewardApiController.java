package com.qdbank.mall.controller.beidou.activity;

import com.alibaba.fastjson.JSON;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.api.StatusEnum;
import com.qdbank.mall.beidou.activity.AutoRewardService;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.response.beidou.activity.req.*;
import com.qdbank.mall.response.beidou.activity.res.CouponInvalidatedResDTO;
import com.qdbank.mall.response.beidou.activity.res.CouponResDTO;
import com.qdbank.mall.response.beidou.activity.res.PrefectureProductResDTO;
import com.qdbank.mall.util.AesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author zengxiong
 * @Description 北斗达标活动自动奖励API
 * @Date 2021/11/24 15:43
 */
@RestController
@RequestMapping("/api/activity")
@Slf4j
public class AutoRewardApiController {

    @Resource
    private AutoRewardService autoRewardService;

    @Resource
    private AesUtils aesUtils;

    @RequestMapping(value = "/getCouponList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<CouponResDTO>> getCouponList() {
        return CommonResult.success(autoRewardService.getCouponList(), Constant.NOT_ENCRYPT);
    }

    @RequestMapping(value = "/updateCoupon", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult batchUpdateCoupon(@Validated @RequestBody CommonReqDTO commonReqDTO) {
        boolean result = autoRewardService.batchUpdateCoupon(commonReqDTO);
        if (result) {
            return CommonResult.success("权益绑定成功", Constant.NOT_ENCRYPT);
        }
        return CommonResult.failed("权益绑定失败");
    }

    @RequestMapping(value = "/prefectureProductList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<PrefectureProductResDTO>> prefectureProductList(
            @Validated @RequestBody PrefectureProductQueryReqDTO prefectureProductQueryReqDTO) {
        return CommonResult.success(autoRewardService.prefectureProductList(prefectureProductQueryReqDTO), Constant.NOT_ENCRYPT);
    }

    @RequestMapping(value = "/queryUserCoupon", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult queryUserCoupon(@Validated @RequestBody UserCouponQueryReqDTO userCouponQueryReqDTO) {
        try {
            Long userCouponStatus = autoRewardService.queryUserCoupon(userCouponQueryReqDTO);
            if (userCouponStatus != null) {
                //待使用
                if (StatusEnum.USER_COUPON_NOT_USED.getCode().equals(userCouponStatus)) {
                    return CommonResult.success(null);
                }

                //已使用
                if (StatusEnum.USER_COUPON_USED.getCode().equals(userCouponStatus)) {
                    return CommonResult.failed(ResultCode.SER_COUPON_STATUS_USED);
                }

                //已过期或已作废,提示券已过期,无法换购
                if (StatusEnum.USER_COUPON_EXPIRE.getCode().equals(userCouponStatus)
                        || StatusEnum.USER_COUPON_INVLALID.getCode().equals(userCouponStatus)) {
                    return CommonResult.failed(ResultCode.USER_COUPON_STATUS_EXPIRE);
                }
            }
            //未发放或不存在,提示暂无可用的券,请稍后再试
            return CommonResult.failed(ResultCode.USER_COUPON_NOT_EXIST);
        } catch (Exception e) {
            log.error("用户持券状态查询出错", e);
            return CommonResult.failed(ResultCode.FAILED);
        }
    }

    @RequestMapping(value = "/issueCoupon", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult issueCoupon(@Validated @RequestBody CommonReqDTO commonReqDTO) {
        //aes解密
        Long couponId;
        String custNo;
        try {
            String dataDecrypt = aesUtils.aesDecrypt(commonReqDTO.getData());
            IssueCouponReqDTO issueCouponReqDTO = JSON.parseObject(dataDecrypt, IssueCouponReqDTO.class);
            couponId = issueCouponReqDTO.getCouponId();
            custNo = issueCouponReqDTO.getCustNo();
        } catch (Exception e) {
            log.error("aes decrypt failed", e);
            throw new ApiException(ResultCode.AES_DECRYPT_ERROR);
        }

        if (autoRewardService.issueCoupon(couponId, custNo)) {
            return CommonResult.success("发券成功", Constant.NOT_ENCRYPT);
        }
        return CommonResult.failed(ResultCode.SYSTEM_EXCEPTION);
    }

    @RequestMapping(value = "/batchInvalidCoupon", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult batchInvalidCoupon(@Validated @RequestBody CommonReqDTO commonReqDTO) {
        boolean result = autoRewardService.batchInvalidCoupon(commonReqDTO);
        if (result) {
            return CommonResult.success("操作成功", Constant.NOT_ENCRYPT);
        }
        return CommonResult.failed("操作失败");
    }

    @RequestMapping(value = "/getCouponInvalidated", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<CouponInvalidatedResDTO>> getCouponInvalidated(
            @Validated @RequestBody List<CouponInvalidatedReqDTO> couponInvalidatedReqDTOList) {
        return CommonResult.success(autoRewardService.getCouponInvalidated(couponInvalidatedReqDTOList), Constant.NOT_ENCRYPT);
    }

}
