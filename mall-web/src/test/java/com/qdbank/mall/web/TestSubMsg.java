package com.qdbank.mall.web;

import com.qdbank.mall.coupon.CouponExpireNoticeService;
import com.qdbank.mall.mapper.usercoupon.UserCouponDOMapper;
import com.qdbank.mall.model.usercoupon.UserCouponDetailDO;
import com.qdbank.mall.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.qdbank.mall.util.AesUtils.aesEncrypt;

/**
 * @Author zengxiong
 * @Description 测试订阅消息发送
 * @Date 2022/8/11 15:23
 */
@Slf4j
public class TestSubMsg extends BaseSpringTest {
    @Autowired
    private UserCouponDOMapper userCouponDOMapper;

    @Autowired
    private CouponExpireNoticeService couponExpireNoticeService;

    @Test
    public void sendCoupon() throws Exception {
        String s = "{\"couponId\":\"290912862092787712\",\"custNo\":\"2968\"}";
        System.out.println(aesEncrypt(s, "5de7e29919fad4d5"));
    }

    @Test
    public void sendExpireMq() {
        //行发、名单文件发放、待使用优惠券(sms_coupon.end_time->expire_date)
        LocalDate now = LocalDate.now();
        ZonedDateTime zonedStartDateTime = now.atStartOfDay(ZoneId.systemDefault());
        Date startDate = Date.from(zonedStartDateTime.toInstant());

        LocalDate endLocalDate = now.plusDays(5);
        ZonedDateTime zonedEndDateTime = endLocalDate.atStartOfDay(ZoneId.systemDefault());
        Date endDate = Date.from(zonedEndDateTime.toInstant());
        endDate = TimeUtil.dateEndChange(endDate);

        List<UserCouponDetailDO> fileExpiringCoupons = userCouponDOMapper.selectFileExpiringCoupon(endDate);
        //北斗发放、待使用优惠券(sms_user_coupon.expire_date)
        List<UserCouponDetailDO> beidouExpiringCoupons = userCouponDOMapper.selectBeidouExpiringCoupon(endDate);
        //用户自行兑换、待使用优惠券(sms_user_coupon.expire_date)
        List<UserCouponDetailDO> selfExchangeExpiringCoupons = userCouponDOMapper.selectSelfExchangeExpiringCoupon(endDate);
        log.info("fileExpiringCoupons size:{},beidouExpiringCoupons size:{}, selfExchangeExpiringCoupons size:{}",
                fileExpiringCoupons.size(), beidouExpiringCoupons.size(), selfExchangeExpiringCoupons.size());
        List<UserCouponDetailDO> userCouponDOList =
                Stream.of(fileExpiringCoupons, beidouExpiringCoupons, selfExchangeExpiringCoupons).flatMap(Collection::stream).collect(Collectors.toList());
        couponExpireNoticeService.batchAsyncSendMq(userCouponDOList);
    }

}
