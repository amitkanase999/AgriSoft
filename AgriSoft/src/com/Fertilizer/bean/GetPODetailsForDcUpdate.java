package com.Fertilizer.bean;

import java.math.BigDecimal;
import java.math.BigInteger;

public class GetPODetailsForDcUpdate {

	//for DC Number update
	private String productName;
	private BigInteger quantity;
	private BigDecimal buyPrice;
	private BigDecimal salePrice;
	private BigDecimal totalAmount;
	private BigDecimal weight;
	private BigInteger dcNum;
	private BigInteger pkPOId;
	private BigInteger poNumber;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigInteger getQuantity() {
		return quantity;
	}
	public void setQuantity(BigInteger quantity) {
		this.quantity = quantity;
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
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public BigInteger getDcNum() {
		return dcNum;
	}
	public void setDcNum(BigInteger dcNum) {
		this.dcNum = dcNum;
	}
	public BigInteger getPkPOId() {
		return pkPOId;
	}
	public void setPkPOId(BigInteger pkPOId) {
		this.pkPOId = pkPOId;
	}
	public BigInteger getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(BigInteger poNumber) {
		this.poNumber = poNumber;
	}

}
