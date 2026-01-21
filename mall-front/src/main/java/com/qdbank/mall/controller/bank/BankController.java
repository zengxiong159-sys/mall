package com.qdbank.mall.controller.bank;

import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.bank.BankService;
import com.qdbank.mall.request.bank.BankLikeReqDTO;
import com.qdbank.mall.response.bank.BankRespDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "BankController", description = "营业网点管理")
@RequestMapping("/openapi/bank")
@Slf4j
public class BankController {
    @Autowired
    private BankService bankService;
    @ApiOperation("网点列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<List<BankRespDTO>> list(@Validated @RequestBody BankLikeReqDTO bankLikeReqDTO){
        return CommonResult.success(bankService.list(bankLikeReqDTO));
    }
}
