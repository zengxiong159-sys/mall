package com.qdbank.mall.mapper.prefecture;

import com.qdbank.mall.model.prefecture.PrefectureDO;
import com.qdbank.mall.model.prefecture.PrefectureDOExample;
import com.qdbank.mall.model.prefecture.PrefectureInfoDO;
import com.qdbank.mall.model.product.ProductSkuDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PrefectureDOMapper {
    long countByExample(PrefectureDOExample example);

    int deleteByExample(PrefectureDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PrefectureDO record);

    int insertSelective(PrefectureDO record);

    List<PrefectureDO> selectByExample(PrefectureDOExample example);

    PrefectureDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PrefectureDO record, @Param("example") PrefectureDOExample example);

    int updateByExample(@Param("record") PrefectureDO record, @Param("example") PrefectureDOExample example);

    int updateByPrimaryKeySelective(PrefectureDO record);

    int updateByPrimaryKey(PrefectureDO record);

    List<PrefectureInfoDO> selectPrefectureInfoByParams(PrefectureDO prefectureDO);

    List<ProductSkuDO> selectPrefectureProductInfoByParams(PrefectureDO prefectureDO);

    PrefectureInfoDO selectPrefectureInfo(PrefectureDO prefectureDO);

    int updateStatusByPrimaryKey(PrefectureDO record);

    int updateExpireStatus();
}