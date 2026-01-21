package com.qdbank.mall.controller.account;

import com.qdbank.mall.account.AccountService;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.exception.AvailableAccountNotExistsException;
import com.qdbank.mall.response.account.AccountInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/openapi/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @RequestMapping(value = "/queryAccountInfo")
    public CommonResult queryAccountInfo(String idCde){
        List<AccountInfo> accountInfo= accountService.queryAccountInfo(idCde);
        if(CollectionUtils.isEmpty(accountInfo)){
            throw new AvailableAccountNotExistsException("可用账户不存在");
        }
        return CommonResult.success(accountInfo);
    }

}
