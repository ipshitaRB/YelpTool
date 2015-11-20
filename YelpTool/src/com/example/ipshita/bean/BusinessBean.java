package com.example.ipshita.bean;

import java.io.Serializable;

public class BusinessBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7488581781712548696L;


	public BusinessBean(String business_id, String city, String state, String name, float stars) {
		super();
		this.business_id = business_id;
		this.city = city;
		this.state = state;
		this.name = name;
		this.stars = stars;
	}
	private String business_id;
	private String city;
	private String state;
	private String name;
	private float stars;
	
	
	public String getBusiness_id() {
		return business_id;
	}
	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getStars() {
		return stars;
	}
	public void setStars(float stars) {
		this.stars = stars;
	}
}
