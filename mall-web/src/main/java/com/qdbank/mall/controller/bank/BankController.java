package com.qdbank.mall.controller.bank;

import com.qdbank.mall.api.CommonPage;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.bank.BankService;
import com.qdbank.mall.request.bank.BankReqDTO;
import com.qdbank.mall.request.bank.BankUpdateReqDTO;
import com.qdbank.mall.response.bank.BankRespDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "BankController", description = "银行网点配置")
@RequestMapping("/openapi/bank")
@Slf4j
public class BankController {
    @Autowired
    private BankService bankService;
    @ApiOperation(value = "新建网点")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(BankReqDTO bankReqDTO) {
        int count = bankService.create(bankReqDTO);
        if (count < 1) {
            return CommonResult.failed();
        }
        return CommonResult.success(null);
    }
    @ApiOperation("网点列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult<CommonPage<BankRespDTO>> list(@Validated @RequestBody BankReqDTO bankReqDTO) {

        return CommonResult.success(CommonPage.restPage(bankService.list(bankReqDTO)));
    }

    @ApiOperation(value = "修改网点网点")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult update(BankUpdateReqDTO bankReqDTO) {
        int count = bankService.update(bankReqDTO);
        if (count < 1) {
            return CommonResult.failed();
        }
        return CommonResult.success(null);
    }


    @ApiOperation(value = "修改网点网点")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable(value = "id") Long id) {
        int count = bankService.delete(id);
        if (count < 1) {
            return CommonResult.failed();
        }
        return CommonResult.success(null);
    }
}
