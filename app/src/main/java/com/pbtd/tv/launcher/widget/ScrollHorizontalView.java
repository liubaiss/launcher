package com.pbtd.tv.launcher.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by yuqihui on 16/9/21.
 */

public class ScrollHorizontalView extends LinearLayout {

    private Scroller mScroller;

    private int mLastX;

    public ScrollHorizontalView(Context context) {
        super(context);
        init();
    }

    public ScrollHorizontalView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollHorizontalView(Context context, AttributeSet attrs, int defStyleAttr) {
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

            scrollBy(0, mLastX - mScroller.getCurrX());

            mLastX = mScroller.getCurrX();

            postInvalidate();
        }
    }

    public boolean computeState(){
        if(mScroller == null) return false;
        return mScroller.computeScrollOffset();
    }

    public void smoothScrollToY(int scrollX, boolean isDownAction){

        if(isDownAction) {

            mScroller.startScroll(0, mLastX, 0, -Math.abs(scrollX - mLastX),500);

            postInvalidate();

        }else{

            mScroller.startScroll(0, mLastX,0, Math.abs(mLastX - scrollX),500);

            postInvalidate();

        }
    }

}
