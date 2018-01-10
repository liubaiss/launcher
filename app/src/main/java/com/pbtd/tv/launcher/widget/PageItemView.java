package com.pbtd.tv.launcher.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSubscriber;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.pbtd.tv.launcher.R;
import com.pbtd.tv.launcher.bean.PageItemEntity;
import com.pbtd.tv.launcher.utils.ImageUtil;
import com.pbtd.tv.launcher.utils.ResolutionUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;


/**
 * Created by zhouyong on 2017/3/18.
 */

public class PageItemView extends RelativeLayout {
    public static int PLAY_INDEX = -1;
    private static final long TIME = 5000;
    private static final int SHOW_NEXT_PAGE = 0;

    public static VideoView videoView;
    private RelativeLayout titleLayout;
    private ResolutionUtil resolutionUtil;
    private Context mContext;
    private RelativeLayout baseLayout;
    private CustomMarqueeTextView titleDetail;
    private CustomMarqueeTextView titleText;

    TextView timeText;
    AutoScrollViewPager mViewPager;
    IndicatorView indicatorView;
    ViewPager mviewpager1;
    View indicator4;
    RelativeLayout baseindicator4;
    LinearLayout ll_dot;
    List<ImageView> dotlist;
    private int oldPosition = 0;
    private int currentItem;

    public PageItemView(Context context, PageItemEntity.DataBean data) {
        super(context);
        this.mContext = context;
        resolutionUtil = new ResolutionUtil(mContext);
        titleLayout = new RelativeLayout(mContext);
        timeText = new TextView(mContext);
        titleDetail = new CustomMarqueeTextView(mContext);
        titleText = new CustomMarqueeTextView(mContext);

        initView(data);
    }

    private void initView(PageItemEntity.DataBean data) {

        createBaseLayout(data);

        switch (data.type) {
            case 0:
                setFocusable(true);
                createImageLayout0(data);
                this.setBackgroundResource(R.drawable.album_bg_selector1);
                break;
            case 1:
                setFocusable(true);
                createVideoLayout(data);
                this.setBackgroundResource(R.drawable.album_bg_selector1);
                break;
            case 3:
                createImageLayout0(data);
                setFocusable(false);
                this.setBackgroundColor(Color.TRANSPARENT);
                break;
            case 2:
                setFocusable(true);
                createImageLayout(data);
                this.setBackgroundResource(R.drawable.album_bg_selector1);
                break;
            case 4:
                setFocusable(true);
                createViewpager1(data);
//                createViewpager(data);
                this.setBackgroundResource(R.drawable.album_bg_selector1);
                break;
            default:
                break;
        }
        createTitleLayout(data);
        if (data.type ==4){
            createIndicator4(data);
        }

    }


    private void createBaseLayout(PageItemEntity.DataBean data) {
        baseLayout = new RelativeLayout(mContext);
        addView(baseLayout);
        baseLayout.requestFocus();
        LayoutParams layoutParams = (LayoutParams) baseLayout.getLayoutParams();
        layoutParams.width = resolutionUtil.px2dp2pxWidth(data.width);
//        layoutParams.leftMargin = resolutionUtil.px2dp2pxWidth(data.leftMargin);
//        layoutParams.leftMargin = resolutionUtil.px2dp2pxWidth(-100);
        layoutParams.height = resolutionUtil.px2dp2pxHeight(data.height);
        baseLayout.setLayoutParams(layoutParams);
    }

    /**
     * 隐藏标题栏
     */

    public void hideIndicator4() {
        titleLayout.setVisibility(INVISIBLE);

    }

    /**
     * 显示标题栏
     */
    public void showIndicator4() {
        titleLayout.setVisibility(VISIBLE);

    }


    /**
     * 创建标题栏
     *
     * @param data
     */
    public void createTitleLayout(PageItemEntity.DataBean data) {

//        inflate(mContext,)
//        titleLayout.setOrientation(LinearLayout.HORIZONTAL);
//        titleLayout.setGravity(Gravity.CENTER_VERTICAL);
//        setTitleLayoutDefault();
        baseLayout.addView(titleLayout);
        titleLayout.setVisibility(INVISIBLE);

        LayoutParams layoutParams = (LayoutParams) titleLayout.getLayoutParams();
        layoutParams.height = resolutionUtil.px2dp2pxHeight(85);
        layoutParams.width = resolutionUtil.px2dp2pxWidth(data.width);

        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        titleLayout.setLayoutParams(layoutParams);
        titleLayout.setBackgroundColor(getResources().getColor(R.color.page_item_title_line));

        createTitleDetail(data);
        createTitleText(data);

    }

