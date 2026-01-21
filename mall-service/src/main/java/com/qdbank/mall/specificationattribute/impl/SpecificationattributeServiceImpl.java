package com.qdbank.mall.specificationattribute.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.api.StatusEnum;
import com.qdbank.mall.enums.SpecificationAttributeStatusEnum;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.productskuattrrelation.ProductSkuAttrRelationMapper;
import com.qdbank.mall.mapper.skustock.SkustockDOMapper;
import com.qdbank.mall.mapper.specificationattribute.SpecificationattributeDOMapper;
import com.qdbank.mall.model.productskuattrrelation.ProductSkuAttrRelationDO;
import com.qdbank.mall.model.skustock.SkustockDO;
import com.qdbank.mall.model.specificationattribute.SpecificationattributeDO;
import com.qdbank.mall.model.specificationattribute.SpecificationattributeDOExample;
import com.qdbank.mall.request.specificationattribute.*;
import com.qdbank.mall.response.specificationattribute.SpecificationattributeResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.specificationattribute.SpecificationattributeService;
import com.qdbank.mall.user.UmsAdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.qdbank.mall.constant.Constant.MAXIMUM_SPECIFICATION_ATTRIBUTE;
import static com.qdbank.mall.constant.Constant.STATUS_STOSHELF;

/**
 * SpecificationattributeServiceImpl
 *
 * @author shaoshihang
 * @date 2021/3/3 10:38
 * @since 1.0.0
 */
@Service
@Slf4j
public class SpecificationattributeServiceImpl extends BaseServiceImpl implements SpecificationattributeService {
    @Autowired
    private SpecificationattributeDOMapper specificationattributeDOMapper;

    @Autowired
    private ProductSkuAttrRelationMapper productSkuAttrRelationMapper;

    @Autowired
    private SkustockDOMapper skustockDOMapper;

    @Qualifier("cmsAdminServiceImpl")
    @Autowired
    private UmsAdminService umsAdminService;

