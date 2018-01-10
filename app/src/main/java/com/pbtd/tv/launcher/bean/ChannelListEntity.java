package com.pbtd.tv.launcher.bean;

import java.util.List;

/**
 * Created by zhouyong on 2017/5/18.
 */

public class ChannelListEntity extends BaseEntity {
    public String name; //"小米",
    public String title;  //"OTT-SDK直播测试",
    public int pageSize;
    public int totalResults;
    public int totalPages;
    public int currentPage;
    public int contentType;
    public List<Datas> datas;

    public static class Datas{
        public int type;
        public Config config;
        public String headerTitle;
        public String apiName;
        public String jumpTitle;
        public List<Data> data;


        public static class Config{
            public int line;
        }

        public static class Data{

            public String videoId; //: 271652,
            public String videoName; //: "BTV体育",
            public int videoType;
            public String description;
            public int videoStatus;
            public String area; //: "北京",
            public String firstChar; //: "b",
            public int score; //: 10,
            public int urlSource; //: 0,
            public int isDownload; //: 0,
            public String recomm; //: "",
            public String intro; //: "",
            public int vip; //: 0,
            public Params params; //: { },
            public String cinema; //: "",
            public float cinemaPrice; //: 0,
            public float videoPrice; //: 0,
            public String years; //: "",
            public int isReview; //: 1,
            public int isShare; //: 0,
            public String videoShareImage; //: "http://s1.starschinalive.com/ottimage/btvtiyu_2500.jpg",
            public String videoImage; //: "http://bsy.imgoss.starschina.com/yunying/pindao/BTV1_24.png",
            public String videoTvImage; //: "",
            public String tvImage; //: "",
            public String shareImage; //: "http://s1.starschinalive.com/ottimage/btvtiyu_2500.jpg",
            public int cpId; //: 47,
            public String cpName; //: "北京电视台",
            public Charge charge;


            public static class Params{

            }

            public static class Charge{
                public int chargeId;
                public String chargeTitle;
            }
        }
    }
}
