package com.qdbank.mall.area;

import com.qdbank.mall.response.area.AreaResDTO;

import java.util.List;

/**
 * AreaService
 *
 * @author shaoshihang
 * @date 2021/3/26 10:55
 * @since 1.0.0
 */
public interface AreaService {

    List<AreaResDTO> selectProvince();
}
