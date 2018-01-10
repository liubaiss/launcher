package com.pbtd.tv.launcher.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.pbtd.tv.launcher.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.security.AccessController.getContext;

public class ImageUtil {

    public static Bitmap createReflectedImage(Bitmap originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        Matrix matrix = new Matrix();
        // 实现图片翻转90度  
        matrix.preScale(1, -1);
        // 创建倒影图片（是原始图片的一半大小）  
        Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0, 0, width, height, matrix, false);
        // 创建总图片（原图片 + 倒影图片）  
//        Bitmap finalReflection = Bitmap.createBitmap(width, (height + height / 2), Config.ARGB_8888);  
        // 创建画布  
        Canvas canvas = new Canvas(reflectionImage);
        canvas.drawBitmap(reflectionImage, 0, 0, null);
//        //把倒影图片画到画布上  
////        canvas.drawBitmap(reflectionImage, 0, height + 1, null);  
        Paint shaderPaint = new Paint();
        //创建线性渐变LinearGradient对象  
        LinearGradient shader = new LinearGradient(0, 0, 0, reflectionImage.getHeight() + 1, 0x70ffffff,
                0x00ffffff, TileMode.MIRROR);
        shaderPaint.setShader(shader);
        shaderPaint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        //画布画出反转图片大小区域，然后把渐变效果加到其中，就出现了图片的倒影效果。  
        canvas.drawRect(0, 0, width, reflectionImage.getHeight(), shaderPaint);
        return reflectionImage;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int width = drawable.getIntrinsicWidth();
        width = width > 0 ? width : 1;
        int height = drawable.getIntrinsicHeight();
        height = height > 0 ? height : 1;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public static void setImageStyleRound(Context mContext, SimpleDraweeView simpleDraweeView, int defaultId) {
        // 代码设置SimpleDraweeView的属性（会覆盖XML设置的所有属性，即在XML中有在这里没有的属性都会失效）
        // 注意：一个GenericDraweeHierarchy是不能被多个SimpleDraweeView共用的
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(mContext.getResources())
                .setFadeDuration(3000)
                .setFailureImage(R.mipmap.album_default)
                .setFailureImageScaleType(ScalingUtils.ScaleType.FIT_XY)
                .setPlaceholderImage(defaultId)
                .setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_XY)
                .build();
        // 设置图片圆角
        RoundingParams roundingParams = new RoundingParams();
        roundingParams.setRoundAsCircle(false); // 不将图片剪切成圆形
//        roundingParams.setCornersRadius(10);
        hierarchy.setRoundingParams(roundingParams);
        // 为SimpleDraweeView设置属性
        simpleDraweeView.setHierarchy(hierarchy);
    }

    /**
     * 运营位图片加载
     * @param mContext
     * @param simpleDraweeView
     * @param defaultId
     * @param uri
     */
    public static void setImageStyleRound2(Context mContext, SimpleDraweeView simpleDraweeView, int defaultId, Uri uri) {
        // 代码设置SimpleDraweeView的属性（会覆盖XML设置的所有属性，即在XML中有在这里没有的属性都会失效）
        // 注意：一个GenericDraweeHierarchy是不能被多个SimpleDraweeView共用的
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(mContext.getResources())
                .setFadeDuration(300)
                .setPlaceholderImage(defaultId)
                .setFailureImage(defaultId)
                .setProgressBarImage(defaultId)
                .setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_XY)
                .setFailureImageScaleType(ScalingUtils.ScaleType.FIT_XY)
                .setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_XY)
                .build();
        // 设置图片圆角

//        RoundingParams roundingParams = new RoundingParams();
//        roundingParams.setRoundAsCircle(false); // 不将图片剪切成圆形
//        roundingParams.setCornersRadius(10);
//        hierarchy.setRoundingParams(roundingParams);


        // 为SimpleDraweeView设置属性
        simpleDraweeView.setHierarchy(hierarchy);
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(simpleDraweeView.getController())
                .build();
        simpleDraweeView.setController(controller);
        simpleDraweeView.setImageURI(uri);
    }

    public static void setImageStyleRound3(Context mContext, SimpleDraweeView simpleDraweeView, int defaultId) {
        // 代码设置SimpleDraweeView的属性（会覆盖XML设置的所有属性，即在XML中有在这里没有的属性都会失效）
        // 注意：一个GenericDraweeHierarchy是不能被多个SimpleDraweeView共用的
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(mContext.getResources())
                .setFadeDuration(0)
                .setPlaceholderImage(defaultId)
                .setFailureImage(R.mipmap.album_default)
                .setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_XY)
                .build();
        // 设置图片圆角
        RoundingParams roundingParams = new RoundingParams();
        roundingParams.setRoundAsCircle(false); // 不将图片剪切成圆形
