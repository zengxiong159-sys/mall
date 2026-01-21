package com.qdbank.mall.web;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.extra.ftp.Ftp;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.financial.FinancialService;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.orderreport.TradeMerchantInfoDO;
import com.qdbank.mall.model.usercoupon.UserCouponDO;
import com.qdbank.mall.request.financial.FinancialReqDTO;
import com.qdbank.mall.request.merchant.MerchantReqDTO;
import com.qdbank.mall.response.financial.IntegrationCouponResDTO;
import com.qdbank.mall.util.ImgUtils;
import com.qdbank.mall.util.JsonUtil;
import com.qdbank.mall.util.RSAUtil;
import com.qdbank.mall.util.TimeUtil;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.SocketUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @ClassName StringTest
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2020/12/16 15:04
 * @Version 1.0
 **/
public class StringTest {
    @Test
    public void testLength() {
        String detailAddress = "aaaa888";
        String s = "";
        for (int i = 0; i < detailAddress.length(); i++) {
            s += "*";

            System.out.println(s);
        }
    }

    @Test
    public void testPattern() {
        PathMatcher matcher = new AntPathMatcher();
//        String requestPath = "/app/pub/login.do";
//        String patternPath = "/**/lo?in.do";
//        String requestPath="/app/pub/login.do";
//        String patternPath="/**/*.do";
//        String requestPath = "/app/pub/login.do";
//        String patternPath = "/**/lo?in.do";
        String requestPath = "/menu/create";
        String patternPath = "/menu/**";
        System.out.println(matcher.match(patternPath, requestPath));
    }

