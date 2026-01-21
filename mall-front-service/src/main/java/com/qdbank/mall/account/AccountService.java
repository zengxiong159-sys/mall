package com.qdbank.mall.account;

import com.qdbank.mall.response.account.AccountInfo;

import java.util.List;

public interface AccountService {

    List<AccountInfo> queryAccountInfo(String idNo);

}
