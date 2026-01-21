package com.qdbank.mall.util;


import com.qdbank.mall.bo.UserDetails;

/**
 * @ClassName LoginUserContextHolder
 * @Description TODO
 * @Author ningyuehuai
 * @Date 2021/2/1 16:08
 * @Version 1.0
 **/
public class UserContextHolder {
    /**
     * UserDetail 登录用户信息存储线程变量中
     */
    private static final ThreadLocal<UserDetails> USER_DETAILS_THREAD_LOCAL = new ThreadLocal<>();
    public static void setUserDetailsThreadLocal(UserDetails userDetails){
        USER_DETAILS_THREAD_LOCAL.set(userDetails);
    }
    public static UserDetails getUserDetails(){
        UserDetails userDetails = USER_DETAILS_THREAD_LOCAL.get();
        if(userDetails != null ) {
            return userDetails;
        }
        return null;
    }
    public static void clearUserDetails() {
        USER_DETAILS_THREAD_LOCAL.remove();
    }


}
