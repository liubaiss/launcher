package com.pbtd.tv.launcher.widget;

class Constants {


    // json {"url":"http://192.168.205.33:8080/Hello/app_v3.0.1_Other_20150116.apk","versionCode":2,"updateMessage":"版本更新信息"}

    static final String APK_DOWNLOAD_URL = "apkUrl";
    static final String APK_UPDATE_CONTENT = "message";
    static final String APK_VERSION_CODE = "code";


    static final int TYPE_NOTIFICATION = 2;

    static final int TYPE_DIALOG = 1;

    static final String TAG = "UpdateChecker";

//    static final String UPDATE_URL = "https://raw.githubusercontent.com/feicien/android-auto-update/develop/extras/update.json";
    static String getUpdateUrl(int s ){
        String url = String.format(UPDATE_URL,s);
        return url;
    }

    static final String UPDATE_URL = "http://112.13.167.160/bussUpgradeHisAction!queryIsUpdateVersion.action?proj_id=cmcc&group_id=10000&version=%s&type=4";
}
