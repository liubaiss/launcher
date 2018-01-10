package com.pbtd.tv.launcher.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSubscriber;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.pbtd.tv.launcher.R;
import com.pbtd.tv.launcher.bean.PageItemEntity;
import com.pbtd.tv.launcher.constant.AppConstant;
import com.pbtd.tv.launcher.constant.TagType;
import com.pbtd.tv.launcher.utils.BitmapUtil;
import com.pbtd.tv.launcher.utils.ResolutionUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * Created by zhouyong on 2017/3/18.
 */

public class PageLayout extends RelativeLayout {

    private static final String TAG ="PageLayout" ;
    private Context mContext;
    private ResolutionUtil resolutionUtil;
    private View mCurrentBiggerView;
    private ObjectAnimator mScaleAnimator;
    private ObjectAnimator mBiggerAnimator;
    private int index;
    private JumpCallback itemCallBack;
    private boolean isEnd = true;
    private List<PageItemView> itemViews;
    private View currentFocus;
    private HorizontalScrollView scrollView;
    private RelativeLayout contentLayout;
    private boolean isFirst = true;
    private int SHOW_TAB=520;
    private boolean isReady = false;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==SHOW_TAB){
                isReady = true;
                isFirst=false;
            }
        }
    };

    public PageLayout(Context context, PageItemEntity pageItemEntity, int index, JumpCallback albumItemCallBack) {
        super(context);
        this.mContext = context;
        resolutionUtil = new ResolutionUtil(context);
        this.index = index;
        this.itemCallBack = albumItemCallBack;
        initView(pageItemEntity);

//        this.setClipChildren(false);
//        this.setPadding(resolutionUtil.px2dp2pxWidth(5),0,0,0);
    }

    private void initView(PageItemEntity pageItemEntity) {

        createScrollView();
        int leftMenuWidth = 0;

        itemViews = new ArrayList<>();
        for(int i = 0; i < pageItemEntity.data.size(); i ++){
            PageItemEntity.DataBean data = pageItemEntity.data.get(i);
            PageItemView itemView = new PageItemView(mContext,data);
            itemView.setTag(R.id.tag_entity,data);
            itemView.setTag(R.id.tag_type, TagType.TYPE_PAGE_ITEM);
            itemView.setTag(R.id.tag_index, i);
//            itemView.setFocusable(true);
//            itemView.setFocusableInTouchMode(true);
            contentLayout.addView(itemView);

            itemViews.add(itemView);

            LayoutParams layoutParams = (LayoutParams) itemView.getLayoutParams();
//            layoutParams.width = resolutionUtil.px2dp2pxWidth(itemEntity.getWidth()) + AppConstant.BG_9_MARGIN_WIDTH;
//            layoutParams.height = resolutionUtil.px2dp2pxHeight(itemEntity.getHeight()) + AppConstant.BG_9_MARGIN_HEIGHT;
//            layoutParams.topMargin = resolutionUtil.px2dp2pxHeight(itemEntity.getTopMargin()) - AppConstant.BG_9_MARGIN_TOP;
//            layoutParams.leftMargin = resolutionUtil.px2dp2pxWidth(itemEntity.getLeftMargin()) - AppConstant.BG_9_MARGIN_LEFT;

            layoutParams.width = LayoutParams.WRAP_CONTENT;
            layoutParams.height = LayoutParams.WRAP_CONTENT;
            if(data.type == 3){
                layoutParams.topMargin = resolutionUtil.px2dp2pxHeight(data.topMargin);
                layoutParams.leftMargin = resolutionUtil.px2dp2pxWidth(data.leftMargin)-resolutionUtil.px2dp2pxWidth(55);
//                layoutParams.rightMargin = resolutionUtil.px2dp2pxWidth(100);
            }else {
                layoutParams.topMargin = resolutionUtil.px2dp2pxHeight(data.topMargin) - AppConstant.BG_9_MARGIN_TOP;
//                layoutParams.rightMargin = resolutionUtil.px2dp2pxWidth(100);
                layoutParams.leftMargin = resolutionUtil.px2dp2pxWidth(data.leftMargin)-resolutionUtil.px2dp2pxWidth(55)- AppConstant.BG_9_MARGIN_LEFT;
            }
            if (data.type==2){
                createLeftTab(data,itemView);
            }

            itemView.setLayoutParams(layoutParams);

            setItemOnFocus(itemView);
            setItemOnClick(itemView);

//            if (i==0)mCurrentBiggerView = itemView;
        }

//        itemViews.get(0).requestFocus();
//        itemViews.get(0).bringToFront();
        this.invalidate();
//        currentFocus =  itemViews.get(0);
    }

    private void createScrollView() {
        scrollView = new HorizontalScrollView(mContext);
        addView(scrollView);

        LayoutParams layoutParams = (LayoutParams) scrollView.getLayoutParams();
        layoutParams.width = LayoutParams.MATCH_PARENT;
        layoutParams.height = LayoutParams.MATCH_PARENT;
        layoutParams.leftMargin = resolutionUtil.px2dp2pxWidth(55);
        layoutParams.rightMargin = resolutionUtil.px2dp2pxWidth(55);
//        layoutParams.leftMargin = layoutParams
        scrollView.setLayoutParams(layoutParams);
        scrollView.setHorizontalScrollBarEnabled(false);

        createContentLayout();

    }

    private void createContentLayout() {
        contentLayout = new RelativeLayout(mContext);
        scrollView.addView(contentLayout);
//
        HorizontalScrollView.LayoutParams layoutParams = (HorizontalScrollView.LayoutParams) contentLayout.getLayoutParams();
        layoutParams.width = LayoutParams.WRAP_CONTENT;
        layoutParams.height = LayoutParams.WRAP_CONTENT;
//        layoutParams.rightMargin = resolutionUtil.px2dp2pxWidth(200);
        contentLayout.setLayoutParams(layoutParams);
//        contentLayout.setPadding(resolutionUtil.px2dp2pxWidth(5),0,0,0);

    }
    private Map<Integer,Bitmap[]> drawablelist = new HashMap<>();
    /**
     * 创建左边的搜索导航栏
     */
    private void createLeftTab(final PageItemEntity.DataBean   data, final PageItemView itemView) {
        drawablelist.put(data.getId(),null);
        final Bitmap[] itemDrawable = new Bitmap[2];

        for (int i =0;i<2;i++){
            final int finalI = i;
            final int finalI1 = i;
            loadToBitmap(data.imageUrl.get(i), new BaseBitmapDataSubscriber() {
                @Override
                protected void onNewResultImpl(Bitmap bitmap) {
                    Bitmap bitmap1 = BitmapUtil.byteArray2Bitmap(BitmapUtil.bitmap2ByteArray(bitmap));
                    Drawable bitmapDrawable = new BitmapDrawable(bitmap1);

                    itemDrawable[finalI1] = bitmap1;
                    if (itemDrawable[0]!=null&&itemDrawable[1]!=null){
                        drawablelist.put(data.getId(),itemDrawable);
                        /*RelativeLayout base = (RelativeLayout) itemView.getChildAt(0);
                        ImageView childAt = (ImageView) base.getChildAt(0);
                        childAt.setImageBitmap(itemDrawable[0]);
                        childAt.setImageBitmap(itemDrawable[1]);
                        *//*childAt.setBackground(itemDrawable[0]);
                        childAt.setBackground(itemDrawable[1]);*//*
                        childAt.setBackground(null);*/
                      //  Log.d(TAG,"获取下载的图片"+data.getId());
                    }

//                  Log.d(TAG,"发了一条信息"+drawablelist.size());
                 //   handler.sendEmptyMessageDelayed(SHOW_TAB, 0);

                }
                @Override
                protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {

                }
            });
        }
    }

    private void setItemOnFocus(final PageItemView itemView) {

        itemView.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                PageItemEntity.DataBean data = (PageItemEntity.DataBean) itemView.getTag(R.id.tag_entity);

               /* if (isFirst){
                    if (data.type ==2) {
                        RelativeLayout base = (RelativeLayout) itemView.getChildAt(0);
                        SimpleDraweeView childAt = (SimpleDraweeView) base.getChildAt(0);
                        childAt.setImageURI(Uri.parse(data.imageUrl.get(1)));
                        childAt.setImageURI(Uri.parse(data.imageUrl.get(0)));
                        childAt.setImageURI(Uri.parse(null));
                        isFirst = false;
                    }
                }*/


                if (hasFocus) {
                    currentFocus = view;
                    if (data.isShowTitle == 1) {
                        itemView.showIndicator4();
                    }
                    if (data.type == 0 || data.type == 4) {
                        scaleBigAnimation(view);
                    }

                    if (data.type == 2) {
                        RelativeLayout base = (RelativeLayout) itemView.getChildAt(0);
                        ImageView childAt = (ImageView) base.getChildAt(0);

                        Bitmap[]drawables=drawablelist.get(data.id);
                      //  childAt.setImageURI(Uri.parse(data.imageUrl.get(1)));
                        //childAt.setBackground(drawablelist.get(1));
                        scaleBigAnimation(view);
                        if (drawables!=null){
                            //Log.d(TAG, "获取焦点了"+data.id);
                            childAt.setImageURI(null);
                            childAt.setImageBitmap(drawables[1]);
                            scaleBigAnimation(view);
                        }


                }



                } else {
                    itemView.hideIndicator4();

                    if (data.type == 0 || data.type == 4) {
                        scaleSmallAnimation(view);
                    }

                    if (data.type == 2) {
                            RelativeLayout base = (RelativeLayout) itemView.getChildAt(0);
                            ImageView childAt = (ImageView) base.getChildAt(0);
                            Bitmap[]drawables=drawablelist.get(data.id);
                           // Log.d(TAG, "失去获取焦点了"+(drawablelist.get(data.id)!=null));
                            if (drawables!=null){
                                Log.d(TAG, "失去获取焦点了"+data.id);
                                childAt.setImageURI(null);
                                childAt.setImageBitmap(drawables[0]);

                            }
                             //childAt.setImageURI(Uri.parse(data.imageUrl.get(0)));
                             scaleSmallAnimation(view);
                            //childAt.setBackground(drawablelist.get(0));


                    }

                }
            }
        });
    }

        /**
         * 加载图片成bitmap。
         *
         * @param imageUrl 图片地址。
         */
    public  void loadToBitmap(String imageUrl, BaseBitmapDataSubscriber mDataSubscriber) {
        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(imageUrl))
                .setProgressiveRenderingEnabled(true)
                .build();

        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline.fetchDecodedImage
                (imageRequest, getContext());
        dataSource.subscribe(mDataSubscriber, CallerThreadExecutor.getInstance());
        DataSubscriber dataSubscriber =
                new BaseDataSubscriber<CloseableReference<CloseableImage>>() {
                    @Override
                    public void onNewResultImpl(
                            DataSource<CloseableReference<CloseableImage>> dataSource) {

                        if (!dataSource.isFinished()) {
                            //  FLog.v("Not yet finished - this is just another progressive scan.");
                        }

                        CloseableReference<CloseableImage> imageReference = dataSource.getResult();

                        if (imageReference != null) {
                            try {
                                CloseableImage image = imageReference.get();

                            } finally {
                                imageReference.close();
                            }
                        }
                    }
                    @Override
                    public void onFailureImpl(DataSource dataSource) {
                    }
                };

        Executor executor = new Executor() {
            @Override
            public void execute(@NonNull Runnable command) {

            }
        };
        dataSource.subscribe(dataSubscriber, executor);
    }
