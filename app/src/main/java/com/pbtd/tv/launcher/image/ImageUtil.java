package com.pbtd.tv.launcher.image;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.pbtd.tv.launcher.R;

import static android.R.attr.width;

/**
 * Created by zhouyong on 2017/5/27.
 */

public class ImageUtil {

    public static void setImageStyleRound(Context mContext, SimpleDraweeView simpleDraweeView){
        // 代码设置SimpleDraweeView的属性（会覆盖XML设置的所有属性，即在XML中有在这里没有的属性都会失效）
        // 注意：一个GenericDraweeHierarchy是不能被多个SimpleDraweeView共用的
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(mContext.getResources())
                .setFadeDuration(3000)
                .setFailureImage(R.mipmap.ic_launcher)
                .setFailureImageScaleType(ScalingUtils.ScaleType.FIT_XY)
                .setPlaceholderImage(R.mipmap.ic_launcher)
                .setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_XY)
//                .setProgressBarImage(new ProgressBarDrawable()) // 显示进度条（Fresco自带的进度条）
                .build();
        // 设置图片圆角
        RoundingParams roundingParams = new RoundingParams();
        roundingParams.setRoundAsCircle(false); // 不将图片剪切成圆形
        roundingParams.setCornersRadius(10);
        hierarchy.setRoundingParams(roundingParams);
        // 为SimpleDraweeView设置属性
        simpleDraweeView.setHierarchy(hierarchy);
    }

    public static void setImageStyleRound(Context mContext, SimpleDraweeView simpleDraweeView,int defaultId){
        // 代码设置SimpleDraweeView的属性（会覆盖XML设置的所有属性，即在XML中有在这里没有的属性都会失效）
        // 注意：一个GenericDraweeHierarchy是不能被多个SimpleDraweeView共用的
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(mContext.getResources())
                .setFadeDuration(3000)
                .setFailureImage(R.mipmap.ic_launcher)
                .setFailureImageScaleType(ScalingUtils.ScaleType.FIT_XY)
                .setPlaceholderImage(defaultId)
                .setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_XY)
                .build();
        // 设置图片圆角
        RoundingParams roundingParams = new RoundingParams();
        roundingParams.setRoundAsCircle(false); // 不将图片剪切成圆形
        roundingParams.setCornersRadius(10);
        hierarchy.setRoundingParams(roundingParams);
        // 为SimpleDraweeView设置属性
        simpleDraweeView.setHierarchy(hierarchy);
    }

    public static void setImageStyleRound2(Context mContext, SimpleDraweeView simpleDraweeView,int defaultId){
        // 代码设置SimpleDraweeView的属性（会覆盖XML设置的所有属性，即在XML中有在这里没有的属性都会失效）
        // 注意：一个GenericDraweeHierarchy是不能被多个SimpleDraweeView共用的
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(mContext.getResources())
                .setFadeDuration(0)
                .setPlaceholderImage(defaultId)
                .setFailureImage(R.mipmap.ic_launcher)
                .setFailureImageScaleType(ScalingUtils.ScaleType.FIT_XY)
                .setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_XY)
                .build();
        // 设置图片圆角
        RoundingParams roundingParams = new RoundingParams();
        roundingParams.setRoundAsCircle(false); // 不将图片剪切成圆形
        roundingParams.setCornersRadius(10);
        hierarchy.setRoundingParams(roundingParams);
        // 为SimpleDraweeView设置属性
        simpleDraweeView.setHierarchy(hierarchy);
    }

    public static void setImageStyleRound3(Context mContext, SimpleDraweeView simpleDraweeView,int defaultId){
        // 代码设置SimpleDraweeView的属性（会覆盖XML设置的所有属性，即在XML中有在这里没有的属性都会失效）
        // 注意：一个GenericDraweeHierarchy是不能被多个SimpleDraweeView共用的
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(mContext.getResources())
                .setFadeDuration(0)
                .setPlaceholderImage(defaultId)
                .setFailureImage(R.mipmap.ic_launcher)
                .setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_XY)
                .build();
        // 设置图片圆角
        RoundingParams roundingParams = new RoundingParams();
        roundingParams.setRoundAsCircle(false); // 不将图片剪切成圆形
        roundingParams.setCornersRadius(10);
        hierarchy.setRoundingParams(roundingParams);
        // 为SimpleDraweeView设置属性
        simpleDraweeView.setHierarchy(hierarchy);
    }


}
