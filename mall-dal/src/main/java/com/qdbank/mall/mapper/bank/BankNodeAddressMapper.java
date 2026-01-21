package com.qdbank.mall.mapper.bank;

import com.qdbank.mall.model.bank.BankNodeAddress;
import com.qdbank.mall.model.bank.BankNodeAddressExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface BankNodeAddressMapper {
    long countByExample(BankNodeAddressExample example);

    int deleteByExample(BankNodeAddressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BankNodeAddress record);

    int insertSelective(BankNodeAddress record);

    List<BankNodeAddress> selectByExample(BankNodeAddressExample example);

    BankNodeAddress selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") BankNodeAddress record, @Param("example") BankNodeAddressExample example);

    int updateByExample(@Param("record") BankNodeAddress record, @Param("example") BankNodeAddressExample example);

    int updateByPrimaryKeySelective(BankNodeAddress record);

    int updateByPrimaryKey(BankNodeAddress record);
}