//
//        itemView.setLayoutAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                isEnd = false;
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                isEnd = true;
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });


    public void setItemOnClick(final PageItemView itemView) {
        itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                PageItemEntity.DataBean data = (PageItemEntity.DataBean) itemView.getTag(R.id.tag_entity);

                itemCallBack.onClick(data);

            }
        });
    }

    private void scaleBigAnimation(View v) {

        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f,1.07f, 1.0f, 1.07f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(300);
        scaleAnimation.setFillAfter(true);
        v.startAnimation(scaleAnimation);
        v.bringToFront();
    }

    private void scaleSmallAnimation(View v) {

        ScaleAnimation scaleAnimation = new ScaleAnimation(1.07f,1.0f, 1.07f, 1.0f,Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(0);
        scaleAnimation.setFillAfter(true);
        v.startAnimation(scaleAnimation);
        v.bringToFront();
    }

    public void requestLeft(){
        int index = (int) currentFocus.getTag(R.id.tag_index);
        if(index > 0){
            itemViews.get(index - 1).requestFocus();
        }
    }

    public void requestRight(){
        int index = (int) currentFocus.getTag(R.id.tag_index);
        if(index < itemViews.size() - 1){
            itemViews.get(index + 1).requestFocus();
        }
    }

    public List<PageItemView> getItemViews() {
        return itemViews;
    }
}
