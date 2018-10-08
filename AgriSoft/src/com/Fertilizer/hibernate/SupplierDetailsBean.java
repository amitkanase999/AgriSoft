package com.Fertilizer.hibernate;

public class SupplierDetailsBean {

	private long supId;
	private String dealerName;
	private String personName;
	private String city;
	private long contactNo;
	private long landline;
	private String emailId;
	private String tinNo;
	private String address;
	private String gstNum;

	public SupplierDetailsBean() {
		super();
	}

	public SupplierDetailsBean(long supId, String dealerName,
			String personName, String city, long contactNo, long landline,
			String emailId, long tinNo, String address, String gstNum) {
		super();
		this.supId = supId;
		this.dealerName = dealerName;
		this.personName = personName;
		this.city = city;
		this.contactNo = contactNo;
		this.landline = landline;
		this.emailId = emailId;
		this.address = address;
		this.gstNum = gstNum;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getSupId() {
		return supId;
	}
	public void setSupId(long supId) {
		this.supId = supId;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public long getContactNo() {
		return contactNo;
	}
	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}
	public long getLandline() {
		return landline;
	}
	public void setLandline(long landline) {
		this.landline = landline;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getTinNo() {
		return tinNo;
	}

	public void setTinNo(String tinNo) {
		this.tinNo = tinNo;
	}

	public String getGstNum() {
		return gstNum;
	}
	public void setGstNum(String gstNum) {
		this.gstNum = gstNum;
	}
}
