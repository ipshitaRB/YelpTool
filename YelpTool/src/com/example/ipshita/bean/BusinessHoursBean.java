package com.example.ipshita.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class BusinessHoursBean {
	
	/**
	 * 
	 */
	private String business_id;
	private String dayOfTheWeek;
	private String close;
	private String open;
	
	public BusinessHoursBean(String business_id, String dayOfTheWeek, String close, String open) {
		super();
		this.business_id = business_id;
		this.dayOfTheWeek = dayOfTheWeek;
		this.close = close;
		this.open = open;
	}
	public String getBusiness_id() {
		return business_id;
	}
	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}
	public String getDayOfTheWeek() {
		return dayOfTheWeek;
	}
	public void setDayOfTheWeek(String dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
	}
	public String getClose() {
		return close;
	}
	public void setClose(String close) {
		this.close = close;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	
	

}
