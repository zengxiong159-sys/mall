package com.qdbank.mall.center;

import com.github.pagehelper.PageInfo;
import com.qdbank.mall.request.center.CenterLikeReqDTO;
import com.qdbank.mall.response.center.CenterResDTO;

public interface CenterService {
    /**
     * 根据类型获取配置列表
     *
     * @param configTYpe 配置类型：1新闻中心2小程序隐私协议3商城隐私协议
     * @return
     */
    public PageInfo<CenterResDTO> list(CenterLikeReqDTO centerLikeReqDTO );
}
