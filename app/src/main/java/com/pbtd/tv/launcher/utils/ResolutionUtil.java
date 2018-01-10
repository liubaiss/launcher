package com.pbtd.tv.launcher.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * @author yuqihui
 *
 */
public  class ResolutionUtil {
	
	public static final int WINDOWS_STANDARD_SIZE_WIDTH = 1920;
	public static final int WINDOWS_STANDARD_SIZE_HIGH = 1080;
	
	private static final float DEFAULTDENSITY = 240;
	
	private static final float DEFAULTFONTDESITY = 1.0F;
	
	private float density;
	
	private float fontDesity;
	
	private float scale;
	
	private int deviceWidth;

	private int deviceHeight;
	
	private float scaleWidth ;
	
	private float scaleHeight;
	
	public ResolutionUtil(Context ctx){
		DisplayMetrics dm = ctx.getResources().getDisplayMetrics();
		this.deviceWidth  = dm.widthPixels;
		this.deviceHeight = dm.heightPixels;
		density = dm.densityDpi;
		fontDesity = dm.scaledDensity;
//		if(deviceWidth > deviceHeight){
//			scaleWidth = (float)deviceWidth / WINDOWS_STANDARD_SIZE_HIGH;
//			scaleHeight = (float)deviceHeight / WINDOWS_STANDARD_SIZE_WIDTH;
//		}else{
			scaleWidth = (float)deviceWidth / WINDOWS_STANDARD_SIZE_WIDTH;
			scaleHeight = (float)deviceHeight / WINDOWS_STANDARD_SIZE_HIGH;
//		}
		
	}
	
	public int getWidth(){
		return deviceWidth;
	}
	
	public int getHeight(){
		return deviceHeight;
	}
	
	public int px2dp2pxWidth(float pxVlaue){
		float dp = pxVlaue / (density / DEFAULTDENSITY);
		return (int) (dp * (density / DEFAULTDENSITY) * scaleWidth);
			
	}
	
	public int px2dp2pxHeight(float pxVlaue){
		float dp = pxVlaue / (density / DEFAULTDENSITY);
		return (int) (dp * (density / DEFAULTDENSITY) * scaleHeight);
	}
	
	public int px2sp2px(float spVlaue){
		float dp = spVlaue / fontDesity;
		int px = (int) (dp * (fontDesity / DEFAULTFONTDESITY) / fontDesity * scaleWidth);
		return px;
	}
	
    public int dip2px(float dipValue){ 
		return (int)((int)(dipValue * scale) * density +0.5);
	} 

	public int px2dip(Context context, float pxValue) {
		return (int)((int)(pxValue * scale) / density + 0.5 );
	}
	
}
