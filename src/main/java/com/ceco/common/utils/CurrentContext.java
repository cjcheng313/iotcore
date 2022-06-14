package com.ccj.common.utils;

public class CurrentContext {

    private String userToken;
    private String nonce;
    private String systemKey;
    private String uid;
    private String uname;
    private String mobile;


    private static final ThreadLocal<CurrentContext> THREAD_LOCAL = new ThreadLocal<CurrentContext>() {
        @Override
        protected CurrentContext initialValue() {
            return new CurrentContext();
        }
    };

    public static String getUserToken() {
        return THREAD_LOCAL.get().userToken;
    }

    public static void setUserToken(String userToken) {
        CurrentContext context = THREAD_LOCAL.get();
        context.userToken = userToken;
    }

    public static String getNonce() {
        return THREAD_LOCAL.get().nonce;
    }

    public static void setNonce(String nonce) {
        CurrentContext context = THREAD_LOCAL.get();
        context.nonce = nonce;
    }

    public static String getSystemKey() {
        return THREAD_LOCAL.get().systemKey;
    }

    public static void setSystemKey(String systemKey) {
        CurrentContext context = THREAD_LOCAL.get();
        context.systemKey = systemKey;
    }

    public static String getUid() {
        return THREAD_LOCAL.get().uid;
    }

    public static void setUid(String uid) {
        CurrentContext context = THREAD_LOCAL.get();
        context.uid = uid;
    }

    public static String getUname() {
        return THREAD_LOCAL.get().uname;
    }

    public static void setUname(String uname) {
        CurrentContext context = THREAD_LOCAL.get();
        context.uname = uname;
    }

    public static String getMobile() {
        return THREAD_LOCAL.get().mobile;
    }

    public static void setMobile(String mobile) {
        CurrentContext context = THREAD_LOCAL.get();
        context.mobile = mobile;
    }

    public static void removeContext() {
        THREAD_LOCAL.remove();
    }

}