    private void createIndicator3() {
        View inflate = View.inflate(mContext, R.layout.indicator_view, null);
        titleLayout.addView(inflate);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) inflate.getLayoutParams();
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.width = LayoutParams.WRAP_CONTENT;
        layoutParams.height = LayoutParams.WRAP_CONTENT;
        indicatorView = (IndicatorView) inflate.findViewById(R.id.id_indicator);
        indicatorView.setViewPager(mviewpager1);
    }

    /**
     * 三方控件IndicatorView
     */
    public void createIndicator1() {
        baseindicator4 = new RelativeLayout(mContext);
        baseLayout.addView(baseindicator4);
        RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams) baseindicator4.getLayoutParams();
//        layoutParams1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParams1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams1.width = LayoutParams.MATCH_PARENT;
        layoutParams1.height = LayoutParams.MATCH_PARENT;
        baseindicator4.setLayoutParams(layoutParams1);


        indicator4 = View.inflate(mContext, R.layout.indicator_view, null);
//        baseindicator4.setBackgroundResource(R.mipmap.pic_gradient2);
        baseLayout.addView(indicator4);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) indicator4.getLayoutParams();
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.width = LayoutParams.WRAP_CONTENT;
        layoutParams.height = LayoutParams.WRAP_CONTENT;
//        layoutParams.leftMargin = resolutionUtil.px2dp2pxWidth(650);
//        layoutParams.topMargin = resolutionUtil.px2dp2pxHeight(393);
//        layoutParams.width = resolutionUtil.px2dp2pxWidth(300);
//        layoutParams.height =resolutionUtil.px2dp2pxHeight(85);
//
        indicatorView = (IndicatorView) indicator4.findViewById(R.id.id_indicator);
        indicatorView.setViewPager(mviewpager1);
