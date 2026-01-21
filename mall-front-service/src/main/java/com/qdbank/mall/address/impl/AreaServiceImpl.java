package com.qdbank.mall.address.impl;

import com.qdbank.mall.address.AddressService;
import com.qdbank.mall.address.AreaService;
import com.qdbank.mall.address.mapper.AddressMapper;
import com.qdbank.mall.address.mapper.AreaMapper;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.dao.address.AddressDao;
import com.qdbank.mall.dao.address.AreaDao;
import com.qdbank.mall.dao.address.FreightTemplateDao;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.model.address.AddressDO;
import com.qdbank.mall.model.area.AreaDO;
import com.qdbank.mall.model.freighttemplate.FreighttemplateListDO;
import com.qdbank.mall.request.address.AddressReqDTO;
import com.qdbank.mall.request.address.SetDefaultAddressReqDTO;
import com.qdbank.mall.request.address.UpdateAddressReqDTO;
import com.qdbank.mall.response.address.AddressResDTO;
import com.qdbank.mall.response.area.AreaResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.util.JsonUtil;
import com.qdbank.mall.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @ClassName AreaServiceImpl
 * @Description TODO
 * @Author hongjh
 * @Date 2021/3/17 19:41
 * @Version 1.0
 **/
@Service
@Slf4j
public class AreaServiceImpl extends BaseServiceImpl implements AreaService, InitializingBean {

    @Autowired
    AreaDao areaDao;

    @Autowired
    AreaMapper areaMapper;

    @Autowired
    FreightTemplateDao freightTemplateDao;

    List<AreaResDTO> list = new ArrayList<>();

    Map<Long,AreaResDTO> all = new HashMap<>();

    Map<Long,AreaResDTO> _provines = new LinkedHashMap<>();
    Map<Long,AreaResDTO> _citys = new LinkedHashMap<>();
    Map<Long,AreaResDTO> _areas = new LinkedHashMap<>();



    @Override
    public FreighttemplateListDO qryTemplatesById(Long templateId){
       return freightTemplateDao.qryTemlates(templateId);
    }


    @Override
    public List<AreaResDTO> getProvines(){
        return list;
    }

    @Override
    public AreaResDTO getAreaByCode(String code){
        AreaResDTO obj =all.get(Long.parseLong(code));
        return obj;
    }

    @Override
    public List<AreaResDTO> getCity(String code){
        //获取某个省
        if(StringUtil.hasText(code)){
            AreaResDTO obj =_provines.get(Long.parseLong(code));
            if(obj!=null){
                return  obj.getChildren();
            }else{
                return null;
            }
        }
        //全部
        return new ArrayList<>(_citys.values());
    }

    @Override
    public List<AreaResDTO> getAreas(String code){
        //获取某个省
        if(StringUtil.hasText(code)){
            AreaResDTO obj= _citys.get(Long.parseLong(code));
            if(obj!=null){
                return  obj.getChildren();
            }else{
                return null;
            }
        }
        //全部
        return new ArrayList<>(_areas.values());
    }


    @Override
    public List<AreaResDTO> qryAreas(){
        //清空
        list.clear();
        all.clear();
        _provines.clear();
        _citys.clear();
        _areas.clear();
      //获取地区
      List<AreaDO> areas=areaDao.qryAreas(null);
      areas.stream().forEach(item->{
          long parentId =item.getParentId();
          AreaResDTO res =areaMapper.dTOToPo(item);
          //最顶层
          if(parentId==0L){
              res.setAreaType("0");
              list.add(res);
              _provines.put(item.getId(),res);
          }else{
              //子类
              AreaResDTO parent  =all.get(res.getParentId());
              List<AreaResDTO> childs = parent.getChildren();
              if(childs==null){
                  childs = new ArrayList<>();
                  parent.setChildren(childs);
              }
              if("0".equals(parent.getAreaType())){
                  res.setAreaType("1");
                  _citys.put(item.getId(),res);
              }else{
                  res.setAreaType("2");
                  _areas.put(item.getId(),res);
              }
              childs.add(res);
          }
          all.put(res.getId(),res);
      });
        return list;
  }


    @Override
    public void afterPropertiesSet() throws Exception {
        //启动加载省市区
        qryAreas();
    }
}
