package com.Fertilizer.hibernate;

import java.util.Date;

public class LoanPaymentBean {
	
	private Long pk_payment_id;
	private String custname;
	private Date paydate;
	private String type;
	private String paymentmode;
	private Double amount;
	private String chequeno;
	private String nameoncheque;
	private String cardno;
	private String accountno;
	private String bankname;
	
	
	
	public Long getPk_payment_id() {
		return pk_payment_id;
	}
	public void setPk_payment_id(Long pk_payment_id) {
		this.pk_payment_id = pk_payment_id;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public Date getPaydate() {
		return paydate;
	}
	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPaymentmode() {
		return paymentmode;
	}
	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
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
	
	
	
	

}
