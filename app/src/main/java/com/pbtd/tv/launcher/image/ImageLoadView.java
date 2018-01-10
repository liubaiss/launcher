package com.pbtd.tv.launcher.image;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.pbtd.tv.launcher.volley.VolleyUtils;


public class ImageLoadView extends MyNetworkImageView {
	public static final String TAG = ImageLoadView.class.getSimpleName();

	public ImageLoadView(Context context) {
		super(context);
	}

	public ImageLoadView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ImageLoadView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setImageUrl(final Context mContext, final String url){
		try {
//			//默认图片
//			setDefaultImageResId(R.mipmap.loading_default);
////			//加载失败图片
//			setErrorImageResId(R.mipmap.loading_default);


			setImageUrl(url, VolleyUtils.getInstance(mContext).getImageLoader());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			
		}
		
//		this.getWidth();
//		this.getHeight();
	}
	
	public void setImageUrl(final Context mContext, final String url, final int defautImgId){

		if(!TextUtils.isEmpty(url)){
			//默认图片
	         setDefaultImageResId(defautImgId);
//			//加载失败图片
	         setErrorImageResId(defautImgId);
	         setImageUrl(url, VolleyUtils.getInstance(mContext).getImageLoader());
		}else{
			//默认图片
			setDefaultImageResId(defautImgId);
		}

	}
	
	
	
}
