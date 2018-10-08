package com.Fertilizer.bean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class GetProductDetailPO {

	private BigInteger productID;
	private BigInteger SupplierId;
	private String productName;
	private BigDecimal buyPrice;
	private BigDecimal salePrice;
	private Long quantity;
	private BigDecimal weight;
	private String batchNo;
	private String expireDate;
	private BigDecimal quantityForBatchNo;
	private BigDecimal mrp;
	private BigDecimal taxPercentage;
	private Date date;
	private String manufacturer;
	private String unitName;
	private String catId;
	private BigInteger catIDforVAt;
	private Double cGst;
	private Double sGst;
	private String hsn;
	private Double iGst;

	public String getBatchNo() {
		return batchNo;
	}
	public Double getiGst() {
		return iGst;
	}
	public void setiGst(Double iGst) {
		this.iGst = iGst;
	}
	public String getHsn() {
		return hsn;
	}
	public void setHsn(String hsn) {
		this.hsn = hsn;
	}
	public Double getsGst() {
		return sGst;
	}
	public void setsGst(Double sGst) {
		this.sGst = sGst;
	}
	public Double getcGst() {
		return cGst;
	}
	public void setcGst(Double cGst) {
		this.cGst = cGst;
	}

	public BigInteger getCatIDforVAt() {
		return catIDforVAt;
	}
	public void setCatIDforVAt(BigInteger catIDforVAt) {
		this.catIDforVAt = catIDforVAt;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public BigDecimal getMrp() {
		return mrp;
	}
	public void setMrp(BigDecimal mrp) {
		this.mrp = mrp;
	}
	public BigDecimal getTaxPercentage() {
		return taxPercentage;
	}
	public void setTaxPercentage(BigDecimal taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public BigInteger getProductID() {
		return productID;
	}
	public void setProductID(BigInteger productID) {
		this.productID = productID;
	}
	public BigInteger getSupplierId() {
		return SupplierId;
	}
	public void setSupplierId(BigInteger supplierId) {
		SupplierId = supplierId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getQuantityForBatchNo() {
		return quantityForBatchNo;
	}
	public void setQuantityForBatchNo(BigDecimal quantityForBatchNo) {
		this.quantityForBatchNo = quantityForBatchNo;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getCatId() {
		return catId;
	}
	public void setCatId(String catId) {
		this.catId = catId;
	}

}
