package com.qdbank.mall.mapper.prefecturestockrelation;

import com.qdbank.mall.model.prefecturestockrelation.PrefectureStockRelationDO;
import com.qdbank.mall.model.prefecturestockrelation.PrefectureStockRelationDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PrefectureStockRelationDOMapper {
    long countByExample(PrefectureStockRelationDOExample example);

    int deleteByExample(PrefectureStockRelationDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PrefectureStockRelationDO record);

    int insertSelective(PrefectureStockRelationDO record);

    List<PrefectureStockRelationDO> selectByExample(PrefectureStockRelationDOExample example);

    PrefectureStockRelationDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PrefectureStockRelationDO record, @Param("example") PrefectureStockRelationDOExample example);

    int updateByExample(@Param("record") PrefectureStockRelationDO record, @Param("example") PrefectureStockRelationDOExample example);

    int updateByPrimaryKeySelective(PrefectureStockRelationDO record);

    int updateByPrimaryKey(PrefectureStockRelationDO record);
}