package com.Fertilizer.bean;

public class PurchaseStockMinus {

	private String productName;
	private String company;
	private Double weightof;
	private Double dupQuantity;

	public PurchaseStockMinus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PurchaseStockMinus(String productName, String company,
			Double weightof, Double dupQuantity) {
		super();
		this.productName = productName;
		this.setCompany(company);
		this.setWeightof(weightof);
		this.dupQuantity = dupQuantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getDupQuantity() {
		return dupQuantity;
	}

	public void setDupQuantity(Double dupQuantity) {
		this.dupQuantity = dupQuantity;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Double getWeightof() {
		return weightof;
	}

	public void setWeightof(Double weightof) {
		this.weightof = weightof;
	}

}
