package com.pbtd.tv.launcher.bean;

import java.util.List;

/**
 * Created by zhouyong on 2017/6/2.
 */

public class TestDataEntity extends BaseEntity {

    public List<Data> data;

    public static class Data{
        public int id = -1;
        public int topMargin;   //控件上边距
        public int leftMargin;   //控件左边距
        public int width;   //控件宽度
        public int height;  //控件高端
        public String playUrl; //播放地址
        public String picUrl;    //图片地址
        public String packageName;   //跳转包名
        public String className;   //跳转类明
        public String chnId;   //跳转参数（频道ID）
        public String albumId;   //跳转参数（专辑ID）
        public int picId;  //图片ID，用来改默认图
        public int type = 1; //0:回看 ，1:直播
        public String time;
        public String titleName;
        public int isShowTitle; //0：显示， 1；不显

    }
}
