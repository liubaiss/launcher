package com.pbtd.tv.launcher.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gong on 2017/7/11.
 */

public class Tab1 implements Serializable {


    /**
     * timestamp : 20170724110947
     * message : true
     * data : [{"className":"0","height":453,"id":4,"imageUrl":["http://112.13.167.159/upload_37d92c36_268e_47f4_acc2_84b42569f58d_00000040.jpg"],"imageUrls":[{"imageUrl":"http://112.13.167.159/upload_37d92c36_268e_47f4_acc2_84b42569f58d_00000040.jpg"}],"isShowTitle":1,"leftMargin":360,"message":false,"name":"4醉玲珑","navId":1,"packageName":"0","paramKey":"[\"0\"]","paramKeys":["0"],"paramValue":"[\"0\"]","paramValues":["0"],"state":1,"time":"","titleDetail":"4醉玲珑","titleName":"4醉玲珑","topMargin":158,"type":0,"vedioPlayUrl":"0","versionId":0,"width":878},{"className":"0","height":214,"id":5,"imageUrl":["http://112.13.167.159/upload_37d92c36_268e_47f4_acc2_84b42569f58d_00000043.jpg"],"imageUrls":[{"imageUrl":"http://112.13.167.159/upload_37d92c36_268e_47f4_acc2_84b42569f58d_00000043.jpg"}],"isShowTitle":1,"leftMargin":360,"message":false,"name":"5冰雪","navId":1,"packageName":"0","paramKey":"[\"0\"]","paramKeys":["0"],"paramValue":"[\"0\"]","paramValues":["0"],"state":1,"time":"","titleDetail":"5冰雪","titleName":"5冰雪","topMargin":636,"type":0,"vedioPlayUrl":"0","versionId":0,"width":427},{"className":"0","height":214,"id":6,"imageUrl":["http://112.13.167.159/upload_37d92c36_268e_47f4_acc2_84b42569f58d_00000046.jpg"],"imageUrls":[{"imageUrl":"http://112.13.167.159/upload_37d92c36_268e_47f4_acc2_84b42569f58d_00000046.jpg"}],"isShowTitle":1,"leftMargin":812,"message":false,"name":"6伦敦","navId":1,"packageName":"0","paramKey":"[\"0\"]","paramKeys":["0"],"paramValue":"[\"0\"]","paramValues":["0"],"state":1,"time":"","titleDetail":"6伦敦","titleName":"6伦敦","topMargin":636,"type":0,"vedioPlayUrl":"0","versionId":0,"width":427},{"className":"0","height":453,"id":7,"imageUrl":["http://112.13.167.159/upload_37d92c36_268e_47f4_acc2_84b42569f58d_00000049.jpg"],"imageUrls":[{"imageUrl":"http://112.13.167.159/upload_37d92c36_268e_47f4_acc2_84b42569f58d_00000049.jpg"}],"isShowTitle":1,"leftMargin":1263,"message":false,"name":"7我的前半","navId":1,"packageName":"0","paramKey":"[\"0\"]","paramKeys":["0"],"paramValue":"[\"0\"]","paramValues":["0"],"state":1,"time":"","titleDetail":"7我的前半","titleName":"7我的前半","topMargin":158,"type":0,"vedioPlayUrl":"0","versionId":0,"width":350},{"className":"0","height":214,"id":8,"imageUrl":["http://112.13.167.159/upload_37d92c36_268e_47f4_acc2_84b42569f58d_00000052.jpg"],"imageUrls":[{"imageUrl":"http://112.13.167.159/upload_37d92c36_268e_47f4_acc2_84b42569f58d_00000052.jpg"}],"isShowTitle":1,"leftMargin":1263,"message":false,"modifyTime":1500719942000,"name":"8决战","navId":1,"packageName":"0","paramKey":"[\"0\"]","paramKeys":["0"],"paramValue":"[\"0\"]","paramValues":["0"],"state":1,"time":"","titleDetail":"8决战","titleName":"8决战","topMargin":636,"type":0,"vedioPlayUrl":"0","versionId":1,"width":350},{"className":"0","height":214,"id":9,"imageUrl":["http://112.13.167.159/upload_37d92c36_268e_47f4_acc2_84b42569f58d_00000055.jpg"],"imageUrls":[{"imageUrl":"http://112.13.167.159/upload_37d92c36_268e_47f4_acc2_84b42569f58d_00000055.jpg"}],"isShowTitle":1,"leftMargin":1613,"message":false,"modifyTime":1500720075000,"name":"9致青春","navId":1,"packageName":"0","paramKey":"[\"0\"]","paramKeys":["0"],"paramValue":"[\"0\"]","paramValues":["0"],"state":1,"time":"","titleDetail":"9致青春","titleName":"9致青春","topMargin":158,"type":0,"vedioPlayUrl":"0","versionId":1,"width":351},{"className":"0","height":214,"id":10,"imageUrl":["http://112.13.167.159/upload_37d92c36_268e_47f4_acc2_84b42569f58d_00000058.jpg"],"imageUrls":[{"imageUrl":"http://112.13.167.159/upload_37d92c36_268e_47f4_acc2_84b42569f58d_00000058.jpg"}],"isShowTitle":1,"leftMargin":1613,"message":false,"name":"10蓝精灵","navId":1,"packageName":"0","paramKey":"[\"0\"]","paramKeys":["0"],"paramValue":"[\"0\"]","paramValues":["0"],"state":1,"time":"","titleDetail":"10蓝精灵","titleName":"10蓝精灵","topMargin":397,"type":0,"vedioPlayUrl":"0","versionId":0,"width":351}]
     */

