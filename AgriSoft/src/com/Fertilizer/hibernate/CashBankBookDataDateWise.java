package com.Fertilizer.hibernate;

import java.util.Date;

public class CashBankBookDataDateWise {

	private Long pkLastCashId;
	private String date;
	private Double totalAmount;


	public CashBankBookDataDateWise(Long pkLastCashId, String date,
			Double totalAmount) {
		super();
		this.pkLastCashId = pkLastCashId;
		this.date = date;
		this.totalAmount = totalAmount;
	}
	
	public CashBankBookDataDateWise() {
		super();
	}
	
	public Long getPkLastCashId() {
		return pkLastCashId;
	}
	public void setPkLastCashId(Long pkLastCashId) {
		this.pkLastCashId = pkLastCashId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
}
