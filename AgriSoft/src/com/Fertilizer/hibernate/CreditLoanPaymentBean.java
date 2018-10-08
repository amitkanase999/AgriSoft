package com.Fertilizer.hibernate;

import java.util.Date;

public class CreditLoanPaymentBean {

	private Long pk_loanpayment_id;
	private String custname;
	private Date paymentdate;
	private String loanno;
	private Double loanamt;
	private String ptype;
	private Double balance;
	private String loanpaymentmode;
	private Double payamt;
	private String chequeno;
	private String nameoncheque;
	private String cardno;
	private String accountno;
	private String bankname;
	
	private Double interestrate;
	private Long day;
	private Double interestamount;
	private Double totalamount;
	
	
	public CreditLoanPaymentBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CreditLoanPaymentBean(Long pk_loanpayment_id, String custname,
			Date paymentdate, String loanno, Double loanamt, String ptype,
			Double balance, String loanpaymentmode, Double payamt,
			String chequeno, String nameoncheque, String cardno,
			String accountno, String bankname, Double interestrate, Long day,
			Double interestamount, Double totalamount) {
		super();
		this.setPk_loanpayment_id(pk_loanpayment_id);
		this.custname = custname;
		this.paymentdate = paymentdate;
		this.loanno = loanno;
		this.loanamt = loanamt;
		this.ptype = ptype;
		this.balance = balance;
		this.loanpaymentmode = loanpaymentmode;
		this.payamt = payamt;
		this.chequeno = chequeno;
		this.nameoncheque = nameoncheque;
		this.cardno = cardno;
		this.accountno = accountno;
		this.bankname = bankname;
		this.interestrate = interestrate;
		this.day = day;
		this.interestamount = interestamount;
		this.totalamount = totalamount;
	}
	public Double getInterestrate() {
		return interestrate;
	}
	public void setInterestrate(Double interestrate) {
		this.interestrate = interestrate;
	}
	public Long getDay() {
		return day;
	}
	public void setDay(Long day) {
		this.day = day;
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
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public Date getPaymentdate() {
		return paymentdate;
	}
	public void setPaymentdate(Date paymentdate) {
		this.paymentdate = paymentdate;
	}
	public String getLoanno() {
		return loanno;
	}
	public void setLoanno(String loanno) {
		this.loanno = loanno;
	}
	public Double getLoanamt() {
		return loanamt;
	}
	public void setLoanamt(Double loanamt) {
		this.loanamt = loanamt;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getLoanpaymentmode() {
		return loanpaymentmode;
	}
	public void setLoanpaymentmode(String loanpaymentmode) {
		this.loanpaymentmode = loanpaymentmode;
	}
	public Double getPayamt() {
		return payamt;
	}
	public void setPayamt(Double payamt) {
		this.payamt = payamt;
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
	public Long getPk_loanpayment_id() {
		return pk_loanpayment_id;
	}
	public void setPk_loanpayment_id(Long pk_loanpayment_id) {
		this.pk_loanpayment_id = pk_loanpayment_id;
	}
	
	
}
