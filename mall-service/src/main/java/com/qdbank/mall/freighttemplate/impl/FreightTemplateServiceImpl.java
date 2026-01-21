package com.qdbank.mall.freighttemplate.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.freighttemplate.FreightTemplateService;
import com.qdbank.mall.mapper.areafreighttemplate.AreafreighttemplateDOMapper;
import com.qdbank.mall.mapper.freighttemplate.FreighttemplateDOMapper;
import com.qdbank.mall.mapper.product.ProductDOMapper;
import com.qdbank.mall.model.areafreighttemplate.AreaFreightTemplateDetailDO;
import com.qdbank.mall.model.areafreighttemplate.AreafreighttemplateDO;
import com.qdbank.mall.model.freighttemplate.FreighttemplateDO;
import com.qdbank.mall.model.freighttemplate.FreighttemplateDOExample;
import com.qdbank.mall.model.freighttemplate.FreighttemplateListDO;
import com.qdbank.mall.model.merchant.MerchantDO;
import com.qdbank.mall.request.freighttemplate.*;
import com.qdbank.mall.response.freighttemplate.FreightTemplateResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.hutool.core.util.StrUtil.COMMA;

/**
 * FreightTemplateServiceImpl
 *
 * @author shaoshihang
 * @date 2021/3/4 18:02
 * @since 1.0.0
 */
@Service
@Slf4j
public class FreightTemplateServiceImpl extends BaseServiceImpl implements FreightTemplateService {
    @Resource
    private FreighttemplateDOMapper freighttemplateDOMapper;
    @Autowired
    private ProductDOMapper productDOMapper;
    @Resource
    private AreafreighttemplateDOMapper areafreighttemplateDOMapper;

    @Autowired
    @Qualifier("cmsAdminServiceImpl")
    private UmsAdminService umsAdminService;

