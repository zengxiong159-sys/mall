package com.qdbank.mall.address;

import com.qdbank.mall.model.freighttemplate.FreighttemplateListDO;
import com.qdbank.mall.request.address.AddressReqDTO;
import com.qdbank.mall.request.address.SetDefaultAddressReqDTO;
import com.qdbank.mall.request.address.UpdateAddressReqDTO;
import com.qdbank.mall.response.address.AddressResDTO;
import com.qdbank.mall.response.area.AreaResDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AreaService {



    FreighttemplateListDO qryTemplatesById(Long templateId);

    List<AreaResDTO> getProvines();

    AreaResDTO getAreaByCode(String code);

    List<AreaResDTO> getCity(String code);

    List<AreaResDTO> getAreas(String code);

    List<AreaResDTO> qryAreas();
}
