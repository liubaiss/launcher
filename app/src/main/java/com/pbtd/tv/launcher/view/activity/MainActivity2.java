package com.pbtd.tv.launcher.view.activity;

//import android.app.FragmentManager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.format.Time;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSubscriber;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.google.gson.Gson;
import com.pbtd.tv.launcher.R;
import com.pbtd.tv.launcher.bean.AuthEntity;
import com.pbtd.tv.launcher.bean.LogoEntity;
import com.pbtd.tv.launcher.bean.PageItemEntity;
import com.pbtd.tv.launcher.bean.SplashUrlEntity;
import com.pbtd.tv.launcher.bean.TabEntity;
import com.pbtd.tv.launcher.constant.Constant;
import com.pbtd.tv.launcher.constant.TagType;
import com.pbtd.tv.launcher.utils.AppStartUtil;
import com.pbtd.tv.launcher.utils.BitmapUtil;
import com.pbtd.tv.launcher.utils.LogUtil;
import com.pbtd.tv.launcher.utils.MacUtil;
import com.pbtd.tv.launcher.utils.NetworkUtil;
import com.pbtd.tv.launcher.view.fragment.PageFragment;
import com.pbtd.tv.launcher.volley.VolleyController;
import com.pbtd.tv.launcher.widget.AppUtils;
import com.pbtd.tv.launcher.widget.AutoViewPager;
import com.pbtd.tv.launcher.widget.CustomVideoView;
import com.pbtd.tv.launcher.widget.PageItemView;
import com.pbtd.tv.launcher.widget.PageLayout;
import com.pbtd.tv.launcher.widget.UpdateChecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

import static android.view.View.FOCUS_DOWN;
import static android.view.View.FOCUS_LEFT;
import static android.view.View.FOCUS_RIGHT;
import static com.pbtd.tv.launcher.R.color.timecolor;
import static java.security.AccessController.getContext;

//import android.support.v4.app.FragmentManager;

/**
 * launcher界面
 */
public class MainActivity2 extends BaseActivity {
    private static final String TAG = "MainActivity2";
    private Context mcontext;
    private TextView timeText;
    private ImageView wifiImg;
    private ArrayList<Fragment> fragments;
    private static final int WHAT_TOP_STATUS = 11;
    private ViewPager mViewPager;
    private List<PageItemEntity> pageItemEntityList;
    private RelativeLayout baseLayout;
    private LinearLayout tabs_LinearLayout;
    private HorizontalScrollView horizontalScrollView;
    MyFragmentPagerAdapter myFragmentPagerAdapter;
    int currentselected = 0;
    AudioManager mAudioManager;
    int iconsize;
    List<TabEntity.DataBean> data;
    List<Integer> idList;
    List<String> dataUrlList;
    Map<Integer, String> pagamap;

    private static int viewpagerIndex;
    private static final int SHOW_NEXT_PAGE = 0;
    private static final int TO_ACTIVITY = 1;
    private static final int CHANGETIME = 4;
    private static final long TIME = 5000;
    private static final int WHAT_TIME_STATUS = 2;
    private int timeCount;
    private int tab = 0;
    private static final int TAB = 182883;

    ArrayList<String> picUriList;

    Uri vedioUri;
    String videoString;
    FrameLayout base_splash;
    private SplashUrlEntity mSplashUrlEntity;
    private List<SimpleDraweeView> mPagesIV;
    private CustomVideoView customVideoView;
    ViewPager viewPager;
    TextView timetext;
     LinearLayout ll_timetext;
    View view;
    TextView ad;
    String mac;
    TimeThread timeThread;
    String bussId = String.valueOf(1);
    List<Drawable> selecorlist;


    SimpleDraweeView viewBg;
    SimpleDraweeView viewLogo;
    private String groupId;
    private String projectId = "launcher";
    private int voice;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case WHAT_TOP_STATUS:
//                    LogUtil.d("voice",voice+"");
                    timeText.setText(getCurrentTime());
//                    timeText.invalidate();
                    setNetworkStatus();

                    ll_timetext.setVisibility(View.VISIBLE);

                    if (timeCount > 0) {

                        if (timeCount < 10) {
                            timetext.setText(0 + "" + timeCount);
                        } else
                            timetext.setText(timeCount + "");
                    }
                    if (timeCount == 0) {
                        base_splash.setVisibility(View.GONE);
                        baseLayout.setVisibility(View.VISIBLE);
                    }

