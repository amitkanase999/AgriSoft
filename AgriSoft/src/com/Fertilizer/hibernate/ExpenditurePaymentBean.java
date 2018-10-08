package com.Fertilizer.hibernate;

import java.util.Date;

public class ExpenditurePaymentBean {

	private Long pkExpPaymentId;
	private Long fkExpDetailId;
	private String serviceProvider;
	private Long contactNumber;
	private String accountantName;
	private Double expcredit;
	private Double expDebit;
	private Double totalAmount;
	private Date insertDate;

	//For Mapping

	private ExpenditureDetailsBean expenditureDetailsBean ;

	public ExpenditurePaymentBean() {
		super();
	}

	public ExpenditurePaymentBean(Long pkExpPaymentId, Long fkExpDetailId,
			String serviceProvider, Long contactNumber, String accountantName,
			Double expcredit, Double expDebit, Double totalAmount,
			Date insertDate, ExpenditureDetailsBean expenditureDetailsBean) {
		super();
		this.pkExpPaymentId = pkExpPaymentId;
		this.fkExpDetailId = fkExpDetailId;
		this.serviceProvider = serviceProvider;
		this.contactNumber = contactNumber;
		this.accountantName = accountantName;
		this.expcredit = expcredit;
		this.expDebit = expDebit;
		this.totalAmount = totalAmount;
		this.insertDate = insertDate;
		this.expenditureDetailsBean = expenditureDetailsBean;
	}

	public ExpenditureDetailsBean getExpenditureDetailsBean() {
		return expenditureDetailsBean;
	}
	public void setExpenditureDetailsBean(
			ExpenditureDetailsBean expenditureDetailsBean) {
		this.expenditureDetailsBean = expenditureDetailsBean;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Long getPkExpPaymentId() {
		return pkExpPaymentId;
	}
	public void setPkExpPaymentId(Long pkExpPaymentId) {
		this.pkExpPaymentId = pkExpPaymentId;
	}
	public Long getFkExpDetailId() {
		return fkExpDetailId;
	}
	public void setFkExpDetailId(Long fkExpDetailId) {
		this.fkExpDetailId = fkExpDetailId;
	}
	public String getServiceProvider() {
		return serviceProvider;
	}
	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}
	public Long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getAccountantName() {
		return accountantName;
	}
	public void setAccountantName(String accountantName) {
		this.accountantName = accountantName;
	}
	public Double getExpcredit() {
		return expcredit;
	}
	public void setExpcredit(Double expcredit) {
		this.expcredit = expcredit;
	}
	public Double getExpDebit() {
		return expDebit;
	}
	public void setExpDebit(Double expDebit) {
		this.expDebit = expDebit;
	}
}
