package com.qdbank.mall.bank;

import com.qdbank.mall.request.bank.BankReqDTO;
import com.qdbank.mall.request.bank.BankUpdateReqDTO;
import com.qdbank.mall.response.bank.BankRespDTO;

import java.util.List;

public interface BankService {
    /**
     * 新增营业网点
     * @param bankReqDTO
     * @return
     */
    public int create(BankReqDTO bankReqDTO);

    /**
     * 删除营业网点
     * @param id
     * @return
     */
    public int delete(Long id);

    /**
     * 修改营业网点
     * @param bankUpdateReqDTO
     * @return
     */
    public int update(BankUpdateReqDTO bankUpdateReqDTO);

    /**
     *
     * @param bankReqDTO
     * @return
     */
    public List<BankRespDTO> list(BankReqDTO bankReqDTO);
}
