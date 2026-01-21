package com.qdbank.mall.api;

import lombok.Getter;

/**
 * 枚举了一些常用API操作码
 * Created by ningyuehuai on 2020/10/19.
 */
@Getter
public enum ResultCode implements IErrorCode {
    /**
     * 公共响应码
     */
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限，请联系管理员"),
    SYSTEM_EXCEPTION(405,"系统异常"),
    ILLEGAL_IMG_FILE_TYPE(406, "图片文件类型非法"),
    REPEAT_LOGIN(407, "你已下线，账户在其他地方登录"),
    /**
     * 1000-1999 银行后端错误码
     */
    USER_EXIST(1000,"用户名已存在"),
    USER_PASSWORD_ERROR(1001,"请输入正确的账号和密码"),
    ACCOUNT_FORBIDDEN(1002,"账号已禁用"),
    USER_NOT_EXIST(1003,"用户不存在"),
    ROLE_ID_BIND(1004,"角色已加挂用户，暂不允许删除"),
    DEPT_NO_BIND(1005,"部门已加挂用户，暂不允许删除"),
    EMAIL_NOT_EXIST(1006,"用户邮箱不存在"),
    UPLOAD_FILE_EXCEPTION(1007,"上传文件异常"),
    BUILD_ACTIVITY_EXCEPTION(1008,"新建活动异常"),
    ACTIVITY_NAME_EXIST(1009,"活动名称已存在，请重新输入"),
    ADVERTISE_NAME_EXIST(1010,"广告名称已存在，请重新输入"),
    BUILD_ADVERTISE_EXCEPTION(1011,"新建广告异常"),
    DELETE_CATEGORY_ID_NOT_EXIST(1012,"删除分类不存在"),
    PRODUCT_NO_EXIST(1013,"商品信息不存在"),
    PRODUCT_STATUS_ERROR(1014,"商品非上架状态"),
    PRODUCT_NOT_EXIST(1015,"该商品不存在或已下架"),
    COUPON_TYPE_ERROR(1016,"优惠券类型传入错误"),
    PARASE_COUPON_FILE_ERROR(1017,"解析白名单文件异常"),
    COUPON_FILE_EMPTY(1018,"白名单文件不能为空"),
    COUPON_FILE_SIZE_ERROR(1019,"最多支持100000条数据"),
    COUPON_FILE_OUT_SIZE(1020,"文件不能超过5MB"),
    COUPON_FILE_TYPE_ERROR(1021,"文件格式不正确"),
    COUPON_FILE_EXIST_REPEATE_DATE(1022,"存在重复数据,请核对"),
    COUPON_ID_ERROR(1023,"券商品ID传入错误"),
    COUPON_STATUS_ERROR(1024,"优惠券状态非待发放(生效)状态,不允许编辑"),
    CUST_NO_ERROR(1025,"白名单格式或数据有误，上传失败，请检查后重新上传"),
    DELETE_USER_COUPON_ERROR(1026,"删除用户优惠券错误"),
    COUPON_BATCH_NO_NOT_EXIST(1027,"该批次白名单数据不存在"),
    COUPON_BATCH_STATUS_ERROR(1028,"批次状态传入错误"),
    COUPON_BATCH_STATUS_EXPIRE(1029,"批次状态为已过期、已作废或已失效，不允许修改"),
    USER_COUPON_STATUS_EXPIRE(1030, "券已过期或已作废,无法换购"),
    USER_COUPON_NOT_EXIST(1031, "券未发放或不存在,暂无可用的券"),
    COUPON_NOT_ACTIVE(1032, "券未生效"),
    COUPON_ISSUE_REPEAT(1033, "券已发放,不允许重复发放"),
    SER_COUPON_STATUS_USED(1034, "券已使用"),

