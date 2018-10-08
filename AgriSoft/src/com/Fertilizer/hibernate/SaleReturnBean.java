package com.Fertilizer.hibernate;

import java.io.Serializable;
import java.util.Date;

public class SaleReturnBean implements Serializable{

	private Long pkSaleReturnId;
	private Long fkFertilizerBillId;
	private Long fkSeedBillId;
	private Long fkPesticideBillId;
	private String customerName;
	private Long aadhar;
	private String productName;
	private String company;
	private Long fkCatId;
	private Long BillNo;
	private Double weight;
	private Double availableQuantity;
	private Double returnQuantity;
	private Double salePrice;
	private Double mrp;
	private Double taxPercentage;
	private Double ReturnAmount;
	private String saleDate;
	private Date returnDate;
	private String batchNumber;

	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	public Long getFkSeedBillId() {
		return fkSeedBillId;
	}
	public void setFkSeedBillId(Long fkSeedBillId) {
		this.fkSeedBillId = fkSeedBillId;
	}
	public Long getFkPesticideBillId() {
		return fkPesticideBillId;
	}
	public void setFkPesticideBillId(Long fkPesticideBillId) {
		this.fkPesticideBillId = fkPesticideBillId;
	}
	public String getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public Long getPkSaleReturnId() {
		return pkSaleReturnId;
	}
	public void setPkSaleReturnId(Long pkSaleReturnId) {
		this.pkSaleReturnId = pkSaleReturnId;
	}
	public Long getFkFertilizerBillId() {
		return fkFertilizerBillId;
	}
	public void setFkFertilizerBillId(Long fkFertilizerBillId) {
		this.fkFertilizerBillId = fkFertilizerBillId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getFkCatId() {
		return fkCatId;
	}
	public void setFkCatId(Long fkCatId) {
		this.fkCatId = fkCatId;
	}
	public Long getBillNo() {
		return BillNo;
	}
	public void setBillNo(Long billNo) {
		BillNo = billNo;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getAvailableQuantity() {
		return availableQuantity;
	}
	public void setAvailableQuantity(Double availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	public Double getReturnQuantity() {
		return returnQuantity;
	}
	public void setReturnQuantity(Double returnQuantity) {
		this.returnQuantity = returnQuantity;
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
	public Double getTaxPercentage() {
		return taxPercentage;
	}
	public void setTaxPercentage(Double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Double getReturnAmount() {
		return ReturnAmount;
	}
	public void setReturnAmount(Double returnAmount) {
		ReturnAmount = returnAmount;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Long getAadhar() {
		return aadhar;
	}
	public void setAadhar(Long aadhar) {
		this.aadhar = aadhar;
	}
}
