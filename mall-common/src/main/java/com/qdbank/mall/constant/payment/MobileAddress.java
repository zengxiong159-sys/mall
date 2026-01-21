package com.qdbank.mall.constant.payment;

/**
 * 地址码表
 */
public enum MobileAddress {

        A10001("10001",	"北京"),
        A10002("10002",	"天津"),
        A10003("10003",	"上海"),
        A10004("10004",	"重庆"),
        A10005("10005",	"河北"),
        A10006("10006",	"河南"),
        A10007("10007",	"云南"),
        A10008("10008",	"辽宁"),
        A10009("10009",	"黑龙江"),
        A10010("10010",	"湖南"),
        A10011("10011",	"湖北"),
        A10012("10012",	"安徽"),
        A10013("10013",	"山东"),
        A10014("10014",	"江苏"),
        A10015("10015",	"浙江"),
        A10016("10016",	"江西"),
        A10017("10017",	"广西"),
        A10018("10018",	"甘肃"),
        A10019("10019",	"山西"),
        A10020("10020",	"内蒙古"),
        A10021("10021",	"陕西"),
        A10022("10022",	"吉林"),
        A10023("10023",	"福建"),
        A10024("10024",	"贵州"),
        A10025("10025",	"四川"),
        A10026("10026",	"广东"),
        A10027("10027",	"青海"),
        A10028("10028",	"西藏"),
        A10029("10029",	"新疆"),
        A10030("10030",	"宁夏"),
        A10031("10031",	"海南"),
        A10032("10032",	"全国");

    public String code;
    public String name;

    MobileAddress(String code,String name){
        this.code=code;
        this.name=name;
    }

    public static String getName(String code){
            for(MobileAddress mobileAddress : MobileAddress.values()){
                    if(mobileAddress.code.equals(code)){
                            return mobileAddress.name;
                    }
            }
            return null;
    }



}
