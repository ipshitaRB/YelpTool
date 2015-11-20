package com.example.ipshita.bean;

import java.io.Serializable;
import java.sql.Date;

public class ReviewBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 390344440371630052L;
	private String review_id;
	private String user_id;
	private String business_id;
	private int useful;
	private float stars;
	private Date date;
	private String description;
	public ReviewBean(String review_id, String user_id, String business_id, int useful, float stars, Date date,
			String description) {
		super();
		this.review_id = review_id;
		this.user_id = user_id;
		this.business_id = business_id;
		this.useful = useful;
		this.stars = stars;
		this.date = date;
		this.description = description;
	}
	public String getReview_id() {
		return review_id;
	}
	public void setReview_id(String review_id) {
		this.review_id = review_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getBusiness_id() {
		return business_id;
	}
	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}
	public int getUseful() {
		return useful;
	}
	public void setUseful(int useful) {
		this.useful = useful;
	}
	public float getStars() {
		return stars;
	}
	public void setStars(float stars) {
		this.stars = stars;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
