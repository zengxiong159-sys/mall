package com.qdbank.mall.send.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.qdbank.mall.mapper.send.SendReturnDOMapper;
import com.qdbank.mall.model.activity.ActivityDOExample;
import com.qdbank.mall.model.send.SendReturnDO;
import com.qdbank.mall.model.send.SendReturnDOExample;
import com.qdbank.mall.response.send.SendReturnResDTO;
import com.qdbank.mall.send.SendReturnService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName SendReturnServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/4/13 15:46
 * @Version 1.0
 **/
@Service
public class SendReturnServiceImpl implements SendReturnService {
    @Autowired
    private SendReturnDOMapper sendReturnDOMapper;
    @Override
    public List<SendReturnResDTO> querySendReturn(String orderSN) {
        SendReturnDOExample sendReturnDOExample = new SendReturnDOExample();
        sendReturnDOExample.setOrderByClause("create_time asc");
        SendReturnDOExample.Criteria criteria = sendReturnDOExample.createCriteria();
        criteria.andOrderSnEqualTo(orderSN);
        List<SendReturnDO> list = sendReturnDOMapper.selectByExample(sendReturnDOExample);
        if(CollectionUtil.isEmpty(list) || (CollectionUtil.isNotEmpty(list) && list.size() < 2)) return  null;
        List<SendReturnResDTO> sendReturnResDTOList = list.stream().map(sendReturnDO -> {
            SendReturnResDTO sendReturnResDTO = new SendReturnResDTO();
            BeanUtils.copyProperties(sendReturnDO,sendReturnResDTO);
            return sendReturnResDTO;
        }).collect(Collectors.toList());
        return sendReturnResDTOList;
    }
}
