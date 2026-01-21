package com.qdbank.mall.userwhite.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.monitorjbl.xlsx.StreamingReader;
import com.qdbank.mall.annotation.CheckRole;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.domain.ExportDataBO;
import com.qdbank.mall.enums.GenderEnum;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.mapper.userwhite.UserWhiteDOMapper;
import com.qdbank.mall.model.role.RoleDO;
import com.qdbank.mall.model.user.UserInfoDO;
import com.qdbank.mall.model.userwhite.UserWhiteDO;
import com.qdbank.mall.model.userwhite.UserWhiteDOExample;
import com.qdbank.mall.request.userwhite.UpdateStatusReqDTO;
import com.qdbank.mall.request.userwhite.UserWhiteCreateReqDTO;
import com.qdbank.mall.request.userwhite.UserWhiteQueryReqDTO;
import com.qdbank.mall.request.userwhite.UserWhiteUpdateReqDTO;
import com.qdbank.mall.response.financial.OrderTotalResDTO;
import com.qdbank.mall.response.role.RoleResDTO;
import com.qdbank.mall.response.userwhite.UserWhiteResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.userwhite.UserWhiteService;
import com.qdbank.mall.util.ExcelExportUtil;
import com.qdbank.mall.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static cn.hutool.core.util.CharUtil.DOT;

@Service
@Slf4j
public class UserWhiteServiceImpl extends BaseServiceImpl implements UserWhiteService {
    @Qualifier("umsAdminServiceImpl")
    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private UserWhiteDOMapper userWhiteDOMapper;

    @Override
    public void uploadUserName(MultipartFile multipartFile) {
        if (multipartFile == null) throw new ApiException(ResultCode.COUPON_FILE_EMPTY);
        List<UserWhiteDO> userWhiteDOS = this.checkFile(multipartFile);
        if (CollectionUtil.isNotEmpty(userWhiteDOS)) {
            for (UserWhiteDO userWhiteDO : userWhiteDOS) {
                try {
                    userWhiteDOMapper.insert(userWhiteDO);
                } catch (DuplicateKeyException duplicateKeyException) {
                    log.info("身份证号：{}重复异常：{}", userWhiteDO.getIdNo(), duplicateKeyException);
                }
            }
        }
    }

    @Override
    public PageInfo<UserWhiteResDTO> list(UserWhiteQueryReqDTO userWhiteQueryReqDTO) {
        PageHelper.startPage(userWhiteQueryReqDTO.getPageNum(), userWhiteQueryReqDTO.getPageSize());
        List<UserWhiteDO> userWhiteDOList = this.queryUserWhiteDO(userWhiteQueryReqDTO);
        List<UserWhiteResDTO> userWhiteResDTOS = new ArrayList<>();
        UserInfoDO userInfoDO = umsAdminService.getUserInfoByToken();
        if (CollectionUtil.isNotEmpty(userWhiteDOList)) {
            for (UserWhiteDO userWhiteDO : userWhiteDOList) {
                UserWhiteResDTO userWhiteResDTO = new UserWhiteResDTO();
                BeanUtils.copyProperties(userWhiteDO, userWhiteResDTO);
                if (userWhiteDO.getUpdatedBy().equals(userInfoDO.getNickName())) {
                    userWhiteResDTO.setHidden(0);
                } else {
                    userWhiteResDTO.setHidden(1);
                }
                userWhiteResDTOS.add(userWhiteResDTO);
            }
        }
        return super.getPageInfo(userWhiteDOList, userWhiteResDTOS);
    }

    @Override
    public void create(UserWhiteCreateReqDTO userWhiteCreateReqDTO) {
        try {
            UserWhiteDO userWhiteDO = new UserWhiteDO();
            BeanUtils.copyProperties(userWhiteCreateReqDTO, userWhiteDO);
            userWhiteDO.setStatus("0");
            userWhiteDO.setId(super.generateId());
            umsAdminService.injectUserValue(userWhiteDO);
            userWhiteDOMapper.insert(userWhiteDO);
        } catch (DuplicateKeyException duplicateKeyException) {
            log.error("身份证号:{}重复异常：{}", userWhiteCreateReqDTO.getIdNo(), duplicateKeyException);
            throw new ApiException(ResultCode.ID_NO_REPEAT);
        }
    }

