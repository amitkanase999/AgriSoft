package com.Fertilizer.bean;

import java.util.Date;

public class CreditLoanBean {

	private Long pk_loan_id;
	private String Loanno;
	private String loanDate;
	private String paidDate;
	private String cust_name;
	private String type;
	private String payment_mode;
	private Date loan_date;
	private Double interest_rate;
	private Double loan_amt;
	private Double paid_amt;
	private String chequeno;
	private String nameoncheque;
	private String cardno;
	private String accountno;
	private String bankname;
	
	
	private Long days;
	private Double interestamount;
	private Double totalamount; 
	
	private Double balance_status;
	private Date paid_date;
	
	private Long MaxLoanNo;
	
	
	
	

	public Long getMaxLoanNo() {
		return MaxLoanNo;
	}
	public void setMaxLoanNo(Long maxLoanNo) {
		MaxLoanNo = maxLoanNo;
	}
	public Double getBalance_status() {
		return balance_status;
	}
	public void setBalance_status(Double balance_status) {
		this.balance_status = balance_status;
	}
	public Date getPaid_date() {
		return paid_date;
	}
	public void setPaid_date(Date paid_date) {
		this.paid_date = paid_date;
	}
	public Double getInterestamount() {
		return interestamount;
	}
	public void setInterestamount(Double interestamount) {
		this.interestamount = interestamount;
	}
	public Double getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}
	public Long getDays() {
		return days;
	}
	public void setDays(Long days) {
		this.days = days;
	}
	public Long getPk_loan_id() {
		return pk_loan_id;
	}
	public void setPk_loan_id(Long pk_loan_id) {
		this.pk_loan_id = pk_loan_id;
	}
	public String getLoanno() {
		return Loanno;
	}
	public void setLoanno(String loanno) {
		Loanno = loanno;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPayment_mode() {
		return payment_mode;
	}
	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}
	public Date getLoan_date() {
		return loan_date;
	}
	public void setLoan_date(Date loan_date) {
		this.loan_date = loan_date;
	}
	public Double getInterest_rate() {
		return interest_rate;
	}
	public void setInterest_rate(Double interest_rate) {
		this.interest_rate = interest_rate;
	}
	public Double getLoan_amt() {
		return loan_amt;
	}
	public void setLoan_amt(Double loan_amt) {
		this.loan_amt = loan_amt;
	}
	public String getChequeno() {
		return chequeno;
	}
	public void setChequeno(String chequeno) {
		this.chequeno = chequeno;
	}
	public String getNameoncheque() {
		return nameoncheque;
	}
	public void setNameoncheque(String nameoncheque) {
		this.nameoncheque = nameoncheque;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getAccountno() {
		return accountno;
	}
	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}
	public Double getPaid_amt() {
		return paid_amt;
	}
	public void setPaid_amt(Double paid_amt) {
		this.paid_amt = paid_amt;
	}
	public String getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}
}
