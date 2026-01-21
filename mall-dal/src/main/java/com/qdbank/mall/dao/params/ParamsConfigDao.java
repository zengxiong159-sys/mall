package com.qdbank.mall.dao.params;

import com.qdbank.mall.mapper.paramsconfig.ParamsConfigDOMapper;
import com.qdbank.mall.model.paramsconfig.ParamsConfigDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hongjh
 */
@Repository
@Slf4j
public class ParamsConfigDao {

    @Autowired
    private ParamsConfigDOMapper paramsConfigDOMapper;


    /**
     * 查询参数表所有记录
     * @return
     */
    public List<ParamsConfigDO> list() {
        List<ParamsConfigDO> configDOList = paramsConfigDOMapper.selectByExample(null);
        return configDOList;
    }





}