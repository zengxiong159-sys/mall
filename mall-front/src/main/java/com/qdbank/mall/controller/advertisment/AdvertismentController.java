package com.qdbank.mall.controller.advertisment;

import com.qdbank.mall.advertisment.AdvertismentService;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.bo.UserDetails;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.coupon.CouponService;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.request.CommonIDReqDTO;
import com.qdbank.mall.request.advertisement.AdvertismentLikeQueryReqDTO;
import com.qdbank.mall.request.coupon.UserCouponReqDTO;
import com.qdbank.mall.response.advertisement.AdvertisementResDTO;
import com.qdbank.mall.response.advertisement.FrontPageResDTO;
import com.qdbank.mall.response.coupon.UserCouponResDTO;
import com.qdbank.mall.util.MetricsUtils;
import com.qdbank.mall.util.UserContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AdvertismentController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/1/11 10:09
 * @Version 1.0
 **/
@RestController
@Api(tags = "AdvertismentController", description = "广告Banner")
@RequestMapping("/advertisment")
@Slf4j
public class AdvertismentController {

    @Autowired
    AdvertismentService advertismentService;

    @Autowired
    CouponService couponService;

    @Autowired
    OrderService orderService;

    @ApiOperation("首页整合数据")
    @RequestMapping(value = "/frontPage", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<FrontPageResDTO> list() {
        //统计首页PV
        MetricsUtils.count("prometheus_mall_pv_total", "1", "page", "homepage");

        //广告和菜单
        List<AdvertisementResDTO> list = advertismentService.list(new AdvertismentLikeQueryReqDTO());

        List<AdvertisementResDTO> ads = new ArrayList<>();
        List<AdvertisementResDTO> menus = new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)){
            list.stream().forEach(item->{
                if(0L == item.getModuleId()){
                    //banner
                    ads.add(item);
                }else if(1L == item.getModuleId()){
                    //入口
                    menus.add(item);
                }
            });
        }
        FrontPageResDTO front = new FrontPageResDTO();
        front.setAdvertisements(ads);
        front.setMenus(menus);

        //step 2 积分券
        UserDetails userDetails = UserContextHolder.getUserDetails();
        if(userDetails==null || !StringUtils.hasText(userDetails.getCustNo())){
            //不存在用户
            front.setCouponNum(0);
            front.setStayReceriveNum(0);
        }else{
            //存在用户
            UserCouponReqDTO userCouponReqDTO = new UserCouponReqDTO();

            //可用优惠券
            userCouponReqDTO.setStatus(0L);
            List<UserCouponResDTO> result =couponService.countUseCounpon(UserContextHolder.getUserDetails().getCustNo(),userCouponReqDTO);
            front.setCouponNum(result!=null?result.size():0);

            //step 3 待收货
            Long o_results =orderService.countOrderByProductTypeAndStatus(UserContextHolder.getUserDetails().getCustNo(),2L,ProductEnum.PAYMENT_IN_KIND);
            front.setStayReceriveNum(o_results!=null?o_results.intValue():0);
        }

        return CommonResult.success(front);
    }

    @ApiOperation("顶通广告位")
    @RequestMapping(value = "/ads", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<AdvertisementResDTO>> ads() {
        //广告和菜单
        List<AdvertisementResDTO> list = advertismentService.list(new AdvertismentLikeQueryReqDTO());
        List<AdvertisementResDTO> ads = new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)){
            list.stream().forEach(item->{
                if(2L == item.getModuleId()){
                    ads.add(item);
                }
            });
        }
        return CommonResult.success(ads);
    }


    @ApiOperation("分享banner")
    @RequestMapping(value = "/shareBanner", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<AdvertisementResDTO> shareBanner() {
        //广告和菜单
        List<AdvertisementResDTO> list = advertismentService.list(new AdvertismentLikeQueryReqDTO());
        AdvertisementResDTO ads = null;
        if(!CollectionUtils.isEmpty(list)){
            for(AdvertisementResDTO item : list){
                if(0L == item.getModuleId()){
                    //banner
                    ads = item;
                }
            }
        }
        return CommonResult.success(ads);
    }


    /*@ApiOperation("广告列表 0 首页顶通 1 快捷入口 2 积分兑换顶通")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<AdvertisementResDTO>> list(@Validated @RequestBody AdvertismentLikeQueryReqDTO commonIDReqDTO) {
        return CommonResult.success(advertismentService.list(commonIDReqDTO));
    }*/

    @ApiOperation("广告详情->广告编号")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<AdvertisementResDTO> getItem(@Validated @RequestBody CommonIDReqDTO commonIDReqDTO) {
        return CommonResult.success(advertismentService.detail(commonIDReqDTO.getId()));
    }
}
