package com.pbtd.tv.launcher.bean;

/**
 * Created by zhouyong on 2017/5/30.
 */

public class PageControlEntity extends BaseEntity {

    public int isPlayContinue;  // 是否弹出播放继续框  0：弹出，1：不弹出
    public int isShowChnName;   // 是否显示台标： 0：不显示，1:显示
    public int isNumChange;     // 是否支持数字换台  0：不支持，1：支持
    public int isShowLiveList;  // 是否显示直播列表  0：不支持，1：支持
}
