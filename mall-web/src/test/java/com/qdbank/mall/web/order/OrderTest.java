package com.qdbank.mall.web.order;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.monitorjbl.xlsx.StreamingReader;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.api.ResultCode;
import com.qdbank.mall.exception.ApiException;
import com.qdbank.mall.feign.MallFeignService;
import com.qdbank.mall.freighttemplate.FreightTemplateService;
import com.qdbank.mall.mapper.bank.BankNodeAddressMapper;
import com.qdbank.mall.mapper.center.CenterDOMapper;
import com.qdbank.mall.mapper.freighttemplate.FreighttemplateDOMapper;
import com.qdbank.mall.mapper.order.OrderDOMapper;
import com.qdbank.mall.mapper.orderrefundreport.OrderRefundreportDOMapper;
import com.qdbank.mall.mapper.orderreport.OrderReportDOMapper;
import com.qdbank.mall.mapper.product.ProductDOMapper;
import com.qdbank.mall.model.bank.BankNodeAddress;
import com.qdbank.mall.model.center.CenterDO;
import com.qdbank.mall.model.order.OrderCouponDetailDO;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.orderrefundreport.OrderRefundreportDO;
import com.qdbank.mall.model.product.ProductDO;
import com.qdbank.mall.ods.OdsService;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.request.order.OrderIDReqDTO;
import com.qdbank.mall.request.order.OrderLikeQueryReqDTO;
import com.qdbank.mall.response.freighttemplate.FreightTemplateResDTO;
import com.qdbank.mall.response.order.OrderResDTO;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import com.qdbank.mall.service.impl.SnowFlakeService;
import com.qdbank.mall.web.BaseSpringTest;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName OrderTest
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/3/18 10:49
 * @Version 1.0
 **/
