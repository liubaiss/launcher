package com.pbtd.tv.launcher.widget;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pbtd.tv.launcher.R;
import com.pbtd.tv.launcher.utils.ImageUtil;

import java.util.List;

/**
 * Created by gyzhong on 15/2/13.
 */
public class ViewPagerAdapter extends BaseViewPagerAdapter1<String> {
    public Context mcontext;
    
    protected ViewPagerAdapter(Context context, List<String> mDataList) {
        super(context, mDataList);
        mcontext = context;
    }

    @Override
    public void setViewData(View view, String position) {
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.id_view_pager_img);
        if (!TextUtils.isEmpty(position)){
//            simpleDraweeView.setImageURI(position);
            Uri uri = Uri.parse(position);
            ImageUtil.setImageStyleRound2(mcontext, simpleDraweeView, R.mipmap.album_default,uri);
//            ImageUtil.setfrescoloading(simpleDraweeView,uri);
//            view.setImageURI(url);
        }else
        {

        }
//            ImageUtil.setImageStyleRound2(mcontext, simpleDraweeView, R.mipmap.album_default);
//        simpleDraweeView.setImageURI(position);
    }


    @Override
    public View createView(LayoutInflater layoutInflater) {
        View view = layoutInflater.inflate(R.layout.view_pager_item,null) ;
        return view;
    }
}
