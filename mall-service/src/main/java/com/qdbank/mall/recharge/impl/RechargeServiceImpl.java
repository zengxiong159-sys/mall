package com.qdbank.mall.recharge.impl;

import cn.hutool.core.collection.CollUtil;
import com.qdbank.mall.mapper.rechargestatusdetail.RechargeStatusDOMapper;
import com.qdbank.mall.model.rechargestatusdetail.RechargeStatusDO;
import com.qdbank.mall.model.rechargestatusdetail.RechargeStatusDOExample;
import com.qdbank.mall.recharge.RechargeService;
import com.qdbank.mall.response.order.RechargeStatusDetailResDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName RechargeServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/4/28 13:37
 * @Version 1.0
 **/
@Service
public class RechargeServiceImpl implements RechargeService {
    @Autowired
    private RechargeStatusDOMapper rechargeStatusDOMapper;
    @Override
    public List<RechargeStatusDetailResDTO> getRechargeStatus(String orderSn) {
        RechargeStatusDOExample rechargeStatusDOExample = new RechargeStatusDOExample();
        rechargeStatusDOExample.setOrderByClause("CREATE_TIME,DETAIL_LEVEL asc");
        rechargeStatusDOExample.createCriteria().andOrderSnEqualTo(orderSn);
        List<RechargeStatusDO> rechargeStatusDOS = rechargeStatusDOMapper.selectByExample(rechargeStatusDOExample);
        List<RechargeStatusDetailResDTO> resDTOList = null;
        if(CollUtil.isNotEmpty(rechargeStatusDOS)){
                resDTOList = rechargeStatusDOS.stream().map(item ->{
                RechargeStatusDetailResDTO rechargeStatusDetailResDTO = new RechargeStatusDetailResDTO();
                BeanUtils.copyProperties(item,rechargeStatusDetailResDTO);
                return rechargeStatusDetailResDTO;
            }).collect(Collectors.toList());
        }
        return resDTOList ;
    }
}
