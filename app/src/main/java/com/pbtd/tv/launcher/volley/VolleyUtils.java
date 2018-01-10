package com.pbtd.tv.launcher.volley;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.Volley;
import com.pbtd.tv.launcher.image.DiskLruCache;

import java.io.File;


public class VolleyUtils {
	
	private static VolleyUtils volleyUtils;
	
	private RequestQueue mQueue;
	
	private ImageLoader mImageLoader;
	
//	private ImageCacheManager imageCacheManager;
	
	private DiskLruCache cache;

	private static Context mContext;
	
	
	
	
	private VolleyUtils(Context ctx){
		mContext = ctx;
		mQueue = getRequestQueue();
		mImageLoader = initImageLoader();
		File file = getFile();
		if(!file.exists()){
			file.mkdirs();
		}
		cache = DiskLruCache.openCache(mContext,file , 1024 * 1024 * 10);

	}
	
	public static File getFile(){
		File file = DiskLruCache.getDiskCacheDir(mContext, "cache_img");
		return file;
	}
	
	public static synchronized VolleyUtils getInstance(Context context){
		if(volleyUtils == null){
			volleyUtils = new VolleyUtils(context);
		}
		return volleyUtils;
	}
	
	private ImageLoader initImageLoader() {
		if(mImageLoader == null){
			mImageLoader = new ImageLoader(mQueue, mImageCache);
		}
		return mImageLoader;
	}

	private ImageCache mImageCache = new ImageCache() {
		
		@Override
		public void putBitmap(String url, Bitmap bitmap) {
			cache.put(url, bitmap);
		}
		
		@Override
		public Bitmap getBitmap(String url) {
			return cache.get(url);
//			return null;
		}
	};
	
	public RequestQueue getRequestQueue(){
		if(mQueue == null){
			mQueue = Volley.newRequestQueue(mContext.getApplicationContext());
			mQueue.start();
		}
		return mQueue;
	}
	
	public ImageLoader getImageLoader(){
		return mImageLoader;
	}
	
	/**
	 * 添加任务
	 * @param reg
	 */
	public <T> void addToRequestQueue(Request<T> reg){
		getRequestQueue().add(reg);
	}
	
	/**
	 * 取消任务
	 */
	public void cancleAll(){
		getRequestQueue().cancelAll("volley_tag");
	}
	
}
