package com.qdbank.mall.mapper.specificationattribute;

import com.qdbank.mall.model.specificationattribute.SpecificationattributeDO;
import com.qdbank.mall.model.specificationattribute.SpecificationattributeDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SpecificationattributeDOMapper {
    long countByExample(SpecificationattributeDOExample example);

    int deleteByExample(SpecificationattributeDOExample example);

    int deleteByPrimaryKey(Long id);

    int deleteByParentId(Long id);

    int insert(SpecificationattributeDO record);

    int insertSelective(SpecificationattributeDO record);

    List<SpecificationattributeDO> selectByExample(SpecificationattributeDOExample example);

    List<String> selectByParentIdAndMerchantNo(SpecificationattributeDO specificationattributeDO);

    List<String> selectByMerchantNo(SpecificationattributeDO specificationattributeDO);

    SpecificationattributeDO selectByPrimaryKey(SpecificationattributeDO specificationattributeDO);

    List<SpecificationattributeDO> selectAllList(SpecificationattributeDO specificationattributeDO);

    List<SpecificationattributeDO> selectByParentId(Long parentId);

    int updateByExampleSelective(@Param("record") SpecificationattributeDO record, @Param("example") SpecificationattributeDOExample example);

    int updateByExample(@Param("record") SpecificationattributeDO record, @Param("example") SpecificationattributeDOExample example);

    int updateByPrimaryKeySelective(SpecificationattributeDO record);

    int updateByPrimaryKey(SpecificationattributeDO record);

    /**
     * 修改父id规格属性状态
     * @param record
     * @return
     */
    int updateParentStatusByParentId(SpecificationattributeDO record);

    /**
     * 根据规格属性名查询所有已上架和已入库商品的商品属性
     * @param id 规格属性id
     * @return  商品属性
     */
    List<String> selectProductSpDataByAttrName(Long id);

    /**
     * 根据商户号、属性名、父级id查询规格属性
     * @param specificationattributeDO  规格属性信息
     * @return  规格属性
     */
    SpecificationattributeDO selectAttrByMerchantNoPidAttrName(SpecificationattributeDO specificationattributeDO);

    /**
     * 查询当前规格属性下对应已上架参数的数量
     * @param specificationattributeDO 规格属性信息
     * @return  参数数量
     */
    int countChildrenByPid(SpecificationattributeDO specificationattributeDO);

    /**
     * 查询当前属性所有已上架的参数名称
     * @param specificationattributeDO  规格属性信息
     * @return  已上架的参数名称
     */
    List<String> selectChildrenByPid(SpecificationattributeDO specificationattributeDO);
}