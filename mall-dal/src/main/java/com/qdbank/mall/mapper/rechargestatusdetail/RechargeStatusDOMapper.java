package com.qdbank.mall.mapper.rechargestatusdetail;

import com.qdbank.mall.model.rechargestatusdetail.RechargeStatusDO;
import com.qdbank.mall.model.rechargestatusdetail.RechargeStatusDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RechargeStatusDOMapper {
    long countByExample(RechargeStatusDOExample example);

    int deleteByExample(RechargeStatusDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RechargeStatusDO record);

    int insertSelective(RechargeStatusDO record);

    List<RechargeStatusDO> selectByExample(RechargeStatusDOExample example);

    RechargeStatusDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RechargeStatusDO record, @Param("example") RechargeStatusDOExample example);

    int updateByExample(@Param("record") RechargeStatusDO record, @Param("example") RechargeStatusDOExample example);

    int updateByPrimaryKeySelective(RechargeStatusDO record);

    int updateByPrimaryKey(RechargeStatusDO record);
}