    @Override
    public void update(UserWhiteUpdateReqDTO userWhiteUpdateReqDTO) {
        try {
            UserWhiteDO userWhiteDO = new UserWhiteDO();
            BeanUtils.copyProperties(userWhiteUpdateReqDTO, userWhiteDO);
            umsAdminService.injectUpdateUserValue(userWhiteDO);
            userWhiteDOMapper.updateByPrimaryKeySelective(userWhiteDO);
        } catch (DuplicateKeyException duplicateKeyException) {
            log.error("身份证号:{}重复异常：{}", userWhiteUpdateReqDTO.getIdNo(), duplicateKeyException);
            throw new ApiException(ResultCode.ID_NO_REPEAT);
        }
    }

    @Override
    public UserWhiteResDTO detail(Long id) {
        UserWhiteDO userWhiteDO = userWhiteDOMapper.selectByPrimaryKey(id);
        UserWhiteResDTO userWhiteResDTO = new UserWhiteResDTO();
        BeanUtils.copyProperties(userWhiteDO, userWhiteResDTO);
        return userWhiteResDTO;
    }

    @Override
    @CheckRole
    public void batchUpdatStatus(String ids) {
        if (StringUtils.isNotBlank(ids)) {
            String[] idsStr = ids.split(",");
            for (String id : idsStr) {
                UserWhiteDO userWhiteDO = new UserWhiteDO();
                userWhiteDO.setId(Long.valueOf(id));
                userWhiteDO.setStatus("1");
                umsAdminService.injectUpdateUserValue(userWhiteDO);
                userWhiteDOMapper.updateByPrimaryKeySelective(userWhiteDO);
            }
        }
    }

    @Override
    public void export(HttpServletResponse response, UserWhiteQueryReqDTO userWhiteQueryReqDTO) {
        List<UserWhiteDO> userWhiteDOS = this.queryUserWhiteDO(userWhiteQueryReqDTO);
        if (CollectionUtil.isEmpty(userWhiteDOS)) throw new ApiException(ResultCode.DATA_EMPTY);
        List<String> rowHead = CollUtil.newLinkedList("用户编号", "用户姓名", "性别", "手机号", "身份证号", "创建时间","状态");
        ExportDataBO<OrderTotalResDTO> exportDataBO = new ExportDataBO<>("用户白名单", "用户白名单", Constant.COUPON_FILE_SUFFIX, rowHead);
        exportDataBO.setFileName("用户白名单" + (DateUtil.format(userWhiteQueryReqDTO.getStartTime(), DatePattern.NORM_DATE_PATTERN)) + "-" + (DateUtil.format(userWhiteQueryReqDTO.getEndTime(), DatePattern.NORM_DATE_PATTERN)));
        List<List<String>> datas = CollUtil.newLinkedList();
        for (UserWhiteDO userWhiteDO : userWhiteDOS) {
            List<String> data = CollUtil.newLinkedList(userWhiteDO.getId() + "", userWhiteDO.getUserName(), GenderEnum.getDesc(userWhiteDO.getGender()), userWhiteDO.getMobile(), userWhiteDO.getIdNo(), DateUtil.format(userWhiteDO.getCreateTime(), DatePattern.NORM_DATETIME_PATTERN),"0".equals(userWhiteDO.getStatus()) ? "停用" : "启用");
            datas.add(data);
        }
        exportDataBO.setData(datas);
        ExcelExportUtil.exportExcelByHuTools(response, exportDataBO);
    }

