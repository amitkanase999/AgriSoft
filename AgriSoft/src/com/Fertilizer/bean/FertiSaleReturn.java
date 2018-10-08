package com.Fertilizer.bean;

import java.math.BigInteger;
import java.util.Date;

public class FertiSaleReturn {

	private BigInteger pkfertilizerBillId;
	private BigInteger pkSeedBillingId;
	private BigInteger pkPesticideBillingId;
	private BigInteger fkGoodsReceive;
	private BigInteger catId;
	private Long quantity;
	private BigInteger availbleQuantity;
	private BigInteger barcode;
	private BigInteger aadhar;
	private String batchNo;
	private String customerName;
	private String productName;
	private String customerHiddenName;
	private String company;
	private Double weight;
	private Double salePrice;
	private Double mrp;
	private Double totalInGrid;
	private BigInteger billNo;
	private String insertDate;
	private Date expiryDate;
	private Double taxPercentage;

	public Double getTaxPercentage() {
		return taxPercentage;
	}
	public void setTaxPercentage(Double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	public BigInteger getPkPesticideBillingId() {
		return pkPesticideBillingId;
	}
	public void setPkPesticideBillingId(BigInteger pkPesticideBillingId) {
		this.pkPesticideBillingId = pkPesticideBillingId;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public BigInteger getPkSeedBillingId() {
		return pkSeedBillingId;
	}
	public void setPkSeedBillingId(BigInteger pkSeedBillingId) {
		this.pkSeedBillingId = pkSeedBillingId;
	}
	public BigInteger getAvailbleQuantity() {
		return availbleQuantity;
	}
	public void setAvailbleQuantity(BigInteger availbleQuantity) {
		this.availbleQuantity = availbleQuantity;
	}
	public BigInteger getPkfertilizerBillId() {
		return pkfertilizerBillId;
	}
	public void setPkfertilizerBillId(BigInteger pkfertilizerBillId) {
		this.pkfertilizerBillId = pkfertilizerBillId;
	}
	public BigInteger getFkGoodsReceive() {
		return fkGoodsReceive;
	}
	public void setFkGoodsReceive(BigInteger fkGoodsReceive) {
		this.fkGoodsReceive = fkGoodsReceive;
	}
	public BigInteger getCatId() {
		return catId;
	}
	public void setCatId(BigInteger catId) {
		this.catId = catId;
	}

	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public BigInteger getBarcode() {
		return barcode;
	}
	public void setBarcode(BigInteger barcode) {
		this.barcode = barcode;
	}
	public BigInteger getAadhar() {
		return aadhar;
	}
	public void setAadhar(BigInteger aadhar) {
		this.aadhar = aadhar;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCustomerHiddenName() {
		return customerHiddenName;
	}
	public void setCustomerHiddenName(String customerHiddenName) {
		this.customerHiddenName = customerHiddenName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	public Double getMrp() {
		return mrp;
	}
	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}
	public Double getTotalInGrid() {
		return totalInGrid;
	}
	public void setTotalInGrid(Double totalInGrid) {
		this.totalInGrid = totalInGrid;
	}
	public BigInteger getBillNo() {
		return billNo;
	}
	public void setBillNo(BigInteger billNo) {
		this.billNo = billNo;
	}

	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

}
