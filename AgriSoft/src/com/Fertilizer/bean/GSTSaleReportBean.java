package com.Fertilizer.bean;

public class GSTSaleReportBean {

	private String customerType;
	private Double salesTaxable;
	private Double TaxableValue;
	private Double cgst;
	private Double sgst;
	private Double totalTaxAmount;
	
	
	
	
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public Double getSalesTaxable() {
		return salesTaxable;
	}
	public void setSalesTaxable(Double salesTaxable) {
		this.salesTaxable = salesTaxable;
	}
	public Double getTaxableValue() {
		return TaxableValue;
	}
	public void setTaxableValue(Double taxableValue) {
		TaxableValue = taxableValue;
	}
	public Double getCgst() {
		return cgst;
	}
	public void setCgst(Double cgst) {
		this.cgst = cgst;
	}
	public Double getSgst() {
		return sgst;
	}
	public void setSgst(Double sgst) {
		this.sgst = sgst;
	}
	public Double getTotalTaxAmount() {
		return totalTaxAmount;
	}
	public void setTotalTaxAmount(Double totalTaxAmount) {
		this.totalTaxAmount = totalTaxAmount;
	}
	
	
	
}
