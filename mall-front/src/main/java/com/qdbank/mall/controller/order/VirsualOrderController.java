package com.qdbank.mall.controller.order;

import com.qdbank.mall.annotation.CheckUser;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.bo.UserDetails;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.constant.ProductEnum;
import com.qdbank.mall.constant.payment.OilSupply;
import com.qdbank.mall.constant.payment.event.MobileReChargePayOrderEvent;
import com.qdbank.mall.mobile.MobileService;
import com.qdbank.mall.mobile.mapper.MobileMapper;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.rechargeMobile.RechargeMobileDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.order.PaymentService;
import com.qdbank.mall.order.business.handler.OilCardPaymenHandler;
import com.qdbank.mall.order.mapper.OrderMapper;
import com.qdbank.mall.request.mobile.MobileLocationReqDTO;
import com.qdbank.mall.request.oil.OilLocationReqDTO;
import com.qdbank.mall.request.order.MobileRechargePaymenOrderReqDTO;
import com.qdbank.mall.request.order.OilRechargePaymenOrderReqDTO;
import com.qdbank.mall.request.order.OrderIDReqDTO;
import com.qdbank.mall.response.mobile.MobileLocationResDTO;
import com.qdbank.mall.response.mobile.MobileSkuResDTO;
import com.qdbank.mall.response.mobile.UserMobileSkuResDTO;
import com.qdbank.mall.response.oil.UserOilSkuResDTO;
import com.qdbank.mall.response.order.GenerateOrderResDTO;
import com.qdbank.mall.response.order.OrderResDTO;
import com.qdbank.mall.response.skustock.SkustockResDTO;
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
@Api(tags = "virsualOrderController", description = "虚拟商品充值")
@RequestMapping("/order/virsualRecharge")
@Slf4j
public class VirsualOrderController extends CommonBsn{

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


    @Autowired
    OilCardPaymenHandler oilCardPaymenHandler;



    @CheckUser
    @ApiOperation("订单取消->订单编号")
    @RequestMapping(value = "/cancle", method = RequestMethod.POST)
    public CommonResult<OrderResDTO> cancleOrder(@Validated @RequestBody OrderIDReqDTO commonIDReqDTO){
        Message message =paymentService.handlerOrder(commonIDReqDTO.getOrderSn()+"",MobileReChargePayOrderEvent.CANCEL,null);
        return CommonResult.success(orderMapper.poToDo((OrderDO) message.getHeaders().get(Constant.ORDER_STR)));
    }

    @CheckUser
    @ApiOperation("虚拟商品生成订单")
    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    public CommonResult<GenerateOrderResDTO> generateOrder(@Validated @RequestBody OilRechargePaymenOrderReqDTO orderReqDTO) {
        initUserDetail(orderReqDTO);
        //网信号段测试
        checkWxMobile(orderReqDTO.getAccNo());
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



    @ApiOperation("油卡规格列表")
    @RequestMapping(value = "/oilSkuList", method = RequestMethod.POST)
    public CommonResult<UserOilSkuResDTO> mobileSkuList(@Validated@RequestBody OilLocationReqDTO mobileLocationReqDTO) {
        UserOilSkuResDTO res = new UserOilSkuResDTO();

        String accNo="";
        Long skuId=-1L;

        //判断是否上传手机号-没上传以客户为准
        MobileLocationResDTO historyRechargeMobile = new MobileLocationResDTO();
        //历史充值话费记录
        UserDetails userDetails = UserContextHolder.getUserDetails();
        if(userDetails!=null && StringUtils.hasText(userDetails.getCustNo())){
            OrderResDTO rechargeMobile =mobileService.qryMobileByCustNo(userDetails.getCustNo(),ProductEnum.OIL_CARD);
            if(rechargeMobile!=null){
                skuId=rechargeMobile.getProductSkuId();
                accNo = rechargeMobile.getRechargeMobile()+"";
            }
        }

        //获取话费规格
        Map<OilSupply,List<SkustockResDTO>> mobileSkus= oilCardPaymenHandler.qryOilSkus(false);

        List<SkustockResDTO> zshs=mobileSkus.get(OilSupply.ZSH);
        List<SkustockResDTO> zsys=mobileSkus.get(OilSupply.ZSY);

        String oilType = "";
        //判断何种类型
        if(skuId>-1L){
            if(checkOilType(zshs,skuId)){
                oilType=OilSupply.ZSH.code;
            }else if(checkOilType(zsys,skuId)){
                oilType=OilSupply.ZSY.code;
            }
        }

        res.setZshSkus(zshs);
        res.setZsySkus(zsys);
        res.setAccNo(accNo);
        res.setOilType(oilType);


        log.info("当前油卡结果[{}]", JsonUtil.toJSONString(mobileSkus));
        return CommonResult.success(res);
    }

    private boolean checkOilType(List<SkustockResDTO> ress, Long skuId) {
        for(SkustockResDTO res : ress){
            if(skuId.equals(res.getProductSkuId())){
                return true;
            }
        }
        return false;
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
