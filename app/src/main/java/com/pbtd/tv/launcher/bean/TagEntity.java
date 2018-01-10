package com.pbtd.tv.launcher.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouyong on 2017/4/26.
 *
 * 标签接口 按照手动排列的顺序，返回有效的标签信息
 */

public class TagEntity extends BaseEntity {


    public int chnSwitch; //"1",
    public int newestSwitch; // "1",
    public List<Data> data;
    public int autoPlay; //: "0",
    public String code;  //: "A000000",
    public String playConfirm; //: "3"

    public static class Data implements Serializable {
        public String id;
        public String tagName;  //标签名称
        public String tagShowName;  //标签展示名称
        public int tagStatus;   //1:可用 0:禁用
        public int tagOrder;   //展示顺序
        public int epgTotal;  //有效的节目包总数
        public long createTime;  //数据创建时间
        public long updateTime;  //数据更新时间
    }

}