    @Override
    public PageInfo<FreightTemplateResDTO> list(AreaFreightTemplateListReqDTO areaFreightTemplateListReqDTO) {
        List<FreightTemplateResDTO> resDTOS = new ArrayList<>();
        FreighttemplateDO freighttemplateDO = new FreighttemplateDO();
        BeanUtils.copyProperties(areaFreightTemplateListReqDTO, freighttemplateDO);
        this.injectMerchantNo(freighttemplateDO);
        if (areaFreightTemplateListReqDTO.getPageNum() != null && areaFreightTemplateListReqDTO.getPageSize() != null
                && StringUtils.isNotBlank(areaFreightTemplateListReqDTO.getIsPage())) {
            PageHelper.startPage(areaFreightTemplateListReqDTO.getPageNum(), areaFreightTemplateListReqDTO.getPageSize());
        }
        List<FreighttemplateDO> freighttemplateDOS = freighttemplateDOMapper.selectListByMerchanrNo(freighttemplateDO);
        for (FreighttemplateDO freighttemplate : freighttemplateDOS) {
            FreightTemplateResDTO resDTO = new FreightTemplateResDTO();
            if(freighttemplate.getStatus() == 0L){
                resDTO.setRelationFlag(productDOMapper.selectTemplateProduct(freighttemplate.getFreightTemplateId(),null) > 0 ? 1L : 0L );
                resDTO.setPublishStatus(productDOMapper.selectTemplateProduct(freighttemplate.getFreightTemplateId(),1L) > 0 ? 0L : 1L);
            }else{//待启用
                resDTO.setRelationFlag(0L);
                resDTO.setPublishStatus(1L);
            }
            BeanUtils.copyProperties(freighttemplate, resDTO);
            resDTOS.add(resDTO);
        }
        return super.getPageInfo(freighttemplateDOS, resDTOS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(FreightTemplateReqDTO freightTemplateReqDTO) {
        FreighttemplateDO freighttemplateDO = new FreighttemplateDO();
        BeanUtils.copyProperties(freightTemplateReqDTO, freighttemplateDO);
        freighttemplateDO.setStatus(Constant.TEMPLATE_STATUS_STOP);
        Long templateId = super.generateId();
        this.injectMerchantNo(freighttemplateDO);
        checkFreightNameExist(freighttemplateDO.getTemplateName(), freighttemplateDO.getMerchantNo());
        freighttemplateDO.setFreightTemplateId(templateId);
        if (StringUtils.isNotBlank(freightTemplateReqDTO.getAreaFreightTemplates())) {
            List<AreaFreightTemplateReqDTO> areaFreightTemplateReqDTOS = JsonUtil.json2List(freightTemplateReqDTO.getAreaFreightTemplates(), AreaFreightTemplateReqDTO.class);
            //转换异常
            if(areaFreightTemplateReqDTOS == null) {
                throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
            }
            checkProvinceId(areaFreightTemplateReqDTOS);

            for (AreaFreightTemplateReqDTO areaFreightTemplateReqDTO : areaFreightTemplateReqDTOS) {
                AreafreighttemplateDO areafreighttemplateDO = new AreafreighttemplateDO();
                BeanUtils.copyProperties(areaFreightTemplateReqDTO, areafreighttemplateDO);
                areafreighttemplateDO.setId(super.generateId());
                areafreighttemplateDO.setFreightTemplateId(templateId);
                umsAdminService.injectUserValue(areafreighttemplateDO);
                areafreighttemplateDOMapper.insert(areafreighttemplateDO);
            }
        }
        umsAdminService.injectUserValue(freighttemplateDO);
        return freighttemplateDOMapper.insert(freighttemplateDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(UpdateFreightTemplateReqDTO updateFreightTemplateReqDTO) {
        FreighttemplateDO freighttemplateDO = new FreighttemplateDO();
        BeanUtils.copyProperties(updateFreightTemplateReqDTO, freighttemplateDO);
        this.injectMerchantNo(freighttemplateDO);

        //查询该运费模板更新前的指定地区设置运费信息
        Set<Long> originalAreaFreightTemplateIds = Sets.newHashSet();
        List<AreafreighttemplateDO> areaFreightTemplateDOList =
                areafreighttemplateDOMapper.selectByFreightTemplateId(updateFreightTemplateReqDTO.getFreightTemplateId());
        if (CollectionUtil.isNotEmpty(areaFreightTemplateDOList)) {
            originalAreaFreightTemplateIds =
                    areaFreightTemplateDOList.stream().map(AreafreighttemplateDO::getId).collect(Collectors.toSet());
        }

        if (StringUtils.isNotBlank(updateFreightTemplateReqDTO.getAreaFreightTemplates())) {
            List<AreaFreightTemplateReqDTO> areaFreightTemplateReqDTOS = JsonUtil.json2List(updateFreightTemplateReqDTO.getAreaFreightTemplates(), AreaFreightTemplateReqDTO.class);
            //转换异常
            if(areaFreightTemplateReqDTOS == null) {
                throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
            }
            checkProvinceId(areaFreightTemplateReqDTOS);

            if (CollectionUtil.isNotEmpty(areaFreightTemplateReqDTOS)) {
                //与原始指定地区设置运费信息进行数据对比,判断哪些数据需要删除
                Set<Long> nowAreaFreightTemplateIds =
                        areaFreightTemplateReqDTOS.stream().map(AreaFreightTemplateReqDTO::getId).collect(Collectors.toSet());
                Set<Long> needDeleteAreaFreightTemplateIds = Sets.difference(originalAreaFreightTemplateIds, nowAreaFreightTemplateIds);
                if (CollectionUtil.isNotEmpty(needDeleteAreaFreightTemplateIds)) {
                    areafreighttemplateDOMapper.deleteBatch(needDeleteAreaFreightTemplateIds);
                }

                //若数据已存在,则进行数据更新;若不存在,则新增数据
                for (AreaFreightTemplateReqDTO areaFreightTemplateReqDTOList : areaFreightTemplateReqDTOS) {
                    AreafreighttemplateDO areafreighttemplateDO = new AreafreighttemplateDO();
                    BeanUtils.copyProperties(areaFreightTemplateReqDTOList, areafreighttemplateDO);
                    if (areafreighttemplateDO.getId() == null) {
                        umsAdminService.injectUserValue(areafreighttemplateDO);
                        areafreighttemplateDO.setId(super.generateId());
                        areafreighttemplateDO.setFreightTemplateId(updateFreightTemplateReqDTO.getFreightTemplateId());
                        areafreighttemplateDOMapper.insert(areafreighttemplateDO);
                    } else {
                        umsAdminService.injectUpdateUserValue(areafreighttemplateDO);
                        areafreighttemplateDOMapper.updateByPrimaryKeySelective(areafreighttemplateDO);
                    }
                }
            } else {
                //若当前更新后的指定地区设置运费信息为空,直接清空原始数据
                if(CollectionUtil.isNotEmpty(originalAreaFreightTemplateIds)) {
                    areafreighttemplateDOMapper.deleteBatch(originalAreaFreightTemplateIds);
                }
            }
        }
        return freighttemplateDOMapper.updateByPrimaryKeySelective(freighttemplateDO);
    }

    @Override
    public int updateStatus(UpdateFreightStatusReqDTO updateFreightStatusReqDTO) {
        FreighttemplateDO freighttemplateDO = new FreighttemplateDO();
        BeanUtils.copyProperties(updateFreightStatusReqDTO, freighttemplateDO);
        this.injectMerchantNo(freighttemplateDO);
        umsAdminService.injectUpdateUserValue(freighttemplateDO);
        int count = 0;
        try {
            count = freighttemplateDOMapper.updateByPrimaryKeySelective(freighttemplateDO);
            //禁用模板 清除关联商品模板
            if(count > 0 && updateFreightStatusReqDTO.getStatus() == 1L){
                productDOMapper.updateTemplateProduct(updateFreightStatusReqDTO.getFreightTemplateId());
            }
        } catch (DuplicateKeyException duplicateKeyException) {
            throw new ApiException(ResultCode.FREIGHT_TEMPLATE_NAME);
        }
        return count;
    }

    @Override
    public FreightTemplateResDTO detail(Long id) {
        FreightTemplateResDTO freightTemplateResDTO = new FreightTemplateResDTO();
        List<FreightTemplateResDTO.AreaFreightTemplateResDTO> areaFreightTemplateResDTOS = new ArrayList<>();
        AreaFreightTemplateDetailDO areaFreightTemplateDetail = new AreaFreightTemplateDetailDO();
        areaFreightTemplateDetail.setFreightTemplateId(id);
        FreighttemplateListDO freighttemplateListDO = freighttemplateDOMapper.selectByIdAndMerchantNo(areaFreightTemplateDetail);
        BeanUtils.copyProperties(freighttemplateListDO, freightTemplateResDTO);
        List<AreafreighttemplateDO> areafreighttemplateDOS = freighttemplateListDO.getAreafreighttemplateDO();
        if (!CollectionUtils.isEmpty(areafreighttemplateDOS)) {
            for (AreafreighttemplateDO areafreighttemplateDO : freighttemplateListDO.getAreafreighttemplateDO()) {
                FreightTemplateResDTO.AreaFreightTemplateResDTO areaFreightTemplateResDTO = new FreightTemplateResDTO.AreaFreightTemplateResDTO();
                BeanUtils.copyProperties(areafreighttemplateDO, areaFreightTemplateResDTO);
                areaFreightTemplateResDTOS.add(areaFreightTemplateResDTO);
            }
        }
        freightTemplateResDTO.setAreaFreightTemplates(areaFreightTemplateResDTOS);
        return freightTemplateResDTO;
    }

    private void injectMerchantNo(FreighttemplateDO freighttemplateDO) {
        MerchantDO merchantDO = umsAdminService.queryUserNameAndMerchantNo();
        freighttemplateDO.setMerchantNo(merchantDO.getMerchantNo());
    }

    /**
     * 检查运费模板名称是否重复
     *
     * @param freightName 运费模板名称
     * @param merchantNo  商户号
     */
    private void checkFreightNameExist(String freightName, long merchantNo) {
        FreighttemplateDOExample freighttemplateDOExample = new FreighttemplateDOExample();
        freighttemplateDOExample.createCriteria().andTemplateNameEqualTo(freightName).andMerchantNoEqualTo(merchantNo);
        List<FreighttemplateDO> freighttemplateDOS = freighttemplateDOMapper.selectByExample(freighttemplateDOExample);
        if (CollUtil.isNotEmpty(freighttemplateDOS)) {
            throw new ApiException(ResultCode.FREIGHT_TEMPLATE_NAME);
        }
    }

    /**
     * 检查指定地区选择的省份信息是否重复,防止出现省份相同运费信息相互冲突的情况
     * @param areaFreightTemplateReqDTOS    指定地区运费信息列表
     */
    private void checkProvinceId(List<AreaFreightTemplateReqDTO> areaFreightTemplateReqDTOS) {
        Set<String> provinceSet = Sets.newHashSet();
        List<String> provinceIdList = areaFreightTemplateReqDTOS.stream()
                .map(AreaFreightTemplateReqDTO::getProvinceIds).collect(Collectors.toList());
        if(CollectionUtil.isNotEmpty(provinceIdList)) {
            provinceIdList.stream().forEach(provinceIds -> Splitter.on(COMMA).splitToList(provinceIds).forEach(provinceId -> {
                if(!provinceSet.add(provinceId)) {
                    throw new ApiException(ResultCode.FREIGHT_TEMPLATE_PROVINCEIDS);
                }
            }));
        }
    }
}
