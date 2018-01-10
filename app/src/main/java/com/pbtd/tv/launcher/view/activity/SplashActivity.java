package com.pbtd.tv.launcher.view.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.facebook.drawee.view.SimpleDraweeView;
import com.pbtd.tv.launcher.R;
import com.pbtd.tv.launcher.bean.BussIdEntity;
import com.pbtd.tv.launcher.bean.LogoEntity;
import com.pbtd.tv.launcher.bean.SplashUrlEntity;
import com.pbtd.tv.launcher.bean.TabEntity;
import com.pbtd.tv.launcher.constant.Constant;
import com.pbtd.tv.launcher.utils.MacUtil;
import com.pbtd.tv.launcher.volley.VolleyController;
import com.pbtd.tv.launcher.widget.CustomVideoView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.pbtd.tv.launcher.constant.Constant.ADS;
import static com.pbtd.tv.launcher.constant.Constant.IP;
import static com.pbtd.tv.launcher.constant.Constant.LOGO;
import static com.pbtd.tv.launcher.constant.Constant.NAVIGATION;

/**
 * 开机启动界面
 * Created by SS on 2017/6/13.
 */

public class SplashActivity extends BaseActivity {
    private static final int SHOW_NEXT_PAGE = 0;
    private static final int TO_ACTIVITY = 1;
    private static final long TIME = 5000;
    private static final int WHAT_TIME_STATUS = 2;
    private int timeCount;
    private static long time;
    LogoEntity logoEntity;
    List<String> dataUrlList;
    List<TabEntity.DataBean> data;
    int iconsize;
    List<Integer> idList;

    ArrayList<String> picUriList;

    Uri vedioUri;
    String videoString;
    //    FrameLayout base_splash;
    FrameLayout base_splash;
    private SplashUrlEntity mSplashUrlEntity;
    private List<SimpleDraweeView> mPagesIV;
    private CustomVideoView customVideoView;
    ViewPager viewPager;
    Uri muri;
    TextView timetext;
    LinearLayout ll_timetext;
    View view;
    TextView ad;
    String mac;
    TimeThread timeThread;
    boolean stopThread = false;
    String bussId  = String.valueOf(1);

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_NEXT_PAGE:
                    showNextPage();
                    break;
                case TO_ACTIVITY:
                    Intent intent = new Intent(SplashActivity.this, MainActivity2.class);
                    intent.putExtra("bussId", bussId);
                    startActivity(intent);
                    finish();
                    break;
                case WHAT_TIME_STATUS:
                    timeCount -= 1;
                    if (timeCount < 10) {
                        timetext.setText(0 + "" + timeCount);
                    } else
                        timetext.setText(timeCount + "");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        base_splash = (FrameLayout) findViewById(R.id.base_splash);
        base_splash = (FrameLayout) findViewById(R.id.base_splash);
        viewPager = (ViewPager) findViewById(R.id.pic);
        ll_timetext = (LinearLayout) findViewById(R.id.ll_time);
        view = findViewById(R.id.v);
        timetext = (TextView) findViewById(R.id.time);
        ad = (TextView) findViewById(R.id.ad);
        mac = MacUtil.getLocalEthernetMacAddress();
        idList = new ArrayList<>();
        dataUrlList = new ArrayList<>();

        getSplashEntity();

    }


    @Override
    public void onDestroy() {

        if (customVideoView != null) {
            customVideoView.stopPlayback();

//            handler
        }
//        timeThread.stop();
        handler.removeCallbacks(timeThread);
        stopThread = true;
        super.onDestroy();
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
                    startActivity(new Intent(SplashActivity.this, MainActivity2.class));
                }
            });
        }
        if (picUriList == null) {
            Toast.makeText(SplashActivity.this, "picUriList", Toast.LENGTH_SHORT).show();
        }
        if (picUriList != null && picUriList.size() > 0) {
            mPagesIV = new ArrayList<>();
            int len = picUriList.size();
            for (int i = 0; i < len; i++) {
                SimpleDraweeView view = new SimpleDraweeView(this);
                view.setImageURI(picUriList.get(i));
                mPagesIV.add(view);
                mPagesIV.size();
            }
            showPic();

        }

    }

    /**
     * 自动加载下一页
     */
    private void showNextPage() {
        int currentItem = viewPager.getCurrentItem();
        viewPager.setCurrentItem(currentItem + 1);
        if (!stopThread) {


            if (currentItem == picUriList.size() - 1) {
                handler.sendEmptyMessageDelayed(TO_ACTIVITY, 0);
            } else {
                handler.sendEmptyMessageDelayed(SHOW_NEXT_PAGE, TIME);
            }

        }
    }

    /**
     * 加载开机轮播图
     */

    private void showPic() {
        viewPager.setAdapter(new BannerAdapter(mPagesIV));
//        viewPager.setAdapter(new BannerAdapter(imageResIds));

        viewPager.setCurrentItem(0);
        if (!stopThread) {
            handler.sendEmptyMessageDelayed(SHOW_NEXT_PAGE, TIME);
        }
        createTime();
//        new TimeThread().start();
        timeThread = new TimeThread();
        timeThread.start();
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
        if (timeCount<10){
            timetext.setText(0 + "" + timeCount);
        }else
            timetext.setText(timeCount + "");
        ad.setTextSize(resolutionUtil.px2sp2px(24));
    }

    /**
     * 网络获取到SplanshEntiity
     *
     * @return
     */
    public void getSplashEntity() {
        try {
            String splashUrl = Constant.IP + Constant.SPLASH + bussId;
            requestGetUrl(splashUrl, new VolleyController.VolleyCallback() {
                @Override
                public void onResponse(String response) {

                    SplashUrlEntity splashUrlEntity = gson.fromJson(response, SplashUrlEntity.class);
                    videoString = splashUrlEntity.getVideoURL();
                    picUriList = (ArrayList<String>) splashUrlEntity.getImageURL();
                    timeCount = splashUrlEntity.getTimeCount();
                    if (timeCount != 0) {
                        ll_timetext.setVisibility(View.VISIBLE);

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

//
    class TimeThread extends Thread {
        @Override
        public void run() {
            do {

                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = WHAT_TIME_STATUS;
                    if (timeCount > 0)
                        handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while (!stopThread);
        }
    }


}