    @Override
    public int create(SpecificationattributeReqDTO specificationattributeReqDTO) {
        SpecificationattributeDO specificationattributeDO = new SpecificationattributeDO();
        BeanUtils.copyProperties(specificationattributeReqDTO, specificationattributeDO);
        /**
         * 根据商户号、属性名、父级id查询规格属性
         * 若数据存在,判断该规格属性状态，若为未上架,则更新规格属性为已上架,否则提示该规格已存在
         * 若数据不存在,则直接新增数据
         */
        SpecificationattributeDO specificationattribute =
                specificationattributeDOMapper.selectAttrByMerchantNoPidAttrName(specificationattributeDO);
        int result;
        if (specificationattribute != null) {
            long status = specificationattribute.getStatus();
            long putOnStatus = SpecificationAttributeStatusEnum.PUT_ON.getCode();
            if (putOnStatus == status) {
                throw new ApiException(ResultCode.SPRCIFICATION_ATTRIBUTE_NAME);
            } else {
                specificationattribute.setStatus(putOnStatus);
                umsAdminService.injectUpdateUserValue(specificationattribute);
                result = specificationattributeDOMapper.updateByPrimaryKeySelective(specificationattribute);
            }
        } else {
            //新增数据之前,先判断当前规格属性(pid=0不作限制)对应的所有参数是否超过20个
            int countChildrenByPid = specificationattributeDOMapper.countChildrenByPid(specificationattributeDO);
            if (countChildrenByPid > MAXIMUM_SPECIFICATION_ATTRIBUTE) {
                throw new ApiException(ResultCode.SPECIFICATION_ATTRIBUTE_NAMBER);
            }
            specificationattributeDO.setId(super.generateId());
            umsAdminService.injectUserValue(specificationattributeDO);
            result = specificationattributeDOMapper.insert(specificationattributeDO);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(UpdateSpecificationNameReqDTO updateSpecificationNameReqDTO) {
        Long id = updateSpecificationNameReqDTO.getId();
        String currentAttributeName = updateSpecificationNameReqDTO.getName();

        SpecificationattributeDO specificationattributeDO = new SpecificationattributeDO();
        specificationattributeDO.setAttributeName(currentAttributeName);
        specificationattributeDO.setMerchantNo(updateSpecificationNameReqDTO.getMerchantNo());

        //判断该商户所有已上架或已入库商品是否已关联该属性,若存在关联数据则不允许编辑
        if (validateAttrExistProductRelation(id)) {
            throw new ApiException(ResultCode.SPRCIFICATION_ATTRIBUTE_NAME_CORRELATION);
        }

        /**
         * 根据商户号、父id查询当前商户现有规格属性同级数据(不区分上下架状态,排除自身)
         * 若存在该属性名称,判断是否是上架状态,如果是上架状态则提示规格属性已存在,不允许编辑;若为下架状态,则直接更新数据状态为上架,原数据做下架处理
         * 若不存在,直接更新数据
         */
        SpecificationattributeDO originalSpecificationattributeDO = new SpecificationattributeDO();
        originalSpecificationattributeDO.setId(id);
        SpecificationattributeDO  originalAttributeInfo = specificationattributeDOMapper.selectByPrimaryKey(originalSpecificationattributeDO);
        SpecificationattributeDOExample specificationattributeDOExample = new SpecificationattributeDOExample();
        SpecificationattributeDOExample.Criteria criteria = specificationattributeDOExample.createCriteria();
        criteria.andMerchantNoEqualTo(originalAttributeInfo.getMerchantNo());
        criteria.andParentIdEqualTo(originalAttributeInfo.getParentId());
        criteria.andIdNotEqualTo(originalAttributeInfo.getId());
        List<SpecificationattributeDO> specificationattributeDOList =
                specificationattributeDOMapper.selectByExample(specificationattributeDOExample);

        //查询是否关联待入库的商品,若存在关联商品,则清空该商品与当前规格属性的关联关系;若不存在,直接更新数据即可
        deleteAttributeAndProductRelation(id);

        //同一个商户号、同一个父id对应唯一属性名
        int result = 0;
        if(CollectionUtil.isNotEmpty(specificationattributeDOList)) {
            Map<String, SpecificationattributeDO> attributeNameAndStatusMap = specificationattributeDOList.stream()
                    .collect(Collectors.toMap(SpecificationattributeDO::getAttributeName, Function.identity()));
            if (attributeNameAndStatusMap.containsKey(currentAttributeName)) {
                long status = attributeNameAndStatusMap.get(currentAttributeName).getStatus();
                if(SpecificationAttributeStatusEnum.PUT_ON.getCode().equals(status)) {
                    throw new ApiException(ResultCode.SPRCIFICATION_ATTRIBUTE_NAME);
                } else if(SpecificationAttributeStatusEnum.PUT_OFF.getCode().equals(status)) {
                    //存在该规格属性,但已下架,将数据状态恢复为上架
                    specificationattributeDO.setId(attributeNameAndStatusMap.get(currentAttributeName).getId());
                    specificationattributeDO.setStatus(SpecificationAttributeStatusEnum.PUT_ON.getCode());
                    umsAdminService.injectUpdateUserValue(specificationattributeDO);
                    specificationattributeDOMapper.updateByPrimaryKeySelective(specificationattributeDO);

                    //原规格属性数据状态做下架处理
                    specificationattributeDO.setId(id);
                    specificationattributeDO.setStatus(SpecificationAttributeStatusEnum.PUT_OFF.getCode());
                    umsAdminService.injectUpdateUserValue(specificationattributeDO);
                    result = specificationattributeDOMapper.updateByPrimaryKeySelective(specificationattributeDO);
                }
            } else {
                //更新规格属性信息
                specificationattributeDO.setId(id);
                umsAdminService.injectUpdateUserValue(specificationattributeDO);
                result = specificationattributeDOMapper.updateByPrimaryKeySelective(specificationattributeDO);
            }
        } else {
            //无同级元素,直接更新
            specificationattributeDO.setId(id);
            umsAdminService.injectUpdateUserValue(specificationattributeDO);
            result = specificationattributeDOMapper.updateByPrimaryKeySelective(specificationattributeDO);
        }
        return result;
    }

    @Override
    public int delete(DeleteSpecificationReqDTO deleteSpecificationReqDTO) {
        //判断该商户所有已上架或已入库商品是否已关联该属性,若存在关联数据则不允许删除
        long id = deleteSpecificationReqDTO.getId();
        if (validateAttrExistProductRelation(id)) {
            throw new ApiException(ResultCode.SPRCIFICATION_ATTRIBUTE_NAME_CORRELATION);
        }

        /**
         *  查询是否关联待入库的商品,若存在关联商品,则清空该商品与当前规格属性的关联关系;若不存在,允许删除
         *  若当前属性为父级属性,在删除父属性同时,子属性也一并删除
         */
        deleteAttributeAndProductRelation(id);
        List<SpecificationattributeDO> childAttributeList = specificationattributeDOMapper.selectByParentId(id);
        if(CollectionUtil.isNotEmpty(childAttributeList)) {
            childAttributeList.stream().forEach(specificationattributeDO ->
                    deleteAttributeAndProductRelation(specificationattributeDO.getId()));
        }

        SpecificationattributeDO specificationattributeDO = new SpecificationattributeDO();
        specificationattributeDO.setStatus(0L);
        specificationattributeDO.setId(id);
        umsAdminService.injectUpdateUserValue(specificationattributeDO);
        //如果是删除父类则下面的子类也要一并删除
        specificationattributeDOMapper.updateParentStatusByParentId(specificationattributeDO);
        return specificationattributeDOMapper.updateByPrimaryKeySelective(specificationattributeDO);
    }

    @Override
    public List<SpecificationattributeResDTO> getList(SpecificationattributeParentListReqDTO specificationattributeParentListReqDTO) {
        List<SpecificationattributeResDTO> specificationattributeResDTOList = new ArrayList<>();
        SpecificationattributeDO specificationattribute = new SpecificationattributeDO();
        BeanUtils.copyProperties(specificationattributeParentListReqDTO, specificationattribute);
        SpecificationattributeDO specificationattributeDO = specificationattributeDOMapper.selectByPrimaryKey(specificationattribute);
        //递归查询
        List<SpecificationattributeResDTO> resDTOList = recursiveQuery(specificationattributeDO.getId());
        SpecificationattributeResDTO resDTO = new SpecificationattributeResDTO();
        BeanUtils.copyProperties(specificationattributeDO, resDTO);
        resDTO.setSpecificationattributeResDTOList(resDTOList);
        specificationattributeResDTOList.add(resDTO);
        return specificationattributeResDTOList;
    }

    @Override
    public PageInfo<SpecificationattributeResDTO> list(SpecificationattributeListReqDTO specificationattributeListReqDTO) {
        List<SpecificationattributeResDTO> resDTOList = new ArrayList<>();
        if (specificationattributeListReqDTO.getPageNum() != null && specificationattributeListReqDTO.getPageSize() != null && StringUtils.isNotBlank(specificationattributeListReqDTO.getIsPage())) {
            PageHelper.startPage(specificationattributeListReqDTO.getPageNum(), specificationattributeListReqDTO.getPageSize());
        }
        SpecificationattributeDO specificationattribute = new SpecificationattributeDO();
        BeanUtils.copyProperties(specificationattributeListReqDTO, specificationattribute);
        List<SpecificationattributeDO> specificationattributeDOList = specificationattributeDOMapper.selectAllList(specificationattribute);
        for (SpecificationattributeDO specificationattributeDO : specificationattributeDOList) {
            SpecificationattributeResDTO resDTO = new SpecificationattributeResDTO();
            BeanUtils.copyProperties(specificationattributeDO, resDTO);
            //递归查询
            List<SpecificationattributeResDTO> resDTOS = recursiveQuery(specificationattributeDO.getId());
            resDTO.setSpecificationattributeResDTOList(resDTOS);
            resDTOList.add(resDTO);
        }
        return super.getPageInfo(specificationattributeDOList, resDTOList);
    }

    @Override
    public List<SpecificationattributeResDTO> parentlist(ParentSpecificationattributeReqDTO specificationattributeListReqDTO) {
        SpecificationattributeDO specificationattribute = new SpecificationattributeDO();
        BeanUtils.copyProperties(specificationattributeListReqDTO, specificationattribute);
        List<SpecificationattributeResDTO> resDTOList = new ArrayList<>();
        List<SpecificationattributeDO> specificationattributeDOList = specificationattributeDOMapper.selectAllList(specificationattribute);
        for (SpecificationattributeDO specificationattributeDO : specificationattributeDOList) {
            SpecificationattributeResDTO resDTO = new SpecificationattributeResDTO();
            BeanUtils.copyProperties(specificationattributeDO, resDTO);
            resDTOList.add(resDTO);
        }
        return resDTOList;
    }


    /**
     * 递归查询
     *
     * @param id
     * @return
     */
    private List<SpecificationattributeResDTO> recursiveQuery(Long id) {
        List<SpecificationattributeResDTO> resDTOList = new ArrayList<>();
        //查询是否有子类
        List<SpecificationattributeDO> specificationattributeDOList = specificationattributeDOMapper.selectByParentId(id);
        for (SpecificationattributeDO specificationattributeDO : specificationattributeDOList) {
            SpecificationattributeResDTO resDTO = new SpecificationattributeResDTO();
            BeanUtils.copyProperties(specificationattributeDO, resDTO);
            //递归查询
            List<SpecificationattributeResDTO> resDTOS = recursiveQuery(specificationattributeDO.getId());
            resDTO.setSpecificationattributeResDTOList(resDTOS);
            resDTOList.add(resDTO);
        }

        return resDTOList;
    }

    /**
     * 判断该商户所有已上架或已入库商品是否已关联该属性
     *
     * @param attributeId 规格属性id
     * @return true:关联 false:未关联
     */
    private boolean validateAttrExistProductRelation(Long attributeId) {
        List<Long> productPublishedAndStoredStatus = Lists.newArrayList();
        productPublishedAndStoredStatus.add(StatusEnum.PUBLIST_STATUS_IN.getCode());
        productPublishedAndStoredStatus.add(StatusEnum.PUBLIST_STATUS_UP.getCode());
        List<ProductSkuAttrRelationDO> productSkuAttrRelationDOList =
                productSkuAttrRelationMapper.selectByAttrIdAndProductStatus(attributeId, productPublishedAndStoredStatus);
        return CollectionUtil.isNotEmpty(productSkuAttrRelationDOList);
    }

    /**
     * 删除该规格属性对应的关联数据
     *
     * @param attributeId 规格属性id
     */
    private void deleteAttributeAndProductRelation(Long attributeId) {
        List<Long> productPublishedAndStoredStatus = Lists.newArrayList();
        productPublishedAndStoredStatus.add(StatusEnum.PUBLISH_STATUS_OUT.getCode());
        List<ProductSkuAttrRelationDO> productSkuAttrRelationDOList =
                productSkuAttrRelationMapper.selectByAttrIdAndProductStatus(attributeId, productPublishedAndStoredStatus);
        if (CollectionUtil.isNotEmpty(productSkuAttrRelationDOList)) {
            Set<Long> productIdSet = productSkuAttrRelationDOList.stream().map(ProductSkuAttrRelationDO::getProductId).collect(Collectors.toSet());
            productIdSet.stream().forEach(productId -> {
                //该商品对应的规格信息全部下架
                SkustockDO skustockDO = new SkustockDO();
                skustockDO.setStatus(STATUS_STOSHELF);
                skustockDO.setProductId(productId);
                skustockDOMapper.updateByProductId(skustockDO);

                //商品-规格信息-规格属性关联关系数据全部清空
                ProductSkuAttrRelationDO productSkuAttrRelationDO = new ProductSkuAttrRelationDO();
                productSkuAttrRelationDO.setProductId(productId);
                productSkuAttrRelationMapper.delete(productSkuAttrRelationDO);
            });
        }
    }
}
