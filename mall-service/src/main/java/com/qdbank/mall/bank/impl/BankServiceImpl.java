package com.qdbank.mall.bank.impl;

import com.github.pagehelper.PageHelper;
import com.qdbank.mall.bank.BankService;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.bank.BankNodeAddressMapper;
import com.qdbank.mall.model.bank.BankNodeAddress;
import com.qdbank.mall.model.bank.BankNodeAddressExample;
import com.qdbank.mall.request.bank.BankReqDTO;
import com.qdbank.mall.request.bank.BankUpdateReqDTO;
import com.qdbank.mall.response.bank.BankRespDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.user.UmsAdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class BankServiceImpl extends BaseServiceImpl implements BankService {
    @Autowired
    private BankNodeAddressMapper bankNodeAddressMapper;
    @Qualifier("umsAdminServiceImpl")
    @Autowired
    private UmsAdminService umsAdminService;
    @Override
    public int create(BankReqDTO bankReqDTO) {
        BankNodeAddress bankNodeAddress = new BankNodeAddress();
        BeanUtils.copyProperties(bankReqDTO,bankNodeAddress);
        bankNodeAddress.setId(super.generateId());
        umsAdminService.injectUserValue(bankNodeAddress);
        int count = 0;
        try {
            count = bankNodeAddressMapper.insert(bankNodeAddress);
        }catch (DuplicateKeyException  duplicateKeyException){
            log.info("网点机构编号：{} 重复异常：{}",bankReqDTO.getBranchNo(),duplicateKeyException);
        }

        return count;
    }

    @Override
    public int delete(Long id) {
        return bankNodeAddressMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(BankUpdateReqDTO bankUpdateReqDTO) {
        BankNodeAddress bankNodeAddress = new BankNodeAddress();
        BeanUtils.copyProperties(bankUpdateReqDTO,bankNodeAddress);
        return bankNodeAddressMapper.updateByPrimaryKeySelective(bankNodeAddress);
    }

    @Override
    public List<BankRespDTO> list(BankReqDTO bankReqDTO) {
        PageHelper.startPage(bankReqDTO.getPageNum(), bankReqDTO.getPageSize());
        List<BankRespDTO> bankRespDTOList = new ArrayList<>();
        BankNodeAddressExample bankNodeAddressExample = new BankNodeAddressExample();
        BankNodeAddressExample.Criteria criteria = bankNodeAddressExample.createCriteria();
        if(StringUtils.isNotBlank(bankReqDTO.getCityName())){
            criteria.andCityNameLike("%"+bankReqDTO.getCityName()+"%");
        }
        if(StringUtils.isNotBlank(bankReqDTO.getBranchName())){
            criteria.andBranchNameLike("%"+bankReqDTO.getBranchName()+"%");
        }
        if(StringUtils.isNotBlank(bankReqDTO.getBranchNo())){
            criteria.andBranchNoEqualTo(bankReqDTO.getBranchNo());
        }
        if(StringUtils.isNotBlank(bankReqDTO.getBranchAddress())){
            criteria.andBranchAddressLike("%"+bankReqDTO.getBranchAddress()+"%");
        }
        List<BankNodeAddress> bankNodeAddressList = bankNodeAddressMapper.selectByExample(bankNodeAddressExample);
        for(BankNodeAddress bankNodeAddress : bankNodeAddressList){
            BankRespDTO bankRespDTO = new BankRespDTO();
            BeanUtils.copyProperties(bankNodeAddress,bankRespDTO);
            bankRespDTOList.add(bankRespDTO);
        }
        return bankRespDTOList;
    }
}