    @Override
    @CheckRole
    public int updateStatus(UpdateStatusReqDTO updateStatusReqDTO) {
        UserWhiteDO userWhiteDO = new UserWhiteDO();
        userWhiteDO.setId(updateStatusReqDTO.getId());
        userWhiteDO.setStatus(updateStatusReqDTO.getStatus());
        umsAdminService.injectUpdateUserValue(userWhiteDO);
        return userWhiteDOMapper.updateByPrimaryKeySelective(userWhiteDO);
    }


    /**
     * 校验文件类型
     *
     * @param fileName 文件名
     */
    public void checkFileType(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            throw new ApiException(ResultCode.COUPON_FILE_TYPE_ERROR);
        }
        String fileEndName = fileName.substring(fileName.lastIndexOf(DOT));
        if (StringUtils.isNotBlank(fileEndName)) {
            if (!fileEndName.endsWith(".xlsx")) {
                throw new ApiException(ResultCode.COUPON_FILE_TYPE_ERROR);
            }
        } else {
            throw new ApiException(ResultCode.COUPON_FILE_TYPE_ERROR);
        }
    }

    /**
     * 判断文件大小
     *
     * @param len  文件长度
     * @param size 限制大小
     * @param unit 限制单位（B,K,M,G）
     */
    public void checkFileSize(Long len, int size, String unit) {
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())) {
            fileSize = (double) len;
        } else if ("K".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if ("M".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1048576;
        } else if ("G".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }
        if (fileSize > size) {
            throw new ApiException(ResultCode.COUPON_FILE_OUT_SIZE);
        }
    }

    /**
     * 检查名单数据是否重复
     *
     * @param list 名单数据
     */
    public void checkRepeatData(List<UserWhiteDO> list) {
        long count = list.stream().distinct().count();
        if (list.size() != count) {
            throw new ApiException(ResultCode.COUPON_FILE_EXIST_REPEATE_DATE);
        }
    }


    /**
     * 校验白名单文件
     *
     * @param file 白名单文件
     * @return 客户号
     */
    public List<UserWhiteDO> checkFile(MultipartFile file) {
        if (file == null) {
            throw new ApiException(ResultCode.COUPON_FILE_EMPTY);
        }
        List<UserWhiteDO> readList;
        try {
            checkFileType(file.getOriginalFilename());
            checkFileSize(file.getSize(), 5, "M");
            Long start = System.currentTimeMillis();
            readList = getAllUserWhite(file);
            log.info("读取文件总耗时:{}", (System.currentTimeMillis() - start));
            if (CollectionUtil.isEmpty(readList)) {
                throw new ApiException(ResultCode.COUPON_FILE_EMPTY);
            }
            if (readList.size() > 50) {
                throw new ApiException("一次最多支持50条记录");
            }
            //checkRepeatData(readList);
        } catch (ApiException api) {
            throw api;
        } catch (Exception e) {
            log.error("校验上传文件异常：" + e);
            throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
        }
        return readList;
    }

    /**
     * 读取白名单文件客户号
     *
     * @param file 白名单文件
     * @return 客户号名单
     */
    private List<UserWhiteDO> getAllUserWhite(MultipartFile file) {
        Workbook wk = null;
        try {
            wk = StreamingReader.builder()
                    //缓存到内存中的行数，默认是10
                    .rowCacheSize(100)
                    //读取资源时，缓存到内存的字节大小，默认是1024
                    .bufferSize(4096)
                    //打开资源，必须，可以是InputStream或者是File，注意：只能打开xlsx格式的文件
                    .open(file.getInputStream());

            //读取第一个sheet
            Sheet sheet = wk.getSheetAt(0);
            if (sheet != null) {
                //遍历所有的行
                List<UserWhiteDO> userWhiteDOS = new ArrayList<>(sheet.getLastRowNum());
                for (Row row : sheet) {
                    UserWhiteDO userWhiteDO = new UserWhiteDO();
                    //遍历所有的列
                    if (row.getRowNum() != 0) {
                        Cell userName = row.getCell(0);
                        Cell gender = row.getCell(1);
                        Cell mobile = row.getCell(2);
                        Cell idNo = row.getCell(3);
                        this.checkUserName(userName);
                        this.checkGender(gender);
                        this.checkMobile(mobile);
                        this.checkIdNo(idNo);
                        userWhiteDO.setId(super.generateId());
                        userWhiteDO.setIdNo(idNo.getStringCellValue());
                        userWhiteDO.setMobile(mobile.getStringCellValue());
                        userWhiteDO.setStatus("0");
                        userWhiteDO.setGender(GenderEnum.getGender(gender.getStringCellValue()));
                        userWhiteDO.setUserName(userName.getStringCellValue());
                        umsAdminService.injectUserValue(userWhiteDO);
                        userWhiteDOS.add(userWhiteDO);
                    }
                }
                return userWhiteDOS;
            }
        } catch (ApiException apiException) {
            throw apiException;
        } catch (Exception e) {
            log.error("读取文件客户号异常", e);
            throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
        } finally {
            try {
                if (wk != null) {
                    wk.close();
                }
            } catch (IOException e) {
                log.error("文件流关闭异常", e);
                throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
            }
        }
        return Collections.EMPTY_LIST;
    }

    private void checkMobile(Cell mobile) {
        String mobileReg = "^1[3-9]\\d{9}$";
        Pattern pattern = Pattern.compile(mobileReg);
        if (mobile == null || !pattern.matcher(mobile.getStringCellValue()).matches())
            throw new ApiException(ResultCode.MOBILE_ERROR);
    }

    private void checkIdNo(Cell idNo) {
// 校验15位和18位身份证的正则表达式
        String idNoReg =
                "^([1-9]\\d{5}(18|19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{3}(\\d|X|x)$)|" +
                        "^([1-9]\\d{5}\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{2}(\\d|X|x)?$)";
        Pattern pattern = Pattern.compile(idNoReg);
        if (idNo == null || !pattern.matcher(idNo.getStringCellValue()).matches())
            throw new ApiException(ResultCode.ID_NO_ERROR);
    }

    private void checkGender(Cell gender) {
        if (gender == null || GenderEnum.getGender(gender.getStringCellValue()) == null)
            throw new ApiException(ResultCode.GENDER_ERROR);
    }

    private void checkUserName(Cell userName) {
        if (userName == null || userName.getStringCellValue().length() > 10)
            throw new ApiException(ResultCode.USER_NAME_ERROR);
    }

    private List<UserWhiteDO> queryUserWhiteDO(UserWhiteQueryReqDTO userWhiteQueryReqDTO) {
        UserWhiteDOExample userWhiteDOExample = new UserWhiteDOExample();
        userWhiteDOExample.setOrderByClause("create_time desc");
        UserWhiteDOExample.Criteria criteria = userWhiteDOExample.createCriteria();
        if (userWhiteQueryReqDTO.getStartTime() != null && userWhiteQueryReqDTO.getEndTime() != null) {
            criteria.andCreateTimeGreaterThan(TimeUtil.dateZeroChange(userWhiteQueryReqDTO.getStartTime()));
            criteria.andCreateTimeLessThan(TimeUtil.dateEndChange(userWhiteQueryReqDTO.getEndTime()));
        }
        if (StringUtils.isNotBlank(userWhiteQueryReqDTO.getStatus())) {
            criteria.andStatusEqualTo(userWhiteQueryReqDTO.getStatus());
        }
        if (StringUtils.isNotBlank(userWhiteQueryReqDTO.getUserName())) {
            criteria.andUserNameLike("%" + userWhiteQueryReqDTO.getUserName() + "%");
        }
        if (userWhiteQueryReqDTO.getId() != null) {
            criteria.andIdEqualTo(userWhiteQueryReqDTO.getId());
        }
        return userWhiteDOMapper.selectByExample(userWhiteDOExample);
    }

}