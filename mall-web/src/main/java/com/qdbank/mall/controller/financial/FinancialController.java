package com.qdbank.mall.controller.financial;

import com.qdbank.mall.annotation.ExcludeHandler;
import com.qdbank.mall.api.*;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.financial.FinancialService;
import com.qdbank.mall.request.financial.FinancialReqDTO;
import com.qdbank.mall.response.financial.IntegrationCouponResDTO;
import com.qdbank.mall.response.financial.OrderDetailResDTO;
import com.qdbank.mall.response.financial.OrderTotalResDTO;
import com.qdbank.mall.response.financial.ProductCouponResDTO;
import com.qdbank.mall.util.SpringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName FinancialController
 * @Description 商城报表导出
 * @Author ningyuehuai
 * @Date 2021/1/13 16:47
 * @Version 1.0
 **/
@RestController
@Api(tags = "FinancialController", description = "财务结算报表")
@RequestMapping("/financial")
@Slf4j
public class FinancialController {
    @ExcludeHandler
    @ApiOperation(value="商城报表汇总导出",produces="application/octet-stream")
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void excleTotalDownload(HttpServletResponse response, @Validated @RequestBody FinancialReqDTO financialReqDTO){
        FinancialService financialService = this.getFinancialServiceByType(financialReqDTO.getTradeType());
        financialService.export(response,financialReqDTO);
    }

    @ApiOperation("交易汇总列表")
    @RequestMapping(value = "/tradeTotal/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<OrderTotalResDTO>> tradeTotal(FinancialReqDTO financialReqDTO){
        FinancialService<OrderTotalResDTO> financialService = this.getFinancialServiceByType(FinancialServiceEnum.TRADE_TOTAL_SERVICE.getCouponType());
        return CommonResult.success(CommonPage.restPage(financialService.getTradeList(financialReqDTO)));
    }

    @ApiOperation("交易明细列表")
    @RequestMapping(value = "/tradeDetail/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<OrderDetailResDTO>> tradeDetail(FinancialReqDTO financialReqDTO){
        FinancialService<OrderDetailResDTO> financialService = this.getFinancialServiceByType(FinancialServiceEnum.TRADE_DETAIL_SERVICE.getCouponType());
        return CommonResult.success(CommonPage.restPage(financialService.getTradeList(financialReqDTO)));
    }

    @ApiOperation("积分结算汇总列表")
    @RequestMapping(value = "/tradeCouponTotal/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<IntegrationCouponResDTO>> tradeIntegration(FinancialReqDTO financialReqDTO){
        FinancialService<IntegrationCouponResDTO> financialService = this.getFinancialServiceByType(FinancialServiceEnum.COUPON_TOTAL_SERVICE.getCouponType());
        return CommonResult.success(CommonPage.restPage(financialService.getTradeList(financialReqDTO)));
    }

    @ApiOperation("营销费用汇总列表")
    @RequestMapping(value = "/tradeMarketFee/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<ProductCouponResDTO>> tradeMarketFee(FinancialReqDTO financialReqDTO){
        FinancialService<ProductCouponResDTO> financialService = this.getFinancialServiceByType(FinancialServiceEnum.MARKET_TOTAL_SERVICE.getCouponType());
        return CommonResult.success(CommonPage.restPage(financialService.getTradeList(financialReqDTO)));
    }

    @ApiOperation("数据入库测试")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult insert( FinancialReqDTO financialReqDTO){
        FinancialService financialService = this.getFinancialServiceByType(financialReqDTO.getTradeType());
        financialService.insertTradeRecord(financialReqDTO.getStartDate());
        return CommonResult.success(0);
    }


    private FinancialService getFinancialServiceByType(Long tradeType){
        String beanName = FinancialServiceEnum.getServiceName(tradeType);
        if(StringUtils.isEmpty(beanName)) throw new ApiException(ResultCode.TRADE_TYPE_ERROR);
        FinancialService financialService = SpringUtil.getBean(beanName,FinancialService.class);
        if(financialService == null) throw new ApiException(ResultCode.TRADE_TYPE_ERROR);
        return financialService;
    }

}
