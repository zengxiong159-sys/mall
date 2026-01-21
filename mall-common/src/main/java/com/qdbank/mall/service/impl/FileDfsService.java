package com.qdbank.mall.service.impl;


import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.service.MinioService;
import com.qdbank.mall.util.ImgUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @ClassName FileDfsUtil
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/4/29 13:59
 * @Version 1.0
 **/
@Component
@Slf4j
public class FileDfsService {
    @Autowired
    private MinioService minioService;
    /**
     * 上传文件
     */
    public String upload(MultipartFile multipartFile, boolean isPicture) throws Exception{
        String originalFilename = multipartFile.getOriginalFilename().
                substring(multipartFile.getOriginalFilename().
                        lastIndexOf(".") + 1);

        if(isPicture) {
            //判断图片文件类型是否合法;判断是否为图片文件
            if(!ImgUtils.checkImgFileType(originalFilename) || !ImgUtils.isImage(multipartFile)) {
                throw new ApiException(ResultCode.ILLEGAL_IMG_FILE_TYPE);
            }
        }
        return  minioService.uploadFile(multipartFile);
    }
    /**
     * 删除文件
     */
    public void deleteFile(String groupName,String filePath) {
        if(StringUtils.isBlank(groupName) || StringUtils.isBlank(filePath) ) return;
        try {
            minioService.deleteFile(groupName);
            log.info("文件:{} 删除成功",filePath);
        } catch (Exception e) {
            log.error("文件:{}删除失败，异常:{}",filePath,e);
        }
    }
}
