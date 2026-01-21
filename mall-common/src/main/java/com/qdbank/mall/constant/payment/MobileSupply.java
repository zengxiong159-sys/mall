package com.qdbank.mall.constent.payment;

/**
 * 运营商
 */
public enum MobileSupply {

        CM("CM",	"移动"),
        CU("CU",	"联通"),
        CT("CT",	"电信"),
      SW("SW",	"三网");


        public String code;
    public String name;

    MobileSupply(String code, String name){
        this.code=code;
        this.name=name;
    }

    public static String getName(String code){
            for(MobileSupply mobileAddress : MobileSupply.values()){
                    if(mobileAddress.code.equals(code)){
                            return mobileAddress.name;
                    }
            }
            return null;
    }



}
