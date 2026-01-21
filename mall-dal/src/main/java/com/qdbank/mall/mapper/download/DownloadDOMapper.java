package com.qdbank.mall.mapper.download;

import com.qdbank.mall.model.download.DownloadDO;
import com.qdbank.mall.model.download.DownloadDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface DownloadDOMapper {
    long countByExample(DownloadDOExample example);

    int deleteByExample(DownloadDOExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(DownloadDO record);

    int insertSelective(DownloadDO record);

    List<DownloadDO> selectByExample(DownloadDOExample example);

    DownloadDO selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") DownloadDO record, @Param("example") DownloadDOExample example);

    int updateByExample(@Param("record") DownloadDO record, @Param("example") DownloadDOExample example);

    int updateByPrimaryKeySelective(DownloadDO record);

    int updateByPrimaryKey(DownloadDO record);
}