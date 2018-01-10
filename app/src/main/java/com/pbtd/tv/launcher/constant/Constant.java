package com.pbtd.tv.launcher.constant;

import com.pbtd.tv.launcher.utils.MacUtil;

/**
 * Created by gong on 2017/7/7.
 */

public class Constant {
    public static final String APK_DOWNLOAD_URL = "apkUrl";
    public static final String APK_UPDATE_CONTENT = "message";
    public static final String APK_VERSION_CODE = "code";


    public static final int TYPE_NOTIFICATION = 2;

    public static final int TYPE_DIALOG = 1;

    public static final String TAG = "UpdateChecker";
//    public static final String IP = "http://192.168.0.148";
//    public static final String IP = "http://112.13.167.157:8080";
//    public static final String IP = "http://192.168.0.109:8080";
    public static final String IP = "111.63.146.238:8080";//鉴权
    private static String server = "111.63.146.238:8080";  //自有数据

    public static final String BUSSID = "http://112.13.167.157:8080/authAction!auth.action?type=3&key=111";
//    public static final String SPLASH = "/bussDetailInfoAction!getStartAd.action?bussId=";//开机
    public static  String SPLASH = "http://%s/bussDetailInfoAction!getStartAd.action?proj_id=%s&group_id=%s";//开机
//    public static final String LOGO = "/bussDetailInfoAction!getLogo.action?bussId=";//logo&背景图
    public static  String LOGO = "http://%s/bussDetailInfoAction!getLogo.action?proj_id=%s&group_id=%s";//logo&背景图
    public static final String NEWS = "/newsAction!getNews.action?bussId=";//消息接口
//    public static final String ADS = "/adsAction!getAdsListByNavId.action?navId=";//运营位
    public static  String ADS = "http://%s/adsAction!getAdsListByNavId.action?navId=%s&proj_id=%s&group_id=%s";//运营位
//    public static final String NAVIGATION = "/navigationAction!getNavigationList.action?bussId=";//底部导航栏
    public static  String NAVIGATION = "http://%s/navigationAction!getNavigationList.action?proj_id=%s&group_id=%s";//底部导航栏
    private static String AUTH = "http://%s/authAction!auth.action?type=%s&key=%s";

    public static String getSPLASH(String pro,String group){
//        String url = String.format(PAGE_CONTROL, MacUtil.getLocalEthernetMacAddress());
        String url = String.format(SPLASH, server,pro,group );
        return url;
    }

    public static String getLOGO(String pro,String group){
//        String url = String.format(PAGE_CONTROL, MacUtil.getLocalEthernetMacAddress());
        String url = String.format(LOGO, server,pro,group );
        return url;
    }



    public static String getAds(int id,String pro,String group){
//        String url = String.format(PAGE_CONTROL, MacUtil.getLocalEthernetMacAddress());
        String url = String.format(ADS, server,id,pro,group );
        return url;
    }

    public static String getNavigation(String pro,String group){
//        String url = String.format(PAGE_CONTROL, MacUtil.getLocalEthernetMacAddress());
        String url = String.format(NAVIGATION, server,pro,group );
        return url;
    }

    public static String getAuth(String type){
//        String url = String.format(PAGE_CONTROL, MacUtil.getLocalEthernetMacAddress());
        String url = String.format(AUTH, server,type, MacUtil.getLocalEthernetMacAddress());
        return url;
    }

    public static String getUpdateUrl(int s ){
        String url = String.format(UPDATE_URL,s);
        return url;
    }

    public static final String UPDATE_URL = "http://112.13.167.160/bussUpgradeHisAction!queryIsUpdateVersion.action?proj_id=cmcc&group_id=10000&version=%s&type=4";
}
