package com.pbtd.tv.launcher.bean;

import java.io.Serializable;

public class BaseEntity implements Serializable {
	public static final long serialVersionUID = 1L;
	public String state;
	public long createTime;
	public long updateTime;
	public String message; // "成功",
	public String timestamp; // "20170427143918",
}
