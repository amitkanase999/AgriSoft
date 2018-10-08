package com.Fertilizer.bean;

import java.util.Date;

public class CustomerBean {

	private String itemName;
	private Double vatPercentage;
	private Double salePrice;
	private Double weight;
	private Long cat_id;
	private Long supplier_id;
	private Long PkGoodreceiveId;
	private Double quantity;
	private String companyName;
	private Long barcodeNo;
	private Double mrp;
	private String expiryDate;
	private String unitName;
	private String batchNumber;
	private String hsn;
	private Double cGst;
	private Double sGst;
	private Double iGst;
	private Double originalQuantity;
	
	private Long quantityl;
	private Date expdate;
	private String availableQty;


	
	
	public String getAvailableQty() {
		return availableQty;
	}
	public void setAvailableQty(String availableQty) {
		this.availableQty = availableQty;
	}
	public Date getExpdate() {
		return expdate;
	}
	public void setExpdate(Date expdate) {
		this.expdate = expdate;
	}
	public Double getQuantity() {
		return quantity;
	}
	public Long getQuantityl() {
		return quantityl;
	}
	public void setQuantityl(Long quantityl) {
		this.quantityl = quantityl;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getOriginalQuantity() {
		return originalQuantity;
	}
	public void setOriginalQuantity(Double originalQuantity) {
		this.originalQuantity = originalQuantity;
	}
	public String getHsn() {
		return hsn;
	}
	public void setHsn(String hsn) {
		this.hsn = hsn;
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
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Double getVatPercentage() {
		return vatPercentage;
	}
	public void setVatPercentage(Double vatPercentage) {
		this.vatPercentage = vatPercentage;
	}
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Long getCat_id() {
		return cat_id;
	}
	public void setCat_id(Long cat_id) {
		this.cat_id = cat_id;
	}
	public Long getSupplier_id() {
		return supplier_id;
	}
	public void setSupplier_id(Long supplier_id) {
		this.supplier_id = supplier_id;
	}
	public Long getPkGoodreceiveId() {
		return PkGoodreceiveId;
	}
	public void setPkGoodreceiveId(Long pkGoodreceiveId) {
		PkGoodreceiveId = pkGoodreceiveId;
	}

	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Long getBarcodeNo() {
		return barcodeNo;
	}
	public void setBarcodeNo(Long barcodeNo) {
		this.barcodeNo = barcodeNo;
	}
	public Double getMrp() {
		return mrp;
	}
	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}
}
