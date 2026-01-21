package com.qdbank.mall.constant.payment;

/**
 * 运营商
 */
public enum OilSupply {

        ZSH("zsh",	"中石化"),
        ZSY("zsy",	"中石油"),
;

        public String code;
    public String name;

    OilSupply(String code, String name){
        this.code=code;
        this.name=name;
    }

    public static String getName(String code){
            for(OilSupply mobileAddress : OilSupply.values()){
                    if(mobileAddress.code.equals(code)){
                            return mobileAddress.name;
                    }
            }
            return null;
    }



}
