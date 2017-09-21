package com.lida.cloud.util;


import com.umeng.socialize.PlatformConfig;

/**
 * Created by Administrator on 2016/11/3 0003.
 */

public class ShareUtil {

    public static String weiboAppId = "3028578287";
    public static String weiboAppSecret = "8cad7ad744230a52024ea69aacc9544b";
    public static String qqAppId = "1106101833";
    public static String qqAppKEY = "ybr8p8FcpdMg3O5F";
    public static String weixinAppId = "wx624ae4772b304702";
    public static String weixinAppSecret = "d70e1d72a3d7af2321205d899ac54782";

    public static void init(){
        PlatformConfig.setWeixin(weixinAppId, weixinAppSecret);
        PlatformConfig.setSinaWeibo(weiboAppId, weiboAppSecret);
        PlatformConfig.setQQZone(qqAppId,qqAppKEY);
    }
}
