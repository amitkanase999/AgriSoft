package com.Fertilizer.hibernate;

import java.util.Date;

public class FertilizerBillingCollectionBean {
	
	private Long pk_collection_id;
	private String customerName;
	private Long billno;
	private Date billdate;
	private Double grosstotal;
	private double interestrate;
	
	public Long getPk_collection_id() {
		return pk_collection_id;
	}
	public void setPk_collection_id(Long pk_collection_id) {
		this.pk_collection_id = pk_collection_id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public Long getBillno() {
		return billno;
	}
	public void setBillno(Long billno) {
		this.billno = billno;
	}
	public Date getBilldate() {
		return billdate;
	}
	public void setBilldate(Date billdate) {
		this.billdate = billdate;
	}
	public Double getGrosstotal() {
		return grosstotal;
	}
	public void setGrosstotal(Double grosstotal) {
		this.grosstotal = grosstotal;
	}
	public double getInterestrate() {
		return interestrate;
	}
	public void setInterestrate(double interestrate) {
		this.interestrate = interestrate;
	}
	
	
	

}
