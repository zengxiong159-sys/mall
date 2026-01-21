package com.qdbank.mall.mapper.refundsetting;

import com.qdbank.mall.model.refundsetting.RefundsettingDO;
import com.qdbank.mall.model.refundsetting.RefundsettingDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RefundsettingDOMapper {
    long countByExample(RefundsettingDOExample example);

    int deleteByExample(RefundsettingDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RefundsettingDO record);

    int insertSelective(RefundsettingDO record);

    List<RefundsettingDO> selectByExample(RefundsettingDOExample example);

    RefundsettingDO selectByPrimaryKey(Long id);

    int updatedByExampleSelective(@Param("record") RefundsettingDO record, @Param("example") RefundsettingDOExample example);

    int updatedByExample(@Param("record") RefundsettingDO record, @Param("example") RefundsettingDOExample example);

    int updatedByPrimaryKeySelective(RefundsettingDO record);

    int updatedByPrimaryKey(RefundsettingDO record);
}