public class OrderTest extends BaseSpringTest {
    @Autowired
    private OrderDOMapper orderDOMapper;
    @Autowired
    private OrderRefundreportDOMapper orderRefundreportDOMapper;
    @Autowired
    private OdsService odsService;
    @Resource
    private FreightTemplateService freightTemplateService;
    @Autowired
    private MallFeignService mallFeignService;
    @Autowired
    private CenterDOMapper centerDOMapper;
    @Autowired
    private SnowFlakeService snowFlakeService;
    @Autowired
    private BankNodeAddressMapper bankNodeAddressMapper;
    @Autowired
    private ProductDOMapper productDOMapper;
    @Autowired
    private MinioClient minioClient;
    @Autowired
    private OrderService orderService;
    @Test
    public void insert(){
        try {

            String bucket = "testbucket";
            if(!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())){
                System.out.println("创建桶成功");
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            }else{
                System.out.println("创建失败");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

      //  ProductDO productDO = productDOMapper.selectByPrimaryKey(447523107014447104l);
        //System.out.println(productDO.getProductDetail());
//        OrderRefundreportDO orderDO = new OrderRefundreportDO();
//        orderDO.setRefundSerial(DateUtil.format(new Date(),"yyyyMMddHHmmss"));
//        orderDO.setOrderSn(20210318170712l);
//        orderDO.setCustName("张三");
//        orderDO.setFreightAmount(new BigDecimal(10));
//        orderDO.setMerchantName("张三");
//        orderDO.setMerchantNo(102968254556930048L);
//        orderDO.setOrderCash(new BigDecimal(99));
//        orderDO.setOrderIntegration(1L);
//        orderDO.setProductId(1L);
//        orderDO.setProductName("测试商品");
//        orderDO.setHandleFinishTime(new Date());
//        try {
//            orderRefundreportDOMapper.insert(orderDO);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
    @Test
    public void odsInsert(){
       // odsService.insertOdsOrder(new Date());
       // odsService.insertOdsRefundOrder(new Date());
       // odsService.insertOdsSmsCoupon(new Date());
        //odsService.insertProduct(new Date());
       // odsService.insertSkuStock(new Date());
//        //odsService.insertUserCoupon(new Date());
//        OrderCouponDetailDO orderCouponDetailDO = orderDOMapper.selectCouponOrderDetail(2L);
//        System.out.println(orderCouponDetailDO);
        OrderLikeQueryReqDTO orderLikeQueryReqDTO = new OrderLikeQueryReqDTO();
        orderLikeQueryReqDTO.setPageNum(1);
        orderLikeQueryReqDTO.setPageSize(10);
        PageInfo<OrderResDTO> pageInfo = orderService.list(orderLikeQueryReqDTO);
        System.out.println(JSON.toJSONString(pageInfo));
    }
    @Test
    public void testStatus(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        try {
            orderDOMapper.batchUpdateExpireOrder(list);
        }catch (Exception e){
           e.printStackTrace();
        }

    }
    @Test
    public void testOrder(){
        List<OrderDO> list = new ArrayList<>();
        OrderDO orderDO = new OrderDO();
        orderDO.setOrderSn("1");
        orderDO.setExchangeCouponId(1L);
        list.add(orderDO);
        OrderDO orderDO1 = new OrderDO();
        orderDO1.setOrderSn("1");
        orderDO1.setExchangeCouponId(1L);
        list.add(orderDO1);
        OrderDO orderDO2 = new OrderDO();
        orderDO2.setOrderSn("1");
        orderDO2.setExchangeCouponId(1L);
        list.add(orderDO2);
        try {
            orderDOMapper.batchUpdate(list);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void testFreight(){
        FreightTemplateResDTO freightTemplateResDTO = freightTemplateService.detail(140864086582923264L);
        System.out.println(JSON.toJSONString(freightTemplateResDTO));
    }
    @Test
    public void testFeign(){
        OrderIDReqDTO orderIDReqDTO = new OrderIDReqDTO();
        orderIDReqDTO.setOrderSn("111111");
        CommonResult commonResult = mallFeignService.qryOrder(orderIDReqDTO);
        System.out.println("远程调用结果:"+JSON.toJSONString(commonResult));
    }
    @Test
    public void testCenter(){
       List<CenterDO> centerDOList = centerDOMapper.selectByExample(null);
        System.out.println(centerDOList);
    }



        @Test
        public void testExcle(){

            Workbook wk = null;
            try {
                File file = new File("D:\\Users\\qd20000128\\Desktop\\网点信息-20230918_1.xlsx");
                wk = StreamingReader.builder()
                        //缓存到内存中的行数，默认是10
                        .rowCacheSize(100)
                        //读取资源时，缓存到内存的字节大小，默认是1024
                        .bufferSize(4096)
                        //打开资源，必须，可以是InputStream或者是File，注意：只能打开xlsx格式的文件
                        .open(file);

                //读取第一个sheet
                Sheet sheet = wk.getSheetAt(0);
                if(sheet != null) {
                    //遍历所有的行
                    for (Row row : sheet) {
                        //遍历所有的列
                        if(row.getRowNum() != 0 && row.getCell(0) != null) {
                            BankNodeAddress bankNodeAddress = new BankNodeAddress();
                            bankNodeAddress.setId(snowFlakeService.getId());
                            bankNodeAddress.setCityName(row.getCell(0).getStringCellValue());
                            bankNodeAddress.setBranchNo(row.getCell(2).getStringCellValue());
                            bankNodeAddress.setBranchName(row.getCell(3).getStringCellValue());
                            bankNodeAddress.setBranchAddress(row.getCell(4).getStringCellValue());
                            bankNodeAddress.setCreatedBy("SYSTEM");
                            bankNodeAddress.setCreateTime(new Date());
                            bankNodeAddress.setUpdatedBy("SYSTEM");
                            bankNodeAddress.setUpdateTime(new Date());
                            bankNodeAddressMapper.insert(bankNodeAddress);
                        }
                    }

                }
            } catch (Exception e) {

                throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
            } finally {
                try {
                    if(wk != null) {
                        wk.close();
                    }
                } catch (IOException e) {

                    throw new ApiException(ResultCode.SYSTEM_EXCEPTION);
                }
            }

        }
}
