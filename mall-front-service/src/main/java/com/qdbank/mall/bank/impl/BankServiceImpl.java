package com.qdbank.mall.bank.impl;

import com.qdbank.mall.bank.BankService;
import com.qdbank.mall.mapper.bank.BankNodeAddressMapper;
import com.qdbank.mall.model.bank.BankNodeAddress;
import com.qdbank.mall.model.bank.BankNodeAddressExample;
import com.qdbank.mall.request.bank.BankLikeReqDTO;
import com.qdbank.mall.response.bank.BankRespDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BankServiceImpl implements BankService {
    @Autowired
    private BankNodeAddressMapper bankNodeAddressMapper;
    @Override
    public List<BankRespDTO> list(BankLikeReqDTO bankLikeReqDTO) {
        List<BankRespDTO> bankRespDTOList = new ArrayList<>();
        BankNodeAddressExample bankNodeAddressExample = new BankNodeAddressExample();
        bankNodeAddressExample.createCriteria().andCityNameEqualTo(bankLikeReqDTO.getCityName()).andBranchAddressLike("%"+bankLikeReqDTO.getBranchAddress()+"%");
        List<BankNodeAddress> bankNodeAddressList = bankNodeAddressMapper.selectByExample(bankNodeAddressExample);
        for(BankNodeAddress bankNodeAddress : bankNodeAddressList){
            BankRespDTO bankRespDTO = new BankRespDTO();
            BeanUtils.copyProperties(bankNodeAddress,bankRespDTO);
            bankRespDTOList.add(bankRespDTO);
        }
        return bankRespDTOList;
    }
}
