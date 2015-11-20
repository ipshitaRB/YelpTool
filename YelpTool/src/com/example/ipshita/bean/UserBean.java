package com.example.ipshita.bean;

import java.io.Serializable;

public class UserBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5757741128490415173L;
	private String user_id;
	private String name;
	public UserBean(String user_id, String name) {
		super();
		this.user_id = user_id;
		this.name = name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
