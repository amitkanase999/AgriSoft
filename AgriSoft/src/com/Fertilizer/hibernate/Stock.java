package com.Fertilizer.hibernate;

import java.util.Date;

public class Stock {

	public long PkStockId;
	public long catID;
	public String productName;
	public String companyName;
	public double weight;
	private double quantity;
	private String batchNum;
	private java.util.Date UpdateDate;

	public Stock() {
		super();
	}

	public Stock(long pkStockId, long catID, String productName,
			String companyName, double weight, double quantity,
			String batchNum, Date updateDate) {
		super();
		PkStockId = pkStockId;
		this.catID = catID;
		this.productName = productName;
		this.companyName = companyName;
		this.weight = weight;
		this.quantity = quantity;
		this.batchNum = batchNum;
		UpdateDate = updateDate;
	}

	public String getBatchNum() {
		return batchNum;
	}
	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}
	public long getPkStockId() {
		return PkStockId;
	}
	public void setPkStockId(long pkStockId) {
		PkStockId = pkStockId;
	}
	public long getCatID() {
		return catID;
	}
	public void setCatID(long catID) {
		this.catID = catID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public java.util.Date getUpdateDate() {
		return UpdateDate;
	}
	public void setUpdateDate(java.util.Date updateDate) {
		UpdateDate = updateDate;
	}
}
