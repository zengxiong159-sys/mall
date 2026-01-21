package com.qdbank.mall.mapper.bind;

import com.qdbank.mall.model.bind.WechatBindInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WechatBindInfoMapper {

    int insert(WechatBindInfoDO wechatBindInfoDO);

    int disable(@Param("openId") String openId,@Param("idNo") String idNo);

    int disableByOpenId(@Param("openId")String openId);

    int enableByIdNoAndOpenId(WechatBindInfoDO wechatBindInfoDO);

    WechatBindInfoDO queryByOpenId(@Param("openId")String openId);
}
