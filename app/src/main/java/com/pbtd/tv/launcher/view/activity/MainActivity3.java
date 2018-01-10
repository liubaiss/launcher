package com.pbtd.tv.launcher.view.activity;

import android.graphics.Picture;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pbtd.tv.launcher.R;
import com.pbtd.tv.launcher.widget.AutoScrollViewPager;
import com.pbtd.tv.launcher.widget.BaseViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义viewpager demo
 */
public class MainActivity3 extends AppCompatActivity {

    private AutoScrollViewPager mViewPager;

    private String[] paths = {
            "https://ss3.baidu.com/-fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=c493b482b47eca800d053ee7a1229712/8cb1cb1349540923abd671df9658d109b2de49d7.jpg",
            "https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=45fbfa5555da81cb51e684cd6267d0a4/2f738bd4b31c8701491ea047237f9e2f0608ffe3.jpg",
            "https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=ae0e95c0fc1986185e47e8847aec2e69/0b46f21fbe096b63eb314ef108338744ebf8ac62.jpg",
            "https://ss3.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=1fad2b46952397ddc9799f046983b216/dc54564e9258d109c94bbb13d558ccbf6d814de2.jpg",
            "https://ss1.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=ff0999f6d4160924c325a51be406359b/86d6277f9e2f070861ccd4a0ed24b899a801f241.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //初始化AutoScrollViewPager对象
        mViewPager = (AutoScrollViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(new BaseViewPagerAdapter<String>(this,initData(),listener) {
            @Override
            public void loadImage(SimpleDraweeView view, int position, String url) {
                view.setImageURI(url);
            }
        });
    }

    private List<String> initData() {
        List<String> data = new ArrayList<>();
        Picture picture ;
        for (int i = 0 ; i < paths.length ;i++){
            data.add(paths[i]);
        }
        return data;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //记得在销毁的时候调用onDestroy()方法。用来销毁定时器。
        mViewPager.onDestroy();
    }

    //定义点击事件
    private BaseViewPagerAdapter.OnAutoViewPagerItemClickListener listener = new BaseViewPagerAdapter.OnAutoViewPagerItemClickListener<String>() {

        @Override
        public void onItemClick(int position, String url) {
            Toast.makeText(getApplicationContext(),
                    url, Toast.LENGTH_SHORT).show();
        }
    };
}
