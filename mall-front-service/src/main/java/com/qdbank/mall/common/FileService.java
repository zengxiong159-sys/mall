package com.qdbank.mall.common;

import com.qdbank.mall.model.productpicurl.ProductPicUrlDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {


    String[] upload(MultipartFile multipartFile);

    int addFile(Long productId, String[] urls, String author);

    int addFile(Long productId, String fileName, String url, String author);

    public int addFile(Long productId ,String[] urls,String author,boolean deleteFlag);

    List<ProductPicUrlDO> qryPicById(Long productId);

    /**
     * 上传文件
     * @param multipartFile 文件
     * @param isPicture 是否是图片 true:是 false:否
     * @return 文件信息
     */
    String[] upload(MultipartFile multipartFile, boolean isPicture);
}
