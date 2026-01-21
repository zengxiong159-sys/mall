package com.qdbank.mall.common.impl;


import com.qdbank.mall.common.FileService;
import com.qdbank.mall.dao.file.FileDao;
import com.qdbank.mall.model.productpicurl.ProductPicUrlDO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * 文件服务
 */
@Service
@Slf4j
public class FileServiceImpl extends BaseServiceImpl implements FileService{


    @Autowired
    FileDao fileDao;


    @Override
    public String[] upload(MultipartFile multipartFile){
        String [] filePath = super.uploadFile(multipartFile, true);
        return filePath;
    }


    @Override
    public int addFile(Long productId ,String[] urls,String author){
        if(urls!=null){
            for(String url : urls){
                addFile(productId, productId+"",  url,  author);
            }
        }
        log.info("图片上传[{}]",urls);
        return urls.length;
    }

    @Override
    public int addFile(Long productId, String fileName, String url, String author){
        ProductPicUrlDO productPicUrlDO = new ProductPicUrlDO();
        productPicUrlDO.setId(super.generateId());
        productPicUrlDO.setProductId(productId);
        productPicUrlDO.setFileName(fileName);
        productPicUrlDO.setPicUrl(url);
        Date now = new Date();
        productPicUrlDO.setCreateTime(now);
        productPicUrlDO.setUpdateTime(now);
        productPicUrlDO.setCreatedBy(author);
        productPicUrlDO.setUpdatedBy(author);
        log.info("文件上传信息[{}]", JsonUtil.toJSONString(productPicUrlDO));
        return fileDao.addFile(productPicUrlDO);
    }

    @Override
    public int addFile(Long productId ,String[] urls,String author,boolean deleteFlag){
        if(deleteFlag){
            fileDao.deleteFile(productId);
        }
        return this.addFile(productId,urls,author);
    }


    @Override
    public List<ProductPicUrlDO> qryPicById(Long productId){
        List<ProductPicUrlDO> list=fileDao.qryPicById(productId);
        return list;
    }

    @Override
    public String[] upload(MultipartFile multipartFile, boolean isPicture) {
        String [] filePath = super.uploadFile(multipartFile, isPicture);
        return filePath;
    }


}
