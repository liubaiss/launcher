package com.pbtd.tv.launcher.bean;

import java.io.Serializable;

/**
 * Created by gong on 2017/7/28.
 */

public class BussIdEntity implements Serializable {

    /**
     * group_id : 1
     * code : 1
     * message : 操作成功
     */

    private int group_id;
    private int code;
    private String message;

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