                    timeCount -= 1;
                    break;
                case SHOW_NEXT_PAGE:
                    showNextPage();
                    break;

                case TO_ACTIVITY:
                    base_splash.setVisibility(View.GONE);
                    baseLayout.setVisibility(View.VISIBLE);
                    break;
                case TAB:
                    showTab();
                default:
                    break;
            }
            return false;
        }
    }
    );
    private boolean run ;

    /**
     * 创建底部导航栏
     */
    private void showTab() {
        for (int i = 0; i < drawablelist.size(); i++) {
            View v = LayoutInflater.from(this).inflate(R.layout.view2, null);
            tv = (ImageView) v;
            tv.setFocusable(true);
            tv.setTag(R.id.tag_type, TagType.TYPE_NUM);
            tv.setTag(R.id.tag_index, i);
            tv.setBackground(drawablelist.get(i));
            final TabEntity.DataBean dataBean = data.get(i);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String className = dataBean.getClassName();
                    String packageName = dataBean.getPackageName();
                    try {
                        AppStartUtil.startApp(MainActivity2.this, packageName, className, dataBean);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            v.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    int count = tabs_LinearLayout.getChildCount();
                    for (int i = 0; i < count; i++) {
                        View cv = tabs_LinearLayout.getChildAt(i);
                        if (v == cv) {
                            if (hasFocus) {
//                                getPageData();
                                mViewPager.setCurrentItem(i);
                                break;
                            }
                        }
                    }
                }
            });

            tabs_LinearLayout.addView(v, i);
            tabs_LinearLayout.getChildAt(0).requestFocus();
            tabs_LinearLayout.getChildAt(0).setSelected(true);

        }
        for (int j = 0; j < tabs_LinearLayout.getChildCount(); j++) {
            ImageView childAt = (ImageView) tabs_LinearLayout.getChildAt(j);
//            childAt.setBackground(selecorlist.get(j));
            LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            layoutParams1.width = resolutionUtil.px2dp2pxWidth(100);
            layoutParams1.height = resolutionUtil.px2dp2pxHeight(132);
            layoutParams1.rightMargin = resolutionUtil.px2dp2pxWidth(146);
//            layoutParams1.leftMargin = resolutionUtil.px2dp2pxWidth(73);
            childAt.setLayoutParams(layoutParams1);
//            childAt.setImageResource(icons[j]);
        }
    }

    private ImageView tv;
    private List<Drawable> drawablelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        getWindow().setBackgroundDrawable(null);
