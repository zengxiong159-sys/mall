package com.qdbank.mall.refundsetting.impl;

import com.qdbank.mall.mapper.refundsetting.RefundsettingDOMapper;
import com.qdbank.mall.model.refundsetting.RefundsettingDO;
import com.qdbank.mall.refundsetting.RefundSettingService;
import com.qdbank.mall.request.refundsetting.RefundSettingReqDTO;
import com.qdbank.mall.request.refundsetting.UpdateRefundReasonReqDTO;
import com.qdbank.mall.request.refundsetting.UpdateRefundStatusReqDTO;
import com.qdbank.mall.response.refundsetting.RefundSettingResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.user.UmsAdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RefundSettingServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/27 21:52
 * @Version 1.0
 **/
@Service
public class RefundSettingServiceImpl extends BaseServiceImpl implements RefundSettingService {
    @Autowired
    private RefundsettingDOMapper refundsettingDOMapper;
    @Qualifier("umsAdminServiceImpl")
    @Autowired
    private UmsAdminService umsAdminService;
    @Override
    public int create(RefundSettingReqDTO refundSettingReqDTO) {
        RefundsettingDO refundsettingDO = new RefundsettingDO();
        BeanUtils.copyProperties(refundSettingReqDTO,refundsettingDO);
        refundsettingDO.setId(super.generateId());
        umsAdminService.injectUserValue(refundsettingDO);
        return refundsettingDOMapper.insert(refundsettingDO);
    }

    @Override
    public List<RefundSettingResDTO> list() {
        List<RefundsettingDO> refundsettingDOS = refundsettingDOMapper.selectByExample(null);
        List<RefundSettingResDTO> refundSettingResDTOS = new ArrayList<>();
        for(RefundsettingDO refundsettingDO : refundsettingDOS){
            RefundSettingResDTO refundSettingResDTO = new RefundSettingResDTO();
            BeanUtils.copyProperties(refundsettingDO,refundSettingResDTO);
            refundSettingResDTOS.add(refundSettingResDTO);
        }
        return refundSettingResDTOS;
    }

    @Override
    public int update(UpdateRefundReasonReqDTO refundSettingReqDTO) {
        RefundsettingDO refundsettingDO = new RefundsettingDO();
        BeanUtils.copyProperties(refundSettingReqDTO,refundsettingDO);
        umsAdminService.injectUpdateUserValue(refundsettingDO);
        return refundsettingDOMapper.updatedByPrimaryKeySelective(refundsettingDO);
    }

    @Override
    public int updateStatus(UpdateRefundStatusReqDTO updateRefundStatusReqDTO) {
        RefundsettingDO refundsettingDO = new RefundsettingDO();
        BeanUtils.copyProperties(updateRefundStatusReqDTO,refundsettingDO);
        umsAdminService.injectUpdateUserValue(refundsettingDO);
        return refundsettingDOMapper.updatedByPrimaryKeySelective(refundsettingDO);
    }

    @Override
    public RefundSettingResDTO detail(Long id) {
        RefundsettingDO refundsettingDO = refundsettingDOMapper.selectByPrimaryKey(id);
        RefundSettingResDTO refundSettingResDTO = new RefundSettingResDTO();
        BeanUtils.copyProperties(refundsettingDO,refundSettingResDTO);
        return refundSettingResDTO;
    }

    @Override
    public int delete(Long id) {
        return refundsettingDOMapper.deleteByPrimaryKey(id);
    }
}
