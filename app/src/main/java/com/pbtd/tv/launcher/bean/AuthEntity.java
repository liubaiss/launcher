package com.pbtd.tv.launcher.bean;

/**
 * Created by gong on 2017/8/4.
 */

public class AuthEntity extends BaseEntity {

    /**
     * group_id : 1
     * code : 1
     */

    private String group_id;
    private int code;

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
