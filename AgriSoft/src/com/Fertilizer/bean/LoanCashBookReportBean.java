package com.Fertilizer.bean;

import java.util.Date;

public class LoanCashBookReportBean {

	private String custname;
	private Long loanno;
	private Date loandate;
	private Double loanamount;
	private Double interest_rate;
	private Double interest_amount;
	private Double interest_per_day;
	private Long Days;
	
	private Date paiddate;
	private Double paidamount;
	private Double paidamountinterest;
	
	
	
	
	public Double getPaidamountinterest() {
		return paidamountinterest;
	}
	public void setPaidamountinterest(Double paidamountinterest) {
		this.paidamountinterest = paidamountinterest;
	}
	public Date getPaiddate() {
		return paiddate;
	}
	public void setPaiddate(Date paiddate) {
		this.paiddate = paiddate;
	}
	public Double getPaidamount() {
		return paidamount;
	}
	public void setPaidamount(Double paidamount) {
		this.paidamount = paidamount;
	}
	public Long getDays() {
		return Days;
	}
	public void setDays(Long days) {
		Days = days;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public Long getLoanno() {
		return loanno;
	}
	public void setLoanno(Long loanno) {
		this.loanno = loanno;
	}
	public Date getLoandate() {
		return loandate;
	}
	public void setLoandate(Date loandate) {
		this.loandate = loandate;
	}
	public Double getLoanamount() {
		return loanamount;
	}
	public void setLoanamount(Double loanamount) {
		this.loanamount = loanamount;
	}
	public Double getInterest_rate() {
		return interest_rate;
	}
	public void setInterest_rate(Double interest_rate) {
		this.interest_rate = interest_rate;
	}
	public Double getInterest_amount() {
		return interest_amount;
	}
	public void setInterest_amount(Double interest_amount) {
		this.interest_amount = interest_amount;
	}
	public Double getInterest_per_day() {
		return interest_per_day;
	}
	public void setInterest_per_day(Double interest_per_day) {
		this.interest_per_day = interest_per_day;
	}
	
	
	
}
