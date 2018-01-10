package com.pbtd.tv.launcher.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zhouyong on 2017/3/20.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration{

    private Context context;
    private int num;
    private int space;

    public SpaceItemDecoration(Context context, int space, int num) {
        this.space = space;
        this.num = num;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);

        outRect.left = space;
        outRect.right = space;
        outRect.top = space;
        outRect.bottom = space;
//        outRect.right = resolutionUtil.px2dp2pxWidth(space);

//        if(position%num != 0){
//            outRect.left = space;
//        }else{
//            outRect.left = 30;
//        }
//        if(position >= num){
//            outRect.top = space;
//        }
//        if(position < num){
//            outRect.top = 30;
//        }


    }
}
