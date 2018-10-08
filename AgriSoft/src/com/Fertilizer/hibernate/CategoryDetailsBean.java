package com.Fertilizer.hibernate;

public class CategoryDetailsBean {

	private long catId;
	private String categoryName;

	public CategoryDetailsBean() {
		super();
	}

	public CategoryDetailsBean(long catId, String categoryName) {
		super();
		this.catId = catId;
		this.categoryName = categoryName;
	}

	public long getCatId() {
		return catId;
	}
	public void setCatId(long catId) {
		this.catId = catId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
