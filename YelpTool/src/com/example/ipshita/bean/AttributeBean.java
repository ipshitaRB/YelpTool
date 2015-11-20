package com.example.ipshita.bean;

import java.io.Serializable;

public class AttributeBean implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 4190117322594304332L;
	private String business_id;
	private String attribute;
	
	public AttributeBean(String business_id, String attribute) {
		super();
		this.business_id = business_id;
		this.attribute = attribute;
	}

	public String getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(String business_id) {
		this.business_id = business_id;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	
	
}
