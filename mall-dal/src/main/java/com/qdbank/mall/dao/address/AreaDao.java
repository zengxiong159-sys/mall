package com.qdbank.mall.dao.address;

import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.address.AddressDOMapper;
import com.qdbank.mall.mapper.area.AreaDOMapper;
import com.qdbank.mall.model.address.AddressDO;
import com.qdbank.mall.model.address.AddressDOExample;
import com.qdbank.mall.model.area.AreaDO;
import com.qdbank.mall.model.area.AreaDOExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hongjh
 */
@Repository
@Slf4j
public class AreaDao {

    @Resource
    private AreaDOMapper areaDOMapper;




    public List<AreaDO> qryAreas(Long parentId){
        AreaDOExample example =  new AreaDOExample();
        example.setOrderByClause("PARENT_ID,VIEW_ORDER");
        AreaDOExample.Criteria criteria =example.createCriteria();
        if(parentId != null){
            criteria.andParentIdEqualTo(parentId);
        }
        return areaDOMapper.selectByExample(example);
    }




}