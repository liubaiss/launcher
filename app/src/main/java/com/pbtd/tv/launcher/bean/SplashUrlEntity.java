package com.pbtd.tv.launcher.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SS on 2017/6/18.
 */

public class SplashUrlEntity  implements Serializable {


    /**
     * message : true
     * videoURL : mock
     * imageURL : ["1","2","3","4","5"]
     * timestamp : mock
     */

////    private boolean message;
//    private String videoURL;
//    private String timestamp;
//    private int timeCount;
//    private List<String> imageURL;
//
////    public boolean isMessage() {
////        return message;
////    }
////
////    public void setMessage(boolean message) {
////        this.message = message;
////    }
//
//    public String getVideoURL() {
//        return videoURL;
//    }
//
//    public void setVideoURL(String videoURL) {
//        this.videoURL = videoURL;
//    }
//
//    public String getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(String timestamp) {
//        this.timestamp = timestamp;
//    }
//    public int gettimeCount() {
//        return timeCount;
//    }
//
//    public void settimeCount(int timestamp) {
//        this.timeCount = timeCount;
//    }
//
//    public List<String> getImageURL() {
//        return imageURL;
//    }
//
//    public void setImageURL(List<String> imageURL) {
//        this.imageURL = imageURL;
//    }

    private boolean message;
    private int timeCount;
    private String timestamp;
    private String videoURL;
    private List<String> imageURL;

    public boolean isMessage() {
        return message;
    }

    public void setMessage(boolean message) {
        this.message = message;
    }

    public int getTimeCount() {
        return timeCount;
    }

    public void setTimeCount(int timeCount) {
        this.timeCount = timeCount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public List<String> getImageURL() {
        return imageURL;
    }

    public void setImageURL(List<String> imageURL) {
        this.imageURL = imageURL;
    }



}