    public String timestamp;
    public boolean message;
    public List<DataBean> data;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isMessage() {
        return message;
    }

    public void setMessage(boolean message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * className : 0
         * height : 453
         * id : 4
         * imageUrl : ["http://112.13.167.159/upload_37d92c36_268e_47f4_acc2_84b42569f58d_00000040.jpg"]
         * imageUrls : [{"imageUrl":"http://112.13.167.159/upload_37d92c36_268e_47f4_acc2_84b42569f58d_00000040.jpg"}]
         * isShowTitle : 1
         * leftMargin : 360
         * message : false
         * name : 4醉玲珑
         * navId : 1
         * packageName : 0
         * paramKey : ["0"]
         * paramKeys : ["0"]
         * paramValue : ["0"]
         * paramValues : ["0"]
         * state : 1
         * time :
         * titleDetail : 4醉玲珑
         * titleName : 4醉玲珑
         * topMargin : 158
         * type : 0
         * vedioPlayUrl : 0
         * versionId : 0
         * width : 878
         * modifyTime : 1500719942000
         */

        public String className;
        public int height;
        public int id;
        public int isShowTitle;
        public int leftMargin;
        public boolean message;
        public String name;
        public int navId;
        public String packageName;
        public String paramKey;
        public String paramValue;
        public int state;
        public String time;
        public String titleDetail;
        public String titleName;
        public int topMargin;
        public int type;
        public String vedioPlayUrl;
        public int versionId;
        public int width;
        public long modifyTime;
        public List<String> imageUrl;
        public List<ImageUrlsBean> imageUrls;
        public List<String> paramKeys;
        public List<String> paramValues;

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsShowTitle() {
            return isShowTitle;
        }

        public void setIsShowTitle(int isShowTitle) {
            this.isShowTitle = isShowTitle;
        }

        public int getLeftMargin() {
            return leftMargin;
        }

        public void setLeftMargin(int leftMargin) {
            this.leftMargin = leftMargin;
        }

        public boolean isMessage() {
            return message;
        }

        public void setMessage(boolean message) {
            this.message = message;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNavId() {
            return navId;
        }

        public void setNavId(int navId) {
            this.navId = navId;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getParamKey() {
            return paramKey;
        }

        public void setParamKey(String paramKey) {
            this.paramKey = paramKey;
        }

        public String getParamValue() {
            return paramValue;
        }

        public void setParamValue(String paramValue) {
            this.paramValue = paramValue;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTitleDetail() {
            return titleDetail;
        }

        public void setTitleDetail(String titleDetail) {
            this.titleDetail = titleDetail;
        }

        public String getTitleName() {
            return titleName;
        }

        public void setTitleName(String titleName) {
            this.titleName = titleName;
        }

        public int getTopMargin() {
            return topMargin;
        }

        public void setTopMargin(int topMargin) {
            this.topMargin = topMargin;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getVedioPlayUrl() {
            return vedioPlayUrl;
        }

        public void setVedioPlayUrl(String vedioPlayUrl) {
            this.vedioPlayUrl = vedioPlayUrl;
        }

        public int getVersionId() {
            return versionId;
        }

        public void setVersionId(int versionId) {
            this.versionId = versionId;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public List<String> getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(List<String> imageUrl) {
            this.imageUrl = imageUrl;
        }

        public List<ImageUrlsBean> getImageUrls() {
            return imageUrls;
        }

        public void setImageUrls(List<ImageUrlsBean> imageUrls) {
            this.imageUrls = imageUrls;
        }

        public List<String> getParamKeys() {
            return paramKeys;
        }

        public void setParamKeys(List<String> paramKeys) {
            this.paramKeys = paramKeys;
        }

        public List<String> getParamValues() {
            return paramValues;
        }

        public void setParamValues(List<String> paramValues) {
            this.paramValues = paramValues;
        }

        public static class ImageUrlsBean {
            /**
             * imageUrl : http://112.13.167.159/upload_37d92c36_268e_47f4_acc2_84b42569f58d_00000040.jpg
             */

            public String imageUrl;

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }
        }
    }
}
