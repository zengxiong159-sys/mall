package com.qdbank.mall.controller.order;

import com.qdbank.mall.annotation.CheckUser;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.bo.UserDetails;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.constant.payment.event.MobileReChargePayOrderEvent;
import com.qdbank.mall.mobile.MobileService;
import com.qdbank.mall.mobile.mapper.MobileMapper;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.rechargeMobile.RechargeMobileDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.PaymentService;
import com.qdbank.mall.order.mapper.OrderMapper;
import com.qdbank.mall.request.mobile.MobileLocationReqDTO;
import com.qdbank.mall.request.order.MobileRechargePaymenOrderReqDTO;
import com.qdbank.mall.request.order.OrderIDReqDTO;
import com.qdbank.mall.response.mobile.MobileLocationResDTO;
import com.qdbank.mall.response.mobile.MobileSkuResDTO;
import com.qdbank.mall.response.mobile.UserMobileSkuResDTO;
import com.qdbank.mall.response.order.GenerateOrderResDTO;
import com.qdbank.mall.response.order.OrderResDTO;
import com.qdbank.mall.util.JsonUtil;
import com.qdbank.mall.util.UserContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MobileOrderController
 * @Description TODO
 * @Author hongjh
 * @Date 2021/5/7 15:34
 * @Version 1.0
 **/
@RestController
@Api(tags = "mobileOrderController", description = "手机订单管理")
@RequestMapping("/order/mobileRecharge")
@Slf4j
public class MobileOrderController extends CommonBsn{

    @Autowired
    PaymentService paymentService;

    @Autowired
    OrderService orderService;

    @Autowired
    MobileService mobileService;

    @Autowired
    MobileMapper mobileMapper;


    @Autowired
    OrderMapper orderMapper;

    private ProductEnum PRODUCT_TYPE = ProductEnum.MOBILE_RECHARGE;



    @CheckUser
    @ApiOperation("订单取消->订单编号")
    @RequestMapping(value = "/cancle", method = RequestMethod.POST)
    public CommonResult<OrderResDTO> cancleOrder(@Validated @RequestBody OrderIDReqDTO commonIDReqDTO){
        Message message =paymentService.handlerOrder(commonIDReqDTO.getOrderSn()+"",MobileReChargePayOrderEvent.CANCEL,null);
        return CommonResult.success(orderMapper.poToDo((OrderDO) message.getHeaders().get(Constant.ORDER_STR)));
    }

    @CheckUser
    @ApiOperation("手机充值生成订单")
    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    public CommonResult<GenerateOrderResDTO> generateOrder(@Validated @RequestBody MobileRechargePaymenOrderReqDTO orderReqDTO) {
        initUserDetail(orderReqDTO);
        //网信号段测试
        checkWxMobile(orderReqDTO.getRechargeMobile());
        Map params = new HashMap();
        params.put("mobileOrder",orderReqDTO);
        Message message =paymentService.handlerOrder(null, MobileReChargePayOrderEvent.CREATE,params);
        OrderDO order = (OrderDO) message.getHeaders().get(Constant.ORDER_STR);
        //跳转地址
        String resUrl = order.getReqUrl();
        GenerateOrderResDTO result = new GenerateOrderResDTO();
        result.setJumpUrl(resUrl);
        return CommonResult.success(result, "下单成功");
    }



    @ApiOperation("话费规格列表")
    @RequestMapping(value = "/mobileSkuList", method = RequestMethod.POST)
    public CommonResult<UserMobileSkuResDTO> mobileSkuList(@Validated@RequestBody MobileLocationReqDTO mobileLocationReqDTO) {
        UserMobileSkuResDTO res = new UserMobileSkuResDTO();

        //通过手机号码获取面值规格
        String lastMobileNo = mobileLocationReqDTO.getRechargeMobile();

        //判断是否上传手机号-没上传以客户为准
        MobileLocationResDTO historyRechargeMobile = new MobileLocationResDTO();
        if(StringUtils.hasText(lastMobileNo)){
            //上传手机号
            checkWxMobile(lastMobileNo);
            historyRechargeMobile =mobileService.mobileLocation(lastMobileNo);
        }else{
            //历史充值话费记录
            UserDetails userDetails = UserContextHolder.getUserDetails();
            if(userDetails!=null && StringUtils.hasText(userDetails.getCustNo())){
                OrderResDTO rechargeMobile =mobileService.qryMobileByCustNo(userDetails.getCustNo(),ProductEnum.MOBILE_RECHARGE);
                //存在历史记录
                if(rechargeMobile!=null){
                    historyRechargeMobile = mobileService.mobileLocation(rechargeMobile.getRechargeMobile()+"");
                   /* historyRechargeMobile.setRechargeMobile(rechargeMobile.getMobile()+"");
                    historyRechargeMobile.setMobileAddress(rechargeMobile.getMobileAddress());
                    historyRechargeMobile.setMobileAddressDesc(MobileAddress.getName(rechargeMobile.getMobileAddress()));
                    historyRechargeMobile.setSupplierType(rechargeMobile.getSupplierType());*/
                }else{
                    // 查询归属地
                  //  historyRechargeMobile =mobileService.mobileLocation(userDetails.getCustMobile());
                }
            }
        }
        res.setHistoryRechargeMobile(historyRechargeMobile);


        //获取话费规格
        String mobileAddress =historyRechargeMobile.getMobileAddress();
        String supplierType= historyRechargeMobile.getSupplierType();
        List<MobileSkuResDTO> mobileSkus= null;
        //存在省、运营商
        if(StringUtils.hasText(mobileAddress) && StringUtils.hasText(supplierType)){
             mobileSkus=mobileService.qryeffectiveMobileSkus(mobileAddress,supplierType);
        }
        List<MobileSkuResDTO> initSkus=mobileService.qryMobileSkus();
        if(!CollectionUtils.isEmpty(mobileSkus)){
            //整合操作
            for(int i =0;i<initSkus.size();i++){
                MobileSkuResDTO item = initSkus.get(i);
                String supplyProductSize=item.getSupplyProductSize();
                //查询是否存在
                MobileSkuResDTO result = this.check(supplyProductSize,mobileSkus);
                if(result!=null){
                    initSkus.set(i,result);
                }
            }
        }
        res.setMobileSkus(initSkus);
        log.info("当前手机结果[{}]", JsonUtil.toJSONString(initSkus));
        return CommonResult.success(res);
    }

    private void checkWxMobile(String lastMobileNo) {
        /*String pattern = "170、171、162、172、198、167、165、191、195、199";
        if(pattern.contains(lastMobileNo.substring(0,3))){
            throw  new ApiException(ResultCode.NOT_SUPPORT_MOBILE);
        }*/
    }

    /**
     * 判断手机充值规格
     * @param supplyProductSize
     * @param mobileSkus
     * @return
     */
    private MobileSkuResDTO check(String supplyProductSize,List<MobileSkuResDTO> mobileSkus){
        for(MobileSkuResDTO res : mobileSkus){
            if(supplyProductSize.equals(res.getSupplyProductSize())){
                return res;
            }
        }
        return null;
    }















}
