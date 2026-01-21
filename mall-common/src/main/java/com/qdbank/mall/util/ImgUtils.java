package com.qdbank.mall.util;

import com.qdbank.mall.enums.PictureTypeEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;

/**
 * @Author zengxiong
 * @Description 图片操作工具类
 * @Date 2021/7/15 11:45
 */
public class ImgUtils {
    /**
     * 检查图片类型是否合法
     *
     * @param imgFileType 图片类型
     * @return 是否为合法图片类型 true:合法 false:非法
     */
    public static boolean checkImgFileType(String imgFileType) {
        return PictureTypeEnum.getEnumByName(imgFileType.toUpperCase()) != null;
    }

    /**
     * 通过读取文件并获取其width及height的方式，来判断判断当前文件是否图片
     *
     * @param multipartFile 图片文件
     * @return 是否为图片类型 true:是 false:否
     */
    public static boolean isImage(MultipartFile multipartFile) {
        if (multipartFile == null) {
            return false;
        }
        Image img;
        try {
            img = ImageIO.read(multipartFile.getInputStream());
            return img != null && img.getWidth(null) > 0 && img.getHeight(null) > 0;
        } catch (Exception e) {
            return false;
        } finally {
            img = null;
        }
    }
}