//        setTheme(R.style.MyTheme1);
        super.onCreate(savedInstanceState);

        Toast.makeText(this,"当前版本是"+ AppUtils.getVersionName(this),Toast.LENGTH_LONG).show();
        UpdateChecker.checkForDialog(MainActivity2.this);

        mcontext = getApplication();
        selecorlist = new ArrayList<>();

        setContentView(R.layout.activity_main2);

        base_splash = (FrameLayout) findViewById(R.id.base_splash);
        viewPager = (ViewPager) findViewById(R.id.pic);
        ll_timetext = (LinearLayout) findViewById(R.id.ll_time);
        view = findViewById(R.id.v);
        timetext = (TextView) findViewById(R.id.time);
        ad = (TextView) findViewById(R.id.ad);
        mac = MacUtil.getLocalEthernetMacAddress();
        idList = new ArrayList<>();
        dataUrlList = new ArrayList<>();

        mAudioManager = (AudioManager) MainActivity2.this.getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
        baseLayout = (RelativeLayout) findViewById(R.id.baseLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mac = MacUtil.getLocalEthernetMacAddress();
        pageItemEntityList = new ArrayList<>();
        fragments = new ArrayList<>();
        idList = new ArrayList<>();
        dataUrlList = new ArrayList<>();
        pagamap = new HashMap<>();
        voice = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        //logo和背景
//        createProgress();

        getAuth();

//        getSplashEntity();
//        getLogoEntity();
//        //底部导航栏数据
//        getTabEntity();
//        createTop();
        run = true;

    }

    public void getSplashEntity() {
        try {
            String splashUrl = Constant.getSPLASH(projectId, groupId);
            requestGetUrl(splashUrl, new VolleyController.VolleyCallback() {
                @Override
                public void onResponse(String response) {

                    SplashUrlEntity splashUrlEntity = gson.fromJson(response, SplashUrlEntity.class);
                    videoString = splashUrlEntity.getVideoURL();
                    picUriList = (ArrayList<String>) splashUrlEntity.getImageURL();
                    timeCount = splashUrlEntity.getTimeCount();
                    if (timeCount != 0) {
//                        ll_timetext.setVisibility(View.VISIBLE);
                    }
                    isSplashVideo();

                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

        }


    }

    /**
     * 判断splash界面是否是video还是pic
     *
     * @return
     */
    public void isSplashVideo() {
        if (videoString != null && !videoString.equals("")) {
            customVideoView = new CustomVideoView(this);
            vedioUri = Uri.parse(videoString);
            base_splash.addView(customVideoView);
            customVideoView.playVideo(vedioUri);
            customVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
//                    startActivity(new Intent(SplashActivity.this, MainActivity2.class));
//                    base_splash.setVisibility(View.GONE);
                }
            });
        }

        if (picUriList != null && picUriList.size() > 0) {
            mPagesIV = new ArrayList<>();
            int len = picUriList.size();
            for (int i = 0; i < len; i++) {
                SimpleDraweeView view = new SimpleDraweeView(this);
//                view.setImageURI(picUriList.get(i));
                Uri uri = Uri.parse(picUriList.get(i));
//                ImageUtil.setfrescoimage(picUriList.get(i), view, mcontext);
                setfrescoimage(picUriList.get(i), view, mcontext);

                mPagesIV.add(view);
                mPagesIV.size();
//                if (mPagesIV.size() == len) {
//                    timeThread = new TimeThread();
//                    timeThread.start();
//                }
            }

            showPic();
        }

    }

    private void showPic() {
//        progressLoading.setVisibility(View.GONE);
        viewPager.setAdapter(new BannerAdapter(mPagesIV));
//        viewPager.setAdapter(new BannerAdapter(imageResIds));

        viewPager.setCurrentItem(0);
        handler.sendEmptyMessageDelayed(SHOW_NEXT_PAGE, TIME);
        createTime();
//        new TimeThread().start();
//        timeThread = new TimeThread();
//        timeThread.start();
    }

    private void showNextPage() {
        int currentItem = viewPager.getCurrentItem();
        viewPager.setCurrentItem(currentItem + 1);


        if (currentItem == picUriList.size() - 1) {
//            handler.sendEmptyMessageDelayed(TO_ACTIVITY, 0);
        } else {
            handler.sendEmptyMessageDelayed(SHOW_NEXT_PAGE, TIME);
        }

    }

    /**
     * splash倒计时
     */
    private void createTime() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) ll_timetext.getLayoutParams();
        layoutParams.topMargin = resolutionUtil.px2dp2pxHeight(30);
        layoutParams.leftMargin = resolutionUtil.px2dp2pxWidth(1700);
        layoutParams.height = resolutionUtil.px2dp2pxHeight(50);
        layoutParams.width = resolutionUtil.px2dp2pxWidth(160);
        ll_timetext.setLayoutParams(layoutParams);
        ll_timetext.setBackgroundResource(R.drawable.splashtimecount);
        timetext.setTextSize(resolutionUtil.px2sp2px(30));
//        if (timeCount < 10) {
//            timetext.setText(0 + "" + timeCount);
//        } else
//            timetext.setText(timeCount + "");
        ad.setTextSize(resolutionUtil.px2sp2px(24));
        ll_timetext.setVisibility(View.GONE);
    }

    class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Message msg = new Message();
                    msg.what = WHAT_TOP_STATUS;
                    Thread.sleep(1000);

//                    msg.what = WHAT_TIME_STATUS;


//                    createTime();
//                    if (timeCount > 0)
                    handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while (run);
        }
    }

    /**
     * 开机轮播图adapter网络数据
     */
    class BannerAdapter extends PagerAdapter {

        List<SimpleDraweeView> mPagesIV;


        public BannerAdapter(List mPagesIV) {
            this.mPagesIV = mPagesIV;
        }

        @Override
        public int getCount() {
            return mPagesIV.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            SimpleDraweeView imageView = new SimpleDraweeView(container.getContext());
            position = position % mPagesIV.size();
//            imageView.setBackgroundResource(mPagesIV.get(position));
            container.addView(mPagesIV.get(position));
            return mPagesIV.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((SimpleDraweeView) object);
        }
    }

    /**
     * 改变导航栏的颜色
     *
     * @param position
     */
    public void changeDotAndDesc(int position) {
        position = position % tabs_LinearLayout.getChildCount();
        for (int i = 0; i < tabs_LinearLayout.getChildCount(); i++) {
            tabs_LinearLayout.getChildAt(i).setSelected(i == position);
        }
    }

    /**
     * 创建底部导航栏
     */
    private void createBottom() {
        // 将在tabs_LinearLayout里面添加需要的若干选项卡片。
        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.tabs_HorizontalScrollView);
        tabs_LinearLayout = (LinearLayout) findViewById(R.id.tabs_LinearLayout);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) horizontalScrollView.getLayoutParams();
        layoutParams.leftMargin = resolutionUtil.px2dp2pxWidth(170);
        layoutParams.rightMargin = resolutionUtil.px2dp2pxWidth(100);

