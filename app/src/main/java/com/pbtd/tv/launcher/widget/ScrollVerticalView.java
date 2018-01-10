package com.pbtd.tv.launcher.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by yuqihui on 16/9/21.
 */

public class ScrollVerticalView extends LinearLayout {

    private Scroller mScroller;

    private int mLastY;

    public ScrollVerticalView(Context context) {
        super(context);
        init();
    }

    public ScrollVerticalView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollVerticalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(){

        setOrientation(LinearLayout.VERTICAL);

        mScroller = new Scroller(getContext());

    }

    @Override
    public void computeScroll() {
        super.computeScroll();

        if(mScroller != null && mScroller.computeScrollOffset()){

            scrollBy(0, mLastY - mScroller.getCurrY());

            mLastY = mScroller.getCurrY();

            postInvalidate();
        }
    }

    public boolean computeState(){
        if(mScroller == null) return false;
        return mScroller.computeScrollOffset();
    }

    public void smoothScrollToY(int scrollY, boolean isDownAction){

        if(isDownAction) {

            mScroller.startScroll(0, mLastY, 0, -Math.abs(scrollY - mLastY),500);

            postInvalidate();

        }else{

            mScroller.startScroll(0,mLastY,0, Math.abs(mLastY - scrollY),500);

            postInvalidate();

        }
    }

}
