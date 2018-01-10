package com.pbtd.tv.launcher.bean;

import java.io.Serializable;

/**
 * Created by gong on 2017/7/11.
 */

public class LogoEntity implements Serializable {

    /**
     * timestamp : 20170711094547
     * message : false
     * backgroudUrl : http://i1.buimg.com/599862/28ff90ef4ff49481.jpg
     * logoUrl : http://i4.piimg.com/599862/9ee33c32aebdba97.png
     */

    private String timestamp;
    private boolean message;
    private String backgroudUrl;
    private String logoUrl;

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

    public String getBackgroudUrl() {
        return backgroudUrl;
    }

    public void setBackgroudUrl(String backgroudUrl) {
        this.backgroudUrl = backgroudUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
