package com.qdbank.mall.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.qdbank.mall.domain.ExportDataBO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;

/**
 * ExcelUtil
 *
 * @author shaoshihang
 * @date 2021/3/8 14:24
 * @since 1.0.0
 */
@Slf4j
public class ExcelExportUtil {


    /**
     * 导出excle
      * @param response
     * @param <T>
     */
    public static <T> void exportExcelByHuTools(HttpServletResponse response, ExportDataBO<T> exportDataBO ) {
        long startTime = System.currentTimeMillis();
        try {
            ExcelWriter writer = exportDataBO.getWriter();
            writer.renameSheet(exportDataBO.getSheetName());
            writer.writeHeadRow(exportDataBO.getHeards());
            writer.write(exportDataBO.getData(), true);
            writer.setOnlyAlias(true);
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            String fileName = URLEncoder.encode(exportDataBO.getFileName(), StandardCharsets.UTF_8.toString());
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + exportDataBO.getFileSuffix());
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control","no-cache");
            response.setHeader("filename",fileName+exportDataBO.getFileSuffix());
            //中文名称需要特殊处理
            writer.flush(response.getOutputStream());
            writer.close();
            long endTime = System.currentTimeMillis();
            log.info("写入记录耗时：{} 秒",(endTime - startTime) / 1000);
        } catch (Exception e) {
            log.error("######导出  excel异常  ：{}", e.getMessage());
        }

    }
}
