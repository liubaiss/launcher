package com.pbtd.tv.launcher.widget;

import android.view.View;

/**
 * Created by yuqihui on 16/8/3.
 */
public interface OnItemSelectListener {

    public void onItemSelected(View v, int position);

    public void onItemFocusChanged(View v, boolean hasFocus);

    public void onItemClick(View v);

}
