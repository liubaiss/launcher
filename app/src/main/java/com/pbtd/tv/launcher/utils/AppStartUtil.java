package com.pbtd.tv.launcher.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.pbtd.tv.launcher.bean.EpgListEntity;
import com.pbtd.tv.launcher.bean.PageItemEntity;
import com.pbtd.tv.launcher.bean.Params;
import com.pbtd.tv.launcher.bean.PlayEntity;
import com.pbtd.tv.launcher.bean.TabEntity;

import java.util.List;

public class AppStartUtil {
    public static final String ENTITY = "entity";

    public static void startApp(Context mContext, String packageName) throws NameNotFoundException {
        PackageInfo pi = mContext.getPackageManager().getPackageInfo(packageName, 0);

        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(pi.packageName);
        List<ResolveInfo> apps = mContext.getPackageManager()
                .queryIntentActivities(resolveIntent, 0);

        ResolveInfo ri = apps.iterator().next();
        if (ri != null) {
            String packageName2 = ri.activityInfo.packageName;
            String className = ri.activityInfo.name;

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);

            ComponentName cn = new ComponentName(packageName2, className);

            intent.setComponent(cn);
            mContext.startActivity(intent);
        }

    }

    /**
     *
     * 运营为跳转
     * @param mContext
     * @param packageName
     * @param className
     * @param data
     */
    public static void startApp(Context mContext, String packageName, String className, PageItemEntity.DataBean data) {
        ComponentName componetName = new ComponentName(packageName, className);
//        ComponentName componetName = new ComponentName("com.pbtd.tv.live", "com.pbtd.tv.live.activity.MainActivity");
        Intent mIntent = new Intent();
        if(data != null){
//            将data里的key和value传递过去成对
            for (int i = 0; i<data.paramKeys.size();i++){
                Bundle bundle = new Bundle();
                bundle.putSerializable(data.paramKeys.get(i),data.paramValues.get(i));
                mIntent.putExtras(bundle);
            }
//                            Bundle bundle = new Bundle();
//                bundle.putSerializable("type",8);
//                bundle.putSerializable("chnNum",1);
//                mIntent.putExtras(bundle);
//            mIntent.putExtra("launcher","1");
//            mIntent.putExtra("index","29");
//            mIntent.putExtra("startTime","1501664580000");
//            mIntent.putExtra("endTime","1501668240000");
//            mIntent.putExtra("type",8);
//            mIntent.putExtra("chnNum",1);
//            mIntent.putExtra("playurl","http://live.dispatcher.hzprotrend.com/cibn_live/CCTV-1-HD/live.m3u8?project=SD_CMCC_OTT_HUAWEI");

        }
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mIntent.setComponent(componetName);
        mContext.startActivity(mIntent);
    }
    public static void startApp1(Context mContext, String packageName, String className) {
//        ComponentName componetName = new ComponentName(packageName, className);
        ComponentName componetName = new ComponentName("com.pbtd.tv.live", "com.pbtd.tv.live.activity.MainActivity");
        Intent mIntent = new Intent();
//            mIntent.putExtra("launcher","1");
//            mIntent.putExtra("index","29");
//            mIntent.putExtra("startTime","1501664580000");
//            mIntent.putExtra("endTime","1501668240000");
//            mIntent.putExtra("type",8);
//            mIntent.putExtra("chnNum",1);
//            mIntent.putExtra("playurl","http://live.dispatcher.hzprotrend.com/cibn_live/CCTV-1-HD/live.m3u8?project=SD_CMCC_OTT_HUAWEI");

        mContext.startActivity(mIntent);
    }

    /**
     * 导航跳转
     * @param mContext
     * @param packageName
     * @param className
     * @param data
     */
    public static void startApp(Context mContext, String packageName, String className, TabEntity.DataBean data) {
        if (packageName==null||className==null){
            Toast.makeText(mContext,"packageName或className为空",Toast.LENGTH_SHORT).show();
        }
        ComponentName componetName = new ComponentName(packageName, className);
        Intent mIntent = new Intent();
        if(data != null){
            //将data里的key和value传递过去成对
            for (int i = 0; i<data.paramKeys.size();i++){
                Bundle bundle = new Bundle();
                bundle.putSerializable(data.paramKeys.get(i),data.paramValues.get(i));
                mIntent.putExtras(bundle);
            }
        }
        mIntent.setComponent(componetName);
        mContext.startActivity(mIntent);
    }


   public static void startapk(Context context,String appName,PageItemEntity.DataBean data)throws NameNotFoundException{

        // 获取包管理器
        PackageManager manager = context.getPackageManager();
        // 指定入口,启动类型,包名
        Intent intent = new Intent(Intent.ACTION_MAIN);//入口Main
        intent.addCategory(Intent.CATEGORY_LAUNCHER);// 启动LAUNCHER,跟MainActivity里面的配置类似
        intent.setPackage(appName);//包名
        //查询要启动的Activity
        List<ResolveInfo> apps = manager.queryIntentActivities(intent, 0);
        if (apps.size() > 0) {//如果包名存在
            ResolveInfo ri = apps.get(0);
            // //获取包名
            String packageName = ri.activityInfo.packageName;
            //获取app启动类型
            String className = ri.activityInfo.name;
            //组装包名和类名
            ComponentName cn = new ComponentName(packageName, className);
            //设置给Intent
            intent.setComponent(cn);
            //根据包名类型打开Activity
            if(data != null){
                //将data里的key和value传递过去成对
                for (int i = 0; i<data.paramKeys.size();i++){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(data.paramKeys.get(i),data.paramValues.get(i));
                    intent.putExtras(bundle);
                }
            }
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "找不到包名;" + appName, Toast.LENGTH_SHORT).show();
        }
    }
    public static void startapk(Context context,String appName,TabEntity.DataBean data)throws NameNotFoundException{

        // 获取包管理器
        PackageManager manager = context.getPackageManager();
        // 指定入口,启动类型,包名
        Intent intent = new Intent(Intent.ACTION_MAIN);//入口Main
        intent.addCategory(Intent.CATEGORY_LAUNCHER);// 启动LAUNCHER,跟MainActivity里面的配置类似
        intent.setPackage(appName);//包名
        //查询要启动的Activity
        List<ResolveInfo> apps = manager.queryIntentActivities(intent, 0);
        if (apps.size() > 0) {//如果包名存在
            ResolveInfo ri = apps.get(0);
            // //获取包名
            String packageName = ri.activityInfo.packageName;
            //获取app启动类型
            String className = ri.activityInfo.name;
            //组装包名和类名
            ComponentName cn = new ComponentName(packageName, className);
            //设置给Intent
            intent.setComponent(cn);
            //根据包名类型打开Activity
            if(data != null){
                //将data里的key和value传递过去成对
                for (int i = 0; i<data.paramKeys.size();i++){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(data.paramKeys.get(i),data.paramValues.get(i));
                    intent.putExtras(bundle);
                }
            }
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "找不到包名;" + appName, Toast.LENGTH_SHORT).show();
        }
    }



    public static void startActivity(Context mContext, String className) throws ClassNotFoundException {
        if (!TextUtils.isEmpty(className)) {
            Class activity = Class.forName(className);
            Intent intent = new Intent(mContext, activity);
            mContext.startActivity(intent);
        }
    }

    public static void startActivity(Context mContext, String className,EpgListEntity.Data data) throws ClassNotFoundException {
//		Class activity = Class.forName("com.gitv.launcher.market.AppActivity" );
        if(!TextUtils.isEmpty(className)){
            Class activity = Class.forName(className);
            Intent intent = new Intent(mContext,activity);
            if(data != null){
                Bundle bundle = new Bundle();
                bundle.putSerializable(ENTITY,data);
                intent.putExtras(bundle);
            }

            mContext.startActivity(intent);

        }

    }
    public static void startActivity(Context mContext, String className,PlayEntity entity) throws ClassNotFoundException {
//		Class activity = Class.forName("com.gitv.launcher.market.AppActivity" );
        if(!TextUtils.isEmpty(className)){
            Class activity = Class.forName(className);
            Intent intent = new Intent(mContext,activity);
            if(entity != null){
                Bundle bundle = new Bundle();
                bundle.putSerializable(PlayEntity.ID,entity);
                intent.putExtras(bundle);
            }

            mContext.startActivity(intent);

        }

    }
}
