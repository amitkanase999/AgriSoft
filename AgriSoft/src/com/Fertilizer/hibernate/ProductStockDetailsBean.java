package com.Fertilizer.hibernate;

import java.util.Date;

public class ProductStockDetailsBean {
	
	private Long pk_stock_id;
	private String supplier_name;
	private String companyname;
	private String product_category;
	private String product_name;
	private Long quantity;
	private String batchno;
	private Date expiryDate;
	private Double packing;
	private Date updateDate;
	private String unitName;
	private String godownName;
	private Long TRF_Qty;
	private Long leftover;
	
	private String expDate;
	
	
	
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public Long getTRF_Qty() {
		return TRF_Qty;
	}
	public void setTRF_Qty(Long tRF_Qty) {
		TRF_Qty = tRF_Qty;
	}
	public Long getLeftover() {
		return leftover;
	}
	public void setLeftover(Long leftover) {
		this.leftover = leftover;
	}
	public String getGodownName() {
		return godownName;
	}
	public void setGodownName(String godownName) {
		this.godownName = godownName;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
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
