package com.bayside.app.opinion.showdata.sma.model;

import java.util.Date;

public class Article {
	private String id;
	private String userid;
	private String endTime;
	private String startTime;
	private int totalblogger;
	private String province;
	private String location;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public int getTotalblogger() {
		return totalblogger;
	}
	public void setTotalblogger(int totalblogger) {
		this.totalblogger = totalblogger;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
