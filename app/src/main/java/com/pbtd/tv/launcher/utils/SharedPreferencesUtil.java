package com.pbtd.tv.launcher.utils;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {
	
	private final static String FileName = "com.pbtd.tv.playtv";
	private static SharedPreferences sharedPreferences;
	public static void putUri(Context context,String uri){
		sharedPreferences = getSharedPreferences(context);
//		sharedPreferences.edit().putString("myuri", "14370,309006000").commit();
		sharedPreferences.edit().putString("myuri", uri).commit();
	}
	
	public static String getUri(Context context){
		sharedPreferences = getSharedPreferences(context);
		return sharedPreferences.getString("myuri", null);
	}
	
	public static void putPosition(Context context,int position){
		sharedPreferences = getSharedPreferences(context);
		sharedPreferences.edit().putInt("position", position).commit();
	}
	
	public static int getPosition(Context context){
		sharedPreferences = getSharedPreferences(context);
		return sharedPreferences.getInt("position", 0);
	}
	
	public static SharedPreferences getSharedPreferences(Context context){
		return context.getSharedPreferences(FileName, Activity.MODE_PRIVATE);
	}
	
	public static void putFileUrl(Context context, String fileUrl){
		sharedPreferences = getSharedPreferences(context);
		sharedPreferences.edit().putString("fileUrl", fileUrl).commit();
	}
	
	public static String getFileUrl(Context context){
		sharedPreferences = getSharedPreferences(context);
		return sharedPreferences.getString("fileUrl", "http://218.205.48.250:33200/EPG/FlashUi/yinhebhz");
	}

	public static long getDownloadId(Context context, String kEY_NAME_DOWNLOAD_ID) {
		sharedPreferences = getSharedPreferences(context);
		return sharedPreferences.getLong(kEY_NAME_DOWNLOAD_ID, 0);
	}

	public static void putDownloadId(Context context, String KeyName, long id) {
		sharedPreferences = getSharedPreferences(context);
		sharedPreferences.edit().putLong(KeyName, id).commit();
	}
	public static void putBussId(Context context, String bussId) {
		sharedPreferences = getSharedPreferences(context);
		sharedPreferences.edit().putString("bussId", bussId).commit();
	}
	public static String getBussId(Context context) {
		sharedPreferences = getSharedPreferences(context);
		return sharedPreferences.getString("bussId", "");
	}

    public static void putBgdImg(Context context, String bdgImg) {
        sharedPreferences = getSharedPreferences(context);
        sharedPreferences.edit().putString("bdgImg", bdgImg).commit();
    }
    public static String getBgdImg(Context context) {
        sharedPreferences = getSharedPreferences(context);
        return sharedPreferences.getString("bdgImg","");
    }
}
