package com.qdbank.mall.bank;

import com.qdbank.mall.request.bank.BankLikeReqDTO;
import com.qdbank.mall.response.bank.BankRespDTO;

import java.util.List;

public interface BankService {
    public List<BankRespDTO> list(BankLikeReqDTO branchName);
}
