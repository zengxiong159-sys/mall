package com.qdbank.mall.download.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdbank.mall.download.DownloadService;
import com.qdbank.mall.mapper.download.DownloadDOMapper;
import com.qdbank.mall.model.department.UmsDepartmentDO;
import com.qdbank.mall.model.department.UmsDepartmentDOExample;
import com.qdbank.mall.model.download.DownloadDO;
import com.qdbank.mall.model.download.DownloadDOExample;
import com.qdbank.mall.replace.ReplaceService;
import com.qdbank.mall.request.download.DownloadReqDTO;
import com.qdbank.mall.response.dept.DeptResDTO;
import com.qdbank.mall.response.download.DownloadRespDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.util.TimeUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DownloadServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2022/1/13 14:30
 * @Version 1.0
 **/
@Service
public class DownloadServiceImpl extends BaseServiceImpl implements DownloadService {
    @Autowired
    private DownloadDOMapper downloadDOMapper;
    @Autowired
    private ReplaceService replaceService;
    @Override
    public PageInfo<DownloadRespDTO> downloadList(DownloadReqDTO downloadReqDTO) {
        PageHelper.startPage(downloadReqDTO.getPageNum(),downloadReqDTO.getPageSize());
        DownloadDOExample downloadDOExample = new DownloadDOExample();
        downloadDOExample.setOrderByClause("create_time desc");
        DownloadDOExample.Criteria criteria = downloadDOExample.createCriteria();
        if(StringUtils.isNotBlank(downloadReqDTO.getFileName())){
            criteria.andFileNameLike("%"+downloadReqDTO.getFileName()+"%");
        }
        if(downloadReqDTO.getStartTime() != null && downloadReqDTO.getEndTime() != null){
            criteria.andCreateTimeGreaterThan(TimeUtil.dateZeroChange(downloadReqDTO.getStartTime()));
            criteria.andCreateTimeLessThan(TimeUtil.dateEndChange(downloadReqDTO.getEndTime()));
        }
        if(downloadReqDTO.getStatus() != null){
            criteria.andStatusEqualTo(downloadReqDTO.getStatus());
        }
        if(downloadReqDTO.getFileType() != null){
            criteria.andFileTypeEqualTo(downloadReqDTO.getFileType());
        }
        if(downloadReqDTO.getMerchantNo() != null){
            criteria.andMerchantNoEqualTo(downloadReqDTO.getMerchantNo());
        }
        if(downloadReqDTO.getCreatedBy() != null){
            criteria.andCreatedByEqualTo(downloadReqDTO.getCreatedBy());
        }
        List<DownloadDO> downloadDOS = downloadDOMapper.selectByExample(downloadDOExample);
        List<DownloadRespDTO> deptResDTOS = new ArrayList<>();
        for(DownloadDO downloadDO : downloadDOS){
            DownloadRespDTO downloadRespDTO = new DownloadRespDTO();
            BeanUtils.copyProperties(downloadDO,downloadRespDTO);
            downloadRespDTO.setFileUrl(downloadReqDTO.getMerchantNo() != null ? downloadRespDTO.getFileUrl() : replaceService.replaceUrl(downloadRespDTO.getFileUrl()));
            deptResDTOS.add(downloadRespDTO);
        }
        return super.getPageInfo(downloadDOS,deptResDTOS);
    }
}
