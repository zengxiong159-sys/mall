package com.qdbank.mall.controller.orderimport;

import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.orderimport.OrderImportService;
import com.qdbank.mall.request.orderimport.OrderImportReqDTO;
import com.qdbank.mall.util.SpringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @ClassName OrderImportController
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/5/26 17:52
 * @Version 1.0
 **/
@RestController
@RequestMapping("/order")
public class OrderImportController {

    @RequestMapping("/orderImport")
    public CommonResult orderImport(OrderImportReqDTO orderImportReqDTO){
        OrderImportService orderImportService = SpringUtil.getBean(getName(orderImportReqDTO.getType()),OrderImportService.class);
        if(orderImportService == null) return CommonResult.failed("存量数据类型传入错误");
        return orderImportService.orderImport(orderImportReqDTO);
    }
    private String getName(Integer type){
        if(type == 0) return "orderImportServiceImpl";
        if(type == 1) return "refundOrderImportServiceImpl";
        if(type == 2) return "userCouponImportServiceImpl";
        if(type == 3) return "integerationImportServiceImpl";
        return "";
    }
}
