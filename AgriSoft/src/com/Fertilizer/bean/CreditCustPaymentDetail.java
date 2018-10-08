package com.Fertilizer.bean;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CreditCustPaymentDetail {

	private String creditCustomerFirstName;
	private String creditCustomerLastName;
	private Double totalAmount;
	private Double balanceAmount;
	private Double credit;
	private String paymentMode;
	private BigInteger billNo;
	private Double paymentAmount;
	private String PaymentType;
	private String paymentDate;
	private String catName;
	private String accountantName;
	private Double creditAmount;
	private Double debitAmount;

	public Double getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(Double creditAmount) {
		this.creditAmount = creditAmount;
	}
	public Double getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(Double debitAmount) {
		this.debitAmount = debitAmount;
	}
	public String getAccountantName() {
		return accountantName;
	}
	public void setAccountantName(String accountantName) {
		this.accountantName = accountantName;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public String getPaymentType() {
		return PaymentType;
	}
	public void setPaymentType(String paymentType) {
		PaymentType = paymentType;
	}
	public String getCreditCustomerFirstName() {
		return creditCustomerFirstName;
	}
	public void setCreditCustomerFirstName(String creditCustomerFirstName) {
		this.creditCustomerFirstName = creditCustomerFirstName;
	}
	public String getCreditCustomerLastName() {
		return creditCustomerLastName;
	}
	public void setCreditCustomerLastName(String creditCustomerLastName) {
		this.creditCustomerLastName = creditCustomerLastName;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Double getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	public Double getCredit() {
		return credit;
	}
	public void setCredit(Double credit) {
		this.credit = credit;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public BigInteger getBillNo() {
		return billNo;
	}
	public void setBillNo(BigInteger billNo) {
		this.billNo = billNo;
	}

}
