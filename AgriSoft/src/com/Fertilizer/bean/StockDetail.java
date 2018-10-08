package com.Fertilizer.bean;

import java.math.BigDecimal;
import java.util.Date;

public class StockDetail {

	private String productName;
	private BigDecimal stock;
	private String godownName;
	private String categoryName;
	private String batchNo;
	private String companyName;
	private Double weight;
	private Double quantity;
	private BigDecimal weightInBigDecimal;
	private BigDecimal quantityInBigDecimal;
	private String productCategory;
	private Date expdate;
	private String supplier_name;

	
	
	public String getSupplier_name() {
		return supplier_name;
	}
	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public Date getExpdate() {
		return expdate;
	}
	public void setExpdate(Date expdate) {
		this.expdate = expdate;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getStock() {
		return stock;
	}
	public void setStock(BigDecimal stock) {
		this.stock = stock;
	}
	public String getGodownName() {
		return godownName;
	}
	public void setGodownName(String godownName) {
		this.godownName = godownName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public BigDecimal getWeightInBigDecimal() {
		return weightInBigDecimal;
	}
	public void setWeightInBigDecimal(BigDecimal weightInBigDecimal) {
		this.weightInBigDecimal = weightInBigDecimal;
	}
	public BigDecimal getQuantityInBigDecimal() {
		return quantityInBigDecimal;
	}
	public void setQuantityInBigDecimal(BigDecimal quantityInBigDecimal) {
		this.quantityInBigDecimal = quantityInBigDecimal;
	}

}