//        roundingParams.setCornersRadius(10);
        hierarchy.setRoundingParams(roundingParams);
        // 为SimpleDraweeView设置属性
        simpleDraweeView.setHierarchy(hierarchy);
    }

    public static void setfrescoimage(final String uri, SimpleDraweeView simpleDraweeView, final Context mcontext) {
        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(
                    String id,
                    @Nullable ImageInfo imageInfo,
                    @Nullable Animatable anim) {
                if (imageInfo == null) {
                    return;
                }

                QualityInfo qualityInfo = imageInfo.getQualityInfo();
                Toast.makeText(mcontext, uri, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
                Toast.makeText(mcontext, "loading", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                Toast.makeText(mcontext, "faile", Toast.LENGTH_SHORT).show();
            }
        };

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setControllerListener(controllerListener)
                .setImageRequest(ImageRequest.fromUri(uri))
//                .setUri(uri)
                // other setters
                .build();
        simpleDraweeView.setController(controller);
    }

    public static void setfrescoloading(SimpleDraweeView simpleDraweeView, Uri uri) {
//        Uri uri;
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(simpleDraweeView.getController())
                .build();
        simpleDraweeView.setController(controller);
    }

    public static void setImageStyleRound2(Uri uri, final Context mcontext) {
//        cacheKey = DefaultCacheKeyFactory.getInstance().getEncodedCacheKey(ImageRequest.fromUri(Uri.parse(data.imageUrl.get(0))),null);
//
//                File file = getCachedImageOnDisk(cacheKey);
//
        Bitmap bitmap;
        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        final DataSource<CloseableReference<CloseableImage>>
                dataSource = imagePipeline.fetchDecodedImage(imageRequest, getContext());
        dataSource.subscribe(new BaseBitmapDataSubscriber() {
            @Override
            public void onNewResultImpl(Bitmap bitmap) {
                if (bitmap == null) {
                    bitmap = bitmap;
//                            Log.e(TAG,"保存图片失败啦,无法下载图片");
                    Toast.makeText(mcontext, "保存图片失败啦,无法下载图片", Toast.LENGTH_SHORT).show();
                }
//                        File appDir = new File(IMAGE_PIC_CACHE_DIR);
//                        if (!appDir.exists()) {
//                            appDir.mkdir();
//                        }
//                        String fileName = filename + ".jpg";
//                        File file = new File(appDir, fileName);
//                        try {
//                            FileOutputStream fos = new FileOutputStream(file);
//                            assert bitmap != null;
//                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//                            fos.flush();
//                            fos.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
            }

            @Override
            public void onFailureImpl(DataSource dataSource) {

            }
        }, CallerThreadExecutor.getInstance());

    }

    public static void imagerequest(Uri muri,SimpleDraweeView simpleDraweeView){
//        Uri uri = Uri.parse(muri);
//
//        ImageDecodeOptions decodeOptions = ImageDecodeOptions.newBuilder()
////                .setBackgroundColor(Color.GREEN)
//                .build();
//
//        ImageRequest request = ImageRequestBuilder
//                .newBuilderWithSource(uri)
//                .setImageDecodeOptions(decodeOptions)
//                .setAutoRotateEnabled(true)
//                .setLocalThumbnailPreviewsEnabled(true)
//                .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.FULL_FETCH)
//                .setProgressiveRenderingEnabled(false)
////                .setResizeOptions(new ResizeOptions(width, height))
//                .build();


        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(ImageRequest.fromUri(muri))
//                .setOldController(simpleDraweeView.getController())
                .build();
        simpleDraweeView.setController(controller);


    }



}