    TRADE_TYPE_ERROR(1016,"交易类型传入错误"),
    EMAIL_FORMAT_ERROR(1017,"请填写正确的用户邮箱"),
    MERCHANT_ADMIN_EXIST(1018,"商户管理员账号已存在"),
    OLD_PASSWORD_ERROR(1019,"旧密码错误"),
    ORDER_NOT_EXIST(1020,"订单不存在"),
    PARAM_NAME_EXIST(1021,"该参数名称已存在"),
    PARAM_NAME_NOT_EXIST(1022,"参数名称不存在"),
    PRODUCT_SKU_STOCK_NOT_EXIST(1023,"请正确填写商品编号和规格编号"),
    USER_PASSWORD_NOT_COMPLICATED(1024,"密码必须包含数字、大小写字符、特殊字符,且密码长度不少于8位"),
    COUPON_RELATION_PRODUCT(1025,"该商品已关联现金券批次，不允许下架。如需下架，请先作废关联的现金批次"),
    COUPON_NOT_CASH_PAY(1026,"关联的商品实际售价非全现金支付，暂不支持，请核对"),
    COUPON_DATA_PROCESSING(1027,"白名单数据解析中，请稍后处理"),
    COUPON_PRODUCT_NOT_EXIST(1028,"券商品不存在或未上架，请核对"),
    ORDER_SN_START_ERROR(1027,"订单编号格式或数据错误，请核对"),
    COMPANY_NAME_ERROR(1028,"物流公司格式或数据错误，请核对"),
    COMPANY_SN_ERROR(1029,"物流单号格式或数据错误，请核对"),
    ORDER_SIZE_OUT(1030,"最多支持1000条数据"),
    FILE_EMPTY(1031,"文件内容存在空值，请核对"),
    PREFECTURE_ID_ERROR(1032,"专区编号不存在或未启用，请核对"),
    FILE_DOWN_LOADING(1033,"文件处理中，请稍后查询"),
    DATA_EMPTY(1034,"数据为空"),
    AES_DECRYPT_ERROR(1035,"AES解密出错"),
    MAX_INTEGRAL_DEDUCT_LIMIT(1036,"积分最多可抵扣量超出限制,请核对!"),
    GENDER_ERROR(1037,"性别传入错误"),
    USER_NAME_ERROR(1038,"姓名为空或者长度超过5位"),
    ID_NO_ERROR(1039,"身份证为空或者格式不正确"),
    MOBILE_ERROR(1040,"手机号码格式不正确"),
    ID_NO_REPEAT(1041,"该身份证号已存在，请勿重复添加"),
    NO_OPERATE_AUTH(1042,"非管理员，无操作权限"),

    /***
     * 2000-2999 商户后端错误码
     */

    BUILD_SPECIFICATIONS_MAIN_PICTURE_EXCEPTION(2001,"新建规格信息主图异常"),
    BUILD_SPECIFICATIONS_ATLAS_EXCEPTION(2002,"新建商品图册异常"),
    BUILD_SPECIFICATIONS_UPLOAD_PICTURES_EXCEPTION(2003,"新建商品详情上传图片异常"),
    ORDER_REFUND_NULL_EXCEPTION(2004,"没有该订单"),
    ORDER_REFUND_NOT_THREE_OR_ONE(2005,"订单状态不是已完成或者代发货"),
    ORDER_REFUND_SUCCESS(2006,"该订单已经退款成功"),
    ORDER_REFUND_IN_PROGRESS(2007,"该订单已经在申请退款"),
    THREE_MONTHS_EXCEL_EXPORT(2008,"请以三个月为时间间隔"),
    SPECIFICATION_ATTRIBUTE_NAMBER(2009,"该属性下已经存在20个参数"),
    SPRCIFICATION_ATTRIBUTE_NAME(2010,"已存在该规格名称"),
    FREIGHT_TEMPLATE_NAME(2011,"已存在该模板名称"),
    FREIGHT_TEMPLATE_PROVINCEIDS(2012,"省份信息不能相同"),
    BUILD_SKUSTOCK_PRODUCT_SP_DATA(2013,"不能有相同的商品属性"),
    PRODUCT_NAME(2014,"不能创建相同的商品名称"),
    UPDATE_SKU_ERROR(2015,"修改规格信息错误"),
    BEGAIN_TIEM_ERROR(2016,"开始时间不能为空"),
    END_TIEM_ERROR(2017,"结束时间不能为空"),
    BEGAIN_END_TIEM_ERROR(2018,"开始时间不能晚于截至时间"),
    BEGAIN_END_TIEM_OVER_ERROR(2019,"开始时间和截至时间相差过大"),
    SPRCIFICATION_ATTRIBUTE_NAME_CORRELATION(2020, "该规格已关联上架或入库商品,暂不支持操作"),
    SPRCIFICATION_ATTRIBUTE_NAME_EMPTY(2021, "规格属性不允许为空,请重新设置规格属性"),
    SPRCIFICATION_ATTRIBUTE_NAME_CHANGED(2022, "商品规格信息异常,请设置商品规格后再操作入库"),
    TEMPLATE_INFO_EXCEPTION(2023,"商品运费信息异常，请设置后再操作入库"),
    SEND_RETURN_OUT(2024,"撤销失败"),
    /**
     * 3000-3999 商城C端响应码
     * TODO 后续拆出独立的的模块
     */
    ADDRESS_NOT_FOUND_ERROR(3001,"地址不存在"),
    ADDRESS_OLD_NEW_SSAME_ERROR(3002,"新老地址一样"),
    PRODUCT_NOT_FOUND(3003,"商品不存在"),
    PRODUCT_NOT_DEPLOY(3004,"商品未上架"),
    OUT_OF_STOCK(3005,"商品规格库存不足"),
    PRODUCT_LIMIT_STOCK(3006,"您已超出购买限制，暂时无法下单。"),

