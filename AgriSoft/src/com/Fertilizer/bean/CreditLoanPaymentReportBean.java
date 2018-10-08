package com.Fertilizer.bean;

import java.util.Date;

public class CreditLoanPaymentReportBean {
	
	/*private String loannum;
	private String cust_name;
	private Double loanamt;
	private Date loandate;*/
	private Double payamout;
	private String paymentmode;
	private Date paydate;
/*	private Long days;
	private Double  interest_rate;
	private Double interest_amount;*/
	
	public Double getPayamout() {
		return payamout;
	}
	public void setPayamout(Double payamout) {
		this.payamout = payamout;
	}
	public String getPaymentmode() {
		return paymentmode;
	}
	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}
	public Date getPaydate() {
		return paydate;
	}
	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}
	
	
	

}
