package com.Fertilizer.hibernate;

import java.util.Date;

public class UpdateGoodReceiveBean {

	private Long fk_suppler_id;
	private Long catId;
	private String productName;
	private String companyName;
	private String hsn;
	private Double buyPrice;
	private Double mrp;
	private Double salePrice;
	private Double packing;
	private Double cGst;
	private Double sGst;
	private Double iGst;
	private Double quantity;
	private Double total;
	private String batchNo;
	private Date expDate;
	
	
	
	
	public Long getFk_suppler_id() {
		return fk_suppler_id;
	}
	public void setFk_suppler_id(Long fk_suppler_id) {
		this.fk_suppler_id = fk_suppler_id;
	}
	public Long getCatId() {
		return catId;
	}
	public void setCatId(Long catId) {
		this.catId = catId;
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
	public String getHsn() {
		return hsn;
	}
	public void setHsn(String hsn) {
		this.hsn = hsn;
	}
	public Double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public Double getMrp() {
		return mrp;
	}
	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	public Double getPacking() {
		return packing;
	}
	public void setPacking(Double packing) {
		this.packing = packing;
	}
	public Double getcGst() {
		return cGst;
	}
	public void setcGst(Double cGst) {
		this.cGst = cGst;
	}
	public Double getsGst() {
		return sGst;
	}
	public void setsGst(Double sGst) {
		this.sGst = sGst;
	}
	public Double getiGst() {
		return iGst;
	}
	public void setiGst(Double iGst) {
		this.iGst = iGst;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	
	
	
	
}