    USER_COUPON_EXPIR(3007,"优惠券已失效，请确认"),



    USER_COUPON_NOT_FIX_PRODUCT(3008,"优惠券非指定商品"),
    USER_ORDER_HAS_NOTIC(3009,"订单已通知或不存在"),
    ORDER_STATUS_ERROR(3010,"订单状态异常"),

    USER_UNFIND_ERROR(3011,"用户不存在"),
    NOT_BR_ERROR(3012,"非本人操作"),
    USER_COUPON_STATUS_ERROR(3013,"优惠券状态不正确"),
    USER_ORDER_HAS_NOT_FOUND(3014,"订单不存在"),
    STATE_MACHINE_STATUS_FOUND(3015,"状态机流程状态异常"),
    APPROVE_ORDER_STATUS_ERROR(3016,"审核订单状态异常"),
    USER_ERROR(3017,"交易需要绑定授权"),
    HAS_REFUND_ERROR(3018,"订单已退款，不能再退款"),
    REFUND_HAS_ERROR(3019,"该退款流水不需要在进行退款"),
    REFUND_ERROR(3020,"退款异常"),
    PHONE_NUM_ERROR(3021,"抱歉，您的手机号号段暂不支持充值"),


    THIRD_SYSTEM_ERROR(3100,"第三方调用失败"),
    MOBILE_RECHARGE_ERROR(3101,"话费充值中"),
    MERCHANT_NOT_FOUND_ERROR(3102,"商户不存在"),
    NOT_SUPPORT_SEND_ERROR(3103,"该地区不支持配送"),
    USER_COUPON_NOT_GET_EXPIR(3104,"未到优惠券使用时间，请关注有效期开始时间"),
    APPLAY_REFUND_EXPIR(3105,"申请退款时效已过期"),
    NOT_SUPPORT_MOBILE(3106,"抱歉，您的手机号号段暂不支持充值"),
    INTEGRAL_QUERY_ERROR(3107,"积分余额查询异常，请联系客服4006696588，您也可以尝试用现金购买哦"),
    ORDER_BATCH_SEND_ERROR(3108,"订单状态异常，请核对"),
    INTEGRAL_SIGN_ERROR(3109,"积分签到失败"),
    INTEGRAL_SIGN_SUCCESS(3120,"积分签到成功"),

    NOT_BANK_EMPLOYEE(3121,"非本行员工"),
    MOBILE_PHONE_NO_INCONSISTENT(3122,"手机号与智慧人事系统预留手机号不一致"),
    MESSAGE_CODE_ERROR(3123,"验证码好像不对噢，请重新输入"),
    SESSION_KEY_TIMEOUT(3124, "session key超时或不存在"),
    MESSAGE_CODE_TIMEOUT(3125,"输入超时，请重新获取再输入"),
    NOT_EXISTS_AVAILABLE_ACCOUNT(3126,"不存在可用账户信息"),

    PASSWORD_ERROR(175010004,"密码错误,请重新输入"),
    PAY_FAIL(175010007,"请勿重复提交订单"),
    SEND_MESSAGE_LIMIT(3127,"发送短信过于频繁，请稍后再试"),
    ACCOUNT_LOCK(3128,"您的账号已锁定，请2分钟之后重新登录"),
    MESSAGE_REPEAT(3129,"15秒内请勿重复发送短信"),
    DISCOUNT_AMOUNT_DIFF(3130,"优惠金额计算不一致")

    ;

    private long code;
    private String message;

    public static ResultCode getResultCode(Long code){
        for(ResultCode resultCode : ResultCode.values()){
            if(resultCode.code==code){
                return resultCode;
            }
        }
        return null;
    }

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }
}
