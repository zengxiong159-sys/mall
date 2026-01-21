package com.qdbank.mall.service.impl;

import com.qdbank.mall.service.MinioService;
import org.springframework.beans.factory.annotation.Value;
import com.github.pagehelper.PageInfo;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName BaseServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/1 13:45
 * @Version 1.0
 **/
@Slf4j
@RefreshScope
public abstract class BaseServiceImpl  {

    @Autowired
    private SnowFlakeService snowFlakeService;
    @Value(value = "${minio.domainName:}")
    private String minioUrl;
    @Autowired
    private FileDfsService fileDfsService;

    /**
     * 生成数据表主键ID
     * @return
     */
    public Long generateId() {
        return snowFlakeService.getId();
    }

    /**
     * 生成唯一订单号
     * @return 文件路径
     */
    public String generateOrderSN(){
        return snowFlakeService.generateOrderSN();
    }

    public String[] uploadFile(MultipartFile multipartFile, boolean isPicture){
        String filePath = "";
        try {

            filePath = fileDfsService.upload(multipartFile, isPicture);
        }catch (ApiException e){
            log.error("上传文件异常：{}", e);
            throw new ApiException(e.getErrorCode().getMessage());
        } catch (Exception e){
            log.error("上传文件异常：{}",e);
            throw new ApiException(ResultCode.UPLOAD_FILE_EXCEPTION);
        }
        String [] uploadReturn = new String[3];
        uploadReturn[0] = filePath;
        uploadReturn[1] = filePath;
        uploadReturn[2] = minioUrl + filePath;
        return uploadReturn;
    }

    /**
     * 文件删除
     * @param groupName
     * @param filePath
     * @return
     */
    public void deleteFile(String groupName,String filePath){
        fileDfsService.deleteFile( groupName, filePath);
    }

    public <T,R> PageInfo<R> getPageInfo(List<T> sourceList,List<R> responseList){
        PageInfo<R> pageInfo = new PageInfo(sourceList);
        pageInfo.setList(responseList);
        return pageInfo;
    }

    /**
     * 判断文件大小
     *
     * @param len
     *            文件长度
     * @param size
     *            限制大小
     * @param unit
     *            限制单位（B,K,M,G）
     * @return
     */
    public    void checkFileSize(Long len, int size, String unit) {
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
     * 校验文件类型
     * @param fileName
     * @return
     */
    public void checkFileType(String fileName){
        if(StringUtils.isBlank(fileName)) throw new ApiException(ResultCode.COUPON_FILE_TYPE_ERROR);
        String fileEndName = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        if(StringUtils.isNotBlank(fileEndName)){
            if(!fileEndName.endsWith(".xlsx")) throw new ApiException(ResultCode.COUPON_FILE_TYPE_ERROR);
        }else {
            throw new ApiException(ResultCode.COUPON_FILE_TYPE_ERROR);
        }
    }
}
