package com.pbtd.tv.launcher.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gong on 2017/7/6.
 */


public class TabEntity implements Serializable {


    /**
     * timestamp : 20170711141634
     * message : true
     * data : [{"bussId":1,"className":"时间粉","createTime":1497839045000,"id":37,"imageUrl":["http://i4.piimg.com/599862/a1f669477f98c0ad.png","http://i4.piimg.com/599862/459f3132fddb4a20.png","http://i4.piimg.com/599862/d4fdbed72d68b16d.png"],"imageUrls":"[\"http://i4.piimg.com/599862/a1f669477f98c0ad.png\",\"http://i4.piimg.com/599862/459f3132fddb4a20.png\",\"http://i4.piimg.com/599862/d4fdbed72d68b16d.png\"]","message":false,"modifyTime":1497839243000,"navName":"ss","navRedirect":"ss","packageName":"胜多负少的 ","paramKey":"[\"1\",\"2\",\"3\",\"4\",\"5\"]","paramKeys":["1","2","3","4","5"],"paramValue":"[\"a\",\"b\",\"c\",\"d\",\"e\"]","paramValues":["a","b","c","d","e"],"position":1,"state":0},{"bussId":1,"className":"告诉对方","createTime":1497839111000,"id":38,"imageUrl":["http://i4.piimg.com/599862/a1f669477f98c0ad.png","http://i4.piimg.com/599862/459f3132fddb4a20.png","http://i4.piimg.com/599862/d4fdbed72d68b16d.png"],"imageUrls":"[\"http://i4.piimg.com/599862/a1f669477f98c0ad.png\",\"http://i4.piimg.com/599862/459f3132fddb4a20.png\",\"http://i4.piimg.com/599862/d4fdbed72d68b16d.png\"]","message":false,"modifyTime":1497839122000,"navName":"dd","navRedirect":"dd","packageName":"水电费","paramKey":"[\"1\",\"2\",\"3\",\"4\",\"5\"]","paramKeys":["1","2","3","4","5"],"paramValue":"[\"a\",\"b\",\"c\",\"d\",\"e\"]","paramValues":["a","b","c","d","e"],"position":2,"state":0}]
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
         * bussId : 1
         * className : 时间粉
         * createTime : 1497839045000
         * id : 37
         * imageUrl : ["http://i4.piimg.com/599862/a1f669477f98c0ad.png","http://i4.piimg.com/599862/459f3132fddb4a20.png","http://i4.piimg.com/599862/d4fdbed72d68b16d.png"]
         * imageUrls : ["http://i4.piimg.com/599862/a1f669477f98c0ad.png","http://i4.piimg.com/599862/459f3132fddb4a20.png","http://i4.piimg.com/599862/d4fdbed72d68b16d.png"]
         * message : false
         * modifyTime : 1497839243000
         * navName : ss
         * navRedirect : ss
         * packageName : 胜多负少的
         * paramKey : ["1","2","3","4","5"]
         * paramKeys : ["1","2","3","4","5"]
         * paramValue : ["a","b","c","d","e"]
         * paramValues : ["a","b","c","d","e"]
         * position : 1
         * state : 0
         */

        public int bussId;
        public String className;
        public long createTime;
        public int id;
        public int navId;

//        public String imageUrls;
        public boolean message;
        public long modifyTime;
        public String navName;
        public String navRedirect;
        public String packageName;
        public String paramKey;
        public String paramValue;
        public int position;
        public int state;
        public List<String> imageUrl;
        public List<String> paramKeys;
        public List<String> paramValues;

        public int getBussId() {
            return bussId;
        }

        public void setBussId(int bussId) {
            this.bussId = bussId;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getnavId() {
            return navId;
        }

        public void setnavId(int navId) {
            this.navId = navId;
        }
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


        public boolean isMessage() {
            return message;
        }

        public void setMessage(boolean message) {
            this.message = message;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getNavName() {
            return navName;
        }

        public void setNavName(String navName) {
            this.navName = navName;
        }

        public String getNavRedirect() {
            return navRedirect;
        }

        public void setNavRedirect(String navRedirect) {
            this.navRedirect = navRedirect;
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

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public List<String> getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(List<String> imageUrl) {
            this.imageUrl = imageUrl;
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
    }
}


