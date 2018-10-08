package com.Fertilizer.hibernate;

public class WholesaleCustomerDetailsBean {

	
	private Long pk_shop_id;
	private String shopname;
	private String address;
	private Long contactno;
	private String emailid;
	private String gstno;
	private String adharno;
	private Long  zipcode;
	
	public Long getPk_shop_id() {
		return pk_shop_id;
	}
	public void setPk_shop_id(Long pk_shop_id) {
		this.pk_shop_id = pk_shop_id;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getContactno() {
		return contactno;
	}
	public void setContactno(Long contactno) {
		this.contactno = contactno;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getGstno() {
		return gstno;
	}
	public void setGstno(String gstno) {
		this.gstno = gstno;
	}
	public String getAdharno() {
		return adharno;
	}
	public void setAdharno(String adharno) {
		this.adharno = adharno;
	}
	public Long getZipcode() {
		return zipcode;
	}
	public void setZipcode(Long zipcode) {
		this.zipcode = zipcode;
	}
	
	
}
