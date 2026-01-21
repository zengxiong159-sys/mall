package com.qdbank.mall.web.user;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;

import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.constant.Constant;
import com.qdbank.mall.controller.user.UmsAdminController;
import com.qdbank.mall.coupon.CouponMqSendService;
import com.qdbank.mall.domain.CouponMQBO;
import com.qdbank.mall.feign.GatewayFeignService;
import com.qdbank.mall.feign.MallFeignService;
import com.qdbank.mall.mapper.download.DownloadDOMapper;
import com.qdbank.mall.mapper.order.OrderDOMapper;
import com.qdbank.mall.mapper.product.ProductDOMapper;
import com.qdbank.mall.mapper.productpicurl.ProductPicUrlDOMapper;
import com.qdbank.mall.mapper.skustock.SkustockDOMapper;
import com.qdbank.mall.model.download.DownloadDO;
import com.qdbank.mall.model.download.DownloadDOExample;
import com.qdbank.mall.model.product.ProductDO;
import com.qdbank.mall.model.product.ProductInfoQueryDO;
import com.qdbank.mall.model.productpicurl.ProductPicUrlDO;
import com.qdbank.mall.model.skustock.SkustockDO;
import com.qdbank.mall.model.user.UserInfoDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.paramsconfig.ParamsConfigService;
import com.qdbank.mall.product.ProductService;
import com.qdbank.mall.request.CommonStringIDReqDTO;
import com.qdbank.mall.request.gatawayrequest.GatawayReqDTO;
import com.qdbank.mall.request.gatawayrequest.GwHeader;
import com.qdbank.mall.request.integral.IntegralBalanceReqDTO;
import com.qdbank.mall.request.order.OrderExportReqDTO;
import com.qdbank.mall.request.product.ProductLikeQueryReqDTO;
import com.qdbank.mall.request.send.SendReturnReqDTO;
import com.qdbank.mall.request.user.UpdateUserInfoReqDTO;
import com.qdbank.mall.request.user.UserInfoLikeQueryReqDTO;
import com.qdbank.mall.request.user.UserInfoReqDTO;
import com.qdbank.mall.response.order.orderdetail.OrderBaseDetailResDTO;
import com.qdbank.mall.response.paramsconfig.ParamsConfigResDTO;
import com.qdbank.mall.response.send.SendReturnResDTO;
import com.qdbank.mall.response.user.UserInfoResDTO;
import com.qdbank.mall.send.SendReturnService;
import com.qdbank.mall.service.MinioService;
import com.qdbank.mall.service.impl.FileDfsService;
import com.qdbank.mall.task.ITaskService;
import com.qdbank.mall.user.RedisKeyDeleteService;
import com.qdbank.mall.user.UmsAdminCacheService;
import com.qdbank.mall.user.UmsAdminService;
import com.qdbank.mall.util.TimeUtil;
import com.qdbank.mall.web.BaseSpringTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserTest
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/1 13:58
 * @Version 1.0
 **/
@Slf4j
public class UserTest extends BaseSpringTest {

    @Qualifier("umsAdminServiceImpl")
    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private UmsAdminController umsAdminController;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OrderService orderService;
    @Autowired
    private DownloadDOMapper downloadDOMapper;
    @Autowired
    private FileDfsService fileDfsService;
    @Autowired
    private RedisKeyDeleteService redisKeyDeleteService;
    @Value("${teststr}")
    private String testStr;

