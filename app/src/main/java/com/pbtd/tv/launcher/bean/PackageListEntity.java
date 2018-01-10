package com.pbtd.tv.launcher.bean;

/**
 * Created by zhouyong on 2017/4/26.
 *
 *  节目包接口
 *  根据标签分页查询节目包信息，前 30 个节目包以手动排序为主，
 *  后面的数据按照时间倒序。
 */

public class PackageListEntity extends BaseEntity {

    public Data[] data;

    public static class Data{
        public String packageCode;  //节目包代码
        public String packageName;  //节目包名称
        public String packageContent;  //节目包简介
        public String packageCover;   //节目包封面
        public long startTime;  //开始时间
        public long endTime;   //结束时间
        public String chnCode;  //频道代码
        public String chnName;   //频道名称
        public long tagId;  //标签关联 ID
        public String tagName;  //标签名称
        public int totalSeries;  //总集数
        public int currSeries;  //当前集数
        public String playUrl;  //播放地址
        public String packageRegex;  //暂时不用
        public int packageOrder;   //包的顺序，为空时按照更新时 间倒序排列
        public String packagePoster;   //节目包海报
        public int packageStatus; //状态 1:有效 0:无效
    }

}
