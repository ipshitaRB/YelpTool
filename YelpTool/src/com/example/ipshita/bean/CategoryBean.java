package com.example.ipshita.bean;

import java.io.Serializable;

public class CategoryBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6350188456624109995L;
	private String businessId;
	private String mainCategory;
	private String subCategory;
	public CategoryBean(String businessId, String mainCategory, String subCategory) {
		super();
		this.businessId = businessId;
		this.mainCategory = mainCategory;
		this.subCategory = subCategory;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getMainCategory() {
		return mainCategory;
	}
	public void setMainCategory(String mainCategory) {
		this.mainCategory = mainCategory;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	
	
}
