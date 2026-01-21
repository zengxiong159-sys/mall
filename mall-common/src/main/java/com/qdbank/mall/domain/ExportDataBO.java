package com.qdbank.mall.domain;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ExportDataBO
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/13 11:11
 * @Version 1.0
 **/
@Data
public class ExportDataBO<T> {
    private ExcelWriter writer;
    private String fileName;//文件名称
    private String sheetName;//工作簿名称
    private  List<List<String>> data;//数据
    private String fileSuffix;//文件后缀
    public ExportDataBO(String fileName,String sheetName,String fileSuffix,List<String> heards){
        this.fileName = fileName;
        this.sheetName = sheetName;
        this.fileSuffix = fileSuffix;
        this.heards = heards;
        writer =  ExcelUtil.getBigWriter();
    }
    public  ExportDataBO(){
        writer =  ExcelUtil.getBigWriter();
    }
    private List<String> heards;//标题头

}
