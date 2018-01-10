package com.pbtd.tv.launcher.widget;

import android.view.View;

import com.pbtd.tv.launcher.bean.ChannelListEntity;
import com.pbtd.tv.launcher.bean.EpgListEntity;
import com.pbtd.tv.launcher.bean.PackageListEntity;
import com.pbtd.tv.launcher.bean.PageItemEntity;
import com.pbtd.tv.launcher.bean.TabEntity;


/**
 * Created by zhouyong on 2017/5/18.
 */

public abstract class JumpCallback {
    public void onClick(PackageListEntity.Data data) {

}

    public void onClick(ChannelListEntity.Datas.Data data) {

    }
    public void onClick(TabEntity.DataBean data) {

    }


    public void onFocus(int index, View onItemFocusView) {

    }

    public void onClick(EpgListEntity.Data data) {

    }

    public void onClick(PageItemEntity.DataBean data){

    }
}
