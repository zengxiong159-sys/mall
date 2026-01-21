package com.qdbank.mall.orderimport.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.qdbank.mall.api.CommonResult;
import com.qdbank.mall.coupon.AsyncInsertCouponService;
import com.qdbank.mall.mapper.coupon.CouponDOMapper;
import com.qdbank.mall.mapper.order.OrderDOMapper;
import com.qdbank.mall.mapper.usercoupon.UserCouponDOMapper;
import com.qdbank.mall.model.coupon.CouponDO;
import com.qdbank.mall.model.coupon.CouponDOExample;
import com.qdbank.mall.model.order.OrderDO;
import com.qdbank.mall.model.order.OrderDOExample;
import com.qdbank.mall.model.usercoupon.UserCouponDO;
import com.qdbank.mall.model.usercoupon.UserCouponImportDO;
import com.qdbank.mall.order.OrderService;
import com.qdbank.mall.orderimport.OrderImportService;
import com.qdbank.mall.request.orderimport.OrderImportReqDTO;
import com.qdbank.mall.service.RedisService;
import com.qdbank.mall.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName UserCouponImportServiceImpl
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/5/30 21:51
 * @Version 1.0
 **/
@Service
@Slf4j
public class UserCouponImportServiceImpl extends BaseServiceImpl implements OrderImportService {
    @Autowired
    private AsyncInsertCouponService asyncInsertCouponService;
    @Autowired
    private CouponDOMapper couponDOMapper;
    @Override
    public CommonResult orderImport(OrderImportReqDTO orderImportReqDTO) {
        try {
//客户号	下发批次号 券名称	券面值	优惠券有效期开始时间	优惠券有效期结束时间	发放成功时间	当前状态	状态更新时间	关联订单号	商品编号	商品名称
            Long start = System.currentTimeMillis();
            ExcelReader reader = ExcelUtil.getReader(orderImportReqDTO.getFile().getInputStream());
            reader.addHeaderAlias("客户号","custNo");
            reader.addHeaderAlias("下发批次号","batchNo");
            reader.addHeaderAlias("券名称","couponName");
            reader.addHeaderAlias("券面值","couponAmount");
            reader.addHeaderAlias("发放成功时间","sendTime");
            reader.addHeaderAlias("优惠券有效期开始时间","startTime");
            reader.addHeaderAlias("优惠券有效期结束时间","endTime");
            reader.addHeaderAlias("当前状态","batchStatus");
            reader.addHeaderAlias("状态更新时间","updateTime");
            reader.addHeaderAlias("关联订单号","orderSn");
            reader.addHeaderAlias("商品编号","productId");
            reader.addHeaderAlias("商品名称","productName");
            List<UserCouponImportDO> userCouponImportDOS = reader.readAll(UserCouponImportDO.class);
            List<UserCouponDO> userCouponDOList = new ArrayList<>();
            List<OrderDO> orderDOS = new ArrayList<>();
            for(UserCouponImportDO o : userCouponImportDOS){
//                Object obj = redisService.get(BATCH_KEY+o.getBatchNo()+o.getCouponName());
//                //创建商品兑换券
//                CouponDO couponDO = new CouponDO();
//                if(obj == null){
//                    //查询指定商品券是否已创建
//                    CouponDOExample couponDOExample = new CouponDOExample();
//                    couponDOExample.createCriteria().andBatchNoEqualTo(o.getBatchNo()).andCouponNameEqualTo(o.getCouponName());
//                    List<CouponDO> list = couponDOMapper.selectByExample(couponDOExample);
//                    if(CollUtil.isNotEmpty(list)){
//                        couponDO = list.get(0);
//                        redisService.set(BATCH_KEY+o.getBatchNo()+o.getCouponName(),couponDO);
//                    }else{
//                        couponDO.setProductId(o.getProductId());
//                        couponDO.setProductName(o.getProductName());
//                        couponDO.setSendTime(o.getSendTime());
//                        couponDO.setAllTotal(0L);
//                        couponDO.setStartTime(o.getStartTime());
//                        couponDO.setEndTime(o.getEndTime());
//                        couponDO.setBatchNo(o.getBatchNo());
//                        couponDO.setBatchStatus(2L);
//                        couponDO.setCouponAmount(o.getCouponAmount());
//                        couponDO.setCouponType(1L);
//                        couponDO.setProductType(0L);
//                        couponDO.setCreatedBy("酷屏2");
//                        couponDO.setUpdatedBy("酷屏2");
//                        couponDO.setCreateTime(o.getEndTime());
//                        couponDO.setUpdateTime(o.getEndTime());
//                        couponDO.setCouponId(super.generateId());
//                        couponDO.setCouponName(o.getCouponName());
//                        couponDO.setCouponDescription("1.兑换路径：微信搜索青岛银行信用卡小程序，积分商城-个人中心-我的卡券，兑换券的有效期为发放之日起30天。" +
//                                "2.兑换券不折现、不找零，一经兑换非质量问题，不予退换，如售后问题可拨打商城客服电话400-669-6588。" +
//                                "3.下单前请核实订单信息，如因客户原因（配送地址更改等）无法妥投，运费及兑换券将不予返还。" +
//                                "4.对于活动期间，出现异常或违反银行相关规定，我行将有权取消该用户的参与资格或提前中止活动。" +
//                                "5.活动若有变更，以我行最新发布的规则为准。");
//                        couponDOMapper.insert(couponDO);
//                        redisService.set(BATCH_KEY+o.getBatchNo()+o.getCouponName(),couponDO);
//                    }
//                }else{
//                    couponDO = (CouponDO)redisService.get(BATCH_KEY+o.getBatchNo()+o.getCouponName());
//                }
                UserCouponDO u = new UserCouponDO();
                u.setCustNo(o.getCustNo());
                u.setCouponName(o.getCouponName());
                u.setCouponId(getCouponId(o.getBatchNo()));
                u.setUserCouponId(super.generateId());
                u.setOrderSn(o.getOrderSn());
                u.setBatchNo(o.getBatchNo());
                u.setStatus(getStatus(o.getBatchStatus()));
                u.setCouponType(1L);
                u.setCreateTime(o.getEndTime());
                u.setUpdateTime(o.getEndTime());
                u.setExpireDate(o.getEndTime());
                u.setCouponName(o.getCouponName());
                userCouponDOList.add(u);
                if(StringUtils.isNotBlank(o.getOrderSn())){
                    OrderDO orderDO = new OrderDO();
                    orderDO.setUserCouponId(u.getUserCouponId());
                    orderDO.setOrderSn(u.getOrderSn());
                    orderDO.setExchangeCouponId(getCouponId(o.getBatchNo()));
                    orderDOS.add(orderDO);
                }
            }

            //批量创建优惠券
            asyncInsertCouponService.batchInsert(userCouponDOList);
            //更新券发放记录条数
            Map<Long,Long > collect = userCouponDOList.stream().collect(Collectors.groupingBy(UserCouponDO::getCouponId, Collectors.counting()));
            Iterator<Long> iter = collect.keySet().iterator();
            while(iter.hasNext()){
                Long key = iter.next();
                Long value = collect.get(key);
                couponDOMapper.updateTotalCount(value,key);
            }
            //批量更新订单关联订单号
            asyncInsertCouponService.batchUpdate(orderDOS);
            log.info("解析商品指定免费兑换券数据条数:{}耗时：{}",CollUtil.isNotEmpty(userCouponImportDOS) ? userCouponImportDOS.size() : 0,(System.currentTimeMillis() - start)/1000);
        }catch (Exception e){
           log.error("解析用户持券存量数据表异常：{}",e);
           return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(null);
    }
    private Long getStatus(String status){
        if("已过期".equals(status))return  2L;
        if("已使用".equals(status)) return  1L;
        return 3L;
    }

    private Long getCouponId(String batchNo){
        if("20210113141239956678".equals(batchNo)) return 151665165285195776L;
        if("20201229140044034030".equals(batchNo)) return 151665166631567360L;
        if("20210114153348051617".equals(batchNo)) return 151665167352987648L;
        if("20201203222412929126".equals(batchNo)) return 151665169089429504L;
        if("20201229224342991363".equals(batchNo)) return 151665175821287424L;
        if("20201215114401890813".equals(batchNo)) return 151665176509153280L;
        if("20201130185051706498".equals(batchNo)) return 151665177700335616L;
        if("20201230093914812466".equals(batchNo)) return 151665225930637312L;
        if("20210118141730620024".equals(batchNo)) return 151665226727555072L;
        if("20210115182605212579".equals(batchNo)) return 151665227935514624L;
        if("20210128191329947240".equals(batchNo)) return 151665229139279872L;
        if("20201216142120322369".equals(batchNo)) return 151665229839728640L;
        if("20201113174502416234".equals(batchNo)) return 151665231035105280L;
        if("20201103150255872684".equals(batchNo)) return 151665351701037056L;
        if("20201113174839735745".equals(batchNo)) return 151675175821287425L;
        if("20201119091014087209".equals(batchNo))return 151675185821287426L;
        if("20201122231201768739".equals(batchNo))return 151675195821287427L;
        if("20201130190824884440".equals(batchNo))return 151675205821287428L;
        if("20201203215249200547".equals(batchNo))return 151675215821287429L;
        if("20201211141320605141".equals(batchNo)) return 151675225821287430L;
        if("20201106135845548175".equals(batchNo)) return 151675235821287431L;
        if("20201125190151804091".equals(batchNo)) return 151675245821287432L;
        if("20201126182818058416".equals(batchNo)) return 151675255821287433L;
        if("20201211141210436857".equals(batchNo)) return 151675265821287434L;
        if("20201217173217496229".equals(batchNo)) return 151675275821287435L;
        if("20201119091136982576".equals(batchNo)) return 151675285821287436L;
        if("20201026144918909840".equals(batchNo)) return  151675305821287438L;
        if("20201111085955334247".equals(batchNo)) return  151675315821287439L;
        if("20201113174211446825".equals(batchNo)) return  1516753258212874340L;
        if("20201122230718107592".equals(batchNo)) return  1516753358212874341L;
        if("20201125190315970248".equals(batchNo)) return  1516753458212874342L;
        if("20201126182331299715".equals(batchNo)) return  1516753558212874343L;
        if("20201203221019219045".equals(batchNo)) return  1516753658212874344L;
        if("20201203221149909986".equals(batchNo)) return  1516753758212874345L;
        if("20201203221539937830".equals(batchNo)) return  1516753858212874346L;
        if("20201203222527347804".equals(batchNo)) return  1516753958212874347L;
        if("20201204194742144992".equals(batchNo)) return  1516754058212874348L;
        if("20201207162412389950".equals(batchNo)) return  1516754158212874349L;
        if("20201211140939504087".equals(batchNo)) return  1516754258212874350L;
        if("20201215101506868039".equals(batchNo)) return  1516754358212874351L;
        if("20210108101531282067".equals(batchNo)) return  1516754368212874352L;
        if("20210111162539875159".equals(batchNo)) return  1516754468212874353L;
        if("20210204192721045698".equals(batchNo)) return  1516755468212874354L;
        if("20201215192830938052".equals(batchNo)) return 1516755478212874355L;
        if("20201228132507139401".equals(batchNo)) return 1516755488212874356L;
        if("20210526111330921073".equals(batchNo)) return 151665477700335617L;
        if("20210601134353013187".equals(batchNo)) return 151665577700335618L;
        if("20210607163955524833".equals(batchNo)) return 151665677700335619L;
        if("20210706182620850191".equals(batchNo)) return 151665777700335620L;
        return 0L;
    }
}
