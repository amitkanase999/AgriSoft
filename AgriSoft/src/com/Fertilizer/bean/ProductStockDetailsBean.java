package com.Fertilizer.bean;

import java.util.Date;

public class ProductStockDetailsBean {
	
	private Long pk_stock_id;
	private String supplier_name;
	private String company_name;
	private String product_category;
	private String product_name;
	private Long quantity;
	private String batchno;
	private Date expiryDate;
	private Double packing;
	private Date updateDate;
	private Long openingQty;
	private Long closingQty;
	private Long sale;
	private Date currentDate;
	
	
	
	
	
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public Date getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	public Long getOpeningQty() {
		return openingQty;
	}
	public void setOpeningQty(Long openingQty) {
		this.openingQty = openingQty;
	}
	public Long getClosingQty() {
		return closingQty;
	}
	public void setClosingQty(Long closingQty) {
		this.closingQty = closingQty;
	}
	public Long getSale() {
		return sale;
	}
	public void setSale(Long sale) {
		this.sale = sale;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Double getPacking() {
		return packing;
	}
	public void setPacking(Double packing) {
		this.packing = packing;
	}
	public Long getPk_stock_id() {
		return pk_stock_id;
	}
	public void setPk_stock_id(Long pk_stock_id) {
		this.pk_stock_id = pk_stock_id;
	}
	public String getSupplier_name() {
		return supplier_name;
	}
	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}
	public String getProduct_category() {
		return product_category;
	}
	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public String getBatchno() {
		return batchno;
	}
	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	
	
	

}