//        layoutParams.topMargin = resolutionUtil.px2dp2pxHeight(1026);
        layoutParams.bottomMargin = resolutionUtil.px2dp2pxHeight(70);
        horizontalScrollView.setLayoutParams(layoutParams);
        drawablelist = new ArrayList();
        for (int x = 0; x < iconsize; x++) {
            drawablelist.add(null);
        }

        for (int i = 0; i < iconsize; i++) {
            /*View v = LayoutInflater.from(this).inflate(R.layout.view2, null);
            tv = (ImageView) v;
            tv.setFocusable(true);
            tv.setTag(R.id.tag_type, TagType.TYPE_NUM);
            tv.setTag(R.id.tag_index, i);*/
//            tv.setImageResource(icons[i]);
            final TabEntity.DataBean dataBean = data.get(i);
            final List<String> imageurl = dataBean.getImageUrl();
            final Drawable[] mbitmap = new Drawable[3];
            for (int j = 0; j < imageurl.size(); j++) {
                final int finalI = i;
                final int finalJ = j;
                loadToBitmap(imageurl.get(j), new BaseBitmapDataSubscriber() {
                    @Override
                    protected void onNewResultImpl(Bitmap bitmap) {
                        //获取到Bitmap数据转化到对应的数组
                        Bitmap bitmap1 = BitmapUtil.byteArray2Bitmap(BitmapUtil.bitmap2ByteArray(bitmap));
                        //  Log.d(TAG,"获取到的图片大小"+bitmap.getByteCount()+":::"+bitmap1.getByteCount()+"图片的内存地址"+bitmap.toString()+":::"+bitmap1.toString());
                        Drawable bitmapDrawable = new BitmapDrawable(bitmap1);
                        mbitmap[finalJ] = bitmapDrawable;
                        //  drawablelist.add(finalJ,bitmapDrawable);
                        if (mbitmap[0] != null && mbitmap[1] != null && mbitmap[2] != null) {
                            //同一个选择器3个图片下载完毕则生成一个Selector，并添加到对应集合的对应位置
                            Drawable drawable = createDrawableSelector(MainActivity2.this, mbitmap);
                            drawablelist.set(finalI, drawable);
                            //判断集合是否全部生成完毕，是的话发送handler生成视图
                            for (int z = 0; z < drawablelist.size(); z++) {
                                if (drawablelist.get(z) == null) {
                                    return;
                                }
                            }
//                            Log.d(TAG,"发了一条信息"+drawablelist.size());
                            handler.sendEmptyMessageDelayed(TAB, 0);
                        }
                    }

                    @Override
                    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {


                    }
                });


            }
        }

    }

    /**
     * 加载图片成bitmap。
     *
     * @param imageUrl 图片地址。
     */
    public void loadToBitmap(String imageUrl, BaseBitmapDataSubscriber mDataSubscriber) {
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

    /**
     * 动态生成selector
     *
     * @param context
     * @param
     * @return
     */
    private StateListDrawable createDrawableSelector(Context context, Drawable[] mbitmap) {
        StateListDrawable stateList = new StateListDrawable();
        int stateSelected = android.R.attr.state_selected;
        int stateFocused = android.R.attr.state_focused;
        stateList.addState(new int[]{stateSelected, stateFocused}, mbitmap[1]);
        stateList.addState(new int[]{stateSelected, -stateFocused}, mbitmap[2]);
        stateList.addState(new int[]{-stateSelected}, mbitmap[0]);
        return stateList;
    }

    private StateListDrawable createDrawableSelector(Context context, List<Drawable> list) {
        StateListDrawable stateList = new StateListDrawable();
        int stateSelected = android.R.attr.state_selected;
        int stateFocused = android.R.attr.state_focused;
        stateList.addState(new int[]{-stateSelected}, list.get(0));
        stateList.addState(new int[]{stateSelected, stateFocused}, list.get(1));
        stateList.addState(new int[]{stateSelected, -stateFocused}, list.get(2));

        return stateList;
    }

    /**
     * 按行读取txt
     *
     * @param is
     * @return
     * @throws Exception
     */
    private String readTextFromSDcard(InputStream is) throws Exception {
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer("");
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
            buffer.append("\n");
        }
        return buffer.toString();
    }


    /**
     * 创建顶部布局
     */
    private void createTop() {

        createLogoImg();

        createWifiImg();

        createTimeText();

    }

    private void createTimeText() {
        timeText = new TextView(this);
        timeText.setGravity(Gravity.CENTER);
        timeText.setTextColor(this.getResources().getColor(timecolor));
        timeText.setTextSize(resolutionUtil.px2sp2px(36));
        baseLayout.addView(timeText);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) timeText.getLayoutParams();
        layoutParams.width = resolutionUtil.px2dp2pxWidth(97);
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//        layoutParams.height = resolutionUtil.px2dp2pxHeight(43);
        layoutParams.topMargin = resolutionUtil.px2dp2pxHeight(50);
        layoutParams.leftMargin = resolutionUtil.px2dp2pxWidth(1645);
        timeText.setLayoutParams(layoutParams);
    }

    private void createWifiImg() {
        wifiImg = new ImageView(this);
        wifiImg.setScaleType(ImageView.ScaleType.FIT_XY);
        baseLayout.addView(wifiImg);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) wifiImg.getLayoutParams();
        layoutParams.width = resolutionUtil.px2dp2pxWidth(46);
        layoutParams.height = resolutionUtil.px2dp2pxHeight(40);
        layoutParams.topMargin = resolutionUtil.px2dp2pxHeight(54);
        layoutParams.leftMargin = resolutionUtil.px2dp2pxWidth(1775);
        wifiImg.setLayoutParams(layoutParams);
    }

    /**
     * 网络获取logo和背景图
     */
    private void getLogoEntity() {
//        final String logoBgUrl = Constant.IP + LOGO + bussId;
        final String logoBgUrl = Constant.getLOGO(projectId, groupId);

        viewLogo = new SimpleDraweeView(this);
        viewBg = new SimpleDraweeView(this);
        requestGetUrl(logoBgUrl, new VolleyController.VolleyCallback() {
            @Override
            public void onResponse(String response) {
                LogoEntity logoEntity = gson.fromJson(response, LogoEntity.class);
                String logourl = logoEntity.getLogoUrl();
                viewLogo.setImageURI(logourl);
                createLogoImg();
                String bgUrl = logoEntity.getBackgroudUrl();
                //ImageUtil.setImageStyleRound2(mcontext, new SimpleDraweeView(mcontext), R.mipmap.album_default, Uri.parse(bgUrl));

                viewBg.setImageURI(bgUrl);
                baseLayout.setBackgroundDrawable(viewBg.getDrawable());
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }

    private void createLogoImg() {
        ImageView topLogoImg = new ImageView(this);
        topLogoImg.setScaleType(ImageView.ScaleType.FIT_XY);
//        topLogoImg.setImageResource(R.mipmap.logo);
        topLogoImg.setBackground(viewLogo.getDrawable());
        baseLayout.addView(topLogoImg);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) topLogoImg.getLayoutParams();
        layoutParams.width = resolutionUtil.px2dp2pxWidth(145);
        layoutParams.height = resolutionUtil.px2dp2pxHeight(40);
        layoutParams.topMargin = resolutionUtil.px2dp2pxHeight(54);
        layoutParams.leftMargin = resolutionUtil.px2dp2pxWidth(100);
        topLogoImg.setLayoutParams(layoutParams);

    }


    /**
     * viewpager适配器
     */
    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        public Fragment currentFragment;


        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            return fragments.get(pos);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            this.currentFragment = (Fragment) object;
            super.setPrimaryItem(container, position, object);
        }
    }


    /**
     * 创建fragment及viewpager
     */
    private void getPageDatas() {
        try {
            fragments = new ArrayList<Fragment>();
            for (int i = 0; i < dataUrlList.size(); i++) {
                PageItemEntity pageItemEntity = gson.fromJson(dataUrlList.get(i), PageItemEntity.class);
                List<PageItemEntity.DataBean> data = pageItemEntity.getData();

                for (int j = 0; j < data.size(); j++) {
                    PageItemEntity.DataBean dataBean = data.get(j);
                    if (dataBean.vedioPlayUrl != null) {
                        viewpagerIndex = j;
                    }
                }

                Fragment f = PageFragment.newInstance(idList.get(i), pageItemEntity);

                fragments.add(f);
            }
//            new TimeThread().start();
            myFragmentPagerAdapter = new MyFragmentPagerAdapter(this.getSupportFragmentManager());
            mViewPager.setAdapter(myFragmentPagerAdapter);
            mViewPager.setOffscreenPageLimit(dataUrlList.size());
//            mViewPager.setOffscreenPageLimit(0);

            mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrollStateChanged(int arg0) {

                }

                @Override
                public void onPageScrolled(int arg0, float arg1, int arg2) {


                }

                @Override
                public void onPageSelected(int pos) {
//                    Log.d("~~~",""+pos);
                    if (viewpagerIndex == pos) {
//                        Toast.makeText(getApplicationContext(),"fang"+pos, LENGTH_SHORT).show();
//                        mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, voice, 0);
                    } else {
//                        Toast.makeText(getApplicationContext(),"bu"+pos,LENGTH_SHORT).show();
//                        mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
                    }

                    View v = tabs_LinearLayout.getChildAt(pos);
                    changeDotAndDesc(pos);
                    currentselected = pos;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }


    /**
     * 网络加载tab数据
     * //
     */
    public void getTabEntity() {

        try {
//            final String taburl = IP + NAVIGATION + bussId;
            final String taburl = Constant.getNavigation(projectId, groupId);
            requestGetUrl(taburl, new VolleyController.VolleyCallback() {
                @Override
                public void onResponse(String response) {
                    TabEntity tabEntity = gson.fromJson(response, TabEntity.class);
                    data = tabEntity.getData();
//            data = mTabEntity.getData();
                    iconsize = data.size();
                    if (iconsize > 0) {
                        createBottom();
                        for (int i = 0; i < iconsize; i++) {
                            int id = data.get(i).getId();
                            idList.add(id);
                        }
                    }
                    getpageMap(0);
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

        }
    }

    /**
     * 本地加载icon
     */
    private void getTabEntity1() {
        gson = new Gson();
        pageItemEntityList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            try {
                InputStream is = this.getAssets().open("tab.txt");
                String json = readTextFromSDcard(is);

                TabEntity tabEntity = gson.fromJson(json, TabEntity.class);
                data = tabEntity.getData();
//            data = mTabEntity.getData();
                iconsize = data.size();
                if (iconsize > 0) {
                    createBottom();
                    for (int j = 0; j < iconsize; j++) {
                        int id = data.get(j).getId();
                        idList.add(id);
                    }
                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    int index = 0;

    private void getpageMap(int i) {
        if (i >= idList.size()) {
            getPageDatas();
            return;
        }
        int id = idList.get(i);
//        String pageUrl = IP + ADS + id;
        String pageUrl = Constant.getAds(id, projectId, groupId);

        requestGetUrl(pageUrl, new VolleyController.VolleyCallback() {
            @Override
            public void onResponse(String response) {
                dataUrlList.add(response);
                index = index + 1;
                getpageMap(index);
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    private void getAuth() {
        String url = Constant.getAuth("3");
        requestGetUrl(url, new VolleyController.VolleyCallback() {

            @Override
            public void onResponse(String response) {
                AuthEntity authEntity = gson.fromJson(response, AuthEntity.class);
                groupId = authEntity.getGroup_id();
                getSplashEntity();
                getLogoEntity();
                //底部导航栏数据
                getTabEntity();

                createTop();


            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }


    /**
     * 获取实时时间
     *
     * @return
     */

    private String getCurrentTime() {
        String minuteString;
        Time t = new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
        t.setToNow(); // 取得系统时间。
        int year = t.year;
        int month = t.month;
        int date = t.monthDay;
        int hour = t.hour; // 0-23
        int minute = t.minute;
        int second = t.second;

        if (minute < 10) {
            minuteString = "0" + minute;
        } else {
            minuteString = String.valueOf(minute);
        }
        return hour + ":" + minuteString;
    }

    /**
     * 设置网络状态
     */
    private void setNetworkStatus() {
        if (NetworkUtil.isNetworkConnected(this)) {
            if (NetworkUtil.isWifi(this)) {
                wifiImg.setImageResource(R.mipmap.icon_signal_1);
            } else {
                wifiImg.setImageResource(R.mipmap.icon_signal_3);
            }

        } else {
            if (NetworkUtil.isWifi(this)) {
                wifiImg.setImageResource(R.mipmap.icon_signal_1);
            } else {
                wifiImg.setImageResource(R.mipmap.icon_signal_3);
            }
        }
    }

    /**
     * 重写dispatchKeyEvent
     *
     * @param event
     * @return
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    if (dpadDown(event)) {
                        return true;
                    }
                    return false;
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    if (dpadLeft()) {
                        return true;
                    }
                    return false;
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    if (dpadRight()) {
                        return true;
                    }
                    return false;
                case KeyEvent.KEYCODE_BACK:
//                    Intent intent = new Intent(Intent.ACTION_MAIN);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.addCategory(Intent.CATEGORY_HOME);
//                    startActivity(intent);


//
//                    if(numBaseLayout.getVisibility() == View.VISIBLE){
//                        numBaseLayout.setVisibility(View.INVISIBLE);
//                        return true;
//                    }
                    break;
                default:
                    break;
            }

        }
        else  if (event.getAction() == KeyEvent.ACTION_UP){
//            LogUtil.d("ACTION_UP_0",voice+"");
            switch (event.getKeyCode()) {

                case KeyEvent.KEYCODE_VOLUME_UP:
                    voice = mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
                    LogUtil.d("ACTION_UP_1",voice+"");

                    break;
                case KeyEvent.KEYCODE_VOLUME_DOWN:
                    voice = mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
                    LogUtil.d("ACTION_UP_2",voice+"");
                    break;
                default:
                    break;


            }


        }

        return super.dispatchKeyEvent(event);
    }

    /**
     * 遥控向右
     *
     * @return
     */
    private boolean dpadRight() {

        View view = getCurrentFocus();
        if (view != null) {
            Object object = view.getTag(R.id.tag_type);
            if (object != null) {
                int type = (int) object;
                switch (type) {
                    case TagType.TYPE_NUM:
                        int index = (int) view.getTag(R.id.tag_index);
                        if (index == iconsize - 1) {
                            return true;
                        } else {
                            View cv = tabs_LinearLayout.getChildAt(index + 1);
                            cv.requestFocus();
//                            mViewPager.setCurrentItem(index + 1);
                            RelativeLayout fragmentlayout =
                                    (RelativeLayout) myFragmentPagerAdapter.currentFragment.getView().findViewById(R.id.launcher_base_layout);
                            PageLayout childAt = (PageLayout) fragmentlayout.getChildAt(0);
                            HorizontalScrollView scrollView = (HorizontalScrollView) childAt.getChildAt(0);
                            scrollView.scrollTo(0, 0);
                            return true;
                        }
                    case TagType.TYPE_PAGE_ITEM:
                        View nextview = view.focusSearch(FOCUS_RIGHT);
//                        if (nextview instanceof AutoViewPager){
//                            nextview.clearFocus();
//                            Toast.makeText(this,"hh",Toast.LENGTH_SHORT).show();
//                            return true;
//
//                        }
//                        if (nextview==null){
//                            Toast.makeText(MainActivity2.this,"nextview==null",Toast.LENGTH_SHORT).show();
//                            return true;
//                        }
                        if (!((nextview instanceof PageItemView))) {
                            if (currentselected != iconsize - 1) {
//                                Toast.makeText(MainActivity2.this,"nextview==null",Toast.LENGTH_SHORT).show();
                                currentselected += 1;
                                mViewPager.setCurrentItem(currentselected);
                                RelativeLayout fragmentlayout =
                                        (RelativeLayout) myFragmentPagerAdapter.currentFragment.getView().findViewById(R.id.launcher_base_layout);
                                PageLayout childAt = (PageLayout) fragmentlayout.getChildAt(0);
                                int len = childAt.getItemViews().size();
                                PageItemView itemView = childAt.getItemViews().get(0);
                                itemView.requestFocus();
                                Log.d("len", "" + len);
                                Log.d("itemView", "" + itemView);
                                return true;
                            } else
                                return true;
                        }

                    default:
                        break;
                }
            }
        }
        return false;
    }

    /**
     * 遥控向左
     *
     * @return
     */
    private boolean dpadLeft() {

        View view = getCurrentFocus();
        if (view != null) {
            Object object = view.getTag(R.id.tag_type);
            if (object != null) {
                int type = (int) object;
//                Log.d("dpadLeft",""+type);

                switch (type) {
                    case TagType.TYPE_NUM:
                        int index = (int) view.getTag(R.id.tag_index);
                        if (index == 0) {
                            return true;
                        } else {
                            View cv = tabs_LinearLayout.getChildAt(index - 1);
                            cv.requestFocus();
//                            mViewPager.setCurrentItem(index - 1);
                            RelativeLayout fragmentlayout =
                                    (RelativeLayout) myFragmentPagerAdapter.currentFragment.getView().findViewById(R.id.launcher_base_layout);
                            PageLayout childAt = (PageLayout) fragmentlayout.getChildAt(0);
                            HorizontalScrollView scrollView = (HorizontalScrollView) childAt.getChildAt(0);
                            scrollView.scrollTo(0, 0);
                            return true;
                        }
                    case TagType.TYPE_PAGE_ITEM:
                        View nextview = view.focusSearch(FOCUS_LEFT);
//                        if (nextview!=null){
//
//                        }
//                        if (nextview==null){
//                            Toast.makeText(MainActivity2.this,"nextview==null",Toast.LENGTH_SHORT).show();
//                            return true;
//                        }
                        if (!((nextview instanceof PageItemView))) {
                            if (currentselected != 0) {
//                                Toast.makeText(MainActivity2.this,"nextview==null",Toast.LENGTH_SHORT).show();

                                currentselected -= 1;
                                mViewPager.setCurrentItem(currentselected);
                                RelativeLayout fragmentlayout =
                                        (RelativeLayout) myFragmentPagerAdapter.currentFragment.getView().findViewById(R.id.launcher_base_layout);
                                PageLayout childAt = (PageLayout) fragmentlayout.getChildAt(0);
                                int len = childAt.getItemViews().size();

                                PageItemView itemView = childAt.getItemViews().get(len - 1);
                                itemView.requestFocus();
                                Log.d("len", "" + len);
                                Log.d("itemView", "" + itemView);

                                return true;
                            } else
                                return true;
                        }
                    default:
                        break;
                }
            }
        }

        return false;
    }

    /**
     * 遥控向上
     *
     * @return
     */
    private boolean dpadUp() {

        View view = getCurrentFocus();
        if (view != null) {
            Object object = view.getTag(R.id.tag_type);
            if (object != null) {
                int type = (int) object;
                switch (type) {
                    case TagType.TYPE_NUM:

                        return true;
                    case TagType.TYPE_PAGE_ITEM:
                        View nextview = view.focusSearch(View.FOCUS_UP);
                        if (!((nextview instanceof PageItemView) || nextview instanceof AutoViewPager)) {

                            return true;
                        }
                    default:
                        break;
                }
            }
        }
        return false;

    }

    /**
     * 遥控向下
     *
     * @param event
     * @return
     */
    private boolean dpadDown(KeyEvent event) {

        View view = getCurrentFocus();
        if (view != null) {
            Object object = view.getTag(R.id.tag_type);
            if (object != null) {
                int type = (int) object;
                switch (type) {
                    case TagType.TYPE_NUM:
                        return true;
                    case TagType.TYPE_PAGE_ITEM:
                        View nextview = view.focusSearch(FOCUS_DOWN);
                        if ((int) nextview.getTag(R.id.tag_type) == TagType.TYPE_NUM) {
                            tabs_LinearLayout.getChildAt(currentselected).requestFocus();
                        }
                    default:
                        break;
                }
            }
        }
        return false;
    }

    @Override
    public void onDestroy() {

        if (customVideoView != null) {
            customVideoView.stopPlayback();
        }
//        timeThread.interrupt();
//        handler.removeCallbacks(timeThread);
        super.onDestroy();
        run = false;
//        LogUtil.d("onDestroy","onDestroy");
    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (customVideoView != null) {
//            customVideoView.stopPlayback();
//        }
//        handler.removeCallbacks(timeThread);
//    }
//
//    @Override
//    protected void onStop() {
//
//        super.onStop();
//        if (customVideoView != null) {
//            customVideoView.stopPlayback();
//        }
//        handler.removeCallbacks(timeThread);
//    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PageItemView.videoView != null && !PageItemView.videoView.isPlaying()) {
            PageItemView.videoView.start();
        }
    }

    public void setfrescoimage(final String uri, SimpleDraweeView simpleDraweeView, final Context mcontext) {
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
//                Toast.makeText(mcontext, uri, Toast.LENGTH_SHORT).show();
//                handler.sendEmptyMessageDelayed(CHANGETIME, 0);

                timeThread = new TimeThread();
                timeThread.start();
//                ll_timetext.setVisibility(View.VISIBLE);
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
                .setUri(uri)
                // other setters
                .build();
        simpleDraweeView.setController(controller);
    }


}


