package com.qdbank.mall.merchant.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.cmsuser.CmsAdminCacheService;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.cmsuser.CmsAdminMapper;
import com.qdbank.mall.mapper.merchant.MerchantDOMapper;
import com.qdbank.mall.mapper.product.ProductDOMapper;
import com.qdbank.mall.merchant.MerchantCacheService;
import com.qdbank.mall.merchant.MerchantService;
import com.qdbank.mall.model.merchant.MerchantDO;
import com.qdbank.mall.model.merchant.MerchantDOExample;
import com.qdbank.mall.model.product.ProductDO;
import com.qdbank.mall.model.product.ProductDOExample;
import com.qdbank.mall.model.user.UserInfoDO;
import com.qdbank.mall.model.user.UserInfoDOExample;
import com.qdbank.mall.request.merchant.MerchantLikeQueryReqDTO;
import com.qdbank.mall.request.merchant.MerchantReqDTO;
import com.qdbank.mall.request.merchant.UpdateMerchantReqDTO;
import com.qdbank.mall.request.merchant.UpdateMerchantStatusReqDTO;
import com.qdbank.mall.response.merchant.MerchantResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.util.TimeUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName MerchantServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/24 11:37
 * @Version 1.0
 **/
@Service
@RefreshScope
public class MerchantServiceImpl extends BaseServiceImpl implements MerchantService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MerchantDOMapper merchantDOMapper;
    @Qualifier("umsAdminServiceImpl")
    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private MerchantCacheService merchantCacheService;
    @Autowired
    private CmsAdminMapper cmsAdminMapper;

    @Autowired
    private CmsAdminCacheService cmsAdminCacheService;

    @Value("${redis.database-cms:mall-cms}")
    private String redisDatabase;

    @Value("${redis.key-cms-admin:cms:admin}")
    private String redisKeyAdmin;
    @Autowired
    private ProductDOMapper productDOMapper;
    @Override
    public PageInfo<MerchantResDTO> list(MerchantLikeQueryReqDTO merchantLikeQueryReqDTO) {
        PageHelper.startPage(merchantLikeQueryReqDTO.getPageNum(), merchantLikeQueryReqDTO.getPageSize());
        MerchantDOExample merchantDOExample = new MerchantDOExample();
        MerchantDOExample.Criteria criteria = merchantDOExample.createCriteria();
        merchantDOExample.setOrderByClause("create_time desc");
        if(merchantLikeQueryReqDTO.getMerchantNo() != null){
            criteria.andMerchantNoEqualTo(merchantLikeQueryReqDTO.getMerchantNo());
        }
        if(StrUtil.isNotEmpty(merchantLikeQueryReqDTO.getMerchantName())){
            criteria.andMerchantNameLike("%"+merchantLikeQueryReqDTO.getMerchantName()+"%");
        }
        if(merchantLikeQueryReqDTO.getStatus() != null){
            criteria.andStatusEqualTo(merchantLikeQueryReqDTO.getStatus());
        }
        if(merchantLikeQueryReqDTO.getEndTime() != null){
            criteria.andStartTimeLessThanOrEqualTo(TimeUtil.dateEndChange(merchantLikeQueryReqDTO.getEndTime()));
        }
        if(merchantLikeQueryReqDTO.getStartTime() != null){
            criteria.andStartTimeGreaterThanOrEqualTo(TimeUtil.dateZeroChange(merchantLikeQueryReqDTO.getStartTime()));
        }
        if(merchantLikeQueryReqDTO.getCreateStartTime() != null){
            criteria.andCreateTimeGreaterThanOrEqualTo(TimeUtil.dateZeroChange(merchantLikeQueryReqDTO.getCreateStartTime()));
        }
        if(merchantLikeQueryReqDTO.getCreateEndTime() != null){
            criteria.andCreateTimeLessThanOrEqualTo(TimeUtil.dateEndChange(merchantLikeQueryReqDTO.getCreateEndTime()));
        }
        List<MerchantDO> merchantDOS = merchantDOMapper.selectByExample(merchantDOExample);
        if(CollUtil.isEmpty(merchantDOS)) return  null;
        List<MerchantResDTO> merchantResDTOList = new ArrayList<>();
        merchantDOS.forEach(merchantDO -> {
            MerchantResDTO merchantResDTO = new MerchantResDTO();
            BeanUtils.copyProperties(merchantDO,merchantResDTO);
            merchantResDTOList.add(merchantResDTO);
        });
        return super.getPageInfo(merchantDOS,merchantResDTOList);
    }

    @Override
    public int create(MerchantReqDTO merchantReqDTO) {
        //校验管理员邮箱是否存在
        MerchantDO merchantDO = new MerchantDO();
        BeanUtils.copyProperties(merchantReqDTO,merchantDO);
        umsAdminService.injectUserValue(merchantDO);
        registerMember(merchantReqDTO);//默认给商户开通管理员用户
        return merchantDOMapper.insert(merchantDO);
    }

    @Override
    public int delete(Long id) {
       int count = merchantDOMapper.deleteByPrimaryKey(id);
        if(count > 0) merchantCacheService.delMerchant(id);
        return count;
    }

    @Override
    public int update(UpdateMerchantReqDTO merchantReqDTO) {
        MerchantDO merchantDO = new MerchantDO();
        BeanUtils.copyProperties(merchantReqDTO,merchantDO);
        umsAdminService.injectUpdateUserValue(merchantDO);
        MerchantDO oldMerchantDO = merchantDOMapper.selectByPrimaryKey(merchantDO.getMerchantNo());
        if(oldMerchantDO  == null) throw new ApiException(ResultCode.MERCHANT_NOT_FOUND_ERROR);
        if(StringUtils.isNotBlank(merchantReqDTO.getMerchantName()) && !merchantReqDTO.getMerchantName().equals(oldMerchantDO.getMerchantName())){
            //商户名称修改 同步修改商品信息
            ProductDO productDO = new ProductDO();
            productDO.setMerchantName(merchantReqDTO.getMerchantName());
            productDO.setUpdateTime(new Date());
            productDO.setUpdatedBy(merchantDO.getUpdatedBy());
            ProductDOExample productDOExample = new ProductDOExample();
            productDOExample.createCriteria().andMerchantNoEqualTo(merchantReqDTO.getMerchantNo());
            productDOMapper.updateByExampleSelective(productDO,productDOExample);
        }
        int count = merchantDOMapper.updateByPrimaryKeySelective(merchantDO);

        //同步商户用户管理员密码
        String currentEmail = merchantReqDTO.getEmail();
        String currentMobile = merchantReqDTO.getMobile();
        String oldEmail = merchantReqDTO.getOldEmail();
        if(StringUtils.isNotBlank(currentEmail) && StringUtils.isNotBlank(currentMobile) && StringUtils.isNotBlank(oldEmail)){
            UserInfoDOExample userInfoDOExample = new UserInfoDOExample();
            userInfoDOExample.createCriteria().andUsernameEqualTo(oldEmail);
            List<UserInfoDO> userInfoDOList = cmsAdminMapper.selectByExample(userInfoDOExample);
            if(CollUtil.isNotEmpty(userInfoDOList)){
                String defaultPassword = currentEmail.substring(0, 4) + currentMobile.substring(currentMobile.length() - 4);
                String encodePassword = passwordEncoder.encode(defaultPassword);
                UserInfoDO oldUserInfoDO = userInfoDOList.get(0);
                if(!currentEmail.equals(oldEmail) || !currentMobile.equals(oldUserInfoDO.getMobile())){
                    UserInfoDO userInfoDO = new UserInfoDO();
                    userInfoDO.setPassword(encodePassword);
                    userInfoDO.setEmail(currentEmail);
                    userInfoDO.setUsername(currentEmail);
                    userInfoDO.setMobile(currentMobile);
                    userInfoDO.setId(oldUserInfoDO.getId());
                    if(cmsAdminMapper.updateByPrimaryKeySelective(userInfoDO) > 0) {
                        cmsAdminCacheService.deleteKey(redisDatabase, redisKeyAdmin, oldEmail);
                    }
                }
            }
        }
        delMerchantFromCache(count,merchantDO.getMerchantNo());
        return count;
    }

    @Override
    public MerchantResDTO getItem(Long id) {
        MerchantResDTO merchantResDTO = new MerchantResDTO();
        MerchantDO merchantDO = merchantCacheService.getMerchantById(id);
        if(merchantDO == null){
            merchantDO = merchantDOMapper.selectByPrimaryKey(id);
            if(merchantDO == null) return null;
            merchantCacheService.setMerchant(merchantDO);
        }
        BeanUtils.copyProperties(merchantDO,merchantResDTO);
        return merchantResDTO;
    }

    @Override
    public MerchantResDTO selectByEmail(String email) {
        MerchantResDTO merchantResDTO = new MerchantResDTO();
        MerchantDO merchantDO = merchantDOMapper.selectByEmail(email);
        if (merchantDO != null){
            BeanUtils.copyProperties(merchantDO,merchantResDTO);
        }
        return merchantResDTO;
    }

    @Override
    public int updateStatus(UpdateMerchantStatusReqDTO updateMerchantStatusReqDTO) {
        MerchantDO merchantDO = new MerchantDO();
        BeanUtils.copyProperties(updateMerchantStatusReqDTO,merchantDO);
        umsAdminService.injectUpdateUserValue(merchantDO);
        int count = merchantDOMapper.updateStatusByPrimaryKey(merchantDO);
        this.delMerchantFromCache(count,merchantDO.getMerchantNo());
        return count;
    }


    /**
     * 创建商户管理员账号
     * @param merchantReqDTO
     */
    private void registerMember(MerchantReqDTO merchantReqDTO){
        UserInfoDOExample userInfoDOExample = new UserInfoDOExample();
        userInfoDOExample.createCriteria().andUsernameEqualTo(merchantReqDTO.getEmail());
        List<UserInfoDO> list = cmsAdminMapper.selectByExample(userInfoDOExample);
        if(CollUtil.isNotEmpty(list)) throw  new ApiException(ResultCode.MERCHANT_ADMIN_EXIST);
        UserInfoDO userInfoDO = new UserInfoDO();
        userInfoDO.setStatus(1L);
        userInfoDO.setUsername(merchantReqDTO.getEmail());
        userInfoDO.setId(super.generateId());
        String defaultPassword = merchantReqDTO.getEmail().substring(0,4)+merchantReqDTO.getMobile().substring(merchantReqDTO.getMobile().length() - 4,merchantReqDTO.getMobile().length());
        String encodePassword = passwordEncoder.encode(defaultPassword);
        userInfoDO.setPassword(encodePassword);
        userInfoDO.setNickName(merchantReqDTO.getAdminName());
        userInfoDO.setEmail(merchantReqDTO.getEmail());
        userInfoDO.setMobile(merchantReqDTO.getMobile());
        umsAdminService.injectUserValue(userInfoDO);
        cmsAdminMapper.insert(userInfoDO);
    }
    private void delMerchantFromCache(int count,Long merchantNo){
        if(count > 0){
            merchantCacheService.delMerchant(merchantNo);
        }
    }
}
