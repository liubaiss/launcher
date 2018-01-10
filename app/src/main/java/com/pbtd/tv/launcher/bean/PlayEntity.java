package com.pbtd.tv.launcher.bean;

/**
 * Created by zhouyong on 2017/4/27.
 */

public class PlayEntity extends BaseEntity {
    public static final String ID = "PlayEntity";
    public String playName;
    public String playUrl;
    public int type;
    public EpgListEntity.Data data;
    public EpgListEntity epgListEntity;
    public String albumImgUrl;

    public void parseData(EpgListEntity.Data data) {
        playName = data.chnName;
        playUrl = data.playUrl;
        this.data = data;
    }
}