//        invalidate();
    }

    /**
     * 手写指示器
     * @param data
     */
    public void createIndicator4(PageItemEntity.DataBean data) {
        ll_dot = new LinearLayout(mContext);
//        ll_dot.setBackgroundResource(R.drawable.pic_gradient2);
        dotlist  = new ArrayList<>();
        ll_dot.setGravity(LinearLayout.HORIZONTAL);
//        ll_dot.setBackgroundColor(Color.RED);
        baseLayout.addView(ll_dot);

        List<String> list = data.imageUrl;
        for (int i = 0;i <list.size();i++ ){
            ImageView iv = new ImageView(mContext);
            iv.setBackgroundResource(R.drawable.dot01);
            dotlist.add(iv);
            ll_dot.addView(iv);
        }
        dotlist.get(0).setBackgroundResource(R.drawable.dot02);
        for (int i  = 0;i<dotlist.size();i++){
            View ll_dotChildAt = ll_dot.getChildAt(i);
            LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) ll_dotChildAt.getLayoutParams();
            layoutParams1.width = resolutionUtil.px2dp2pxWidth(32);
            layoutParams1.height = resolutionUtil.px2dp2pxHeight(58);
            layoutParams1.rightMargin = resolutionUtil.px2dp2pxWidth(5);
            ll_dotChildAt.setLayoutParams(layoutParams1);

        }

        RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams) ll_dot.getLayoutParams();
        layoutParams1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParams1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams1.width = LayoutParams.WRAP_CONTENT;
        layoutParams1.height =LayoutParams.WRAP_CONTENT;
        ll_dot.setLayoutParams(layoutParams1);

    }



        private void createTitleDetail(PageItemEntity.DataBean data) {
        titleDetail.setSingleLine();
        titleDetail.setTextColor(getResources().getColor(R.color.splashtext));
        titleDetail.setTextSize(resolutionUtil.px2sp2px(24));
        titleDetail.setText(data.titleDetail);
        titleDetail.setGravity(Gravity.TOP);
        titleLayout.addView(titleDetail);

        LayoutParams layoutParams = (LayoutParams) titleDetail.getLayoutParams();
        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.leftMargin = resolutionUtil.px2dp2pxWidth(15);
        layoutParams.topMargin = resolutionUtil.px2dp2pxHeight(45);
        layoutParams.bottomMargin = resolutionUtil.px2dp2pxHeight(10);
        titleDetail.setLayoutParams(layoutParams);
        titleDetail.setGravity(Gravity.TOP);
    }

    private void createTitleText(PageItemEntity.DataBean data) {
        titleText.setTextColor(Color.WHITE);
        titleText.setTextSize(resolutionUtil.px2sp2px(36));
        titleText.setText(data.titleName);
        titleText.setGravity(Gravity.TOP);
        titleLayout.addView(titleText);

        LayoutParams layoutParams = (LayoutParams) titleText.getLayoutParams();
        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.leftMargin = resolutionUtil.px2dp2pxWidth(15);
        layoutParams.topMargin = resolutionUtil.px2dp2pxHeight(2);
        titleText.setLayoutParams(layoutParams);
    }

    private void createVideoLayout(PageItemEntity.DataBean data) {

        videoView = new CustomVideoView(mContext);
        baseLayout.setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);

        videoView.setFocusable(false);
        baseLayout.addView(videoView);

        LayoutParams layoutParams = (LayoutParams) videoView.getLayoutParams();
        layoutParams.width = resolutionUtil.px2dp2pxWidth(data.width);
        layoutParams.height = resolutionUtil.px2dp2pxHeight(data.height);
        videoView.setLayoutParams(layoutParams);

        if (data.vedioPlayUrl!=null){
            videoView.setVideoURI(Uri.parse(data.vedioPlayUrl));
            videoView.start();
        }


        RelativeLayout roundLayout = new RelativeLayout(mContext);
        roundLayout.setFocusable(false);
        roundLayout.setBackgroundResource(R.mipmap.round_bg);
        baseLayout.addView(roundLayout);

        layoutParams = (LayoutParams) roundLayout.getLayoutParams();
        layoutParams.width = resolutionUtil.px2dp2pxWidth(data.width);
        layoutParams.height = resolutionUtil.px2dp2pxHeight(data.height);
        roundLayout.setLayoutParams(layoutParams);
    }

    /**
     * 三方AutoScrollViewPager
     *
     * @param data
     */
    private void createViewpager(PageItemEntity.DataBean data) {

        mViewPager = new AutoScrollViewPager(mContext);
        baseLayout.addView(mViewPager);
        baseLayout.setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
//        mViewPager.setFocusable(false);
//        mViewPager.getDescendantFocusability();
        mViewPager.setFocusableInTouchMode(false);
        LayoutParams layoutParams = (LayoutParams) mViewPager.getLayoutParams();
        layoutParams.width = resolutionUtil.px2dp2pxWidth(data.width);
        layoutParams.height = resolutionUtil.px2dp2pxHeight(data.height);
        mViewPager.setLayoutParams(layoutParams);
        if (data.imageUrl.size() > 0) {
            mViewPager.setAdapter(new BaseViewPagerAdapter<String>(mContext, data.imageUrl, listener) {
                @Override
                public void loadImage(SimpleDraweeView view, int position, String url) {
                    view.setImageURI(url);
                }
            });
        }
    }

    /**
     * 原生ViewPager
     *
     * @param data
     */
    private void createViewpager1(PageItemEntity.DataBean data) {
        mviewpager1 = new ViewPager(mContext);
//        mviewpager1.setBackgroundResource(R.mipmap.viewpagerbg);
        baseLayout.addView(mviewpager1);
        baseLayout.setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
//        mviewpager1.setFocusableInTouchMode(false);
        LayoutParams layoutParams = (LayoutParams) mviewpager1.getLayoutParams();
        layoutParams.width = resolutionUtil.px2dp2pxWidth(data.width);
        layoutParams.height = resolutionUtil.px2dp2pxHeight(data.height);
        mviewpager1.setLayoutParams(layoutParams);
//        createIndicator4(data);
        showPic(data);

    }


    private void showPic(final PageItemEntity.DataBean data) {

        mviewpager1.setAdapter(new ViewPagerAdapter(mContext, data.imageUrl));
        handler.sendEmptyMessageDelayed(SHOW_NEXT_PAGE, TIME);
        mviewpager1.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setIndicator(data,position);
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void setIndicator(PageItemEntity.DataBean data,int position) {
        int len = data.imageUrl.size();
        position %= len;
        for (int i = 0; i < len; i++) {
            if (position != i) {
                dotlist.get(i).setBackgroundResource(R.drawable.dot01);
            }else
                dotlist.get(i).setBackgroundResource(R.drawable.dot02);


        }
    }

    private void showNextPage() {
        int currentItem = mviewpager1.getCurrentItem();
        mviewpager1.setCurrentItem(currentItem + 1);

        handler.sendEmptyMessageDelayed(SHOW_NEXT_PAGE, TIME);

    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_NEXT_PAGE:
                    showNextPage();
                    invalidate();
                    break;

                default:
                    break;
            }
        }
    };
    //定义点击事件
    private BaseViewPagerAdapter.OnAutoViewPagerItemClickListener listener = new BaseViewPagerAdapter.OnAutoViewPagerItemClickListener<String>() {

        @Override
        public void onItemClick(int position, String url) {

            Toast.makeText(mContext, "heh", Toast.LENGTH_SHORT).show();

        }
    };

    private void createImageLayout(PageItemEntity.DataBean data) {
        final Bitmap[] mbitmap = new Bitmap[2];
        SimpleDraweeView imageView = new SimpleDraweeView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        baseLayout.addView(imageView);
        LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
        layoutParams.width = resolutionUtil.px2dp2pxWidth(data.width);
        layoutParams.height = resolutionUtil.px2dp2pxHeight(data.height);
        imageView.setLayoutParams(layoutParams);
//        if (data.imageUrl!=null){
//            if (!TextUtils.isEmpty(data.imageUrl.get(0))) {
//                Uri uri = Uri.parse(data.imageUrl.get(0));
//                imageView.setImageURI(uri);
//            } else {
//                //加载假数据
//                ImageUtil.setImageStyleRound2(mContext, imageView, R.mipmap.album_default);
//            }
//        }

        if (data.imageUrl!=null){
            if (!TextUtils.isEmpty(data.imageUrl.get(0))) {

                Uri uri = Uri.parse(data.imageUrl.get(0));
                imageView.setImageURI(uri);
//                ImageUtil.setImageStyleRound2(mContext,imageView, R.mipmap.album_default,uri);

//                for (int k = 0;k<data.imageUrl.size();k++){
//                    final int finalK = k;
//
//                    loadToBitmap(data.imageUrl.get(k), new BaseBitmapDataSubscriber() {
//                        @Override
//                        protected void onNewResultImpl(Bitmap bitmap) {
//                            Bitmap bitmap1 = BitmapUtil.byteArray2Bitmap(BitmapUtil.bitmap2ByteArray(bitmap));
//
//                        }
//
//                        @Override
//                        protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
//
//                        }
//                    });
//
//                }
            }
        }





//        //如果没有加载到图片则加载默认图片
//        if (data.type == 3) {
//            ImageUtil.setImageStyleRound3(mContext, imageView, R.mipmap.album_default);
//        } else {
//////            imageView.setFocusable(true);
//////imageView.setFocusableInTouchMode(true);
//            ImageUtil.setImageStyleRound2(mContext, imageView, R.mipmap.album_default);
//        }
    }

    private void createImageLayout0(PageItemEntity.DataBean data) {
        SimpleDraweeView imageView = new SimpleDraweeView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        baseLayout.addView(imageView);
        LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
        layoutParams.width = resolutionUtil.px2dp2pxWidth(data.width);
        layoutParams.height = resolutionUtil.px2dp2pxHeight(data.height);
        imageView.setLayoutParams(layoutParams);
//        if (data.imageUrl!=null){
//            if (!TextUtils.isEmpty(data.imageUrl.get(0))) {
//                Uri uri = Uri.parse(data.imageUrl.get(0));
//                imageView.setImageURI(uri);
//            } else {
//                //加载假数据
//                ImageUtil.setImageStyleRound2(mContext, imageView, R.mipmap.album_default);
//            }
//        }

        if (data.imageUrl!=null){
            if (!TextUtils.isEmpty(data.imageUrl.get(0))) {
                Uri uri = Uri.parse(data.imageUrl.get(0));
//                imageView.setImageURI(uri);
                ImageUtil.setImageStyleRound2(mContext,imageView, R.mipmap.album_default,uri);
            }
        }





//        //如果没有加载到图片则加载默认图片
//        if (data.type == 3) {
//            ImageUtil.setImageStyleRound3(mContext, imageView, R.mipmap.album_default);
//        } else {
//////            imageView.setFocusable(true);
//////imageView.setFocusableInTouchMode(true);
//            ImageUtil.setImageStyleRound2(mContext, imageView, R.mipmap.album_default);
//        }
    }


    public void setTitleLayoutFocus() {
        if (titleLayout != null) {
            titleLayout.setBackgroundColor(getResources().getColor(R.color.page_item_title_line));

        }

        if (titleText != null) {
            titleText.setSelected(true);
        }

    }

    public static File getCachedImageOnDisk(CacheKey cacheKey) {
        File localFile = null;
        if (cacheKey != null) {
            if(ImagePipelineFactory.getInstance().getMainDiskStorageCache().hasKey(cacheKey)) {
                BinaryResource resource = ImagePipelineFactory.getInstance().getMainDiskStorageCache().getResource(cacheKey);
                localFile = ((FileBinaryResource) resource).getFile();
            } else if (ImagePipelineFactory.getInstance().getSmallImageDiskStorageCache().hasKey(cacheKey)) {
                BinaryResource resource = ImagePipelineFactory.getInstance().getSmallImageDiskStorageCache().getResource(cacheKey);
                localFile = ((FileBinaryResource) resource).getFile();
            }
        }
        return localFile;
    }

    public void setTitleLayoutDefault() {
        if (titleLayout != null) {
//            titleLayout.setBackgroundResource(R.mipmap.title_layout_bg_default);
            titleLayout.setBackgroundColor(getResources().getColor(R.color.transparent));


        }

        if (titleText != null) {
            titleText.setSelected(false);
        }
    }

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


}