    @Test
    public void testExcle() {
//        String path = "D:/文档/需求文档/商城需求文档/迁移需求附件/优惠券白名单导入表.模板.xlsx";
//        String fileEndName = path.substring(path.lastIndexOf("."), path.length());
//        System.out.println(fileEndName);
//        System.out.println(fileEndName.endsWith(".xlsx"));
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            String ss = "11a";
            System.out.println(NumberUtil.isNumber(ss));
        }
        System.out.println(System.currentTimeMillis() - start);
        int a = 101 / 2;
        System.out.println(a);


//        ExcelReader reader = ExcelUtil.getReader(path);
//        System.out.println(reader.getRowCount());
//        List<List<Object>> readList = reader.read(1, reader.getRowCount());
//        System.out.println(JSON.toJSONString(readList));
//        long count = readList.stream().distinct().count();
//        System.out.println(count);
//        System.out.println(readList.size());
    }

    @Test
    public void testBatch() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100002; i++) {
            list.add(i);
        }
        int batchCount = 10000;
        int batchLastIndex = batchCount;
        List<List<Integer>> shareList = new ArrayList<>();
        for (int index = 0; index < list.size(); ) {
            if (batchLastIndex >= list.size()) {
                batchLastIndex = list.size();
                shareList.add(list.subList(index, batchLastIndex));
                break;
            } else {
                shareList.add(list.subList(index, batchLastIndex));
                // 设置下一批下标
                index = batchLastIndex;
                batchLastIndex = index + (batchCount - 1);
            }
        }
        System.out.println(shareList.size());
    }

    @Test
    public void testTime() {
        String beginDate = "1616601600000";
        SimpleDateFormat sdf = new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
        String sd = sdf.format(new Date(Long.parseLong(beginDate))); // 时间戳转换日期
        System.out.println(sd);
        List<Long> longs = null;
        System.out.println(longs.isEmpty());
    }

    @Test
    public void testDay() {
        String str = new SimpleDateFormat(DatePattern.NORM_DATE_PATTERN).format(DateUtil.offsetDay(new Date(), -1));
        System.out.println(str);
    }

    @Test
    public void setTime() {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        // 将时分秒,毫秒域清零
//        calendar.set(Calendar.HOUR_OF_DAY, 23);
//        calendar.set(Calendar.MINUTE, 00);
//        calendar.set(Calendar.SECOND, 00);
//        System.out.println(calendar.getTime());
        FinancialReqDTO financialReqDTO = new FinancialReqDTO();
        financialReqDTO.setStartDate(new Date());
//        Date date = FinancialService.changeDate(financialReqDTO);
//        System.out.println(date);
    }

    @Test
    public void testBigDecimal() {
        String mobile = "15821512401";
        System.out.println(mobile.substring(mobile.length() - 4, mobile.length()));
    }

    @Test
    public void testListAdd() {
        List<String> list = new ArrayList();
        List<String> list1 = new ArrayList();
        list1.add("1");
        List<String> list2 = new ArrayList();
        list2.add("2");
        list.addAll(list1);
        list.addAll(list2);
        list.stream().forEach(s -> System.out.println(s));
        System.out.println(list.size());
    }

    @Test
    public void testSTR() {
        String str = "[{\"颜色\":\"黑\"},{\"重量\":\"1kg\"}]";
        List<JSONObject> list = JsonUtil.json2List(str, JSONObject.class);
        for (JSONObject jsonObject : list) {
            for (Object obj : jsonObject.values()) {
                System.out.println(obj.toString());
            }
        }
    }

    @Test
    public void testIndexOf() {
        String filename = "test.html";
        String originalFilename = filename.
                substring(filename.
                        lastIndexOf(".") + 1);

        if(true) {
            //判断图片文件类型是否合法;判断是否为图片文件
            if(!ImgUtils.checkImgFileType(originalFilename) ) {
                throw new ApiException(ResultCode.ILLEGAL_IMG_FILE_TYPE);
            }

        }

    }

    @Test
    public void listTest() {
        //交集 intersection
        //subtractToList 差集
        //subtract 差集 新增的
        //

        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        list1.add("5");
        list1.add("6");
        List<String> list2 = new ArrayList<>();
        list2.add("1");
        list2.add("2");
        list2.add("5");
        list2.add("7");
        list2.add("9");
        list2.add("10");
        List list3 = CollUtil.subtractToList(list1, list2);
        List list4 = CollUtil.subtractToList(list2, list1);
        System.out.println(list3);
        System.out.println(list4);
    }

    @Test
    public void testStr() {
//       BigDecimal bigDecimal = new BigDecimal(0.05);
//       BigDecimal bigDecimal1 = bigDecimal;
//        System.out.println(bigDecimal1+" "+ bigDecimal);

        IntegrationCouponResDTO integrationCouponResDTO = new IntegrationCouponResDTO();
        Date str = null;
        integrationCouponResDTO.setPaymentTime(DateUtil.format(str, DatePattern.NORM_DATE_PATTERN));
        System.out.println(JSON.toJSONString(integrationCouponResDTO));


        // System.out.println(new BigDecimal("3.00").compareTo(new BigDecimal("3")));
    }

    @Test
    public void testFile() {
        try {
            String ss = "            ";
            String filePath = "C:\\Users\\Qdccb\\Documents\\WeChat Files\\xiaoning369221\\FileStorage\\File\\2021-06\\MALLRECONCILEDATA";
            FileInputStream fin = new FileInputStream(filePath);
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "iso-8859-1"));
            String strTmp = "";
            StringBuffer stringBuffer = new StringBuffer();
            while ((strTmp = buffReader.readLine()) != null) {
                System.out.println(strTmp.getBytes("iso-8859-1").length);
                System.out.println(strTmp.length());
                stringBuffer.append(strTmp);
            }
            System.out.println(stringBuffer.toString().length());
            buffReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testList() {
        List<TradeMerchantInfoDO> list1 = new ArrayList<>();
        List<TradeMerchantInfoDO> list2 = new ArrayList<>();
        TradeMerchantInfoDO tradeMerchantInfoDO1 = new TradeMerchantInfoDO();
        tradeMerchantInfoDO1.setMerchantNo(1L);
        TradeMerchantInfoDO tradeMerchantInfoDO2 = new TradeMerchantInfoDO();
        tradeMerchantInfoDO2.setMerchantNo(2L);
        TradeMerchantInfoDO tradeMerchantInfoDO3 = new TradeMerchantInfoDO();
        tradeMerchantInfoDO3.setMerchantNo(3L);
        list1.add(tradeMerchantInfoDO1);
        list1.add(tradeMerchantInfoDO2);
        list1.add(tradeMerchantInfoDO3);

        TradeMerchantInfoDO tradeMerchantInfoDO4 = new TradeMerchantInfoDO();
        tradeMerchantInfoDO4.setMerchantNo(2L);
        TradeMerchantInfoDO tradeMerchantInfoDO5 = new TradeMerchantInfoDO();
        tradeMerchantInfoDO5.setMerchantNo(5L);
        list2.add(tradeMerchantInfoDO4);
        list2.add(tradeMerchantInfoDO5);
        List<TradeMerchantInfoDO> onlyRefundMerchantNos = list2.stream().filter(r -> !list1.stream().map(o -> o.getMerchantNo()).collect(Collectors.toList()).contains(r.getMerchantNo())).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(onlyRefundMerchantNos));

    }

    @Test
    public void checkCompany() {
        String companyName = "哈jjjjj哈jj你";
        Pattern pattern1 = Pattern.compile("^[a-z0-9A-Z\\u4e00-\\u9fa5]{1,10}$");
        // Pattern pattern2 = Pattern.compile("^[A-Za-z]{1,10}$");
        Matcher matcher1 = pattern1.matcher(companyName);
        // Matcher matcher2 = pattern2.matcher(companyName);
        System.out.println(matcher1.find());
        //if(!matcher1.find() && !matcher2.find()) throw  new ApiException(ResultCode.COMPANY_NAME_ERROR);
    }

    @Test
    public void checkCompanySn() {
        String companySn = "aa33aa44aaaaaaaaaa44aa55";
        // Pattern pattern1 = Pattern.compile("^{1,30}$");
        Pattern pattern2 = Pattern.compile("^[A-Za-z\\d]{1,30}$");
        //Matcher matcher1 = pattern1.matcher(companySn);
        Matcher matcher2 = pattern2.matcher(companySn);
        System.out.println(matcher2.find());
        //if() throw  new ApiException(ResultCode.COMPANY_SN_ERROR);
    }

    @Test
    public void testExcle1() {
        try {
            List<String> row1 = CollUtil.newArrayList("aa", "bb", "cc", "dd");
            List<String> row2 = CollUtil.newArrayList("aa1", "bb1", "cc1", "dd1");
            List<String> row3 = CollUtil.newArrayList("aa2", "bb2", "cc2", "dd2");
            List<String> row4 = CollUtil.newArrayList("aa3", "bb3", "cc3", "dd3");
            List<String> row5 = CollUtil.newArrayList("aa4", "bb4", "cc4", "dd4");
            List<List<String>> rows = CollUtil.newArrayList(row1, row2, row3, row4, row5);
            //通过工具类创建writer
            ExcelWriter writer = ExcelUtil.getWriter("d:/111.xlsx");
//通过构造方法创建writer
//ExcelWriter writer = new ExcelWriter("d:/writeTest.xls");

//跳过当前行，既第一行，非必须，在此演示用
            writer.passCurrentRow();

//合并单元格后的标题行，使用默认标题样式
            writer.merge(row1.size() - 1, "测试标题");
//一次性写出内容，强制输出标题
            writer.write(rows, true);
//关闭writer，释放内存
            writer.close();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            System.out.println("文件导出中，请稍后再试");
        }


    }

    @Test
    public void testFileExist() {
        Ftp ftp = new Ftp("");
        ftp.exist("");
        String fileName = FileUtil.getName("d:/订单查询明细导出表-2022-01-13-2022-01-13.xlsx");
        System.out.println(fileName);
        String file = fileName.substring(FileUtil.getName("d:/订单查询明细导出表-2022-01-13-2022-01-13.xlsx").
                lastIndexOf(".") + 1);
        System.out.println(file);
    }


    @Test
    public void getSpringVersion() {
        String version = SpringVersion.getVersion();
        String version1 = SpringBootVersion.getVersion();
        System.out.println(version);
        System.out.println(version1);
    }

    @Test
    public void deleteFile() {
//        String fileName = "d:/home/omsapp/订单查询明细导出表-2021-10-30-2021-11-29.xlsx";
//        System.out.println("删除结果："+FileUtil.del(fileName));
        OrderDO orignrderDO = new OrderDO();
        orignrderDO.setStatus(2L);
        System.out.println(orignrderDO.getStatus().equals(2L));
    }

    @Test
    public void jsonData() {
//        Date startDate = new Date();
//        Date endDate = DateUtil.offsetDay(startDate, 5);
//        endDate = TimeUtil.dateEndChange(endDate);
//        System.out.println(JSON.toJSONString(endDate));
        Map<String, String> map = new HashMap<>();
        map.put("type", "23");
        map.put("switch", "Y");
        try {
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(map));
            String days = (String) jsonObject.get("days");
            System.out.println(days);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testHashMap() {
        String fle = "aaabc.txt";
        String filename_are =fle.substring(fle.lastIndexOf("."));
        String filename_pre =fle.substring(0,fle.lastIndexOf(".")).concat(DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN)).concat(fle.substring(fle.lastIndexOf(".")));


        System.out.println(filename_are+"     "+filename_pre);
    }
    @Test
    public void TestRandom(){
      String str = RandomStringUtils.randomNumeric(3);
        System.out.println(str);
    }
    @Test
    public void testSecond(){
        Date currentDate = new Date();
        LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault()).plusDays(1).withHour(0).withMinute(0)
                .withSecond(0).withNano(0);
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault());
        long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
        System.out.println(seconds);
    }


}