    @Value(value = "${http.mciurl}")
    private String mciUrl ;
    @Autowired
    private GatewayFeignService gatewayFeignService;
    @Autowired
    private MallFeignService mallFeignService;
    @Autowired
    private OrderDOMapper doMapper;
    @Autowired
    private SendReturnService sendReturnService;
    @Autowired
    private ParamsConfigService paramsConfigService;
    @Autowired
    private CouponMqSendService couponMqSendService;
    @Autowired
    private OrderDOMapper orderDOMapper;

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDOMapper productDOMapper;
    @Autowired
    private SkustockDOMapper skustockDOMapper;
    @Autowired
    private ProductPicUrlDOMapper productPicUrlDOMapper;
    @Autowired
    @Qualifier("tradeTotalTaskImpl")
    private ITaskService taskService;
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private MinioService minioService;
    @Autowired
    private UmsAdminCacheService adminCacheService;
    @Test
    public void testCreateUser(){
        UserInfoReqDTO userInfoReqDTO = new UserInfoReqDTO();
        userInfoReqDTO.setDeptno(111L);
        userInfoReqDTO.setEmail("591112966@qq.com");
        userInfoReqDTO.setMobile("15821512401");
        userInfoReqDTO.setNickName("张三");
        userInfoReqDTO.setUsername("591112966@qq.com");
        CommonResult<UserInfoDO> commonResult = umsAdminController.register(userInfoReqDTO);
        System.out.println(JSON.toJSONString(commonResult));
    }
    @Test
    public void getUser(){
        UserInfoDO userInfoDO = umsAdminService.getAdminByUsername("591112962@qq.com");
        System.out.println(JSON.toJSONString(userInfoDO));
    }
    @Test
    public void getUserById(){
        UserInfoResDTO userInfoDO = umsAdminService.getItem(3L);
        System.out.println(JSON.toJSONString(userInfoDO));
    }
    @Test
    public void login(){
            String token = umsAdminService.login("admin","macro123");
            System.out.println(token);

    }
    @Test
    public void userList(){
        UserInfoLikeQueryReqDTO userInfoLikeQueryReqDTO = new UserInfoLikeQueryReqDTO();
        userInfoLikeQueryReqDTO.setPageNum(1);
        userInfoLikeQueryReqDTO.setPageSize(10);
//        List<UserInfoResDTO> userInfoDOList = umsAdminService.list(userInfoLikeQueryReqDTO);
//        System.out.println(userInfoDOList);
    }
    @Test
    public void updateStatus(){
        UpdateUserInfoReqDTO updateUserInfoReqDTO = new UpdateUserInfoReqDTO();
        updateUserInfoReqDTO.setStatus(1L);
        umsAdminService.update(3L,updateUserInfoReqDTO);
    }
    @Test
    public void testPattern(){
        PathMatcher matcher = new AntPathMatcher();
        String requestPath = "/app/pub/login.do";
        String patternPath = "/**/lo?in.do";
        System.out.println(matcher.match(requestPath,patternPath));
    }
    @Test
    public void testDownLoad(){
    }
    @Test
    public void testProdInfo(){
        ProductInfoQueryDO productInfoQueryDO = new ProductInfoQueryDO();
        productInfoQueryDO.setMulStatus("1,2");
//        List<ProductInfoDO>  list = productDOMapper.selectProductInfo(productInfoQueryDO);
//        System.out.println(list);
    }
    @Test
    public void testPassword(){
       boolean flag =  passwordEncoder.matches("fanf1111","$2a$10$lgeqCLHOi14S5l/9OeDNl.nWDoWnRfTH8caymB/3R7AxzNzy7V4We");
        System.out.println(flag);
    }
    @Test
    public void fastdfs(){
        try {
            File file = new File("d:/111.xlsx");
            System.out.println(file.getName());
            String ss = minioService.uploadFileByInputstream(file,"111.xlsx");
            System.out.println(ss);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Test
    public void fastdeleteflie(){
        try {
            File file = new File("d:/111.xlsx");
            System.out.println(file.getName());
            minioService.deleteFile("11120231010101034.xlsx");
            System.out.println("删除成功");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Test
    public void testDownload(){
        OrderExportReqDTO orderExportReqDTO = new OrderExportReqDTO();
        orderExportReqDTO.setStartTime(DateUtil.parse("2022-01-13", DatePattern.NORM_DATE_PATTERN));
        orderExportReqDTO.setEndTime(DateUtil.parse("2022-01-13", DatePattern.NORM_DATE_PATTERN));
        orderExportReqDTO.setCreatedBy("张三");
        orderExportReqDTO.setFileLocalPath("d:/");
        orderService.excelExport(orderExportReqDTO);
    }
    @Test
    public void deleteFile(){
        DownloadDOExample downloadDOExample = new DownloadDOExample();
        downloadDOExample.createCriteria().andCreateTimeLessThanOrEqualTo(TimeUtil.dateZeroChange(DateUtil.offsetDay(ITaskService.getDate(null),- 7))).andStatusEqualTo(1L);
        List<DownloadDO> downloadDOS = downloadDOMapper.selectByExample(downloadDOExample);
        if(CollUtil.isNotEmpty(downloadDOS)){
            downloadDOS.stream().forEach(downloadDO -> {
                fileDfsService.deleteFile(downloadDO.getFileGroup(),downloadDO.getFileUrl());
                downloadDO.setUpdateTime(new Date());
                downloadDO.setStatus(2L);
                downloadDOMapper.updateByPrimaryKeySelective(downloadDO);
            });
        }
    }
    @Test
    public void testNacos(){
        System.out.println("注册中心："+mciUrl);
    }

    @Test
    public void testGateway(){
        GatawayReqDTO gatawayReqDTO = new GatawayReqDTO();
        GwHeader gwHeader = new GwHeader();
        gwHeader.setGwVer("1");
        gwHeader.setSvcId("17070");
        gwHeader.setTellerId("*SPGUSER");
        gatawayReqDTO.setGwSysHeader(gwHeader);
        IntegralBalanceReqDTO integralBalanceReqDTO = new IntegralBalanceReqDTO();
        integralBalanceReqDTO.setCustId("1111");
        gatawayReqDTO.setReqBody(integralBalanceReqDTO);
        try {
            Object object = gatewayFeignService.getIntegralByCustId(gatawayReqDTO);
            log.info("请求参数:{}",JSON.toJSONString(gatawayReqDTO));
            log.info("调用结果："+JSON.toJSONString(object));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void testRefund(){

        CommonStringIDReqDTO commonStringIDReqDTO = new CommonStringIDReqDTO();
        commonStringIDReqDTO.setId("247422782489559040");
        CommonResult commonResult = mallFeignService.errorRefund(commonStringIDReqDTO);
        System.out.println("结果"+JSON.toJSONString(commonResult));
    }


    @Test
    public void testSendReturnList(){

        List<SendReturnResDTO> sendReturnResDTOList = sendReturnService.querySendReturn("QD202202221457350015");
        System.out.println(JSON.toJSONString(sendReturnResDTOList));
    }

    @Test
    public void testParam(){
        SendReturnReqDTO sendReturnReqDTO = new SendReturnReqDTO();
        sendReturnReqDTO.setOrderId(252584067829088256L);
        sendReturnReqDTO.setReturnReason("测试");
        orderService.sendReturnOrder(sendReturnReqDTO);
    }

    private boolean getSendReturnFlag(Date deliveryTime){
        if(deliveryTime == null) return false;
        ParamsConfigResDTO paramsConfigResDTO = paramsConfigService.queryByParamName("sendReturnTime");
        //发货7天后时间是否大于当前时间
        return DateUtil.offsetDay(deliveryTime,Integer.valueOf(paramsConfigResDTO.getParamValue())).isAfter(new Date());
    }
    @Test
    public void testProduct(){
        ProductLikeQueryReqDTO productLikeQueryReqDTO = new ProductLikeQueryReqDTO();
        productLikeQueryReqDTO.setPageSize(10);
        productLikeQueryReqDTO.setPageNum(1);
        productLikeQueryReqDTO.setMulStatus("1,2");
        Long start = System.currentTimeMillis();
        productService.listNew(productLikeQueryReqDTO);
        System.out.println("耗时"+(System.currentTimeMillis() - start)/1000);
    }

    @Test
    public void testProduct1(){
        ProductLikeQueryReqDTO productLikeQueryReqDTO = new ProductLikeQueryReqDTO();
        productLikeQueryReqDTO.setPageSize(10);
        productLikeQueryReqDTO.setPageNum(1);
        productLikeQueryReqDTO.setMulStatus("1,2");
        Long start = System.currentTimeMillis();
        productService.list(productLikeQueryReqDTO);
        System.out.println("耗时"+(System.currentTimeMillis() - start)/1000);
    }
    @Test
    public  void testProduct2(){
        ProductDO productDO = productDOMapper.selectByPrimaryKey(257265615589367808L);
        List<SkustockDO> skustockDOList = skustockDOMapper.selectAllByProductId(257265615589367808L);
        ProductPicUrlDO productPicUrlDO = productPicUrlDOMapper.selectByPrimaryKey(257265553555611648L);
        for(int i = 0 ; i< 5000; i++){
            ProductDO productDO1 = new ProductDO();
            BeanUtils.copyProperties(productDO,productDO1);
            productDO1.setProductId(Long.valueOf(30220612+i));
            productDOMapper.insert(productDO1);
            SkustockDO skustockDO = new SkustockDO();
            BeanUtils.copyProperties(skustockDOList.get(0),skustockDO);
            skustockDO.setProductId(productDO1.getProductId());
            skustockDO.setProductSkuId(Long.valueOf(30220612+i));
            skustockDOMapper.insert(skustockDO);
            ProductPicUrlDO productPicUrlDO1 = new ProductPicUrlDO();
            BeanUtils.copyProperties(productPicUrlDO1,productPicUrlDO);
            productPicUrlDO1.setProductId(productDO1.getProductId());
            productPicUrlDO1.setId(Long.valueOf(30220612+i));
            productPicUrlDOMapper.insert(productPicUrlDO1);
        }

    }
    @Test
    public void testOrder(){
        OrderBaseDetailResDTO orderBaseDetailResDTO = orderService.orderDetail(250442587974991872L);
        System.out.println("订单:"+JSON.toJSONString(orderBaseDetailResDTO));
    }
    @Test
    public void testMQ(){
        CouponMQBO couponMQBO = new CouponMQBO();
        couponMQBO.setCouponId("1234");
        couponMQBO.setAvailableBeginTime(DateUtil.format(new Date(),DatePattern.NORM_DATETIME_PATTERN));
        couponMQBO.setAvailableEndTime(DateUtil.format(new Date(),DatePattern.NORM_DATETIME_PATTERN));
        couponMQBO.setCouponAmount(new BigDecimal("3.00"));
        couponMQBO.setCouponNotice("");
        couponMQBO.setCreateTime(DateUtil.format(new Date(),DatePattern.NORM_DATETIME_PATTERN));
        couponMQBO.setCustNo("2222");
        couponMQBO.setDescription("测试优惠券描述");
        couponMQBO.setMallCouponType("0");
        couponMQBO.setStatus("20");
        couponMQBO.setOperateType("insert");
        couponMqSendService.couponMqSend(couponMQBO);
    }

    @Test
    public void testMQDelete(){
        CouponMQBO couponMQBO = new CouponMQBO();
        couponMQBO.setCouponId("111111");
        couponMQBO.setOperateType(Constant.OPERATE_TYPE_DELETE);
        couponMqSendService.couponMqSend(couponMQBO);
    }
    @Test
    public void testTrade(){
//        taskService.run(null);
        CouponMQBO couponMQBO = new CouponMQBO();
        couponMQBO.setCouponId("1234");
        couponMQBO.setOperateType(Constant.OPERATE_TYPE_DELETE);
        try {
            kafkaTemplate.send("TP_SPG_COUPON",JSON.toJSONString(couponMQBO));
            System.out.println("发送成功");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void testCache(){

        adminCacheService.deleteKey(Constant.USER_LOGIN_COUNT+"username");
    }
//    @Test
//    public void testDubbo(){
//        Map params = new HashMap();
//        params.put("idcode","340828199108084023");
//        String custId = "";
//        try{
//            Map dataMap= dubboService.call("AC202002",params);
//            List wxAccInfoList = (List) dataMap.get("wxAccInfoList");
//            if(!wxAccInfoList.isEmpty()) {
//                custId = ((Map<String, String>)wxAccInfoList.get(0)).get("custId");
//            }
//        }catch (Exception e){
//            log.error("获取客户号失败",e);
//        }
//        System.out.println(custId);
//    }



}
